package ru.geekbrains.market.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.market.api.dtos.cart.CartDto;
import ru.geekbrains.market.api.dtos.cart.CartItemsDto;
import ru.geekbrains.market.api.dtos.modeldtos.OrderDto;
import ru.geekbrains.market.api.dtos.modeldtos.OrderItemsDto;
import ru.geekbrains.market.api.exeptions.ResourceNotFoundException;
import ru.geekbrains.market.core.integration.CartServiceIntegration;
import ru.geekbrains.market.core.model.Order;
import ru.geekbrains.market.core.model.OrderItems;
import ru.geekbrains.market.core.repositories.OrderRepository;
import ru.geekbrains.market.core.utils.UserNameFromHttpRequestUtil;
import ru.geekbrains.market.core.utils.converters.ConverterDtoToModel;
import ru.geekbrains.market.core.utils.converters.ConverterModelToDto;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    public final OrderRepository orderRepository;
    public final OrderStatusService orderStatusService;
    public final UserService userService;
    public final ConverterDtoToModel converterDtoToModel;
    private final CartServiceIntegration cartServiceIntegration;
    private final UserNameFromHttpRequestUtil userNameFromHttpRequestUtil;
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    public void createAndSaveOrder(OrderDto orderDto) {
        Order order = createOrderFromOrderDto(orderDto);
        saveOrder(order);
    }


    @Transactional
    public Order createOrderFromOrderDto(OrderDto orderDto) {
        Order order = converterDtoToModel.orderFromOrderDto(orderDto);
        String currentUserName = userNameFromHttpRequestUtil.getUserCurrentName();
        CartDto cart = cartServiceIntegration.getUserCartDto();
        List<OrderItems> itemsList = new LinkedList<>();

        for (OrderItemsDto item : cart.getItems()) {
          OrderItems orderItems = new OrderItems();
           itemsList.add(cartItemDtoToOrderItems(item, orderItems, order));
        }
        order.setOrdersItems(itemsList);
        // Начальный статус заказа сформирован
        order.setOrderStatus(orderStatusService.getOrderStatusById(1l));
        return order;
    }

    private OrderItems cartItemDtoToOrderItems(OrderItemsDto item, OrderItems orderItems, Order order) {
        orderItems.setOrder(order);
        orderItems.setProductId(item.getProductId());
        orderItems.setProductTitle(item.getProductTitle());
        orderItems.setQuantity(item.getQuantity());
        orderItems.setPricePerProduct(item.getPricePerProduct());
        orderItems.setPrice(item.getPrice());
        return orderItems;
    }

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public List<Order> findAllByUsername(String userName) {
        return orderRepository.findAllByUsername(userName);
    }
}
