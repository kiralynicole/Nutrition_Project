import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { Nav } from './components/nav/Nav';
import { AuthContextProvider } from './features/Auth/AuthContextProvider';
import { Login} from './features/Auth/Login';
import { Register } from './features/Auth/Register';
import './App.css';
import { Cart } from './features/Cart/Cart';
import { ProductDetails } from './features/details/ProductDetails';
import { DeliveryDetails } from './features/Delivery/DeliveryDetails';
import {HomePage} from './features/home/HomePage';
import { Product } from './features/Product/Product';
import { useState } from 'react';

function App() {
  const [searchText, setSearchText] = useState("");
  return (
    <AuthContextProvider>

        <Nav onSearch = {setSearchText} />
        <Routes>
          <Route path="/" element={<HomePage></HomePage>} />
          <Route path="proteins" element={<Product category="PROTEINMEALS" search={searchText} />} />
          <Route path="snacks" element={<Product category="BARSANDSNACKS" search={searchText} />} />
          <Route path="creatine" element={<Product category="CREATINE" search={searchText} />} />
          <Route path="vitamins" element={<Product category="VITAMINS" search={searchText} />} />
          <Route path="vegan" element={<Product category="VEGAN" search={searchText} />} />
          <Route path="login" element={<Login />} />
          <Route path="register" element={<Register />} />
          <Route path="cart" element={<Cart />} />
          <Route path="*" element={<h1>404 Not Found</h1>} />
          <Route path = "/product/:id" element= {<ProductDetails/>}></Route>
          <Route path = "/delivery-details" element = {<DeliveryDetails/>}></Route>
        </Routes>
        <ToastContainer />

    </AuthContextProvider>
  );
}

//default export
export default App;

// named export
export { App };