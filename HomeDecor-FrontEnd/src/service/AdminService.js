import { baseUrl } from '../util/AppConstants'
import axios from 'axios'

export function AddCategory(cat) {
    return axios.post(baseUrl + "/category/", cat);
}

export function viewAllCategory() {
    return axios.get(baseUrl + "/category/get");
}

export function deleteCategoryById(id) {
    return axios.delete(baseUrl + "/category/" + id);
}
export function fetchCategoryById(id) {
    return axios.get(baseUrl + "/category/" + id);
}
export function updateCategory(cat) {
    return axios.put(baseUrl + "/category/update", cat)
}

export function AddAllItems(item) {
    return axios.post(baseUrl + "/api/ItemModel/addItem", item);
}

export function viewAllItems() {
    return axios.get(baseUrl + "/api/ItemModel/Items");
}

export function deleteItemById(id) {
    return axios.delete(baseUrl + "/api/ItemModel/delete/" + id);
}
export function updateProductById(item) {
    return axios.put(baseUrl + "/api/ItemModel/update", item);
}
export function fetchProductById(itemId) {
    return axios.get(baseUrl + "/api/ItemModel/itemById/" + itemId);
}

export function addUser(cat) {
    return axios.post(baseUrl + "/User/register", cat);
}

export function viewAllUsers() {
    return axios.get(baseUrl + "/User/viewAllUsers");
}

export function deleteUserById(id) {
    return axios.delete(baseUrl + "/User/deleteById/" + id);
}

export function updateUser(id, user) {
    return axios.put(baseUrl + "/User/update/" + id, user);
}

export function fetchUserById(id) {
    return axios.get(baseUrl + "/User/viewUserById/" + id);
}