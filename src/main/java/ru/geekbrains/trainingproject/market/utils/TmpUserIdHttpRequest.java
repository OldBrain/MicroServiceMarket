package ru.geekbrains.trainingproject.market.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor

public class TmpUserIdHttpRequest extends HttpServlet {
    private String userTmpId;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tmpIdHandler = req.getHeader("tmpId");
        if (tmpIdHandler != null && tmpIdHandler.startsWith("tmpId ")) {
            userTmpId = tmpIdHandler.substring(6);
        } else {
            userTmpId = "NoName";
        }
    }


    public String getUserTmpId() {
        return userTmpId;
    }
}
