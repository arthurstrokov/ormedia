<#import "parts/common.ftl" as c>

<@c.page>
    <#if isCurrentUser>
        <#include "parts/filmEdit.ftl" />
    </#if>

    <#include "parts/filmList.ftl" />
</@c.page>