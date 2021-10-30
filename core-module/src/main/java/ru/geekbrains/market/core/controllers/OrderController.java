package ru.geekbrains.market.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.market.api.dtos.OrderDetailsDto;
import ru.geekbrains.market.api.dtos.OrderDto;
import ru.geekbrains.market.api.dtos.StringResponse;
import ru.geekbrains.market.core.services.OrderService;
import ru.geekbrains.market.core.utils.Converter;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final Converter converter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StringResponse createOrder(@RequestBody OrderDetailsDto orderDetailsDto, @RequestHeader String username) {
        System.out.println(new StringResponse(orderService.createOrder(username, orderDetailsDto).getId().toString()));
        return new StringResponse(orderService.createOrder(username, orderDetailsDto).getId().toString());
    }

    @GetMapping("")
    public List<OrderDto> getOrdersForCurrentUser(@RequestHeader String username, @RequestParam( name = "status",defaultValue = "0",required = false) Long status) {
        System.out.println("status=" + status);
        if (status.equals(0l)) {
            return orderService.findAllByUsername(username).stream().map(o -> converter.orderToDto(o)).collect(Collectors.toList());
        }
        return orderService.findAllByUsernameAndStatus(username, status).stream().map(o -> converter.orderToDto(o)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public OrderDto getOrderForCurrentUser(@RequestHeader String username, @PathVariable Long id) {
        return orderService.findDtoByIdAndUsername(id, username).get();
    }
}
