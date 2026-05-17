package com.ariesfashionstore.dao;

import com.ariesfashionstore.model.User;
import java.util.List;

public interface UserDAO {

    boolean registerUser(User user);
    
    User loginUser(String email, String password);

    User getUserById(int userId);

    User getUserByEmail(String email);

    User getUserByPhone(String phone);

    boolean updateUser(User user);

    boolean updatePassword(int userId, String newPassword);

    boolean isEmailExists(String email);

    boolean isPhoneExists(String phone);

    List<User> getAllUsers();
}