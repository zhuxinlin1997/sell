<#include "../common/header.ftl" />

        <div id="wrapper" class="toggled">
            <#-- 边框栏 -->
            <#include "../common/nav.ftl" />
            <#--主要内容-->
            <div class="container">
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <a href="/sell/seller/order/list" type="button" class="btn btn-default btn-info">返回</a>
                    </div>
                </div>
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <table class="table">
                            <thead>
                            <tr>
                                <th>订单号</th>
                                <th>买家名字</th>
                                <th>买家电话</th>
                                <th>买家地址</th>
                                <th>订单总金额</th>
                                <th>订单状态</th>
                                <th>支付状态</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>${orderDTO.orderId}</td>
                                <td>${orderDTO.buyerName}</td>
                                <td>${orderDTO.buyerPhone}</td>
                                <td>${orderDTO.buyerAddress}</td>
                                <td>${orderDTO.orderAmount}</td>
                                <td>${orderDTO.getOrderStatusEnum().getMsg()}</td>
                                <td>${orderDTO.getOrderPayEnum().getMsg()}</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>订单子编号</th>
                                <th>商品小图</th>
                                <th>商品名称</th>
                                <th>单价</th>
                                <th>商品数量</th>
                                <th>价格</th>
                            </tr>
                            </thead>
                            <tbody>
                        <#list orderDTO.getOrderDetailList() as orderDetail>
                        <tr>
                            <td>${orderDetail.detailId}</td>
                            <td>
                                <img src="${orderDetail.productIcon}" alt="商品小图" width="100px" height="100px">
                            </td>
                            <td>${orderDetail.productName}</td>
                            <td>${orderDetail.productPrice}</td>
                            <td>${orderDetail.productQuantity}</td>
                            <td>${orderDetail.productPrice * orderDetail.productQuantity}</td>
                        </tr>
                        </#list>
                            </tbody>
                        </table>
                        <div class="row clearfix">
                            <div class="col-md-12 column">
                        <#if orderDTO.getOrderStatusEnum().getCode() == 0>
                            <a href="/sell/seller/order/finish?orderId=${orderDTO.orderId}" type="button" class="btn btn-default btn-primary">完成订单</a>
                            <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}" type="button" class="btn btn-default btn-danger">取消订单</a>
                        </#if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>