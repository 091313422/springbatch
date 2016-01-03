package com.ym.demo.writer;

import com.ym.demo.dao.ProductTestMapper;
import com.ym.demo.model.Product;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Writes products to a database
 */
public class ProductItemWriter implements ItemWriter<Product>
{
    @Autowired
    private ProductTestMapper productTestMapper;

    @Override
    public void write(List<? extends Product> products) throws Exception
    {
        for( Product product : products )
        {
            List<Product> productList = productTestMapper.queryProductById(product.getId());

            if( productList.size() > 0 )
            {
            	productTestMapper.updateProduct(product);
            }
            else
            {
            	productTestMapper.insertProductList(product);
            }
        }
    }
}
