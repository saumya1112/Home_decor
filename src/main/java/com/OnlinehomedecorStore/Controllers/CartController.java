package com.OnlinehomedecorStore.Controllers;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.OnlinehomedecorStore.Services.ICartService;
import com.OnlinehomedecorStore.Services.ItemService;
import com.OnlinehomedecorStore.Services.UserService;
import com.OnlinehomedecorStore.entities.CartModel;
import com.OnlinehomedecorStore.entities.ItemModel;
import com.OnlinehomedecorStore.entities.UserModel;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "http://localhost:3000")
public class CartController {

	@Autowired
	ICartService cartService;

	@Autowired
	UserService userService;

	@Autowired
	ItemService itemService;

	@PostMapping("/addProduct")
	public ResponseEntity<?> addCartwithProduct(@RequestBody HashMap<String, String> addCartRequest) {
		int productId = Integer.parseInt(addCartRequest.get("productId"));
		int userId = Integer.parseInt(addCartRequest.get("userId"));
		int qty = Integer.parseInt(addCartRequest.get("qty"));
		// double price = Double.parseDouble(addCartRequest.get("price"));
		// c.setProduct(productId); find by product id
		// c.setUserid(userId);
		// c.setPrice(price);
		// c.setQuantity(qty);
		UserModel user = userService.getUserById(userId);
		ItemModel product = itemService.getUserById(productId);
		cartService.findByUserAndProduct(userId, productId); // CHECK
		// c.setProduct(product);
		CartModel c = new CartModel(productId, qty, userId, (qty * product.getItemCost()), product.getItemName());
		return ResponseEntity.ok(cartService.addCart(c));

	}

	@PutMapping("/update")
	public ResponseEntity<?> getCartsByUserId(@RequestBody HashMap<String, String> getCartRequest) {
		int cId = Integer.parseInt(getCartRequest.get("cId"));
		int qty = Integer.parseInt(getCartRequest.get("qty"));

		CartModel obj = cartService.findById(cId);
		int Old_qty = obj.getQuantity();
		obj.setQuantity(qty);
		obj.setPrice((obj.getPrice() / Old_qty) * qty);
		return ResponseEntity.ok(cartService.addCart(obj));

	}

	@GetMapping("/viewAll")
	public ResponseEntity<?> View() {
		List<CartModel> obj = cartService.findAll();
		return ResponseEntity.ok(obj);
	}

	@GetMapping("/view/{id}")
	public ResponseEntity<?> View(@PathVariable int id) {
		List<CartModel> obj = cartService.findAllByUserid(id);
		return ResponseEntity.ok(obj);
	}

	@GetMapping("/view/delete/{cid}")
	public ResponseEntity<?> Delete(@PathVariable int cid) {
		CartModel obj = cartService.findById(cid);
		cartService.delete(cid);

		return ResponseEntity.ok("Item has been remover" + obj);
	}
}
