
-------------------------------------------------------------------------------
--  workcal type
-------------------------------------------------------------------------------
CREATE TABLE WORKCAL_TYPE(
        ID BIGINT auto_increment,
	NAME VARCHAR(50),
        CONSTRAINT PK_WORKCAL_TYPE PRIMARY KEY(ID)
) engine=innodb;

-------------------------------------------------------------------------------
--  workcal rule
-------------------------------------------------------------------------------
CREATE TABLE WORKCAL_RULE(
        ID BIGINT auto_increment,
	YEAR INT,
        WEEK INT,
	NAME VARCHAR(50),
	WORK_DATE TIMESTAMP,
	STATUS INT,
	TYPE_ID BIGINT,
        CONSTRAINT PK_WORKCAL_RULE PRIMARY KEY(ID),
	CONSTRAINT FK_WORKCAL_RULE_TYPE FOREIGN KEY(TYPE_ID) REFERENCES WORKCAL_TYPE(ID)
) engine=innodb;

-------------------------------------------------------------------------------
--  workcal part
-------------------------------------------------------------------------------
CREATE TABLE WORKCAL_PART(
        ID BIGINT auto_increment,
        SHIFT INT,
	START_TIME VARCHAR(5),
	END_TIME VARCHAR(5),
	RULE_ID BIGINT,
        CONSTRAINT PK_WORKCAL_PART PRIMARY KEY(ID),
	CONSTRAINT FK_WORKCAL_PART_RULE FOREIGN KEY(RULE_ID) REFERENCES WORKCAL_RULE(ID)
) engine=innodb;

