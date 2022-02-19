<#import "../parts/common.ftl" as c>
<#import "../parts/header.ftl" as h>
<#import "../parts/login.ftl" as l>

<@c.page "admin">
    <h2>Новый пользователь</h2>
    ${message!""}
    <@l.login "/admin/users/add" "Регистрация"/>
</@c.page>