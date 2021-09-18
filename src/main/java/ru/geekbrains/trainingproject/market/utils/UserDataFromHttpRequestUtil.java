package ru.geekbrains.trainingproject.market.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.geekbrains.trainingproject.market.exceptions.ResourceNotFoundException;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Component
public class UserDataFromHttpRequestUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;

    private final HttpServletRequest httpServletRequest;

    public String getUserTmpId() {
        String tmpIdHandler = httpServletRequest.getHeader("tmpId");
        if (tmpIdHandler != null && tmpIdHandler.startsWith("tmpId ")) {
            return tmpIdHandler.substring(6);
        } else {
//            throw new ResourceNotFoundException("Не удалось получить праметр <tmpId> от клиента");
            return null;
        }
    }

    public String getUserName() {
        String authHandler = httpServletRequest.getHeader("Authorization");
        String token = null;
        if (authHandler != null && authHandler.startsWith("Bearer ")) {
            token = authHandler.substring(7);
            return getLoginFromToken(token);
        } else {
            return null;
//            throw new ResourceNotFoundException("Не удалось получить имя пользователя из из httpServlet");
        }
    }

    public String getLoginFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}

