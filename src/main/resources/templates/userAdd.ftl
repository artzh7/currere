<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    <header>
        <a href="/">На главную</a>
        <a href="/users">Пользователи</a>
    </header>

    <h2>Новый пользователь</h2>
    ${message!""}
    <@l.login "/users/add" "Регистрация"/>
</@c.page>