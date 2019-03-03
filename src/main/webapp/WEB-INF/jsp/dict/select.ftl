<#if itemList?exists> 
  <select>
   <#list itemList as item>
      <option value="${item.id?string("0")}">${item.itemName}</option>
    </#list>
  </select> 
</#if>