package ru.geekbrains.market.core.controllers;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.market.api.dtos.CommentsDto;
import ru.geekbrains.market.core.services.CommentsService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comments")
public class CommentsController {
    private final CommentsService commentsService;

    @GetMapping("/{id}")
    public List<CommentsDto>  getCommentsByProductId(@PathVariable Long id) {
        return commentsService.getCommentsByProductId(id);
    }

}
