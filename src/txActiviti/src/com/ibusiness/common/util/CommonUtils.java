package com.ibusiness.common.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 共用方法类
 * 
 * @author JiangBo
 *
 */
public class CommonUtils {

    /**
     * 单例模式
     */
    private static CommonUtils instance = new CommonUtils();
    private CommonUtils() {}
    public static CommonUtils getInstance() {
        return instance;
    }
    
    /**
     * 字符串,日期类型转换用变量
     */
    private SimpleDateFormat ymdhms = new SimpleDateFormat(Constants.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
    /**
     * Log4j
     */
    private static Logger logger = LoggerFactory.getLogger(CommonUtils.class);
    
	/**
	 * 导出Excel方法
	 * 
	 * @param request
	 * @param response
	 * @param excelContext 导出内容,拼接成table字符串
	 */
	public static void exportExcel(HttpServletRequest request, HttpServletResponse response, String excelContext) {
		try {
			// 设置request的编码方式，防止中文乱码
			request.setCharacterEncoding("UTF-8");
			// 定义导出文件的格式的字符串
			response.setContentType("application/vnd.ms-excel");// 设置导出文件格式
			String fileName = request.getParameter("name");// 设置导出的文件名称
	        
	        fileName+= "("+UtilDateFormat.getDate("yyyy-MM-dd　HH时mm分ss")+")";
	        
			String recommendedName = new String(fileName.getBytes("GBK"), "iso-8859-1");
			recommendedName = recommendedName+".xls";
			response.setHeader("Content-Disposition", "attachment; filename=" + recommendedName );//
			response.resetBuffer();
			// 利用输出输入流导出文件
			ServletOutputStream sos;
			sos = response.getOutputStream();
			sos.write(excelContext.getBytes());
			sos.flush();
			sos.close();
		} catch (UnsupportedEncodingException e) {
		    logger.error("==========================导出Excel方法UnsupportedEncodingException:" + e.toString());
		} catch (IOException e) {
		    logger.error("==========================导出Excel方法IOException:" + e.toString());
		}
	}
	
    /**
     * 判断日期之间的天数
     * 
     * @return
     */
    @SuppressWarnings("deprecation")
    public boolean isDaysBetween(String startTime, String endTime, int number) {
        try {
            Date startDate = ymdhms.parse(startTime.replace("T", " "));
            startDate.setDate(startDate.getDate() + number);
            if (startDate.after(ymdhms.parse(endTime.replace("T", " ")))) {
                return true;
            }
        } catch (ParseException e) {
            logger.error("==========================导出Excel方法 ParseException:" + e.toString());
        }
        return false;
    }
    
    /**
     * 判断字符串是否为bull
     * @param str
     * @return true 为空 false 不为空
     */
    public static boolean isNull(String str) {
        return (null == str || "".equals(str) || "null".equals(str)) ? true : false;
    }
    
    /**
     * Double格式化,返回一位小数
     * @param temp
     * @return
     */
    public static String formatDoubleBy1Decimal(double temp) {
        return new DecimalFormat("0.0").format(temp);
    }
    /**
     * Double格式化,返回一位小数
     * @param temp
     * @return
     */
    public static String formatDoubleBy2Decimal(double temp) {
        return new DecimalFormat("0.00").format(temp);
    }
    /**
     * 链接JSON字符串，是否加逗号分隔
     * @return
     */
    public static String appendJsonString(String strOne, String strTwo, boolean flag) {
        if (flag) {
            strOne = strOne + ",";
        }
        strOne = strOne + strTwo;
        return strOne;
    }
}
