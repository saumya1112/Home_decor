import React, { useState, useEffect } from 'react';
import { useParams,useNavigate } from 'react-router-dom';
import { fetchCartById,editCart } from '../../service/ProductService';
import NavBar from '../navBar/NavBar';
import '../../util/Form.css'

function UpdateCart() {

    
    const [quantity, setQuantity] = useState('');
    const { id } = useParams();
    const navigate = useNavigate();

    useEffect(() => {
        fetchCartById(id).then(resp => {
            setQuantity(resp.data.quantity);
        });
    }, [id])

    const handleSubmit = (e) => {
        e.preventDefault();
        const payload = {
            cId:id,
            qty: quantity
        }
        editCart(payload).then(resp => navigate(-1)).catch(error=>console.log("something went wrong"))
    }
    return (
        <><NavBar/>
        <div className="form1">
            <div >
                <label>CartId</label>
                <input className='input' type="text"  name="pName" value={id} disabled></input>
            </div>
            <div>
                <label>Quantity</label>
                <input className='input' type="text"  name="pPrice" value={quantity} onChange={e => {if(e.target.value>0)setQuantity(e.target.value)}}></input>
            </div>
            
            <div>
                <button onClick={handleSubmit}>Submit</button>
            </div>
        </div>
        </>
    )
}

export default UpdateCart;

