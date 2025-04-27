package com.rating.RatingService.services;

import com.rating.RatingService.entities.Rating;

import java.util.List;

public interface RatingService {

    Rating createRating(Rating rating);

    List<Rating> getAllRatings();

    List<Rating> getAllRatingsByUserId(String userId);

    List<Rating> getAllRatingsByHotelId(String hotelId);
}
