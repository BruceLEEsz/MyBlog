<%@ page language="java" import="java.util.*" import="domain.*"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!-- <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> -->
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<title>MyBlog - 修改基本信息</title>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="个人基本信息修改页面">

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

</head>

<body style="background-color: ghostwhite;">

	<!-- Navigation -->
	<nav class="navbar navbar-expand-lg navbar-light fixed-top container"
		id="mainNav">
		<div class="container">
			<a class="navbar-brand" href="<%=basePath%>index">MyBlog</a>
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
		style="background:rgba(180,200,180, 0.5);">
		<div class="container" style="padding-top:90px;">
			<div class="row">
				<div class="col-lg-8 col-md-8 mx-auto">
					<div class="subheading">
						<div class="container">
							<div class="row">
								<div class="col-md-9">
									<h1 class="post-title" style="color:white">修改个人信息</h1>
								</div>
							</div>
						</div>
						<div class="container">
							<form action="<%=path%>/authmanage_modifyinfo" method="post">
								<div class="container">
									<div class=" form-group"
										style="height:85px; >
									<div class="container">
										<label for="input1">昵称:</label> <input id="input1"
											class="form-control" type="text" name="user_nickname">
										<br />
									</div>
									<div class="container" style="color:red">
										<s:property value="exception.message" />
									</div>
								</div>
								<div class=" form-group" style="height:85px;">
									<div class="container">
										<label for="input2">年龄:</label> <input type="text"
											name="user_age" id="input2" class="form-control"> <br />
									</div>
								</div>
								<div class="form-group" style="height:40px;">
									<div class="container">
										<label>性别:</label> <label class="radio-inline"> <input
											type="radio" name="user_gender" id="optionsRadios1" value="男">
											男
										</label> <label class="radio-inline"> <input type="radio"
											name="user_gender" id="optionsRadios2" value="女"> 女
										</label> <label class="radio-inline"> <input type="radio"
											name="user_gender" id="optionsRadios3" value="保密"> 保密
										</label>
									</div>
								</div>
								<div class=" form-group" style="height:85px;">
									<div class="container">
										<label for="input3">生日:</label> <input type="date" id="input3"
											name="user_birthday" class="form-control"> <br />
									</div>
								</div>
								<div class=" form-group" style="height:85px;">
									<div class="container">
										<label for="input4">所在地:</label> <input type="text"
											name="user_address" id="form4" class="form-control">
										<br />
									</div>
								</div>
								<div class=" form-group" style="height:85px;">
									<div class="container">
										<label for="input5">爱好:</label> <input type="text"
											name="user_hobby" id="input5" class="form-control"> <br />
									</div>
								</div>

								<div class="container">
									<div class="row">
										<div class="col-md-4">
											<button
												style="padding: 10px 25px;font-size: 18px;line-height: 1.33;border-radius: 6px;"
												class="btn btn-success btn-block " type="submit">提交</button>
										</div>
										<div class="col-md-8">
											<button
												style="padding: 10px 25px;font-size: 18px;line-height: 1.33;border-radius: 6px;"
												class="btn btn-info btn-block" type="reset">重新填写</button>
										</div>
									</div>
								</div>
							</form>
							<br />
						</div>
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
</body>
</html>
