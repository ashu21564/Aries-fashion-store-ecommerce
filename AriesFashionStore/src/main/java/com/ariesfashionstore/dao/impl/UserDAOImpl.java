package com.ariesfashionstore.dao.impl;

import com.ariesfashionstore.dao.UserDAO;
import com.ariesfashionstore.model.User;
import com.ariesfashionstore.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    // ================= SQL CONSTANTS =================

    private static final String INSERT_USER_SQL =
            "INSERT INTO users (full_name, email, phone, password, gender, address) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String LOGIN_USER_SQL =
            "SELECT * FROM users WHERE email = ?";

    private static final String GET_USER_BY_ID_SQL =
            "SELECT * FROM users WHERE user_id = ?";

    private static final String GET_USER_BY_EMAIL_SQL =
            "SELECT * FROM users WHERE email = ?";

    private static final String GET_USER_BY_PHONE_SQL =
            "SELECT * FROM users WHERE phone = ?";

    private static final String UPDATE_USER_SQL =
            "UPDATE users SET full_name = ?, email = ?, phone = ?, gender = ?, address = ? WHERE user_id = ?";

    private static final String UPDATE_PASSWORD_SQL =
            "UPDATE users SET password = ? WHERE user_id = ?";

    private static final String CHECK_EMAIL_EXISTS_SQL =
            "SELECT user_id FROM users WHERE email = ?";

    private static final String CHECK_PHONE_EXISTS_SQL =
            "SELECT user_id FROM users WHERE phone = ?";

    private static final String GET_ALL_USERS_SQL =
            "SELECT * FROM users";

    // ================= REGISTER =================

    @Override
    public boolean registerUser(User user) {

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_USER_SQL)) {

            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPhone());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getGender());
            ps.setString(6, user.getAddress());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // ================= LOGIN =================

    @Override
    public User loginUser(String email, String password) {

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(LOGIN_USER_SQL)) {

            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");

                if (password.equals(storedPassword)) {
                    return mapResultSetToUser(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // ================= GET USER BY ID =================

    @Override
    public User getUserById(int userId) {

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(GET_USER_BY_ID_SQL)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapResultSetToUser(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // ================= GET USER BY EMAIL =================

    @Override
    public User getUserByEmail(String email) {

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(GET_USER_BY_EMAIL_SQL)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapResultSetToUser(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // ================= GET USER BY PHONE =================

    @Override
    public User getUserByPhone(String phone) {

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(GET_USER_BY_PHONE_SQL)) {

            ps.setString(1, phone);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapResultSetToUser(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // ================= UPDATE USER =================

    @Override
    public boolean updateUser(User user) {

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_USER_SQL)) {

            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPhone());
            ps.setString(4, user.getGender());
            ps.setString(5, user.getAddress());
            ps.setInt(6, user.getUserId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // ================= UPDATE PASSWORD =================

    @Override
    public boolean updatePassword(int userId, String newPassword) {

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_PASSWORD_SQL)) {

            ps.setString(1, newPassword);
            ps.setInt(2, userId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // ================= CHECK EMAIL EXISTS =================

    @Override
    public boolean isEmailExists(String email) {

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(CHECK_EMAIL_EXISTS_SQL)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // ================= CHECK PHONE EXISTS =================

    @Override
    public boolean isPhoneExists(String phone) {

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(CHECK_PHONE_EXISTS_SQL)) {

            ps.setString(1, phone);
            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // ================= GET ALL USERS =================

    @Override
    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(GET_ALL_USERS_SQL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                users.add(mapResultSetToUser(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    // ================= HELPER METHOD =================

    private User mapResultSetToUser(ResultSet rs) throws SQLException {

        User user = new User();

        user.setUserId(rs.getInt("user_id"));
        user.setFullName(rs.getString("full_name"));
        user.setEmail(rs.getString("email"));
        user.setPhone(rs.getString("phone"));
        user.setPassword(rs.getString("password"));
        user.setGender(rs.getString("gender"));
        user.setAddress(rs.getString("address"));

        return user;
    }
}