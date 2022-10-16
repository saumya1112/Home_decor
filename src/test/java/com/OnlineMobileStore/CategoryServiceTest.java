package com.OnlinehomedecorStore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.OnlinehomedecorStore.Repositories.CategoryRepository;
import com.OnlinehomedecorStore.Services.CategoryServiceImpl;
import com.OnlinehomedecorStore.entities.CategoryModel;

@SpringBootTest
public class CategoryServiceTest {

	@InjectMocks
	private CategoryServiceImpl categoryService = new CategoryServiceImpl();
	@Mock
	private CategoryRepository categoryRepository;

	@Test
	void testSaveCategory() {
		CategoryModel categoryModel = new CategoryModel();
		categoryModel.setCategoryId(101);
		categoryModel.setCategoryName("Sofas & Recliners");
		when(categoryRepository.save(categoryModel)).thenReturn(categoryModel);
		CategoryModel savedCategoryModel = categoryService.addCategory(categoryModel);
		assertEquals("Sofas & Recliners", categoryModel.getCategoryName());
		verify(categoryRepository, times(1)).save(categoryModel);
	}

	@Test
	void testUpdateCategory() {
		CategoryModel categoryModel = new CategoryModel();
		categoryModel.setCategoryId(101);
		categoryModel.setCategoryName("Sofas & Recliners");
		when(categoryRepository.save(categoryModel)).thenReturn(categoryModel);
		categoryModel.setCategoryName("Dining");
		when(categoryRepository.save(categoryModel)).thenReturn(categoryModel);
		assertEquals("Dining", categoryModel.getCategoryName());
	}

	@Test
	void testGetAllCategories() {

		CategoryModel c1 = new CategoryModel();
		c1.setCategoryId(101);
		c1.setCategoryName("Dining");
		CategoryModel c2 = new CategoryModel();
		c2.setCategoryId(101);
		c2.setCategoryName("Sofas & Recliners");
		CategoryModel c3 = new CategoryModel();
		c3.setCategoryId(101);
		c3.setCategoryName("Tables");
		CategoryModel c4 = new CategoryModel();
		c4.setCategoryId(101);
		c4.setCategoryName("Carpets");
		List<CategoryModel> categories = new ArrayList<>();
		categories.add(c1);
		categories.add(c2);
		categories.add(c3);
		categories.add(c4);
		when(categoryRepository.findAll()).thenReturn(categories);
		assertEquals(4, categories.size());
//		Set<CategoryModel> categories = categoryService.getCategories();

	}

	@Test
	void testGetCategoryById() {
		CategoryModel c = new CategoryModel();
		c.setCategoryId(101);
		c.setCategoryName("Sofas & Recliners");
		Optional<CategoryModel> category = Optional.of(c);
		when(categoryRepository.findById(101)).thenReturn(category);
		Optional<CategoryModel> category2 = categoryService.getCategory(101);
		assertTrue(category2.isPresent());
	}

	void testDeleteCategory() {
		CategoryModel c = new CategoryModel();
		c.setCategoryId(101);
		c.setCategoryName("Sofas & Recliners");
		Optional<CategoryModel> optionalCategory = Optional.of(c);
		when(categoryRepository.findById(101)).thenReturn(optionalCategory);
		categoryService.delete(c.getCategoryId());
		assertNull(c);
	}

}