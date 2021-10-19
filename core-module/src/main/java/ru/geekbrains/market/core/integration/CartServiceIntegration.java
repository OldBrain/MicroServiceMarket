package ru.geekbrains.market.core.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.header.Header;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import ru.geekbrains.market.api.dtos.cart.CartDto;
import ru.geekbrains.market.core.utils.UserNameFromHttpRequestUtil;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Enumeration;
import java.util.Iterator;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {
    private final RestTemplate restTemplate;
    private final HttpServletRequest httpServletRequest;

    @Value("${integration.cart-service.url}")
    private String cartServiceUrl;
    public CartDto getUserCartDto() {
//        if (currentUserName.equals(null)) {
//            throw new RuntimeException("ERROR!!!");
//        }
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
//        headers.add("username", currentUserName);

//        Iterator iterator = httpServletRequest.getHeaderNames().asIterator();
//        while (iterator.hasNext()) {
//            headers.add(iterator.next(), httpServletRequest.getHeader((String) iterator.next()));
//        }


        return restTemplate.exchange(cartServiceUrl, HttpMethod.GET, new HttpEntity(headers), CartDto.class).getBody();

    }

    public void clear(String currentUserName) {
        if (currentUserName.equals(null)) {
            throw new RuntimeException("ERROR!!!");
        }
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

//        headers.add("username", currentUserName);
        restTemplate.exchange(cartServiceUrl + "/clear", HttpMethod.GET, new HttpEntity(headers), void.class);
    }
}
