package com.graodireto.quemtemboca.repository.restaurant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.graodireto.quemtemboca.entity.Restaurant;
import com.graodireto.quemtemboca.repository.AbstractRepository;

public class RestaurantRepositoryCustomImpl extends AbstractRepository<Restaurant> implements RestaurantRepositoryCustom {

	@Override
	public List<Restaurant> findByRestaurantOrItem(String queryName) {
		
		StringBuilder queryBuilder = new StringBuilder();
		Map<String, Object> params = new HashMap<>();
		
		queryName = queryName != null ? "%" + queryName + "%" : "%%"; 
		
		queryBuilder.append("SELECT DISTINCT R FROM Restaurant R");
		queryBuilder.append(" INNER JOIN R.restaurantCategory RC");
		queryBuilder.append(" INNER JOIN RestaurantItem RI");
		queryBuilder.append(" ON RI.restaurant.id = R.id");
		queryBuilder.append(" WHERE (lower(R.name) LIKE lower(:queryName))");
		queryBuilder.append(" 	OR (lower(RC.name) LIKE lower(:queryName))");
		queryBuilder.append(" 	OR (lower(RI.name) LIKE lower(:queryName))");
		queryBuilder.append(" ORDER BY R.id");
		
		params.put("queryName", queryName);
		
		return executeQuery(queryBuilder.toString(), params);
	}

}
