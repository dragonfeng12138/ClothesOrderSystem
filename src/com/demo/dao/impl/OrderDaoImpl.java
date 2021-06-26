package com.demo.dao.impl;

import com.demo.MyUtils;
import com.demo.dao.OrderDao;
import com.demo.model.Clothes;
import com.demo.model.Order;
import com.demo.model.OrderItem;
import com.demo.model.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;
import java.util.Set;

/**
 * @author Dragon
 * @create 2021-06-26 21:37
 */
public class OrderDaoImpl implements OrderDao {
    @Override
    public void add(Order order) {
        try {
            QueryRunner runner = new QueryRunner(MyUtils.getDataSource());
            String sql="insert into orders(id,ordertime,price,state,user_id)values(?,?,?,?,?)";
            Object[] params={order.getId(),order.getOrdertime(),order.getPrice(),order.getUser().getId()};
            runner.update(sql,params);
            Set<OrderItem> set = order.getOrderItems();
            for (OrderItem item : set) {
                sql="insert into orderitem(id,quantity,price,order_id,clothes_id)values(?,?,?,?,?)";
                params = new Object[]{item.getId(),item.getQuantity(),item.getPrice(),item.getClothes().getImage()};
                runner.update(sql,params);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Order find(String id) {
        try {
            QueryRunner runner =new QueryRunner(MyUtils.getDataSource());
            String sql="select * from orders where id=?";
            Order order = (Order)runner.query(sql,id,new BeanHandler(Order.class));
            sql="select * from oederitem where order_id=?";
            List<OrderItem> list= (List<OrderItem>) runner.query(sql,id,new BeanListHandler(OrderItem.class));
            for (OrderItem item:list ) {
                sql="select clothes.* from orderitem,clothes where" +
                        " orderitem.id=? and orderitem.clothes_id=clothes.id";
                Clothes clothes = (Clothes) runner.query(sql,item.getId(),new BeanHandler(Clothes.class));
        }
            order.getOrderItems().addAll(list);
            sql="select * from orders,user where order.id=? and order.user_id=user.id";
            User user=(User)runner.query(sql,order.getId(),new BeanHandler(User.class));
            order.setUser(user);
            return order;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> getAll(boolean state) {
        try {
            QueryRunner runner =new QueryRunner(MyUtils.getDataSource());
            String sql="select * from orders where state=?";
            List<Order> list = (List<Order>) runner.query(sql,state,new BeanListHandler(Order.class));
            for (Order order : list) {
                sql="select user.* form oeders,user where order.id=? and oeder.user_id=user_id";
                User user=(User) runner.query(sql,order.getId(),new BeanHandler(User.class));
                order.setUser(user);
            }
            return list;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> getAll(boolean state, String userId) {
        try {
            QueryRunner runner=new QueryRunner(MyUtils.getDataSource());
            String sql="select * from orders where state=? and orders.user_id=?";
            Object[] params={state,userId};
            List<Order> list=(List<Order>) runner.query(sql,params,new BeanListHandler(Order.class));
            for (Order order : list) {
                sql="select * from user where user.id=?";
                User user=(User)runner.query(sql,userId,new BeanHandler(User.class));
                order.setUser(user);
            }
            return list;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> getAllOrder(String userId) {
        try {
            QueryRunner runner = new QueryRunner(MyUtils.getDataSource());
            String sql="select * from orders where user_id=?";
            List<Order>list=(List<Order>)runner.query(sql,userId,new BeanListHandler(Order.class));
            for (Order order : list) {
                sql="select * from user where id=?";
                User user=(User) runner.query(sql,userId,new BeanHandler(User.class));
                order.setUser(user);
            }
            return list;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Order order) {
       try {
           QueryRunner runner=new QueryRunner(MyUtils.getDataSource());
           String sql="update orders set state=? where id=?";
           Object[] params={order.isState(),order.getId()};
           runner.update(sql,params);
       }catch (Exception e){
           e.printStackTrace();
           throw new RuntimeException(e);
       }
    }
}
