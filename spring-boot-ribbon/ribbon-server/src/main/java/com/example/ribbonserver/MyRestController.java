package com.example.ribbonserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestController {

    @Autowired
    Environment environment;

    @GetMapping("/")
    public String health() {
        String serverPort = environment.getProperty("local.server.port");

        if ("9090".equals(serverPort)) {
            return "I am Ok, I am Server 1";
        } else if ("9091".equals(serverPort)) {
            return "I am Server 2";
        }

        return "I am Ok";
    }

    @GetMapping("/backend")
    public String backend() {
        String serverPort = environment.getProperty("local.server.port");

        if ("9090".equals(serverPort)) {
            return "I am Ok, I am Server 1";
        } else if ("9091".equals(serverPort)) {
            return "I am Server 2";
        }

        return "Hello from Backend!!! Host : localhost :: Port : " + serverPort;
    }
}