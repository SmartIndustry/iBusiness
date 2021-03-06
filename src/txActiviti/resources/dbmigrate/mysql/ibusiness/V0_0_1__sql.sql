/*==============================================================*/
/* 业务模块组件管理表   */
/*==============================================================*/
CREATE TABLE IB_CONF_SERVICE_MODULE (
    ID  VARCHAR(64),
	PACKAGENAME VARCHAR(128),
	MODULENAME VARCHAR(128),
	PARENTID VARCHAR(64),
	TYPEID VARCHAR(8),
	SUBNAME VARCHAR(32),
        CONSTRAINT PK_TX_CONF_TABLE PRIMARY KEY(ID)
) ENGINE=INNODB;

/*==============================================================*/
/* config table info 业务表结构管理表   */
/*==============================================================*/
CREATE TABLE IB_CONF_TABLE(
    UUID  VARCHAR(64),
    PACKAGENAME VARCHAR(128),
	TABLENAME VARCHAR(128),
	TABLENAMECOMMENT VARCHAR(128),
        CONSTRAINT PK_IB_CONF_TABLE PRIMARY KEY(UUID)
) ENGINE=INNODB;

/*==============================================================*/
/* config table columns info 业务表字段结构管理表   */
/*==============================================================*/
CREATE TABLE TX_CONF_TABLE_COLUMNS (
		tableName VARCHAR(128),
		columnValue VARCHAR(128),
		columnName VARCHAR(128),
		columnType VARCHAR(32),
		columnSize VARCHAR(8),
		isNull VARCHAR(8),
		defaultValue VARCHAR(32),
		columnNo INTEGER,
        CONSTRAINT PK_TX_CONF_TABLE_COL PRIMARY KEY(tableName,COLUMNVALUE)
) ENGINE=INNODB;
