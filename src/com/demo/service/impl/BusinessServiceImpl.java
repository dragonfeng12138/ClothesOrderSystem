package com.demo.service.impl;

import com.demo.dao.*;
import com.demo.model.*;
import com.demo.service.BusinessService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Dragon
 * @create 2021-06-26 23:15
 */
public class BusinessServiceImpl implements BusinessService {

    private CategoryDao categoryDao = DaoFactory.getInstance().createDao("com.demo.dao.impl.CreategoryDaoImpl",
            CategoryDao.class);
    private ClothesDao clothesDao =DaoFactory.getInstance().createDao("com.demo.dao.impl.ClothesDaoImpl",
            ClothesDao.class);
    private UserDao userDao = DaoFactory.getInstance().createDao("com.demo.dao.impl.UserDaoImpl",
            UserDao.class);
    private OrderDao orderDao= DaoFactory.getInstance().createDao("com.demo.dao.impl.OrderDaoimpl",
            OrderDao.class);

    @Override
    public void addcategory(Category category) {
        categoryDao.add(category);
    }

    @Override
    public Category findCategory(String id) {
        return categoryDao.find(id);
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryDao.getAll();
    }

    @Override
    public void addClothes(Clothes clothes) {
        clothesDao.add(clothes);
    }

    @Override
    public Clothes findClothes(String id) {
        return clothesDao.find(id);
    }

    @Override
    public Page getClothesData(String pageNum) {
        int totalrecord =clothesDao.getTotalRecord();
        Page page=null;
        if(pageNum==null){
            page=new Page(1,totalrecord);
        }
        else {
            page=new Page(Integer.parseInt(pageNum),totalrecord);
        }
        List<Clothes> list=clothesDao.getPageData(page.getStartIndex(),page.getPageSize());
        return page;
    }

    @Override
    public Page getClothesData(String pageNum, String categoryId) {
        int totalrecord = clothesDao .getTotalRecord(categoryId);
        Page page=null;
        if(pageNum==null){
            page=new Page(1,totalrecord);
        }
        else {
            page = new Page(Integer.parseInt(pageNum),totalrecord);
        }
        List<Clothes> list = clothesDao.getPageData(page.getStartIndex(),page.getPageSize(),categoryId);
        return page;
    }

    @Override
    public void buyClothes(Cart cart, Clothes clothes) {
        cart.add(clothes);
    }

    @Override
    public void registerUser(User user) {
        userDao.add(user);
    }

    @Override
    public User findUser(String id) {
        return userDao.find(id);
    }

    @Override
    public User UserLogin(String username, String password) {
        return userDao.find(username,password);
    }

    @Override
    public void crearteOrder(Cart cart, User user) {
        if(cart==null){
            throw new RuntimeException("对不起，您还没有购买任何商品");
        }
        Order order= new Order();
        order.setId("1");
        order.setOrdertime(new Date());
        order.setPrice(cart.getPrice());
        order.setState(false);
        order.setUser(user);
        for(Map.Entry<String, CartItem> me : cart.getMap().entrySet()){
            CartItem cItem=me.getValue();
            OrderItem oItem=new OrderItem();
            oItem.setClothes(cItem.getClothes());
            oItem.setPrice(cItem.getPrice());
            oItem.setId("1");
            oItem.setQuantity(cItem.getQuantity());
            order.getOrderItems().add(oItem);
        }
    }

    @Override
    public List<Order> listOrder(String state) {
        return orderDao.getAll(Boolean.parseBoolean(state));
    }

    @Override
    public Order findOrder(String orderId) {
        return orderDao.find(orderId);
    }

    @Override
    public void conFirmOrder(String orderId) {
        Order order=orderDao.find(orderId);
        order.setState(true);
        orderDao.update(order);
    }

    @Override
    public List<Order> listOrder(String state, String userId) {
        return orderDao.getAll(Boolean.parseBoolean(state),userId);
    }

    @Override
    public List<Order> clientListOrder(String userId) {
        return orderDao.getAllOrder(userId);
    }
}
