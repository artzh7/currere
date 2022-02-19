<#macro edit action>
    <form action="${action}" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="hidden" name="userId" value="${user.id}">

        <div><label> Отображаемое имя <input type="text" name="displayedName" value="${user.displayedName!}"/> </label></div>
        <div><label> Адрес <input type="text" name="address" value="${user.address!}"/> </label></div>
        <div><label> Телефон <input type="text" name="phoneNumber" value="${user.phoneNumber!}"/> </label></div>

        <#if user.role.name()?lower_case != "courier">
            <div><label> Комментарий
                    <textarea name="comment" rows="3" cols="40" style="resize: none"
                              placeholder="${user.comment!}"></textarea>
                </label></div>
        </#if>

        <div><input type="submit" value="Сохранить"></div>
    </form>
</#macro>