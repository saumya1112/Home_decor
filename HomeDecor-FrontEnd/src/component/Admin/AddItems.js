import React, { useState } from 'react';
import '../../util/Form.css';
import { AddAllItems } from '../../service/AdminService';
import { useNavigate } from 'react-router-dom';
import { ApiUrl } from '../../util/AppConstants';


function AddItem() {

    const [mName, setItemName] = useState('');
    const [mModel, setItemIdentifier] = useState('');
    const [mDescription, setDescription] = useState('');
    const [mfd, setMfd] = useState('');
    const [mCost, setItemCost] = useState('');
    const [mCategoryId, setmCategoryId] = useState('');
    const [mCategoryName, setmCategoryName] = useState('');

    const navigate = useNavigate();

    const [formErrors, setFormErrors] = useState({});

    const handleSubmit1 = () => {
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
            errors["pMfdError"] = "Item Manufacture Date is required"
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

            //alert("inside no error");

            const payload = {
                itemName: mName,
                itemCost: mCost,
                mfd: mfd,
                itemIdentifier: mModel,
                description: mDescription,
                category: {
                    categoryId: mCategoryId,
                    categoryName: mCategoryName
                }
            }
            AddAllItems(payload).then(resp => window.location.replace(ApiUrl + "/admin"));
        }
    }

    return (
        <div className="login-page">
            <div className="form1">

                <h2 className='heading'>Add Item</h2>
                <br></br>

                <input className='input' type="text" id="mName" name="mName" placeholder='ItemName'
                    value={mName} onChange={e => setItemName(e.target.value)}></input>
                <div>
                    {
                        formErrors.pNameError && <div style={{ color: "red", paddingBottom: 10 }}> {formErrors.pNameError}</div>
                    }
                </div>

                <input className='input' type="number" id="mCost" name="mCost" placeholder='ItemCost'
                    value={mCost} onChange={e => setItemCost(e.target.value)}></input>
                <div>
                    {
                        formErrors.pPriceError && <div style={{ color: "red", paddingBottom: 10 }}> {formErrors.pPriceError}</div>
                    }
                </div>

                <input className='input' type="date" id="mfd" name="mfd"
                    value={mfd} placeholder="YYYY-MM-DD" onChange={e => setMfd(e.target.value)}></input>
                <div>
                    {
                        formErrors.pMfdError && <div style={{ color: "red", paddingBottom: 10 }}> {formErrors.pMfdError}</div>
                    }
                </div>

                <input className='input' type="text" id="mModel" name="mModel" placeholder='ItemIdentifier'
                    value={mModel} onChange={e => setItemIdentifier(e.target.value)}></input>
                <div>
                    {
                        formErrors.pModelError && <div style={{ color: "red", paddingBottom: 10 }}> {formErrors.pModelError}</div>
                    }
                </div>


                <input className='input' type="text" id="mDescription" name="mDescription" placeholder='Description'
                    value={mDescription} onChange={e => setDescription(e.target.value)}></input>
                <div>
                    {
                        formErrors.pDescriptionError && <div style={{ color: "red", paddingBottom: 10 }}> {formErrors.pDescriptionError}</div>
                    }
                </div>

                <input className='input' type="number" id="mCategoryId" name="mCategoryId" placeholder='CategoryId'
                    value={mCategoryId} onChange={e => setmCategoryId(e.target.value)}></input>
                <div>
                    {
                        formErrors.pCategoryIdError && <div style={{ color: "red", paddingBottom: 10 }}> {formErrors.pCategoryIdError}</div>
                    }
                </div>

                <input className='input' type="text" id="mCategoryName" name="mCategoryName" placeholder='CategoryName'
                    value={mCategoryName} onChange={e => setmCategoryName(e.target.value)}></input>
                <div>
                    {
                        formErrors.pCategoryNameError && <div style={{ color: "red", paddingBottom: 10 }}> {formErrors.pCategoryNameError}</div>
                    }
                </div>

                <button className='button' onClick={handleSubmit1}>Submit</button>

            </div>
        </div>
    )
}

export default AddItem;