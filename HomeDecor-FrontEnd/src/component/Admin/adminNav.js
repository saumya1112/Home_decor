import React from "react";
import { Link } from "react-router-dom";
import '../../../node_modules/bootstrap/dist/css/bootstrap.min.css'
import ItemDisplay from "../itemModule/item";//Item
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faAt, faFilter, faMobile, faUser, faSignOutAlt, faHome } from "@fortawesome/free-solid-svg-icons";
import { ApiUrl } from "../../util/AppConstants";


function AdminNav() {

    //Logout
    const logout = () => {
        localStorage.clear();
        window.location.replace(ApiUrl + "/");
    }
    //Checking Admin Logged in or Not
    if (localStorage.getItem('Admin') === null || localStorage.getItem('Admin') !== "true") {
        //alert("You need to login as Admin");
        window.location.replace(ApiUrl + "/");
    }

    return (
        <div>
            <nav className="navbar navbar-expand navbar-dark bg-dark">
                <Link to={"/admin"} className="navbar-brand">
                    Online <FontAwesomeIcon icon={faHome} /> Home-Decor <FontAwesomeIcon icon={faHome} />
                </Link>
                <div className="navbar-nav mr-auto">
                    <li className="nav-item">
                        <Link to={"/admin/user"} className="nav-link">
                            <FontAwesomeIcon icon={faUser} /> User
                        </Link>
                    </li>
                    <li className="nav-item">
                        <Link to={"/admin/cat"} className="nav-link">
                            <FontAwesomeIcon icon={faFilter} /> Category </Link>
                    </li>
                    <li id="nav-item">
                        <a href="/" className="nav-link" onClick={logout}>
                            <FontAwesomeIcon icon={faSignOutAlt} /> LogOut
                        </a>
                    </li>

                </div>

            </nav>

        </div>
    )
}

export default AdminNav;