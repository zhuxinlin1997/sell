<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>个人中心</title>
    <link rel="stylesheet" type="text/css" href="/sell/css/zui.css"/>
    <link rel="stylesheet" type="text/css" href="/sell/css/gerenzhongxin.css"/>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
    <script src="/sell/js/jquery-1.10.0.js" type="text/javascript" charset="utf-8"></script>
    <script src="/sell/js/gerenzhongxin.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
<div class="pet-1">
    <span class="pet-1-1"><marquee direction="up" scrollamount="3" style="width: 200px;">欢迎来到网上宠物商城</marquee></span>
    <span class="pet-1-2">
        <#if (buyerInfo)??>
            <a href="/sell/buyer/product/web/list">首页</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="/sell/buyer/manage">个人管理</a>&nbsp;&nbsp;|&nbsp;&nbsp;${buyerInfo.username}&nbsp;&nbsp;|&nbsp;&nbsp;<a href="/sell/buyer/logout">退出</a>
        </#if>
    </span>
</div>
<div class="grzx-a">
    <ul>
        <li class="dianjiul" onclick="changdiv(1);">账号信息</li>
        <li class="dianjiul" onclick="changdiv(2);">地址管理</li>
        <li class="dianjiul" onclick="changdiv(3);">查看订单</li>
        <li class="dianjiul" onclick="changdiv(4);">我的购物车</li>
    </ul>
</div>
<div class="grzx-b" id="di1">
    <table style="border: none; width: 200px; height: 200px;">
        <tbody>
        <tr><td>用户名:</td><td>${buyerInfo.username}</td></tr>
        <tr><td>真实姓名:</td><td>${buyerInfo.userRelName}</td></tr>
        <tr><td>密保问题:</td><td>${buyerInfo.relQuestion}</td></tr>
        </tbody>
    </table>
</div>
<div class="grzx-b" id="di2">
    <button class="btn btn-primary" type="button" onclick="addshow()">新增地址</button>
    <table class="table">
        <thead>
        <tr>
            <th>收货人</th>
            <th>收货地址</th>
            <th>收货号码</th>
            <th>设置为默认</th>
            <th>删除地址</th>
        </tr>
        </thead>
        <tbody>
        <#list buyerAddressList as buyerAddress>
            <tr>
                <td>${buyerAddress.receiver}</td>
                <td>${buyerAddress.receiptAddress}</td>
                <td>${buyerAddress.receiptPhone}</td>
                <td>
                    <#if buyerAddress.defaultFlag == 'Y'>
                        <span>默认地址</span>
                    <#else>
                        <a href="/sell/buyer/address/setDefaultAddress?addressId=${buyerAddress.addressId}" class="btn btn-primary" type="button">设置默认地址</a>
                    </#if>
                </td>
                <td>
                    <a href="/sell/buyer/address/delete?addressId=${buyerAddress.addressId}" class="btn btn-warning" type="button">删除地址</a>
                </td>
            </tr>
        </#list>
    </table>
</div>
<div class="grzx-b" id="di3">
    <div id="page-content-wrapper">
        <div class="container">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>订单编号</th>
                            <th>姓名</th>
                            <th>手机号</th>
                            <th>地址</th>
                            <th>金额</th>
                            <th>订单状态</th>
                            <th>支付状态</th>
                            <th>创建时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                            <#list orderDTOPage.getContent() as orderDTO>
                            <tr>
                                <td>${orderDTO.orderId}</td>
                                <td>${orderDTO.buyerName}</td>
                                <td>${orderDTO.buyerPhone}</td>
                                <td>${orderDTO.buyerAddress}</td>
                                <td>${orderDTO.orderAmount}</td>
                                <td>${orderDTO.getOrderStatusEnum().msg}</td>
                                <td>${orderDTO.getOrderPayEnum().msg}</td>
                                <td>${orderDTO.createTime}</td>
                                <td width="65px">
                                    <a href="/sell/buyer/order/detail1?openid=${buyerInfo.buyerId}&orderId=${orderDTO.orderId}">详情</a>
                                </td>
                                <td width="65px">
                                    <#if orderDTO.getOrderStatusEnum().getCode() == 0>
                                        <a href="/sell/buyer/order/cancel1?openid=${buyerInfo.buyerId}&orderId=${orderDTO.orderId}">取消</a>
                                    </#if>
                                </td>
                            </tr>
                            </#list>
                        </tbody>
                    </table>
                    <!-- 分页 -->
                    <ul class="pagination pull-right">
                        <#if currentPage lte 1>
                            <li class="disabled">
                                <a href="#">上一页</a>
                            </li>
                        <#else>
                            <li>
                                <a href="/sell/seller/order/list?page=${currentPage - 1}&size=${currentSize}">上一页</a>
                            </li>
                        </#if>
                        <#list 1..orderDTOPage.getTotalPages() as index>
                            <#if currentPage == index>
                                <li class="disabled">
                                    <a href="#">${index}</a>
                                </li>
                            <#else>
                                <li>
                                    <a href="/sell/seller/order/list?page=${index}&size=${currentSize}">${index}</a>
                                </li>
                            </#if>
                        </#list>
                        <#if currentPage gte orderDTOPage.getTotalPages()>
                            <li class="disabled">
                                <a href="#">下一页</a>
                            </li>
                        <#else>
                            <li>
                                <a href="/sell/seller/order/list?page=${currentPage + 1}&size=${currentSize}">下一页</a>
                            </li>
                        </#if>
                    </ul>
                </div>


            </div>

        </div>
    </div>
</div>
<div class="grzx-b" id="di4">
    <table class="table table-bordered">
        <thead>
            <tr>
                <th></th>
                <th>商品名称</th>
                <th>商品图片</th>
                <th>单价</th>
                <th>数量</th>
                <th>小计</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
        <#list shoppingCartList as shoppingCart>
            <#list productInfoList as productInfo>
                <#if shoppingCart.getProductId() == productInfo.getProductId()>
                    <tr>
                        <td>
                            <input name="check" type="checkbox" value="${shoppingCart.getCartId()}">
                        </td>
                        <td>${productInfo.getProductName()}</td>
                        <td>
                            <img src="${productInfo.getProductIcon()}" alt="小图" width="100" height="100">
                        </td>
                        <td>${productInfo.getProductPrice()}</td>
                        <td>
                            <input id="id_${shoppingCart.getCartId()}" style="width: 50px;" type="number" value="${shoppingCart.getProductNumber()}"/>
                        </td>
                        <td>${productInfo.getProductPrice() * shoppingCart.getProductNumber()}</td>
                        <td>
                            <a href="/sell/buyer/cart/delete?cartId=${shoppingCart.getCartId()}">删除</a>
                        </td>
                    </tr>
                </#if>
            </#list>
        </#list>
    </table>
    <#if (shoppingCartList)??>
        <a onclick="createOrder()" type="button" class="btn btn-default btn-primary">下单</a>
    </#if>
</div>

<!--
    描述：新增弹出层
-->
<div id="add" class="add" style="width: 100%;height: 100%;background-color:#000;position: absolute;top: 0px;z-index=1000; opacity: 1; display: none; margin-top: 36px;">
    <div class="add-1" style="width: 325px;height: 350px;padding: 63px 0 43px 58px;background-color: white; z-index: 10;position: relative;margin: 0 auto;">
        <div class="add-1-2" style="width: 24px;height: 24px;cursor: pointer;position: absolute;top: 0px; right: 0px;">
            <i class="icon icon-times icon-2x" onclick="addshowdn();"></i>
        </div>
        <form action="/sell/buyer/address/save" method="post">
            <div class="form-group">
                <label for="exampleInputAccount1">收货人</label>
                <input name="receiver" type="text" class="form-control" id="exampleInputAccount1" placeholder="收件人姓名" style="width: 200px;">
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">收货号码</label>
                <input name="receiptPhone" type="text" class="form-control" id="exampleInputAccount1" placeholder="收件人号码" style="width: 200px;">
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">详细地址</label>
                <input name="receiptAddress" type="text" class="form-control" id="exampleInputPassword1" placeholder="详细收货地址" style="width: 200px;">
            </div>
            <input name="buyerId" value="${buyerInfo.buyerId}" hidden>
            <button type="submit" class="btn btn-primary">提交</button>
        </form>
    </div>
</div>
</body>
<script type="text/javascript">
    function createOrder(){
        var items = "[";
        $("input[type=checkbox]:checked").each(function(){
            var cart_id = $(this).val();
            <#list shoppingCartList as shoppingCart>
                var shoppingCart_id = ${shoppingCart.getCartId()};
                if(cart_id == shoppingCart_id){
                    items += "{productId:${shoppingCart.getProductId()},productQuantity:"+document.getElementById('id_'+cart_id).value+"},";
                }
            </#list>
        });
        items = items.substring(0,items.length-1);
        items += "]";
        $.ajax({
            type : "post",
            url : "/sell/buyer/order/create2",
            data : {
                "username" :"${buyerInfo.getUsername()!''}",
                "items" : items
            },
            dataType : 'json',
            success : function(data) {
                if (data && data.code == 0) {
                    window.location.href="/sell/buyer/manage";
                } else {
                    alert("失败，原因：" + data.msg);
                }
            },
            error : function(XMLHttpRequest, textStatus, errorThrown) {//请求的失败的返回的方法
                alert("失败，请稍后再次尝试删除！");
            }
        });
    }
</script>
</html>