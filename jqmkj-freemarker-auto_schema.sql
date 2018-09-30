/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : jqmkj-old

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 07/08/2018 10:17:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for gen_scheme_t
-- ----------------------------
DROP TABLE IF EXISTS `gen_scheme_t`;
CREATE TABLE `gen_scheme_t`  (
  `id_` BIGINT(20) AUTO_INCREMENT  NOT NULL COMMENT '编号',
  `name_` varchar(200)  NULL DEFAULT NULL COMMENT '名称',
  `category_` varchar(2000)  NULL DEFAULT NULL COMMENT '分类',
  `view_type` char(2)  NULL DEFAULT NULL COMMENT '视图类型 0  普通表格 1  表格采用ajax刷新',
  `package_name` varchar(500)  NULL DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30)  NULL DEFAULT NULL COMMENT '生成模块名',
  `sub_module_name` varchar(30)  NULL DEFAULT NULL COMMENT '生成子模块名',
  `function_name` varchar(500)  NULL DEFAULT NULL COMMENT '生成功能名',
  `function_name_simple` varchar(100)  NULL DEFAULT NULL COMMENT '生成功能名（简写）',
  `function_author` varchar(100)  NULL DEFAULT NULL COMMENT '生成功能作者',
  `gen_table_id` BIGINT(20)  NULL DEFAULT NULL COMMENT '生成表编号',

  `status_` char(2)  NULL DEFAULT '0' COMMENT '状态 -2 已删除 -1停用 0 正常',
  `version_` int(11) NULL DEFAULT 0 COMMENT '默认0，必填，离线乐观锁',
  `description_` varchar(255)  NULL DEFAULT NULL COMMENT '描述',
  `created_by` varchar(50)  NOT NULL,
  `created_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `last_modified_by` varchar(50)  NULL DEFAULT NULL,
  `last_modified_date` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '生成方案' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gen_scheme_t
-- ----------------------------

-- ----------------------------
-- Table structure for gen_table_column_t
-- ----------------------------
DROP TABLE IF EXISTS `gen_table_column_t`;
CREATE TABLE `gen_table_column_t`  (
  `id_` BIGINT(20) AUTO_INCREMENT  NOT NULL COMMENT '编号',
  `gen_table_id` BIGINT(20)  NULL DEFAULT NULL COMMENT '归属表编号',
  `name_` varchar(200)  NULL DEFAULT NULL COMMENT '名称',
  `title_` varchar(255)  NULL DEFAULT NULL,
  `comments` varchar(500)  NULL DEFAULT NULL COMMENT '描述',
  `jdbc_type` varchar(100)  NULL DEFAULT NULL COMMENT '列的数据类型的字节长度',
  `java_type` varchar(500)  NULL DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200)  NULL DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` char(1)  NULL DEFAULT NULL COMMENT '是否主键',
  `is_unique` char(1)  NULL DEFAULT '0' COMMENT '是否唯一（1：是；0：否）',
  `is_null` char(1)  NULL DEFAULT NULL COMMENT '是否可为空',
  `is_insert` char(1)  NULL DEFAULT NULL COMMENT '是否为插入字段',
  `is_edit` char(1)  NULL DEFAULT NULL COMMENT '是否编辑字段',
  `is_list` char(1)  NULL DEFAULT NULL COMMENT '是否列表字段',
  `is_query` char(1)  NULL DEFAULT NULL COMMENT '是否查询字段',
  `query_type` varchar(200)  NULL DEFAULT NULL COMMENT '查询方式（等于、不等于、大于、小于、范围、左LIKE、右LIKE、左右LIKE）',
  `show_type` varchar(200)  NULL DEFAULT NULL COMMENT '字段生成方案（文本框、文本域、下拉框、复选框、单选框、字典选择、人员选择、部门选择、区域选择）',
  `dict_type` varchar(200)  NULL DEFAULT NULL COMMENT '字典类型',
  `settings` varchar(2000)  NULL DEFAULT NULL COMMENT '其它设置（扩展字段JSON）',
  `sort_` decimal(10, 0) NULL DEFAULT NULL COMMENT '排序（升序）',

  `status_` char(2)  NULL DEFAULT '0' COMMENT '状态 -2 已删除 -1停用 0 正常',
  `version_` int(11) NULL DEFAULT 0 COMMENT '默认0，必填，离线乐观锁',
  `description_` varchar(255)  NULL DEFAULT NULL COMMENT '描述',
  `created_by` varchar(50)  NOT NULL,
  `created_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `last_modified_by` varchar(50)  NULL DEFAULT NULL,
  `last_modified_date` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id_`) USING BTREE,
  INDEX `gen_table_column_table_id`(`gen_table_id`) USING BTREE,
  INDEX `gen_table_column_name`(`name_`) USING BTREE,
  INDEX `gen_table_column_sort`(`sort_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '业务表字段' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gen_table_column_t
-- ----------------------------

-- ----------------------------
-- Table structure for gen_table_fk_t
-- ----------------------------
DROP TABLE IF EXISTS `gen_table_fk_t`;
CREATE TABLE `gen_table_fk_t`  (
  `id_` BIGINT(20) AUTO_INCREMENT  NOT NULL COMMENT '编号',
  `gen_table_id` BIGINT(20)  NULL DEFAULT NULL COMMENT '归属表编号',
  `name_` varchar(200)  NULL DEFAULT NULL COMMENT '名称',
  `comments` varchar(500)  NULL DEFAULT NULL COMMENT '描述',
  `jdbc_type` varchar(100)  NULL DEFAULT NULL COMMENT '列的数据类型的字节长度',
  `java_type` varchar(500)  NULL DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200)  NULL DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` char(1)  NULL DEFAULT NULL COMMENT '是否主键',
  `is_unique` char(1)  NULL DEFAULT '0' COMMENT '是否唯一（1：是；0：否）',
  `is_null` char(1)  NULL DEFAULT NULL COMMENT '是否可为空',
  `is_insert` char(1)  NULL DEFAULT NULL COMMENT '是否为插入字段',
  `is_edit` char(1)  NULL DEFAULT NULL COMMENT '是否编辑字段',
  `is_list` char(1)  NULL DEFAULT NULL COMMENT '是否列表字段',
  `is_query` char(1)  NULL DEFAULT NULL COMMENT '是否查询字段',
  `query_type` varchar(200)  NULL DEFAULT NULL COMMENT '查询方式（等于、不等于、大于、小于、范围、左LIKE、右LIKE、左右LIKE）',
  `show_type` varchar(200)  NULL DEFAULT NULL COMMENT '字段生成方案（文本框、文本域、下拉框、复选框、单选框、字典选择、人员选择、部门选择、区域选择）',
  `dict_type` varchar(200)  NULL DEFAULT NULL COMMENT '字典类型',
  `settings` varchar(2000)  NULL DEFAULT NULL COMMENT '其它设置（扩展字段JSON）',
  `sort_` decimal(10, 0) NULL DEFAULT NULL COMMENT '排序（升序）',
  `status_` char(2)  NULL DEFAULT '0' COMMENT '状态 -2 已删除 -1停用 0 正常',
  `version_` int(11) NULL DEFAULT 0 COMMENT '默认0，必填，离线乐观锁',
  `description_` varchar(255)  NULL DEFAULT NULL COMMENT '描述',
  `created_by` varchar(50)  NOT NULL,
  `created_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `last_modified_by` varchar(50)  NULL DEFAULT NULL,
  `last_modified_date` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id_`) USING BTREE,
  INDEX `gen_table_column_table_id`(`gen_table_id`) USING BTREE,
  INDEX `gen_table_column_name`(`name_`) USING BTREE,
  INDEX `gen_table_column_sort`(`sort_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '业务表字段' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for gen_table_t
-- ----------------------------
DROP TABLE IF EXISTS `gen_table_t`;
CREATE TABLE `gen_table_t`  (
  `id_` BIGINT(20) AUTO_INCREMENT  NOT NULL COMMENT '编号',
  `name_` varchar(200)  NULL DEFAULT NULL COMMENT '名称',
  `comments` varchar(500)  NULL DEFAULT NULL COMMENT '描述',
  `class_name` varchar(100)  NULL DEFAULT NULL COMMENT '实体类名称',
  `parent_table` varchar(200)  NULL DEFAULT NULL COMMENT '关联父表',
  `parent_table_fk` varchar(100)  NULL DEFAULT NULL COMMENT '关联父表外键',
  `status_` char(2)  NULL DEFAULT '0' COMMENT '状态 -2 已删除 -1停用 0 正常',
  `version_` int(11) NULL DEFAULT 0 COMMENT '默认0，必填，离线乐观锁',
  `description_` varchar(255)  NULL DEFAULT NULL COMMENT '描述',
  `created_by` varchar(50)  NOT NULL,
  `created_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `last_modified_by` varchar(50)  NULL DEFAULT NULL,
  `last_modified_date` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id_`) USING BTREE,
  INDEX `gen_table_name`(`name_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '业务表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gen_table_t
-- ----------------------------

-- ----------------------------
-- Table structure for gen_template_t
-- ----------------------------
DROP TABLE IF EXISTS `gen_template_t`;
CREATE TABLE `gen_template_t`  (
  `id` BIGINT(20) AUTO_INCREMENT  NOT NULL COMMENT '编号',
  `name` varchar(200)  NULL DEFAULT NULL COMMENT '名称',
  `category` varchar(2000)  NULL DEFAULT NULL COMMENT '分类',
  `file_path` varchar(500)  NULL DEFAULT NULL COMMENT '生成文件路径',
  `file_name` varchar(200)  NULL DEFAULT NULL COMMENT '生成文件名',
  `content` text  NULL COMMENT '内容',
  `status_` char(2)  NULL DEFAULT '0' COMMENT '状态 -2 已删除 -1停用 0 正常',
  `version_` int(11) NULL DEFAULT 0 COMMENT '默认0，必填，离线乐观锁',
  `description_` varchar(255)  NULL DEFAULT NULL COMMENT '描述',
  `created_by` varchar(50)  NOT NULL,
  `created_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `last_modified_by` varchar(50)  NULL DEFAULT NULL,
  `last_modified_date` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '代码模板表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for jhi_authority
-- ----------------------------
DROP TABLE IF EXISTS `jhi_authority`;
CREATE TABLE `jhi_authority`  (
  `name` varchar(50)  NOT NULL,
  PRIMARY KEY (`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for jhi_persistent_audit_event
-- ----------------------------
DROP TABLE IF EXISTS `jhi_persistent_audit_event`;
CREATE TABLE `jhi_persistent_audit_event`  (
  `event_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `principal` varchar(50)  NOT NULL,
  `event_date` timestamp(0) NULL DEFAULT NULL,
  `event_type` varchar(255)  NULL DEFAULT NULL,
  PRIMARY KEY (`event_id`) USING BTREE,
  INDEX `idx_persistent_audit_event`(`principal`, `event_date`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 590 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of jhi_persistent_audit_event
-- ----------------------------

-- ----------------------------
-- Table structure for jhi_persistent_audit_evt_data
-- ----------------------------
DROP TABLE IF EXISTS `jhi_persistent_audit_evt_data`;
CREATE TABLE `jhi_persistent_audit_evt_data`  (
  `event_id` bigint(20) NOT NULL,
  `name` varchar(150)  NOT NULL,
  `value` varchar(255)  NULL DEFAULT NULL,
  PRIMARY KEY (`event_id`, `name`) USING BTREE,
  INDEX `idx_persistent_audit_evt_data`(`event_id`) USING BTREE,
  CONSTRAINT `jhi_persistent_audit_evt_data_ibfk_1` FOREIGN KEY (`event_id`) REFERENCES `jhi_persistent_audit_event` (`event_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of jhi_persistent_audit_evt_data
-- ----------------------------

-- ----------------------------
-- Table structure for jhi_persistent_token
-- ----------------------------
DROP TABLE IF EXISTS `jhi_persistent_token`;
CREATE TABLE `jhi_persistent_token`  (
  `id_` BIGINT(20) AUTO_INCREMENT  NOT NULL,
  `series_` varchar(76)  NOT NULL,
  `user_id` bigint(20) NULL DEFAULT NULL,
  `token_value` varchar(76)  NOT NULL,
  `token_date` date NULL DEFAULT NULL,
  `ip_address` varchar(39)  NULL DEFAULT NULL,
  `user_agent` varchar(255)  NULL DEFAULT NULL,
  PRIMARY KEY (`id_`) USING BTREE,
  INDEX `fk_user_persistent_token`(`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for logging_event
-- ----------------------------
DROP TABLE IF EXISTS `logging_event`;
CREATE TABLE `logging_event`  (
  `event_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `timestmp` bigint(20) NOT NULL COMMENT '创建时间',
  `formatted_message` text  NOT NULL COMMENT '内容',
  `logger_name` varchar(254)  NOT NULL COMMENT '名称',
  `level_string` varchar(254)  NOT NULL COMMENT '级别',
  `thread_name` varchar(254)  NULL DEFAULT NULL COMMENT '线程',
  `reference_flag` smallint(6) NULL DEFAULT NULL COMMENT '引用标识',
  `arg0` varchar(254)  NULL DEFAULT NULL COMMENT '参数0',
  `arg1` varchar(254)  NULL DEFAULT NULL COMMENT '参数1',
  `arg2` varchar(254)  NULL DEFAULT NULL COMMENT '参数2',
  `arg3` varchar(254)  NULL DEFAULT NULL COMMENT '参数3',
  `caller_filename` varchar(254)  NOT NULL COMMENT '操作文件',
  `caller_class` varchar(254)  NOT NULL COMMENT '操作类名',
  `caller_method` varchar(254)  NOT NULL COMMENT '操作方法',
  `caller_line` char(4)  NOT NULL COMMENT '操作行',
  PRIMARY KEY (`event_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for logging_event_exception
-- ----------------------------
DROP TABLE IF EXISTS `logging_event_exception`;
CREATE TABLE `logging_event_exception`  (
  `event_id` bigint(20) NOT NULL,
  `i` smallint(6) NOT NULL,
  `trace_line` varchar(254)  NOT NULL,
  PRIMARY KEY (`event_id`, `i`) USING BTREE,
  CONSTRAINT `logging_event_exception_ibfk_1` FOREIGN KEY (`event_id`) REFERENCES `logging_event` (`event_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for logging_event_property
-- ----------------------------
DROP TABLE IF EXISTS `logging_event_property`;
CREATE TABLE `logging_event_property`  (
  `event_id` bigint(20) NOT NULL,
  `mapped_key` varchar(254)  NOT NULL,
  `mapped_value` text  NULL,
  PRIMARY KEY (`event_id`, `mapped_key`) USING BTREE,
  CONSTRAINT `logging_event_property_ibfk_1` FOREIGN KEY (`event_id`) REFERENCES `logging_event` (`event_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_area_t
-- ----------------------------
DROP TABLE IF EXISTS `sys_area_t`;
CREATE TABLE `sys_area_t`  (
  `id_` BIGINT(20) AUTO_INCREMENT NOT NULL COMMENT '区域id',
  `parent_ids` text  NOT NULL COMMENT '所有上级区域节点',
  `parent_id` BIGINT(20) NULL DEFAULT NULL COMMENT '上级区域',
  `name_` varchar(32)  NULL DEFAULT NULL COMMENT '区域名称',
  `short_name` varchar(32)  NULL DEFAULT NULL COMMENT '区域简称',
  `sort_` int(11) NOT NULL COMMENT '序号',
  `level_` int(11) NULL DEFAULT NULL COMMENT '区域等级(1省/2市/3区县)',
  `code_` varchar(32)  NULL DEFAULT NULL COMMENT '区域编码',
  `is_leaf` bit(1) NULL DEFAULT b'0' COMMENT '1 叶子节点 0 非叶子节点',
  `created_by` varchar(32)  NULL DEFAULT NULL COMMENT '创建人',
  `created_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `last_modified_by` varchar(32)  NULL DEFAULT NULL COMMENT '修改人',
  `last_modified_date` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `description_` varchar(225)  NULL DEFAULT NULL COMMENT '描述',
  `status_` varchar(32)  NULL DEFAULT '0' COMMENT '状态',
  `version_` int(11) NOT NULL DEFAULT 0 COMMENT '版本',
  PRIMARY KEY (`id_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '区域表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_area_t
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dict_t
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_t`;
CREATE TABLE `sys_dict_t`  (
  `id_` BIGINT(20) AUTO_INCREMENT  NOT NULL COMMENT '编号',
  `name_` varchar(255)  NULL DEFAULT NULL COMMENT '字典名称',
  `parent_id` BIGINT(20)  NULL DEFAULT NULL,
  `parent_ids` varchar(2000)  NULL DEFAULT NULL,
  `val_` varchar(255)  NULL DEFAULT NULL COMMENT '字典值',
  `key_` varchar(225)  NULL DEFAULT NULL,
  `code_` varchar(255)  NULL DEFAULT NULL COMMENT '字典唯一编码',
  `is_leaf` bit(1) NULL DEFAULT b'0' COMMENT '0 叶子节点 1 非叶子节点',
  `sort_` int(11) NULL DEFAULT 0 COMMENT '排序',
  `description_` varchar(255)  NULL DEFAULT NULL COMMENT '描述',
  `status_` varchar(2)  NULL DEFAULT '0',
  `show_name` varchar(255)  NULL DEFAULT NULL COMMENT '资源文件key',
  `is_show` bit(1) NULL DEFAULT b'1' COMMENT '0 叶子节点 1 非叶子节点',
  `version_` int(11) NULL DEFAULT 0,
  `created_by` varchar(50)  NOT NULL,
  `created_date` datetime(0) NULL DEFAULT NULL,
  `last_modified_by` varchar(50)  NULL DEFAULT NULL,
  `last_modified_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id_`) USING BTREE,
  INDEX `val_`(`val_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_t
-- ----------------------------

-- ----------------------------
-- Table structure for sys_file_data_t
-- ----------------------------
DROP TABLE IF EXISTS `sys_file_data_t`;
CREATE TABLE `sys_file_data_t`  (
  `id_` BIGINT(20) AUTO_INCREMENT  NOT NULL,
  `name_` varchar(32)  NULL DEFAULT NULL COMMENT '名称',
  `path_` varchar(255)  NULL DEFAULT NULL COMMENT '路径',
  `size_` int(11) NOT NULL COMMENT '大小',
  `type_` varchar(60)  NULL DEFAULT NULL COMMENT '类型',
  `created_by` varchar(50)  NOT NULL,
  `created_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `last_modified_by` varchar(50)  NULL DEFAULT NULL,
  `last_modified_date` timestamp(0) NULL DEFAULT NULL,
  `status_` int(11) NULL DEFAULT NULL,
  `description_` varchar(255)  NULL DEFAULT NULL,
  `version_` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id_`) USING BTREE,
  UNIQUE INDEX `login`(`size_`) USING BTREE,
  UNIQUE INDEX `idx_user_login`(`size_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文件对象' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_log_login_t
-- ----------------------------
DROP TABLE IF EXISTS `sys_log_login_t`;
CREATE TABLE `sys_log_login_t`  (
  `id_` BIGINT(20) AUTO_INCREMENT  NOT NULL,
  `staff_id` varchar(32)  NULL DEFAULT NULL,
  `login_id` varchar(50)  NULL DEFAULT NULL,
  `remote_addr` varchar(255)  NULL DEFAULT NULL,
  `session_id` BIGINT(20)  NULL DEFAULT NULL,
  `enter_time` datetime(0) NULL DEFAULT NULL,
  `leave_time` datetime(0) NULL DEFAULT NULL,
  `total_time` int(11) NULL DEFAULT NULL,
  `login_flag` int(11) NULL DEFAULT NULL,
  `description_` varchar(255)  NULL DEFAULT NULL,
  `status_` varchar(2)  NULL DEFAULT '0',
  PRIMARY KEY (`id_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_log_operate_t
-- ----------------------------
DROP TABLE IF EXISTS `sys_log_operate_t`;
CREATE TABLE `sys_log_operate_t`  (
  `id_` BIGINT(20) AUTO_INCREMENT  NOT NULL,
  `title_` varchar(500)  NULL DEFAULT NULL,
  `type_` char(1)  NULL DEFAULT '0' COMMENT '日志类型 0：接入日志；1：错误日志',
  `staff_id` varchar(50)  NULL DEFAULT NULL,
  `login_id` varchar(50)  NULL DEFAULT NULL,
  `exception_` text  NULL,
  `request_method` varchar(50)  NULL DEFAULT NULL COMMENT '请求方式',
  `access_method` varchar(255)  NULL DEFAULT NULL COMMENT '请求方法',
  `params_` varchar(2000)  NULL DEFAULT NULL,
  `remote_addr` varchar(255)  NULL DEFAULT NULL,
  `permissions` varchar(255)  NULL DEFAULT NULL COMMENT '权限标识',
  `time_consuming` int(11) NULL DEFAULT NULL,
  `request_uri` varchar(2000)  NULL DEFAULT NULL COMMENT '耗时(毫秒)',
  `user_agent` varchar(255)  NULL DEFAULT NULL,
  `operate_time` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  `operate_des` varchar(255)  NULL DEFAULT NULL,
  `status_` varchar(2)  NULL DEFAULT NULL,
  PRIMARY KEY (`id_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log_operate_t
-- ----------------------------

-- ----------------------------
-- Table structure for sys_message_deal_t
-- ----------------------------
DROP TABLE IF EXISTS `sys_message_deal_t`;
CREATE TABLE `sys_message_deal_t`  (
  `id_` BIGINT(20) AUTO_INCREMENT  NOT NULL,
  `message_id` BIGINT(20)  NOT NULL,
  `staff_id` varchar(32)  NOT NULL,
  `deal_time` datetime(0) NULL DEFAULT NULL,
  `version_` int(11) NULL DEFAULT NULL,
  `status_` varchar(2)  NULL DEFAULT NULL,
  PRIMARY KEY (`id_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_message_deal_t
-- ----------------------------

-- ----------------------------
-- Table structure for sys_message_t
-- ----------------------------
DROP TABLE IF EXISTS `sys_message_t`;
CREATE TABLE `sys_message_t`  (
  `id_` BIGINT(20) AUTO_INCREMENT  NOT NULL,
  `type_` varchar(255)  NULL DEFAULT NULL,
  `title_` varchar(255)  NULL DEFAULT NULL COMMENT '消息主题',
  `content_` varchar(4000)  NULL DEFAULT NULL,
  `attachment_` varchar(255)  NULL DEFAULT NULL,
  `attach_path` varchar(255)  NULL DEFAULT NULL COMMENT '文件路径',
  `message_flag` varchar(255)  NULL DEFAULT NULL,
  `sender` varchar(64)  NULL DEFAULT NULL COMMENT '发件端',
  `send_time` datetime(0) NULL DEFAULT NULL COMMENT '发送时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `reciver` varchar(64)  NULL DEFAULT NULL,
  `deal_time` datetime(0) NULL DEFAULT NULL,
  `description_` varchar(255)  NULL DEFAULT NULL,
  `version_` int(11) NULL DEFAULT NULL,
  `status_` varchar(2)  NULL DEFAULT NULL,
  PRIMARY KEY (`id_`) USING BTREE,
  INDEX `FKFBFB834A7A37E1A2`(`sender`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_message_t
-- ----------------------------

-- ----------------------------
-- Table structure for sys_module_t
-- ----------------------------
DROP TABLE IF EXISTS `sys_module_t`;
CREATE TABLE `sys_module_t`  (
  `id_` BIGINT(20) AUTO_INCREMENT  NOT NULL COMMENT '模块编码',
  `name_` varchar(255)  NULL DEFAULT '' COMMENT '名称',
  `parent_id` BIGINT(20)  NULL DEFAULT NULL COMMENT '父module的id',
  `parent_ids` varchar(2000)  NULL DEFAULT NULL,
  `type_` varchar(50)  NULL DEFAULT NULL COMMENT '模块类型  0 菜单模块 1权限模块',
  `microservice_` varchar(500)  NULL DEFAULT NULL,
  `permission_` varchar(500)  NULL DEFAULT NULL COMMENT '权限标识',
  `component_` varchar(255)  NULL DEFAULT NULL,
  `sort_` int(11) NULL DEFAULT 0 COMMENT '排序',
  `target_` varchar(255)  NULL DEFAULT NULL,
  `url_` varchar(2000)  NULL DEFAULT NULL,
  `request_method` varchar(64)  NULL DEFAULT NULL COMMENT '请求方法',
  `status_` varchar(2)  NULL DEFAULT NULL COMMENT '是否删除  0正常 1不可用',
  `icon_cls` varchar(50)  NULL DEFAULT NULL,
  `show_type` varchar(10)  NULL DEFAULT '0' COMMENT '针对顶层菜单，0 普通展示下级菜单， 1已树形结构展示',
  `description_` varchar(255)  NULL DEFAULT NULL COMMENT '描述',
  `version_` int(11) NOT NULL,
  `is_leaf` bit(1) NULL DEFAULT b'0' COMMENT '1 叶子节点 0 非叶子节点',
  `created_by` varchar(50)  NOT NULL,
  `created_date` datetime(0) NULL DEFAULT NULL,
  `last_modified_by` varchar(50)  NULL DEFAULT NULL,
  `last_modified_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'module表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_module_t
-- ----------------------------

-- ----------------------------
-- Table structure for sys_org_t
-- ----------------------------
DROP TABLE IF EXISTS `sys_org_t`;
CREATE TABLE `sys_org_t`  (
  `id_` BIGINT(20) AUTO_INCREMENT  NOT NULL,
  `name_` varchar(255)  NULL DEFAULT NULL,
  `parent_id` BIGINT(20)  NULL DEFAULT NULL,
  `parent_ids` varchar(2000)  NULL DEFAULT NULL,
  `code_` varchar(64)  NULL DEFAULT NULL COMMENT '机构编码',
  `grade_` varchar(255)  NULL DEFAULT NULL COMMENT '机构等级',
  `is_leaf` bit(1) NULL DEFAULT b'0' COMMENT '1 叶子节点 0 非叶子节点',
  `en_` varchar(255)  NULL DEFAULT NULL,
  `sort_` int(11) NULL DEFAULT 0 COMMENT '序号',
  `type_` varchar(64)  NULL DEFAULT NULL COMMENT '组织类型',
  `status_` int(11) NULL DEFAULT NULL,
  `created_by` varchar(50)  NOT NULL,
  `created_date` datetime(0) NULL DEFAULT NULL,
  `last_modified_by` varchar(50)  NULL DEFAULT NULL,
  `last_modified_date` datetime(0) NULL DEFAULT NULL,
  `version_` int(11) NULL DEFAULT NULL,
  `description_` varchar(255)  NULL DEFAULT NULL,
  PRIMARY KEY (`id_`) USING BTREE,
  INDEX `FK2006A5E73CA88251`(`parent_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_org_t
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_module_t
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_module_t`;
CREATE TABLE `sys_role_module_t`  (
  `role_id` BIGINT(20)  NOT NULL,
  `module_id` BIGINT(20)  NOT NULL,
  PRIMARY KEY (`role_id`, `module_id`) USING BTREE,
  INDEX `FK_6eloh5l1ylo4pteqj5n1viu3c`(`module_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_module_t
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_org_t
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_org_t`;
CREATE TABLE `sys_role_org_t`  (
  `role_id` BIGINT(20)  NOT NULL,
  `org_id` BIGINT(20)  NOT NULL,
  PRIMARY KEY (`role_id`, `org_id`) USING BTREE,
  INDEX `FK4C18BAB12A44C67D`(`org_id`) USING BTREE,
  INDEX `FK4C18BAB1B90B46FD`(`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role_t
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_t`;
CREATE TABLE `sys_role_t`  (
  `id_` BIGINT(20) AUTO_INCREMENT  NOT NULL,
  `name_` varchar(100)  NULL DEFAULT NULL,
  `org_id` BIGINT(20)  NULL DEFAULT NULL,
  `sys_data` char(1)  NULL DEFAULT NULL COMMENT '是否系统数据',
  `data_scope` int(11) NULL DEFAULT NULL COMMENT '数据范围',
  `status_` varchar(64)  NULL DEFAULT NULL COMMENT '是否删除  0正常 1不可用',
  `sort_` int(11) NULL DEFAULT 0 COMMENT '序号',
  `type_` varchar(225)  NULL DEFAULT NULL COMMENT '对应activiti中的group表',
  `en_` varchar(225)  NULL DEFAULT NULL,
  `description_` varchar(225)  NULL DEFAULT NULL,
  `version_` int(11) NULL DEFAULT NULL,
  `created_by` varchar(50)  NOT NULL,
  `created_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `last_modified_by` varchar(50)  NULL DEFAULT NULL,
  `last_modified_date` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id_`) USING BTREE,
  INDEX `FKE5C4B49D852279D7`(`org_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_t
-- ----------------------------

-- ----------------------------
-- Table structure for sys_task_schedule_job_t
-- ----------------------------
DROP TABLE IF EXISTS `sys_task_schedule_job_t`;
CREATE TABLE `sys_task_schedule_job_t`  (
  `id_` BIGINT(20) AUTO_INCREMENT  NOT NULL,
  `name_` varchar(255)  NULL DEFAULT NULL COMMENT '名称',
  `group_` varchar(255)  NULL DEFAULT NULL COMMENT '分组',
  `job_status` varchar(255)  NULL DEFAULT NULL COMMENT '任务状态',
  `cron_expression` varchar(255)  NOT NULL COMMENT 'cron表达式',
  `bean_class` varchar(255)  NULL DEFAULT NULL COMMENT '任务执行时调用哪个类的方法 包名+类名',
  `is_concurrent` varchar(255)  NULL DEFAULT NULL COMMENT '任务是否有状态',
  `spring_id` varchar(255)  NULL DEFAULT NULL COMMENT 'spring bean',
  `source_id` varchar(32)  NULL DEFAULT NULL,
  `method_name` varchar(255)  NOT NULL COMMENT '任务调用的方法名',
  `method_params` varchar(512)  NULL DEFAULT NULL,
  `created_by` varchar(50)  NOT NULL,
  `created_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `last_modified_by` varchar(50)  NULL DEFAULT NULL,
  `last_modified_date` timestamp(0) NULL DEFAULT NULL,
  `status_` int(11) NULL DEFAULT NULL,
  `description_` varchar(255)  NULL DEFAULT NULL,
  `version_` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id_`) USING BTREE,
  UNIQUE INDEX `name_group`(`name_`, `group_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '计划任务表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_task_schedule_job_t
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_role_t
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role_t`;
CREATE TABLE `sys_user_role_t`  (
  `user_id` BIGINT(20)  NOT NULL,
  `role_id` BIGINT(20)  NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE,
  INDEX `FK_k7q8xbl92flmkhwupf64o6ii5`(`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role_t
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_t
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_t`;
CREATE TABLE `sys_user_t`  (
  `id_` BIGINT(20) AUTO_INCREMENT  NOT NULL,
  `org_id` BIGINT(20)  NULL DEFAULT NULL,
  `avatar_` varchar(255)  NULL DEFAULT NULL,
  `login_id` varchar(50)  NOT NULL,
  `password_hash` varchar(60)  NULL DEFAULT NULL,
  `name_` varchar(50)  NULL DEFAULT NULL,
  `email_` varchar(100)  NULL DEFAULT NULL,
  `phone_` varchar(32)  NULL DEFAULT NULL,
  `type_` varchar(32)  NOT NULL DEFAULT 'system' COMMENT '类型',
  `key_` varchar(500)  NULL DEFAULT NULL,
  `lang_key` varchar(5)  NULL DEFAULT NULL,
  `property_` varchar(500)  NULL DEFAULT NULL,
  `reset_key` varchar(20)  NULL DEFAULT NULL,
  `reset_date` timestamp(0) NULL DEFAULT NULL,
  `created_by` varchar(50)  NOT NULL,
  `created_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `last_modified_by` varchar(50)  NULL DEFAULT NULL,
  `last_modified_date` timestamp(0) NULL DEFAULT NULL,
  `status_` int(11) NULL DEFAULT NULL,
  `description_` varchar(255)  NULL DEFAULT NULL,
  `version_` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id_`) USING BTREE,
  UNIQUE INDEX `login`(`login_id`) USING BTREE,
  UNIQUE INDEX `idx_user_login`(`login_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_t
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
