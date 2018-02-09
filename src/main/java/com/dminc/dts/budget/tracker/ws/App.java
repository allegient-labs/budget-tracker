package com.dminc.dts.budget.tracker.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.dminc.dts.budget.tracker.controllers"} )
@EntityScan(basePackages = {"com.dminc.dts.budget.tracker.model"})
@EnableJpaRepositories(basePackages = {"com.dminc.dts.budget.tracker.db"})

public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }


}