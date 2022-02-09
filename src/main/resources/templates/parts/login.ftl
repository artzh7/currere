<!-- форма используется в логине и регистрации -->
<#macro login path buttonValue>
    <div>
        <form action="${path}" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <div><label> Имя пользователя <input type="text" name="username"/> </label></div>
            <div><label> Пароль <input type="password" name="password"/> </label></div>
            <div><input type="submit" value="${buttonValue}"/></div>
        </form>
    </div>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Выйти"/>
    </form>
</#macro>