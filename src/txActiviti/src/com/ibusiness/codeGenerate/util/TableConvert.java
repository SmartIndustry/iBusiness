package com.ibusiness.codeGenerate.util;

import org.apache.commons.lang3.StringUtils;

public class TableConvert
{
  public static String getNullAble(String paramString)
  {
    if (("YES".equals(paramString)) || ("yes".equals(paramString)) || ("y".equals(paramString)) || ("Y".equals(paramString)))
      return "Y";
    if (("NO".equals(paramString)) || ("N".equals(paramString)) || ("no".equals(paramString)) || ("n".equals(paramString)))
      return "N";
    return null;
  }

  public static String getNullString(String paramString)
  {
    if (StringUtils.isBlank(paramString))
      return "";
    return paramString;
  }

  public static String getV(String paramString)
  {
    return "'" + paramString + "'";
  }
}

/* Location:           C:\Users\Administrator\Desktop\commons-cg-2.1.jar
 * Qualified Name:     com.util.TableConvert
 * JD-Core Version:    0.5.4
 */