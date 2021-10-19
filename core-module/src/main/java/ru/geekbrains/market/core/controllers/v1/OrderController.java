package ru.geekbrains.market.core.controllers.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.market.api.dtos.modeldtos.OrderDto;
import ru.geekbrains.market.core.services.OrderService;
import ru.geekbrains.market.core.services.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")

public class OrderController {
    private final OrderService orderService;
    private final UserService userService;


    @GetMapping("")
    public List<OrderDto> findByCurrentUser() {
        return userService.getOrderByCurrentUser();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createOrder(@RequestBody OrderDto orderDto) {
        orderService.createAndSaveOrder(orderDto);

//        cartService.clearCart();
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
