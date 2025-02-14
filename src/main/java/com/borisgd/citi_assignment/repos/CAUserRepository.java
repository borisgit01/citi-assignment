package com.borisgd.citi_assignment.repos;

import com.borisgd.citi_assignment.domain.CAUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CAUserRepository extends JpaRepository<CAUser, Integer> {
}
