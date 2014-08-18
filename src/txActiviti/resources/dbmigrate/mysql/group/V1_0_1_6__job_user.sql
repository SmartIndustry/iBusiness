
-------------------------------------------------------------------------------
--  org company
-------------------------------------------------------------------------------
CREATE TABLE ORG_COMPANY(
        ID BIGINT auto_increment,
        CODE VARCHAR(50),
        NAME VARCHAR(200),
	DESCN VARCHAR(200),
        STATUS INTEGER,
	REF VARCHAR(200),
	SCOPE_ID VARCHAR(50),
        CONSTRAINT PK_ORG_COMPANY PRIMARY KEY(ID)
) engine=innodb;

-------------------------------------------------------------------------------
--  org department
-------------------------------------------------------------------------------
CREATE TABLE ORG_DEPARTMENT(
        ID BIGINT auto_increment,
        CODE VARCHAR(50),
        NAME VARCHAR(200),
	DESCN VARCHAR(200),
        STATUS INTEGER,
	REF VARCHAR(200),
	SCOPE_ID VARCHAR(50),
        CONSTRAINT PK_ORG_DEPARTMENT PRIMARY KEY(ID)
) engine=innodb;

-------------------------------------------------------------------------------
--  org group
-------------------------------------------------------------------------------
CREATE TABLE ORG_GROUP(
        ID BIGINT auto_increment,
        NAME VARCHAR(200),
	DESCN VARCHAR(200),
        STATUS INTEGER,
	REF VARCHAR(200),
	SCOPE_ID VARCHAR(50),
        CONSTRAINT PK_ORG_GROUP PRIMARY KEY(ID)
) engine=innodb;

-------------------------------------------------------------------------------
--  org position type
-------------------------------------------------------------------------------
CREATE TABLE ORG_POSITION_TYPE(
        ID BIGINT auto_increment,
        NAME VARCHAR(200),
	SCOPE_ID VARCHAR(50),
        CONSTRAINT PK_ORG_POSITION_TYPE PRIMARY KEY(ID)
) engine=innodb;

-------------------------------------------------------------------------------
--  org position
-------------------------------------------------------------------------------
CREATE TABLE ORG_POSITION(
        ID BIGINT auto_increment,
        NAME VARCHAR(200),
	PRIORITY INTEGER,
	TYPE_ID BIGINT,
	SCOPE_ID VARCHAR(50),
        CONSTRAINT PK_ORG_POSITION PRIMARY KEY(ID),
        CONSTRAINT FK_ORG_POSITION_TYPE FOREIGN KEY(TYPE_ID) REFERENCES ORG_POSITION_TYPE(ID)
) engine=innodb;

-------------------------------------------------------------------------------
--  job grade
-------------------------------------------------------------------------------
CREATE TABLE JOB_GRADE(
        ID BIGINT auto_increment,
	NAME VARCHAR(50),
	SCOPE_ID VARCHAR(50),
        CONSTRAINT PK_JOB_GRADE PRIMARY KEY(ID)
) engine=innodb;

-------------------------------------------------------------------------------
--  job level
-------------------------------------------------------------------------------
CREATE TABLE JOB_LEVEL(
        ID BIGINT auto_increment,
	NAME VARCHAR(50),
	GRADE_ID BIGINT,
	SCOPE_ID VARCHAR(50),
        CONSTRAINT PK_JOB_LEVEL PRIMARY KEY(ID),
        CONSTRAINT FK_JOB_LEVEL_GRADE FOREIGN KEY(GRADE_ID) REFERENCES JOB_GRADE(ID)
) engine=innodb;

-------------------------------------------------------------------------------
--  job type
-------------------------------------------------------------------------------
CREATE TABLE JOB_TYPE(
        ID BIGINT auto_increment,
	NAME VARCHAR(50),
	PARENT_ID BIGINT,
	SCOPE_ID VARCHAR(50),
        CONSTRAINT PK_JOB_TYPE PRIMARY KEY(ID),
        CONSTRAINT FK_JOB_TYPE_PARENT FOREIGN KEY(PARENT_ID) REFERENCES JOB_TYPE(ID)
) engine=innodb;

-------------------------------------------------------------------------------
--  job title
-------------------------------------------------------------------------------
CREATE TABLE JOB_TITLE(
        ID BIGINT auto_increment,
	NAME VARCHAR(50),
	SCOPE_ID VARCHAR(50),
        CONSTRAINT PK_JOB_TITLE PRIMARY KEY(ID)
) engine=innodb;

-------------------------------------------------------------------------------
--  job info
-------------------------------------------------------------------------------
CREATE TABLE JOB_INFO(
        ID BIGINT auto_increment,
	NAME VARCHAR(50),
	LEVEL_ID BIGINT,
	TYPE_ID BIGINT,
	TITLE_ID BIGINT,
	SCOPE_ID VARCHAR(50),
        CONSTRAINT PK_JOB_INFO PRIMARY KEY(ID),
        CONSTRAINT FK_JOB_INFO_LEVEL FOREIGN KEY(LEVEL_ID) REFERENCES JOB_LEVEL(ID),
        CONSTRAINT FK_JOB_INFO_TYPE FOREIGN KEY(TYPE_ID) REFERENCES JOB_TYPE(ID),
        CONSTRAINT FK_JOB_INFO_TITLE FOREIGN KEY(TITLE_ID) REFERENCES JOB_TITLE(ID)
) engine=innodb;

-------------------------------------------------------------------------------
--  job user
-------------------------------------------------------------------------------
CREATE TABLE JOB_USER(
        ID BIGINT auto_increment,
	USER_REF VARCHAR(50),
	JOB_INFO_ID BIGINT,
	SCOPE_ID VARCHAR(50),
        CONSTRAINT PK_JOB_USER PRIMARY KEY(ID),
        CONSTRAINT FK_JOB_USER_JOB FOREIGN KEY(JOB_INFO_ID) REFERENCES JOB_INFO(ID)
) engine=innodb;

