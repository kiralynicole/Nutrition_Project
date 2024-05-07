
/*
import { useEffect } from 'react';
import './App.css'
import {Button, Container, ThemeProvider} from '@mui/material'
import theme from './styles/theme';
import AppBar from './components/appbar/AppBar';


function App() {
  //const [count, setCount] = useState(0)
  useEffect(()=>{
    document.title = "MyProtein";
  }, []);

  return (
    <ThemeProvider theme={theme}>
        <Container maxwidth = "xl" 
    sx = {{background: '#fff'}}>

    <AppBar></AppBar>

    <Button variant='contained'>TEST</Button>
     </Container>
    </ThemeProvider>
  
  )
}

export default App;
*/


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
import { Auth } from './features/Auth/Auth';
import './App.css';
import { Cart } from './features/Cart/Cart';
import { ProductDetails } from './features/details/ProductDetails';

function App() {
  return (
    <AuthContextProvider>
     
        <Nav />
        <Routes>
          <Route path="/" element={<h1>Homepage</h1>} />
          <Route path="proteins" element={<Proteins />} />
          <Route path="snacks" element={<Snacks />} />
          <Route path="creatine" element={<Creatine />} />
          <Route path="vitamins" element={<Vitamins />} />
          <Route path="vegan" element={<Vegan />} />
          <Route path="login" element={<Auth />} />
          <Route path="register" element={<Auth />} />
          <Route path="cart" element={<Cart />} />
          <Route path="*" element={<h1>404 Not Found</h1>} />
          <Route path = "/product/:id" element= {<ProductDetails/>}></Route>
        </Routes>
        <ToastContainer />
     
    </AuthContextProvider>
  );
}

//default export
export default App;

// named export
export { App };