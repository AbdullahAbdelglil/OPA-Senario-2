package com.example.OPA_senario2;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User findUserByID(int id) {
        return userDao.findById(id);
    }
}
