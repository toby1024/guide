package com.example.guide.trans;

import com.example.guide.jpa.entity.User;
import com.example.guide.jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: zhangbin
 * @Date: 2020/12/30
 */
@Component
public class TranssB {
    @Autowired
    private UserRepository userRepository;

    @Transactional(rollbackFor = Exception.class)
    public String b() throws Exception {
        userRepository.save(User.builder().name("b").age(10).build());
        throw new Exception("111");
    }

}
