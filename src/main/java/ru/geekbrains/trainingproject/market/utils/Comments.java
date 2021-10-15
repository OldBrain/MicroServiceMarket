package ru.geekbrains.trainingproject.market.utils;

import lombok.Data;
import org.springframework.data.redis.core.RedisTemplate;
import ru.geekbrains.trainingproject.market.exceptions.MarketError;
import ru.geekbrains.trainingproject.market.exceptions.ResourceNotFoundException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Data
public class Comments {
    private RedisTemplate redisTemplate;
    private Long productId;
    private String userName;
    private String content;

    private HashMap<Long, List<String[] >> commentsMap = new HashMap<>();

    public Comments(RedisTemplate redisTemplate, String userName) {
        this.redisTemplate = redisTemplate;
        this.userName = userName;
    }

    public void add(Long productId,String content) {
        if (userName.equals(null) || productId.equals(null)||content.equals(null)) {
            throw new ResourceNotFoundException("Пустое имя пользователя или Id товара");
        }

        //если уже были записи
        if (redisTemplate.hasKey(productId)) {
            List<String[]> tmpContentList= (List<String[]>) redisTemplate.opsForValue().get(productId);

            if (tmpContentList.stream().anyMatch(s -> s[0] == userName)) {
                //TODO можно добавить возможность редактирования
                System.out.println("Комментарий этого пользователя уже был");
                return;
            } else {
                tmpContentList.stream().filter(s -> s[0] == userName).collect(Collectors.toList()).add(new String[]{userName, content});
                redisTemplate.opsForValue().set(productId, tmpContentList);
                return;
            }

        }
        //если новый продукт
        if (!redisTemplate.hasKey(productId)) {
            String[] newContent = new String[]{userName, content};
            redisTemplate.opsForValue().set(productId, new LinkedList<>(Collections.singleton(newContent)));
            return;
        }

    }

    public List<String[]> getComments(Long productId) {
        return (List<String[]>) redisTemplate.opsForValue().get(productId);
    }

    public Boolean isUsrCommentExists(Long productId) {
        return getComments(productId).stream().anyMatch(s -> s[0] == userName);
    }


}
