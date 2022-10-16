import React, { useState } from 'react';
import '../../util/Form.css';
import { AddCategory } from '../../service/AdminService';



function AddCat() {
    const [cId, setCategoryId] = useState('');
    const [CName, setCategoryName] = useState('');
    const [formErrors, setFormErrors] = useState({});

    const handleSubmit = () => {

        let errors = {};

        if (!cId) {
            errors["pIdError"] = "Category Id is required"
        }
        else if (!CName) {
            errors["pNameError"] = "Category Name is required"
        }
        setFormErrors(errors);

        const noErrors = Object.keys(errors).length === 0;
        //alert("inside no error " + noErrors);

        if (noErrors) {

            const payload = {
                categoryId: cId,
                categoryName: CName
            }

            AddCategory(payload).then(resp => alert("Category is saved with id: " + resp.data.categoryId))
        }
    }

    return (
        <div className="login-page">
            <div className="form1">
                <form className="login-form">
                    <h2 className='heading'>Add Category</h2>
                    <br></br>
                    <label>CategoryID</label>
                    <input className='input' type="number" id="cId" name="cId" value={cId} required
                        onChange={e => setCategoryId(e.target.value)}></input>
                    <div>
                        {
                            formErrors.pIdError && <div style={{ color: "red", paddingBottom: 10 }}> {formErrors.pIdError}</div>
                        }
                    </div>

                    <label>CategoryName</label>
                    <input className='input' type="text" id="CName" name="CName" value={CName} required
                        onChange={e => setCategoryName(e.target.value)}></input>
                    <div>
                        {
                            formErrors.pNameError && <div style={{ color: "red", paddingBottom: 10 }}> {formErrors.pNameError}</div>
                        }
                    </div>

                    <button className='button' onClick={handleSubmit}>Submit</button>
                </form>
            </div>
        </div>
    )
}
export default AddCat;