import React from "react";
import { useCart } from "./CartContext";
import { useReducer } from "react";
import "./Cart.css"

//const initialCount = 1;

export function Cart(){

    const{dispatch: dispatchCart, cart} = useCart();
    //const [count, dispatch] = useReducer(counterReducer, initialCount);

    function handleRemove(product){
        dispatchCart({type:'REMOVE_ITEM', payload: {id: product.id}});
    }

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
    
    /*
    function counterReducer(oldState, action){
        let newState= oldState;
        switch(action.type){
            case 'add':
                newState = oldState + action.payload;
                break;
            case 'subtract':
                newState = oldState - action.payload;
                break;
                default:
                    throw new Error(`Action ${action.type} is not implemented`)
        }
        return newState;
    }
    */

    return(
        <>
       <h1>Cart</h1>
       <div className="cart-container">
        {cart.length === 0 && <p>Yor cart is empty.</p>}
        {cart.map((product, index) =>(
            <div key = {index} className="cart-item">
                <img src={product.image} className="cart-item-image"/>
                <div className="cart-item-info">
                    <h2>{product.name}</h2>
                    <p className = "total-price">${product.price * product.quantity}</p>
                    <p>Quantity: </p>
                    <div>
                    <button onClick={()=>handleDecreaseQuantity(product.id)} disabled = {product.quantity <= 1}>-</button>
                    <output>{product.quantity}</output>
                     <button onClick={()=>handleIncreaseQuantity(product.id)}>+</button>
                     </div>
                    <button onClick={()=>handleRemove(product)}>Remove from Cart</button>
                    </div>
                    
                     </div> 
                     
                     

             
        ))}
       </div>
        </>
        
    );
}