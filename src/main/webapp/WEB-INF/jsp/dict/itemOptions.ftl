<#if itemList?exists> 
   <#list itemList as item>
      ${item.itemCode}:${item.itemName}
      <#if item_has_next>
       ;
      </#if> 
    </#list> 
</#if>