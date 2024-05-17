import React, { useState, useEffect } from "react";
import { useCart } from "./CartContext";
import { useReducer } from "react";
import "./Cart.css"
import { useNavigate } from "react-router-dom";


export function Cart(){

    const{dispatch: dispatchCart, cart} = useCart();
    const [totalPrice, setTotalPrice] = useState(0);
    const [deliveryPrice, setDeliveryPrice] = useState(0); 
    const navigation = useNavigate();

    function handleRemove(product){
        dispatchCart({type:'REMOVE_ITEM', payload: {id: product.id}});
    }

    useEffect(()=>{
        const newTotalPrice = cart.reduce((acc, product) => acc + product.quantity * product.price, 0);
        if(newTotalPrice >= 200) {
            setDeliveryPrice(0);
        }else{
            setDeliveryPrice(10);
        }
        console.log(deliveryPrice)
        setTotalPrice(newTotalPrice);

    }, [cart]);

    function handleIncreaseQuantity(productId) {
        const product = cart.find(p => p.id === productId);
        if (product) {
            dispatchCart({
                type: 'ADD_ITEM',
                payload: { ...product, quantity: 1 } 
            });
        }
    }

    function handleDecreaseQuantity(productId) {
        const product = cart.find(p => p.id === productId);
        if (product && product.quantity > 1) {
            dispatchCart({
                type: 'ADD_ITEM',
                payload: { ...product, quantity: -1 } 
            });
        } else {
            handleRemove(productId); 
        }
    }

    function handleAddOrder(){
        console.log(totalPrice, "heere");

        navigation('/delivery-details');
        

    }
    

    return (
        <>
            <h1>Cart</h1>
            <div className="cart-container">
                {cart.length === 0 ? (
                    <p>Your cart is empty.</p>
                ) : (
                    <>
                        {cart.map((product, index) => (
                            <div key={index} className="cart-item">
                                <img src={product.image} className="cart-item-image" />
                                <div className="cart-item-info">
                                    <h2>{product.name}</h2>
                                    <p className="total-price">${product.price * product.quantity}</p>
                                    <p>Quantity: </p>
                                    <div>
                                        <button onClick={() => handleDecreaseQuantity(product.id)} disabled={product.quantity <= 1}>-</button>
                                        <output>{product.quantity}</output>
                                        <button onClick={() => handleIncreaseQuantity(product.id)}>+</button>
                                    </div>
                                    <button onClick={() => handleRemove(product)}>Remove from Cart</button>
                                </div>
                            </div>
                        ))}
                        <div >Price : ${totalPrice}</div>
                        <div >Delivery price : ${deliveryPrice}</div>
                        <div className="total-price">Total price : ${deliveryPrice + totalPrice}</div>

                        <button className="order-button" onClick={handleAddOrder}>Send Order</button>
                    </>
                )}
            </div>
        </>
    );
}