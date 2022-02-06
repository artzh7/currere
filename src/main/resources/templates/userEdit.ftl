<#import "parts/common.ftl" as c>

<@c.page>
    <header>
        <a href="/">На главную</a>
        <a href="/users">Пользователи</a>
    </header>

    <h2>Редактирование пользователя</h2>
    <form action="/users" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="hidden" name="userId" value="${user.id}">

        <input type="text" name="username" value="${user.username}">
        <div>
            <#list roles as role>
                <span>
                <label>
                    <input type="checkbox" name="${role}"
                            ${user.roles?seq_contains(role)?string("checked", "")}>
                    ${role.title}
                </label>
                </span>
            </#list>
        </div>
        <div>
            <button type="submit">Сохранить</button>
        </div>
    </form>
</@c.page>