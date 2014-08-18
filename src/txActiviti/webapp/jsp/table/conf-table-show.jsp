<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglibs.jsp"%>
<%pageContext.setAttribute("currentHeader", "table");%>
<%pageContext.setAttribute("currentMenu", "table");%>
<!doctype html>
<html>

  <head>
    <%@include file="/common/meta.jsp"%>
    <title>评论列表</title>
    <%@include file="/common/s.jsp"%>
    <script type="text/javascript">
		var config = {
		    id: 'tableModelGrid',
			selectedItemClass: 'selectedItem',
			gridFormId: 'tableGridForm'
		};
		
		var table;
		
		$(function() {
			table = new Table(config);
		    table.configPagination('.m-pagination');
		    table.configPageInfo('.m-page-info');
		    table.configPageSize('.m-page-size');
		});
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
				  <h4 class="title">表存储模型</h4>
				  <div class="ctrl">
				    <a class="btn"><i id="bpmCategorySearchIcon" class="icon-chevron-up"></i></a>
				  </div>
				</header>
				<div id="tableCategorySearch" class="content content-inner">
				  <region:region-permission permission="tableComment:create">
				  	<button class="btn btn-small a-insert" onclick="location.href='conf-table-insert.do?packageName=${packageName}'">新建</button>
				  </region:region-permission>
				  <region:region-permission permission="tableComment:delete">
				  	<button class="btn btn-small a-remove" onclick="table.removeAll()">删除</button>
				  </region:region-permission>
				</div>
		        <div class="content content-inner">
			        <form id="tableGridForm" name="tableGridForm" method='post' action="conf-table-remove.do?packageName=${packageName}" class="m-form-blank">
						<table class="m-table table-hover table-bordered" id=“tableModelGrid” >
							<thead>
							  <tr>
							    <th width="10" class="m-table-check"><input type="checkbox" name="checkAll" onchange="toggleSelectedItems(this.checked)"></th>
								<th>表名</th>
								<th>标题</th>
								<th>所在模块</th>
								<th>唯一标识</th>
								<th width="80">&nbsp;</th>
							  </tr>
							</thead>
							<tbody>
							<c:forEach items="${tableInfoList}" var="item">
							    <tr>
								    <td><input type="checkbox" class="selectedItem a-check" name="selectedItem" value="${item.uuId}"></td>
									<td>${item.tableName}</td>
									<td>${item.tableNameComment}</td>
									<td>${item.packageName}</td>
									<td>${item.uuId}</td>
									<td>
							          <a href="conf-table-detail-show.do?tableName=${item.tableName}" class="a-update"><spring:message code="core.list.edit" text="编辑表字段"/></a>
							        </td>
							    </tr>
							  </c:forEach>
							</tbody>
						</table>
					</form>
				</div>
			  </article>	
			</div>
    </section>
	<!-- end of main -->
	</div>

  </body>

</html>