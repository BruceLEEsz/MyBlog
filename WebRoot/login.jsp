<%@page import="com.opensymphony.xwork2.ActionContext"%>
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

<title>MyBlog - 登录</title>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="登录页面">
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
		style="background-image: url('img/home-bg.jpg');background-color: rgba(0,0,0,0);">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 col-md-10 mx-auto">
					<div class="site-heading">
						<h1>MyBlog</h1>
						<br />
						<form action="<%=path%>/authmanage_login" method="post">
							<caption>
								<h2>登录</h2>
							</caption>
							<div class=" form-group">
								<input id="loginname" type="text" name="user_loginname"
									placeholder="请输入用户名或电子邮箱">
							</div>
							<div class="form-group">
								<input id="password" type="password" name="user_password"
									placeholder="密码">
							</div>
							<div style="color:red">
								<s:property value="exception.message"></s:property>
								<s:property value="#message"></s:property>
							</div>

							<button type="submit"
								style="padding: 10px 90px;font-size: 18px;line-height: 1.33;border-radius: 6px;"
								class="btn btn-info">登录</button>

							<script type="text/javascript">
								function wangji() {
									var email = window.prompt("请输入注册邮箱。", "");
									if (email) {
										window.location.href = "<%=basePath%>authmanage_findpw?email=" + email;
							
									}
								}
							</script>
							<div class="form-group">
								<a href=" javascript:void(0)" onclick="wangji()">忘记密码？</a>|||||||
								<a href="<%=path%>/register.jsp">新用户？</a>
							</div>
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
