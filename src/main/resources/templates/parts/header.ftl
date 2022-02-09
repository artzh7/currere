<#import "login.ftl" as l>

<#macro admin>
    <header>
        <ul>
            <li><a href="/admin">Главная</a></li>
            <li><a href="/admin/orders">Заказы</a></li>
            <li><a href="/admin/users">Пользователи</a></li>
            <li><a href="/admin/users/add">Регистрация</a></li>
            <li><@l.logout/></li>
        </ul>
    </header>
</#macro>

<#macro restaurant>
    <header>
        <ul>
            <li><a href="/restaurant">Главная</a></li>
            <li><a href="/restaurant/orders">Заказы</a></li>
            <li><a href="/restaurant/profile">Профиль</a></li>
            <li><@l.logout/></li>
        </ul>
    </header>
</#macro>

<#macro courier>
    <header>
        <ul>
            <li><a href="/courier">Текущие заказы</a></li>
            <li><a href="/courier?orderStatus=FINISHED">Завершенные</a></li>
            <li><@l.logout/></li>
        </ul>
    </header>
</#macro>