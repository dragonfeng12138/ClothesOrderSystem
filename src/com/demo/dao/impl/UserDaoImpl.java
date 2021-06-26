package com.demo.dao.impl;

import com.demo.MyUtils;
import com.demo.dao.UserDao;
import com.demo.model.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

/**
 * @author Dragon
 * @create 2021-06-26 22:57
 */
public class UserDaoImpl implements UserDao {

    @Override
    public void add(User user) {
        try {
            QueryRunner runner=new QueryRunner(MyUtils.getDataSource());
            String sql="insert into user(id,username,password,phone,cellphone,address,email)values(?,?,?,?,?,?,?)";
            Object[] params={user.getId(),user.getUsername(),user.getPassword(),user.getPhone(),
                    user.getCellphone(),user.getAddress(),user.getAddress()};
            runner.update(sql,params);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    @Override
    public User find(String id){
        try {
            QueryRunner runner=new QueryRunner(MyUtils.getDataSource());
            String sql="select * from user where id=?";
            return (User)runner.query(sql,id,new BeanHandler(User.class));
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public User find(String username, String password) {
        try {
            QueryRunner runner=new QueryRunner(MyUtils.getDataSource());
            String sql="select * from user where username=? and password=?";
            Object[] params={username,password};
            return (User)runner.query(sql,params,new BeanHandler(User.class));
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
