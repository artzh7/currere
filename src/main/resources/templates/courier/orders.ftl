<#import "../parts/common.ftl" as c>
<#import "../parts/header.ftl" as h>
<#import "../parts/order.ftl" as o>

<@c.page>
    <@h.courier/>

    <h2>${orderListTitle}</h2>
    <@o.list "courier"/>
</@c.page>