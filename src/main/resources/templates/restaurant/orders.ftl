<#import "../parts/common.ftl" as c>
<#import "../parts/header.ftl" as h>
<#import "../parts/order.ftl" as o>

<@c.page>
    <@h.restaurant/>

    <h2>Новый заказ</h2>
    <@o.create "restaurant" "/restaurant/orders/add"/>

    <h2>Список заказов</h2>
    <@o.statusFilter "/restaurant/orders"/>
    <@o.list "restaurant"/>
</@c.page>