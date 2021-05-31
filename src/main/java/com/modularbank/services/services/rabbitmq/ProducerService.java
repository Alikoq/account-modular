package com.modularbank.services.services.rabbitmq;

import com.modularbank.services.config.RabbitMqConfig;
import com.modularbank.services.dto.request.AccountRequest;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.RabbitExceptionTranslator;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {
    private static RabbitMqConfig rabbitMqConfig;
    private static   RabbitTemplate template;
    public ProducerService(RabbitTemplate template,RabbitMqConfig rabbitMqConfig){
        ProducerService.rabbitMqConfig =rabbitMqConfig;
        ProducerService.template = template;
    }

    public  <T> void publishMessageToQueue(T object){
         System.err.println("Start");
         System.err.println(rabbitMqConfig.getExchange());
         template.convertAndSend(rabbitMqConfig.getExchange(),rabbitMqConfig.getRoutingKey(),object);
         System.err.println("End");
    }

//    @RabbitListener(queues = "modular_queue" )
//    public void consumer(AccountRequest accountRequest){
//        System.err.println(accountRequest.getCountry()+":::::::::::"+accountRequest.getCustomerId());
//    }
}
