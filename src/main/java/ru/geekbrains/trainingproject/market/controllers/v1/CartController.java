package ru.geekbrains.trainingproject.market.controllers.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.trainingproject.market.dtos.CartProductDto;
import ru.geekbrains.trainingproject.market.services.CartService;

import java.util.List;

@RestController
@RequiredArgsConstructor

@RequestMapping("/api/v1/cart")
public class CartController {
    private final CartService cartService;

    @GetMapping()
    public List<CartProductDto> findAll() {
        return cartService.getAll();
    }

    @PostMapping("/{id}")
    public Integer save(@PathVariable Long id) {
        cartService.addProduct(id);
        return cartService.getAll().size();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        cartService.deleteById(id);
    }


    @PutMapping("")
    public void productDtoEdit(@RequestBody CartProductDto cartProductDto) {
        cartService.updateProduct(cartProductDto);
    }
}
