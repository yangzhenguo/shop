package com.hiyzg.shop.service.impl;

import com.hiyzg.shop.model.Product;
import com.hiyzg.shop.service.ProductService;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Sam on 2019/3/18.
 */
public class ProductServiceImplTest {
    private ProductService productService = new ProductServiceImpl();

    @Test
    public void listHot() throws Exception {
        List<Product> products = this.productService.listHot();
        System.out.println(products);
        assertFalse(products.isEmpty());
    }

    @Test
    public void listNew() throws Exception {
        List<Product> products = this.productService.listNew();
        System.out.println(products);
        assertFalse(products.isEmpty());
    }

}