package com.OnlineMobileStore.service;


import com.OnlineMobileStore.Exceptions.CartNotFoundException;
import com.OnlineMobileStore.Exceptions.ProductAllreadyException;
import com.OnlineMobileStore.Repositories.CartRepo;
import com.OnlineMobileStore.Services.CartService;
import com.OnlineMobileStore.entities.CartModel;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CartServiceTest {


    @InjectMocks
    private CartService cartService =new CartService();

    @Mock
    private CartRepo cartRepo;

    @Test
    void testSave(){
        CartModel cart=new CartModel();
        cart.setCartId(1);
        cart.setQuantity(5);
        cart.setUserid(1);
        cart.setPrice(20000);
        cart.setProduct(4);

        when(cartRepo.save(cart)).thenReturn(cart);
        CartModel myCart = cartService.addCart(cart);
        assertEquals(1,myCart.getCartId());
        verify(cartRepo,times(1)).save(cart); // useful for testing void methods
    }
    @Test
    void testGetProductById() {
        CartModel cart = new CartModel();
        cart.setCartId(1);
        cart.setQuantity(5);
        cart.setUserid(1);
        cart.setPrice(20000);
        cart.setProduct(4);
        Optional<CartModel> optionalProduct = Optional.of(cart);
        when(cartRepo.findById(1)).thenReturn(optionalProduct);
        CartModel myProduct = cartService.findById(1);
        assertEquals(1, myProduct.getCartId());
    }
   // findByUserAndProduct
   @Test
   void testFindByUserAndProduct() throws ProductAllreadyException {
       when(cartRepo.existsByUseridAndProduct(1,1)).thenThrow(ProductAllreadyException.class);
       assertThrows(ProductAllreadyException.class,()->cartService.findByUserAndProduct(1,1));

   }

    @Test
    void testFindAllByUserid(){
        CartModel cart = new CartModel();
        cart.setCartId(1);
        cart.setQuantity(5);
        cart.setUserid(2);
        cart.setPrice(20000);
        cart.setProduct(4);
        CartModel cart2 = new CartModel();
        cart2.setCartId(2);
        cart2.setQuantity(3);
        cart2.setUserid(2);
        cart2.setPrice(100000);
        cart2.setProduct(4);

        List<CartModel> cartList=new ArrayList<>();
        cartList.add(cart);
        cartList.add(cart2);
        when(cartRepo.findAllByUserid(1)).thenReturn(cartList);
        List<CartModel> myProduct = cartService.findAllByUserid(1);
        assertEquals(2,myProduct.size());
    }

    @Test
    void testDeleteProduct() throws CartNotFoundException {
        CartModel cart = new CartModel();
        cart.setCartId(10);
        cart.setQuantity(5);
        cart.setUserid(1);
        cart.setPrice(20000);
        cart.setProduct(4);
        Optional<CartModel> optionalProduct = Optional.of(cart);
        when(cartRepo.findById(10)).thenReturn(optionalProduct);
        cartService.delete(10);
        verify(cartRepo, times(1)).findById(10);
        verify(cartRepo, times(1)).deleteById(10);

    }







}
