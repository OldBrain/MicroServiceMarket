package ru.geekbrains.market.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.market.api.dtos.CommentsDto;
import ru.geekbrains.market.core.model.Comments;
import ru.geekbrains.market.core.repositories.CommentsRepository;
import ru.geekbrains.market.core.utils.Converter;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentsService {
    private final CommentsRepository commentsRepository;
    private final Converter converter;

    public List<CommentsDto> getCommentsByProductId(Long id) {
        return commentsRepository.findAllByProductIdEquals(id).
                stream().map(c -> converter.commentsToDto(c)).
                collect(Collectors.toList());
    }

    public List<CommentsDto> getCurrentUserComment(String username, Long productId) {
        return commentsRepository.findQueryByUsernameAndProductId(username, productId).
                stream().map(c -> converter.commentsToDto(c))
                .collect(Collectors.toList());
    }

    public void saveComments(Comments newComments) {
        if (!commentsRepository.findQueryByUsernameAndProductId(newComments.getUsername(), newComments.getProductId()).isEmpty()) {
            Comments comments = commentsRepository.findQueryByUsernameAndProductId(newComments.getUsername(), newComments.getProductId()).get();
            comments.setContent(newComments.getContent());
            commentsRepository.save(comments);
        } else {
            Comments comment = new Comments();
            comment.setUsername(newComments.getUsername());
            comment.setContent(newComments.getContent());
            comment.setProductId(newComments.getProductId());
            commentsRepository.save(comment);
        }
    }
}
