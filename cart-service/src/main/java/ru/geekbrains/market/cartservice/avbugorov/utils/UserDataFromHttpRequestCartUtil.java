package ru.geekbrains.market.cartservice.avbugorov.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.geekbrains.market.api.exeptions.ResourceNotFoundException;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Component
public class UserDataFromHttpRequestCartUtil {

    private final HttpServletRequest httpServletRequest;

    public String getUserTmpId() {
        String tmpIdHandler = httpServletRequest.getHeader("tmpId");
        if (tmpIdHandler != null && tmpIdHandler.startsWith("tmpId ")) {
            return tmpIdHandler.substring(6);
        } else {
            return null;
        }
    }

    public String getUserCurrentName() {
        String userNameHeader = httpServletRequest.getHeader("name");
        if (userNameHeader != null && userNameHeader.startsWith("name ")) {
            return userNameHeader.substring(5);
        } else {
            return null;
        }
    }
}

