package com.ibusiness.codeGenerate.code.DbEntity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ibusiness.codeGenerate.code.Columnt;
import com.ibusiness.codeGenerate.util.CodeResourceUtil;
import com.ibusiness.codeGenerate.util.TableConvert;

/**
 * JSP通用数据文件
 * 
 * @author JiangBo
 * 
 */
public class DbFiledToJspUtil {

    private static final Log a_org_apache_commons_logging_Log_static_fld = LogFactory.getLog(DbFiledToJspUtil.class);
    private Connection a_java_sql_Connection_fld;
    private Statement a_java_sql_Statement_fld;
    private String a_java_lang_String_fld;
    private ResultSet a_java_sql_ResultSet_fld;

    public DbFiledToJspUtil() {
    }

    public static void main(String args[]) {
        try {
            List list = (new DbFiledToJspUtil()).readTableColumn("person");
            Columnt columnt;
            for (Iterator iterator = list.iterator(); iterator.hasNext(); System.out.println(columnt.getFieldName()))
                columnt = (Columnt) iterator.next();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * 读取指定表名的表字段List
     * 
     * @param s
     * @return
     */
    public List<Columnt> readTableColumn(String s) {
        ArrayList<Columnt> arraylist = new ArrayList<Columnt>();
        try {
            Class.forName(CodeResourceUtil.DIVER_NAME);
            a_java_sql_Connection_fld = DriverManager.getConnection(CodeResourceUtil.URL, CodeResourceUtil.USERNAME,
                    CodeResourceUtil.PASSWORD);
            a_java_sql_Statement_fld = a_java_sql_Connection_fld.createStatement(1005, 1007);
            if (CodeResourceUtil.DATABASE_TYPE.equals("mysql"))
                a_java_lang_String_fld = MessageFormat
                        .format("select column_name,data_type,column_comment,numeric_precision,numeric_scale,character_maximum_length,is_nullable nullable from information_schema.columns where table_name = {0} and table_schema = {1}",
                                new Object[] {
                                        TableConvert.getV(s.toUpperCase()),
                                        TableConvert.getV(CodeResourceUtil.DATABASE_NAME) });
            if (CodeResourceUtil.DATABASE_TYPE.equals("oracle"))
                a_java_lang_String_fld = MessageFormat
                        .format(" select colstable.column_name column_name, colstable.data_type data_type, commentstable.comments column_comment, colstable.Data_Precision column_precision, colstable.Data_Scale column_scale,colstable.Char_Length,colstable.nullable from user_tab_cols colstable  inner join user_col_comments commentstable  on colstable.column_name = commentstable.column_name  where colstable.table_name = commentstable.table_name  and colstable.table_name = {0}",
                                new Object[] {
                                    TableConvert.getV(s.toUpperCase()) });
            a_java_sql_ResultSet_fld = a_java_sql_Statement_fld.executeQuery(a_java_lang_String_fld);
            a_java_sql_ResultSet_fld.last();
            int i = a_java_sql_ResultSet_fld.getRow();
            int j = i;
            if (j > 0) {
                Columnt columnt = new Columnt();
                if (CodeResourceUtil.JEECG_FILED_CONVERT)
                    columnt.setFieldName(formatField(a_java_sql_ResultSet_fld.getString(1).toLowerCase()));
                else
                    columnt.setFieldName(a_java_sql_ResultSet_fld.getString(1).toLowerCase());
                columnt.setFieldDbName(a_java_sql_ResultSet_fld.getString(1).toUpperCase());
                columnt.setFieldType(formatField(a_java_sql_ResultSet_fld.getString(2).toLowerCase()));
                columnt.setPrecision(a_java_sql_ResultSet_fld.getString(4));
                columnt.setScale(a_java_sql_ResultSet_fld.getString(5));
                columnt.setCharmaxLength(a_java_sql_ResultSet_fld.getString(6));
                columnt.setNullable(TableConvert.getNullAble(a_java_sql_ResultSet_fld.getString(7)));
                a(columnt);
                columnt.setFiledComment(StringUtils.isBlank(a_java_sql_ResultSet_fld.getString(3)) ? columnt
                        .getFieldName() : a_java_sql_ResultSet_fld.getString(3));
                if (!CodeResourceUtil.JEECG_GENERATE_TABLE_ID.equals(columnt.getFieldName())
                        && !"createDt".equals(columnt.getFieldName()) && !"modifyDt".equals(columnt.getFieldName())
                        && !"delflag".equals(columnt.getFieldName()) && !"crtuser".equals(columnt.getFieldName())
                        && !"crtuserName".equals(columnt.getFieldName())
                        && !"modifierName".equals(columnt.getFieldName()) && !"optip".equals(columnt.getFieldName())
                        && !"modifier".equals(columnt.getFieldName()) && !"delDt".equals(columnt.getFieldName())
                        && !"modifyip".equals(columnt.getFieldName()))
                    arraylist.add(columnt);
                while (a_java_sql_ResultSet_fld.previous()) {
                    Columnt columnt2 = new Columnt();
                    if (CodeResourceUtil.JEECG_FILED_CONVERT)
                        columnt2.setFieldName(formatField(a_java_sql_ResultSet_fld.getString(1).toLowerCase()));
                    else
                        columnt2.setFieldName(a_java_sql_ResultSet_fld.getString(1).toLowerCase());
                    columnt2.setFieldDbName(a_java_sql_ResultSet_fld.getString(1).toUpperCase());
                    if (!CodeResourceUtil.JEECG_GENERATE_TABLE_ID.equals(columnt2.getFieldName())
                            && !"createDt".equals(columnt2.getFieldName())
                            && !"modifyDt".equals(columnt2.getFieldName())
                            && !"delflag".equals(columnt2.getFieldName()) && !"crtuser".equals(columnt2.getFieldName())
                            && !"crtuserName".equals(columnt2.getFieldName())
                            && !"modifierName".equals(columnt2.getFieldName())
                            && !"optip".equals(columnt2.getFieldName()) && !"modifier".equals(columnt2.getFieldName())
                            && !"delDt".equals(columnt2.getFieldName()) && !"modifyip".equals(columnt2.getFieldName())) {
                        columnt2.setFieldType(formatField(a_java_sql_ResultSet_fld.getString(2).toLowerCase()));
                        columnt2.setPrecision(a_java_sql_ResultSet_fld.getString(4));
                        columnt2.setScale(a_java_sql_ResultSet_fld.getString(5));
                        columnt2.setCharmaxLength(a_java_sql_ResultSet_fld.getString(6));
                        columnt2.setNullable(TableConvert.getNullAble(a_java_sql_ResultSet_fld.getString(7)));
                        a(columnt2);
                        columnt2.setFiledComment(StringUtils.isBlank(a_java_sql_ResultSet_fld.getString(3)) ? columnt2
                                .getFieldName() : a_java_sql_ResultSet_fld.getString(3));
                        arraylist.add(columnt2);
                    }
                }
            } else {
            }
        } catch (ClassNotFoundException classnotfoundexception) {
        } catch (SQLException sqlexception) {
        }
        try {
            if (a_java_sql_Statement_fld != null) {
                a_java_sql_Statement_fld.close();
                a_java_sql_Statement_fld = null;
                System.gc();
            }
            if (a_java_sql_Connection_fld != null) {
                a_java_sql_Connection_fld.close();
                a_java_sql_Connection_fld = null;
                System.gc();
            }
        } catch (SQLException sqlexception1) {
        }
        try {
            if (a_java_sql_Statement_fld != null) {
                a_java_sql_Statement_fld.close();
                a_java_sql_Statement_fld = null;
                System.gc();
            }
            if (a_java_sql_Connection_fld != null) {
                a_java_sql_Connection_fld.close();
                a_java_sql_Connection_fld = null;
                System.gc();
            }
        } catch (SQLException sqlexception2) {
        }
        ArrayList<Columnt> arraylist1 = new ArrayList<Columnt>();
        for (int k = arraylist.size() - 1; k >= 0; k--) {
            Columnt columnt1 = arraylist.get(k);
            arraylist1.add(columnt1);
        }

        return arraylist1;
    }

    /**
     * 读取表字段List(原值)
     * 
     * @param s
     * @return
     */
    public List<Columnt> readOriginalTableColumn(String s) {
        ArrayList<Columnt> arraylist = new ArrayList<Columnt>();
        try {
            Class.forName(CodeResourceUtil.DIVER_NAME);
            a_java_sql_Connection_fld = DriverManager.getConnection(CodeResourceUtil.URL, CodeResourceUtil.USERNAME,
                    CodeResourceUtil.PASSWORD);
            a_java_sql_Statement_fld = a_java_sql_Connection_fld.createStatement(1005, 1007);
            if (CodeResourceUtil.DATABASE_TYPE.equals("mysql"))
                a_java_lang_String_fld = MessageFormat
                        .format("select column_name,data_type,column_comment,numeric_precision,numeric_scale,character_maximum_length,is_nullable nullable from information_schema.columns where table_name = {0} and table_schema = {1}",
                                new Object[] {
                                        TableConvert.getV(s.toUpperCase()),
                                        TableConvert.getV(CodeResourceUtil.DATABASE_NAME) });
            if (CodeResourceUtil.DATABASE_TYPE.equals("oracle"))
                a_java_lang_String_fld = MessageFormat
                        .format(" select colstable.column_name column_name, colstable.data_type data_type, commentstable.comments column_comment, colstable.Data_Precision column_precision, colstable.Data_Scale column_scale,colstable.Char_Length,colstable.nullable from user_tab_cols colstable  inner join user_col_comments commentstable  on colstable.column_name = commentstable.column_name  where colstable.table_name = commentstable.table_name  and colstable.table_name = {0}",
                                new Object[] {
                                    TableConvert.getV(s.toUpperCase()) });
            a_java_sql_ResultSet_fld = a_java_sql_Statement_fld.executeQuery(a_java_lang_String_fld);
            a_java_sql_ResultSet_fld.last();
            int i = a_java_sql_ResultSet_fld.getRow();
            int j = i;
            if (j > 0) {
                Columnt columnt = new Columnt();
                if (CodeResourceUtil.JEECG_FILED_CONVERT)
                    columnt.setFieldName(formatField(a_java_sql_ResultSet_fld.getString(1).toLowerCase()));
                else
                    columnt.setFieldName(a_java_sql_ResultSet_fld.getString(1).toLowerCase());
                columnt.setFieldDbName(a_java_sql_ResultSet_fld.getString(1).toUpperCase());
                columnt.setPrecision(TableConvert.getNullString(a_java_sql_ResultSet_fld.getString(4)));
                columnt.setScale(TableConvert.getNullString(a_java_sql_ResultSet_fld.getString(5)));
                columnt.setCharmaxLength(TableConvert.getNullString(a_java_sql_ResultSet_fld.getString(6)));
                columnt.setNullable(TableConvert.getNullAble(a_java_sql_ResultSet_fld.getString(7)));
                columnt.setFieldType(a(a_java_sql_ResultSet_fld.getString(2).toLowerCase(), columnt.getPrecision(),
                        columnt.getScale()));
                a(columnt);
                columnt.setFiledComment(StringUtils.isBlank(a_java_sql_ResultSet_fld.getString(3)) ? columnt
                        .getFieldName() : a_java_sql_ResultSet_fld.getString(3));
                arraylist.add(columnt);
                Columnt columnt2;
                for (; a_java_sql_ResultSet_fld.previous(); arraylist.add(columnt2)) {
                    columnt2 = new Columnt();
                    if (CodeResourceUtil.JEECG_FILED_CONVERT)
                        columnt2.setFieldName(formatField(a_java_sql_ResultSet_fld.getString(1).toLowerCase()));
                    else
                        columnt2.setFieldName(a_java_sql_ResultSet_fld.getString(1).toLowerCase());
                    columnt2.setFieldDbName(a_java_sql_ResultSet_fld.getString(1).toUpperCase());
                    columnt2.setPrecision(TableConvert.getNullString(a_java_sql_ResultSet_fld.getString(4)));
                    columnt2.setScale(TableConvert.getNullString(a_java_sql_ResultSet_fld.getString(5)));
                    columnt2.setCharmaxLength(TableConvert.getNullString(a_java_sql_ResultSet_fld.getString(6)));
                    columnt2.setNullable(TableConvert.getNullAble(a_java_sql_ResultSet_fld.getString(7)));
                    columnt2.setFieldType(a(a_java_sql_ResultSet_fld.getString(2).toLowerCase(),
                            columnt2.getPrecision(), columnt2.getScale()));
                    a(columnt2);
                    columnt2.setFiledComment(StringUtils.isBlank(a_java_sql_ResultSet_fld.getString(3)) ? columnt2
                            .getFieldName() : a_java_sql_ResultSet_fld.getString(3));
                }

            } else {
            }
        } catch (ClassNotFoundException classnotfoundexception) {
        } catch (SQLException sqlexception) {
        }
        try {
            if (a_java_sql_Statement_fld != null) {
                a_java_sql_Statement_fld.close();
                a_java_sql_Statement_fld = null;
                System.gc();
            }
            if (a_java_sql_Connection_fld != null) {
                a_java_sql_Connection_fld.close();
                a_java_sql_Connection_fld = null;
                System.gc();
            }
        } catch (SQLException sqlexception1) {
        }
        try {
            if (a_java_sql_Statement_fld != null) {
                a_java_sql_Statement_fld.close();
                a_java_sql_Statement_fld = null;
                System.gc();
            }
            if (a_java_sql_Connection_fld != null) {
                a_java_sql_Connection_fld.close();
                a_java_sql_Connection_fld = null;
                System.gc();
            }
        } catch (SQLException sqlexception2) {
        }
        ArrayList arraylist1 = new ArrayList();
        for (int k = arraylist.size() - 1; k >= 0; k--) {
            Columnt columnt1 = (Columnt) arraylist.get(k);
            arraylist1.add(columnt1);
        }

        return arraylist1;
    }

    public static String formatField(String s) {
        String as[] = s.split("_");
        s = "";
        int i = 0;
        for (int j = as.length; i < j; i++)
            if (i > 0) {
                String s1 = as[i].toLowerCase();
                s1 = (new StringBuilder(String.valueOf(s1.substring(0, 1).toUpperCase()))).append(
                        s1.substring(1, s1.length())).toString();
                s = (new StringBuilder(String.valueOf(s))).append(s1).toString();
            } else {
                s = (new StringBuilder(String.valueOf(s))).append(as[i].toLowerCase()).toString();
            }

        return s;
    }

    public static String formatFieldCapital(String s) {
        String as[] = s.split("_");
        s = "";
        int i = 0;
        for (int j = as.length; i < j; i++)
            if (i > 0) {
                String s1 = as[i].toLowerCase();
                s1 = (new StringBuilder(String.valueOf(s1.substring(0, 1).toUpperCase()))).append(
                        s1.substring(1, s1.length())).toString();
                s = (new StringBuilder(String.valueOf(s))).append(s1).toString();
            } else {
                s = (new StringBuilder(String.valueOf(s))).append(as[i].toLowerCase()).toString();
            }

        s = (new StringBuilder(String.valueOf(s.substring(0, 1).toUpperCase()))).append(s.substring(1)).toString();
        return s;
    }

    public boolean checkTableExist(String s) {
        try {
            int i;
            System.out.println((new StringBuilder("\u6570\u636E\u5E93\u9A71\u52A8: ")).append(
                    CodeResourceUtil.DIVER_NAME).toString());
            Class.forName(CodeResourceUtil.DIVER_NAME);
            a_java_sql_Connection_fld = DriverManager.getConnection(CodeResourceUtil.URL, CodeResourceUtil.USERNAME,
                    CodeResourceUtil.PASSWORD);
            a_java_sql_Statement_fld = a_java_sql_Connection_fld.createStatement(1005, 1007);
            if (CodeResourceUtil.DATABASE_TYPE.equals("mysql"))
                a_java_lang_String_fld = (new StringBuilder(
                        "select column_name,data_type,column_comment,0,0 from information_schema.columns where table_name = '"))
                        .append(s.toUpperCase()).append("'").append(" and table_schema = '")
                        .append(CodeResourceUtil.DATABASE_NAME).append("'").toString();
            if (CodeResourceUtil.DATABASE_TYPE.equals("oracle"))
                a_java_lang_String_fld = (new StringBuilder(
                        "select colstable.column_name column_name, colstable.data_type data_type, commentstable.comments column_comment from user_tab_cols colstable  inner join user_col_comments commentstable  on colstable.column_name = commentstable.column_name  where colstable.table_name = commentstable.table_name  and colstable.table_name = '"))
                        .append(s.toUpperCase()).append("'").toString();
            a_java_sql_ResultSet_fld = a_java_sql_Statement_fld.executeQuery(a_java_lang_String_fld);
            a_java_sql_ResultSet_fld.last();
            i = a_java_sql_ResultSet_fld.getRow();
            return i > 0;
        } catch (Exception e) {
            return false;
        }
    }

    private void a(Columnt columnt) {
        String s = columnt.getFieldType();
        String s1 = columnt.getScale();
        if ("N".equals(columnt.getNullable()))
            columnt.setOptionType("required:true");
        if ("datetime".equals(s) || "time".equals(s))
            columnt.setClassType("easyui-datetimebox");
        else if ("date".equals(s))
            columnt.setClassType("easyui-datebox");
        else if ("int".equals(s))
            columnt.setClassType("easyui-numberbox");
        else if ("number".equals(s)) {
            if (StringUtils.isNotBlank(s1) && Integer.parseInt(s1) > 0) {
                columnt.setClassType("easyui-numberbox");
                if (StringUtils.isNotBlank(columnt.getOptionType()))
                    columnt.setOptionType((new StringBuilder(String.valueOf(columnt.getOptionType()))).append(",")
                            .append("precision:2,groupSeparator:','").toString());
                else
                    columnt.setOptionType("precision:2,groupSeparator:','");
            } else {
                columnt.setClassType("easyui-numberbox");
            }
        } else if ("float".equals(s) || "double".equals(s) || "decimal".equals(s)) {
            columnt.setClassType("easyui-numberbox");
            if (StringUtils.isNotBlank(columnt.getOptionType()))
                columnt.setOptionType((new StringBuilder(String.valueOf(columnt.getOptionType()))).append(",")
                        .append("precision:2,groupSeparator:','").toString());
            else
                columnt.setOptionType("precision:2,groupSeparator:','");
        } else {
            columnt.setClassType("easyui-validatebox");
        }
    }

    private String a(String s, String s1, String s2) {
        if (s.contains("char"))
            s = "java.lang.String";
        else if (s.contains("int"))
            s = "java.lang.Integer";
        else if (s.contains("float"))
            s = "java.lang.Float";
        else if (s.contains("double"))
            s = "java.lang.Double";
        else if (s.contains("number")) {
            if (StringUtils.isNotBlank(s2) && Integer.parseInt(s2) > 0)
                s = "java.math.BigDecimal";
            else if (StringUtils.isNotBlank(s1) && Integer.parseInt(s1) > 10)
                s = "java.lang.Long";
            else
                s = "java.lang.Integer";
        } else if (s.contains("decimal"))
            s = "BigDecimal";
        else if (s.contains("date"))
            s = "java.util.Date";
        else if (s.contains("time"))
            s = "java.sql.Timestamp";
        else if (s.contains("clob"))
            s = "java.sql.Clob";
        else
            s = "java.lang.Object";
        return s;
    }

}
