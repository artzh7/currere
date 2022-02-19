<#import "../parts/common.ftl" as c>
<#import "../parts/header.ftl" as h>

<@c.page "admin">
    <h2>Список пользователей</h2>
    <table class="table">
        <thead>
        <tr>
            <th>Имя пользователя</th>
            <th>Роль</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
            <tr>
                <td>${user.username}</td>
                <td>${user.role.title}</td>
                <td><a href="/admin/users/${user.id}">Редактировать</a></td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>