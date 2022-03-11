# Currere

Currere — платформа для автоматизации сервиса доставки. Целью проекта было создать приложение, позволяющее авторизованным в системе ресторанам создавать заказы на доставку и отслеживать их выполнение. 



## Возможности

Управление заказами:

- Создание рестораном/админом заказов.
- Назначение на курьера, отмена, удаление заказов админом
- Изменение статуса заказов назначенным на заказ курьером

Управление пользователями:

- Регистрация пользователей в системе админом, назначение ролей
- Редактирование личных данных, отображаемых деталей



## Пользовательские роли

#### Ресторан

- Регистрируется в системе администратором
- Создает заказ, содержащий все необходимые для доставки подробности о клиенте: адрес, телефон, комментарий
- Есть возможность редактирования своего профиля: логин-пароль, адрес, телефон, комментарий ресторана
- Видит список всех текущих и завершенных своих заказов. Отдельно можно перейти на страницу одного из заказов с информацией о статусе, деталях и назначенном курьере



![Ресторан](https://s7.gifyu.com/images/gif_rest.gif)



#### Администратор

- Полное управление жизненным циклом заказа: назначение заказа на курьера с открытой сменой, отмена, полное удаление, создание заказа без прикрепления к определенному ресторану
- Полное управление учетными записями пользователей: регистрация в системе, назначение ролей, обновление профиля, удаление



![Администратор](https://s7.gifyu.com/images/gif_admin.gif)



#### Курьер

- Курьеру доступен список назначенных заказов. В каждом заказе отображается вся необходимая для доставки информация. Есть возможность просмотреть историю выполненных заказов
- Проставление статусов заказа для его отслеживания: прибыл в ресторан, выехал к клиенту, отдал заказ
- Открытие и закрытие смены



![Курьер](https://s7.gifyu.com/images/gif_courier.gif)



## Запуск и использование 

Приложение создано на основе Spring Boot. Готового jar-файла пока нет, поэтому простейший запуск либо из IDE, либо деплой на Heroku и т.п. 

Для запуска требуется Java 8 и PostgreSQL (версии ниже 13 не тестировались). 

Необходимо предварительно создать базу данных

- В файле `src/main/resources/application.properties` поменять имя БД, пользователя и пароль на свои.

В проекте присутствует Flyway, при первом запуске приложения сформируются все необходимые таблицы в БД. 

- Регистрировать пользователей может только администратор, поэтому для старта уже есть учетная запись админа с логином `admin` и паролем `1`. Разумеется, его можно сразу же изменить на странице управления пользователем.



#### Деплой на Heroku

[Ссылка на гайд](https://devcenter.heroku.com/articles/getting-started-with-java). Если вкратце, то создается удаленный репозиторий хероку, связанный с локальным, туда заливается код для дальнейшего развертывания. Как ни странно, для простейшего взаимодействия с приложением ничего настраивать не нужно: аддон PostgreSQL автоматически настраивается.



## От разработчика

To be continued.