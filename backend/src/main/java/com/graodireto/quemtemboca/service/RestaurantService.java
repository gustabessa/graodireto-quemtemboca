package com.graodireto.quemtemboca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graodireto.quemtemboca.entity.Restaurant;
import com.graodireto.quemtemboca.repository.restaurant.IRestaurantRepository;

@Service
public class RestaurantService extends AbstractService<Restaurant> {

	@Autowired
	private IRestaurantRepository restaurantRepository;
	
	@Override
	public IRestaurantRepository getRepository() {
		return restaurantRepository;
	}

	public List<Restaurant> findByRestaurantOrItem(String queryName) {
		return getRepository().findByRestaurantOrItem(queryName);
	}
	
}
