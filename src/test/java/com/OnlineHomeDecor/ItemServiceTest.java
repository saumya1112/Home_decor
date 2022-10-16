package com.OnlinehomedecorStore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

import com.OnlinehomedecorStore.Exceptions.ItemNotFoundException;
import com.OnlinehomedecorStore.Repositories.ProductRepo;
import com.OnlinehomedecorStore.Services.ItemService;
import com.OnlinehomedecorStore.entities.ItemModel;

@SpringBootTest
public class ItemServiceTest {
	@InjectMocks
	private ItemService itemService = new ItemService();
	@Mock
	private ProductRepo productRepo;

	@Test
	void testGetProductById() {
		ItemModel product = new ItemModel();
		product.setItemId(100);
		product.setItemName("dining room set");
		product.setItemCost(50000);
		product.setItemIdentifier("SE55455");
		product.setDescription("this is a dining room set");
		product.setCategory(product.getCategory());
		Optional<ItemModel> optionalProduct = Optional.of(product);
		when(productRepo.findById(100)).thenReturn(optionalProduct);
		ItemModel myProduct = itemService.showItemById(100);
		assertEquals("dining room set", myProduct.getItemName());
	}

	@Test
	void testGetProductByIdWithException() {
		when(productRepo.findById(100)).thenThrow(ItemNotFoundException.class);
		assertThrows(ItemNotFoundException.class, () -> itemService.showItemById(100));
	}

	@Test
	void testGetAllProducts() {
		ItemModel product = new ItemModel();
		product.setItemId(100);
		product.setItemName("dining room set");
		product.setItemCost(50000);
		product.setItemIdentifier("SE55455");
		product.setDescription("this is a dining room set");
		product.setCategory(product.getCategory());
		ItemModel product2 = new ItemModel();
		product2.setItemId(200);
		product2.setItemName("sofa set");
		product2.setItemCost(20000);
		product2.setItemIdentifier("SE79455");
		product2.setDescription("this is a sofa set");
		product2.setCategory(product2.getCategory());
		ItemModel product3 = new ItemModel();
		product3.setItemId(300);
		product3.setItemName("table");
		product3.setItemCost(60000);
		product3.setItemIdentifier("SE2672");
		product3.setDescription("this is a table set");
		product3.setCategory(product3.getCategory());
		List<ItemModel> productList = new ArrayList<>();
		productList.add(product3);
		productList.add(product2);
		productList.add(product);
		when(productRepo.findAll()).thenReturn(productList);
		List<ItemModel> products = itemService.showAllItem();
		assertEquals(3, products.size());
	}

	@Test
	void testSaveProduct() {
		ItemModel product = new ItemModel();
		product.setItemId(100);
		product.setItemName("dining room set");
		product.setItemCost(50000);
		product.setItemIdentifier("SE55455");
		product.setDescription("this is a dining room set");
		product.setCategory(product.getCategory());
		when(productRepo.save(product)).thenReturn(product);
		ItemModel newProduct = itemService.addItem(product);
		assertEquals("dining room set", newProduct.getItemName());
		verify(productRepo, times(1)).save(product); // useful for testing void methods
	}

	@Test
	void testDeleteProduct() throws ItemNotFoundException {
		ItemModel product = new ItemModel();
		product.setItemId(100);
		product.setItemName("dining room set");
		product.setItemCost(50000);
		product.setItemIdentifier("SE55455");
		product.setDescription("this is a dining room set");
		product.setCategory(product.getCategory());
		Optional<ItemModel> optionalProduct = Optional.of(product);
		when(productRepo.findById(100)).thenReturn(optionalProduct);
		ItemModel myProduct = itemService.showItemById(100);
		itemService.deleteItem(100);
		verify(productRepo, times(1)).findById(100);
		verify(productRepo, times(1)).deleteById(100);
	}

	@Test
	void testGetProductByName() {
		ItemModel product = new ItemModel();
		product.setItemId(100);
		product.setItemName("dining room set");
		product.setItemCost(50000);
		product.setItemIdentifier("SE55455");
		product.setDescription("this is a dining room set");
		product.setCategory(product.getCategory());
		ItemModel product2 = new ItemModel();
		product2.setItemId(200);
		product2.setItemName("dining room set");
		product2.setItemCost(20000);
		product2.setItemIdentifier("SE79455");
		product2.setDescription("this is a dining room set");
		List<ItemModel> productList = new ArrayList<>();
		productList.add(product);
		productList.add(product2);
		when(productRepo.findByItemName("dining room set")).thenReturn(productList);
		List<ItemModel> products = itemService.showAllItemByName("dining room set");
		assertEquals(2, products.size());
	}

}
