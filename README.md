# sell
**spring boot项目小Demo，适合Java初学者学习使用，也可以做毕设**

## 案例介绍
此案例非常简单，主要实现了简易的电商平台，分为商家端和用户端，分为了商家信息管理，商品管理，类目管理，订单管理等功能
用户端分为了用户管理，商品查询，下单，地址管理等功能。

## 技术介绍
前端使用bootStrap，freemarker编写，主要使用了Spring boot进行构建，持久层使用的是Spring JPA快速编写，另外使用了Redis做缓存和session储存，项目中使用了拦截器做了统一异常处理和登录token统一校验。

## 项目运行
运行此案例，需要先创建个名称为:sell的数据库，然后运行 src/main/resources/db/sell.sql 脚本初始化数据库。
另外注意，代码中使用了lombok，idea打开的话，需要安装下lombok插件。

项目运行后，商家端访问：http://ip:port/sell/seller/order 会自动跳转至商家登录界面，用户端访问：http://ip:port/sell/buyer/product/list 会自动跳转至用户登录界面

## 联系
有问题可以通过QQ联系，QQ：865302279
