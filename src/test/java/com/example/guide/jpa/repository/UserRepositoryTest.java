package com.example.guide.jpa.repository;

import com.example.guide.jpa.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserRepositoryTest {

  @Test
  public void testSave() {
    User u = new User();
    u.setName("test");
    u.setAge(20);
    User save = userRepository.save(u);

    assertEquals(20, save.getAge());
  }


  @Autowired
  private UserRepository userRepository;
}