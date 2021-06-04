    <#include "../common/header.ftl" />
    <div id="wrapper" class="toggled">
        <#-- 边框栏 -->
        <#include "../common/nav.ftl" />

         <#--主要内容-->
        <div id="page-content-wrapper">
            <div class="container">
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <a href="/sell/seller/category/list" type="button" class="btn btn-default btn-info">返回</a>
                    </div>
                </div>
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <form role="form" method="post" action="/sell/seller/category/save">
                            <div class="form-group">
                                <label>类目名称</label>
                                <input name="categoryName" value="${(productCategory.categoryName)!''}" type="text" class="form-control" />
                            </div>
                            <div class="form-group">
                                <label>类目编号</label>
                                <input name="categoryType" value="${(productCategory.categoryType)!''}" type="number" class="form-control" />
                            </div>
                            <input name="categoryId" value="${(productCategory.categoryId)!''}" type="hidden" class="form-control" />
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