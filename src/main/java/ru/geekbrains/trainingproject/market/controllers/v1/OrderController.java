package ru.geekbrains.trainingproject.market.controllers.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.trainingproject.market.dtos.OrderDetailsDto;
import ru.geekbrains.trainingproject.market.services.CartService;
import ru.geekbrains.trainingproject.market.services.UserService;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final CartService cartService;
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestBody OrderDetailsDto orderDetailsDto) {

        cartService.clearCart();
    }

//    @GetMapping
//    public AdditionalUserInfo getAdditionalUserInfo(@RequestBody String userName) {
//        User user = userService.getUserUserName(userName).orElseThrow(()->new ResourceNotFoundException("Не удалось найти пользователя с именем "+userName));
//        return user.getAdditionalUserInfo();
//    }


}
