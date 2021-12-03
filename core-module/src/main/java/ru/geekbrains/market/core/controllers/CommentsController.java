package ru.geekbrains.market.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.market.api.dtos.CommentsDto;
import ru.geekbrains.market.api.exceptions.ResourceNotFoundException;
import ru.geekbrains.market.core.exceptions.DataValidationException;
import ru.geekbrains.market.core.model.Comments;
import ru.geekbrains.market.core.services.CommentsService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comments")
public class CommentsController {
    private final CommentsService commentsService;

    @GetMapping("/{id}")
    public List<CommentsDto> getCommentsByProductId(@PathVariable Long id) {
        return commentsService.getCommentsByProductId(id);
    }

    @GetMapping("/current/{productId}")
    public CommentsDto getCurrentUserComment(@RequestHeader(required = false, defaultValue = "noname") String username, @PathVariable Long productId ) {
        if (username.equals("noname")) {
            throw new ResourceNotFoundException("Не авторизованный пользователь");
        }
        return commentsService.getCurrentUserComment(username, productId).get(0);
    }

    @PostMapping()
    public void saveComment(@RequestBody @Validated Comments comment, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new DataValidationException(bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
        }
        if (comment.getContent() == "") {
            return;
        }

        commentsService.saveComments(comment);
    }
}
