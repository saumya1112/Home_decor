import React, { useEffect, useState } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { fetchAllProducts, fetchItemByName } from '../../service/ProductService';
import dummy2 from '../../assets/images/dummy2.jpg';
import '../../../node_modules/bootstrap/dist/css/bootstrap.min.css';
import './item.css';
import NavBar from '../navBar/NavBar';
import { ApiUrl } from '../../util/AppConstants';


const ItemDisplay = () => {
    let navigate = useNavigate();
    const [products, setProducts] = useState([]);
    //search
    const { name } = useParams("");

    useEffect(() => {


        if (name != null) {//alert("1");
            fetchItemByName(name).then(resp => {
                document.getElementById('errorname').innerHTML = ""; setProducts(resp.data);
                //alert("1a  "+products+"/ "+resp.data+resp.data[0]+"  hi");
                if (resp.data[0] == null) {
                    //alert("2");
                    document.getElementById('errorname').innerHTML = "Could not find item with the name '" + name + "'";
                }
            });

        } else {
            fetchAllProducts().then(resp => { setProducts(resp.data); });
            document.getElementById('errorname').innerHTML = "";
        }

    }, [name])
    /*
    if (localStorage.getItem('Admin') == null || localStorage.getItem('Admin') !="true" ) {
        alert("Please Log in");
        window.location.replace(baseUrl+"/");
      }
    */
    let admin = (localStorage.getItem("Admin").toLowerCase() === 'false');
    return (
        <>
            {admin ?
                <div>
                    <NavBar />
                </div> : null
            }
            <div>
                {(admin) ?
                    <div>
                        <span className="error" id="errorname"></span>
                        <td >
                            {
                                products.map(p =>


                                    <Link to={`/home/product/${p.itemId}`}>
                                        <tr >
                                            <tr className="card h-100 name"  >
                                                <img className='imgT' src="https://ii2.pepperfry.com/media/wysiwyg/banners/Dining_trend_web_20122021_3.jpg" alt="Item image" />
                                                <h4 className="name">{p.itemName}</h4>
                                                <p className="name">{p.itemCost}</p>
                                            </tr>
                                        </tr>
                                    </Link>
                                )
                            }

                        </td>
                    </div> :
                    <div>
                        <span className="error" id="errorname"></span>
                        <td >
                            {
                                products.map(p =>
                                    <Link to={`/admin/product/${p.itemId}`}>
                                        <tr >
                                            <tr className="card h-100 name"  >
                                                <img className='imgT' src="https://ii2.pepperfry.com/media/wysiwyg/banners/Dining_trend_web_20122021_3.jpg" alt="Item image" />
                                                <h4 className="name">{p.itemName}</h4>
                                                <p className="name">{p.itemCost}</p>
                                            </tr>
                                        </tr>
                                    </Link>
                                )
                            }

                        </td>
                    </div>
                }
            </div>
        </>
    )


}
export default ItemDisplay;