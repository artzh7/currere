<#import "parts/common.ftl" as c>

<@c.page>
    <header>
        <a href="/">На главную</a>
        <a href="/users/add">Регистрация</a>
    </header>

    <h2>Список пользователей</h2>
    <table class="table">
        <thead>
        <tr>
            <th>Имя пользователя</th>
            <th>Роли</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
            <tr>
                <td>${user.username}</td>
                <td><#list user.roles as role>${role}<#sep>, </#list></td>
                <td><a href="/users/${user.id}">Редактировать</a></td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>