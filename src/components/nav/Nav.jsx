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



export function Nav() {
  const { user, logout } = useAuthContext();
  const [showSearch, setShowSearch] = useState(false);
  const [searchText, setSearchText] = useState("");
  
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

        {user && (
          <li className={styles.shiftRight}>
            Welcome, {user.firstName}!
            <a
              href="/"
              onClick={(e) => {
                e.preventDefault();
                logout();
              }}
            >
              Logout
            </a>
          </li>
        )}

        {!user && (
          <>
            <li className={styles.shiftRight}>
              <BrandNavLink to="login">Login</BrandNavLink>
            </li>
            <li>
              <BrandNavLink to="register">Register</BrandNavLink>
            </li>
          </>
        )}
         <li>
              <BrandNavLink to="cart">
                <FontAwesomeIcon icon = {faShoppingCart}></FontAwesomeIcon>
              </BrandNavLink>
            </li>
      </menu>
    </nav>
  );
}