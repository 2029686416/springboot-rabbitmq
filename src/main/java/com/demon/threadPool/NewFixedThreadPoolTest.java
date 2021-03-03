package com.demon.threadPool;

import java.util.concurrent.Executors;

/**
 * @description: TODO
 * @author: liuhao
 * @create: 2021/3/3 13:34
 */
//因为线程池大小为3，每个任务输出打印结果后sleep 2秒，所以每两秒打印3个结果。

public class NewFixedThreadPoolTest {

    //因为线程池大小为3，每个任务输出打印结果后sleep 2秒，所以每两秒打印3个结果。
    public static void main(String[] args) {
        // 创建一个可重用固定个数的线程池  线程池大小：3
        java.util.concurrent.ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            fixedThreadPool.execute(new Runnable() {
                public void run() {
                    try {
                        // 打印正在执行的缓存线程信息
                        System.out.println(Thread.currentThread().getName()
                                + "正在被执行");
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

}