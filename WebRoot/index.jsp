<%@ page language="java" import="java.util.*" import="domain.*"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>

<head>
<base href="<%=basePath%>">

<title>CS Forum - 主页</title>

<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="主页。显示最新发表的博客。">

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
		style="background-image: url('img/home-bg.jpg')">
		<div class="overlay"></div>
		<div class="container">
			<div class="row">
				<div class="col-lg-8 col-md-10 mx-auto">
					<div class="site-heading">
						<h1>Welcome to CS Forum</h1>
						<span class="subheading">Have a good Time!</span> <span
							class="subheading"><s:property value="#message"></s:property></span>
					</div>
				</div>
			</div>
		</div>
	</header>

	<!-- Main Content -->
	<div class="container">
		<div class="row">
			<div class="col-lg-8 col-md-10 mx-auto">
				<%
					List<Blog> blogs = (List<Blog>) request.getAttribute("blogs");
					Iterator<Blog> it = blogs.iterator();
					int i = 0;
					while (it.hasNext()) {
						Blog b = it.next();
				%>
				<div class="post-preview">
					<a href="<%=basePath%>blog?blog_id=<%=b.getBlog_id()%>&page=1">
						<h2 class="post-title"><%=b.getTitle()%></h2>
					</a>
					<p class="post-meta">
						Posted by<strong> <a style="text-decoration: none"
							href="<%=basePath%>main?user_nickname=<%=b.getUser().getUser_nickname()%>&page=1"><%=b.getUser().getUser_nickname()%></a></strong>
						on
						<%=b.getAdd_time()%>
					</p>
					<hr />
				</div>
				<%
					i++;
						if (i == 15)
							break;
					}
				%>
			</div>
		</div>
	</div>
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
	<!-- Bootstrap core JavaScript -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Custom scripts for this template -->
	<script src="js/clean-blog.min.js"></script>
</body>

</html>
