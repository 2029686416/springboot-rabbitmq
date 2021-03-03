package com.demon.threadPool;

import java.util.concurrent.Executors;

/**
 * @description: TODO
 * @author: liuhao
 * @create: 2021/3/3 13:41
 */
public class NewSingleThreadExecutorTest {

    public static void main(String[] args) {
        //创建一个单线程化的线程池
        java.util.concurrent.ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            singleThreadExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        //结果依次输出，相当于顺序执行各个任务
                        System.out.println(Thread.currentThread().getName()+"正在被执行,打印的值是:"+index);
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }


    }
}
