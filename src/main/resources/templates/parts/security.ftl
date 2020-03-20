<#assign
    known = Session.SPRING_SECURITY_CONTEXT??
>
<#if known>
    <#assign
        user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        name = user.getUsername()
        id = user.getId()
    >
<#else>
    <#assign
        name = "unknown"
    >
</#if>
