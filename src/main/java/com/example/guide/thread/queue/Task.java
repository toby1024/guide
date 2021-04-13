package com.example.guide.thread.queue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: zhangbin
 * @Date: 2021/3/15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    private String message;

    public boolean job() {
        System.out.println("this is task job:" + message);
        return Boolean.TRUE;
    }
}
