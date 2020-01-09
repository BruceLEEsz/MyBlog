<%@ page language="java" import="java.util.*" import="domain.*"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!-- <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> -->
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<title>CS FORUM - 修改密码</title>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="密码修改页面">

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
			<a class="navbar-brand" href="<%=basePath%>index">CS FORUM</a>
			<button class="navbar-toggler navbar-toggler-right" type="button"
				data-toggle="collapse" data-target="#navbarResponsive"
				aria-controls="navbarResponsive" aria-expanded="false"
				aria-label="Toggle navigation">
				Menu <i class="fas fa-bars"></i>
			</button>

			<%
				User currentuser = (User) session.getAttribute("currentuser");
				if (currentuser != null) {
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
		<%
			}
		%>
	</nav>

	<header class="masthead container"
		style="background:rgba(180,200,180, 0.5);">
		<div class="container" style="padding-top:90px;">
			<div class="row">
				<div class="col-lg-8 col-md-8 mx-auto">
					<div class="container">
						<div>
							<h1 class="post-title" style="color:white">密码修改</h1>
						</div>
						<%
							Boolean resetpw1 = (Boolean) request.getAttribute("resetpw");
							Boolean resetpw2 = new Boolean(request.getParameter("resetpw"));
							Boolean resetpw = resetpw1 != null ? resetpw1 : resetpw2;
						%>
						<%
							if (resetpw != null && resetpw) {
						%>
						<form action="<%=path%>/authmanage_resetpw" method="post">
							<input type="hidden" value="<%=request.getParameter("email")%>"
								name="user_email">
							<%
								} else {
							%>
							<form action="<%=path%>/authmanage_modifypw" method="post">
								<%
									}
								%>
								<div class="form-groupe">
									<label for="input1">密码</label> <input class="form-control"
										id="input1" name="user_password" type="password"> <br />
								</div>
								<div class="form-groupe">
									<label for="input2">密码确认：</label> <input type="password"
										name="user_password2" class="form-control" id="input2">
									<br />
								</div>

								<input type="hidden" name="hadsendmail" value="true">

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
							<br /> <br />
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
					<p class="copyright text-muted">Copyright &copy; NJUST_CS_FORUM 2019</p>
				</div>
			</div>
		</div>
	</footer>
</body>
</html>
