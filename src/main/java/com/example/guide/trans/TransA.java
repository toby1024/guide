package com.example.guide.trans;

import com.example.guide.jpa.entity.User;
import com.example.guide.jpa.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: zhangbin
 * @Date: 2020/12/30
 */
@Slf4j
@Component
public class TransA {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TranssB transsB;

    @Transactional(rollbackFor = Exception.class)
    public String a() {
        userRepository.save(User.builder().name("a").age(10).build());
        try {
            transsB.b();
        } catch (Exception e) {
            log.error("error", e);
        }
        return "a";
    }



}
