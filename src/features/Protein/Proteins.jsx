import React, { useEffect, useState } from "react";
import { Link } from 'react-router-dom';

import './Protein.css';

export const Proteins = () => {
    const [products, setProducts] = useState([]);
    const [cartStatus, setCartStatus] = useState({});

    useEffect(() => {
        fetch('http://localhost:8080/products')  
            .then(response => response.json())
            .then(allProducts => {
                console.log("Fetched products:", allProducts);
                const proteinProducts = allProducts.filter(product => product.category == 'PROTEINMEALS');
                setProducts(proteinProducts);

                let initialCartStatus = {};
                proteinProducts.forEach(product=>{
                    initialCartStatus[product.id] = false;
                });
                setCartStatus(initialCartStatus);
            })
            .catch(error => {
                console.error('Error fetching products:', error);
            });
    }, []);

    useEffect(() => {
        console.log('Component did update');
        return () => {
            console.log('Component will unmount');
        };
    }, [products]);

    function handleAddToCart(e, productId){
        e.stopPropagation();
        setCartStatus(prev=>({
            ...prev,
            [productId]: !prev[productId]
        }));
    }

    return (
        <div className="product-container">
            {products.map(product => (
                <Link key={product.id} to = {`/product/${product.id}`} className="product-card" >
                    <img src={product.image} alt={product.name} className="product-image" />
                    <div className="product-info">
                        <h3>{product.name}</h3>
                        <p>${product.price}</p>
                        <button onClick={(e)=>handleAddToCart(e, product.id)}>{cartStatus[product.id] ? "Add to Cart" : "Added to Cart"}</button>
                    </div>
                </Link>
            ))}
        </div>
    );
}


