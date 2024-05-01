import React, { useEffect, useState } from "react";

export function Creatine(){

    const [products, setProducts] = useState([]);

    useEffect(() => {
        fetch('http://localhost:8080/products')  
            .then(response => response.json())
            .then(allProducts => {
                console.log("Fetched products:", allProducts);
                const proteinProducts = allProducts.filter(product => product.category == 'CREATINE');
                setProducts(proteinProducts);
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

    return (
        <div>
            <h1>Creatine</h1>
            <ul>
                {products.map(product => (
                    <li key={product.id}>
                        {product.name} - Price: {product.price}
                    </li>
                ))}
            </ul>
        </div>
    );
}