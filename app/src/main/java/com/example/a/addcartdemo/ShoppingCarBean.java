package com.example.a.addcartdemo;

import java.util.List;

/**
 * Created by 陈志凯 on 2017/12/12.
 */

public class ShoppingCarBean extends BaseBean {
    public List<Product> data;
    public static  class Product{
        public List<Specs>  specs;
       public int status;
        public ShoppingCarBean.CartItem cartItem;

    }
    public static class Specs{
        public String specValue;
        public String specKey;
    }
    public static class CartItem{
        public int productId;
        public String productName;
        public double productPrice;
        public int number;
        public String image;
    }

}
