package com.demo.dao.impl;

import com.demo.MyUtils;
import com.demo.dao.ClothesDao;
import com.demo.model.Clothes;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.util.List;

/**
 * @author Dragon
 * @create 2021-06-26 19:45
 */
public class ClothesDaoImpl implements ClothesDao {

    @Override
    public void add(Clothes clothes) {
        try {
            QueryRunner runner = new QueryRunner(MyUtils.getDataSource());
            String sql ="insert into clothes(id,name,brand,price,image,description,category_id)values(?,?,?,?,?,?,?)";
            Object[] params = {clothes.getId(), clothes.getName(), clothes.getBrand(), clothes.getPrice(),
                    clothes.getImage(), clothes.getDescription(), clothes.getCategoryId()};
            runner.update(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Clothes find(String id) {
        try {
            QueryRunner runner = new QueryRunner(MyUtils.getDataSource());
            String sql = "select * from clothes where id=?";
            return (Clothes) runner.query(sql, new BeanHandler(Clothes.class));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Clothes> getPageData(int startIndex, int pageSize) {
        try {
            QueryRunner runner = new QueryRunner(MyUtils.getDataSource());
            String sql="select * from clothes limit ?,?";
            Object[] params = {startIndex,pageSize};
            return (List<Clothes>) runner.query(sql,params,new BeanListHandler(Clothes.class));
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getTotalRecord() {
        try {
            QueryRunner runner = new QueryRunner(MyUtils.getDataSource());
            String sql="select count(*) from clothes";
            long totalrecord=(Long)runner.query(sql,new ScalarHandler<>());
            return (int)totalrecord;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Clothes> getPageData(int startIndex, int pageSize, String categoryId) {
        try {
            QueryRunner runner = new QueryRunner(MyUtils.getDataSource());
            String sql="select * from clothes where category_id=? limit ?,?";
            Object[] params={categoryId,startIndex,pageSize};
            return (List<Clothes>)runner.query(sql,params,new BeanListHandler(Clothes.class));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getTotalRecord(String categoryId) {
        try {
            QueryRunner runner = new QueryRunner(MyUtils.getDataSource());
            String sql="select count(*) from clothes where category_id=?";
            long totalrecord=(Long)runner.query(sql,categoryId,new ScalarHandler());
            return (int)totalrecord;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
