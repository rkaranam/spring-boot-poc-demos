package com.springtweaks.boot.poc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MultipleDbController {
	
	@Autowired
	@Qualifier("mysqlJdbcTemplate")
	private JdbcTemplate mysqlJdbcTemplate;
	
	@Autowired
	@Qualifier("h2JdbcTemplate")
	private JdbcTemplate h2JdbcTemplate;
	
	@RequestMapping("/mysql-users")
	public @ResponseBody String getMySqlUsers() {
		List<Map<String, Object>> users = new ArrayList<Map<String, Object>>();
		String query = "select * from usermaster";
		
		try {
			users = mysqlJdbcTemplate.queryForList(query);			
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		return users.toString();		
	}
	
	@RequestMapping("/h2-users")
	public String getH2Users() {
		Map<String, Object> users = new HashMap<String, Object>();
		String query = "select * from usermaster";
		
		try {
			users = h2JdbcTemplate.queryForMap(query);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		return users.toString();
	}
}
