package ru.geekbrains.market.api.dtos;

public class CommentsDto {
    private String username;
    private String content;
    private Long productId;

    public CommentsDto(String username, String content, Long productId) {
        this.username = username;
        this.content = content;
        this.productId = productId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
