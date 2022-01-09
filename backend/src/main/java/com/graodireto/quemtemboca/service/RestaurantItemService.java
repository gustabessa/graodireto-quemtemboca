package com.graodireto.quemtemboca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graodireto.quemtemboca.entity.RestaurantItem;
import com.graodireto.quemtemboca.repository.restaurantitem.IRestaurantItemRepository;

@Service
public class RestaurantItemService extends AbstractService<RestaurantItem> {

	@Autowired
	private IRestaurantItemRepository restaurantItemRepository;
	
	@Override
	public IRestaurantItemRepository getRepository() {
		return restaurantItemRepository;
	}

	public List<RestaurantItem> findByRestaurant(Long restaurantId) {
		return getRepository().findByRestaurant(restaurantId);
	}

}
