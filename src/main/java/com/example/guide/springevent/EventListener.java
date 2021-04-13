package com.example.guide.springevent;

import com.example.guide.jpa.entity.User;
import com.example.guide.jpa.repository.UserRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * @Author: zhangbin
 * @Date: 2020/12/29
 */
@Slf4j
@Component
public class EventListener {
    @Autowired
    private UserRepository userRepository;

    @SneakyThrows
    @Async
    @org.springframework.context.event.EventListener
    public void asyncListener(String parameter) {
        log.info("1:async listener message:{}", parameter);
        Thread.sleep(1000L);
        userRepository.save(User.builder().name("test async").age(18).build());
        log.info("1:----async wakeup-----");
    }

    @SneakyThrows
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void listenerAfterCommit(String parameter) {

        log.info("--->{}", TransactionSynchronizationManager.getCurrentTransactionName());
        log.info("--->{}", TransactionSynchronizationManager.getCurrentTransactionIsolationLevel());
        log.info("--->{}", TransactionSynchronizationManager.isCurrentTransactionReadOnly());
        log.info("--->{}", TransactionSynchronizationManager.isActualTransactionActive());

        log.info("2:after commit listener message:{}", parameter);
        User test = userRepository.findById(1L).get();
        test.setAge(233);
        test.setName("trans");
        userRepository.save(test);
        log.info("test--->{}:{}", test.getName(), test.getAge());
        userRepository.save(User.builder().name("test trans").age(18).build());
        Thread.sleep(1000L);
        log.info("2:----after commit wakeup-----");
    }

    @SneakyThrows
    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void listenerAfterCommit2(String parameter) {
        log.info("2.2:after commit listener message:{}", parameter);
        User test = userRepository.findById(2L).get();
        test.setAge(666);
        userRepository.save(test);
        log.info("2.2test--->{}:{}", test.getName(), test.getAge());
        userRepository.save(User.builder().name("test trans").age(18).build());
        Thread.sleep(1000L);
        log.info("2.2:----after commit wakeup-----");
    }

    @SneakyThrows
    @org.springframework.context.event.EventListener
    public void listener(String parameter) {
        log.info("3:listener message:{}", parameter);
        userRepository.save(User.builder().name("test normal").age(18).build());
        Thread.sleep(1000L);
        log.info("3:----wakeup-----");
    }
}
