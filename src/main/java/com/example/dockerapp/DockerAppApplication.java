package com.example.dockerapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@Slf4j
public class DockerAppApplication {

    @RequestMapping("/")
    public String home() {
        System.out.println("sysout log. 로그 수집 테스트") ;

        log.info("sl4j 로그: {}", "hello kj, hello docker world");
        System.out.println(" ");
        System.out.println("########################") ;
        System.out.println("sysout log. 로그 수집 테스트 222") ;
        System.out.println("########################") ;



        return "Hello Docker World, Hello kj";
    }

    @RequestMapping("/index")
    public String home2() {
        return "Hello World... and Rolling Update";
    }

    public static void main(String[] args) {
        SpringApplication.run(DockerAppApplication.class, args);
    }

}
