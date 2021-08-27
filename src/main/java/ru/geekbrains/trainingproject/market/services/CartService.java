package ru.geekbrains.trainingproject.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.geekbrains.trainingproject.market.dtos.CartProductDto;
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


    public void addProduct(Long id) {
        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category title = " + id + " not found"));
        CartProductDto cartProductsDto = new CartProductDto(product);
        addProductToCartAndCalculateQuantity(cartProductsDto);
    }

    public void deleteAll() {
        cartRepository.getCartProductsDtoList().clear();
    }

    public void deleteById(Long id) {
        for (int i = 0; i < cartRepository.getCartProductsDtoList().size(); i++) {
            if (cartRepository.getCartProductsDtoList().get(i).getId().equals(id)) {
                cartRepository.getCartProductsDtoList().remove(i);
                break;
            }
        }
    }

    public Optional<CartProductDto> getById(long id) {
        Optional<CartProductDto> cartProductsDto = Optional.ofNullable(cartRepository.getCartProductsDtoList().stream().filter(p -> p.getId() == id).findFirst().orElseThrow(() -> new ResourceNotFoundException("Category title = " + id + " not found in this cart")));
        return cartProductsDto;
    }

    public List<CartProductDto> getAll() {
        return cartRepository.getCartProductsDtoList();
    }

    public CartProductDto getProduct(CartProductDto cartProductDto) {
        for (CartProductDto p : cartRepository.getCartProductsDtoList()) {
            if (p.getId().equals(cartProductDto.getId())) {
                return p;
            }
        }
        return null;
    }

    private boolean isProductExistInCart(CartProductDto cartProductsDto) {
        for (CartProductDto p : cartRepository.getCartProductsDtoList()
        ) {
            if (p.getId().equals(cartProductsDto.getId())) {
                return true;
            }
        }
        return false;
    }

    private void addProductToCartAndCalculateQuantity(CartProductDto cartProductDto) {
        if (isProductExistInCart(cartProductDto)) {
            for (CartProductDto p : cartRepository.getCartProductsDtoList()) {
                if (p.getId().equals(cartProductDto.getId())) {
                    p.setQuantity(p.getQuantity() + 1);
                }
            }
        } else {
            cartProductDto.setQuantity(1);
            cartRepository.getCartProductsDtoList().add(cartProductDto);
        }
    }


    public void updateProduct(CartProductDto cartProductDto) {
        CartProductDto cartProductDtoEdit = getProduct(cartProductDto);
        deleteById(cartProductDtoEdit.getId());
        save(cartProductDtoEdit);
    }

    private void save(CartProductDto cartProductDto) {
        CartProductDto cartProductDtoEdit = new CartProductDto();
        cartProductDtoEdit = cartProductDto
        cartRepository.getCartProductsDtoList().add(cartProductDtoEdit);
    }

}

