import React, { useState, useEffect } from 'react';
import { useParams,useNavigate } from 'react-router-dom';
import { fetchUserById, updateUser } from '../../service/AdminService';

import '../../util/Form.css';

function UpdateUser() {
    const [uId, setUserId] = useState('');
    const [uName, setUserName] = useState('');
    const [uPassword, setUserPassword] = useState('');
    const [uMobile, setMobile] = useState('');
    const [uEmail, setEmail] = useState('');
    const [userRole, SetUserRole] = useState('');

    const { id } = useParams();
    const navigate = useNavigate();

    useEffect(()=> {
 
        fetchUserById(id).then(resp => {
            setUserId(resp.data.userID);
            setUserName(resp.data.userName);
            setUserPassword(resp.data.userPassword);
            setMobile(resp.data.mobileNumber);
            setEmail(resp.data.emailId);
            SetUserRole(resp.data.userRole);
        });
    }, [id])

    const [formErrors, setFormErrors] = useState({});
    var testEmail =    /^[ ]*([^@\s]+)@((?:[-a-z0-9]+\.)+[a-z]{2,})[ ]*$/i;
    const handleSubmit = () => {

        let errors = {};

        if (!uName) {
            errors["pNameError"] = "User Name is required"
        }
        else if (!uPassword) {
            errors["pPasswordError"] = "Password is required"
        }
        else if (uPassword.length < 8) {
            errors["pPasswordError"] = "Password Should be 8 or more char is required"
        }
        else if (!uMobile) {
            errors["pMobileError"] = "Mobile number is required"
        }
        else if (!uEmail) {
            errors["pEmailError"] = "Proper email is required"
        }
        else if (!testEmail.test(uEmail)) {
            errors["pEmailError"] = "Invalid email address";
          }      
        else if (!userRole) {
            errors["pUserError"] = "User Role is Required"
        }


        setFormErrors(errors);

        const noErrors = Object.keys(errors).length === 0;

        if (noErrors) {

        const payload = {
            userID: uId,
            userName: uName,
            userPassword: uPassword,
            mobileNumber: uMobile,
            emailId: uEmail,
            userRole: userRole
        }
        updateUser(uId, payload).then(resp => navigate(-1)).catch(error => console.log("something went wrong"))
    }
    }
    return (
        <div className="form1">
            <div className="login-form">
               
                    <h2 className='heading'>Update User</h2>
                    <br></br>
                    <input className='input' type="text" id="uId" name="uId" placeholder='UserID' value={uId} disabled
                    onChange={e => setUserId(e.target.value)}></input>
                    <input className='input' type="text" id="uName" name="uName" placeholder='UserName' required
                        value={uName} onChange={e => setUserName(e.target.value)}></input>
                        <div>
                        {
                            formErrors.pNameError && <div style={{ color: "red", paddingBottom: 10 }}> {formErrors.pNameError}</div>
                        }
                    </div>

                    <input className='input' type="text" id="uPassword" name="uPassword" placeholder='UserPassword' required
                        value={uPassword} onChange={e => setUserPassword(e.target.value)}></input>
                        <div>
                        {
                            formErrors.pPasswordError && <div style={{ color: "red", paddingBottom: 10 }}> {formErrors.pPasswordError}</div>
                        }
                    </div>

                    <input className='input' type="number" id="uMobile" name="uMobile" required
                        value={uMobile} placeholder="MobileNumber" onChange={e => setMobile(e.target.value)}></input> 
                        <div>
                        {
                            formErrors.pMobileError && <div style={{ color: "red", paddingBottom: 10 }}> {formErrors.pMobileError}</div>
                        }
                    </div>

                    <input className='input' type="email" id="uEmail" name="uEmail" placeholder='Email' required
                        value={uEmail} onChange={e => setEmail(e.target.value)}></input>
                        <div>
                        {
                            formErrors.pEmailError && <div style={{ color: "red", paddingBottom: 10 }}> {formErrors.pEmailError}</div>
                        }
                    </div>


                    <input className='input' type="text" id="userRole" name="userRole" placeholder='UserRole' required
                        value={userRole} onChange={e => SetUserRole(e.target.value)}></input>
                        <div>
                        {
                            formErrors.pUserError && <div style={{ color: "red", paddingBottom: 10 }}> {formErrors.pUserError}</div>
                        }
                    </div>

                    <button className='button' onClick={handleSubmit} formAction="/admin/user" >Submit</button>
            
            </div>
        </div>
    )
}

export default UpdateUser;