package com.ym.demo.dao;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.ym.demo.model.Product;

public interface ProductTestMapper {

	public List<Product> queryProduct(@Param("test") Integer test);
	
	public List<Product> queryProductById(@Param("id") Integer id);
	
	public Integer insertBatchProductList(@Param("list") List<Product> list);
	
	public Integer insertProductList(@Param("product") Product product);
	
	public Integer updateProduct(@Param("product") Product product);
}
