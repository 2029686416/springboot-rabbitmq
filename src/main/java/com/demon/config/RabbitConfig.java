package com.demon.config;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 用于配置rabbitMQ
 */
//@Configuration
public class RabbitConfig {


    private CachingConnectionFactory connectionFactory(String addresses, String username, String password, String vhost){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(addresses);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        if(vhost!=null && !vhost.equals("")) {
            connectionFactory.setVirtualHost(vhost);        	
        }
        return connectionFactory;
    }
    
    private CachingConnectionFactory connectionFactory(String host, int port, String username, String password, String vhost){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        if(vhost!=null && !vhost.equals("")) {
            connectionFactory.setVirtualHost(vhost);        	
        }
        return connectionFactory;
    }

    @Bean(name = "znywConnectionFactory")
    @Primary
    public ConnectionFactory znywConnectionFactory(
            @Value("${spring.rabbitmq.znyw.host}") String host,
            @Value("${spring.rabbitmq.znyw.port}") String port,
            @Value("${spring.rabbitmq.znyw.addresses}") String addresses,
            @Value("${spring.rabbitmq.znyw.username}") String username,
            @Value("${spring.rabbitmq.znyw.password}") String password, @Value("${spring.rabbitmq.znyw.vhost}") String vhost
    ) {
    	if(addresses!=null && !addresses.equals("")) {
            return connectionFactory(addresses, username, password,vhost);
    	}else {
            return connectionFactory(host, Integer.valueOf(port), username, password,vhost);
    	}
    }

    @Bean(name = "znywRabbitTemplate")
    @Primary
    public RabbitTemplate znywRabbitTemplate(
            @Qualifier("znywConnectionFactory") ConnectionFactory connectionFactory
    ) {
        RabbitTemplate znywRabbitTemplate = new RabbitTemplate(connectionFactory);
        return znywRabbitTemplate;
    }

    @Bean(name = "znywContainerFactory")
    @Primary
    public SimpleRabbitListenerContainerFactory znywContainerFactory(
            SimpleRabbitListenerContainerFactoryConfigurer configurer,
            @Qualifier("znywConnectionFactory") ConnectionFactory connectionFactory
    ) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        return factory;
    }


}
