import React from "react";
import { useCart } from "./CartContext";
import { useReducer } from "react";
import "./Cart.css"

const initialCount = 1;

export function Cart(){

    const{dispatch: dispatchCart, cart} = useCart();
    const [count, dispatch] = useReducer(counterReducer, initialCount);

    function handleRemove(product){
        dispatchCart({type:'REMOVE_ITEM', payload: {id: product.id}});
    }
    
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
                    <p className = "total-price">${product.price * count}</p>
                    <p>Quantity: </p>
                    <div>
                    <button onClick={()=>dispatch({type:'subtract', payload:1})} disabled = {count <= 1}>-</button>
                    <output>{count}</output>
                     <button onClick={()=>dispatch({type:'add', payload:1})}>+</button>
                     </div>
                    <button onClick={()=>handleRemove(product)}>Remove from Cart</button>
                    </div>
                    
                     </div> 
                     
                     

             
        ))}
       </div>
        </>
        
    );
}