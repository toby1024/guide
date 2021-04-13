package com.example.guide.trans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zhangbin
 * @Date: 2020/12/30
 */
@RestController
public class ApiTest {

    @Autowired
    private TransA transA;

    @GetMapping("/trans")
    public String test() {
        return transA.a();
    }
}
