package com.prajjwal.project.Uber.repositories;

import com.prajjwal.project.Uber.entities.RideRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRequestRepository extends JpaRepository<RideRequest, Long> {
}
