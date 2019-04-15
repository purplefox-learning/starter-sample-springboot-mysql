package com.ycm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        //a tomcat server will be listening on port 8080
        //if no controller or mvc is configured in the project,
        //visiting the default url will lead to an error page, which is expected

        SpringApplication.run(Application.class,args);
    }
}
