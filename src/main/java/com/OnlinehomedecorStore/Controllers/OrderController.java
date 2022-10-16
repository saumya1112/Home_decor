package com.OnlinehomedecorStore.Controllers;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.OnlinehomedecorStore.Exceptions.OrderNotFoundException;
import com.OnlinehomedecorStore.Services.ICartService;
import com.OnlinehomedecorStore.Services.ItemService;
import com.OnlinehomedecorStore.Services.OrderItemService;
import com.OnlinehomedecorStore.Services.OrderService;
import com.OnlinehomedecorStore.Services.UserService;
import com.OnlinehomedecorStore.entities.OrderModel;
import com.OnlinehomedecorStore.entities.UserModel;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/order")
public class OrderController {

	@Autowired
	ICartService cartService;

	@Autowired
	UserService userService;

	@Autowired
	ItemService itemService;

	@Autowired
	OrderService orderService;

	@Autowired
	OrderItemService orderItemService;

	@PostMapping("/add")
	public ResponseEntity<?> addToOrder(@RequestBody HashMap<String, String> addOrderRequest) {
		int userId = Integer.parseInt(addOrderRequest.get("userId"));
		UserModel user = userService.getUserById(userId);
		float TotalCost = cartService.TotalSum(userId);
		// double price = Double.parseDouble(addCartRequest.get("price"));
		// c.setProduct(productId); find by product id
		// c.setUserid(userId);
		// c.setPrice(price);
		// c.setQuantity(qty);

		// c.setProduct(product);
		OrderModel o = new OrderModel(userId, TotalCost);
		orderService.save(o);
		return ResponseEntity.ok(o);

	}

	@GetMapping("/viewAll")
	public ResponseEntity<?> View() {
		List<OrderModel> obj = orderService.findAll();
		return ResponseEntity.ok(obj);

	}

	@GetMapping("/view/{id}")
	public ResponseEntity<?> ViewWithId(@PathVariable int id) throws OrderNotFoundException {
		List<OrderModel> obj = orderService.findAllByUserId(id);
		return ResponseEntity.ok(obj);

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteById(@PathVariable int id) throws OrderNotFoundException {
		OrderModel orderModel = orderService.findById(id);
		orderModel.setStatus("Cancelled");
		orderService.save(orderModel);
		return ResponseEntity.ok("Order with Id " + id + " has been Cancelled");
	}

}
