<%@ page language="java" pageEncoding="UTF-8" %>

      <!-- start of sidebar -->
      <aside id="m-sidebar" class="accordion span2" data-spy="affix" data-offset-top="100">

        <div class="accordion-group">
          <div class="accordion-heading">
            <a class="accordion-toggle" data-toggle="collapse" data-parent="#m-sidebar" href="#collapse-group">
              <i class="icon-user"></i>
              <span class="title">业务模块组件</span>
            </a>
          </div>
          <div id="collapse-group" class="accordion-body collapse ${currentMenu == 'serviceModule' ? 'in' : ''}">
            <ul id="treeMenu" class="ztree"></ul>
          </div>
        </div>

		<footer id="m-footer" class="text-center">
		  <hr>
		  &copy;天翔
		</footer>
      </aside>
      <!-- end of sidebar -->

<script type="text/javascript">
		var setting = {
			async: {
				enable: true,
				url: "${scopePrefix}/rs/serviceModule/tree?parentId=${parentId}"
			},
			callback: {
				onClick: function(event, treeId, treeNode) {
					location.href = '${scopePrefix}/serviceModule/serviceModule-action.do?packageName=' +  treeNode.packageName + '&typeId=' + treeNode.typeId;
				}
			}
		};

		var zNodes =[
		             ];

		$(function(){
			$.fn.zTree.init($("#treeMenu"), setting, zNodes);
		});
</script>
