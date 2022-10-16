import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { deleteItemById, fetchProductById, updateProductById } from '../../service/AdminService';
import { ApiUrl } from '../../util/AppConstants';

import '../../util/Form.css';
import AdminNav from './adminNav';

function EditProduct() {
    const [mId, setItemID] = useState('');
    const [mName, setItemName] = useState('');
    const [mModel, setItemIdentifier] = useState('');
    const [mDescription, setDescription] = useState('');
    const [mfd, setMfd] = useState('');
    const [mCost, setItemCost] = useState('');
    const [mCategoryId, setmCategoryId] = useState('');
    const [mCategoryName, setmCategoryName] = useState('');

    const { id } = useParams();
    const navigate = useNavigate();

    useEffect(() => {
        fetchProductById(id).then(resp => {
            setItemID(resp.data.itemId);
            setItemName(resp.data.itemName);
            setItemIdentifier(resp.data.itemIdentifier);
            setDescription(resp.data.description);
            setMfd(resp.data.mfd);
            setItemCost(resp.data.itemCost);
            setmCategoryId(resp.data.category.categoryId);
            setmCategoryName(resp.data.category.categoryName);
        });
    }, [id])

    /*useEffect(() => {
        if(parseInt(id)>0){
            deleteItemById(id).then(resp=>navigate(-1))
        }
    }, [id])*/

    const handleDelete = () => {
        deleteItemById(id).then(resp => navigate(-1))
    }

    const [formErrors, setFormErrors] = useState({});

    const handleSubmit = () => {
        let errors = {};

        if (!mName) {
            errors["pNameError"] = "Item Name is required"
        }
        else if (!mCost) {
            errors["pPriceError"] = "Item Price is required"
        }
        else if (mCost < 0) {
            errors["pPriceError"] = "Item Price should be positive number"
        }
        else if (!mfd) {
            errors["pMfdError"] = "Item Manufature Date is required"
        }
        else if (!mModel) {
            errors["pModelError"] = "Item Model is required"
        }
        else if (!mDescription) {
            errors["pDescriptionError"] = "Item Description is required"
        }
        else if (!mCategoryId) {
            errors["pCategoryIdError"] = "Item CategoryId is required"
        }
        else if (!mCategoryName) {
            errors["pCategoryNameError"] = "Item CategoryName is required"
        }

        setFormErrors(errors);

        const noErrors = Object.keys(errors).length === 0;
        //alert("inside no error " + noErrors);

        if (noErrors) {
            const payload = {
                itemId: mId,
                itemName: mName,
                itemCost: mCost,
                mfd: mfd,
                itemIdentifier: mModel,
                description: mDescription,
                category: {
                    categoryId: mCategoryId,
                    CategoryName: mCategoryName,
                }
            }
            updateProductById(payload).then(resp => navigate(-1)).catch(error => console.log("something went wrong"),
                window.location.replace(ApiUrl + "/admin"));
        }
    }

    return (
        <><AdminNav />
            <div >
                <div className="form1">
                    <form className="login-form">
                        <h2 className='heading'>Update/Delete Item</h2>
                        <br></br>
                        <input className='input' type="text" id="mId" name="mId" placeholder='ItemId' disabled
                            value={mId} onChange={e => setItemID(e.target.value)}></input>

                        <input className='input' type="text" id="mName" name="mName" placeholder='ItemName' required
                            value={mName} onChange={e => setItemName(e.target.value)}></input>
                        <div>
                            {
                                formErrors.pNameError && <div style={{ color: "red", paddingBottom: 10 }}> {formErrors.pNameError}</div>
                            }
                        </div>


                        <input className='input' type="number" id="mCost" name="mCost" placeholder='ItemCost' required
                            value={mCost} onChange={e => setItemCost(e.target.value)}></input>
                        <div>
                            {
                                formErrors.pPriceError && <div style={{ color: "red", paddingBottom: 10 }}> {formErrors.pPriceError}</div>
                            }
                        </div>

                        <input className='input' type="date" id="mfd" name="mfd" required
                            value={mfd} placeholder="YYYY-MM-DD" onChange={e => setMfd(e.target.value)}></input>
                        <div>
                            {
                                formErrors.pMfdError && <div style={{ color: "red", paddingBottom: 10 }}> {formErrors.pMfdError}</div>
                            }
                        </div>

                        <input className='input' type="text" id="mModel" name="mModel" placeholder='ItemIdentifier' required
                            value={mModel} onChange={e => setItemIdentifier(e.target.value)}></input>
                        <div>
                            {
                                formErrors.pModelError && <div style={{ color: "red", paddingBottom: 10 }}> {formErrors.pModelError}</div>
                            }
                        </div>

                        <input className='input' type="text" id="mDescription" name="mDescription" placeholder='Description' required
                            value={mDescription} onChange={e => setDescription(e.target.value)}></input>
                        <div>
                            {
                                formErrors.pDescriptionError && <div style={{ color: "red", paddingBottom: 10 }}> {formErrors.pDescriptionError}</div>
                            }
                        </div>

                        <input className='input' type="number" id="mCategoryId" name="mCategoryId" placeholder='CategoryId' required
                            value={mCategoryId} onChange={e => setmCategoryId(e.target.value)}></input>
                        <div>
                            {
                                formErrors.pCategoryIdError && <div style={{ color: "red", paddingBottom: 10 }}> {formErrors.pCategoryIdError}</div>
                            }
                        </div>

                        <input className='input' type="text" id="mCategoryName" name="mCategoryName" placeholder='CategoryName' required
                            value={mCategoryName} onChange={e => setmCategoryName(e.target.value)}></input>
                        <div>
                            {
                                formErrors.pCategoryNameError && <div style={{ color: "red", paddingBottom: 10 }}> {formErrors.pCategoryNameError}</div>
                            }
                        </div>

                        <button className='button' onClick={handleSubmit}>Submit</button>

                        <button id='button1' onClick={handleDelete}>Delete</button>


                    </form>
                </div>
            </div>
        </>
    )
}
/**<Link to={`/user/edit/${mId}`}>Delete</Link> */
export default EditProduct;