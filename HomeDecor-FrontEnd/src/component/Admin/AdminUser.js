import React, { useEffect, useState } from "react";
import { Link,useParams ,useNavigate } from "react-router-dom";
import { deleteUserById, viewAllUsers } from "../../service/AdminService";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faEdit } from "@fortawesome/free-solid-svg-icons";
import './AdminButton.css';
import EditUSer from './EditUser';
import { ApiUrl } from "../../util/AppConstants";
import AdminNav from "./adminNav";


function AdminUser() {
    const [user, setUser] = useState([]);
    const {id} = useParams();
    const navigate = useNavigate();

    const [userwindow, setUserWindow] = useState(false);
    const toggleUserWindow = (e) => {
        e.preventDefault();
        setUserWindow(!userwindow);
    }

    useEffect(() => {
        viewAllUsers().then(resp => setUser(resp.data));
        if(parseInt(id)>0){
            deleteUserById(id).then(resp=>navigate(-1))
        }
    }, [id])
    
    return (
        <><AdminNav/>
        <div>
            <h1>Users List</h1>
            <div className="table-responsive">
                <table className="table table-bordered">
                    <thead className="thead-dark">
                        <tr>
                            <th>userID</th>
                            <th>userName</th>
                            <th>userPassword</th>
                            <th>mobileNumber</th>
                            <th>emailId</th>
                            <th>userRole</th>
                            <th>Edit</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            user.map(p =>
                                <tr>
                                    <td>{p.userID}</td>
                                    <td>{p.userName}</td>
                                    <td>{p.userPassword}</td>
                                    <td>{p.mobileNumber}</td>
                                    <td>{p.emailId}</td>
                                    <td>{p.userRole}</td>
                                    <td><Link to={`/user/Update/${p.userID}`}><FontAwesomeIcon icon={faEdit} /></Link></td>
                                    <td><button className="btn btn-danger"><Link to={`/user/edit/${p.userID}`}>DELETE</Link></button></td>
                                </tr>
                            )}
                    </tbody>
                </table>
            </div>
        </div>
        </>
    )
}
export default AdminUser;
/*<button className="btn btn-primary"><Link to={`/user/find/${p.userID}`}>EDIT</Link></button>* */