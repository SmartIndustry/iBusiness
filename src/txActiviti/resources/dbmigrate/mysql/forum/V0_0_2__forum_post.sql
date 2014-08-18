
-------------------------------------------------------------------------------
--  forum topic
-------------------------------------------------------------------------------
CREATE TABLE FORUM_TOPIC(
        ID BIGINT auto_increment,
	TITLE VARCHAR(200),
	CONTENT VARCHAR(200),
	CREATE_TIME TIMESTAMP,
	UPDATE_TIME TIMESTAMP,
	HIT_COUNT INTEGER,
	POST_COUNT INTEGER,
	USER_ID BIGINT,
        CONSTRAINT PK_FORUM_TOPIC PRIMARY KEY(ID)
) engine=innodb;

-------------------------------------------------------------------------------
--  forum post
-------------------------------------------------------------------------------
CREATE TABLE FORUM_POST(
        ID BIGINT auto_increment,
	CONTENT VARCHAR(200),
	CREATE_TIME TIMESTAMP,
	TOPIC_ID BIGINT,
	USER_ID BIGINT,
	PRIORITY INTEGER,
        CONSTRAINT PK_FORUM_POST PRIMARY KEY(ID),
        CONSTRAINT FK_FORUM_POST_TOPIC FOREIGN KEY(TOPIC_ID) REFERENCES FORUM_TOPIC(ID)
) engine=innodb;

