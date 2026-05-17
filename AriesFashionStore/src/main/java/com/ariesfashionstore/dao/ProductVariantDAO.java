package com.ariesfashionstore.dao;

import com.ariesfashionstore.model.ProductVariant;
import java.util.List;

public interface ProductVariantDAO {


    boolean addProductVariant(ProductVariant variant);

    boolean updateProductVariant(ProductVariant variant);

    boolean deleteProductVariant(int variantId);


    ProductVariant getVariantById(int variantId);

    ProductVariant getVariantByProductIdAndSize(int productId, String sizeLabel);

    List<ProductVariant> getVariantsByProductId(int productId);

    List<ProductVariant> getAllVariants();


    boolean updateStock(int variantId, int quantity);

    boolean isStockAvailable(int variantId);
}