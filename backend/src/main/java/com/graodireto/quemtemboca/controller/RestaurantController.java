package com.graodireto.quemtemboca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.graodireto.quemtemboca.entity.Restaurant;
import com.graodireto.quemtemboca.service.RestaurantService;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
	
	@Autowired
	private RestaurantService restaurantService;
	
	@GetMapping
	public ResponseEntity<List<Restaurant>> findAll() {
		return ResponseEntity.ok(restaurantService.findAll());
	}
	
	@GetMapping("restaurantoritem")
	public ResponseEntity<List<Restaurant>> findByRestaurantOrItem(
			@RequestParam(name = "queryName") String queryName) {
		return ResponseEntity.ok(restaurantService.findByRestaurantOrItem(queryName));
	}
	
}
