package ru.geekbrains.market.cartservice.avbugorov.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.redis.core.RedisTemplate;
import ru.geekbrains.market.api.dtos.modeldtos.OrderItemsDto;
import ru.geekbrains.market.api.dtos.modeldtos.ProductDto;
import ru.geekbrains.market.api.exeptions.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
public class Cart {
    private List<OrderItemsDto> items;
    private int totalPrice;
    private int pricePerProduct;

    @JsonIgnore
    private UserDataFromHttpRequestCartUtil userDataFromHttpRequestCartUtil;
    @JsonIgnore

    
    private RedisTemplate redisTemplate;

    public Cart(UserDataFromHttpRequestCartUtil userDataFromHttpRequestCartUtil, RedisTemplate redisTemplate) {
        this.userDataFromHttpRequestCartUtil = userDataFromHttpRequestCartUtil;
        this.redisTemplate = redisTemplate;
    }

    public void add(ProductDto p) {
        setCurrentItemList();
        for (OrderItemsDto i : items) {
            if (i.getProductId().equals(p.getId())) {
                i.changeQuantity(1);
                recalculate();
                addToRedis();
                return;
            }
        }
        items.add(new OrderItemsDto(p.getId(), p.getTitle(), 1, p.getPrice(), p.getPrice()));
    }


    public void decrement(Long productId) {
        setCurrentItemList();
        Iterator<OrderItemsDto> iter = items.iterator();
        while (iter.hasNext()) {
            OrderItemsDto i = iter.next();
            if (i.getProductId().equals(productId)) {
                i.changeQuantity(-1);
                if (i.getQuantity() <= 0) {
                    iter.remove();
                }
                recalculate();
                addToRedis();
                break;
            }
        }
        addToRedis();
    }

    public void remove(Long productId) {
        setCurrentItemList();
        items.removeIf(i -> i.getProductId().equals(productId));
        recalculate();
        items.forEach(System.out::println);
        addToRedis();
    }

    public void clear() {
        setCurrentItemList();
        items.clear();
        totalPrice = 0;
        redisTemplate.delete(getCurrentKeyForCart());
    }

    private void recalculate() {
//        setCurrentItemList();
        totalPrice = 0;
        for (OrderItemsDto i : items) {
            totalPrice += i.getPrice();
        }
    }

    private void addToRedis() {
        redisTemplate.opsForValue().set(getCurrentKeyForCart(), items);
    }

    public List<OrderItemsDto> setCurrentItemList() {
        if (redisTemplate.hasKey(getCurrentKeyForCart())) {
            items = (List<OrderItemsDto>) redisTemplate.opsForValue().get(getCurrentKeyForCart());
        } else {
            items = new ArrayList<>();
        }
        return items;
    }

    public List<OrderItemsDto> getItemListByUserName(String userName) {
        if (userName != null) {
            return (List<OrderItemsDto>) redisTemplate.opsForValue().get(userName);
        } else {
            throw new ResourceNotFoundException("Корзина для " + userName + " не найдена");
        }
    }

    private String getCurrentTmpId() {
        return userDataFromHttpRequestCartUtil.getUserTmpId();
    }

    private String getCurrentUserName() {
        return userDataFromHttpRequestCartUtil.getUserCurrentName();
    }

    private String getCurrentKeyForCart() {
        if (getCurrentUserName() != null) {
            reorganizeCurtMap();
            return getCurrentUserName();
        } else if (getCurrentTmpId() != null) {
            return getCurrentTmpId();
        }
        throw new ResourceNotFoundException("Не удалось получить данные о клиенте из httpServlet");
    }

    private void reorganizeCurtMap() {
        if (redisTemplate.hasKey(getCurrentUserName()) && redisTemplate.hasKey(getCurrentTmpId())) {
            List<OrderItemsDto> tmpListByNameKey = (List<OrderItemsDto>) redisTemplate.opsForValue().get(getCurrentUserName());
            List<OrderItemsDto> tmpListByTmpId = (List<OrderItemsDto>) redisTemplate.opsForValue().get(getCurrentTmpId());
            List<OrderItemsDto> resultItemList = Stream.concat(
                    tmpListByNameKey.parallelStream(),
                    tmpListByTmpId.parallelStream()).collect(Collectors.toList());
            redisTemplate.delete(getCurrentTmpId());
            List<OrderItemsDto> sortedList = resultItemList.stream().sorted(Comparator.comparing(OrderItemsDto::getProductId)).collect(Collectors.toList());

            items = mergeItemList(sortedList);

            redisTemplate.opsForValue().set(getCurrentUserName(), items);

        }
        if (!redisTemplate.hasKey(getCurrentUserName()) && redisTemplate.hasKey(getCurrentTmpId())) {
            List<OrderItemsDto> tmpListByTmpId = (List<OrderItemsDto>) redisTemplate.opsForValue().get(getCurrentTmpId());
            redisTemplate.delete(getCurrentTmpId());
            redisTemplate.opsForValue().set(getCurrentUserName(), tmpListByTmpId);
        }
    }

    private List<OrderItemsDto> mergeItemList(List<OrderItemsDto> itemList) {
        for (int i = 0; i < itemList.size() - 1; i++) {
            if (itemList.get(i).getProductId().
                    equals(itemList.get(i + 1).getProductId())) {

                itemList.get(i).setQuantity(itemList.get(i).getQuantity()
                        + itemList.get(i + 1).getQuantity());
                itemList.remove(i + 1);
                i = 0;
            }
        }
        return itemList;
    }

    public int getTotalPrice() {
        setCurrentItemList();
        recalculate();
        return totalPrice;
    }
}
