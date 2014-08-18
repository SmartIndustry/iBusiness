package com.ibusiness.codeGenerate.code.generate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.ibusiness.codeGenerate.util.CodeResourceUtil;
import com.ibusiness.codeGenerate.util.CodeStringUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 代码生成工厂类
 * 
 * @author JiangBo
 *
 */
public class CodeFactory {

    private ICallBack callBack;

    public CodeFactory() {
    }

    public Configuration getConfiguration() throws IOException {
        Configuration configuration = new Configuration();
        String s = getTemplatePath();
        File file = new File(s);
        configuration.setDirectoryForTemplateLoading(file);
        configuration.setLocale(Locale.CHINA);
        configuration.setDefaultEncoding("UTF-8");
        return configuration;
    }

    /**
     * 生成文件
     * 
     * @param templateName
     * @param type
     * @param data
     */
    public void generateFile(String templateName, String type, Map<String, Object> data) {
        try {
            String entityPackage = data.get("entityPackage").toString();
            String entityName = data.get("entityName").toString();
            // 取得生成文件路径
            String fileNamePath = getCodePath(type, entityPackage, entityName);
            String fileDir = StringUtils.substringBeforeLast(fileNamePath, "/");
            Template template = getConfiguration().getTemplate(templateName);
            FileUtils.forceMkdir(new File(fileDir + "/"));
            OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(fileNamePath),
                    CodeResourceUtil.SYSTEM_ENCODING);
            template.process(data, out);
            out.close();
        } catch (TemplateException templateexception) {
            templateexception.printStackTrace();
        } catch (IOException ioexception) {
            ioexception.printStackTrace();
        }
    }

    public static String getProjectPath() {
        String path = System.getProperty("user.dir").replace("\\", "/") + "/";
        return path;
    }

    /**
     * 取得类路径
     * 
     * @return
     */
    public String getClassPath() {
        String path = Thread.currentThread().getContextClassLoader().getResource("./").getPath();
        return path;
    }

    /**
     * main方法
     * 
     * @param args
     */
    public static void main(String args[]) {
        System.out.println(getProjectPath());
    }

    /**
     * 取得模板路径
     * 
     * @return
     */
    public String getTemplatePath() {
        String path = getClassPath() + CodeResourceUtil.TEMPLATEPATH;
        return path;
    }

    /**
     * 取得代码路径
     * 
     * @return
     */
    public String getCodePath(String type, String entityPackage, String entityName) {
        String path = getProjectPath();
        StringBuilder str = new StringBuilder();
        if (StringUtils.isNotBlank(type)) {
            String codeType = CodeType.valueOf(type).getValue();
            str.append(path);
            // JSP文件路径
            if (("jsp".equals(type)) || ("jspList".equals(type))) {
                String jspPath = CodeResourceUtil.JSPPATH;
                // 设置JSP路径
                jspPath = jspPath.replace("/com/ibusiness", "");
                str.append(jspPath);
            } else {
                str.append(CodeResourceUtil.CODEPATH);
            }
            // 判断相等(忽略大小写)
            if ("Action".equalsIgnoreCase(codeType)) {
                str.append(StringUtils.lowerCase("action"));
            } else if ("ServiceImpl".equalsIgnoreCase(codeType)) {
                str.append(StringUtils.lowerCase("service/impl"));
            } else if ("ServiceI".equalsIgnoreCase(codeType)) {
                str.append(StringUtils.lowerCase("service"));
            } else if (!"List".equalsIgnoreCase(codeType)) {
                str.append(StringUtils.lowerCase(codeType));
            }
            str.append("/");
            str.append(StringUtils.lowerCase(entityPackage));
            str.append("/");
            if ("jsp".equals(type) || ("jspList".equals(type))) {
                String jspName = StringUtils.capitalize(entityName);
                str.append(CodeStringUtils.getInitialSmall(jspName));
                str.append(codeType);
                // 显示页面 还是插入页面
                if ("jsp".equals(type)) {
                    str.append("-input");
                } if ("jspList".equals(type)) {
                    str.append("-list");
                }
                str.append(".jsp");
            } else {
                str.append(StringUtils.capitalize(entityName));
                str.append(codeType);
                str.append(".java");
            }
        } else {
            throw new IllegalArgumentException("type is null");
        }
        return str.toString();
    }

    /**
     * invoke
     * 
     * @param templateFileName
     * @param type
     */
    public void invoke(String templateFileName, String type) {
        Map<String, Object> data = new HashMap<String, Object>();
        data = callBack.execute();
        generateFile(templateFileName, type, data);
    }

    public ICallBack getCallBack() {
        return callBack;
    }

    public void setCallBack(ICallBack icallback) {
        callBack = icallback;
    }

    /**
     * 枚举类
     * 
     * @author Administrator
     *
     */
    public enum CodeType {
        serviceImpl("ServiceImpl"), service("Service"), controller("Controller"), page("Page"), entity("Entity"), jsp(""), jspList("");
        // 成员变量 
        private String type;

        private CodeType(String type) {
            this.type = type;
        }
        public String getValue() {
            return this.type;
        }
    }
}

