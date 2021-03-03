package com.demon.controller;

import com.demon.exception.BasicException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: TODO
 * @author: liuhao
 * @create: 2020/9/16 16:20
 */
@RestController
@RequestMapping("/rb")
public class RbController {

    private final Logger logger = LoggerFactory.getLogger(RbController.class);
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("/send")
    public void sendMessage(){
        //路由模式的交换机  发送的消息会发送到，交换机通过routing绑定的队列上
        rabbitTemplate.convertAndSend("lh_exc","mns","发送信息1");
        logger.info("1");
    }

    @RequestMapping("/send2")
    public void sendMessage2(){
        //
        rabbitTemplate.convertAndSend("test","发送信息2");//此时的routingKey作为queue名//        rabbitTemplate.convertAndSend("logs", "lo", "我是fanout模式！");
        //topics模式
        rabbitTemplate.convertAndSend("topics", "user.save", "user.save路由消息！");
        //fanout模式，不需要routingkey,队列绑定交换机就可以发送了
        rabbitTemplate.convertAndSend("lh_exc","","我是fanout模式");


        logger.info("1");
    }

    /**
     * 测试异常
     */
    @RequestMapping("/exc")
    public void testExc(){
        if (2>1){
            throw new BasicException();
        }
        logger.info("1");
    }

}
