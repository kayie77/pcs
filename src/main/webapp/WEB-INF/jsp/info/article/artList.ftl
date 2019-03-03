<#if artList?exists> 
   <ul class="block-ul">
   <#list artList as art> 
        <li><a class="article" href="/fss/info/article/view.action?id=${art.id}">${art.title}</a></li>
    </#list> 
    </ul>
</#if>