package com.ibusiness.codeGenerate;

import com.ibusiness.codeGenerate.util.JeecgCodeDeleteUtil;


/**
 * @Description 删除生成代码工具类[一对多删除可能没有剩余，需要手工协助删除]
 * @author jiangBo
 *
 */
public class DeleteUtil {

	public static void main(String[] args) {
		//说明：业务包，会自动读取配置文件
		//【参数一】子包名
		String sub_package = "jeecg";
		//【参数二】实体名字[首字母大写]
		String name = "Demo4Many";
		new JeecgCodeDeleteUtil().delCode(sub_package, name);
		
	}
}
