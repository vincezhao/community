<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <#include "/WEB-INF/freemarker/title.ftl"/>
    
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="apple-mobile-web-app-capable" content="yes" />    
    
    <link href="./css/bootstrap.min.css" rel="stylesheet" />
    <link href="./css/bootstrap-responsive.min.css" rel="stylesheet" />
    
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600" rel="stylesheet" />
    <link href="./css/font-awesome.css" rel="stylesheet" />
    
    <link href="./css/adminia.css" rel="stylesheet" /> 
    <link href="./css/adminia-responsive.css" rel="stylesheet" /> 
    
    
    <link href="./css/pages/plans.css" rel="stylesheet" /> 
	
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>

<body>
	
<#include "/WEB-INF/freemarker/innavbar.ftl"/>

<div id="content">
	
	<div class="container">
		
		<div class="row">
			
			<div class="span3">
				
				<#include "/WEB-INF/freemarker/container.ftl"/>
				
				<hr />
				
				<#include "/WEB-INF/freemarker/mainnav.ftl"/>	
				
				<hr />
				
				<div class="sidebar-extra">
					<#include "/WEB-INF/freemarker/notice.ftl"/>
				</div> <!-- .sidebar-extra -->
				
				<br />
		
			</div> <!-- /span3 -->
			
			
			
			<div class="span9">
				
				<h1 class="page-title">
					<i class="icon-th-large"></i>
					用户信息					
				</h1>
				
				
				<div class="row">
					
					<div class="span9">
				
						<div class="widget">
							
							<div class="widget-header">
								<h3>基本信息</h3>
							</div> <!-- /widget-header -->
									
							<div class="widget-content">
								
								
								
								<div class="tabbable">
						<ul class="nav nav-tabs">
						  <li class="active">
						    <a href="#1" data-toggle="tab">资料设置</a>
						  </li>
						  <li><a href="#2" data-toggle="tab">修改密码</a></li>
						</ul>
						
						<br />
						
							<div class="tab-content">
								<div class="tab-pane active" id="1">
								<form id="edit-profile" class="form-horizontal" />
									<fieldset>
										
										<div class="control-group">											
											<label class="control-label" for="username">用户名</label>
											<div class="controls">
												<input type="text" class="input-medium disabled" id="username" value="${username}" disabled="" />
												<p class="help-block">用户名唯一,不能被更改.</p>
											</div> <!-- /controls -->				
										</div> <!-- /control-group -->
										
										
										<div class="control-group">											
											<label class="control-label" for="firstname">真实姓名</label>
											<div class="controls">
												<#if userInfo??>
												<input type="text" class="input-medium" id="firstname" value="${userInfo.realname!''}" />
												<#else>
												<input type="text" class="input-medium" id="firstname" value="" />
												</#if>
											</div> <!-- /controls -->				
										</div> <!-- /control-group -->
										
										
										<div class="control-group">											
											<label class="control-label" for="sex">性别</label>
											<div class="controls">
												<#if userInfo??>
												<input type="text" class="input-medium" id="sex" value="${userInfo.sex!''}" />
												<#else>
												<input type="text" class="input-medium" id="sex" value="" />
												</#if>
											</div> <!-- /controls -->				
										</div> <!-- /control-group -->
										
										
										<div class="control-group">											
											<label class="control-label" for="email">邮箱</label>
											<div class="controls">
												<#if userInfo??>
												<input type="text" class="input-large" id="email" value="${userInfo.email!''}" />
												<#else>
												<input type="text" class="input-large" id="email" value="" />
												</#if>
											</div> <!-- /controls -->				
										</div> <!-- /control-group -->
										
										<div class="control-group">											
											<label class="control-label" for="avatar">头像</label>
											<div class="controls">
												<#if userInfo??>
												<select class="input-large" id="avatar">
													<option value="${userInfo.avatar!''}">${userInfo.avatar!''}</option>
													<option value="./img/header2.jpg">头像二</option>
													<option value="./img/header3.jpg">头像三</option>
													<option value="./img/header4.jpg">头像四</option>
													<option value="./img/header5.jpg">头像五</option>
													<option value="./img/header6.jpg">头像六</option>
													<option value="./img/header7.jpg">头像七</option>
												</select>
												<#else>
												<select class="input-large" id="avatar">
													<option value="./img/header1.jpg">头像一</option>
													<option value="./img/header2.jpg">头像二</option>
													<option value="./img/header3.jpg">头像三</option>
													<option value="./img/header4.jpg">头像四</option>
													<option value="./img/header5.jpg">头像五</option>
													<option value="./img/header6.jpg">头像六</option>
													<option value="./img/header7.jpg">头像七</option>
												</select>
												</#if>
											</div> <!-- /controls -->				
										</div> <!-- /control-group -->
										
										<div class="control-group">											
											<label class="control-label" for="address">地址</label>
											<div class="controls">
												<#if userInfo??>
												<input type="text" class="input-large" id="address" value="${userInfo.address!''}" />
												<#else>
												<input type="text" class="input-large" id="address" value="" />
												</#if>
											</div> <!-- /controls -->				
										</div> <!-- /control-group -->										
										
										<div class="control-group">											
											<label class="control-label" for="telephone">手机号</label>
											<div class="controls">
												<#if userInfo??>
												<input type="text" class="input-large" id="telephone" value="${userInfo.telephone!''}" />
												<#else>
												<input type="text" class="input-large" id="telephone" value="" />
												</#if>
											</div> <!-- /controls -->				
										</div> <!-- /control-group -->	
																			
										<div class="control-group">											
											<label class="control-label" for="community">社区</label>
											<div class="controls">
												<#if userInfo??>
												<select class="input-large" id="community">
													<option value="${userInfo.community!''}">社区一</option>
													<option value="2">社区二</option>
													<option value="3">社区三</option>
													<option value="4">社区四</option>
													<option value="5">社区五</option>
													<option value="6">社区六</option>
												</select>
												<#else>
												<select class="input-large" id="community">
													<option value="1">社区一</option>
													<option value="2">社区二</option>
													<option value="3">社区三</option>
													<option value="4">社区四</option>
													<option value="5">社区五</option>
													<option value="6">社区六</option>
												</select>
												</#if>
											</div> <!-- /controls -->				
										</div> <!-- /control-group -->	
											
											<br />
										
										<div class="form-actions">
											<button type="submit" class="btn btn-primary" onclick="setprofiler()">保存</button> 
											<button class="btn">取消</button>
										</div> <!-- /form-actions -->
									</fieldset>
								</form>
								</div>
								
								<div class="tab-pane" id="2">
									<form id="edit-profile2" class="form-horizontal" />
										<fieldset>
											
											
										<div class="control-group">											
											<label class="control-label" for="password1">密码</label>
											<div class="controls">
												<input type="password" class="input-medium" id="password1" value="${pwd!''}" />
											</div> <!-- /controls -->				
										</div> <!-- /control-group -->
										
										
										<div class="control-group">											
											<label class="control-label" for="password2">确认密码</label>
											<div class="controls">
												<input type="password" class="input-medium" id="password2" value="${pwd!''}" />
											</div> <!-- /controls -->				
										</div> <!-- /control-group -->
											
											<br />
											
											<div class="form-actions">
												<button type="submit" class="btn btn-primary" onclick="changepwd()">保存</button> <button class="btn">取消</button>
											</div>
										</fieldset>
									</form>
								</div>
								
							</div>
						  
						  
						</div>
								
								
								
								
								
								
								
								
							</div> <!-- /widget-content -->
							
						</div> <!-- /widget -->
						
					</div> <!-- /span9 -->
					
				</div> <!-- /row -->
				
				
				
				
				
				
				
				
				
			</div> <!-- /span9 -->
			
			
		</div> <!-- /row -->
		
	</div> <!-- /container -->
	
</div> <!-- /content -->
					
	
<#include "/WEB-INF/freemarker/footer.ftl"/>

    
<!-- Le javascript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="./js/jquery-1.7.2.min.js"></script>

<script src="./js/account.js"></script>
<script src="./js/bootstrap.js"></script>

  </body>
</html>
