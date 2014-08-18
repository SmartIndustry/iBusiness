package com.ibusiness.codeGenerate;

import com.ibusiness.codeGenerate.code.generate.CodeTreeGenerate;

/**
 * 自动生成Dao,Service,Action及Dao和Service测试类
 */
public class TreeUtil {

	/*
	 * 参数为Entity类名(注意第一个字符大写)
	 */
	public static void main(String[] args) {
		String entityPackage = "demo";// 包名（小写）
		String entityName = "JeecgGroup";// 实体名（首字母大写）
		String tableName = "jeecg_group"; // 表名
		String ftlDescription = "组织机构";// 功能描述
		new CodeTreeGenerate(entityPackage, entityName, tableName,ftlDescription).generateToFile();
		System.out.println("-- success --");
	}

}
