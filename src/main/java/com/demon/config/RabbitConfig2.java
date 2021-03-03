package com.demon.config;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.io.IOException;

/**
 * @description: TODO
 * @author: liuhao
 * @create: 2020/9/16 16:13
 */
@Configurable
public class RabbitConfig2 {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host,port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost("/");
        //开启发送确认机制
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    //必须是prototype类型
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        return template;
    }

    //AMQP事务机制实现
    public Connection connection(@Qualifier ConnectionFactory connectionFactory){
        Channel channel = null;
        try {
            Connection connection = connectionFactory.createConnection();
            channel = connection.createChannel(true);
            channel.exchangeDeclare("exchangeName", "direct", true, false, null);
            //创建exchange
            channel.exchangeDeclare("exchangeName", "direct", true, false, null);
            //创建队列
            channel.queueDeclare("queueName", true, false, false, null);
            //绑定exchange和queue
            channel.queueBind("queueName", "exchangeName", "bindingKey");
            //发送持久化消息
            for(int i = 0;i < 5;i++)
            {
                //第一个参数是exchangeName(默认情况下代理服务器端是存在一个""名字的exchange的,
                //因此如果不创建exchange的话我们可以直接将该参数设置成"",如果创建了exchange的话
                //我们需要将该参数设置成创建的exchange的名字),第二个参数是路由键
                //开启事务
                channel.txSelect();
                channel.basicPublish("exchangeName", "routingKey", true, MessageProperties.PERSISTENT_BASIC, ("第"+(i+1)+"条消息").getBytes());
                if(i == 1)
                {
                    int result = 1/0;
                }
                //提交事务
                channel.txCommit();
            }
        }catch (Exception e){
            try {
                //回滚操作
                channel.txRollback();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return (Connection) channel;
    }

}
