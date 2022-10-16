package com.OnlinehomedecorStore.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OnlinehomedecorStore.Exceptions.CartNotFoundException;
import com.OnlinehomedecorStore.Exceptions.ProductAllreadyException;
import com.OnlinehomedecorStore.Repositories.CartRepo;
import com.OnlinehomedecorStore.entities.CartModel;

@Service
public class CartService implements ICartService {

	@Autowired
	CartRepo cartRepo;

	@Override
	public List<CartModel> findAll() {
		return cartRepo.findAll();
	}

	@Override
	public CartModel addCart(CartModel cartModel) {
		return cartRepo.save(cartModel);

	}

	@Override
	public CartModel findById(int id) throws CartNotFoundException {
		Optional<CartModel> optionalProduct = cartRepo.findById(id);
		if (!optionalProduct.isPresent())
			throw new CartNotFoundException("Cart With this Id Dosn't exist " + id);
		return optionalProduct.get();
	}

	@Override
	public List<CartModel> findAllByUserid(int id) throws CartNotFoundException {
		if (cartRepo.findAllByUserid(id).isEmpty())
			throw new ProductAllreadyException("Cart Not Found");
		return cartRepo.findAllByUserid(id);
	}

	@Override
	public void delete(int id) throws CartNotFoundException {
		if (cartRepo.findById(id).isEmpty())
			throw new CartNotFoundException("Cart Id is Invalid");
		cartRepo.deleteById(id);
	}

	public Boolean findByUserAndProduct(int User_id, int pid) throws ProductAllreadyException { // Need to Change
		boolean x = false;
		if (cartRepo.existsByUseridAndProduct(User_id, pid))
			throw new ProductAllreadyException("Item Exist"); // .getItemId()

		x = true;
		return x;
	}

	@Override
	public float TotalSum(int userId) {
		return cartRepo.TotalCost(userId);

	}

}
