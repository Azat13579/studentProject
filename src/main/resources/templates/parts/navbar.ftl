<#import "login.ftl" as l>
<#include "security.ftl">

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="/">
            <img src="img/logo.png">
            medProject
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/history">История обращений</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/addRequest">Создать обращение</a>
                </li>
            </ul>

            <div class="nav-item dropdown ml-5">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    ${name}
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="/user">Профиль пользователя</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="/user/${id}">Редактирование профиля</a>
                </div>
            </div>
        </div>
        <@l.logout />
    </div>
</nav>