package ru.geekbrains.trainingproject.market.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.trainingproject.market.exceptions.ResourceNotFoundException;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Component
public class TmpUserIdFromHttpRequestUtil {
    private final HttpServletRequest httpServletRequest;

    public String getUserTmpId() {
        String tmpIdHandler = httpServletRequest.getHeader("tmpId");
        if (tmpIdHandler != null && tmpIdHandler.startsWith("tmpId ")) {
            return tmpIdHandler.substring(6);
        } else {
            throw new ResourceNotFoundException("Не удалось получить праметр <tmpId> от клиента");
//            return "NoName";
        }
    }


}
