package ru.geekbrains.trainingproject.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.trainingproject.market.dtos.CartItemDto;
import ru.geekbrains.trainingproject.market.dtos.OrderDto;
import ru.geekbrains.trainingproject.market.model.Order;
import ru.geekbrains.trainingproject.market.model.OrderItems;
import ru.geekbrains.trainingproject.market.repositories.OrderRepository;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    public final OrderRepository orderRepository;
    public final OrderItemsService orderItemsService;
    public final OrderStatusService orderStatusService;
    public final UserService userService;

    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    public void createAndSaveOrder(OrderDto orderDto) {
        Order order = createOrderFromOrderDto(orderDto);
        saveOrder(order);

    }

    private Order createOrderFromOrderDto(OrderDto orderDto) {
        Order order = new Order();
        order.setFirst_name(orderDto.getDetailsUser().getFirstName());
        order.setLast_name(orderDto.getDetailsUser().getLastName());
        order.setPatronymic(orderDto.getDetailsUser().getPatronymic());
        order.setSum(orderDto.getTotalPrice());
        order.setPhone(orderDto.getDetailsUser().getPhone());
        order.setAddress(orderDto.getDetailsUser().getAddress());
        order.setUser(userService.getUserById(orderDto.getUserId()).get());

//        OrderItems orderItems = new OrderItems();
        List<OrderItems> itemsList = new LinkedList<>();

        for (CartItemDto item:  orderDto.getItems()) {
            OrderItems orderItems = new OrderItems();
            itemsList.add(cartItemDtoToOrderItems(item, orderItems,order));

        }
        order.setOrdersItems(itemsList);
        System.out.println("********"+order.getOrdersItems());


       // Начальный статус заказа сформирован
        order.setOrderStatus(orderStatusService.getOrderStatusById(1l));
        return order;
    }

    private OrderItems cartItemDtoToOrderItems(CartItemDto item,OrderItems orderItems,Order order) {
//        OrderItems orderItems = new OrderItems();
//        orderItems.setOrder(order);
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
}
