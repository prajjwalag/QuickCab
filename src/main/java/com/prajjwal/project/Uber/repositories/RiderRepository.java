package com.prajjwal.project.Uber.repositories;

import com.prajjwal.project.Uber.entities.Rider;
import com.prajjwal.project.Uber.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RiderRepository extends JpaRepository<Rider, Long> {
    Optional<Rider> findByUser(User user);
}
