<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    <h2>Логин</h2>
    <@l.login "/login" "Войти"/>
    <div><a href="/registration">Регистрация</a></div>
</@c.page>