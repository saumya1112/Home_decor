import React, { useState } from 'react';
import { addUser } from '../../service/AdminService';
import { ApiUrl } from '../../util/AppConstants';

import '../../util/Form.css';



function AddUser() {
    const [uName, setUserName] = useState('');
    const [uPassword, setUserPassword] = useState('');
    const [uMobile, setMobile] = useState('');
    const [uEmail, setEmail] = useState('');
    const [userRole, SetUserRole] = useState('');

    var testEmail =    /^[ ]*([^@\s]+)@((?:[-a-z0-9]+\.)+[a-z]{2,})[ ]*$/i;

    const [formErrors, setFormErrors] = useState({});
    
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
            errors["pEmailError"] = "Email is required"
        }
        else if (!testEmail.test(uEmail)) {
            errors["pEmailError"] = "Invalid email address";
        }
        else if (!userRole) {
            errors["pUserError"] = "User Role is Required"
        }

        setFormErrors(errors);

        const noErrors = Object.keys(errors).length === 0;
        //alert("inside no error " + noErrors);

        if (noErrors) {

        const payload = {
            userName: uName,
            userPassword: uPassword,
            mobileNumber: uMobile,
            emailId: uEmail,
            userRole: userRole
        }

        addUser(payload).then(resp => window.location.replace(ApiUrl+"/admin/user"));
    }
    }
    return (
        <div className="login-page">
            <div className="form1">
                    <h2 className='heading'>Add User</h2>
                    <br></br>
                <input className='input' type="text" id="uName" name="uName" placeholder='UserName' 
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

                <button className='button' onClick={handleSubmit}>Submit</button>
        </div>
    </div>
        
    )
}
export default AddUser;