import React, {useState } from "react";
import "./login.css";
import { ApiUrl } from "../../util/AppConstants";
import { logIn, addProduct } from "../../service/ProductService";
import {  faPenClip, faSignIn,} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

const LoginForm = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [mobile, setMobile] = useState("");
  const [email, setEmail] = useState("");
  const [lemail, setLemail] = useState("");
  const [lpassword, setLpassword] = useState("");
  const [formErrors, setFormErrors] = useState({});
  const [authFailed, setAuthFailed] = useState("");
  const [regDone, setRegDone] = useState("");
  const [authSfailed,setAuthSfailed] =useState("");
  const [slide, setSlide] = useState(false);
  const toggleClass = () => {
    setSlide(!slide);
  };

  const Signup = (e) => {
    e.preventDefault();
    let errors = {};
    var testEmail =    /^[ ]*([^@\s]+)@((?:[-a-z0-9]+\.)+[a-z]{2,})[ ]*$/i;
    if (!username) {
      errors["usernameSErr"] = "User Name is required";
    }
    else if (!password) {
      errors["passwordSErr"] = "Password is required";
    }
    else if (password.length<8) {
      errors["passwordSErr"] = "Password not Strong.Password must be at least 8 characters long.";
    }
    else if (!mobile) {
      errors["mobileSErr"] = "Mobile is required";
    }
    else if (!email) {
      errors["emailESrr"] = "Email is required";
    }
    else if (!testEmail.test(email)) {
      errors["emailESrr"] = "Invalid email address";
    }
    
    setFormErrors(errors);
    setAuthFailed("");
    setRegDone("");
    const noErrors = Object.keys(errors).length === 0;



  if(noErrors)  {
    const payload = {
      userName: username,
      userPassword: password,
      mobileNumber: mobile,
      emailId: email,
      userRole: "Customer",
    };
    addProduct(payload).then(
      (resp) => {
        if (resp.data.user == null) {
          
          setAuthSfailed(resp.data.message);
        } else {
          setAuthSfailed();
          setRegDone("Registered successfully.You can now Login");
        }
      }
    );
  }
  };

  const Login = (e) => {
    e.preventDefault();
    let errors = {};

    if (!lemail) {
      errors["usernameErr"] = "Email is required";
    }
    if (!lpassword) {
      errors["passwordErr"] = "Password is required";
    }
    setFormErrors(errors);
    setAuthFailed("");
    const noErrors = Object.keys(errors).length === 0;
    if (noErrors) {
      const payload = {
        userPassword: lpassword,
        emailId: lemail,
      };
      logIn(payload)
        .then((resp) => {
          
          if(resp.data.user!=null){
          localStorage.setItem("user", JSON.stringify(resp.data.user));
          if (resp.data.user.userRole == "Admin") {
            localStorage.setItem("Admin", true);
            window.location.replace(ApiUrl + "/admin");
          } else {
            localStorage.setItem("Admin", false);
            window.location.replace(ApiUrl + "/home");
          }}
          else {
            setAuthFailed(resp.data.message);
          }
        })
    }

    //login done
  };

  return (
    <div
      className={slide ? "container1 right-panel-active" : "container1"}
      id="container"
    >
      <div className="form-container sign-up-container">
        <form className="form">
          <h1>Create Account</h1>
          <FontAwesomeIcon icon={faPenClip} />
          <br></br>

          <input className="input" type="text" placeholder="Name" value={username}
            onChange={(event) => setUsername(event.target.value)}         />
          
          {formErrors.usernameSErr && (  <div style={{ color: "red" }}>
              {formErrors.usernameSErr}
            </div>
          )}
          
          <input  className="input" type="password" placeholder="Password"  value={password}
            onChange={(event) => setPassword(event.target.value)}          />
          
          {formErrors.passwordSErr && (  <div style={{ color: "red" }}>
              {formErrors.passwordSErr}
            </div>
          )}
          
          <input  className="input"  type="number"  placeholder="Mobile"  value={mobile}
            onChange={(event) => setMobile(event.target.value)}           />
          
          {formErrors.mobileSErr && (  <div style={{ color: "red" }}>
              {formErrors.mobileSErr}
            </div>
          )}

          <input  className="input"  type="text"  placeholder="Email"  value={email}
            onChange={(event) => setEmail(event.target.value)}  />
          
          {formErrors.emailESrr && (  <div style={{ color: "red"}}>
              {formErrors.emailESrr}
            </div>
          )}
          
          <div style={{ color: "red", paddingBottom: 10 }}>
                {authSfailed}
            </div>

           <div style={{ color: "green", paddingBottom: 10 }}>
                {regDone}
          </div>

          <button className="button" onClick={Signup}>
            Sign Up
          </button>
          <span className="errorSin" id="errorSinname"></span>
        </form>
      </div>

      <div className="form-container sign-in-container">
        <form className="form">
          <h1>Sign in</h1>
          <FontAwesomeIcon icon={faSignIn} />
          <br></br>

          <input  className="input"  type="text"  placeholder="Email Id"  value={lemail}
            onChange={(event) => setLemail(event.target.value)} />
          {formErrors.usernameErr && (  <div style={{ color: "red", paddingBottom: 10 }}>
              {formErrors.usernameErr}
            </div>
          )}
          <input  className="input"  type="password"  placeholder="Password"  value={lpassword}
            onChange={(event) => setLpassword(event.target.value)} />
          {formErrors.passwordErr && (  <div style={{ color: "red", paddingBottom: 10 }}>
              {formErrors.passwordErr}
            </div>
          )}

            <div style={{ color: "red", paddingBottom: 10 }}>
                {authFailed}
            </div>
          
          <button className="button" onClick={Login}>
            Sign In
          </button>
        </form>
      </div>
      <div className="overlay-container">
        <div className="overlay">
          <div className="overlay-panel overlay-left">
            <h1>Welcome Back!</h1>
            <p>Please login with your personal info</p>
            <button
              onClick={() => setSlide(0)}
              className="button ghost"
              id="signIn"
            >
              Sign In
            </button>
          </div>
          <div className="overlay-panel overlay-right">
            <h1>New User!</h1>
            <p>Register with us</p>
            <button
              onClick={() => setSlide(1)}
              className="button ghost"
              id="signUp"
            >
              Sign Up
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default LoginForm;
