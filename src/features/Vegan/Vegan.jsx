import React, { useEffect, useState } from "react";

export function Vegan(){

    const [products, setProducts] = useState([]);

    useEffect(() => {
        fetch('http://localhost:8080/products')  
            .then(response => response.json())
            .then(allProducts => {
                console.log("Fetched products:", allProducts);
                const veganProducts = allProducts.filter(product => product.category == 'VEGAN');
                setProducts(veganProducts);
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
            <h1>Vegan</h1>
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