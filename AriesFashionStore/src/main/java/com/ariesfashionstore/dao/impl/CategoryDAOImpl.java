package com.ariesfashionstore.dao.impl;

import com.ariesfashionstore.dao.CategoryDAO;
import com.ariesfashionstore.model.Category;
import com.ariesfashionstore.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {

    // ================= SQL CONSTANTS =================

    private static final String ADD_CATEGORY_SQL =
            "INSERT INTO categories (category_name, description) VALUES (?, ?)";

    private static final String UPDATE_CATEGORY_SQL =
            "UPDATE categories SET category_name = ?, description = ? WHERE category_id = ?";

    private static final String DELETE_CATEGORY_SQL =
            "DELETE FROM categories WHERE category_id = ?";

    private static final String GET_CATEGORY_BY_ID_SQL =
            "SELECT * FROM categories WHERE category_id = ?";

    private static final String GET_CATEGORY_BY_NAME_SQL =
            "SELECT * FROM categories WHERE category_name = ?";

    private static final String GET_ALL_CATEGORIES_SQL =
            "SELECT * FROM categories";

    // ================= ADD =================

    @Override
    public boolean addCategory(Category category) {

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(ADD_CATEGORY_SQL)) {

            ps.setString(1, category.getCategoryName());
            ps.setString(2, category.getDescription());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // ================= UPDATE =================

    @Override
    public boolean updateCategory(Category category) {

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_CATEGORY_SQL)) {

            ps.setString(1, category.getCategoryName());
            ps.setString(2, category.getDescription());
            ps.setInt(3, category.getCategoryId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // ================= DELETE =================

    @Override
    public boolean deleteCategory(int categoryId) {

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_CATEGORY_SQL)) {

            ps.setInt(1, categoryId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // ================= GET BY ID =================

    @Override
    public Category getCategoryById(int categoryId) {

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(GET_CATEGORY_BY_ID_SQL)) {

            ps.setInt(1, categoryId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapResultSetToCategory(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // ================= GET BY NAME =================

    @Override
    public Category getCategoryByName(String categoryName) {

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(GET_CATEGORY_BY_NAME_SQL)) {

            ps.setString(1, categoryName);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapResultSetToCategory(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // ================= GET ALL =================

    @Override
    public List<Category> getAllCategories() {

        List<Category> categories = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(GET_ALL_CATEGORIES_SQL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                categories.add(mapResultSetToCategory(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }

    // ================= HELPER =================

    private Category mapResultSetToCategory(ResultSet rs) throws SQLException {

        Category category = new Category();

        category.setCategoryId(rs.getInt("category_id"));
        category.setCategoryName(rs.getString("category_name"));
        category.setDescription(rs.getString("description"));

        return category;
    }
}