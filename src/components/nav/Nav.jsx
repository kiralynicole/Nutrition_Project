import { NavLink } from 'react-router-dom';
import styles from './Nav.module.css';
import { useAuthContext } from '../../features/Auth/AuthContextProvider';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faHome, faSearch, faShoppingCart } from '@fortawesome/free-solid-svg-icons';
import { useState } from 'react';


function BrandNavLink({ children, ...props }) {
  return (
    <NavLink
      {...props}
      className={({ isActive }) => (isActive ? styles.active : '')}
    >
      {children}
    </NavLink>
  );
}



export function Nav({onSearch}) {

  const [showSearch, setShowSearch] = useState(false);
  const [searchText, setSearchText] = useState("");
  const [isCartVisible, setIsCartVisible] = useState(false);

  const {user, isAuthenticated, login, logout} = useAuthContext();


  const toggleCart = ()=> setIsCartVisible(!isCartVisible);

  
  function toggleSearch(event){
    if(showSearch){
      if(searchText.trim() !== ""){
        handleSearchSubmit(event);
      }
    }else{
      setShowSearch(!showSearch);
    }
    
  }
  
  function handleSearchSubmit(event){
    //this function will search for some words in page
    event.preventDefault();
    console.log("Searching for: ", searchText);
    if (searchText.trim() !== "") {
      onSearch(searchText);
    }
    setShowSearch(!showSearch);
    setSearchText("");
  }

  function handleInputChange(event){
    setSearchText(event.target.value);
  }

  return (
    <nav className={styles.mainNav}>
      <menu>
        <li>
          <BrandNavLink to="/">
          <FontAwesomeIcon icon = {faHome}></FontAwesomeIcon>
          </BrandNavLink>
        </li>
        <li>
          <BrandNavLink to="proteins">Protein Meals</BrandNavLink>
        </li>
        <li>
          <BrandNavLink to="snacks">Bars and Snacks</BrandNavLink>
        </li>
        <li>
          <BrandNavLink to = "creatine">Creatine</BrandNavLink>
        </li>
        <li>
          <BrandNavLink to = "vitamins">Vitamins</BrandNavLink>
        </li>
        <li>
          <BrandNavLink to = "vegan">Vegan</BrandNavLink>
        </li>

      <li>
        <button onClick = {toggleSearch} className={styles.iconButton}>
          <FontAwesomeIcon icon={faSearch}></FontAwesomeIcon>
        </button>
      </li>

      {showSearch && (
        <form onSubmit={handleSearchSubmit} className={styles.searchForm}>
          <input className={styles.searchInput} type="text" placeholder='Search...' value={searchText} onChange={handleInputChange} autoFocus/>
        
        </form>
      )}

      {!isAuthenticated && (
          <>
            <li className={styles.shiftRight}>
              <BrandNavLink to="login">Login</BrandNavLink>
            </li>
            <li>
              <BrandNavLink to="register">Register</BrandNavLink>
            </li>
          </>

      )};

      {isAuthenticated && (
        <div className={styles.shiftRight }>
        <p>Welcome, {user?.name}</p>
        <button onClick={logout} className={styles.logoutButton}>Logout</button>
        </div>
      )}
       
         <li>
              <BrandNavLink to="cart" onClick = {toggleCart}>
                <FontAwesomeIcon icon = {faShoppingCart} />
              </BrandNavLink>
            </li>

      </menu>
    </nav>
  );
}