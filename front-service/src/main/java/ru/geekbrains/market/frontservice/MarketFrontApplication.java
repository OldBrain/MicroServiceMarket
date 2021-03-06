package ru.geekbrains.market.frontservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MarketFrontApplication {
    public static void main(String[] args) {
        SpringApplication.run(MarketFrontApplication.class, args);
        System.out.println("Front application start OK!");
        System.out.println("http://localhost:3000/market/index.html#!/");
    }

}
