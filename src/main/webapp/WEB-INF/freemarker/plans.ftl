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

    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
	
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
					<i class="icon-th-list"></i>
					矛盾化解跟踪					
				</h1>
				
				<#include "/WEB-INF/freemarker/questionlist.ftl"/>
				
			</div> <!-- /span9 -->
			
			
		</div> <!-- /row -->
		
	</div> <!-- /container -->
	
</div> <!-- /content -->
					
	
<#include "/WEB-INF/freemarker/footer.ftl"/>


<!-- Le javascript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="./js/jquery-1.7.2.min.js"></script>


<script src="./js/bootstrap.js"></script>

  </body>
</html>
