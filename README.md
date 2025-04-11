<h1 id="PUyat">项目详情</h1>
<h2 id="CSSVD">系统角色</h2>

本套《在线旅游系统》，一共设计了三个角色：管理员、用户、供应商。

其中，管理员承担全站信息管理任务；用户为服务或产品使用方(景点、买票、攻略、订房等多种服务使用)，下文做出详解。供应商为服务或产品的提供方。

角色数据流逻辑如下：

:::info

1. 用户信息由经用户注册，注册成功后可凭借个人身份信息登录《在线旅游系统》，并可对个人信息进行管理、使用系统服务或产品。

:::

:::info

2. 供应商身份由用户注册申请，经由管理员审核后，可发布及管理景点或酒店相关配套服务。这类服务一经发布，由管理员审核通过后才可对外展出。

:::

:::info

3. 用户身份或供应商身份状态，受管理员管理。用户或供应商状态异常(封号、禁言、停用)，功能受限，暂停相对应的操作能力。

:::

<h2 id="IyizV">功能模块划分</h2>
<h2 id="TEFRx">数据库表介绍</h2>

《在线旅游系统》，一共设计了15张数据库表。数据库表清单如下:

1. 景点信息表
2. 景点路线表
3. 景点评分表
4. 服务商/供应商表
5. 评论表
6. 景点攻略表
7. 用户信息表
8. 景点酒店表
9. 酒店房间信息表
10. 酒店床位信息表
11. 酒店预约订单表
12. 景点门票信息表
13. 景点订票信息表
14. 景点分类表
15. 公告表

<h2 id="rN1BM">功能模块划分</h2>

上述已经给出了数据库表简述，在这里对功能模块做一个划分，可划分为7大功能模块:

1. 用户模块
2. 景点模块
3. 景点攻略模块
4. 订单模块
5. 评论模块
6. 酒店模块
7. 管理员模块

<h2 id="ijJ8s">功能页面</h2>

![](https://cdn.nlark.com/yuque/0/2024/png/32649796/1731836933346-d1b7ccce-2673-4d0f-9862-827b36cf6ac8.png)

**注册页面**

![](https://cdn.nlark.com/yuque/0/2024/png/32649796/1731836921577-ff0662f6-967e-46da-878b-7bec72de2a5b.png)

**登录页面**

![](https://cdn.nlark.com/yuque/0/2024/png/32649796/1731837038756-e8341287-a56c-4b59-a9f8-b5df0c1873a2.png)

**修改个人信息**

![](https://cdn.nlark.com/yuque/0/2024/png/32649796/1731837049115-3160954a-7fb2-4c4d-9e91-dce03b28e71f.png)

**修改密码**

![](https://cdn.nlark.com/yuque/0/2024/png/32649796/1731836972858-ea6a7bb4-ecd1-4655-8e74-28322434ef5e.png)

**封号禁言及用户管理**

![](https://cdn.nlark.com/yuque/0/2024/png/32649796/1731837245113-60a6d947-ff67-4d5a-9b23-18b4829d39e4.png)

**供应商管理**

![](https://cdn.nlark.com/yuque/0/2024/png/32649796/1731837274962-eda7fbac-7b46-4b17-83ca-86859b96df93.png)

**景点管理**

![](https://cdn.nlark.com/yuque/0/2024/png/32649796/1731837343815-e4f4519f-fd61-45ad-89ab-5d91cfc16b2a.png)

**景点修改**

![](https://cdn.nlark.com/yuque/0/2024/png/32649796/1731837317805-f7ffdf59-5312-4e02-afc9-765f1a4e7ac5.png)

**景点路线管理**

![](https://cdn.nlark.com/yuque/0/2024/png/32649796/1731837359787-b9e16f90-b8f9-4eba-8a7c-f57e24470596.png)

**门票管理**

![](https://cdn.nlark.com/yuque/0/2024/png/32649796/1731837384378-769caf33-716f-4cf5-8675-b2e11831f93e.png)

**门票订单**

![](https://cdn.nlark.com/yuque/0/2024/png/32649796/1731837401485-ddeddb41-84e8-4c82-b68d-2b3b35ca50d1.png)

**攻略管理**

![](https://cdn.nlark.com/yuque/0/2024/png/32649796/1731837435322-ef4e4ee7-e819-4c0a-a31d-51260db30b49.png)

**酒店管理**

![](https://cdn.nlark.com/yuque/0/2024/png/32649796/1731837455579-ca0b2f6f-7736-4273-a7ed-5475b3ca11ec.png)

**酒店房间管理**

![](https://cdn.nlark.com/yuque/0/2024/png/32649796/1731837475853-6a98cd9d-ea8b-420f-af89-c0eb7ead8bf5.png)

**酒店订单管理**

![](https://cdn.nlark.com/yuque/0/2024/png/32649796/1731837494463-e08e9ded-48ff-4da8-8875-e25902a9853c.png)

**分类管理**

![](https://cdn.nlark.com/yuque/0/2024/png/32649796/1731837517906-0cf12fcf-dd61-48c1-84c0-6ba53119b85e.png)

**评论管理**

![](https://cdn.nlark.com/yuque/0/2024/png/32649796/1731837536745-18ddd8fb-620f-46d3-b73a-e4aeffb66d95.png)

**公告管理**

![](https://cdn.nlark.com/yuque/0/2024/png/32649796/1731837569054-f36ed358-fee6-4c43-b2fb-785c6acda977.png)

**用户首页**

![](https://cdn.nlark.com/yuque/0/2024/png/32649796/1731837590002-dc9f37cc-afbc-4a7c-9038-72ab5692c805.png)

景点详情

![](https://cdn.nlark.com/yuque/0/2024/png/32649796/1731837606006-c1566a87-4ac6-4a95-afe7-6b2fa25a8f78.png)

景点评分

![](https://cdn.nlark.com/yuque/0/2024/png/32649796/1731837634998-fa381419-1d88-471e-94c0-d9852434e926.png)

门票列表

![](https://cdn.nlark.com/yuque/0/2024/png/32649796/1731837619278-b979fbbb-fcb2-4d2c-a19b-9e9996c3250f.png)

购票

![](https://cdn.nlark.com/yuque/0/2024/png/32649796/1731837663342-d0268d33-ad3d-4b35-8832-3ea6713b38a1.png)

攻略

![](https://cdn.nlark.com/yuque/0/2024/png/32649796/1731837675631-45bf00c7-6ce4-43c1-aa52-9403f09dea6f.png)

攻略阅读

![](https://cdn.nlark.com/yuque/0/2024/png/32649796/1731837686642-9d04c894-925b-44ec-9410-26cc2759c219.png)

酒店

![](https://cdn.nlark.com/yuque/0/2024/png/32649796/1731837723062-516f1511-a18f-4c83-b740-3a3f654146bf.png)

酒店详情

![](https://cdn.nlark.com/yuque/0/2024/png/32649796/1731837729859-070d13f5-377a-4d0c-b1b1-3974e2d49abe.png)

门票订单

![](https://cdn.nlark.com/yuque/0/2024/png/32649796/1731837740334-f4be9783-c90d-48ad-8ffb-a9d3b0d1ad5b.png)

消费记录

![](https://cdn.nlark.com/yuque/0/2024/png/32649796/1731837758574-7a991d5f-af66-4251-a023-bd96ecc85d6e.png)

酒店订单

![](https://cdn.nlark.com/yuque/0/2024/png/32649796/1731837770628-e9489368-236d-4639-97a4-ce2300827b8d.png)

公告

![](https://cdn.nlark.com/yuque/0/2024/png/32649796/1731837782204-44337d08-b51f-4a22-b5e2-337ffa8623a9.png)

内容中心

![](https://cdn.nlark.com/yuque/0/2024/png/32649796/1731837798617-6bc83038-8d3f-4b58-bd3c-e0d734b13c54.png)

攻略修改

![](https://cdn.nlark.com/yuque/0/2024/png/32649796/1731837814721-b0bc4679-4d92-4e44-8aa8-e7815b4ac8dd.png)

景点收藏

![](https://cdn.nlark.com/yuque/0/2024/png/32649796/1731837826843-0c9e4927-ea1a-4628-ba5f-47818b08abfc.png)

服务中心

![](https://cdn.nlark.com/yuque/0/2024/png/32649796/1731837844426-c2b2844f-4eb5-4227-8009-7b5baffd3768.png)

供应商景区信息

![](https://cdn.nlark.com/yuque/0/2024/png/32649796/1731837863712-a02011c9-0b74-49c8-a0f7-fbe40ec90498.png)

供应商酒店信息

![](https://cdn.nlark.com/yuque/0/2024/png/32649796/1731837905753-b471a583-135c-47fd-9970-dc918430aa2b.png)

供应商景点门票管理

![](https://cdn.nlark.com/yuque/0/2024/png/32649796/1731837923722-babc17ab-0174-4dce-9f83-7d6537dcdb3e.png)

供应酒店房间管理



<h1 id="Tekvt">创新点在何处？</h1>

1. 丰富的可视化图标统计。
2. 多表关联设计结构。
3. 高度灵活且强大的评论组件设计。
4. 功能完备且美观的内容发布模块设计。





