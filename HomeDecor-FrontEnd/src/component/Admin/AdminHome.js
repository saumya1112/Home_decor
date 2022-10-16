import React from "react";
import { Link } from "react-router-dom";
import '../../../node_modules/bootstrap/dist/css/bootstrap.min.css'
import ItemDisplay from "../itemModule/item";//Item
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faAt, faFilter, faItem, faUser, faSignOutAlt } from "@fortawesome/free-solid-svg-icons";
import { ApiUrl } from "../../util/AppConstants";
import AdminNav from "./adminNav";
//import '../itemModule/item.css'

function AdminBoard() {

    //Logout
    const logout = () => {
        localStorage.clear();
        window.location.replace(ApiUrl + "/");
    }
    /*
    //Checking Admin Logged in or Not
    if (localStorage.getItem('Admin') === null || localStorage.getItem('Admin') !== "true") {
        alert("You need to login as Admin");
        window.location.replace(ApiUrl + "/");
      }
    */
    return (
        <div>
            <AdminNav />
            <div>
                <ItemDisplay />
            </div>

        </div>
    )
}

export default AdminBoard;