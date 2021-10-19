package ru.geekbrains.market.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MarketApplication {

    // TODO План на курс:
    // 1. Сделать регистрацию пользователей на отдельной странице
    // 2. Сделать корзину (+ добавить редис)
    // 3. История просмотра товаров
    // 4. Комментарии/рейтинги/отзывы товаров
    // 5. Сделать дерево категорий товаров
    // 6. Блок наиболее полпулярных товаров
    // 7. Начисление бонусов, личный кабинет пользователя
    // 8. Побольше разделения прав пользователей (юзер, админ, суперадмин)
    // 9. Сделать оформление заказов
    // 10. Добавить платежную систему
    // 11. Фильтрация товаров
    // 12. Почтовая рассылка
    // 13. Поиск по сайту (возможно даже умный)
    // *. ** Акции
    // *. *** Админка
    // *. Рассмотреть MapStruct

    // Домашнее задание:
    // 1. Сделать на backend'е оформление заказа с сохранением его в БД
    // 2. * Привязывать заказ к текущему пользователю

    public static void main(String[] args) {
        SpringApplication.run(MarketApplication.class, args);
        System.out.println("Application start OK!");
        System.out.println("see app from url: http://localhost:8189/market/index.html#!/");
        System.out.println("In test mode, see data console from url: http://localhost:8189/market/h2-console/");
    }

}
