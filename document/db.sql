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


insert into auth_role(role)
values ('admin');
insert into auth_role(role)
values ('teacher');
insert into auth_role(role)
values ('student');
insert into auth_role(role)
values ('primary');
insert into auth_role(role)
values ('monitor');

insert into auth_user_role(user_id, role_id)
values (1, 2);
insert into auth_user_role(user_id, role_id)
values (1, 4);
insert into auth_user_role(user_id, role_id)
values (1, 6);
insert into auth_user_role(user_id, role_id)
values (2, 1);
insert into auth_user_role(user_id, role_id)
values (2, 3);
insert into auth_user_role(user_id, role_id)
values (2, 5);

insert into auth_permission(permission, `description`)
values ('/auth/addUser/**', '添加用户');
insert into auth_permission(permission, `description`)
values ('/auth/getUser/**', '查询用户');
insert into auth_permission(permission, `description`)
values ('/auth/updateUser/**', '更新用户');
insert into auth_permission(permission, `description`)
values ('/auth/deleteUser/**', '删除用户');
insert into auth_permission(permission, `description`)
values ('/auth/getUsers/**', '分页查询用户');

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

select p.permission, r.role
from auth_permission p
         left join auth_role_permission rp on p.id = rp.permission_id
         left join auth_role r on rp.role_id = r.id;