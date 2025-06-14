-- 替换原chemical表为药品基本信息表
CREATE TABLE `drugs` (
  `drug_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` VARCHAR(255) NOT NULL COMMENT '中文名称',
  `cas_number` VARCHAR(20) NOT NULL COMMENT 'CAS编号',
  `english_name` VARCHAR(255) COMMENT '英文名称',
  `specification` VARCHAR(100) COMMENT '规格',
  `manufacturer` BIGINT COMMENT '生产商ID（外键关联suppliers表）',
  `storage_conditions` BIGINT COMMENT '存储条件ID（外键关联storage_types表）',
  `hazard_class` BIGINT COMMENT '危险类别ID（外键关联hazard_classes表）',
  `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`drug_id`),
  UNIQUE KEY `uk_cas` (`cas_number`),
  FOREIGN KEY (`manufacturer`) REFERENCES `suppliers`(`supplier_id`),
  FOREIGN KEY (`storage_conditions`) REFERENCES `storage_types`(`storage_type_id`),
  FOREIGN KEY (`hazard_class`) REFERENCES `hazard_classes`(`class_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 新增库存管理表
CREATE TABLE `stock` (
  `stock_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '库存记录ID',
  `drug_id` BIGINT NOT NULL COMMENT '药品ID（外键关联drugs表）',
  `lab_id` BIGINT NOT NULL COMMENT '实验室ID（外键关联laboratory表）',
  `current_stock` INT DEFAULT 0 COMMENT '当前库存量',
  `min_threshold` INT DEFAULT 10 COMMENT '最低库存阈值',
  `max_threshold` INT DEFAULT 1000 COMMENT '最高库存阈值',
  `location` VARCHAR(100) COMMENT '货架位置',
  `expiration_date` DATE COMMENT '失效日期',
  `last_modified_by` BIGINT COMMENT '最后操作人ID（关联lab_member表）',
  `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`stock_id`),
  FOREIGN KEY (`drug_id`) REFERENCES `drugs`(`drug_id`),
  FOREIGN KEY (`lab_id`) REFERENCES `laboratory`(`id`),
  FOREIGN KEY (`last_modified_by`) REFERENCES `lab_member`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='药品库存管理表';

-- 新增供应商信息表
CREATE TABLE `suppliers` (
  `supplier_id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL COMMENT '供应商名称',
  `contact_info` VARCHAR(255) COMMENT '联系方式',
  `address` VARCHAR(500) COMMENT '地址',
  PRIMARY KEY (`supplier_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 存储条件类型表
CREATE TABLE `storage_types` (
  `storage_type_id` BIGINT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(100) NOT NULL COMMENT '存储条件描述',
  PRIMARY KEY (`storage_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 危险等级分类表 
CREATE TABLE `hazard_classes` (
  `class_id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL COMMENT '危险等级名称',
  `symbol` VARCHAR(20) COMMENT '安全标识代码',
  PRIMARY KEY (`class_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 实验室基本信息表
CREATE TABLE `laboratory` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `lab_name` VARCHAR(100) NOT NULL COMMENT '实验室名称',
  `school` VARCHAR(100) NOT NULL COMMENT '隶属学校',
  `registrant_member_id` BIGINT COMMENT '注册成员ID（外键关联lab_member表）',
  `description` TEXT COMMENT '实验室介绍',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` ENUM('active','inactive','archived') DEFAULT 'active' COMMENT '实验室状态',
  PRIMARY KEY (`id`),
  FOREIGN KEY (`registrant_member_id`) REFERENCES `lab_member`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='实验室基本信息表';

-- 实验室成员表
CREATE TABLE `lab_member` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `lab_id` BIGINT NULL COMMENT '隶属实验室ID',  -- 修改为允许NULL
  `member_name` VARCHAR(50) NOT NULL COMMENT '成员姓名',
  `permission` ENUM('admin','member','guest') DEFAULT 'member' COMMENT '成员权限',
  `phone` VARCHAR(20) NOT NULL COMMENT '手机号',
  `email` VARCHAR(255) NOT NULL COMMENT '邮箱',
  `password` VARCHAR(255) NOT NULL COMMENT '登录密码',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  FOREIGN KEY (`lab_id`) REFERENCES `laboratory`(`id`),
  UNIQUE KEY `uk_member_phone` (`phone`),
  UNIQUE KEY `uk_member_email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='实验室成员表';


-- 补充供应商数据
INSERT INTO `suppliers` (name, contact_info, address) 
VALUES 
('生物制剂公司', '400-1122334', '上海市浦东新区生命科学园1号'),
('精密化学公司', '800-3344556', '广州市天河区化学大道88号');

-- 补充存储条件
INSERT INTO `storage_types` (description) 
VALUES ('防潮保存'), ('-20℃冷冻'), ('惰性气体环境');

-- 补充危险等级
INSERT INTO `hazard_classes` (name, symbol) 
VALUES ('氧化性', 'O'), ('有害', 'Xn'), ('环境危害', 'N');

-- 补充药品数据
INSERT INTO drugs 
  (name, cas_number, english_name, specification, manufacturer, storage_conditions, hazard_class)
VALUES
  ('盐酸', '7647-01-0', 'Hydrochloric Acid', 'AR级 1L', 2, 4, 5),
  ('过氧化氢', '7722-84-1', 'Hydrogen Peroxide', '30% 500ml', 3, 5, 4),
  ('丙酮', '67-64-1', 'Acetone', 'HPLC级 4L', 1, 3, 1);

-- 补充库存数据
INSERT INTO stock 
  (drug_id, current_stock, min_threshold, max_threshold, location, expiration_date, last_modified_by)
VALUES
  (3, 8, 2, 30, 'C区2号柜-3层', '2025-09-30', 1),
  (4, 15, 5, 50, 'D区5号柜-1层', '2026-03-31', 2),
  (5, 20, 10, 100, 'A区1号柜-5层', '2027-12-31', 1);

-- 补充实验室数据
INSERT INTO laboratory 
  (lab_name, school, registrant_phone, registrant_password, description)
VALUES
  ('材料实验室', '浙江大学', '13500135000', MD5('Material2024'), '新型材料研发实验室');

-- 补充实验室成员
INSERT INTO lab_member
  (lab_id, member_name, permission, phone)
VALUES
  (3, '王五', 'admin', '13900003333'),
  (3, '赵六', 'member', '13900004444');

