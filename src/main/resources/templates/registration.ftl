<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Login form</title>

    <link href="/css/style.css" rel="stylesheet">
    <link href="/css/font-awesome.css" rel="stylesheet">
</head>
<body>

<form action="/registration" method="post">

    <div class="container">
        Заполните карточку пациента:
        <img src="img/men.png">
        <form>
            <div class="dws-input">
                <input type="text" name="username" placeholder="Введите логин">
            </div>
            <div class="dws-input">
                <input type="password" name="password" placeholder="Введите пароль">
            </div>
            <div class="dws-input">
                <input type="text" name="first_name" placeholder="Введите имя">
            </div>
            <div class="dws-input">
                <input type="text" name="second_name" placeholder="Введите отчество">
            </div>
            <div class="dws-input">
                <input type="text" name="last_name" placeholder="Введите фамилию">
            </div>
            <div class="dws-input">
                <input type="text" name="email" placeholder="Введите email">
            </div>
            <div class="dws-input">
                <input type="text" name="snils" placeholder="Введите номер СНИЛС">
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <input class="dws-submit" type="submit" name="submit" value="Зарегистрироваться"><br/>
            <a href="/login">Войти</a>
        </form>
    </div>
</form>
</body>