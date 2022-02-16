<#import "../parts/common.ftl" as c>
<#import "../parts/header.ftl" as h>

<@c.page>
    <@h.restaurant/>

    <h2>Ваш профиль</h2>
    <form action="/restaurant/profile" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="hidden" name="userId" value="${user.id}">

        <div><label> Отображаемое имя <input type="text" name="displayedName" value="${user.displayedName!}"/> </label></div>
        <div><label> Адрес <input type="text" name="address" value="${user.address!}"/> </label></div>
        <div><label> Телефон <input type="text" name="phoneNumber" value="${user.phoneNumber!}"/> </label></div>
        <div><label> Комментарий <textarea name="comment" rows="3" cols="40" style="resize: none" placeholder="${user.comment!}"></textarea> </label></div>

        <div><input type="submit" value="Сохранить"></div>
    </form>
</@c.page>