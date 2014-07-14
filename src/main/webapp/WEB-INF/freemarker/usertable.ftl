<div class="widget widget-table">
						
	<div class="widget-header">
		<i class="icon-th-list"></i>
		<h3>用户管理表</h3>
	</div> <!-- /widget-header -->
	
	<div class="widget-content">
	
		<table class="table table-striped table-bordered">
			<thead>
				<tr>
					<th>#</th>
					<th>用户名</th>
					<th>角色</th>
					<th>是否激活</th>
					<th>更改状态</th>
				</tr>
			</thead>
			
			<tbody>
				<#if users??>
				<#list users as user>
				<tr>
					<td>${user_index + 1}</td>
					<td>${user.username}</td>
					<td>${user.role}</td>
					<#if user.enable=1>
					<td>激活</td>
					<#else>
					<td>未激活</td>
					</#if>
					<td class="action-td">
						<#if user.enable=0>
						<a href="javascript:;" class="btn btn-small btn-warning">
							<i class="icon-ok"></i>								
						</a>
						<a href="javascript:;" class="btn btn-small">
							<i class="icon-remove"></i>						
						</a>
						<#else>
						<a href="javascript:;" class="btn btn-small">
							<i class="icon-ok"></i>								
						</a>
						<a href="javascript:;" class="btn btn-small btn-warning">
							<i class="icon-remove"></i>						
						</a>
						</#if>
					</td>
				</tr>
				</#list>
				</#if>
			</tbody>
		</table>
	
	</div> <!-- /widget-content -->
	
</div> <!-- /widget -->
