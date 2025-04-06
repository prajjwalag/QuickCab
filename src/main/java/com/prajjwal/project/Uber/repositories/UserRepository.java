package com.prajjwal.project.Uber.repositories;

import com.prajjwal.project.Uber.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
