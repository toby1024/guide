package com.example.guide.springstatemachine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.service.StateMachineService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zhangbin
 * @Date: 2021/3/25
 */
@RestController
@RequestMapping("state")
public class WebController {


    @Autowired
    private MyStateMachineFactory myStateMachineFactory;

    @GetMapping("start")
    public String start(){
        StateMachineService stateMachineService = myStateMachineFactory.get("stateMachine");

        stateMachineService.acquireStateMachine("start").sendEvent(MyStateMachineConfig.MyEvents.EVENT_START);
        return "success";
    }

    @GetMapping("stop")
    public String stop(){

//        stateMachineA.getStateMachine().sendEvent(MyStateMachineConfig.MyEvents.EVENT_STOP);
        return "success";
    }

    @GetMapping("pause")
    public String pause(){

//        stateMachineA.getStateMachine().sendEvent(MyStateMachineConfig.MyEvents.EVENT_PAUSE);
        return "success";
    }

    @GetMapping("restart")
    public String restart(){

//        stateMachineA.getStateMachine().sendEvent(MyStateMachineConfig.MyEvents.EVENT_START);
        return "success";
    }
}
