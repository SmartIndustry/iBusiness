<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglibs.jsp"%>
<%pageContext.setAttribute("currentHeader", "table");%>
<%pageContext.setAttribute("currentMenu", "serviceModule");%>
<!doctype html>
<html>

  <head>
    <%@include file="/common/meta.jsp"%>
    <title>业务模块组件</title>
    <%@include file="/common/s.jsp"%>
    <script type="text/javascript">
		var config = {
		    id: 'codeGrid',
		    pageNo: ${page.pageNo},
		    pageSize: ${page.pageSize},
		    totalCount:${page.totalCount},
		    resultSize: ${page.resultSize},
		    pageCount: ${page.pageCount},
		    orderBy: '${page.orderBy == null ? '' : page.orderBy}',
		    asc: ${page.asc},
		    params: {
		        'filter_LIKES_username': '${param.filter_LIKES_username}',
		        'filter_EQI_status': '${param.filter_EQI_status}'
		    },
			selectedItemClass: 'selectedItem',
			gridFormId: 'gridForm'
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
	  <article class="m-blank">
	    <div class="pull-left">
		  <region:region-permission permission="user:create">
		  <button class="btn btn-small a-insert" onclick="location.href='serviceModule-input.do'">新建</button>
		  </region:region-permission>
		  <region:region-permission permission="user:delete">
		  <button class="btn btn-small a-remove" onclick="table.removeAll()">删除</button>
		  </region:region-permission>
		</div>

		<div class="pull-right">
		  每页显示
		  <select class="m-page-size">
		    <option value="10">10</option>
		    <option value="50">50</option>
		    <option value="100">100</option>
		  </select>
		  条
		</div>
	    <div class="m-clear"></div>
	  </article>

      <article class="m-widget">
	        <header class="header">
			  <h4 class="title"><spring:message code="user.user.list.title" text="业务模块"/></h4>
			</header>
			<div class="content">
					<form id="gridForm" name="gridForm" method='post' action="serviceModule-remove.do" class="m-form-blank">
					  <table id="codeGrid" class="m-table table-hover">
					    <thead>
					      <tr>
					        <th width="10" class="m-table-check"><input type="checkbox" name="checkAll" onchange="toggleSelectedItems(this.checked)"></th>
						                <th class="sorting" name="packagename">模块包名</th>
						                <th class="sorting" name="modulename">模块分类名</th>
						                <th class="sorting" name="parentid">父节点ID</th>
						                <th class="sorting" name="typeid">类型ID</th>
					        <th width="80">&nbsp;</th>
					      </tr>
					    </thead>
					    <tbody>
					      <c:forEach items="${page.result}" var="item">
					      <tr>
					        <td><input type="checkbox" class="selectedItem a-check" name="selectedItem" value="${item.id}"></td>
						        <td>${item.packagename}</td>
						        <td>${item.modulename}</td>
						        <td>${item.parentid}</td>
						        <td>${item.typeid}</td>
					        <td>
					          <a href="serviceModule-input.do?id=${item.id}" class="a-update"><spring:message code="core.list.edit" text="编辑"/></a>
					        </td>
					      </tr>
					      </c:forEach>
					    </tbody>
					  </table>
					</form>
	        </div>
	      </article>
		  <article>
		    <div class="m-page-info pull-left">
			  共100条记录 显示1到10条记录
			</div>
			<div class="btn-group m-pagination pull-right">
			  <button class="btn btn-small">&lt;</button>
			  <button class="btn btn-small">1</button>
			  <button class="btn btn-small">&gt;</button>
			</div>
		    <div class="m-clear"></div>
	      </article>
		  
		</section>
	<!-- end of main -->
	</div>
  </body>
</html>
