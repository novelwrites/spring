package com.golden.project.dao;

import com.golden.project.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> { //Has ALL the methods for the database calls

}
