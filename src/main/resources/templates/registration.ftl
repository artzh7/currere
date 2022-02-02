<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    <h2>Новый пользователь</h2>
    ${message!""}
    <@l.login "/registration" "Зарегистрироваться"/>
</@c.page>