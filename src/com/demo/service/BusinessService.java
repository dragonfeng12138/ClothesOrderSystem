package com.demo.service;

import com.demo.model.*;

import java.util.List;

/**
 * @author Dragon
 * @create 2021-06-26 23:17
 */
public interface BusinessService {
    public void addcategory(Category category);
    public Category findCategory(String id);
    public List<Category> getAllCategory();
    public void addClothes(Clothes clothes);
    public Clothes findClothes(String id);
    public Page getClothesData(String pageNum);
    public Page getClothesData(String pageNum,String categoryId);
    public void buyClothes(Cart cart,Clothes clothes);
    public void registerUser(User user);
    public User findUser(String id);
    public User UserLogin(String username,String password);
    public void crearteOrder(Cart cart,User user);
    public List<Order> listOrder(String state);
    public Order findOrder(String orderId);
    public void conFirmOrder(String orderId);
    public List<Order> listOrder(String state,String userId);
    public List<Order> clientListOrder(String userId);
}
