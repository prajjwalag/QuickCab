package com.prajjwal.project.Uber.repositories;

import com.prajjwal.project.Uber.entities.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiderRepository extends JpaRepository<Rider, Long> {
}
