-------------------------------------------------------------------------------
--  user repo
-------------------------------------------------------------------------------
CREATE TABLE USER_REPO(
        ID BIGINT auto_increment,
		CODE VARCHAR(50),
        NAME VARCHAR(50),
		REF varchar(64),
		SCOPE_ID VARCHAR(50),
        CONSTRAINT PK_U_USER_REPO PRIMARY KEY(ID)
) engine=innodb;

-------------------------------------------------------------------------------
--  user schema
-------------------------------------------------------------------------------
CREATE TABLE USER_SCHEMA(
        ID BIGINT auto_increment,
	CODE VARCHAR(50),
        NAME VARCHAR(50),
        TYPE VARCHAR(200),
	READ_ONLY INTEGER,
	NOT_NULL INTEGER,
	UNIQUE_CONSTRAINT INTEGER,
	VALIDATOR VARCHAR(200),
	CONVERSION_PATTERN VARCHAR(200),
	MULTIPLE INTEGER,
	ENUMERATION_KEYS VARCHAR(200),
	ENUMERATION_VALUES VARCHAR(200),
	USER_REPO_ID BIGINT,
	SCOPE_ID VARCHAR(50),
        CONSTRAINT PK_USER_SCHEMA PRIMARY KEY(ID),
        CONSTRAINT FK_USER_SCHEMA_REPO FOREIGN KEY(USER_REPO_ID) REFERENCES USER_REPO(ID)
) engine=innodb;

-------------------------------------------------------------------------------
--  user base
-------------------------------------------------------------------------------
CREATE TABLE USER_BASE(
        ID BIGINT auto_increment,
        USERNAME VARCHAR(50),
		DISPLAY_NAME VARCHAR(50),
        PASSWORD VARCHAR(50),
        STATUS INTEGER,
		REF varchar(64),
		USER_REPO_ID BIGINT,
		SCOPE_ID VARCHAR(50),
		email varchar(100),
		mobile varchar(50),
        CONSTRAINT PK_USER_BASE PRIMARY KEY(ID),
        CONSTRAINT FK_USER_BASE_REPO FOREIGN KEY(USER_REPO_ID) REFERENCES USER_REPO(ID)
) engine=innodb;

-------------------------------------------------------------------------------
--  user attr
-------------------------------------------------------------------------------
CREATE TABLE USER_ATTR(
        ID BIGINT auto_increment,
	BOOLEAN_VALUE INTEGER,
	DATE_VALUE TIMESTAMP,
	LONG_VALUE BIGINT,
	DOUBLE_VALUE DOUBLE,
	STRING_VALUE VARCHAR(200),
	USER_BASE_ID BIGINT,
	USER_SCHEMA_ID BIGINT,
	SCOPE_ID VARCHAR(50),
        CONSTRAINT PK_USER_ATTR PRIMARY KEY(ID),
        CONSTRAINT FK_USER_ATTR_USER_BASE FOREIGN KEY(USER_BASE_ID) REFERENCES USER_BASE(ID),
	CONSTRAINT FK_USER_ATTR_USER_SCHEMA FOREIGN KEY(USER_SCHEMA_ID) REFERENCES USER_SCHEMA(ID)
) engine=innodb;
