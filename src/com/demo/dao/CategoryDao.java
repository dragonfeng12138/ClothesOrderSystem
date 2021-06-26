package com.demo.dao;

import com.demo.model.Category;

import java.util.List;

/**
 * @author dragon
 * @create 2021-06-26 14:56
 */
public interface CategoryDao {
    public void add(Category category);
    public Category find(String id);
    public List<Category> getAll();
}
