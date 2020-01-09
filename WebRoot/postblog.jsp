<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page language="java" import="java.util.*" import="domain.*"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!--<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">-->
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<title>MyBlog - 发布博客</title>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<meta name="description" content="博客发表页面">


<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom fonts for this template -->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">
<link
	href='https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic'
	rel='stylesheet' type='text/css'>
<link
	href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800'
	rel='stylesheet' type='text/css'>

<!-- Custom styles for this template -->
<link href="css/clean-blog.min.css" rel="stylesheet">
<style type="text/css">
input {
	padding: 10px 5px;
	font-size: 18px;
	line-height: 1.33;
	border-radius: 6px;
	width: 500px;
}
</style>

</head>
<%
	Boolean modify2 = (Boolean) request.getAttribute("modify");
	Boolean modify1 = new Boolean(request.getParameter("modify"));
	Boolean modify = null;
	if (modify1 != null) {
		modify = modify1;
	}
	if (modify2 != null) {
		modify = modify2;
	}
	Blog blog = (Blog) ActionContext.getContext().get("blog");
%>
<body style="background-color: ghostwhite;">
	<!-- Navigation -->
	<nav class="navbar navbar-expand-lg navbar-light fixed-top container"
		id="mainNav">
		<div class="container">
			<a class="navbar-brand" href="<%=basePath%>index">CS FORUM</a>
			<button class="navbar-toggler navbar-toggler-right" type="button"
				data-toggle="collapse" data-target="#navbarResponsive"
				aria-controls="navbarResponsive" aria-expanded="false"
				aria-label="Toggle navigation">
				Menu <i class="fas fa-bars"></i>
			</button>

			<%
				User currentuser = (User) session.getAttribute("currentuser");
			%>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item"><a class="nav-link"
						href="<%=basePath%>index">首页</a></li>
					<li class="nav-item"><a class="nav-link"
						href="<%=basePath%>main?user_nickname=<%=currentuser.getUser_nickname()%>&page=1">个人主页</a>
					</li>
					<li class="nav-item"><a class="nav-link"
						href="<%=basePath%>postblog.jsp">发布博客</a></li>
					<li class="nav-item"><a class="nav-link"
						href="<%=basePath%>/authmanage_logout">注销</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<!-- Page Header -->
	<header class="masthead container"
		style="background:rgba(200,180,180, 0.5);">

		<div class="container" style="padding-top:90px;">
			<div class="row">
				<div class="col-lg-12 col-md-12 mx-auto">
					<div class="subheading">
						<h1 class="post-title" style="color:white">Post Your Mind！</h1>
					</div>
				</div>
			</div>
			<%
				if (modify == null || !modify) {
			%>
			<form action="<%=basePath%>blogmanage_post" method="post">
				<div class="container">
					<div class="row">
						<div class="col-md-8" style="padding-top:5px">
							<div class="form-group">
								<input class="form-control" type="text" name="title"
									placeholder="请填写标题">
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<button class="btn btn-info btn-block"
									style="padding: 10px 25px;font-size: 18px;line-height: 1.33;border-radius: 6px;"
									type="submit">发布</button>
							</div>
						</div>
					</div>
				</div>
				<div style="color:red"></div>
				<div class="form-group">
					<textarea rows="10" cols="20" name="blog_content" class="ckeditor"></textarea>
				</div>
				<br />
			</form>
			<%
				} else {
			%>
			<form name="form" method="post">
				<div class="form-group">
					<div class="container">
						<div class="row">
							<div class="col-md-8" style="padding-top:5px">
								<input type="text" name="title" value="<%=blog.getTitle()%>">
							</div>
							<%
								if (modify1 == null || modify1) {
							%>
							<div class="col-md-4">
								<div class="form-group">
									<button class="btn  btn-warning btn-block"
										onclick="document.form.action ='<%=basePath%>blogmanage_modify?blog_id=<%=blog.getBlog_id()%>';document.form.submit();"
										style="padding: 10px 25px;font-size: 18px;line-height: 1.33;border-radius: 6px;"
										type="submit">修改</button>
								</div>
							</div>
							<%
								} else {
							%>
							<div class="col-md-4">
								<button class="btn btn-info btn-block"
									onclick="window.location.href='<%=basePath%>blogmanage_post'"
									style="padding: 10px 25px;font-size: 18px;line-height: 1.33;border-radius: 6px;"
									type="submit">发布</button>
							</div>
							<%
								}
							%>
						</div>
					</div>
				</div>
				<div class="container" style="color:red">
					<s:property value="exception.message"></s:property>
				</div>
				<div class="form-group">
					<textarea rows="10" cols="20" name="blog_content" class="ckeditor"><%=blog.getBlog_content()%></textarea>
				</div>
				<br />
			</form>
			<%
				}
			%>
		</div>
	</header>
	
	<!-- Footer -->
	<footer>
		<div class="container">
			<div class="row">
				<div class="col-lg-8 col-md-10 mx-auto">
					<p class="copyright text-muted">Copyright &copy;NJUST_CS_FORUM 2019</p>
				</div>
			</div>
		</div>
	</footer>
	
	<!-- ckeditor script -->
	<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
	<!-- Bootstrap core JavaScript -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Custom scripts for this template -->
	<script src="js/clean-blog.min.js"></script>
</body>
</html>
