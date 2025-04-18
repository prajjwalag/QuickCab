package com.prajjwal.project.Uber.repositories;

import com.prajjwal.project.Uber.entities.Driver;
import com.prajjwal.project.Uber.entities.Rating;
import com.prajjwal.project.Uber.entities.Ride;
import com.prajjwal.project.Uber.entities.Rider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    List<Rating> findByRider(Rider rider);
    List<Rating> findByDriver(Driver driver);


    Optional<Rating> findByRide(Ride ride);
}
