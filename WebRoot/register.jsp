<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<title>MyBlog - 注册</title>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="注册页面">

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
<style>
input {
	padding: 10px 5px;
	font-size: 18px;
	line-height: 1.33;
	border-radius: 6px;
}
</style>
</head>

<body style="background-color: ghostwhite;">
	<!-- Navigation -->
	<nav class="navbar navbar-expand-lg navbar-light fixed-top container"
		id="mainNav">
		<div class="container">
			<a class="navbar-brand" href="<%=basePath%>login.jsp">MyBlog</a>
		</div>
	</nav>

	<header class="masthead container"
		style=" background-image: url('img/about-bg.jpg');background-color: rgba(0,0,0,0);">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 col-md-10 mx-auto">
					<div class="site-heading">
						<h1>MyBlog</h1>
						<br />
						<form action="<%=path%>/authmanage_register" method="post">
							<caption>
								<h2>注册</h2>
							</caption>
							<div class=" form-group">
								<input type="text" name="user_name" placeholder="用户名">
							</div>
							<div class="form-group">
								<input type="email" name="user_email" placeholder="邮箱">

							</div>
							<div class="form-group">
								<input type="password" name="user_password" placeholder="密码">
							</div>
							<div class="form-group">
								<input type="password" name="user_password2" placeholder="密码确认">
							</div>
							<div style="color:red">
								<s:property value="exception.message" />

								<s:property value="#message" />
								<br />
							</div>
							<button type="submit"
								style="padding: 10px 25px;font-size: 18px;line-height: 1.33;border-radius: 6px;"
								class="btn btn-info">注册</button>
							<button type="reset"
								style="padding: 10px 25px;font-size: 18px;line-height: 1.33;border-radius: 6px;"
								class="btn btn-danger">重新填写</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</header>
	
	<!-- Footer -->
	<footer>
		<div class="container">
			<div class="row">
				<div class="col-lg-8 col-md-10 mx-auto">
					<p class="copyright text-muted">Copyright &copy; MyBlog 2019</p>
				</div>
			</div>
		</div>
	</footer>
	<!-- Bootstrap core JavaScript -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Custom scripts for this template -->
	<script src="js/clean-blog.min.js"></script>
</body>
</html>
