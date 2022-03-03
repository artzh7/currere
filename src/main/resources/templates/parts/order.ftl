<#macro create role action>
    <h2>Новый заказ</h2>
    <form method="post" action="${action}">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <#if role == "admin">
            <h4>Данные о ресторане</h4>
            <div><label> Ресторан <input type="text" name="restaurantName"/> </label></div>
            <div><label> Адрес <input type="text" name="restaurantAddress"/> </label></div>
            <div><label> Телефон <input type="text" name="restaurantPhoneNumber"/> </label></div>
            <div><label> Комментарий <textarea name="restaurantComment" rows="3" cols="40" style="resize: none"></textarea> </label></div>
        </#if>
        <h4>Данные о заказе</h4>
        <div><label> Адрес клиента <input type="text" name="clientAddress"/> </label></div>
        <div><label> Телефон <input type="text" name="clientPhoneNumber"/> </label></div>
        <div><label> Комментарий к заказу <textarea name="orderComment" rows="3" cols="40" style="resize: none"></textarea> </label></div>
        <div><input type="submit" value="Создать"/></div>
    </form>
</#macro>

<#macro list role>
    <table class="table">
        <thead>
        <tr>
            <th>ID</th>
            <th>Статус</th>
            <th>Ресторан</th>
            <th>Адрес клиента</th>
            <th>Телефон клиента</th>
            <th>Комментарий</th>
            <#if role == "restaurant" || role == "admin">
                <th>Курьер</th>
            </#if>
            <#if role == "admin" || role == "courier">
                <th>Действия</th>
            </#if>
        </tr>
        </thead>
        <tbody>
        <#list orders as order>
            <tr>
                <td>
                    <a href="/${role}/orders/${order.id}">${order.id}</a>
                </td>
                <td>${order.orderStatus.title}</td>
                <td>${order.restaurantName}</td>
                <td>${order.clientAddress}</td>
                <td>${order.clientPhoneNumber}</td>
                <td>${order.orderComment}</td>

                <#if role = "restaurant" || (role = "admin"
                                            && (order.orderStatus.name() == "FINISHED"
                                                || order.orderStatus.name() == "CANCELLED"))>
                    <td>
                        <#if order.courier??>
                            ${order.courier.displayedName}
                        <#else>
                            Не назначен
                        </#if>
                    </td>
                <#elseif role = "admin">
                    <td>
                        <form action="/admin/orders/appointOnCourier" method="post">
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                            <input type="hidden" name="orderId" value="${order.id}">
                            <span><label>
                                    <select name="courierId">
                                        <#if order.courier??><#else><option disabled selected>Назначить курьера</option></#if>
                                        <#list couriers as c>
                                            <option value="${c.id}" <#if order.courier?? && order.courier.id == c.id>selected</#if>>${c.displayedName!c.username}</option>
                                        </#list>
                                    </select>
                                </label>
                            </span>
                            <span><input type="submit" title="Назначить на выбранного курьера" value="✔️"/></span>
                        </form>
                    </td>
                </#if>

                <#if role == "admin">
                    <td>
                        <#if order.orderStatus.name() == "ACCEPTED">
                            <form hidden id="appointOnRandom${order.id}" method="post" action="/admin/orders/appointOnRandom">
                                <input type="hidden" name="_csrf" value="${_csrf.token}">
                                <input type="hidden" name="orderId" value="${order.id}">
                            </form>
                            <span><input form="appointOnRandom${order.id}" type="submit" title="Назначить автоматически" value="✅"></span>
                        </#if>
                        <#if (order.orderStatus.name() != "CANCELLED") && (order.orderStatus.name() != "FINISHED")>
                            <form hidden id="cancel${order.id}" method="post" action="/admin/orders/cancel">
                                <input type="hidden" name="_csrf" value="${_csrf.token}">
                                <input type="hidden" name="orderId" value="${order.id}">
                            </form>
                            <span><input form="cancel${order.id}" type="submit" title="Отменить заказ" value="⭕"></span>
                        </#if>
                        <form hidden id="delete${order.id}" method="post" action="/admin/orders/delete">
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                            <input type="hidden" name="orderId" value="${order.id}">
                        </form>
                        <span><input form="delete${order.id}" type="submit" title="УДАЛИТЬ заказ" value="🛑"></span>
                    </td>
                <#elseif role == "courier" && order.orderStatus.name() != "FINISHED">
                    <td>
                        <form hidden id="nextStatus${order.id}" method="post" action="/courier/orders/nextStatus">
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                            <input type="hidden" name="orderId" value="${order.id}">
                        </form>
                        <span><input form="nextStatus${order.id}" type="submit" title="Следующий статус" value="➡️"></span>
                    </td>
                </#if>
            </tr>
        </#list>
        </tbody>
    </table>
</#macro>

<#macro statusFilter action>
    <form action="${action}" method="get">
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
</#macro>

<#macro info role>
    <h2>Заказ №${order.id}</h2>
    <b>Статус: </b> <span>${order.orderStatus.title}</span>
    <h3>Данные о ресторане</h3>
    <b>Ресторан: </b> <span>${order.restaurantName!}</span> <br>
    <b>Адрес: </b> <span>${order.restaurantAddress!}</span> <br>
    <b>Телефон: </b> <span>${order.restaurantPhoneNumber!}</span> <br>
    <b>Комментарий: </b> <span>${order.restaurantComment!}</span>
    <h3>Данные о заказе</h3>
    <b>Адрес клиента: </b> <span>${order.clientAddress!}</span> <br>
    <b>Телефон: </b> <span>${order.clientPhoneNumber!}</span> <br>
    <b>Комментарий к заказу: </b> <span>${order.clientPhoneNumber!}</span>
    <#if role != "courier">
        <h3>Курьер</h3>
        <#if order.courier??>
            <b>Имя: </b> <span>${order.courier.displayedName!}</span> <br>
            <b>Телефон: </b> <span>${order.courier.phoneNumber!}</span>
        </#if>
    </#if>
</#macro>