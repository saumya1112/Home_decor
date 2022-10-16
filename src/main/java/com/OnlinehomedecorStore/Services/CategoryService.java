package com.OnlinehomedecorStore.Services;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.OnlinehomedecorStore.entities.CategoryModel;

@Service
public interface CategoryService {

	public CategoryModel addCategory(CategoryModel categoryModel);

	public CategoryModel updateCategory(CategoryModel categoryModel);

	public Set<CategoryModel> getCategories();

	public Optional<CategoryModel> getCategory(int categoryId);

	public void delete(Integer categoryId);
}
