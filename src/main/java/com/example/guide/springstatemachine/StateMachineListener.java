package com.example.guide.springstatemachine;

import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

/**
 * @Author: zhangbin
 * @Date: 2021/3/24
 */
@Component
public class StateMachineListener<S extends MyStateMachineConfig.MyStatus, E extends MyStateMachineConfig.MyEvents> extends StateMachineListenerAdapter<S, E> {

    @Override
    public void stateChanged(State<S, E> from, State<S, E> to) {
        try {
            System.out.println("from =>" + from.getStates());
            System.out.println("to =>" + to.getStates());
        }catch (Exception e){}
    }

    @Override
    public void transition(Transition<S, E> transition) {
        System.out.println("source =>" + transition.getSource() + " action =>" + transition.getActions());
    }
}
