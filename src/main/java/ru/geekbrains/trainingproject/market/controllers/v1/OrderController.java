package ru.geekbrains.trainingproject.market.controllers.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.trainingproject.market.dtos.OrderItemDto;
import ru.geekbrains.trainingproject.market.dtos.UserDto;
import ru.geekbrains.trainingproject.market.services.CartService;
import ru.geekbrains.trainingproject.market.services.UserService;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final CartService cartService;
    private final UserService userService;

    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestBody UserDto userDto) {
        System.out.println(userDto);
        cartService.clearCart();
    }

    @PutMapping
    public void createOrderItems(@RequestBody OrderItemDto orderItemDto) {
        System.out.println(orderItemDto);
    }
}
