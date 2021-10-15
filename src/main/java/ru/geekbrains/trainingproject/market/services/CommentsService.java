package ru.geekbrains.trainingproject.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.geekbrains.trainingproject.market.config.redis.RedisConnectionConfig;
import ru.geekbrains.trainingproject.market.utils.Comments;
import ru.geekbrains.trainingproject.market.utils.UserDataFromHttpRequestUtil;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentsService {
    @Value("${redis_comments.port}")
    int port;
    @Value("${redis_comments.hostname}")
    String hostname;
    @Value("${redis_comments.password}")
    String password;

    Comments comments;

    private final UserDataFromHttpRequestUtil userDataFromHttpRequestUtil;
    private final RedisConnectionConfig redisConnectionConfig;

    @PostConstruct
    public void init() {
        this.comments = new Comments(redisConnectionConfig.redisTemplate(port, hostname, password), userDataFromHttpRequestUtil.getUserName());
    }

    private void addComment(String comment, Long productId) {
        comments.add(productId, comment);
    }

    private List<String[]> getCommentsFromProductId(Long productId) {
        return comments.getComments(productId);
    }

    private boolean isUsrCommentExists(Long productId) {
        return comments.isUsrCommentExists(productId);
    }

}
