package com.ibusiness.table.entity;

/**
 * 业务表管理Bean, 对应 ib_conf_table表
 * 
 * @author JiangBo
 *
 */
public class ConfTable {
	// UUID
    private String uuId;
    // 模块包名
    private String packageName;
    // 表名
    private String tableName;
    // 表名含义
    private String tableNameComment;
	/**
     * 取得tableName
     * @return the tableName
     */
    public String getTableName() {
        return tableName;
    }
    /**
     * 设置tableName
     * @param tableName the tableName to set
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    /**
     * 取得tableNameComment
     * @return the tableNameComment
     */
    public String getTableNameComment() {
        return tableNameComment;
    }
    /**
     * 设置tableNameComment
     * @param tableNameComment the tableNameComment to set
     */
    public void setTableNameComment(String tableNameComment) {
        this.tableNameComment = tableNameComment;
    }
	/**
	 * 取得 uuid
	 * @return the uuId
	 */
	public String getUuId() {
		return uuId;
	}
	/**
	 * 设置 uuId
	 * @param uuId the uuId to set
	 */
	public void setUuId(String uuId) {
		this.uuId = uuId;
	}
    /**
     * @return the packageName
     */
    public String getPackageName() {
        return packageName;
    }
    /**
     * @param packageName the packageName to set
     */
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
