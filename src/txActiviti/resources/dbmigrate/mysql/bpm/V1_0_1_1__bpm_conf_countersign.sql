-------------------------------------------------------------------------------
--  流程分类
-------------------------------------------------------------------------------
CREATE TABLE BPM_CATEGORY(
	ID BIGINT auto_increment,
	NAME VARCHAR(200),
	PRIORITY INTEGER,
        CONSTRAINT PK_BPM_CATEGORY PRIMARY KEY(ID)
) engine=innodb;
-------------------------------------------------------------------------------
--  流程定义-流程管理
-------------------------------------------------------------------------------
CREATE TABLE BPM_CONF_BASE(
	ID BIGINT auto_increment,
	PROCESS_DEFINITION_ID VARCHAR(200),
	PROCESS_DEFINITION_KEY VARCHAR(200),
	PROCESS_DEFINITION_VERSION INT,
        CONSTRAINT PK_BPM_CONF_BASE PRIMARY KEY(ID)
) engine=innodb;
-------------------------------------------------------------------------------
--  流程定义-分类管理
-------------------------------------------------------------------------------
CREATE TABLE BPM_PROCESS(
	ID BIGINT auto_increment,
	NAME VARCHAR(200),
	CATEGORY_ID BIGINT,
	PRIORITY INTEGER,
	DESCN VARCHAR(200),
	CODE VARCHAR(64),
	CONF_BASE_ID BIGINT,
	USE_TASK_CONF INTEGER,
        CONSTRAINT PK_BPM_PROCESS PRIMARY KEY(ID),
        CONSTRAINT FK_BPM_PROCESS_CATEGORY_ID FOREIGN KEY(CATEGORY_ID) REFERENCES BPM_CATEGORY(ID)
) engine=innodb;

ALTER TABLE BPM_PROCESS ADD CONSTRAINT FK_BPM_PROCESS_CONF_BASE FOREIGN KEY(CONF_BASE_ID) REFERENCES BPM_CONF_BASE(ID);
-------------------------------------------------------------------------------
--  流程代理信息表
-------------------------------------------------------------------------------
CREATE TABLE BPM_DELEGATE_INFO(
	ID BIGINT auto_increment,
	ASSIGNEE VARCHAR(200),
	ATTORNEY VARCHAR(200),
	START_TIME TIMESTAMP,
	END_TIME TIMESTAMP,
	PROCESS_DEFINITION_ID VARCHAR(100),
	STATUS INTEGER,
        CONSTRAINT PK_BPM_DELEGATE_INFO PRIMARY KEY(ID)
) engine=innodb;
-------------------------------------------------------------------------------
--  流程代理历史信息表
-------------------------------------------------------------------------------
CREATE TABLE BPM_DELEGATE_HISTORY(
	ID BIGINT auto_increment,
	ASSIGNEE VARCHAR(200),
	ATTORNEY VARCHAR(200),
	DELEGATE_TIME TIMESTAMP,
	TASK_ID VARCHAR(100),
	STATUS INTEGER,
        CONSTRAINT PK_BPM_DELEGATE_HISTORY PRIMARY KEY(ID)
) engine=innodb;
-------------------------------------------------------------------------------
--  流程任务配置表
-------------------------------------------------------------------------------
CREATE TABLE BPM_TASK_CONF(
	ID BIGINT auto_increment,
	BUSINESS_KEY VARCHAR(200),
	TASK_DEFINITION_KEY VARCHAR(200),
	ASSIGNEE VARCHAR(200),
        CONSTRAINT PK_BPM_TASK_CONF PRIMARY KEY(ID)
) engine=innodb;
-------------------------------------------------------------------------------
--  流程任务管理表
-------------------------------------------------------------------------------
CREATE TABLE BPM_TASK_DEF(
	ID BIGINT auto_increment,
	TASK_DEFINITION_KEY VARCHAR(200),
	ASSIGNEE VARCHAR(200),
	CANDIDATE VARCHAR(200),
	PROCESS_ID BIGINT,
        CONSTRAINT PK_BPM_TASK_DEF PRIMARY KEY(ID),
        CONSTRAINT FK_BPM_TASK_DEF_ID FOREIGN KEY(PROCESS_ID) REFERENCES BPM_PROCESS(ID)
) engine=innodb;
-------------------------------------------------------------------------------
--  流程邮件模板表
-------------------------------------------------------------------------------
CREATE TABLE BPM_MAIL_TEMPLATE(
	ID BIGINT auto_increment,
	NAME VARCHAR(50),
	SUBJECT VARCHAR(100),
	CONTENT VARCHAR(200),
        CONSTRAINT PK_BPM_MAIL_TEMPLATE PRIMARY KEY(ID)
) engine=innodb;
-------------------------------------------------------------------------------
--  流程任务通知管理表
-------------------------------------------------------------------------------
CREATE TABLE BPM_TASK_DEF_NOTICE(
	ID BIGINT auto_increment,
	TASK_DEFINITION_KEY VARCHAR(200),
	TYPE INTEGER,
	RECEIVER VARCHAR(200),
	DUE_DATE VARCHAR(50),
	TEMPLATE_ID BIGINT,
	PROCESS_ID BIGINT,
        CONSTRAINT PK_BPM_TASK_DEF_NOTICE PRIMARY KEY(ID),
        CONSTRAINT FK_BPM_TASK_DEF_NOTICE_PROCESS FOREIGN KEY(PROCESS_ID) REFERENCES BPM_PROCESS(ID),
        CONSTRAINT FK_BPM_TASK_DEF_NOTICE_TEMPLATE FOREIGN KEY(TEMPLATE_ID) REFERENCES BPM_MAIL_TEMPLATE(ID)
) engine=innodb;
-------------------------------------------------------------------------------
--  流程分类定义配置表
-------------------------------------------------------------------------------
CREATE TABLE BPM_CONF_NODE(
	ID BIGINT auto_increment,
	CODE VARCHAR(200),
	NAME VARCHAR(200),
	TYPE VARCHAR(200),
	CONF_USER INT,
	CONF_LISTENER INT,
	CONF_RULE INT,
	CONF_FORM INT,
	CONF_OPERATION INT,
	CONF_NOTICE INT,
	PRIORITY INT,
	CONF_BASE_ID BIGINT,
        CONSTRAINT PK_BPM_CONF_NODE PRIMARY KEY(ID),
        CONSTRAINT FK_BPM_CONF_NODE_CONF_BASE FOREIGN KEY(CONF_BASE_ID) REFERENCES BPM_CONF_BASE(ID)
) engine=innodb;
-------------------------------------------------------------------------------
--  流程分类定义配置人员表
-------------------------------------------------------------------------------
CREATE TABLE BPM_CONF_USER(
	ID BIGINT auto_increment,
	VALUE VARCHAR(200),
	TYPE INT,
	STATUS INT,
	PRIORITY INT,
	NODE_ID BIGINT,
        CONSTRAINT PK_BPM_CONF_USER PRIMARY KEY(ID),
        CONSTRAINT FK_BPM_CONF_USER_NODE FOREIGN KEY(NODE_ID) REFERENCES BPM_CONF_NODE(ID)
) engine=innodb;
-------------------------------------------------------------------------------
--  流程分类定义配置事件表
-------------------------------------------------------------------------------
CREATE TABLE BPM_CONF_LISTENER(
	ID BIGINT auto_increment,
	VALUE VARCHAR(200),
	TYPE INT,
	STATUS INT,
	PRIORITY INT,
	NODE_ID BIGINT,
        CONSTRAINT PK_BPM_CONF_LISTENER PRIMARY KEY(ID),
        CONSTRAINT FK_BPM_CONF_LISTENER_NODE FOREIGN KEY(NODE_ID) REFERENCES BPM_CONF_NODE(ID)
) engine=innodb;
-------------------------------------------------------------------------------
--  流程分类定义配置规则表
-------------------------------------------------------------------------------
CREATE TABLE BPM_CONF_RULE(
	ID BIGINT auto_increment,
	VALUE VARCHAR(200),
	NODE_ID BIGINT,
        CONSTRAINT PK_BPM_CONF_RULE PRIMARY KEY(ID),
        CONSTRAINT FK_BPM_CONF_RULE_NODE FOREIGN KEY(NODE_ID) REFERENCES BPM_CONF_NODE(ID)
) engine=innodb;
-------------------------------------------------------------------------------
--  流程分类定义配置表单表
-------------------------------------------------------------------------------
CREATE TABLE BPM_CONF_FORM(
	ID BIGINT auto_increment,
	VALUE VARCHAR(200),
	TYPE INT,
	ORIGIN_VALUE VARCHAR(200),
	ORIGIN_TYPE INT,
	STATUS INT,
	NODE_ID BIGINT,
        CONSTRAINT PK_BPM_CONF_FORM PRIMARY KEY(ID),
        CONSTRAINT FK_BPM_CONF_FORM_NODE FOREIGN KEY(NODE_ID) REFERENCES BPM_CONF_NODE(ID)
) engine=innodb;
-------------------------------------------------------------------------------
--  流程分类定义配置操作表
-------------------------------------------------------------------------------
CREATE TABLE BPM_CONF_OPERATION(
	ID BIGINT auto_increment,
	VALUE VARCHAR(200),
	PRIORITY INT,
	NODE_ID BIGINT,
        CONSTRAINT PK_BPM_CONF_OPERATION PRIMARY KEY(ID),
        CONSTRAINT FK_BPM_CONF_OPERATION_NODE FOREIGN KEY(NODE_ID) REFERENCES BPM_CONF_NODE(ID)
) engine=innodb;
-------------------------------------------------------------------------------
--  流程分类定义配置提醒表
-------------------------------------------------------------------------------
CREATE TABLE BPM_CONF_NOTICE(
	ID BIGINT auto_increment,
	TYPE INT,
	RECEIVER VARCHAR(200),
	DUE_DATE VARCHAR(50),
	NODE_ID BIGINT,
	TEMPLATE_ID BIGINT,
        CONSTRAINT PK_BPM_CONF_NOTICE PRIMARY KEY(ID),
        CONSTRAINT FK_BPM_CONF_NOTICE_NODE FOREIGN KEY(NODE_ID) REFERENCES BPM_CONF_NODE(ID),
        CONSTRAINT FK_BPM_CONF_NOTICE_TEMPLATE FOREIGN KEY(TEMPLATE_ID) REFERENCES BPM_MAIL_TEMPLATE(ID)
) engine=innodb;
-------------------------------------------------------------------------------
--  流程关联配置表
-------------------------------------------------------------------------------
CREATE TABLE BPM_CONF_COUNTERSIGN(
	ID BIGINT auto_increment,
	SEQUENTIAL INT,
	PARTICIPANT VARCHAR(200),
	TYPE INT,
	RATE INT,
	NODE_ID BIGINT,
        CONSTRAINT PK_BPM_CONF_COUNTERSIGN PRIMARY KEY(ID),
        CONSTRAINT FK_BPM_CONF_COUNTERSIGN_NODE FOREIGN KEY(NODE_ID) REFERENCES BPM_CONF_NODE(ID)
) engine=innodb;

