package com.example.week10;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private final MessageProducer producer;

    public MessageController(MessageProducer producer) {
        this.producer = producer;
    }

    @GetMapping("/sendName")
    public String sendName(@RequestParam String name) {
        producer.sendMessage("Name: " + name);
        return "Name sent: " + name;
    }

    @GetMapping("/sendAge")
    public String sendAge(@RequestParam String age) {
        producer.sendMessage("Age: " + age);
        return "Age sent: " + age;
    }
}