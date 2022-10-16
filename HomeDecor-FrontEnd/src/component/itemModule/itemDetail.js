import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { AddToCart, fetchItemById } from '../../service/ProductService';
import NavBar from '../navBar/NavBar';
import '../../util/Form.css'
const ItemDetails = () => {

    const { item } = useParams();
    const [user, setUser] = useState([]);
    const navigate = useNavigate();
    const [product, setProduct] = useState("");



    useEffect(() => {
        const user = JSON.parse(localStorage.getItem('user'));
        if (user) {
            setUser(user);
        }
        fetchItemById(item).then(resp => {
            setProduct(resp.data);
        })

    }, [item])

    const handleSubmit = (e) => {
        e.preventDefault();
        const payload = {
            productId: item,
            userId: user.userID,
            qty: 1
        }
        AddToCart(payload).then(resp => navigate(-1))
            .catch(error => console.log("something went wrong"))
    }

    return (
        <>
            <NavBar />
            <div className="form1">
                <form className="login-form"  >
                    <h1 className='heading'>Item Details</h1>
                    <br></br>
                    Item Id
                    <input className='input' type="number"
                        value={item} disabled />
                    Item Name
                    <input className='input' type="text"
                        value={product.itemName} disabled />
                    Item Cost
                    <input className='input' type="number"
                        value={product.itemCost} disabled />
                    Manufacturing Date
                    <input className='input' type="date"
                        value={product.mfd} disabled />
                    Item Identifier
                    <input className='input' type="text"
                        value={product.itemIdentifier} disabled />
                    Description
                    <input className='input' type="text"
                        value={product.description} disabled />

                    <br></br>
                    <button className='button' onClick={handleSubmit}>AddToCart</button>
                </form>
            </div>
        </>
    )

}
export default ItemDetails;
/*Category
                    <input  type="text" 
                    value={category} disabled/> */
