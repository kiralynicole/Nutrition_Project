import { useState, useEffect, useReducer, useContext } from 'react';
import { useParams } from 'react-router-dom';
import { useCart } from '../Cart/CartContext';
import './ProductDetails.css';
import { AuthContext } from '../Auth/AuthContextProvider';

const initialCount = 1;

export function ProductDetails() {
    //const {user} = useContext(AuthContext);
    const { id } = useParams();
    const [product, setProduct] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const{dispatch: cartDispatch, cart} = useCart();
    const [productQuantity, setProductQuantity] = useState(1);


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

    

    const increase = () => {
        setProductQuantity((quantity) => quantity + 1);
    }

    const decrease = () =>{
        setProductQuantity((quantity) => quantity - 1);
    }


    const handleAddToCart = (product) => {
        const action = cart.some(p => p.id === product.id) ? 'REMOVE_ITEM' : 'ADD_ITEM';
        cartDispatch({
            type: action,
            payload:{
                ...product,
                quantity: productQuantity
            }
        })
    }



    if (loading) return <div>Loading...</div>;
    if (error) return <div>Error: {error}</div>;
    if (!product) return <div>No product found.</div>;


    return (
        <div className='prod-details' >
        <img src={product.image} alt="No image available" className="prod-img"></img>
        <div >
        <h1>{product.name}</h1>
        <p>{product.description}</p>
        <p className='prod-price'>${product.price * productQuantity}</p>
         <p>Quantity:</p>
         <div>
         <button onClick={decrease} disabled = {productQuantity <= 1}>-</button>
       <output>{productQuantity}</output>
        <button onClick={increase}>+</button>
        </div>
        <button onClick={()=>handleAddToCart(product)}>
            {cart.some(p=>p.id === product.id) ? 'Remove from Cart' : 'Add To Cart'}
            </button>
            </div>
        </div>
    );
}