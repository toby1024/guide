package com.example.guide.springstatemachine;

import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

/**
 * @Author: zhangbin
 * @Date: 2021/3/25
 */
@Component
public class StopAction implements Action<MyStateMachineConfig.MyStatus, MyStateMachineConfig.MyEvents> {
    @Override
    public void execute(StateContext<MyStateMachineConfig.MyStatus, MyStateMachineConfig.MyEvents> context) {
        System.out.println("---do stop action---");
        System.out.println(context.getSource());
        System.out.println(context.getTarget());
        System.out.println("---end stop action---");
    }
}
