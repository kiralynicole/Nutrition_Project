import React, { useEffect, useState } from "react";
import { Link } from 'react-router-dom';

import './Product.css';

export const Product = ({category, search}) => {
    const [products, setProducts] = useState([]);

    useEffect( () => {
       const fetchProducts = async () => {
         try{
            let url = 'http://localhost:8080/products';  

           if(search){
            url = `http://localhost:8080/products/searchInput/${search}`
           }

            const response = await fetch(url);
            const allProducts = await response.json();
            const filteredProducts = allProducts.filter(product => product.category === category);
                setProducts(filteredProducts);
            }
        catch(error) {
                console.error('Error fetching products:', error);
        }
    };

    fetchProducts();
    }, [category, search]);

    useEffect(() => {
        console.log('Component did update');
        return () => {
            console.log('Component will unmount');
        };
    }, [products]);


    return (
        <div className="product-container">
            {products.map(product => (
                <Link key={product.id} to = {`/product/${product.id}`} className="product-card" >
                    <img src={product.image} alt={product.name} className="product-image" />
                    <div className="product-info">
                        <h3>{product.name}</h3>
                        <p>${product.price}</p>
                        <button>Buy now</button>
                    </div>
                </Link>
            ))}
        </div>
    );
}


