package com.OnlinehomedecorStore.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OnlinehomedecorStore.Exceptions.ItemNotFoundException;
import com.OnlinehomedecorStore.Repositories.ProductRepo;
import com.OnlinehomedecorStore.entities.ItemModel;

@Service
public class ItemService implements IItemService {
	@Autowired
	private ProductRepo productRepo;

	public ItemModel getUserById(int Id) throws ItemNotFoundException { // Need to Change
		Optional<ItemModel> optionalProduct = productRepo.findById(Id);
		if (!optionalProduct.isPresent())
			throw new ItemNotFoundException("Product With this Id Dosn't exist" + Id);
		return optionalProduct.get();
	}

	@Override
	public ItemModel addItem(ItemModel item) {
		return productRepo.save(item);
	}

	@Override
	public ItemModel updateItem(ItemModel item) throws ItemNotFoundException {
		Optional<ItemModel> Opti = productRepo.findById(item.getItemId());
		ItemModel itemModel = Opti.get();
		itemModel.setItemCost(item.getItemCost());
		itemModel.setItemName(item.getItemName());
		itemModel.setItemIdentifier(item.getItemIdentifier());
		itemModel.setCategory(item.getCategory());
		itemModel.setDescription(item.getDescription());
		itemModel.setCreatedAt(item.getCreatedAt());
		return productRepo.save(itemModel);
	}

	@Override
	public String deleteItem(int id) throws ItemNotFoundException {
		productRepo.deleteById(id);
		return "Item Deleted !" + id;
	}

	@Override
	public List<ItemModel> showAllItem() {
		return productRepo.findAll();
	}

	@Override
	public ItemModel showItemById(int ItemId) {
		return productRepo.findById(ItemId).get();
	}

	@Override
	public List<ItemModel> showAllItemByName(String ItemName) {
		return productRepo.findByItemName(ItemName);
	}

}
