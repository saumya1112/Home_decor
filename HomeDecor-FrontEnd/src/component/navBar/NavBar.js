import { useNavigate, NavLink } from "react-router-dom";
import './nav.css';
import React, { useState } from 'react';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faHome, faUserAlt, faShoppingBag, faShoppingCart, faSignOut, faSearch, faMobileRetro } from "@fortawesome/free-solid-svg-icons";
import Profile from "./Profile";

const NavBar = () => {

  const path = window.location.pathname;
  let navigate = useNavigate();
  const handleLogout = () => {

    localStorage.removeItem('Admin');
    localStorage.removeItem('user');

    navigate("../", { replace: true });

  }

  const [search, setSearch] = useState("");




  return (
    <div>
      <nav className='navclass'>
        <div className='navelecontainer'>
          <ul className='navrightele'>
            <li className='eleitem'><div className='navele'>
              <FontAwesomeIcon icon={faMobileRetro} /> Home Decor

            </div></li>
          </ul>
        </div>

        <div className='navelecontainer'>
          <ul className='navleftele'>


            <input type="text" placeholder="Search" id="search"
              value={search} onChange={(event) => setSearch(event.target.value)} />

            <NavLink to={`/search/${(search != null) ? search : "null"}`}>
              <li className='eleitem'><div className='navele' >
                <FontAwesomeIcon icon={faSearch} />
              </div></li>
            </NavLink>

            <NavLink to={"/home"}>
              <li className='eleitem'><div className='navele' >
                <FontAwesomeIcon icon={faHome} /> Home
              </div></li>
            </NavLink>

            <NavLink to={"/profile"}>
              <li className='eleitem'><div className='navele' >
                <FontAwesomeIcon icon={faUserAlt} /> Profile
              </div></li>
            </NavLink>

            <NavLink to={"/order"}>
              <li className='eleitem'><div className='navele' >
                <FontAwesomeIcon icon={faShoppingBag} />  Order
              </div></li>
            </NavLink>
            <NavLink to={"/cart"}>
              <li className='eleitem'><div className='navele' >
                <FontAwesomeIcon icon={faShoppingCart} />  Cart
              </div></li>
            </NavLink>
            <li className='eleitem'><div className='navele' onClick={(e) => handleLogout()}>
              <FontAwesomeIcon icon={faSignOut} />  Logout
            </div></li>
          </ul>
        </div>
      </nav>

    </div>
  )
}

export default NavBar
