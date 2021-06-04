<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>网上宠物商店</title>
    <!--导入主界面的css代码 -->
    <script type="text/javascript" src="/sell/js/jquery-1.10.0.js" ></script>
    <script type="text/javascript" src="/sell/js/zui.js" ></script>
    <link rel="stylesheet" type="text/css" href="/sell/css/zui.css"/>
    <link rel="stylesheet" type="text/css" href="/sell/css/xiangqing.css"/>
    <link rel="stylesheet" type="text/css" href="/sell/css/petIndex.css"/>
</head>
<body>
<div class="pet-1">
    <span class="pet-1-1"><marquee direction="up" scrollamount="3" style="width: 200px;">欢迎来到网上宠物商城</marquee></span>
    <#if !(username)??>
        <span class="pet-1-2">
            <a href="/sell/buyer/toLogin">登录</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="/sell/buyer/toLogin">注册</a>
        </span>
    <#else>
        <span class="pet-1-2">
            <a href="/sell/buyer/manage">个人管理</a>&nbsp;&nbsp;|&nbsp;&nbsp;${username}&nbsp;&nbsp;|&nbsp;&nbsp;<a href="/sell/buyer/logout">退出</a>
        </span>
    </#if>

</div>
<div class="pet-img">
    <img src="/sell/img/2345_image_file_copy_4.jpg"style="width: 1249px;"/>
</div>
<div class="pet-2">
    <img src="/sell/img/logo.png" style="height: 150px;width: 250px;"/>
    <div class="pet-2-1">
        <ul>
            <#list productCategoryList as productCategory>
                <li>
                    <a href="/sell/buyer/product/web/list?productCategory=${productCategory.categoryType}">
                        <img class="sc" src="/sell/img/2345_image_file_copy_3.jpg"/>
                        <b class="b">${productCategory.categoryName}</b>
                    </a>
                </li>
            </#list>
        </ul>
    </div>
</div>
<div class="pet-img" >
    <img src="/sell/img/2345_image_file_copy_4.jpg"style="width: 1249px;"/>
</div>