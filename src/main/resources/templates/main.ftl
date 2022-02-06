<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    <ul>
        <li><a href="/orders">Заказы</a></li>
        <li><a href="/users">Пользователи</a></li>
    </ul>
    <@l.logout/>
</@c.page>