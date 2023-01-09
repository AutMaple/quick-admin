CREATE DATABASE IF NOT EXISTS quick_admin;

USE quick_admin;

DROP TABLE IF EXISTS `auth_permission`;
CREATE TABLE `auth_permission`
(
    `id`          BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
    `permission`  VARCHAR(100) DEFAULT NULL COMMENT '权限',
    `description` VARCHAR(100) DEFAULT NULL COMMENT '描述',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB
  DEFAULT CHARSET = UTF8MB4
  ROW_FORMAT = DYNAMIC COMMENT = '权限表';

DROP TABLE IF EXISTS `auth_user`;
CREATE TABLE `auth_user`
(
    `id`       BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `username` VARCHAR(25) DEFAULT '' COMMENT '用户名',
    `password` VARCHAR(50) DEFAULT '' COMMENT '密码',
    `email`    VARCHAR(50) DEFAULT '' COMMENT '邮箱',
    `gender`   BIT(2)      DEFAULT B'10' COMMENT '性别：0 -> 男; 1-> 女; 2 -> 未知',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB
  DEFAULT CHARSET = UTF8MB4
  ROW_FORMAT = DYNAMIC COMMENT = '用户表';

DROP TABLE IF EXISTS `auth_role`;
CREATE TABLE `auth_role`
(
    `id`   BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `role` VARCHAR(25) DEFAULT '' COMMENT '角色',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB
  DEFAULT CHARSET = UTF8MB4
  ROW_FORMAT = DYNAMIC COMMENT = '角色表';

DROP TABLE IF EXISTS `auth_user_role`;
CREATE TABLE `auth_user_role`
(
    `id`      BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户 ID',
    `role_id` BIGINT DEFAULT NULL COMMENT '角色 ID',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB
  DEFAULT CHARSET = UTF8MB4
  ROW_FORMAT = DYNAMIC COMMENT = '用户角色关联表';

DROP TABLE IF EXISTS `auth_role_permission`;
CREATE TABLE `auth_role_permission`
(
    `id`            BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `role_id`       BIGINT DEFAULT NULL COMMENT '角色 ID',
    `permission_id` BIGINT DEFAULT NULL COMMENT '权限 ID',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB
  DEFAULT CHARSET = UTF8MB4
  ROW_FORMAT = DYNAMIC COMMENT = '角色权限关联表';


INSERT INTO auth_role(role)
VALUES ('admin');
INSERT INTO auth_role(role)
VALUES ('teacher');
INSERT INTO auth_role(role)
VALUES ('student');
INSERT INTO auth_role(role)
VALUES ('primary');
INSERT INTO auth_role(role)
VALUES ('monitor');

INSERT INTO auth_user_role(user_id, role_id)
VALUES (1, 2);
INSERT INTO auth_user_role(user_id, role_id)
VALUES (1, 4);
INSERT INTO auth_user_role(user_id, role_id)
VALUES (1, 6);
INSERT INTO auth_user_role(user_id, role_id)
VALUES (2, 1);
INSERT INTO auth_user_role(user_id, role_id)
VALUES (2, 3);
INSERT INTO auth_user_role(user_id, role_id)
VALUES (2, 5);

INSERT INTO auth_permission(permission, `description`)
VALUES ('/auth/addUser/**', '添加用户');
INSERT INTO auth_permission(permission, `description`)
VALUES ('/auth/getUser/**', '查询用户');
INSERT INTO auth_permission(permission, `description`)
VALUES ('/auth/updateUser/**', '更新用户');
INSERT INTO auth_permission(permission, `description`)
VALUES ('/auth/deleteUser/**', '删除用户');
INSERT INTO auth_permission(permission, `description`)
VALUES ('/auth/getUsers/**', '分页查询用户');

INSERT INTO quick_admin.auth_user (username, password, email, gender)
VALUES ('AutMaple', '$2a$10$p3vrOPqOtjEgZt/WhP5/AuYzDcSjWvNBRUAKdhNK2qntu8pxknFp6', 'autmaple609@qq.com', b'00');
INSERT INTO quick_admin.auth_user (username, password, email, gender)
VALUES ('Irvin', '$2a$10$p3vrOPqOtjEgZt/WhP5/AuYzDcSjWvNBRUAKdhNK2qntu8pxknFp6', 'irvin1111@qq.com', b'00');

INSERT INTO quick_admin.auth_role_permission (id, role_id, permission_id)
VALUES (1, 1, 2);
INSERT INTO quick_admin.auth_role_permission (id, role_id, permission_id)
VALUES (2, 2, 1);
INSERT INTO quick_admin.auth_role_permission (id, role_id, permission_id)
VALUES (3, 2, 2);
INSERT INTO quick_admin.auth_role_permission (id, role_id, permission_id)
VALUES (4, 2, 3);
INSERT INTO quick_admin.auth_role_permission (id, role_id, permission_id)
VALUES (5, 2, 4);
INSERT INTO quick_admin.auth_role_permission (id, role_id, permission_id)
VALUES (6, 2, 5);
INSERT INTO quick_admin.auth_role_permission (id, role_id, permission_id)
VALUES (7, 2, 6);

DROP TABLE IF EXISTS `auth_menu`;

CREATE TABLE `auth_menu`
(
    `id`        BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `name`      VARCHAR(50)  DEFAULT NULL COMMENT '菜单名',
    `url`       VARCHAR(100) DEFAULT NULL COMMENT 'URL',
    `icon`      VARCHAR(50)  DEFAULT NULL COMMENT 'ICON',
    `parent_id` BIGINT       DEFAULT NULL COMMENT '父菜单 ID',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB
  DEFAULT CHARSET = UTF8MB4
  ROW_FORMAT = DYNAMIC COMMENT '菜单表';

DROP TABLE IF EXISTS `auth_menu_role`;

CREATE TABLE `auth_menu_role`
(
    `id`      BIGINT NOT NULL AUTO_INCREMENT COMMEnt 'id',
    `menu_id` BIGINT DEFAULT NULL COMMENT '菜单 ID',
    `role_id` BIGINT DEFAULT NULL COMMENT '角色 ID',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB
  DEFAULT CHARSET = UTF8MB4
  ROW_FORMAT = DYNAMIC COMMENT = '角色菜单表';

INSERT INTO quick_admin.auth_menu (id, name, url, icon, parent_id)
VALUES (1, '首页', '/index', 'home', -1);
INSERT INTO quick_admin.auth_menu (id, name, url, icon, parent_id)
VALUES (2, '组件', null, 'component', -1);
INSERT INTO quick_admin.auth_menu (id, name, url, icon, parent_id)
VALUES (3, '角色权限', '/role/permissions', 'permission', 2);
INSERT INTO quick_admin.auth_menu (id, name, url, icon, parent_id)
VALUES (4, '图标', null, 'icon', 2);
INSERT INTO quick_admin.auth_menu (id, name, url, icon, parent_id)
VALUES (5, '表格', '/component/table', 'table', 2);
INSERT INTO quick_admin.auth_menu (id, name, url, icon, parent_id)
VALUES (6, '常规图标', '/icon/normal', 'normalIcon', 4);
INSERT INTO quick_admin.auth_menu (id, name, url, icon, parent_id)
VALUES (7, '多彩图标', '/icon/colorful', 'colorfulIcon', 4);
INSERT INTO quick_admin.auth_menu (id, name, url, icon, parent_id)
VALUES (8, '地图', '/component/map', 'map', 2);
INSERT INTO quick_admin.auth_menu (id, name, url, icon, parent_id)
VALUES (9, '配置', null, 'config', -1);
INSERT INTO quick_admin.auth_menu (id, name, url, icon, parent_id)
VALUES (10, '用户管理', '/config/user', 'user', 9);
INSERT INTO quick_admin.auth_menu (id, name, url, icon, parent_id)
VALUES (11, '角色管理', '/config/role', 'role', 9);
INSERT INTO quick_admin.auth_menu (id, name, url, icon, parent_id)
VALUES (12, '菜单管理', '/config/menu', 'menu', 9);

INSERT INTO quick_admin.auth_menu_role (role_id, menu_id)
VALUES (1, 1);
INSERT INTO quick_admin.auth_menu_role (role_id, menu_id)
VALUES (1, 3);
INSERT INTO quick_admin.auth_menu_role (role_id, menu_id)
VALUES (1, 5);
INSERT INTO quick_admin.auth_menu_role (role_id, menu_id)
VALUES (1, 6);
INSERT INTO quick_admin.auth_menu_role (role_id, menu_id)
VALUES (1, 7);
INSERT INTO quick_admin.auth_menu_role (role_id, menu_id)
VALUES (1, 8);
INSERT INTO quick_admin.auth_menu_role (role_id, menu_id)
VALUES (1, 10);
INSERT INTO quick_admin.auth_menu_role (role_id, menu_id)
VALUES (1, 11);
INSERT INTO quick_admin.auth_menu_role (role_id, menu_id)
VALUES (1, 12);
