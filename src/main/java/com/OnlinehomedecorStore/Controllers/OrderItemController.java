package com.OnlinehomedecorStore.Controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.OnlinehomedecorStore.Exceptions.OrderItemNotFoundException;
import com.OnlinehomedecorStore.Services.ICartService;
import com.OnlinehomedecorStore.Services.OrderItemService;
import com.OnlinehomedecorStore.Services.OrderService;
import com.OnlinehomedecorStore.entities.CartModel;
import com.OnlinehomedecorStore.entities.OrderItemModel;
import com.OnlinehomedecorStore.entities.OrderModel;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/orderItem")
public class OrderItemController {

	@Autowired
	ICartService cartService;

	@Autowired
	OrderItemService orderItemService;

	@Autowired
	OrderService orderService;

	@PutMapping("/update")
	public List<CartModel> updateOrderItem(@RequestBody HashMap<String, String> addCartRequest) {
		int uId = Integer.parseInt(addCartRequest.get("userId"));
		int oId = Integer.parseInt(addCartRequest.get("orderId"));

		List<CartModel> productL = cartService.findAllByUserid(uId);
		for (CartModel obj : productL) {
			OrderItemModel orderModel = new OrderItemModel(oId, obj.getProduct(), obj.getQuantity(), obj.getPrice(),
					obj.getProductName());
			orderItemService.save(orderModel);
			cartService.delete(obj.getCartId());
		}

		return productL;
	}

	@GetMapping("/viewAll")
	public ResponseEntity<?> View() {

		List<OrderItemModel> obj = orderItemService.findAll();
		return ResponseEntity.ok(obj);

	}

	@GetMapping("/view/{id}")
	public ResponseEntity<?> View(@PathVariable int id) throws OrderItemNotFoundException {

		List<OrderItemModel> obj = orderItemService.findAllByOrderid(id);
		return ResponseEntity.ok(obj);

	}

	@GetMapping("/view/user/{id}")
	public List<List<OrderItemModel>> ViewUser(@PathVariable int id) throws OrderItemNotFoundException {
		List<OrderModel> objL = orderService.findAllByUserId(id);
		List<List<OrderItemModel>> UserOrder = new ArrayList<>();
		for (OrderModel obj : objL) {
			UserOrder.add(orderItemService.findAllByOrderid(obj.getOrderID()));
		}

		return UserOrder;
	}

}
