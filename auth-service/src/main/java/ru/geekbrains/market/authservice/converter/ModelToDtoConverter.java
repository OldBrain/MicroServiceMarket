package ru.geekbrains.market.authservice.converter;

import org.springframework.stereotype.Component;
import ru.geekbrains.market.api.dtos.DetailsUserDto;
import ru.geekbrains.market.authservice.model.DetailsUser;

@Component
public class ModelToDtoConverter {

    public DetailsUserDto detailsUserToDto(DetailsUser dUser) {
        return new DetailsUserDto(dUser.getLastName(),
                dUser.getFirstName(), dUser.getPatronymic(),
                dUser.getPhone(), dUser.getAddress());
    }

}
