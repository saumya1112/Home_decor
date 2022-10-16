import React, { useEffect, useState } from 'react';
import '../../util/Form.css';
import NavBar from './NavBar';

const Profile = () => {
    //cart fins by userid
    const [user, setUser] = useState("");

    useEffect(()=> {
        const loc = JSON.parse(localStorage.getItem('user'));
        if (loc) {
            setUser(loc);
           }
    },[])

    return (
        <><NavBar/>
        <div className="login-page">
            <div className="form1">
                <form className="login-form"  method='post'>
                    <h2 className='heading'>Profile</h2>
                    <br></br>
                <label>User id</label>    
                <input className='input' disabled 
                value={user.userID} ></input>
                <label>User Name</label>
                <input className='input'  disabled 
                value={user.userName} ></input>
                <label>Mobile</label>
                <input className='input' disabled 
                value={user.mobileNumber} placeholder="MobileNumber" ></input>
                <label>Email</label>
                <input className='input' disabled 
                 value={user.emailId} ></input>
                <label>Role</label>
                <input className='input' disabled 
                 value={user.userRole} ></input>

                </form>
        </div>
    </div>
    </>
        
    )
}
export default Profile;
