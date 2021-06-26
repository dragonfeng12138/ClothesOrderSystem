package com.demo.dao;

import com.demo.model.Clothes;

import java.util.List;

/**
 * @author dragon
 * @create 2021-06-26 14:57
 */
public interface ClothesDao {
    public void add(Clothes clothes);
    public Clothes find(String id);
    List<Clothes> getPageData(int startIndex, int pageSize);
    public int getTotalRecord();
    List<Clothes> getPageData(int startIndex, int pageSize, String categoryId);
    public int getTotalRecord(String categoryId);

}
