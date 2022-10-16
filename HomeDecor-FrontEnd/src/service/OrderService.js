import { baseUrl } from '../util/AppConstants'
import axios from 'axios'

export function viewAllOrders(userID) {
    return axios.get(baseUrl + "/api/order/view/" + userID);
}


export function cancelOrder(orderId) {
    return axios.delete(baseUrl + "/api/order/delete/" + orderId);
}

export function fetchOrderItemByOrderId(orderId) {
    return axios.get(baseUrl + "/api/orderItem/view/" + orderId);
}

export function fectchItemByItemId(itemId) {
    return axios.get(baseUrl + "/api/ItemModel/itemById/" + itemId);
}

export function addOrder(user) {
    return axios.post(baseUrl + "/api/order/add", user);
}

export function updateOrder(obj) {
    return axios.put(baseUrl + "/api/orderItem/update", obj);
}
