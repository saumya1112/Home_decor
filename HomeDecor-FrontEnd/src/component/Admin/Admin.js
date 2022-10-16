import {Route,Routes } from 'react-router-dom';

import AdminButton from '.';
import AdminBoard from './AdminHome';



function Admin() {
  return (
     <>
     <AdminBoard/>
     <AdminButton/>
     </>  
     );
}

export default Admin;
/*<Route path="/admin/add" element={<AdminButton />}></Route>
        <Route path="/product/find/:id" element={<EditProduct/>} /> 
        <Route path="/product/edit/:id" element={<FetchProduct/>} /> 
        <Route path="/cat/find/:id" element={<EditCat/>} /> 
        <Route path="/cat/edit/:id" element={<FetchCategory/>} /> 
        <Route path="/user/find/:id" element={<UpdateUser/>} /> 
        <Route path="/user/edit/:id" element={<FetchUser/>} /> 
        <Route path="/admin/board" element={<AdminBoard />}></Route>* */