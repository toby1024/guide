package com.example.guide.jpa.repository;

import com.example.guide.jpa.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @program: guide
 * @description:
 * @author: Jason
 * @date: 2019-12-06 10:17
 **/
public interface UserRepository extends CrudRepository<User, Long> {

    User findByName(String name);
}
