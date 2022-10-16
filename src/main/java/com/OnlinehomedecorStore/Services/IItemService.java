package com.OnlinehomedecorStore.Services;

import java.util.List;

import com.OnlinehomedecorStore.Exceptions.ItemNotFoundException;
import com.OnlinehomedecorStore.entities.ItemModel;

public interface IItemService {
	public ItemModel addItem(ItemModel item);

	public ItemModel updateItem(ItemModel item) throws ItemNotFoundException;

	public String deleteItem(int id) throws ItemNotFoundException;

	public List<ItemModel> showAllItem();

	public ItemModel showItemById(int ItemId);

	public List<ItemModel> showAllItemByName(String ItemName);
}
