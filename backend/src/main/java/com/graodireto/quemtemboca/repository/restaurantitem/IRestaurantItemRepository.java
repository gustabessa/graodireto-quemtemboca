package com.graodireto.quemtemboca.repository.restaurantitem;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.graodireto.quemtemboca.entity.RestaurantItem;

public interface IRestaurantItemRepository extends JpaRepository<RestaurantItem, Long> {

	@Query("SELECT RI FROM RestaurantItem RI WHERE RI.restaurant.id = :restaurantId")
	List<RestaurantItem> findByRestaurant(@Param("restaurantId") Long restaurantId);

}
