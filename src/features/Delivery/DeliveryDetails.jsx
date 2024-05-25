import { useCart } from "../Cart/CartContext";
import "./DeliveryDetails.css"
import { useState } from "react";

export function DeliveryDetails(){
    const {cart} = useCart();
    const[firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [address, setAddress] = useState('');
    const [phone, setPhone] = useState('');
    const [errors, setErrors] = useState({});

    

    function validateFields(){
        let newErrors = {};
        if(!firstName.trim()) newErrors.firstName = "First name is required";
        if (!lastName.trim()) newErrors.lastName = "Last name is required";
        if (!email.trim()) {
            newErrors.email = "Email is required";
        } else if (!/\S+@\S+\.\S+/.test(email)) {
            newErrors.email = "Email is invalid";
        }
        if (!address.trim()) newErrors.address = "Address is required";
        if (!phone.trim()) {
            newErrors.phone = "Phone number is required";
        } else if (!/^\d{10}$/.test(phone.replace(/\D/g, ''))) {
            newErrors.phone = "Phone number is invalid";
        }

        setErrors(newErrors);
        return Object.keys(newErrors).length === 0;
    }

    function generateOrderId() {
        return Math.floor(Math.random() * 1000000); 
    }

    function handleOrder(){
        if(validateFields()){
       
    
        fetch(`http://localhost:8080/users/findEmail/${email}`,{
        method: 'GET'
    }).then(res=>{
        if(!res.ok){
            throw new Error("email not found");
        }
        return res.json();
    }).then(user => {
        const orderId = generateOrderId();
        cart.forEach(item => {

            const orderDetails = {
            idUser: user.id,
            idProduct: item.id,
            quantity: item.quantity,
            id: orderId
        }

    fetch("http://localhost:8080/orders/addOrder", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(orderDetails)
        }).then(res => {
            if (!res.ok) {
                throw new Error('Failed to place order');
            }
            alert("Thank you! Your order has been placed successfully.");
        }).catch(error => {
            console.error('Error:', error);
            alert("An error occurred while placing your order. Please try again.");
        }).catch(() => {
        alert("You don't have an account. Try to register.");
    });

        })
        });
        

        
    }
}

    return (
    <>
    <h1>DELIVERY DETAILS</h1>
    <div className="form-container">
    <div className="form-row">
    <label htmlFor="first-name">First name: </label>
    <input type="text" id="first-name" value = {firstName} onChange={e => setFirstName(e.target.value)}></input>
    {errors.firstName && <div className="error">{errors.firstName}</div>}
    </div>
    <div className="form-row">
    <label htmlFor="last-name">Last name: </label>
    <input type="text" id="last-name" value = {lastName} onChange={e=>setLastName(e.target.value)}></input>
    {errors.lastName && <div className="error">{errors.lastName}</div>}
    </div>
    <div className="form-row">
        <label htmlFor="email">Email: </label>
        <input type="email" id="email" value={email} onChange={e=>setEmail(e.target.value)} />
        {errors.email && <div className="error">{errors.email}</div>}
    </div>
    <div className="form-row">
    <label htmlFor="address">Address: </label>
    <input type="text" id="address" value={address} onChange={e=>setAddress(e.target.value)}></input>
    {errors.address && <div className="error">{errors.address}</div>}
    </div>

    <div className="form-row">
    <label htmlFor="phone">Phone number: </label>
    <input type="phone" id="phone" value={phone} onChange={e=>setPhone(e.target.value)}></input>
    {errors.phone && <div className="error">{errors.phone}</div>}
    </div>

    <button onClick={handleOrder}>Send</button>
        
    </div>
    </>
    );
}