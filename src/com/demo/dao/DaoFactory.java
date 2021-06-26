package com.demo.dao;

/**
 * @author Dragon
 * @create 2021-06-27 0:08
 */
public class DaoFactory {
    private static final DaoFactory FACTORY=new DaoFactory();
    private DaoFactory(){};
    public static DaoFactory getInstance(){
        return FACTORY;
    }
    public <T> T createDao(String className,Class<T> clazz){
        try {
            T t = (T) Class.forName(className).newInstance();
            return t;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
