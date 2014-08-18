<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglibs.jsp"%>
<%pageContext.setAttribute("currentHeader", "table");%>
<%pageContext.setAttribute("currentMenu", "serviceModule");%>
<!doctype html>
<html>
  <head>
    <%@include file="/common/meta.jsp"%>
    <title><spring:message code="user.user.input.title" text="编辑分类"/></title>
    <%@include file="/common/s.jsp"%>
  </head>
  <body>
    <%@include file="/header/table.jsp"%>
    <div class="row-fluid">
	<%@include file="/menu/serviceModule.jsp"%>
	<!-- start of main -->
    <section id="m-main" class="span10">
      <article class="m-widget">
        <header class="header">
		  <h4 class="title"><spring:message code="user.user.input.title" text="编辑分类"/></h4>
		</header>
		<div class="content content-inner">
				<form id="userForm" method="post" action="serviceModule-save.do" class="form-horizontal">
				  <input id="user-base_userRepoId" type="hidden" name="userRepoId" value="1">
				  <c:if test="${model != null}">
				  <input id="code_id" type="hidden" name="id" value="${model.id}">
				  </c:if>
				  
					  <div class="control-group">
					    <label class="control-label" for="code-packagename">模块包名</label>
						<div class="controls">
						  <input id="code-packagename" type="text" name="packagename" value="${model.packagename}" size="40" class="text required" minlength="2" maxlength="50">
					    </div>
					  </div>
					  <div class="control-group">
					    <label class="control-label" for="code-modulename">模块分类名</label>
						<div class="controls">
						  <input id="code-modulename" type="text" name="modulename" value="${model.modulename}" size="40" class="text required" minlength="2" maxlength="50">
					    </div>
					  </div>
					  <div class="control-group">
					    <label class="control-label" for="code-parentid">父节点ID</label>
						<div class="controls">
						  <input id="code-parentid" type="text" name="parentid" value="0" size="40" class="text required" readonly>
					    </div>
					  </div>
					  <div class="control-group">
					    <label class="control-label" for="code-typeid">类型ID</label>
						<div class="controls">
						  <input id="code-typeid" type="text" name="typeid" value="sModule" size="40" class="text required" readonly>
					    </div>
					  </div>
				  
				  <div class="control-group">
				    <div class="controls">
				      <button id="submitButton" class="btn a-submit"><spring:message code='core.input.save' text='保存'/></button>
				      <button type="button" onclick="history.back();" class="btn a-cancel"><spring:message code='core.input.back' text='返回'/></button>
				    </div>
				  </div>
				</form>
		</div>
      </article>
    </section>
	<!-- end of main -->
	</div>
  </body>
</html>
