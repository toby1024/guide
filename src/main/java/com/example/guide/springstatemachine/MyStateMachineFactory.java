package com.example.guide.springstatemachine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.service.DefaultStateMachineService;
import org.springframework.statemachine.service.StateMachineService;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhangbin
 * @Date: 2021/3/26
 */
@Component
public class MyStateMachineFactory {
    @Autowired
    @Qualifier("stateMachineA")
    private StateMachineFactory<MyStateMachineConfig.MyStatus, MyStateMachineConfig.MyEvents> stateMachineA;

    private Map<String, StateMachineService> stateMachineServiceMap;

    public MyStateMachineFactory(StateMachineFactory<MyStateMachineConfig.MyStatus, MyStateMachineConfig.MyEvents> stateMachine) {
        this.stateMachineA = stateMachine;
        stateMachineServiceMap = new HashMap<>();
        stateMachineServiceMap.put("stateMachine", new DefaultStateMachineService(stateMachineA));
    }

    public Map<String, StateMachineService> getStateMachineServiceMap() {
        return stateMachineServiceMap;
    }


    public StateMachineService get(String key) {
        return stateMachineServiceMap.get(key);
    }
}
