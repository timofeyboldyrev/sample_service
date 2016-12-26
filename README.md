  Пример http-сервиса.
  Задача: написать веб-метод, который возвращает информацию о пользователе из Siebel.
  Описание метода: метод POST, принимает JSON, отдаёт JSON.
  Входные параметры:
1) sessionId - идентификатор вызвавшего пользователя (имеется на фронте после того, как пользователь прошёл авторизацию)
2) userSiebelId - siebel id пользователя, по которому нужно получить информацию
  Последовательность действий:
1) Проверка входных параметров. Все параметры обязательные.
2) Проверка доступности.
2.1) Вызвать API сервера авторизации, чтобы убедиться, что срок действия сессии не истёк. А также, чтобы получить список ролей вызывающего.
2.2) Проверить, что вызвавший метод пользователь имеет роль администратора
3) Получить информацию из Siebel путём вызова SOAP сервиса.
4) Возвратить всю информацию, полученную из Siebel.
