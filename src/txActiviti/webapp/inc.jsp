<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script type="text/javascript" src="<%=basePath%>/plug-in/jquery/jquery-1.8.0.min.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=basePath%>/plug-in/jquery/jquery.cookie.js" charset="utf-8"></script>
<%
	String easyuiThemeName = "default";
	Cookie cookies[] = request.getCookies();
	if (cookies != null && cookies.length > 0) {
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("easyuiThemeName")) {
				easyuiThemeName = cookie.getValue();
				break;
			}
		}
	}
%>
<link id="easyuiTheme" rel="stylesheet" href="<%=basePath%>/plug-in/easyui/themes/<%=easyuiThemeName%>/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="<%=basePath%>/plug-in/easyui/themes/icon.css" type="text/css"></link>
<script type="text/javascript" src="<%=basePath%>/plug-in/easyui/jquery.easyui.min.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=basePath%>/plug-in/easyui/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>

<link rel="stylesheet" href="<%=basePath%>/plug-in/jquery-easyui-portal/portal.css" type="text/css"></link>
<script type="text/javascript" src="<%=basePath%>/plug-in/jquery-easyui-portal/jquery.portal.js" charset="utf-8"></script>

<link rel="stylesheet" href="<%=basePath%>/css/syCss.css?v=20120804" type="text/css"></link>
<script type="text/javascript" src="<%=basePath%>/plug-in/syUtil.js?v=20120805" charset="utf-8"></script>

<script type="text/javascript" src="<%=basePath%>/plug-in/xheditor-1.1.14/xheditor-1.1.14-zh-cn.min.js" charset="utf-8"></script>

<script type="text/javascript" src="<%=basePath%>/plug-in/include/js/common.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=basePath%>/plug-in/include/js/validatebox-myrule.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=basePath%>/plug-in/include/js/validatebox-duplicate.js" charset="utf-8"></script>
