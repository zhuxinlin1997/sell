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
                                <th>商品编号</th>
                                <th>商品名称</th>
                                <th>单价</th>
                                <th>库存</th>
                                <th>描述</th>
                                <th>小图</th>
                                <th>商品状态</th>
                                <th>类目编号</th>
                                <th>创建时间</th>
                                <th>最后修改时间</th>
                                <th colspan="2">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list productInfoPage.getContent() as productInfo>
                            <tr>
                                <td>${productInfo.productId}</td>
                                <td>${productInfo.productName}</td>
                                <td>${productInfo.productPrice}</td>
                                <td>${productInfo.productStock}</td>
                                <td>${productInfo.productDescription}</td>
                                <td>
                                    <img src="${productInfo.productIcon}" alt="商品小图" width="100px" height="100px">
                                </td>
                                <td>${productInfo.getProductStatusEnum().getMessage()}</td>
                                <td>${productInfo.categoryType}</td>
                                <td>${productInfo.createTime}</td>
                                <td>${productInfo.updateTime}</td>
                                <td width="65px">
                                    <a href="/sell/seller/product/index?productId=${productInfo.productId}">修改</a>
                                </td>
                                <td width="65px">
                                    <#if productInfo.getProductStatusEnum().getCode() == 0>
                                        <a href="/sell/seller/product/on_off_sale?productId=${productInfo.productId}">下架</a>
                                    <#else>
                                        <a href="/sell/seller/product/on_off_sale?productId=${productInfo.productId}" style="color: red;">上架</a>
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
                                <a href="/sell/seller/product/list?page=${currentPage - 1}&size=${currentSize}">上一页</a>
                            </li>
                        </#if>
                        <#list 1..productInfoPage.getTotalPages() as index>
                            <#if currentPage == index>
                                <li class="disabled">
                                    <a href="#">${index}</a>
                                </li>
                            <#else>
                                <li>
                                    <a href="/sell/seller/product/list?page=${index}&size=${currentSize}">${index}</a>
                                </li>
                            </#if>
                        </#list>
                        <#if currentPage gte productInfoPage.getTotalPages()>
                            <li class="disabled">
                                <a href="#">下一页</a>
                            </li>
                        <#else>
                            <li>
                                <a href="/sell/seller/product/list?page=${currentPage + 1}&size=${currentSize}">下一页</a>
                            </li>
                        </#if>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>


    </body>
</html>