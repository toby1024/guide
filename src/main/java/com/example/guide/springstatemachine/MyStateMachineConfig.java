package com.example.guide.springstatemachine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

/**
 * @Author: zhangbin
 * @Date: 2021/3/25
 */
@Configuration
@EnableStateMachineFactory(name = "stateMachineA")
public class MyStateMachineConfig extends MyStateMachineConfigAdapter<MyStateMachineConfig.MyStatus, MyStateMachineConfig.MyEvents> {
    @Autowired
    private StateMachineListener stateMachineListener;
    @Autowired
    private MyAction myAction;
    @Autowired
    private StopAction stopAction;

    @Override
    public void configure(StateMachineConfigurationConfigurer<MyStatus, MyEvents> config) throws Exception {
        config.withConfiguration().autoStartup(true).listener(stateMachineListener);
    }

    @Override
    public void configure(StateMachineStateConfigurer states) throws Exception {
        states.withStates().initial(MyStatus.START).states(EnumSet.allOf(MyStatus.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<MyStatus, MyEvents> transitions) throws Exception {
        transitions.withExternal().source(MyStatus.START).target(MyStatus.EXECUTE).event(MyEvents.EVENT_START).action(myAction)
                   .and()
                   .withExternal().source(MyStatus.EXECUTE).target(MyStatus.PENDING).event(MyEvents.EVENT_PAUSE).action(stopAction)
                   .and()
                   .withExternal().source(MyStatus.PENDING).target(MyStatus.START).event(MyEvents.EVENT_START).action(myAction)
                   .and()
                   .withExternal().source(MyStatus.START).target(MyStatus.STOP).event(MyEvents.EVENT_STOP).action(stopAction)
                   .and()
                   .withExternal().source(MyStatus.EXECUTE).target(MyStatus.STOP).event(MyEvents.EVENT_STOP).action(stopAction);
    }

    public enum MyStatus {
        START, EXECUTE, PENDING, STOP;
    }

    public enum MyEvents {
        EVENT_START,EVENT_PAUSE, EVENT_STOP;
    }
}
