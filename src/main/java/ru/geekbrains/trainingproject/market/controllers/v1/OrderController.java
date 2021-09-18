package ru.geekbrains.trainingproject.market.controllers.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.trainingproject.market.dtos.OrderDto;
import ru.geekbrains.trainingproject.market.dtos.ProductDto;
import ru.geekbrains.trainingproject.market.dtos.security.AuthResponse;
import ru.geekbrains.trainingproject.market.exceptions.DataValidationException;
import ru.geekbrains.trainingproject.market.exceptions.MarketError;
import ru.geekbrains.trainingproject.market.exceptions.ResourceNotFoundException;
import ru.geekbrains.trainingproject.market.model.Order;
import ru.geekbrains.trainingproject.market.services.CartService;
import ru.geekbrains.trainingproject.market.services.OrderService;
import ru.geekbrains.trainingproject.market.services.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final CartService cartService;
    private final OrderService orderService;



    @GetMapping("")
    public List<Order> findById() {
        return orderService.getAll();
    }

    @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createOrder(@RequestBody OrderDto orderDto) {
//        if (orderDto.getDetailsUser() == null) {
//            return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.NO_CONTENT);
//            }
        orderService.createAndSaveOrder(orderDto);
        cartService.clearCart();
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
