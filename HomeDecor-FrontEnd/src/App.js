import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import LoginForm from './component/loginModule/Login'
import ItemDisplay from './component/itemModule/item'
import Cart from './component/cartModule/Cart'
import UpdateCart from './component/cartModule/updateCart';
import ItemDetails from './component/itemModule/itemDetail';
import ViewAllOrders from './component/orderModule/ViewAllOrders';
import ViewOrderItemByOrderId from './component/orderModule/ViewOrderItemByOrderId';
import EditCat from './component/Admin/EditCategory';
import UpdateUser from './component/Admin/EditUser';
import Admin from './component/Admin/Admin';
import AdminCategory from './component/Admin/AdminCategory';
import AdminBoard from './component/Admin/AdminHome';
import AdminUser from './component/Admin/AdminUser';
import EditProduct from './component/Admin/EditHome';
import Profile from './component/navBar/Profile';
function App() {
  return (

    <BrowserRouter>
      <Routes>

        <Route path="/home" element={<ItemDisplay />}></Route>
        <Route path='/profile' element={<Profile />}></Route>
        <Route path="/" element={<LoginForm />}></Route>
        <Route path="/cart" element={<Cart />}></Route>
        <Route path="/delete/:id" element={<Cart />}></Route>
        <Route path="/cart/update/:id" element={<UpdateCart />} />
        <Route path="/home/product/:item" element={<ItemDetails />} />
        <Route path="/order" element={<ViewAllOrders />}></Route>
        <Route path="/order/:id" element={<ViewOrderItemByOrderId />} />
        <Route path="/order/delete/:id" element={<ViewAllOrders />} />
        <Route path="/search/:name" element={<ItemDisplay />} />


        <Route path="/admin" element={<Admin />}></Route>
        <Route path="/admin/cat" element={<AdminCategory />}></Route>
        <Route path="/admin/user" element={<AdminUser />}></Route>
        <Route path="/user/edit/:id" element={<AdminUser />}></Route>
        <Route path="/product/edit/:id" element={<AdminBoard />}></Route>
        <Route path="/cat/edit/:id" element={<AdminCategory />}></Route>
        <Route path="/cat/update/:id" element={<EditCat />}></Route>
        <Route path="/user/update/:id" element={<UpdateUser />}></Route>
        <Route path="/admin/product/:id" element={<EditProduct />}></Route>


      </Routes>
    </BrowserRouter>

  );
}
//<Route path="/product/find/:id" element={<ItemDisplay />}></Route>
export default App;
