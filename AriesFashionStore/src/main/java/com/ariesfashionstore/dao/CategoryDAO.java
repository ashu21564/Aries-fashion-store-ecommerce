package com.ariesfashionstore.dao;

import com.ariesfashionstore.model.Category;
import java.util.List;

public interface CategoryDAO {

    boolean addCategory(Category category);

    boolean updateCategory(Category category);

    boolean deleteCategory(int categoryId);

    Category getCategoryById(int categoryId);

    Category getCategoryByName(String categoryName);

    List<Category> getAllCategories();
}