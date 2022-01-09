package com.graodireto.quemtemboca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.graodireto.quemtemboca.entity.RestaurantItem;
import com.graodireto.quemtemboca.service.RestaurantItemService;

@RestController
@RequestMapping("/restaurantitem")
public class RestaurantItemController {
	
	@Autowired
	private RestaurantItemService restaurantItemService;
	
	@GetMapping("restaurant")
	public ResponseEntity<List<RestaurantItem>> findByRestaurant(
			@RequestParam(name = "restaurantId") Long restaurantId) {
		return ResponseEntity.ok(restaurantItemService.findByRestaurant(restaurantId));
	}
	
}
