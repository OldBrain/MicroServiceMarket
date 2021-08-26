package ru.geekbrains.trainingproject.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.trainingproject.market.dtos.CartProductsDto;
import ru.geekbrains.trainingproject.market.dtos.ProductDto;
import ru.geekbrains.trainingproject.market.exceptions.ResourceNotFoundException;
import ru.geekbrains.trainingproject.market.model.Product;
import ru.geekbrains.trainingproject.market.repositories.CartRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductService productService;
    private final CartRepository cartRepository;


    public void AddProduct(Long id) {
        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category title = " + id + " not found"));
        CartProductsDto cartProductsDto = new CartProductsDto(product);
        addProductToCartAndCalculateQuantity(cartProductsDto);
    }

    public void deleteAll() {
        cartRepository.getCartProductsDtoList().clear();
    }

    public Optional<CartProductsDto> getById(long id) {
        Optional<CartProductsDto> cartProductsDto = Optional.ofNullable(cartRepository.getCartProductsDtoList().stream().filter(p -> p.getId() == id).findFirst().orElseThrow(() -> new ResourceNotFoundException("Category title = " + id + " not found in this cart")));
        return cartProductsDto;
    }

    public List<CartProductsDto> getAll() {
        return cartRepository.getCartProductsDtoList();
    }

    private boolean isProductExistInCart(ProductDto productDto) {
        return cartRepository.getCartProductsDtoList().contains(productDto);
    }

    private void addProductToCartAndCalculateQuantity(CartProductsDto cartProductsDto) {
        if (isProductExistInCart(cartProductsDto)) {
            cartProductsDto.setQuantity(cartProductsDto.getQuantity() + 1);
        } else {
            cartRepository.getCartProductsDtoList().add(cartProductsDto);
            cartProductsDto.setQuantity(1);
        }
    }
}

