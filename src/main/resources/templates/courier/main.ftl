<#import "../parts/common.ftl" as c>
<#import "../parts/header.ftl" as h>

<@c.page "courier">
    <h2>Главная</h2>
    ${message!}
    <form action="/courier/shift" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="${courier.working?string("Закрыть смену", "Открыть смену")}"/>
    </form>
</@c.page>