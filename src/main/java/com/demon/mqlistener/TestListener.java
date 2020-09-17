package com.demon.mqlistener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @description: TODO
 * @author: liuhao
 * @create: 2020/9/16 16:59
 */
//@Component
public class TestListener {
    private Logger logger = LoggerFactory.getLogger(TestListener.class);

    @RabbitListener(queues = "test")
    public void listener(String msg){
        logger.info("监听到队列test中的消息："+msg);
    }

    @RabbitListener(queues = "lh_queue")
    public void listener2(String msg){
        logger.info("监听到队列lh_queue中的消息："+msg);
    }

}
