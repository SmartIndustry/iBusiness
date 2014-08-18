<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglibs.jsp"%>
<%pageContext.setAttribute("currentHeader", "table");%>
<%pageContext.setAttribute("currentMenu", "serviceModule");%>
<!doctype html>
<html>

  <head>
    <%@include file="/common/meta.jsp"%>
    <title>编辑评论</title>
    <%@include file="/common/s.jsp"%>
    <script type="text/javascript">
$(function() {
    $("#tableCommentForm").validate({
        submitHandler: function(form) {
			bootbox.animate(false);
			var box = bootbox.dialog('<div class="progress progress-striped active" style="margin:0px;"><div class="bar" style="width: 100%;"></div></div>');
            form.submit();
        },
        errorClass: 'validate-error'
    });
})
    </script>
  </head>

  <body>
    <%@include file="/header/table.jsp"%>

    <div class="row-fluid">
	  <%@include file="/menu/serviceModule.jsp"%>

	<!-- start of main -->
    <section id="m-main" class="span10">

      <article class="m-widget">
        <header class="header">
		  <h4 class="title">新建表存储模型</h4>
		</header>
		<div class="content content-inner">

<form id="tableCommentForm" method="post" action="conf-table-save.do" class="form-horizontal">
  <c:if test="${model != null}">
  <input id="table-uuId" type="hidden" name="uuId" value="${model.uuId}">
  </c:if>
  <div class="control-group">
    <label class="control-label" for="table-packageName">所在模块:</label>
	<div class="controls">
	  <input id="table-packageName" type="text" name="packageName" value="${packageName}" size="40" class="text required" readonly >
    </div>
  </div>
  <div class="control-group">
    <label class="control-label" for="table-tableName">表名称:</label>
	<div class="controls">
	  <input id="table-tableName" type="text" name="tableName" value="${model.tableName}" size="40" class="text required" minlength="2" maxlength="50">
    </div>
  </div>
  <div class="control-group">
    <label class="control-label" for="table-tableNameComment">标 题:</label>
	<div class="controls">
	  <input id="table-tableNameComment" type="text" name="tableNameComment" value="${model.tableNameComment}" size="40" class="text required" minlength="2" maxlength="50">
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
