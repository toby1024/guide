package com.example.guide.thread.queue;

import java.util.concurrent.*;
import java.util.function.Function;

/**
 * @Author: zhangbin
 * @Date: 2021/3/15
 */
public class DelayQueueDemo {

    public static void main(String[] args) {
        DelayQueueDemo demo = new DelayQueueDemo();
        //初始化线程池
        BlockingQueue<Runnable> arrayBlockingQueue = new ArrayBlockingQueue<>(10);
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor
                (5, 10, 10, TimeUnit.MILLISECONDS,
                        arrayBlockingQueue, Executors.defaultThreadFactory(),
                        new ThreadPoolExecutor.AbortPolicy());

        DelayQueue<DelayedTask> delayTaskQueue = new DelayQueue<>();


        Task t = new Task("test");
        delayTaskQueue.put(new DelayedTask<>(t, 1, function -> demo.print(t.getMessage()), System.currentTimeMillis() + 3000));

        Task t2 = new Task("test2");
        delayTaskQueue.put(new DelayedTask<>(t2, 1, function -> demo.print(t2.getMessage()), System.currentTimeMillis() + 5000));

        Task t3 = new Task("test3");
        delayTaskQueue.put(new DelayedTask<>(t3, 1, a -> demo.print(t3.getMessage()), System.currentTimeMillis() + 1000));


        while (true) {
            if (delayTaskQueue.size() == 0) {
                break;
            }
            try {
                //从延迟队列中取值,如果没有对象过期则取到null
                DelayedTask delayedTask = delayTaskQueue.poll();
                if (delayedTask != null) {
                    threadPool.execute(delayedTask);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        threadPool.shutdown();
    }

    private String print(String message) {
        System.out.println(System.currentTimeMillis() + ":" + message);
        return message;
    }

    private static class DelayedTask<T> implements Delayed, Runnable {
        /**
         * 任务参数
         */
        private T taskParam;

        /**
         * 任务类型
         */
        private Integer type;

        /**
         * 任务函数
         */
        private Function<T, String> function;

        /**
         * 任务执行时刻
         */
        private Long runAt;

        public T getTaskParam() {
            return taskParam;
        }

        public Integer getType() {
            return type;
        }

        public Function<T, String> getFunction() {
            return function;
        }

        public Long getRunAt() {
            return runAt;
        }

        DelayedTask(T taskParam, Integer type, Function<T, String> function, Long runAt) {
            this.taskParam = taskParam;
            this.type = type;
            this.function = function;
            this.runAt = runAt;
        }

        @Override
        public void run() {
            if (taskParam != null) {
                function.apply(taskParam);
            }
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(this.runAt - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            DelayedTask object = (DelayedTask) o;
            return this.runAt.compareTo(object.getRunAt());
        }
    }
}