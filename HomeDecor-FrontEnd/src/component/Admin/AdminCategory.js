import React, { useEffect, useState } from "react";
import { Link ,useParams ,useNavigate} from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faEdit } from "@fortawesome/free-solid-svg-icons";
import './AdminButton.css';
import {  deleteCategoryById, viewAllCategory } from "../../service/AdminService";

import EditCat from "./EditCategory";
import { ApiUrl } from "../../util/AppConstants";
import AdminNav from "./adminNav";

function AdminCategory(){
    const [cat, setCategory] = useState([]);
    const {id} = useParams();
    const navigate = useNavigate();
    const [userwindow, setUserWindow] = useState(false);
    const toggleUserWindow = (e) => {
        e.preventDefault();
        setUserWindow(!userwindow);
    }
    useEffect(() => {
        viewAllCategory().then(resp => setCategory(resp.data));
        if(parseInt(id)>0){
            deleteCategoryById(id).then(resp=>navigate(-1))
        }
    }, [id])
    /*
    //Checking Admin Logged in or Not
    if (localStorage.getItem('Admin') === null || localStorage.getItem('Admin') !== "true") {
        alert("You need to login as Admin");
        window.location.replace(ApiUrl + "/");
      }
    */
    return(
        <><AdminNav/>
        <div>
            <h1>Category List</h1>
            <div className="table-responsive">
                <table className="table table-bordered">
                    <thead className="thead-dark">
                        <tr>
                            <th>CategoryID</th>
                            <th>CategoryName</th>
                            <th>Edit</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            cat.map(p =>
                                <tr>
                                    <td>{p.categoryId}</td>
                                    <td>{p.categoryName}</td>
                                    <td><Link to={`/cat/Update/${p.categoryId}`}><FontAwesomeIcon icon={faEdit} /></Link></td>
                                    <td><button className="btn btn-danger"><Link to={`/cat/edit/${p.categoryId}`}>Delete</Link></button></td>
                                </tr>
                            )}
                    </tbody>
                </table>
            </div>
        </div>
        </>
    )
}

export default AdminCategory;