import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { Nav } from './components/nav/Nav';
import { Proteins } from './features/Protein/Proteins';
import { AuthContextProvider } from './features/Auth/AuthContextProvider';
import { Snacks } from './features/Snacks/Snacks';
import { Creatine } from './features/Creatine/Creatine';
import { Vegan } from './features/Vegan/Vegan';
import { Vitamins } from './features/Vitamins/Vitamins';
import { Login} from './features/Auth/Login';
import { Register } from './features/Auth/Register';
import './App.css';
import { Cart } from './features/Cart/Cart';
import { ProductDetails } from './features/details/ProductDetails';
import { SearchProvider } from './features/Search/SearchContext';
import { DeliveryDetails } from './features/Delivery/DeliveryDetails';
import {HomePage} from './features/home/HomePage';

function App() {
  return (
    <AuthContextProvider>
     <SearchProvider>
        <Nav />
        <Routes>
          <Route path="/" element={<HomePage></HomePage>} />
          <Route path="proteins" element={<Proteins />} />
          <Route path="snacks" element={<Snacks />} />
          <Route path="creatine" element={<Creatine />} />
          <Route path="vitamins" element={<Vitamins />} />
          <Route path="vegan" element={<Vegan />} />
          <Route path="login" element={<Login />} />
          <Route path="register" element={<Register />} />
          <Route path="cart" element={<Cart />} />
          <Route path="*" element={<h1>404 Not Found</h1>} />
          <Route path = "/product/:id" element= {<ProductDetails/>}></Route>
          <Route path = "/delivery-details" element = {<DeliveryDetails/>}></Route>
        </Routes>
        <ToastContainer />
     </SearchProvider>
    </AuthContextProvider>
  );
}

//default export
export default App;

// named export
export { App };