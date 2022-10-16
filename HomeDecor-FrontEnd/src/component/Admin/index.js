import React, { useState } from 'react'
import './AdminButton.css'
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faEdit, faMobile, faUser } from "@fortawesome/free-solid-svg-icons";
import AddItem from './AddItems'
import AddCat from './AddCategory'
import AddUser from './AddUser'

const AdminButton = () => {
  const [itemwindow, setItemWindow] = useState(false);
  const toggleItemWindow = (e) => {
    e.preventDefault();
    setItemWindow(!itemwindow);
  }

  const [categorywindow, setCategoryWindow] = useState(false);
  const toggleCatWindow = (e) => {
    e.preventDefault();
    setCategoryWindow(!categorywindow);

  }

  const [userwindow, setUserWindow] = useState(false);
  const toggleUserWindow = (e) => {
    e.preventDefault();
    setUserWindow(!userwindow);

  }
  return (
    <div>
      <div className='parent1'>
        <div className='float'>
          <div className="wrapper">
            <input type="checkbox" />
            <div className="fab"></div>
            <div className="fac">
              <a onClick={(e) => { toggleItemWindow(e); }} href='#'><FontAwesomeIcon icon={faMobile} /></a>
              <a onClick={(e) => { toggleCatWindow(e); }} href="#"><FontAwesomeIcon icon={faEdit} /></a>
              <a onClick={(e) => { toggleUserWindow(e); }} href="#"><FontAwesomeIcon icon={faUser} /></a>
            </div>
          </div>
        </div>
        <div className={itemwindow ? 'overlay_form showWindow' : 'overlay_form hideWindow'} onClick={(e) => toggleItemWindow(e)} >
        </div>
        <div className={itemwindow ? 'TeamForm showWindow' : 'TeamForm hideWindow'}>
          <AddItem isOpen={itemwindow} toggle={toggleItemWindow} />
        </div>

        <div className={categorywindow ? 'overlay_form showWindow' : 'overlay_form hideWindow'} onClick={(e) => toggleCatWindow(e)} >
        </div>
        <div className={categorywindow ? 'TeamForm showWindow' : 'TeamForm hideWindow'}>
          <AddCat isOpen={categorywindow} toggle={toggleCatWindow} />
        </div>

        <div className={userwindow ? 'overlay_form showWindow' : 'overlay_form hideWindow'} onClick={(e) => toggleUserWindow(e)} >
        </div>
        <div className={userwindow ? 'TeamForm showWindow' : 'TeamForm hideWindow'}>
          <AddUser isOpen={userwindow} toggle={toggleUserWindow} />
        </div>
      </div>
    </div>
  )
}

export default AdminButton;