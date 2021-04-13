package com.example.guide.springevent;

import com.example.guide.jpa.entity.User;
import com.example.guide.jpa.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zhangbin
 * @Date: 2020/12/29
 */
@Slf4j
@RestController
public class EventPublisher {
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    @Autowired
    private UserRepository userRepository;

    @Transactional(rollbackFor = Exception.class)
    @GetMapping("/publish")
    public String publishEvent(String parameter){
        log.info("start publish event:{}", parameter);
        log.info("a--->{}", TransactionSynchronizationManager.getCurrentTransactionName());
        log.info("a--->{}", TransactionSynchronizationManager.getCurrentTransactionIsolationLevel());
        log.info("a--->{}", TransactionSynchronizationManager.isCurrentTransactionReadOnly());
        eventPublisher.publishEvent(parameter);

        userRepository.save(User.builder().name("test").age(18).build());
        log.info("publish event:{} success", parameter);
        return parameter;
    }
}
