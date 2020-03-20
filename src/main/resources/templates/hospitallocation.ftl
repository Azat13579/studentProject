<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Список ближайших больниц!</title>
</head>
<body>
<h1> "Cписок ближайших медецинских учреждений, в которых Вам обязательно помогут!" </h1>
<form action="/history" method="POST">
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <input class="btn btn-primary btn-block" type="submit" value= "вернуться к истории!">

    <table class="table">
        <thead>
        <tr class="table-secondary">
            <th scope="col">Расстояние до больницы</th>
            <th scope="col">Медицинское учреждение</th>
            <th scope="col">Адрес</th>
            <th scope="col">Телефон</th>
            <th scope="col">E-mail</th>
        </tr>
        </thead>
        <tbody>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <#list clinicEntities as hospital>
            <tr>
                <td>${hospital.getKey()} км.</td>
                <td>${hospital.getValue().name}</td>
                <td>${hospital.getValue().address}</td>
                <td>${hospital.getValue().telephone}</td>
                <td>${hospital.getValue().email}</td>
            </tr>
        </#list>
         </tbody>
    </table>

</form>

</body>
</html>