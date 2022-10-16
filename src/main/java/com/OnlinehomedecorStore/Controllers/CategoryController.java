package com.OnlinehomedecorStore.Controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.OnlinehomedecorStore.Services.CategoryService;
import com.OnlinehomedecorStore.entities.CategoryModel;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	// add category
	@PostMapping("/")
	public ResponseEntity<?> addCategory(@RequestBody CategoryModel category) {
		CategoryModel cat1 = this.categoryService.addCategory(category);
		return ResponseEntity.ok(cat1);
	}

	// get single category
	@GetMapping("/{categoryId}")
	public Optional<CategoryModel> getCategory(@PathVariable("categoryId") Integer categoryId) {

		return this.categoryService.getCategory(categoryId);
	}

	// get all categories
	@GetMapping("/get")
	public ResponseEntity<?> getCategories() {
		return ResponseEntity.ok(this.categoryService.getCategories());
	}

	// update any category
	@PutMapping("/update")
	public CategoryModel updateCategory(@RequestBody CategoryModel category) {
		return this.categoryService.updateCategory(category);
	}

	// delete Category
	@DeleteMapping("/{catId}")
	public ResponseEntity<?> DeleteCategory(@PathVariable("catId") Integer catId) {
		this.categoryService.delete(catId);
		return ResponseEntity.ok("Category with id " + catId + " Deleted.");
	}

}
