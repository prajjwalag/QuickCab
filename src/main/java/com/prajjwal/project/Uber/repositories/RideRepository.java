package com.prajjwal.project.Uber.repositories;

import com.prajjwal.project.Uber.entities.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {
}
