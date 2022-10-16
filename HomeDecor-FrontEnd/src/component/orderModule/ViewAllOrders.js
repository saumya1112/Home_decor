import React, { useState, useEffect } from 'react';
import { cancelOrder, viewAllOrders } from '../../service/OrderService'
import { Link, useNavigate } from 'react-router-dom';
import { useParams } from 'react-router-dom';
import NavBar from '../navBar/NavBar';


function ViewAllOrders() {
    const [orders, setOrders] = useState([]);
    const [user, setUser] = useState([]);
    const { id } = useParams()
    const navigate=useNavigate();

    useEffect(() => {
        const user = JSON.parse(localStorage.getItem('user'));
        if (user) {
            setUser(user);
           }

        viewAllOrders(user.userID).then(resp =>{ 
            if((resp.data)[0].orderID!=null)
            setOrders(resp.data)});//"userid.userID"
        if(id>0){
            console.log("before Cancel")
        cancelOrder(id).then(resp => navigate(-1));
        }
    }, [id])//userid.userID,id

    return (
        <><NavBar/>
        <div>
            <h1 style={{ color: "dark" }}>Order Details</h1>
            <div class="table-responsive">
                <table class="table table-bordered">
                    <thead class="thead-dark">
                        <tr>
                            <th>OrderId</th>
                            <th>OrderDate</th>
                            <th>Status</th>
                            <th>TotalCost</th>
                            <th>View</th>
                            <th>Cancel</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            orders.map(o =>
                                <tr>
                                    <td>{o.orderID}</td>
                                    <td>{o.orderDate}</td>
                                    <td>{o.status}</td>
                                    <td>{o.totalCost}</td>
                                    <td><Link to={`/order/${o.orderID}`}>Oredered Items</Link></td>
                                    <td id='Cancel'><Link to={`/order/delete/${o.orderID}`}>Cancel</Link></td>
                                </tr>
                            )}
                    </tbody>

                </table>
            </div>

        </div>
        </>
    )
}

export default ViewAllOrders;