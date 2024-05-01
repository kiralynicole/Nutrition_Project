import { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import './ProductDetails.css';


export function ProductDetails() {
    const { id } = useParams();
    const [product, setProduct] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [isAddedToCart, setIsAddedToCart] = useState(false);

    useEffect(() => {
        const fetchProductDetails = async () => {
            try {
                const response = await fetch(`http://localhost:8080/products/${id}`);
                if (!response.ok) {
                    throw new Error('Failed to fetch');
                }
                const data = await response.json();
                setProduct(data);
                setLoading(false);
            } catch (err) {
                setError(err.message);
                setLoading(false);
            }
        };
        fetchProductDetails();
    }, [id]);

    const handleAddToCart = () => {
        setIsAddedToCart(!isAddedToCart);
    }

    if (loading) return <div>Loading...</div>;
    if (error) return <div>Error: {error}</div>;
    if (!product) return <div>No product found.</div>;


    return (
        <>
        <h1>{product.name}</h1>
        <img src={product.image} alt="No image available" className="prod-img"></img>
        <p className='prod-price'>${product.price}</p>
        
        <button onClick={handleAddToCart}>{isAddedToCart ? 'Added to Cart' : 'Add to Cart'}</button>
        </>
    );
}