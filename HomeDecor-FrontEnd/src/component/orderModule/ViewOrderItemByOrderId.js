import React, { useState, useEffect } from 'react';
import { fetchOrderItemByOrderId } from '../../service/OrderService'
import { useParams } from 'react-router-dom';
import { Link } from 'react-router-dom';
import NavBar from '../navBar/NavBar';

function ViewOrderItemByOrderId() {
    const [orderItem, setOrderItem] = useState([]);
    const { id } = useParams();
    useEffect(() => {
        fetchOrderItemByOrderId(id).then(resp => {
            if ((resp.data)[0].quantity != null)
                setOrderItem(resp.data)
        });
    }, [id])
    return (
        <><NavBar />
            <div>
                <h1 style={{ color: "dark" }}>Order Items Details</h1>
                <div class="table-responsive">
                    <table class="table table-bordered">
                        <thead class="thead-dark">
                            <tr>
                                <th>ItemName</th>
                                <th>Quantity</th>
                                <th>TotalCost</th>
                                <th>ItemDetails</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                orderItem.map(o =>
                                    <tr>
                                        <td>{o.itemName}</td>
                                        <td>{o.quantity}</td>
                                        <td>{o.totalCost}</td>
                                        <td><Link to={`/home/product/${o.itemId}`} >Details</Link></td>
                                    </tr>
                                )}
                        </tbody>
                    </table>
                </div>
            </div>
        </>
    )
}

export default ViewOrderItemByOrderId;