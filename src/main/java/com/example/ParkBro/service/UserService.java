package com.example.ParkBro.service;

import com.example.ParkBro.model.User;

public interface UserService {

    void deleteUser(Integer userId);
    User updatePassword(Integer userId, String password);
    void register(String name, String phoneNumber, String password);
}
