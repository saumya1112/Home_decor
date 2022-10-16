package com.OnlineMobileStore.service;

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

import com.OnlineMobileStore.Exceptions.ItemNotFoundException;
import com.OnlineMobileStore.Repositories.ProductRepo;
import com.OnlineMobileStore.Services.ItemService;
import com.OnlineMobileStore.entities.ItemModel;

@SpringBootTest
public class MobileServiceTest {
	@InjectMocks
	private ItemService mobileService = new ItemService();
	@Mock
	private ProductRepo productRepo;

	@Test
	void testGetProductById() {
		ItemModel product = new ItemModel();
		product.setItemId(100);
		product.setItemName("Iphone11");
		product.setItemCost(50000);
		product.setItemIdentifier("SE55455");
		product.setDescription("Apple");
		product.setCategory(product.getCategory());
		Optional<ItemModel> optionalProduct = Optional.of(product);
		when(productRepo.findById(100)).thenReturn(optionalProduct);
		ItemModel myProduct = mobileService.showItemById(100);
		assertEquals("Iphone11", myProduct.getItemName());
	}

	@Test
	void testGetProductByIdWithException() {
		when(productRepo.findById(100)).thenThrow(ItemNotFoundException.class);
		assertThrows(ItemNotFoundException.class, () -> mobileService.showItemById(100));
	}

	@Test
	void testGetAllProducts() {
		ItemModel product = new ItemModel();
		product.setItemId(100);
		product.setItemName("Iphone11");
		product.setItemCost(50000);
		product.setItemIdentifier("SE55455");
		product.setDescription("Apple");
		product.setCategory(product.getCategory());
		ItemModel product2 = new ItemModel();
		product2.setItemId(200);
		product2.setItemName("SumsungM32");
		product2.setItemCost(20000);
		product2.setItemIdentifier("SE79455");
		product2.setDescription("Android");
		product2.setCategory(product2.getCategory());
		ItemModel product3 = new ItemModel();
		product3.setItemId(300);
		product3.setItemName("Nokia");
		product3.setItemCost(60000);
		product3.setItemIdentifier("SE2672");
		product3.setDescription("Android");
		product3.setCategory(product3.getCategory());
		List<ItemModel> productList = new ArrayList<>();
		productList.add(product3);
		productList.add(product2);
		productList.add(product);
		when(productRepo.findAll()).thenReturn(productList);
		List<ItemModel> products = mobileService.showAllItem();
		assertEquals(3, products.size());
	}

	@Test
	void testSaveProduct() {
		ItemModel product = new ItemModel();
		product.setItemId(100);
		product.setItemName("Iphone11");
		product.setItemCost(50000);
		product.setItemIdentifier("SE55455");
		product.setDescription("Apple");
		product.setCategory(product.getCategory());
		when(productRepo.save(product)).thenReturn(product);
		ItemModel newProduct = mobileService.addItem(product);
		assertEquals("Iphone11", newProduct.getItemName());
		verify(productRepo, times(1)).save(product); // useful for testing void methods
	}

	@Test
	void testDeleteProduct() throws ItemNotFoundException {
		ItemModel product = new ItemModel();
		product.setItemIdsetItemId(100);
		product.setItemName("Iphone11");
		product.setItemCost(50000);
		product.setItemIdentifier("SE55455");
		product.setDescription("Apple");
		product.setCategory(product.getCategory());
		Optional<ItemModel> optionalProduct = Optional.of(product);
		when(productRepo.findById(100)).thenReturn(optionalProduct);
		ItemModel myProduct = mobileService.showMobileById(100);
		mobileService.deleteMobile(100);
		verify(productRepo, times(1)).findById(100);
		verify(productRepo, times(1)).deleteById(100);
	}

	@Test
	void testGetProductByName() {
		ItemModel product = new ItemModel();
		product.setMobileId(100);
		product.setMobileName("Iphone11");
		product.setMobileCost(50000);
		product.setModelNumber("SE55455");
		product.setCompanyName("Apple");
		product.setCategory(product.getCategory());
		ItemModel product2 = new ItemModel();
		product2.setMobileId(200);
		product2.setMobileName("Iphone11");
		product2.setMobileCost(20000);
		product2.setModelNumber("SE79455");
		product2.setCompanyName("Android");
		List<ItemModel> productList = new ArrayList<>();
		productList.add(product);
		productList.add(product2);
		when(productRepo.findByMobileName("Iphone11")).thenReturn(productList);
		List<ItemModel> products = mobileService.showAllMobileByName("Iphone11");
		assertEquals(2, products.size());
	}

}
