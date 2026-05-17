package com.ariesfashionstore.dao;

import com.ariesfashionstore.model.Product;
import java.util.List;

public interface ProductDAO {

    // ================= CRUD =================

    boolean addProduct(Product product);

    boolean updateProduct(Product product);

    boolean deleteProduct(int productId);

    Product getProductById(int productId);


    // ================= BASIC FETCH =================

    // ⚠️ Avoid using this directly (may include inactive)
    List<Product> getAllProducts();

    // ✅ USE THIS EVERYWHERE (SAFE)
    List<Product> getAllActiveProducts();


    // ================= FILTERS =================

    // Category filter
    List<Product> getProductsByCategory(int categoryId);

    // Search by name
    List<Product> searchProducts(String keyword);

    // Price range filter
    List<Product> getProductsByPriceRange(double minPrice, double maxPrice);

    // Combined filter
    List<Product> getFilteredProducts(int categoryId, double minPrice, double maxPrice);


    // ================= SORTING =================

    List<Product> getProductsSortedByPriceAsc();

    List<Product> getProductsSortedByPriceDesc();


    // ================= SPECIAL =================

    // Latest products (home page)
    List<Product> getLatestProducts(int limit);

    // Related products (product details page)
    List<Product> getRelatedProducts(int productId, int categoryId);
}