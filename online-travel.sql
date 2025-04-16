/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 80030
 Source Host           : localhost:3306
 Source Schema         : online-travel

 Target Server Type    : MySQL
 Target Server Version : 80030
 File Encoding         : 65001

 Date: 10/04/2025 14:32:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for evaluations
-- ----------------------------
DROP TABLE IF EXISTS `evaluations`;
CREATE TABLE `evaluations`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` int(0) NULL DEFAULT NULL COMMENT '父级评论ID',
  `commenter_id` int(0) NULL DEFAULT NULL COMMENT '评论者ID',
  `replier_id` int(0) NULL DEFAULT NULL COMMENT '回复者ID',
  `content_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '内容类型',
  `content_id` int(0) NULL DEFAULT NULL COMMENT '内容ID',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '评论内容',
  `upvote_list` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '点赞列表，以\",\"分割',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '评论时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 147 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of evaluations
-- ----------------------------
INSERT INTO `evaluations` VALUES (138, NULL, 25, NULL, 'HOTEL', 8, '服务到位，好评！', NULL, '2025-04-06 21:23:22');
INSERT INTO `evaluations` VALUES (139, NULL, 16, NULL, 'STRATEGY', 9, '棒👍', '25', '2025-04-06 22:56:22');
INSERT INTO `evaluations` VALUES (140, 139, 25, NULL, 'STRATEGY', 9, '必须的', '18', '2025-04-06 22:56:41');
INSERT INTO `evaluations` VALUES (141, NULL, 25, NULL, 'STRATEGY', 10, '牛逼，太棒了！', '26', '2025-04-07 01:32:49');
INSERT INTO `evaluations` VALUES (142, 141, 16, NULL, 'STRATEGY', 10, '哈哈哈哈哈', '26', '2025-04-07 01:33:04');
INSERT INTO `evaluations` VALUES (143, NULL, 24, NULL, 'SCENIC', 14, '非常漂亮！', '24', '2025-04-07 17:45:39');
INSERT INTO `evaluations` VALUES (144, NULL, 24, NULL, 'STRATEGY', 10, '可以，非常棒', '24', '2025-04-07 17:48:03');
INSERT INTO `evaluations` VALUES (145, 141, 24, NULL, 'STRATEGY', 10, '哈哈哈', '24', '2025-04-07 17:48:13');
INSERT INTO `evaluations` VALUES (146, NULL, 24, NULL, 'HOTEL', 9, '服务到位！', NULL, '2025-04-07 17:52:51');
INSERT INTO `evaluations` VALUES (147, NULL, 16, NULL, 'SCENIC', 15, '6666', NULL, '2025-04-08 15:50:18');

-- ----------------------------
-- Table structure for hotel
-- ----------------------------
DROP TABLE IF EXISTS `hotel`;
CREATE TABLE `hotel`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '酒店的主键ID，自增',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '酒店名称',
  `cover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '酒店封面',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '酒店地址',
  `concat_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '酒店联系电话',
  `vendor_id` int(0) NULL DEFAULT NULL COMMENT '供应商/服务商ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hotel
-- ----------------------------
INSERT INTO `hotel` VALUES (7, '北京王府井大酒店', '/api/online-travel-sys/v1.0/file/getFile?fileName=e13003c31268797_1.jpg', '北京市东城区王府井大街123号', '010-8765-4321', 6, '2025-04-06 17:22:02');
INSERT INTO `hotel` VALUES (8, '天津丽思卡尔顿酒店', '/api/online-travel-sys/v1.0/file/getFile?fileName=d98d9e4R-C.jpg', '天津市和平区大沽北路167号', '022-2330-8888', 7, '2025-04-06 17:40:44');
INSERT INTO `hotel` VALUES (9, '西湖君悦酒店', '/api/online-travel-sys/v1.0/file/getFile?fileName=3b6262f7A94FC69AD0F4C58ACE58DBA2C7B90F2.jpg', '杭州市西湖区湖滨路28号', '0571-8888-6666', 8, '2025-04-07 17:37:48');
INSERT INTO `hotel` VALUES (10, '灵隐安缦度假村', '/api/online-travel-sys/v1.0/file/getFile?fileName=d59258bff441b36.png', '杭州市西湖区法云弄22号', '0571-7777-5555', 8, '2025-04-07 17:40:19');

-- ----------------------------
-- Table structure for hotel_order_info
-- ----------------------------
DROP TABLE IF EXISTS `hotel_order_info`;
CREATE TABLE `hotel_order_info`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '酒店预约订单主键ID，自增',
  `room_id` int(0) NULL DEFAULT NULL COMMENT '房间ID',
  `user_id` int(0) NULL DEFAULT NULL COMMENT '用户ID',
  `concat_person` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '联系人',
  `concat_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '联系电话',
  `amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '总金额',
  `pay_status` tinyint(1) NULL DEFAULT NULL COMMENT '预约订单的状态',
  `pay_time` datetime(0) NULL DEFAULT NULL COMMENT '支付时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hotel_order_info
-- ----------------------------
INSERT INTO `hotel_order_info` VALUES (8, 7, 25, '巴巴托斯', '11-111-1111', 178.11, 1, '2025-03-13 21:36:52', '2025-03-13 20:24:04');
INSERT INTO `hotel_order_info` VALUES (11, 6, 25, '巴巴托斯', '11-111-1111', 195.72, 1, '2025-04-07 00:05:01', '2025-04-07 00:04:56');
INSERT INTO `hotel_order_info` VALUES (12, 6, 26, '芭芭拉', '11-111-2222', 195.72, 1, '2025-04-07 01:29:17', '2025-04-07 01:27:34');
INSERT INTO `hotel_order_info` VALUES (14, 5, 26, '芭芭拉', '11-111-2222', 169.02, 1, '2025-03-28 18:46:30', '2025-03-28 19:48:07');
INSERT INTO `hotel_order_info` VALUES (15, 8, 24, '艾尔海森', '123-111-1233', 159.00, 1, '2025-04-07 17:53:20', '2025-04-07 17:53:11');

-- ----------------------------
-- Table structure for hotel_room
-- ----------------------------
DROP TABLE IF EXISTS `hotel_room`;
CREATE TABLE `hotel_room`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '酒店房间主键ID，自增',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '房间号',
  `hotel_id` int(0) NULL DEFAULT NULL COMMENT '酒店ID',
  `cover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '图片',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `discount` double(10, 2) NULL DEFAULT NULL COMMENT '折扣',
  `detail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '房间描述',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hotel_room
-- ----------------------------
INSERT INTO `hotel_room` VALUES (5, '电竞主题套房', 7, '/api/online-travel-sys/v1.0/file/getFile?fileName=0b9b73705c3dd411.png', 189.90, 8.90, '电竞主题房是游戏爱好者的梦想之地，配备了顶级的游戏电脑和主机，包括高性能显卡、专业级显示器和响应迅速的键盘鼠标套装。此外，房间还配置了舒适的电竞椅和环绕立体声音响系统，为您带来沉浸式的游戏体验。一些房间甚至装饰有流行游戏的主题壁画，增添更多乐趣。', '2025-04-06 17:26:25');
INSERT INTO `hotel_room` VALUES (6, '商务办公套房', 7, '/api/online-travel-sys/v1.0/file/getFile?fileName=199afc60b2c2397a4d55712.png', 219.90, 8.90, '商务办公套房专为商务旅客和数字游民设计，提供高速稳定的无线网络连接、符合人体工程学的办公桌椅以及高清视频会议设备。房间内设有独立的工作区域与休息区，确保工作与休息两不误。部分酒店还提供打印、复印及传真服务，方便您的商务需求。', '2025-04-06 17:34:48');
INSERT INTO `hotel_room` VALUES (7, '电竞主题套房', 8, '/api/online-travel-sys/v1.0/file/getFile?fileName=3d21b3e124f3b505c3dd411.png', 179.90, 9.90, '电竞主题房是游戏爱好者的梦想之地，配备了顶级的游戏电脑和主机，包括高性能显卡、专业级显示器和响应迅速的键盘鼠标套装。此外，房间还配置了舒适的电竞椅和环绕立体声音响系统，为您带来沉浸式的游戏体验。一些房间甚至装饰有流行游戏的主题壁画，增添更多乐趣。', '2025-04-06 17:45:14');
INSERT INTO `hotel_room` VALUES (8, '常规客房', 9, '/api/online-travel-sys/v1.0/file/getFile?fileName=3098aef9ca512f2224.jpg_wh860.jpg', 159.00, NULL, '十分舒适，欢迎入住！', '2025-04-07 17:42:48');
INSERT INTO `hotel_room` VALUES (9, '常规客房', 10, '/api/online-travel-sys/v1.0/file/getFile?fileName=92f7049c1e5cd6ffd1ea913.png', 129.90, NULL, '十分舒适，欢迎入住！', '2025-04-07 17:44:18');

-- ----------------------------
-- Table structure for hotel_room_bed
-- ----------------------------
DROP TABLE IF EXISTS `hotel_room_bed`;
CREATE TABLE `hotel_room_bed`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '酒店房间床位主键ID，自增',
  `number` int(0) NULL DEFAULT NULL COMMENT '床位号',
  `room_id` int(0) NULL DEFAULT NULL COMMENT '房间ID',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '床位是否可用状态',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hotel_room_bed
-- ----------------------------
INSERT INTO `hotel_room_bed` VALUES (17, 1, 5, 1, '2025-04-06 17:33:23');
INSERT INTO `hotel_room_bed` VALUES (18, 2, 5, 1, '2025-04-06 17:33:27');
INSERT INTO `hotel_room_bed` VALUES (19, 1, 7, 1, '2025-04-06 17:45:37');
INSERT INTO `hotel_room_bed` VALUES (20, 1, 6, 1, '2025-04-07 00:03:34');
INSERT INTO `hotel_room_bed` VALUES (21, 1, 8, 1, '2025-04-07 17:44:32');
INSERT INTO `hotel_room_bed` VALUES (22, 1, 9, 1, '2025-04-07 17:44:37');
INSERT INTO `hotel_room_bed` VALUES (23, 2, 9, 1, '2025-04-07 17:44:41');

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '公告信息主键ID，自增',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '标题',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '内容',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (3, '春季特惠！探索世界的限时折扣等你来拿', '<p><span style=\"color: rgb(44, 44, 54); background-color: rgb(255, 255, 255); font-size: 16px;\">随着春天的到来，我们为您准备了一系列精彩纷呈的旅行套餐。不论是向往已久的异国之旅，还是近在咫尺的城市周边游，都能找到令人心动的选择。现在预订，享受超值优惠！</span></p>', '2025-04-07 00:50:11');
INSERT INTO `notice` VALUES (4, '2025年地球日特别活动：绿色出行，环保旅行', '<p><span style=\"color: rgb(44, 44, 54); background-color: rgb(255, 255, 255); font-size: 16px;\">为庆祝第55个地球日，我们携手多家生态友好型酒店和旅行社推出“绿色出行”计划。参与活动，体验可持续旅游的魅力，同时有机会赢取免费旅行机会，让我们一起行动，保护美丽的地球家园。</span></p>', '2025-04-07 00:50:28');
INSERT INTO `notice` VALUES (5, '夏季冒险季即将开启：挑战自我，发现未知', '<p style=\"text-align: start;\">夏天是探险的最佳时节！从登山徒步到潜水冲浪，我们的夏季冒险季提供多样化的户外活动项目。无论你是寻求刺激的极限运动爱好者，还是喜欢宁静自然风光的旅者，这里都有适合你的行程安排。</p>', '2025-04-07 00:51:00');
INSERT INTO `notice` VALUES (6, '全新上线——私人定制旅游服务', '<p><span style=\"color: rgb(44, 44, 54); background-color: rgb(255, 255, 255); font-size: 16px;\">为了满足每位顾客的独特需求，我们隆重推出私人定制旅游服务。只需告诉我们您的梦想旅程细节，我们将为您量身打造独一无二的旅行方案，包括但不限于专属导游、私人交通工具及精选住宿。</span></p>', '2025-04-07 00:51:15');
INSERT INTO `notice` VALUES (7, '会员积分大放送：兑换全球旅行奖励', '<p><span style=\"color: rgb(44, 44, 54); background-color: rgb(255, 255, 255); font-size: 16px;\">感谢您一直以来的支持，现在加入或已经是我们的会员将获得更多积分回馈。使用累积的积分可以轻松兑换机票、酒店住宿以及各种精彩的旅行体验，赶快查看您的账户余额，开始规划下一次难忘的旅行吧！</span></p>', '2025-04-07 00:51:31');

-- ----------------------------
-- Table structure for scenic
-- ----------------------------
DROP TABLE IF EXISTS `scenic`;
CREATE TABLE `scenic`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '景点主键ID、自增的',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '景点名称',
  `category_id` int(0) NULL DEFAULT NULL COMMENT '景点分类ID',
  `cover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '封面',
  `detail` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '景点详情',
  `vendor_id` int(0) NULL DEFAULT NULL COMMENT '供应商ID',
  `save_ids` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '景点收藏者用户ID列表，字符串，用,分割',
  `view_ids` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '景点浏览者用户ID列表，字符串，用,分割',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '景点所在地',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '景点状态',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of scenic
-- ----------------------------
INSERT INTO `scenic` VALUES (12, '故宫博物院', 13, '/api/online-travel-sys/v1.0/file/getFile?fileName=76491a8e83439df8f984b249d56901e67f89d72-1920.jpg', '<p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 16px;\">中国明清两代的皇家宫殿，世界文化遗产</span></p>', 6, '25', '19,25,16,17,26', '北京市东城区景山前街4号', 1, '2025-04-06 01:55:47');
INSERT INTO `scenic` VALUES (13, '天津古文化街', 28, '/api/online-travel-sys/v1.0/file/getFile?fileName=e996a86100u1f000001gqaoa2135_D_10000_1200.jpg', '<p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 16px;\">体验老天津卫的市井风情与泥人张等非遗文化</span></p>', 7, '26,24', '17,25,26,16,24', '天津市南开区通北路', 1, '2025-04-06 17:38:52');
INSERT INTO `scenic` VALUES (14, '西湖风景名胜区', 29, '/api/online-travel-sys/v1.0/file/getFile?fileName=38b4c14d77b728v2-71eaff66299d9d788f2285e62ba0d84e_r.jpg', '<p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 16px;\">\"欲把西湖比西子，淡妆浓抹总相宜\"的经典景观</span></p>', 8, '24', '24', '杭州市西湖区龙井路1号', 1, '2025-04-07 17:25:11');
INSERT INTO `scenic` VALUES (15, '灵隐寺景区', 22, '/api/online-travel-sys/v1.0/file/getFile?fileName=7cdb5f300003.webp', '<p><span style=\"color: rgb(64, 64, 64);\">\"千年古刹，江南禅宗发源地，内有飞来峰石窟造像\"</span></p>', 8, NULL, '18,16', '杭州市西湖区法云弄1号', 1, '2025-04-07 17:27:05');

-- ----------------------------
-- Table structure for scenic_category
-- ----------------------------
DROP TABLE IF EXISTS `scenic_category`;
CREATE TABLE `scenic_category`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '景点分类的主键ID，自增',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '景点分类名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of scenic_category
-- ----------------------------
INSERT INTO `scenic_category` VALUES (12, '自然奇观');
INSERT INTO `scenic_category` VALUES (13, '历史人文');
INSERT INTO `scenic_category` VALUES (14, '文化场馆');
INSERT INTO `scenic_category` VALUES (15, '主题公园');
INSERT INTO `scenic_category` VALUES (16, '城市地标');
INSERT INTO `scenic_category` VALUES (17, '古镇古村');
INSERT INTO `scenic_category` VALUES (18, '生态旅游');
INSERT INTO `scenic_category` VALUES (19, '海洋景观');
INSERT INTO `scenic_category` VALUES (20, '地质公园');
INSERT INTO `scenic_category` VALUES (21, '红色旅游');
INSERT INTO `scenic_category` VALUES (22, '宗教圣地');
INSERT INTO `scenic_category` VALUES (23, '温泉度假');
INSERT INTO `scenic_category` VALUES (24, '自然保护区');
INSERT INTO `scenic_category` VALUES (25, '沙漠景观');
INSERT INTO `scenic_category` VALUES (26, '冰雪旅游');
INSERT INTO `scenic_category` VALUES (27, '摄影胜地');
INSERT INTO `scenic_category` VALUES (28, '民俗文化');
INSERT INTO `scenic_category` VALUES (29, '自然风光');

-- ----------------------------
-- Table structure for scenic_line
-- ----------------------------
DROP TABLE IF EXISTS `scenic_line`;
CREATE TABLE `scenic_line`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '景点路线主键ID，自增',
  `scenic_id` int(0) NULL DEFAULT NULL COMMENT '景点ID',
  `cover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '路线封面图',
  `detail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '路线描述',
  `level` int(0) NULL DEFAULT NULL COMMENT '层级',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of scenic_line
-- ----------------------------
INSERT INTO `scenic_line` VALUES (20, 13, '/api/online-travel-sys/v1.0/file/getFile?fileName=ce9bca6ac6eddc451da81cb39db6294be3ec7160924ab189b6b.webp', '天后宫：\n天后宫原名天妃宫，俗称“娘娘宫”，坐落于天津三岔河口之海河西岸，宫南、宫北大街（今古文化街）正中，始建于元泰定三年（1326年），明永乐元年（1403年）重建，是中国现存年代最久的天妃宫之一。建筑群规模庞大，气势雄伟壮观，庙宇坐西朝东，面向海河，占地面积5352平方米，建筑面积1734平方米。沿中轴线自东向西依次有戏楼、幡杆、山门、牌楼、前殿、大殿、藏经阁、启圣祠。两侧配以钟楼、鼓楼、关帝殿、财神殿、其他配殿及过街楼张仙阁等建筑。', 0, '2025-04-06 17:47:51');
INSERT INTO `scenic_line` VALUES (21, 13, '/api/online-travel-sys/v1.0/file/getFile?fileName=08d97647acb0a46f21fbe096b63b64a87381b338744ebf8076e.webp', '天津民俗博物馆\n天津民俗博物馆保留天后宫大殿及前殿的塑像作复原陈列，征集民俗文物约2000余件，分别在四座配殿和藏经阁、张仙阁开辟了以展示天津民俗民风为内容的基本陈列，介绍了天津漕运兴起、渔盐的发展和习俗；天津民间各路花会特别是“皇会”沿街表演；迎亲嫁娶、夫妻拜堂、洞房花烛和津门独有的“拴娃娃”习俗；天津店铺悬挂于门前的各式招幌；民间生活用品、乡土风情；衣食住行民俗；杨柳青年画、风筝、砖刻、泥塑、绒花、剪纸、编织等民间手工艺品和民间戏曲艺术。', 0, '2025-04-06 17:49:33');
INSERT INTO `scenic_line` VALUES (22, 13, '/api/online-travel-sys/v1.0/file/getFile?fileName=60838d978310a55b319ebc4b745b4c06e7ed8fc1e178a82b86b.webp', '玉皇阁\n玉皇阁位于天津古文化街东北侧，近邻海河，坐西朝东，始建于明洪武元年（1368年），又在明宣德二年（1427）重建。玉皇阁是天津市最早的木结构楼阁。根据中国传统民间习俗，在每年农历九月初九的重阳节，人们都要登高赏菊，饮酒抒怀。', 0, '2025-04-06 17:50:39');
INSERT INTO `scenic_line` VALUES (23, 13, '/api/online-travel-sys/v1.0/file/getFile?fileName=5ae68b4dc54564e9258d109b3de3ce93d00dbbf6c81800ae76d.webp', '通庆里\n通庆里位于天津市古文化街东北侧，西起古文化街，东北口毗邻天津市唯一的道教建筑玉皇阁。据记载，通庆里原址建有少量的平房，天津商人郑元里于民国二年（1913年）购置此地块，并且拆除原先平房，于两侧盖起多幢青砖二层楼房，铺设石路，巷口建起过巷楼，以“通达吉庆”之意命名。通庆里是天津市规模较大的中西合璧式建筑群之一，享有百年民居的称号。据传，通庆里曾是一大银号，采用了“前房经营、后房居住”的形式，楼上居年轻者，楼下住年长者，在其每个院落均发现早已废弃的地下金库，后来这片建筑群被改为了居民住房。', 13, '2025-04-06 17:52:25');
INSERT INTO `scenic_line` VALUES (24, 13, '/api/online-travel-sys/v1.0/file/getFile?fileName=f7ef9989a504fc2d5628535e5dd657d7cb761c6a7efce1bc56c.webp', '天演广场\n古文化街大狮子胡同是严复故居所在地，原房屋已不存在。为挖掘历史文化，先后走访了严复先生的后代和历史专家，结合景观设计，在严复故居原址建设天演广场，设立严复铜像和天演论石刻', 33, '2025-04-06 17:53:09');

-- ----------------------------
-- Table structure for scenic_rating
-- ----------------------------
DROP TABLE IF EXISTS `scenic_rating`;
CREATE TABLE `scenic_rating`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '景点评分主键ID，自增',
  `scenic_id` int(0) NULL DEFAULT NULL COMMENT '景点ID',
  `user_id` int(0) NULL DEFAULT NULL COMMENT '用户ID',
  `score` int(0) NULL DEFAULT NULL COMMENT '评分分数',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of scenic_rating
-- ----------------------------
INSERT INTO `scenic_rating` VALUES (10, 12, 16, 5, '2025-04-06 17:24:43');
INSERT INTO `scenic_rating` VALUES (11, 13, 17, 5, '2025-04-06 17:39:21');
INSERT INTO `scenic_rating` VALUES (12, 13, 25, 5, '2025-04-06 23:04:39');
INSERT INTO `scenic_rating` VALUES (13, 12, 26, 4, '2025-04-07 01:23:57');
INSERT INTO `scenic_rating` VALUES (14, 14, 24, 5, '2025-04-07 17:45:22');
INSERT INTO `scenic_rating` VALUES (15, 15, 18, 4, '2025-04-07 17:49:39');
INSERT INTO `scenic_rating` VALUES (16, 15, 16, 4, '2025-04-08 15:50:11');

-- ----------------------------
-- Table structure for scenic_strategy
-- ----------------------------
DROP TABLE IF EXISTS `scenic_strategy`;
CREATE TABLE `scenic_strategy`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '景点攻略主键ID，自增',
  `user_id` int(0) NULL DEFAULT NULL COMMENT '用户ID',
  `scenic_id` int(0) NULL DEFAULT NULL COMMENT '景点ID',
  `cover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '景点攻略封面',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '景点标题',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '景点攻略内容',
  `is_audit` tinyint(1) NULL DEFAULT NULL COMMENT '是否已经审核',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of scenic_strategy
-- ----------------------------
INSERT INTO `scenic_strategy` VALUES (9, 25, 12, '/api/online-travel-sys/v1.0/file/getFile?fileName=ece91d15d1f90f7d59c88aea4c927c0ffdfc852.jpeg', '穿越六百年的时光——故宫深度游攻略', '<h4 style=\"text-align: start;\">一、前言</h4><p style=\"text-align: start;\">故宫，旧称紫禁城，位于北京市中心，是明、清两代的皇宫，也是世界上现存规模最大、保存最为完整的木质结构古建筑群。作为中国历史文化的重要象征，故宫承载着丰富的历史故事与文化遗产。本篇攻略将为您提供一次难忘的故宫之旅。</p><h4 style=\"text-align: start;\">二、最佳游览时间</h4><ul><li style=\"text-align: start;\">春季（3月-5月）：气候宜人，百花盛开，尤其是御花园内的花木争艳。</li><li style=\"text-align: start;\">秋季（9月-11月）：天高气爽，适合户外活动，可以避开暑期的人流高峰。</li><li style=\"text-align: start;\">避开节假日和周末高峰期，以获得更好的参观体验。</li></ul><h4 style=\"text-align: start;\">三、门票信息</h4><p style=\"text-align: start;\">提前在线购买门票，避免现场排队。故宫官网提供详细的票务信息，包括普通票、学生票及优惠票种等。建议至少提前一周预订。</p><h4 style=\"text-align: start;\">四、推荐游览路线</h4><ol><li style=\"text-align: start;\">午门进入经过午门，步入太和门广场，欣赏三大殿（太和殿、中和殿、保和殿）的雄伟壮丽。</li><li style=\"text-align: start;\">东西六宫探访后宫佳丽的生活区域，了解宫廷生活的点滴。</li><li style=\"text-align: start;\">珍宝馆与钟表馆这里展示了许多珍贵文物，包括珠宝首饰、古代计时器等。</li><li style=\"text-align: start;\">神武门出口结束愉快的故宫之旅。</li></ol><h4 style=\"text-align: start;\">五、实用小贴士</h4><ul><li style=\"text-align: start;\">穿着舒适的鞋子，因为需要大量的步行。</li><li style=\"text-align: start;\">带上充足的水和小吃，但注意保护环境，不要在非指定区域内进食。</li><li style=\"text-align: start;\">使用电子导览设备或下载相关APP，获取更深入的历史文化知识。</li><li style=\"text-align: start;\">注意遵守博物馆的规定，不触摸展品，不大声喧哗。</li></ul><h4 style=\"text-align: start;\">六、结语</h4><p style=\"text-align: start;\">故宫不仅是一座古老的宫殿，它更是中华民族悠久历史文化的见证者。希望通过这份攻略，能让您的故宫之行更加完美，体验到中华文明的独特魅力。记得带着敬畏之心来探索这座神秘而又充满故事的皇家宫殿吧！</p>', 1, '2025-04-06 22:53:29');
INSERT INTO `scenic_strategy` VALUES (10, 26, 13, '/api/online-travel-sys/v1.0/file/getFile?fileName=95541a6fgsdhfcgxhsdzhgased.jpg', '天津古文化街深度游攻略——探寻津门文化的精髓', '<p><br></p><h4 style=\"text-align: start;\">一、引言</h4><p style=\"text-align: start;\">天津古文化街位于天津市南开区，是天津市区内保存完好的传统街区之一。这里不仅汇聚了众多传统的手工艺品店、特色小吃摊，还有多座历史文化遗迹，是体验天津传统文化的绝佳去处。本篇攻略将带您深入游览天津古文化街，感受这座城市的独特魅力。</p><h4 style=\"text-align: start;\">二、最佳游览时间</h4><ul><li style=\"text-align: start;\">春季（3月-5月）和秋季（9月-11月）是游览天津古文化街的最佳时节，气候宜人，适合户外活动。</li><li style=\"text-align: start;\">尽量避开节假日高峰时段，以获得更舒适的游览体验。</li></ul><h4 style=\"text-align: start;\">三、交通指南</h4><ul><li style=\"text-align: start;\">可选择乘坐地铁2号线至“东南角”站下车，步行即可到达古文化街入口。</li><li style=\"text-align: start;\">多条公交线路也可直达，具体可查询当地公共交通信息。</li></ul><h4 style=\"text-align: start;\">四、必看景点</h4><ol><li style=\"text-align: start;\">天后宫始建于元代，是天津最古老的建筑之一，也是中国北方最大的妈祖庙，体现了天津作为海上贸易重要港口的历史背景。</li><li style=\"text-align: start;\">古玩城汇聚了各类古董、字画、玉石等，是收藏爱好者的好去处。</li><li style=\"text-align: start;\">民俗展览馆展示了天津的传统民间艺术与习俗，如泥人张彩塑、杨柳青木版年画等。</li></ol><h4 style=\"text-align: start;\">五、美食推荐</h4><ul><li style=\"text-align: start;\">狗不理包子：天津著名的小吃之一，皮薄馅大，味道鲜美。</li><li style=\"text-align: start;\">耳朵眼炸糕：外焦里嫩，甜而不腻。</li><li style=\"text-align: start;\">茶汤：用糯米粉制成，配以各种果仁，香甜可口。</li></ul><h4 style=\"text-align: start;\">六、购物指南</h4><ul><li style=\"text-align: start;\">古文化街上有很多售卖传统手工艺品的店铺，比如剪纸、风筝等，都是不错的纪念品选择。</li><li style=\"text-align: start;\">注意比较价格，部分商品可能存在较大差异。</li></ul><h4 style=\"text-align: start;\">七、实用小贴士</h4><ul><li style=\"text-align: start;\">提前规划好路线，避免错过重要的景点或体验项目。</li><li style=\"text-align: start;\">穿着舒适的鞋子，因为需要大量的步行。</li><li style=\"text-align: start;\">注意保管个人物品，尤其是在人流密集的地方。</li></ul><h4 style=\"text-align: start;\">八、结语</h4><p style=\"text-align: start;\">天津古文化街是一个充满历史韵味的地方，它不仅是了解天津历史文化的窗口，也是一个享受美食、购买特色纪念品的理想之地。希望这份攻略能帮助您更好地规划您的天津之旅，尽情享受这里的每一分每一秒。不要忘了带上相机，记录下这难忘的文化之旅！</p>', 1, '2025-04-07 00:47:02');

-- ----------------------------
-- Table structure for scenic_ticket
-- ----------------------------
DROP TABLE IF EXISTS `scenic_ticket`;
CREATE TABLE `scenic_ticket`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '景区门票主键ID， 自增',
  `scenic_id` int(0) NULL DEFAULT NULL COMMENT '景点ID',
  `detail` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '门票描述',
  `number` int(0) NULL DEFAULT NULL COMMENT '门票数量',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '门票价格',
  `discount` double(10, 2) NULL DEFAULT NULL COMMENT '折扣',
  `use_status` tinyint(1) NULL DEFAULT NULL COMMENT '门票的可用状态',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of scenic_ticket
-- ----------------------------
INSERT INTO `scenic_ticket` VALUES (6, 12, '暑期来临，全场打折！', 1995, 69.99, 9.80, 1, '2025-04-06 17:24:19');
INSERT INTO `scenic_ticket` VALUES (7, 13, '暑期来临，全场打折！', 1997, 59.90, 9.90, 1, '2025-04-06 17:43:20');
INSERT INTO `scenic_ticket` VALUES (8, 14, '暑期来临，全场打折!', 996, 59.90, 8.90, 1, '2025-04-07 17:41:02');
INSERT INTO `scenic_ticket` VALUES (9, 15, '玩的开心，玩的愉快！', 1000, 49.90, NULL, 1, '2025-04-07 17:41:46');

-- ----------------------------
-- Table structure for scenic_ticket_order
-- ----------------------------
DROP TABLE IF EXISTS `scenic_ticket_order`;
CREATE TABLE `scenic_ticket_order`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '景区门票订单信息主键ID，自增',
  `user_id` int(0) NULL DEFAULT NULL COMMENT '用户ID',
  `ticket_id` int(0) NULL DEFAULT NULL COMMENT '门票ID',
  `concat_person` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '联系人',
  `concat_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '联系电话',
  `amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '金额',
  `buy_number` int(0) NULL DEFAULT NULL COMMENT '购买数量',
  `pay_status` tinyint(1) NULL DEFAULT NULL COMMENT '支付状态',
  `pay_time` datetime(0) NULL DEFAULT NULL COMMENT '支付时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of scenic_ticket_order
-- ----------------------------
INSERT INTO `scenic_ticket_order` VALUES (12, 25, 7, '巴巴托斯', '11-111-1111', 59.31, 1, 1, '2025-03-13 17:55:01', '2025-03-13 14:54:45');
INSERT INTO `scenic_ticket_order` VALUES (13, 25, 6, '巴巴托斯', '11-111-1111', 68.60, 1, 1, '2025-04-06 23:05:49', '2025-04-06 23:05:43');
INSERT INTO `scenic_ticket_order` VALUES (14, 26, 6, '芭芭拉', '11-111-2222', 68.60, 1, 1, '2025-04-07 01:24:57', '2025-04-07 01:24:31');
INSERT INTO `scenic_ticket_order` VALUES (17, 24, 8, '艾尔海森', '123-111-567', 106.63, 2, 1, '2025-04-07 17:46:36', '2025-04-07 17:46:24');
INSERT INTO `scenic_ticket_order` VALUES (18, 24, 7, '艾尔海森', '111-768-333', 118.61, 2, 1, '2025-04-07 17:58:20', '2025-04-07 17:55:13');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `user_account` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户账号',
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `user_pwd` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户密码',
  `user_avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `user_email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户邮箱',
  `user_role` int(0) NULL DEFAULT NULL COMMENT '用户角色',
  `is_login` tinyint(1) NULL DEFAULT NULL COMMENT '可登录状态(0：可用，1：不可用)',
  `is_word` tinyint(1) NULL DEFAULT NULL COMMENT '禁言状态(0：可用，1：不可用)',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '用户注册时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '20250301', '管理员', '14e1b600b1fd579f47433b88e8d85291', '/api/online-travel-sys/v1.0/file/getFile?fileName=acb3d3f09ecc756-9049-4ac3-bcb5-2fa715352592.png', '123@qq.com', 0, 0, 0, '2024-10-25 12:53:05');
INSERT INTO `user` VALUES (16, '20250302', '芙宁娜', '14e1b600b1fd579f47433b88e8d85291', '/api/online-travel-sys/v1.0/file/getFile?fileName=75206eae50ba855-527c-4d2a-a2b9-db98501d400f.png', '302@qq.om', 1, 0, 0, '2025-04-06 01:03:43');
INSERT INTO `user` VALUES (17, '20250303', '琳妮特', '14e1b600b1fd579f47433b88e8d85291', '/api/online-travel-sys/v1.0/file/getFile?fileName=1b88c0685babd1efb1023c4b7d069e1da767b96c2c830d3.jpg', '303@qq.com', 1, 0, 0, '2025-04-06 01:07:17');
INSERT INTO `user` VALUES (18, '20250304', '林尼', '14e1b600b1fd579f47433b88e8d85291', '/api/online-travel-sys/v1.0/file/getFile?fileName=0550032558dae2786d93a7ea8b7f9e67dface124a0ca275.jpg', '304@qq.com', 1, 0, 0, '2025-04-06 01:07:48');
INSERT INTO `user` VALUES (19, '20250305', '莱欧斯利', '14e1b600b1fd579f47433b88e8d85291', '/api/online-travel-sys/v1.0/file/getFile?fileName=539dfaecf49524f236ed5d3be88829c00cb3496348453817.jpg', '305@qq.com', 1, 0, 0, '2025-04-06 01:08:35');
INSERT INTO `user` VALUES (20, '20250306', '夏洛蒂', '14e1b600b1fd579f47433b88e8d85291', '/api/online-travel-sys/v1.0/file/getFile?fileName=a5dfdeda2ede69bc23205d6ce5bf24c77afe588348453817.jpg', '306@qq.com', 1, 0, 0, '2025-04-06 01:09:27');
INSERT INTO `user` VALUES (21, '20250307', '菲米尼', '14e1b600b1fd579f47433b88e8d85291', '/api/online-travel-sys/v1.0/file/getFile?fileName=2d127509794c93a39790b115142a0d920c1278f96dcca50.jpg', '307@qq.com', 1, 0, 0, '2025-04-06 01:10:11');
INSERT INTO `user` VALUES (22, '20250308', '卡维', '14e1b600b1fd579f47433b88e8d85291', '/api/online-travel-sys/v1.0/file/getFile?fileName=aa2ff43d535d2199e35bfad24ffb081e2d0f3fb0e3ddad3.jpg', '308@qq.com', 1, 0, 0, '2025-04-06 01:10:38');
INSERT INTO `user` VALUES (23, '20250309', '白术', '14e1b600b1fd579f47433b88e8d85291', '/api/online-travel-sys/v1.0/file/getFile?fileName=e84e5a6591d974894794dcebfc0f83cd32ddb01ef4fdeda.jpg', '309@qq.com', 1, 0, 0, '2025-04-06 01:11:21');
INSERT INTO `user` VALUES (24, '20250310', '艾尔海森', '14e1b600b1fd579f47433b88e8d85291', '/api/online-travel-sys/v1.0/file/getFile?fileName=f5225548a62808ede44ce1b59e20252504a0bf4890ba231.jpg', '310@qq.com', 1, 0, 0, '2025-04-06 01:11:49');
INSERT INTO `user` VALUES (25, '20250311', '巴巴托斯', '14e1b600b1fd579f47433b88e8d85291', '/api/online-travel-sys/v1.0/file/getFile?fileName=b05049db2712b0aea3ea559e03413ef262fb5de573e1791.jpg', '311@qq.com', 1, 0, 0, '2025-04-06 01:12:23');
INSERT INTO `user` VALUES (26, '20250312', '芭芭拉', '14e1b600b1fd579f47433b88e8d85291', '/api/online-travel-sys/v1.0/file/getFile?fileName=8a75d16b3589e622a332b2133407c7336c2f0a53431cf91.jpg', '312@qq.com', 1, 0, 0, '2025-04-07 00:23:45');

-- ----------------------------
-- Table structure for vendor
-- ----------------------------
DROP TABLE IF EXISTS `vendor`;
CREATE TABLE `vendor`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '供应商表主键ID，自增',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '供应商的名称',
  `concat_person` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '供应商联系人',
  `concat_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '供应商联系电话',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '电子邮箱',
  `product_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '产品类型',
  `work_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '办公地址',
  `user_id` int(0) NULL DEFAULT NULL COMMENT '用户ID',
  `is_audit` tinyint(1) NULL DEFAULT NULL COMMENT '是否已经审核',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '供应商的状态',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vendor
-- ----------------------------
INSERT INTO `vendor` VALUES (6, '蓝天国际旅行社 ', '王丽', '138-1234-5678', 'wangli@bluesky.com', '旅游综合服务', '北京市朝阳区建国路88号', 16, 1, 1, '2025-04-06 01:35:41');
INSERT INTO `vendor` VALUES (7, '津门商旅服务', '李涛', '182-2222-3333', 'litao@tianjin.cn', '商务差旅服务', '天津市和平区南京路12号', 17, 1, 1, '2025-04-06 01:38:18');
INSERT INTO `vendor` VALUES (8, '江南文旅集团', '张伟', '138-1234-5678', 'zhangwei@huaxialv.com', '景区+酒店套餐', '杭州市西湖区文三路456号', 18, 1, 1, '2025-04-06 01:46:02');
INSERT INTO `vendor` VALUES (9, '椰风海韵旅游', '陈美琳', '0898-8888-1234', '[chenml@yefenghy.com', '滨海度假', '三亚市吉阳区凤凰路88号', 19, 1, 1, '2025-04-06 01:49:50');

SET FOREIGN_KEY_CHECKS = 1;
