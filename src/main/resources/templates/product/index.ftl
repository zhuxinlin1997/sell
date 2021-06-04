    <#include "../common/header.ftl" />
    <div id="wrapper" class="toggled">
        <#-- 边框栏 -->
        <#include "../common/nav.ftl" />

         <#--主要内容-->
        <div id="page-content-wrapper">
            <div class="container">
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <a href="/sell/seller/product/list" type="button" class="btn btn-default btn-info">返回</a>
                    </div>
                </div>
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <form role="form" method="post" action="/sell/seller/product/save">
                            <div class="form-group">
                                <label>商品名称</label>
                                <input name="productName" value="${(productInfo.productName)!''}" type="text" class="form-control" />
                            </div>
                            <div class="form-group">
                                <label>单价</label>
                                <input name="productPrice" value="${(productInfo.productPrice)!''}" type="number" class="form-control" />
                            </div>
                            <div class="form-group">
                                <label>库存</label>
                                <input name="productStock" value="${(productInfo.productStock)!''}" type="number" class="form-control" />
                            </div>
                            <div class="form-group">
                                <label>描述</label>
                                <input name="productDescription" value="${(productInfo.productDescription)!''}" type="text" class="form-control" />
                            </div>
                            <div class="form-group">
                                <label>小图</label>
                                <input name="productIcon" value="${(productInfo.productIcon)!''}" type="text" class="form-control" />
                                <img src="${(productInfo.productIcon)!''}" width="200" height="200" alt="小图">
                            </div>
                            <div>
                                <label>商品状态</label>
                                <input type="radio" name="productStatus" value="0"
                                <#if !(productInfo.productStatus)?? || (productInfo.productStatus)?? && productInfo.productStatus == 0>
                                    checked="checked"
                                </#if>>上架
                                <input type="radio" name="productStatus" value="1"
                                <#if (productInfo.productStatus)?? && productInfo.productStatus == 1>
                                    checked="checked"
                                </#if>>下架
                            </div>
                            <div class="form-group">
                                <label>商品类目</label>
                                <select name="categoryType" class="form-group">
                                    <#list productCategoryList as productCategory>
                                        <#if (productInfo.categoryType)?? && productCategory.categoryType == productInfo.categoryType>
                                            <option value="${productCategory.categoryType}" selected = "selected">${productCategory.categoryName}</option>
                                        <#else>
                                            <option value="${productCategory.categoryType}">${productCategory.categoryName}</option>
                                        </#if>
                                    </#list>
                                </select>
                            </div>
                            <input name="productId" value="${(productInfo.productId)!''}" type="hidden" class="form-control" />
                            <button type="reset" class="btn btn-warning btn-default">重置</button>
                            <button type="submit" class="btn btn-default btn-primary">提交</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </body>
</html>