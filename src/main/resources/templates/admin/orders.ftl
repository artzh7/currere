<#import "../parts/common.ftl" as c>
<#import "../parts/header.ftl" as h>
<#import "../parts/order.ftl" as o>

<@c.page "admin">
    <@o.create "admin" "/admin/orders/add"/>

    <h2>Список заказов</h2>
    <@o.statusFilter "/admin/orders"/>
    <@o.list "admin"/>
</@c.page>