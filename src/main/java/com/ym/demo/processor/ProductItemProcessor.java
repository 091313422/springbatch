package com.ym.demo.processor;

import com.ym.demo.dao.ProductTestMapper;
import com.ym.demo.model.Product;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Processor that finds existing products and updates a product quantity appropriately
 */
public class ProductItemProcessor implements ItemProcessor<Product,Product>
{
    @Autowired
    private ProductTestMapper productTestMapper;

    @Override
    public Product process(Product product) throws Exception
    {
        // Retrieve the product from the database
        List<Product> productList = productTestMapper.queryProductById(product.getId());

        if( productList.size() > 0 )
        {
            // Add the new quantity to the existing quantity
            Product existingProduct = productList.get( 0 );
            product.setQuantity( existingProduct.getQuantity() + product.getQuantity() );
        }

        // Return the (possibly) update prduct
        return product;
    }
}
