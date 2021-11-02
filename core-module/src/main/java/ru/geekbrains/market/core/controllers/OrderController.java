package ru.geekbrains.market.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.market.api.dtos.OrderDetailsDto;
import ru.geekbrains.market.api.dtos.OrderDto;
import ru.geekbrains.market.api.dtos.StringResponse;
import ru.geekbrains.market.core.model.Order;
import ru.geekbrains.market.core.services.OrderService;
import ru.geekbrains.market.core.services.OrderStatusService;
import ru.geekbrains.market.core.utils.Converter;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderStatusService orderStatusService;
    private final Converter converter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StringResponse createOrder(@RequestBody OrderDetailsDto orderDetailsDto, @RequestHeader String username) {
        return new StringResponse(orderService.createOrder(username, orderDetailsDto).getId().toString());
    }


    @GetMapping("/status/{id}")
    public String getStatus(@PathVariable Long id) {
        return orderStatusService.findById(id).getTitle();
    }

    @GetMapping("")
    public List<OrderDto> getOrdersForCurrentUser(@RequestHeader String username, @RequestParam(name = "status", defaultValue = "0", required = false) Long status) {
        System.out.println("status=" + status);
        if (status.equals(0l)) {
            return orderService.findAllByUsername(username).stream().map(o -> converter.orderToDto(o)).collect(Collectors.toList());
        }
        return orderService.findAllByUsernameAndStatus(username, status).stream().map(o -> converter.orderToDto(o)).collect(Collectors.toList());
    }

    @GetMapping("/exists/{productId}")
    public Boolean isProductExist(@RequestHeader(required = false, defaultValue = "noname") String username, @PathVariable Long productId) {
        if (username.equals("noname")) {
            return false;
        }
        return orderService.isProductExist(username, productId).booleanValue();
    }


    @GetMapping("/{id}")
    public OrderDto getOrderForCurrentUser(@RequestHeader String username, @PathVariable Long id) {
        return orderService.findDtoByIdAndUsername(id, username).get();
    }
}
