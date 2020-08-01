package com.golden.project.dao;

import com.golden.project.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    User findByEmail(String email);
    User findByUserId(String userId);
}
