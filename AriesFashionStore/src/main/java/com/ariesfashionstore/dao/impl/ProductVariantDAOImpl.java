package com.ariesfashionstore.dao.impl;

import com.ariesfashionstore.dao.ProductVariantDAO;
import com.ariesfashionstore.model.ProductVariant;
import com.ariesfashionstore.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductVariantDAOImpl implements ProductVariantDAO {

    // ================= SQL CONSTANTS =================

    private static final String ADD_VARIANT_SQL =
            "INSERT INTO product_variants (product_id, size_label, stock_quantity, is_available) VALUES (?, ?, ?, ?)";

    private static final String UPDATE_VARIANT_SQL =
            "UPDATE product_variants SET product_id=?, size_label=?, stock_quantity=?, is_available=? WHERE variant_id=?";

    private static final String DELETE_VARIANT_SQL =
            "DELETE FROM product_variants WHERE variant_id=?";

    private static final String GET_VARIANT_BY_ID_SQL =
            "SELECT * FROM product_variants WHERE variant_id=?";

    private static final String GET_VARIANT_BY_PRODUCT_AND_SIZE_SQL =
            "SELECT * FROM product_variants WHERE product_id=? AND size_label=?";

    private static final String GET_VARIANTS_BY_PRODUCT_SQL =
            "SELECT * FROM product_variants WHERE product_id=?";

    private static final String GET_ALL_VARIANTS_SQL =
            "SELECT * FROM product_variants";

    private static final String UPDATE_STOCK_SQL =
            "UPDATE product_variants SET stock_quantity=? WHERE variant_id=?";

    private static final String CHECK_STOCK_SQL =
            "SELECT stock_quantity FROM product_variants WHERE variant_id=?";

    // ================= ADD =================

    @Override
    public boolean addProductVariant(ProductVariant variant) {

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(ADD_VARIANT_SQL)) {

            ps.setInt(1, variant.getProductId());
            ps.setString(2, variant.getSizeLabel());
            ps.setInt(3, variant.getStockQuantity());
            ps.setBoolean(4, variant.isAvailable());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // ================= UPDATE =================

    @Override
    public boolean updateProductVariant(ProductVariant variant) {

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_VARIANT_SQL)) {

            ps.setInt(1, variant.getProductId());
            ps.setString(2, variant.getSizeLabel());
            ps.setInt(3, variant.getStockQuantity());
            ps.setBoolean(4, variant.isAvailable());
            ps.setInt(5, variant.getVariantId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // ================= DELETE =================

    @Override
    public boolean deleteProductVariant(int variantId) {

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_VARIANT_SQL)) {

            ps.setInt(1, variantId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // ================= GET BY ID =================

    @Override
    public ProductVariant getVariantById(int variantId) {

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(GET_VARIANT_BY_ID_SQL)) {

            ps.setInt(1, variantId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapResultSetToVariant(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // ================= GET BY PRODUCT + SIZE =================

    @Override
    public ProductVariant getVariantByProductIdAndSize(int productId, String sizeLabel) {

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(GET_VARIANT_BY_PRODUCT_AND_SIZE_SQL)) {

            ps.setInt(1, productId);
            ps.setString(2, sizeLabel);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapResultSetToVariant(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // ================= GET BY PRODUCT =================

    @Override
    public List<ProductVariant> getVariantsByProductId(int productId) {

        List<ProductVariant> list = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(GET_VARIANTS_BY_PRODUCT_SQL)) {

            ps.setInt(1, productId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapResultSetToVariant(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // ================= GET ALL =================

    @Override
    public List<ProductVariant> getAllVariants() {

        List<ProductVariant> list = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(GET_ALL_VARIANTS_SQL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToVariant(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // ================= UPDATE STOCK =================

    @Override
    public boolean updateStock(int variantId, int quantity) {

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_STOCK_SQL)) {

            ps.setInt(1, quantity);
            ps.setInt(2, variantId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // ================= CHECK STOCK =================

    @Override
    public boolean isStockAvailable(int variantId) {

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(CHECK_STOCK_SQL)) {

            ps.setInt(1, variantId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("stock_quantity") > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // ================= HELPER =================

    private ProductVariant mapResultSetToVariant(ResultSet rs) throws SQLException {

        ProductVariant v = new ProductVariant();

        v.setVariantId(rs.getInt("variant_id"));
        v.setProductId(rs.getInt("product_id"));
        v.setSizeLabel(rs.getString("size_label"));
        v.setStockQuantity(rs.getInt("stock_quantity"));
        v.setAvailable(rs.getBoolean("is_available"));

        return v;
    }
}