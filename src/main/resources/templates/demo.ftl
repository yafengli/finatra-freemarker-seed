<#include "layout/boot.ftl"/>
<@layout "This is title!" "_home">
<h1>${name!"Not SET Name!"}</h1>
<ul>
    <#list persons as t>
        <li>${t.name}|${t.age}</li>
    </#list>
</ul>
</@layout>