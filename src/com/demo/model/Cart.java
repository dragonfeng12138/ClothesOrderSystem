package com.demo.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dragon
 * @create 2021-06-26 14:19
 */
public class Cart {
    private Map<String, CartItem> map=new HashMap<String, CartItem>();
    private double price;
    public void add(Clothes clothes){
        CartItem item=map.get(clothes.getId());
        if (item==null){
            item = new CartItem();
            item.setClothes(clothes);
            item.setQuantity(1);
            item.setPrice(clothes.getPrice());
            map.put(clothes.getId(),item);
        }
        else {
            item.setQuantity(item.getQuantity()+1);
            item.setPrice(item.getPrice()+clothes.getPrice());
        }
    }

    public Map<String, CartItem> getMap() {
        return map;
    }

    public void setMap(Map<String, CartItem> map) {
        this.map = map;
    }

    public double getPrice() {
        double totalPrice=0;
        for (Map.Entry<String, CartItem> me : map.entrySet()){
            CartItem item=me.getValue();
            totalPrice=totalPrice+item.getPrice();
        }
        this.price=totalPrice;
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
