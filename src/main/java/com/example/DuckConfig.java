package com.example;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DuckConfig {

    @Bean
    public QuackBehavior quack(){
        return new Quack();
    }
    @Bean
    public Duck redheadDuck(){
        Duck redheadDuck = new RedheadDuck();
        redheadDuck.setQuackBehavior(quack());
        return redheadDuck;
    }
}
