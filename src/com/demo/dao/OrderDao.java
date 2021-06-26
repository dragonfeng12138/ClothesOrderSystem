package com.demo.dao;

import com.demo.model.Order;

import java.util.List;

/**
 * @author dragon
 * @create 2021-06-26 14:57
 */
public interface OrderDao {
    public void add(Order order);
    public Order find(String id);
    public List<Order> getAll(boolean state);
    public List<Order> getAll(boolean state,String userId);
    public List<Order> getAllOrder(String userId);
    public void update(Order order);

}
