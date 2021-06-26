package com.demo.dao.impl;

import com.demo.MyUtils;
import com.demo.dao.CategoryDao;
import com.demo.model.Category;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.util.List;

/**
 * @author dragon
 * @create 2021-06-26 17:45
 */
public class CategoryDaoImpl implements CategoryDao {

    @Override
    public void add(Category category) {
        try {
            QueryRunner runner=new QueryRunner(MyUtils.getDataSource());
            String sql="insert into category(id,name,description)values(?,?,?)";
            Object params[] = {category.getId(),category.getName(),category.getDescoription()};
            runner.update(sql,params);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Category find(String id){
        try {
            QueryRunner runner=new QueryRunner(MyUtils.getDataSource());
            String sql="select * from category where id=?";
            return (Category) runner.query(sql,new BeanHandler(Category.class));
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Category> getAll(){
        try {
            QueryRunner runner =new QueryRunner(MyUtils.getDataSource());
            String sql="select * from category";
            return (List<Category>) runner.query(sql,new BeanHandler(Category.class));
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
