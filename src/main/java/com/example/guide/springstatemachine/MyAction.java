package com.example.guide.springstatemachine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.stereotype.Component;

/**
 * @Author: zhangbin
 * @Date: 2021/3/25
 */
@Component
public class MyAction implements Action<MyStateMachineConfig.MyStatus, MyStateMachineConfig.MyEvents> {
    @Autowired
    @Qualifier("stateMachineA")
    private StateMachineFactory<MyStateMachineConfig.MyStatus, MyStateMachineConfig.MyEvents> stateMachine;

    @Override
    public void execute(StateContext<MyStateMachineConfig.MyStatus, MyStateMachineConfig.MyEvents> context) {
        System.out.println("---do start action---");
        System.out.println(context.getSource());
        System.out.println(context.getTarget());
        System.out.println("---end start action---");
        System.out.println("---send pause event---");
        stateMachine.getStateMachine().sendEvent(MyStateMachineConfig.MyEvents.EVENT_PAUSE);
    }
}
