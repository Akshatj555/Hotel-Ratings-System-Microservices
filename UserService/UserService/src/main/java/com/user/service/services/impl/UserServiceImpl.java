package com.user.service.services.impl;

import com.netflix.discovery.converters.Auto;
import com.user.service.entities.Hotel;
import com.user.service.entities.Rating;
import com.user.service.entities.User;
import com.user.service.exceptions.ResourceNotFoundException;
import com.user.service.repositories.UserRepository;
import com.user.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {

        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with user id: "+ userId));
        Rating[] ratingsForUser = restTemplate.getForObject("http://localhost:8083/ratings/users/" + userId, Rating[].class);
        List<Rating> ratings = Arrays.stream(ratingsForUser).toList();

        List<Rating> ratingsWithHotel = ratings.stream().map(rating -> {

            ResponseEntity<Hotel> hotelEntity = restTemplate.getForEntity("http://localhost:8082/hotels/" + rating.getHotelId(), Hotel.class);
            Hotel hotel = hotelEntity.getBody();
            rating.setHotel(hotel);

            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingsWithHotel);

        return user;
    }

    @Override
    public void deleteUser(String userId) {

    }

    @Override
    public User updateUser(User user) {
        return null;
    }
}
