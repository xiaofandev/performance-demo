package com.demo.controller;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("product")
public class ProductController {

	@RequestMapping("buy/{productId}")
	public String buy(@PathVariable String productId) throws SQLException {
		String result = "success";
		
		// 1.访问数据库
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/seckill", "root", "root")) {
			try (PreparedStatement ps = conn.prepareStatement("update stock set stock=stock-1 where stock>0 and product_id=?")) {
				ps.setInt(1, Integer.valueOf(productId));
				
				int count = ps.executeUpdate();
				if (count == 0) {
					result = "error";
				}
			}
		} catch (Exception e) {
			result = "error";
		}
		// 2.返回扣减库存结果给用户
		return result;
	}
	
}
