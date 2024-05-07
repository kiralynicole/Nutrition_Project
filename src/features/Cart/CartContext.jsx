import React from 'react';
import {createContext, useReducer, useContext} from "react";

const CartContext = createContext();

const cartReducer = (state, action)=>{
    switch(action.type){
        case 'INCREASE_QUANTITY':
            return state.map(item=>
                item.id === action.payload.id ? {...item, quantity: item.quantity + 1} : item
            );
        case 'DECREASE_QUANTITY':
            return state.map(item=>
                item.id === action.payload.id ? {...item, quantity: Math.max(1, item.quantity - 1)} : item
            );    
        case 'ADD_ITEM':
            console.log("added item");
            return [...state, action.payload];
        case 'REMOVE_ITEM':
            console.log("removed item");
            return state.filter(item => item.id !==action.payload.id);
        default:
            return state;        
    }
}



export const CartProvider = ({children}) => {
    const [cart, dispatch] = useReducer(cartReducer, []);

    return(
        <CartContext.Provider value = {{cart, dispatch}}>
            {children}

        </CartContext.Provider>

    )
}

export const useCart = () => useContext(CartContext);