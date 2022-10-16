
import { fetchAllItems,deleteItembyCartid } from '../../service/ProductService';
import { Link, useParams ,useNavigate } from 'react-router-dom';
import React, { useEffect, useState } from 'react';
import NavBar from '../navBar/NavBar';
import '../../util/Form.css'
import { addOrder,updateOrder } from '../../service/OrderService';
import { ApiUrl } from '../../util/AppConstants';

const Cart = () => {
    
    const [products, setProducts] = useState([]);
    //const [quantity, setQuantity] = useState(1);
    const {id} = useParams();
    const navigate = useNavigate();
    
    //cart fins by userid
    const [user, setUser] = useState("");
    const [order,setOrder]= useState("");

    useEffect(()=> {
        const user = JSON.parse(localStorage.getItem('user'));
        if (user) {
            setUser(user);
           }
        fetchAllItems(user.userID).then(resp=> {
            if((resp.data)[0].product!=null)
            setProducts(resp.data)
        
        });   //update id
        
        if(parseInt(id)>0)
        deleteItembyCartid(id).then(resp=>{window.location.replace('/cart');});
        
        
    },[id])
    
    const Checkout = () => {
        
        const user = JSON.parse(localStorage.getItem('user'));
        if (user) {
            setUser(user);
           }
        
        const payload = {
            userId:user.userID 
        }
        //alert(user.userID );
        addOrder(payload).then(resp => {

            const payload1 = {
                userId:user.userID ,
                orderId:resp.data.orderID
            }
            updateOrder(payload1).them(window.location.replace(ApiUrl+"/order"));
        });
        //window.location.replace(ApiUrl+"/order");


    }
    

    return(
    <><NavBar/>
        <div>
        <h1 style={{ color: "dark" }}>Cart Details</h1>
              <div class="table-responsive">
                    <table class="table table-bordered">
                        <thead class="thead-dark">
                            <tr>
                                <th>ProductId</th>
                                <th>ProductName</th>
                                <th>ProductPrice</th>
                                <th>Quantity</th>
                                <th>Update Quantity</th>

                            </tr>
                        </thead>
                        <tbody>
                            {
                                products.map(p =>
                                    <tr>
                                        <td>{p.product}</td>
                                        <td>{p.productName}</td>
                                        <td>{p.price}</td>
                                        <td>{p.quantity}</td>
                                        <td><Link to={`/cart/update/${p.cartId}`} className="btn btn-dark">Edit</Link>
                                        </td>
                                        <td><Link to={`/delete/${p.cartId}`} className="btn btn-dark">Delete</Link>
                                        </td>
                                
                                        
                                    </tr>
                                )}
                        </tbody>
                    </table>
                </div>
                <br></br>
                <button className="btn right" onClick={Checkout} 
                disabled={(products[0]==null)?true:false }>CheckOut</button>
                                       
        </div>
    </>
    )
}

export default Cart;
