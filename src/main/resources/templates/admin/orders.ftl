<#import "../parts/common.ftl" as c>
<#import "../parts/header.ftl" as h>

<@c.page>
    <@h.admin/>

    <h2>Новый заказ</h2>
    <form method="post" action="/admin/orders/add">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <div><label> Ресторан <input type="text" name="restaurantName"/> </label></div>
        <div><label> Адрес клиента <input type="text" name="clientAddress"/> </label></div>
        <div><label> Телефон клиента <input type="text" name="clientPhoneNumber"/> </label></div>
        <div><label> Комментарий к заказу <textarea name="orderComment" rows="3" cols="40" style="resize: none"></textarea> </label></div>
        <div><input type="submit" value="Создать"/></div>
    </form>

    <h2>Список заказов</h2>
    <form action="/admin/orders" method="get">
        <div>
            <select name="orderStatus">
                <option disabled selected>Выбрать статус</option>
                <option value="">Все</option>
                <#list statuses as status>
                    <option value="${status.name()}">${status.getTitle()}</option>
                </#list>
            </select>
            <input type="submit" value="Фильтр"/>
        </div>
    </form>
    <table class="table">
        <thead>
        <tr>
            <th>ID</th>
            <th>Статус</th>
            <th>Ресторан</th>
            <th>Адрес клиента</th>
            <th>Телефон клиента</th>
            <th>Комментарий</th>
        </tr>
        </thead>
        <tbody>
        <#list orders as order>
            <tr>
                <td>${order.id}</td>
                <td>${order.orderStatus.title}</td>
                <td>${order.restaurantName}</td>
                <td>${order.clientAddress}</td>
                <td>${order.clientPhoneNumber}</td>
                <td>${order.orderComment}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>