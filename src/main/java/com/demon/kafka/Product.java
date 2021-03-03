package com.demon.kafka;

import com.alibaba.fastjson.JSONObject;
import com.demon.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description: TODO
 * @author: liuhao
 * @create: 2021/1/25 15:44
 */
@Component
@RequestMapping("/kfaProduct")
public class Product {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    @RequestMapping("/send")
    @ResponseBody
    public void send(String name){
        User u=new User();
        u.setName(name);
        u.setAge(11);
        kafkaTemplate.send("user", JSONObject.toJSONString(u));
    }
}
