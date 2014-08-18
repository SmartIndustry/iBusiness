<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglibs.jsp"%>
<%pageContext.setAttribute("currentHeader", "user");%>
<%pageContext.setAttribute("currentMenu", "user");%>
<!doctype html>
<html>
  <head>
    <%@include file="/common/meta.jsp"%>
    <title><spring:message code="user.user.input.title" text="编辑用户"/></title>
    <%@include file="/common/s.jsp"%>
  </head>
  <body>
    <%@include file="/header/user.jsp"%>
    <div class="row-fluid">
	<%@include file="/menu/user.jsp"%>
	<!-- start of main -->
    <section id="m-main" class="span10">
      <article class="m-widget">
        <header class="header">
		  <h4 class="title"><spring:message code="user.user.input.title" text="编辑用户"/></h4>
		</header>
		<div class="content content-inner">
				<form id="userForm" method="post" action="${entityName?uncap_first}-save.do?operationMode=STORE" class="form-horizontal">
				  <input id="user-base_userRepoId" type="hidden" name="userRepoId" value="1">
				  <c:if test="${'$' + '{model != null}'}">
				  <input id="code_id" type="hidden" name="id" value="${'$' + '{model.id}'}">
				  </c:if>
				  
				  <#list columns as po>
					  <div class="control-group">
					    <label class="control-label" for="code-${po.filedComment}">${po.filedComment}</label>
						<div class="controls">
						  <input id="code-${po.filedComment}" type="text" name="${po.filedComment}" value="${'$' + '{model.${po.filedComment}}'}" size="40" class="text required" minlength="2" maxlength="50">
					    </div>
					  </div>
				  </#list>
				  
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
