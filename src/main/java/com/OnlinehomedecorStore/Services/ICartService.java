package com.OnlinehomedecorStore.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.OnlinehomedecorStore.Exceptions.ItemNotFoundException;
import com.OnlinehomedecorStore.entities.CartModel;

@Service
public interface ICartService {
	public List<CartModel> findAll();

	public CartModel addCart(CartModel cartModel);

	public CartModel findById(int id);

	public List<CartModel> findAllByUserid(int User_id);

	public Boolean findByUserAndProduct(int User_id, int pid) throws ItemNotFoundException;

	public void delete(int id);

	public float TotalSum(int userId);
}
