<#import "../parts/common.ftl" as c>
<#import "../parts/header.ftl" as h>
<#import "../parts/profile.ftl" as p>

<@c.page "admin">
    <h2>Редактирование пользователя</h2>
    ${message!}
    <form action="/admin/users/" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="hidden" name="userId" value="${user.id}">
        <div><label> Имя пользователя <input type="text" name="username" value="${user.username}"/> </label></div>
        <div><label> Новый пароль <input type="password" name="newPassword" placeholder=""/> </label></div>
        <div><label> Подтвердите пароль <input type="password" name="confirmPassword"/> </label></div>
        <div><label> Роль
                <select name="role">
                    <#list roles as r>
                        <option value="${r.name()}" <#if user.role == r.name()>selected</#if>>${r.title}</option>
                    </#list>
                </select>
            </label>
        </div>
        <div><input type="submit" value="Сохранить"/></div>
    </form>

    <h2>Обновление информации о пользователе</h2>
    <@p.edit "/admin/users/saveDetails"/>

    <h2>Удаление пользователя</h2>
    <form action="/admin/users/delete" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="hidden" name="userId" value="${user.id}">

        <div><input type="submit" value="Удалить"></div>
    </form>
</@c.page>