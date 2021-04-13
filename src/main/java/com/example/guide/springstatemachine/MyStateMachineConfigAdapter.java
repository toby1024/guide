package com.example.guide.springstatemachine;

import org.springframework.statemachine.config.StateMachineConfigurerAdapter;

/**
 * @Author: zhangbin
 * @Date: 2021/3/26
 */
public class MyStateMachineConfigAdapter<S extends Object, E extends Object> extends StateMachineConfigurerAdapter<S, E> {
}
