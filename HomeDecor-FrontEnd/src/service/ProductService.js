import { baseUrl } from '../util/AppConstants'
import axios from 'axios'
//ItemList
export function fetchAllProducts() {
    return axios.get(baseUrl + "/api/ItemModel/Items");
}

export function fetchProductById(productId) {
    return axios.get(baseUrl + "/product/find/" + productId);
}
//Reg 
export function addProduct(product) {
    return axios.post(baseUrl + "/User/register", product)
}
//Log in
export function logIn(product) {
    return axios.post(baseUrl + "/User/login", product)
}
//Cart
export function fetchAllItems(userId) {
    return axios.get(baseUrl + "/api/cart/view/" + userId);
}
export function deleteItembyCartid(Itemid) {
    return axios.get(baseUrl + "/api/cart/view/delete/" + Itemid);
}
//Cart
export function fetchCartById(id) {
    return axios.get(baseUrl + "/api/cart/view/" + id);
}
//Cart
export function editCart(cart) {
    return axios.put(baseUrl + "/api/cart/update", cart);
}
//CartAdd
export function AddToCart(jinput) {
    return axios.post(baseUrl + "/api/cart/addProduct", jinput);
}
//get item by id
export function fetchItemById(item) {
    return axios.get(baseUrl + "/api/ItemModel/itemById/" + item);
}
//get item by Name
export function fetchItemByName(name) {
    return axios.get(baseUrl + "/api/ItemModel/search/" + name);
}