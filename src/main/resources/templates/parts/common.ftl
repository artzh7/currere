<#import "../parts/header.ftl" as h>

<#macro page role>
    <!doctype html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>currere</title>
    </head>
    <body>

    <#if role = "admin">
        <@h.admin/>
    <#elseif role = "restaurant">
        <@h.restaurant/>
    <#elseif role = "courier">
        <@h.courier/>
    <#else>
    </#if>

    <#nested>
    </body>
    <#include "style.ftl"/>
    </html>
</#macro>