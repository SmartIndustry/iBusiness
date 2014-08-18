<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglibs.jsp"%>
<%pageContext.setAttribute("currentHeader", "table");%>
<%pageContext.setAttribute("currentMenu", "table");%>
<!doctype html>
<html>

  <head>
    <%@include file="/common/meta.jsp"%>
    <title>表列字段设置</title>
    <!-- Bootstrap -->
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
				  <h4 class="title">添加</h4>
				  <div class="ctrl">
				    <a class="btn"><i id="bpmCategorySearchIcon" class="icon-chevron-up"></i></a>
				  </div>
				</header>
		        <div id="bpmCategorySearch" class="content content-inner">
				    <form name="tableForm" method="post" action="${scopePrefix}/table/conf-table-columns-save.do?tableName=${tableName}" class="form-inline">
					    <div class="control-group">
						    <label for="columnValue">字段名:</label>
						    <input type="text" id="columnValue" name="columnValue" value="" class="span2" >
						    <label for="columnName">字段标题:</label>
						    <input type="text" id="columnName" name="columnName" value=""  class="span2" >
					    </div>
					    <div class="control-group">
						    <label for="columnType">字段类型:</label>
						    <td> <select id="columnType" name="columnType" class="span2" >
							      <option>VARCHAR</option>
							      <option>INT</option>
							      <option>DOUBLE</option>
							      <option>NUMBER</option>
							  </select>
							</td>
						    <label for="columnSize">长度:</label>
						    <input type="text" id="columnSize" name="columnSize" value="" class="span1"  >
						    <label for="isNull">允许空:</label>
						    <td>
							      <select id="isNull" name="isNull" class="span1" >
								      <option>是</option>
								      <option>否</option>
								  </select>
							</td>
						    <label for="defaultValue">默认值:</label>
						    <input type="text" id="defaultValue" name="defaultValue" value=""  class="input-small" >
						    <label for="columnNo">字段排序编号:</label>
						    <input type="text" id="columnNo" name="columnNo" value=""  class="span1" >
						 </div>
						 <div class="control-group">
							<button class="btn a-cancel" onclick="document.tableForm.submit()">保存</button>
							<button type="button" onclick="history.back();" class="btn a-cancel"><spring:message code='core.input.back' text='返回'/></button>
				        </div>
				    </form>
				</div>
			  </article>
	        <!-- /////////////////////////////////////////////////////////////////////////////////// -->
		    <article class="m-widget">
		        <header class="header">
				  <h4 class="title">表列字段</h4>
				  <div class="ctrl">
				    <a class="btn"><i id="bpmCategorySearchIcon" class="icon-chevron-up"></i></a>
				  </div>
				</header>
				<div id="tableCategorySearch" class="content content-inner">
				  <region:region-permission permission="tableComment:delete">
				  	<button class="btn btn-small a-remove" onclick="table.removeAll()">删除</button>
				  </region:region-permission>
				</div>
		        <div class="content content-inner">
		            <form id="tableGridForm" name="tableGridForm" method='post' action="conf-table-columns-remove.do?tableName=${tableName}" class="m-form-blank">
			          <table class="m-table table-hover table-bordered table-striped">
						<thead>
						  <tr>
						    <th width="10" class="m-table-check"><input type="checkbox" name="checkAll" onchange="toggleSelectedItems(this.checked)"></th>
							<th>字段</th>
							<th>字段标题</th>
							<th>类型</th>
							<th>长度</th>
							<th>允许空</th>
							<th>默认值</th>
							<th>排序</th>
						  </tr>
						</thead>
						<tbody>
						<c:forEach items="${tableInfoList}" var="item">
						  <tr>
						    <td><input type="checkbox" class="selectedItem a-check" name="selectedItem" value="${item.columnValue}"></td>
						    <td>
						        <a href="${scopePrefix}/table/conf-table-column-show.do?tableName=${tableName}&columnValue=${item.columnValue}" class="btn btn-small btn-primary">${item.columnValue}</a>
							</td>
							<td>${item.columnName}</td>
							<td>${item.columnType}</td>
							<td>${item.columnSize}</td>
							<td>${item.isNull}</td>
							<td>${item.defaultValue}</td>
							<td>${item.columnNo}</td>
						  </tr>
						  </c:forEach>
						  
						</tbody>
					  </table>
					</form>
				</div>
			  </article>	
	    </section>
    <!-- end of main -->
	</div>			
  </body>
</html>
