import { useState, useEffect, useReducer } from 'react';
import { useParams } from 'react-router-dom';
import { useCart } from '../Cart/CartContext';
import './ProductDetails.css';

const initialCount = 1;

export function ProductDetails() {
    const { id } = useParams();
    const [product, setProduct] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [count, dispatch] = useReducer(counterReducer, initialCount);
    const{dispatch: cartDispatch, cart} = useCart();


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


    const handleAddToCart = (product) => {
        const action = cart.some(p => p.id === product.id) ? 'REMOVE_ITEM' : 'ADD_ITEM';
        cartDispatch({ type: action, payload: product });
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



    if (loading) return <div>Loading...</div>;
    if (error) return <div>Error: {error}</div>;
    if (!product) return <div>No product found.</div>;


    return (
        <div className='prod-details' >
        <img src={product.image} alt="No image available" className="prod-img"></img>
        <div >
        <h1>{product.name}</h1>
        <p>{product.description}</p>
        <p className='prod-price'>${product.price}</p>
         <p>Quantity:</p>
         <div>
         <button onClick={()=>dispatch({type:'subtract', payload:1})} disabled = {count <= 1}>-</button>
       <output>{count}</output>
        <button onClick={()=>dispatch({type:'add', payload:1})}>+</button>
        </div>
        <button onClick={()=>handleAddToCart(product)}>
            {cart.some(p=>p.id === product.id) ? 'Remove from Cart' : 'Add To Cart'}
            </button>
            </div>
        </div>
    );
}