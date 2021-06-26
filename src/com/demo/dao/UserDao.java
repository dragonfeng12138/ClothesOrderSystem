package com.demo.dao;

import com.demo.model.User;

import java.util.List;

/**
 * @author Dragon
 * @create 2021-06-26 20:50
 */
public interface UserDao {
    public void add(User user);
    public User find(String id);
    public User find(String username,String password);
}
