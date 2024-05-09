import React from 'react';
import {createContext, useReducer, useContext} from "react";

const CartContext = createContext();

const cartReducer = (state, action)=>{
    switch(action.type){
       
        case 'ADD_ITEM':
            console.log("added item");
            const existingItemIndex = state.findIndex(item=> item.id === action.payload.id);
            if(existingItemIndex !== -1){
                const newState = [...state];
                newState[existingItemIndex] = {
                    ...newState[existingItemIndex],
                    quantity: newState[existingItemIndex].quantity + action.payload.quantity
                }
                return newState;
            }
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