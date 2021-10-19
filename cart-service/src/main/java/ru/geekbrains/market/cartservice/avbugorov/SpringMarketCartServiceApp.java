package ru.geekbrains.market.cartservice.avbugorov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringMarketCartServiceApp {


    public static void main(String[] args) {
        SpringApplication.run(SpringMarketCartServiceApp.class, args);
        System.out.println("Cart-Service-Application start OK!");
    }

}
