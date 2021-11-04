package ru.geekbrains.market.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("secret.properties")
public class SpringWebApp {
	// TODO План на курс:
	// 1. Сделать регистрацию пользователей на отдельной странице
	// 2. + Сделать корзину (+ добавить редис)
	// 3. История просмотра товаров
	// 4. (дз) Отзывы товаров
	// 5. Сделать дерево категорий товаров
	// 6. Блок наиболее популярных товаров
	// 7. Начисление бонусов, + личный кабинет пользователя
	// 8. + Побольше разделения прав пользователей (юзер, админ, суперадмин)
	// 9. + Сделать оформление заказов
	// 10. Добавить платежную систему
	// 11. + Фильтрация товаров
	// 12. Почтовая рассылка
	// 13. Поиск по сайту (возможно даже умный)
	// 14. Добавить картинки к товарам
	// *. ** Акции
	// *. *** Админка
	// *. Рассмотреть MapStruct
	// *. Добавить на фронте валидацию токенов
	// *. + Добавить Docker- compose, file

	// TODO Занятие 12 PayPal

	// Домашнее задание:
	// 1. Замените интеграцию core с корзиной с RestTemplate на WebClient

	// 2. * Подумайте как реализовать защиту endpoint'ов

	// 3. * Попробуйте разобраться с CORS Policy для Auth Server

	public static void main(String[] args) {
		SpringApplication.run(SpringWebApp.class, args);
		System.out.println("Core module started!");
	}
}
