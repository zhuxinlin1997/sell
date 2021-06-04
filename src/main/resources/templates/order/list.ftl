    <#include "../common/header.ftl" />
    <div id="wrapper" class="toggled">
        <#-- 边框栏 -->
        <#include "../common/nav.ftl" />

         <#--主要内容-->
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
                                    <a href="/sell/seller/order/detail?orderId=${orderDTO.orderId}">详情</a>
                                </td>
                                <td width="65px">
                                        <#if orderDTO.getOrderStatusEnum().getCode() == 0>
                                            <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}">取消</a>
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

        <div class="modal fade" id="modal-container-808977" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h4 class="modal-title" id="myModalLabel">
                            标题
                        </h4>
                    </div>
                    <div class="modal-body">
                        内容...
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button> <button type="button" class="btn btn-primary">保存</button>
                    </div>
                </div>

            </div>

        </div>

    </div>
    <#--<script>-->
        <#--var websocket = null;-->
        <#--if('WebSocket' in window){-->
            <#--websocket = new WebSocket("ws://localhost:8010/sell/webSocket");-->
        <#--}else{-->
            <#--alert("该浏览器不支持websocket!");-->
        <#--}-->
        <#--websocket.onopen = function(event){-->
            <#--console.log('建立连接');-->
        <#--}-->
        <#--websocket.onclose = function(event){-->
            <#--console.log('连接关闭');-->
        <#--}-->
        <#--websocket.onmessage = function(event){-->
            <#--console.log('收到消息:'+event.data);-->
        <#--}-->
        <#--websocket.onerror = function(){-->
            <#--alert('websocket通信发送错误');-->
        <#--}-->
        <#--window.onbeforeunload = function(){-->
            <#--websocket.close();-->
        <#--}-->
    <#--</script>-->

    </body>
</html>