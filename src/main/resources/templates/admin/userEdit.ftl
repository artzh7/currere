<#import "../parts/common.ftl" as c>
<#import "../parts/header.ftl" as h>

<@c.page>
    <@h.admin/>

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
    <form action="/admin/users/saveDetails" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="hidden" name="userId" value="${user.id}">

        <div><label> Отображаемое имя <input type="text" name="displayedName" value="${user.displayedName!}"/> </label></div>
        <div><label> Адрес <input type="text" name="address" value="${user.address!}"/> </label></div>
        <div><label> Телефон <input type="text" name="phoneNumber" value="${user.phoneNumber!}"/> </label></div>
        <div><label> Комментарий <textarea name="comment" rows="3" cols="40" style="resize: none" placeholder="${user.comment!}"></textarea> </label></div>

        <div><input type="submit" value="Сохранить"></div>
    </form>

    <h2>Удаление пользователя</h2>
    <form action="/admin/users/delete" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="hidden" name="userId" value="${user.id}">

        <div><input type="submit" value="Удалить"></div>
    </form>
</@c.page>