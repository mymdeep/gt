DROP DATABASE
IF EXISTS MyDemo;	-- 如果存在数据库MyDemo，那么就删除它

CREATE DATABASE MyDemo;	-- 创建数据库MyDemo

USE MyDemo;	-- 使用数据库MyDemo

DROP TABLE
IF EXISTS OAT;	-- 如果存在表OAT，那么就删除它

-- 创建运营账号表（OperatingAccountTable）
CREATE TABLE OAT (
	-- 01_用户名_主键
	username NVARCHAR (20) NOT NULL,
	-- 02_用户编号_唯一，格式OAxxx，OA代表运营账号，xxx为具体编号
	userid VARCHAR (10) NOT NULL,
	-- 03_密码
	password VARCHAR (20) NOT NULL,
	-- 04_性别，M为男性，F为女性
	sex VARCHAR (1) NOT NULL,
	-- 05_账号状态，0为禁用，1为启用，默认启用
	oastatus INT NOT NULL DEFAULT 1,
	PRIMARY KEY (username),
	UNIQUE KEY (userid)
) ENGINE = INNODB DEFAULT CHARSET = utf8;

-- 以下为测试数据
-- 运营账号
INSERT INTO OAT
VALUES(
	'lisi',		-- 01_用户名_主键
	'OA0',		-- 02_用户编号_唯一，格式OAxxx，OA代表运营账号，xxx为具体编号
	'lisi',		-- 03_密码
	'M',		-- 04_性别，M为男性，F为女性
	1			-- 05_账号状态，0为禁用，1为启用，默认启用
);
INSERT INTO OAT
VALUES(
	'test',
	'OA128',
	'test',
	'F',
	0
);
INSERT INTO OAT
VALUES(
	'hexin',
	'OA200',
	'hexin',
	'F',
	1
);

INSERT INTO OAT
VALUES(
	'lapulande',
	'OA11',
	'lapulande',
	'M',
	0
);
INSERT INTO OAT
VALUES(
	'935478677',
	'OA205',
	'935478677',
	'M',
	1
);
INSERT INTO OAT
VALUES(
	'liuli',
	'OA300',
	'liuli',
	'F',
	0
);