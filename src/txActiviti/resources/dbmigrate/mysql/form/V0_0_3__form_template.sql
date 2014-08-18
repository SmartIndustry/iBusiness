-------------------------------------------------------------------------------
--  keyvalue record
-------------------------------------------------------------------------------
CREATE TABLE KV_RECORD(
        ID BIGINT auto_increment,
	CATEGORY VARCHAR(200),
	STATUS INT,
	REF VARCHAR(200),
	USER_ID VARCHAR(64),
	CREATE_TIME TIMESTAMP,
        CONSTRAINT PK_KV_RECORD PRIMARY KEY(ID)
) engine=innodb;

-------------------------------------------------------------------------------
--  keyvalue property
-------------------------------------------------------------------------------
CREATE TABLE KV_PROP(
        ID BIGINT auto_increment,
	CODE VARCHAR(200),
	TYPE INT,
	VALUE VARCHAR(200),
	RECORD_ID BIGINT,
        CONSTRAINT PK_KV_PROP PRIMARY KEY(ID),
        CONSTRAINT FK_KV_PROP_RECORD FOREIGN KEY(RECORD_ID) REFERENCES KV_RECORD(ID)
) engine=innodb;

-------------------------------------------------------------------------------
--  form template
-------------------------------------------------------------------------------
create table FORM_TEMPLATE(
	ID BIGINT auto_increment,
	TYPE INT,
	NAME VARCHAR(200),
	CONTENT VARCHAR(2000),
        CONSTRAINT PK_FORM_TEMPLATE PRIMARY KEY(ID)
) engine=innodb;

