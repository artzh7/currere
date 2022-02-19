<#import "../parts/common.ftl" as c>
<#import "../parts/header.ftl" as h>
<#import "../parts/profile.ftl" as p>

<@c.page "restaurant">
    <h2>Ваш профиль</h2>
    <@p.edit action="/restaurant/profile"/>
</@c.page>