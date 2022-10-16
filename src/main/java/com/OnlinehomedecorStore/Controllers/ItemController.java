package com.OnlinehomedecorStore.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.OnlinehomedecorStore.Exceptions.ItemNotFoundException;
import com.OnlinehomedecorStore.Services.ItemService;
import com.OnlinehomedecorStore.entities.ItemModel;

@RestController
@RequestMapping("/api/ItemModel")
@CrossOrigin(origins = "http://localhost:3000")
public class ItemController {
	@Autowired
	ItemService itemService;

	@PostMapping("/addItem")
	public ItemModel addProduct(@RequestBody ItemModel product) {
		return itemService.addItem(product);
	}

	@GetMapping("/Items")
	public List<ItemModel> findAllItems() {
		return itemService.showAllItem();
	}

	@GetMapping("/itemById/{id}")
	public ItemModel findProductById(@PathVariable int id) {
		return itemService.showItemById(id);
	}

	@PutMapping("/update")
	public ItemModel updateProduct(@RequestBody ItemModel product) throws ItemNotFoundException {
		return itemService.updateItem(product);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteProduct(@PathVariable int id) throws ItemNotFoundException {
		return itemService.deleteItem(id);
	}

	@GetMapping("/search/{name}")
	public List<ItemModel> showitemByName(@PathVariable String name) {
		return itemService.showAllItemByName(name);
	}

}
