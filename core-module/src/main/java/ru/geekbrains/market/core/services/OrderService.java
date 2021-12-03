package ru.geekbrains.market.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.market.api.dtos.*;
import ru.geekbrains.market.api.exceptions.ResourceNotFoundException;
import ru.geekbrains.market.core.integration.CartServiceIntegration;
import ru.geekbrains.market.core.model.Order;
import ru.geekbrains.market.core.model.OrderItem;
import ru.geekbrains.market.core.model.Product;
import ru.geekbrains.market.core.repositories.OrderRepository;
import ru.geekbrains.market.core.utils.Converter;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartServiceIntegration cartServiceIntegration;
    private final ProductService productService;
    private final OrderStatusService orderStatusService;
    private final Converter converter;

    @Transactional
    public Order createOrder(String username, OrderDetailsDto orderDetailsDto) {
        CartDto cart = cartServiceIntegration.getUserCartDto(username);
        Order order = new Order();
        order.setUsername(username);
        order.setPrice(cart.getTotalPrice());
        order.setAddress(orderDetailsDto.getAddress());
        order.setPhone(orderDetailsDto.getPhone());
        order.setPatronymic(orderDetailsDto.getPatronymic());
        order.setFirst_name(orderDetailsDto.getFirst_name());
        order.setLast_name(orderDetailsDto.getLast_name());
        order.setOrderStatus(orderStatusService.findById(1l));
        List<OrderItem> items = new ArrayList<>();
        for (OrderItemDto i : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setPrice(i.getPrice());
            orderItem.setPricePerProduct(i.getPricePerProduct());
            orderItem.setQuantity(i.getQuantity());
            orderItem.setProduct(productService.findById(i.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Не удалось найти продукт при оформлении заказа. ID продукта: " + i.getProductId())));
            items.add(orderItem);
        }
        order.setItems(items);
        orderRepository.save(order);
        cartServiceIntegration.clearUserCart(username);
        return order;
    }

    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Transactional
    public Optional<OrderDto> findDtoByIdAndUsername(Long id, String username) {
        return orderRepository.findOneByIdAndUsername(id, username).map(o -> converter.orderToDto(o));
    }


    public List<Order> findAllByUsername(String username) {
        return orderRepository.findAllByUsername(username).stream().collect(Collectors.toList());
    }

    public List<Order> findAllByUsernameAndStatus(String username, Long status) {
        return orderRepository.findAllByUsername(username).stream().
                filter(order -> order.getOrderStatus().getId().
                        equals(status)).collect(Collectors.toList());
    }

    //TODO заменить статус на оплачен
    public Boolean isProductExist(String username, Long productId) {
        List<Order> orderListStatus0 = orderRepository.findAllByUsername(username).stream().
                filter(order -> order.getOrderStatus().getId().
                        equals(1l)).collect(Collectors.toList());

        for (Order o : orderListStatus0) {
            List<OrderItem> items = o.getItems();
            for (OrderItem i : items) {
                if (i.getProduct().getId().equals(productId)) {
                    return true;
                }
            }
        }
        return false;
    }

}
