<#include "./header.ftl">
<div class="pet-4">
    <img src="${productInfo.getProductIcon()}" alt="商品图片" width="350" height="350">
</div>
<div class="pet-5">
    <form action="/sell/buyer/cart/add" method="post">
        <div class="pet-5-1">
            <span>
                <b>描述：</b>
                ${productInfo.getProductDescription()}
            </span>
        </div>
        <div class="pet-5-2">
            <span>单价：</span>
            <h2>￥${productInfo.getProductPrice()}</h2>
        </div>
        <div class="pet-5-3">
            <span>数量：</span>
            <button class="reduce" type="button">-</button><input name="productNumber" style="width: 50px;" onchange="cg()" type="number" value="1" id="aa"/><button type="button" class="add">+</button>
        </div>
        <input name="productId" value="${productInfo.getProductId()}" hidden >
        <div class="pet-5-4">
            <button class="btn btn-success" type="submit">加入购物车</button>
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#mySmModal">直接购买</button>
        </div>
    </form>
</div>
<div class="modal fade" id="mySmModal">
    <div style="padding: 20px;width: 300px;" class="modal-dialog modal-ms">
        <div class="modal-content" style="height: 120px; text-align: center;">
            <span>提示</span><h2>您是否需要生成订单</h2>
            <a onclick="createOrder()" class="btn btn-success">确认</a>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    function createOrder(){
        <#if !(username)??>
            window.location.href="/sell/buyer/manage";
            return;
        </#if>
        $.ajax({
            type : "post",
            url : "/sell/buyer/order/create1",
            data : {
                "username" :"${username!''}",
                "items" : "[{productId: ${productInfo.productId},productQuantity:"+document.getElementById('aa').value+"}]"
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
    function cg(){
        var a =document.getElementById('aa').value;
        if(parseInt(a)<1){
            document.getElementById('aa').value=1;
            return;
        }
        document.getElementById('aa').value=parseInt(a);
    }
    $('.reduce').click(function(){
        var a =document.getElementById('aa').value;
        if(parseInt(a)==1){
            return;
        }
        var num=parseInt(a)-1;
        document.getElementById('aa').value=num;
    });
    $('.add').click(function(){
        var a =document.getElementById('aa').value;
        var num=parseInt(a)+1;
        document.getElementById('aa').value=num;
    });
</script>
</html>
