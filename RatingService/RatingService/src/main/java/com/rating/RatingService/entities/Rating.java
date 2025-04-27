package com.rating.RatingService.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("user_ratings")
public class Rating {

    @Id
    private String ratingId;
    //In mongo Db the Id - primary key is automatically given by the DB

    private String userId;
    private String hotelId;
    private Integer rating;
    private String feedback;
}
