package com.graodireto.quemtemboca.repository.restaurant;

import java.util.List;

import com.graodireto.quemtemboca.entity.Restaurant;

public interface RestaurantRepositoryCustom {

	List<Restaurant> findByRestaurantOrItem(String queryName);

}
