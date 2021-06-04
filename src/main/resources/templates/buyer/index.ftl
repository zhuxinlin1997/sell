<#include "./header.ftl">
<div class="pet-3">
    <#list productInfoList as productInfo>
        <a href="/sell/buyer/product/web/detail?productId=${productInfo.productId}">
            <div class="pet-3-1">
                <img src="${productInfo.productIcon}"/>
                <div class="pet-3-1-1">￥${productInfo.productPrice}</div>
                <div class="pet-3-1-2">${productInfo.productName}</div>
            </div>
        </a>
    </#list>
</div>
</body>
</html>
