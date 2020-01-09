<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page language="java" import="java.util.*" import="domain.*"
	import="Utils.Page" pageEncoding="UTF-8"%>
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

<title>MyBlog - 个人主页</title>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<meta name="description" content="个人主页。包含个人信息，好友，博客展示。">


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
.panel {
	margin-bottom: 20px;
	background-color: #fff;
	border: 1px solid transparent;
	border-radius: 4px;
	-webkit-box-shadow: 0 1px 1px rgba(0, 0, 0, .05);
	box-shadow: 0 1px 1px rgba(0, 0, 0, .05);
}

.panel-body {
	padding: 15px;
}

.panel-footer {
	padding: 10px 15px;
	background-color: black;
	border-top: 1px solid #ddd;
	border-bottom-right-radius: 3px;
	border-bottom-left-radius: 3px;
}
</style>
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
		style="background-image: url('img/about-bg.jpg')">
		<div class="overlay"></div>
		<div class="container">
			<div class="row">
				<div class="col-lg-8 col-md-10 mx-auto">
					<div class="site-heading">
						<h1>Welcome to MyBlog</h1>
						<span class="subheading">Have a good Time!</span>
					</div>
				</div>
			</div>
		</div>
	</header>

	<div class="container">
		<!-- 个人信息 -->
		<div class="row">
			<div class="col-md-4">
				<div class="col-md-12">
					<div class="panel">
						<div class="col-md-12 post-title panel-footer text-center">
							<div style="color:white">个人信息</div>
						</div>
						<%
							Set<User> cu_friends = (Set<User>) request.getAttribute("cufriends");
							User user = (User) ActionContext.getContext().get("user");
						%>
						<div class="col-md-12 ">
							<ul>
								<li>昵称：<s:property value="#user.user_nickname" /> <%
 	if (user.getRole().getRole_authority() > 1) {
 %> <img alt="" src="img/jiaosez.png"> <%
 	}
 %>
								</li>
								<li>年龄：<s:property value="#user.user_age" /></li>
								<li>性别：<s:property value="#user.user_gender" /></li>
								<li>地址：<s:property value="#user.user_address" /></li>
								<li>邮箱：<s:property value="#user.user_email" /></li>
								<li>爱好：<s:property value="#user.user_hobby" /></li>
								<li>注册时间：<%=user.getAdd_time()%></li>
							</ul>
						</div>
						<hr />
						<%
							if (!currentuser.getUser_name().equals(user.getUser_name())) {
								Iterator<User> it = cu_friends.iterator();
								Boolean flag = false;
								while (it.hasNext()) {
									User u = it.next();
									if (u.getUser_nickname().equals(user.getUser_nickname())) {
										flag = true;
										break;
									}
								}
								if (!flag) {
						%>
						<div class="col-md-12">
							<a class="btn btn-info btn-block"
								style="padding: 10px 70px;font-size: 15px;line-height: 1.33;border-radius: 6px;"
								href="<%=basePath%>friendmanage_add?user_nickname=<%=user.getUser_nickname()%>"
								onclick="window.alert('关注成功')">关注</a>
							<hr />
						</div>
						<%
							} else {
						%>

						<script type="text/javascript">
							function quxiaoquren() {
								var url = "<%=basePath%>friendmanage_delete?user_nickname=<%=user.getUser_nickname()%>";
								if (window.confirm("是否取消关注。")) {
									window.location.href = url;
									window.alert("取消关注成功。");
								}
							}
						</script>
						<div class="col-md-12">
							<a class="btn btn-danger btn-block"
								style="padding: 10px 70px;font-size: 15px;line-height: 1.33;border-radius: 6px;"
								href="<%=basePath%>main?user_nickname=<%=user.getUser_nickname()%>&page=1"
								onclick="quxiaoquren()">取消关注</a>
							<hr />
						</div>
						<%
							}
								if (currentuser.getRole().getRole_authority() == 7) {
									if (user.getRole().getRole_authority() == 1) {
						%>
						<div class="col-md-12">
							<a class="btn btn-success btn-block"
								style="padding: 10px 70px;font-size: 15px;line-height: 1.33;border-radius: 6px;"
								href="<%=basePath%>authmanage_setmoderator?user_nickname=<%=user.getUser_nickname()%>">设置协管员</a><br />
						</div>
						<%
							} else if (user.getRole().getRole_authority() == 3) {
						%>
						<div class="col-md-12">
							<a class="btn btn-warning btn-block"
								style="padding: 10px 70px;font-size: 15px;line-height: 1.33;border-radius: 6px;"
								href="<%=basePath%>authmanage_setuser?user_nickname=<%=user.getUser_nickname()%>">取消协管员</a><br />
						</div>
						<%
							}
								}
							} else {
						%>
						<div class="col-md-12">
							<a
								style="padding: 10px 35px;font-size: 15px;line-height: 1.33;border-radius: 6px;"
								class="btn btn-info btn-block" href="<%=basePath%>setinfo.jsp">修改信息</a>

							<div class="col-md-12">
								<hr />
							</div>
							<script type="text/javascript">
								function queren() {
									var url1 = "<%=basePath%>authmanage_modifypw?hadsendmail=false";
									var url2 = " <%=basePath%>main?user_nickname=<%=user.getUser_nickname()%>&page=1";
									if (window.confirm("是否修改密码。")) {
										window.location.href = url1;
										window.confirm("我们已向您的注册邮箱发送了确认邮件，请注意查收");
									} else {
										window.location.href = url2;
									}
								}
							</script>
							<button
								style="padding: 10px 35px;font-size: 15px;line-height: 1.33;border-radius: 6px;"
								class="btn btn-danger btn-block" onclick="queren()">修改密码</button>
						</div>
						<br />
						<%
							}
						%>
					</div>
				</div>
				<br />

				<!-- 好友信息 -->
				<div class="col-md-12">
					<div
						style="padding: 10px 15px;background-color: black;border-top: 1pxsolid #ddd;border-bottom-right-radius: 3px;border-bottom-left-radius: 3px;">
						<div style="color:white">好友</div>
					</div>

					<table class="table">
						<%
							try {
								Set<User> friends = (Set<User>) request.getAttribute("friends");
								Iterator<User> it = friends.iterator();
								while (it.hasNext()) {
									User u = it.next();
						%>
						<tr>
							<td><a
								href="<%=basePath%>main?user_nickname=<%=u.getUser_nickname()%>&page=1"><%=u.getUser_nickname()%></a></td>
						</tr>
						<%
							}
							} catch (Exception e) {
								e.printStackTrace();
							}
						%>
					</table>
				</div>
			</div>

			<!-- 博客信息 -->
			<div class="col-md-8">
				<div
					style="padding: 10px 15px;background-color: black;border-top: 1px solid #ddd;border-bottom-right-radius: 3px;border-bottom-left-radius: 3px;"
					class="col-md-12 ">
					<h5 style="color:white">博客</h5>
				</div>
				<div class="container-fluid">
					<div class="row">
						<div class="col-lg-12 col-md-10 mx-auto">
							<%
								List<Blog> blogs = (List<Blog>) request.getAttribute("blogs");
								Iterator<Blog> it = blogs.iterator();
								while (it.hasNext()) {
									Blog b = it.next();
							%>
							<div class="post-preview">
								<div style="">
									<a href="<%=basePath%>blog?blog_id=<%=b.getBlog_id()%>&page=1">
										<h2 class="post-title"><%=b.getTitle()%></h2>
									</a>
								</div>
								<article>
									<div class="container">
										<div class="row">
											<div class="col-lg-11 col-md-10 mx-auto">
												<div style="height:390px;overflow: hidden"><%=b.getBlog_content()%></div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-9">
											<small>发表时间：<%=b.getAdd_time()%></small>
										</div>
										<div class="col-md-3">
											<small><a
												href="<%=basePath%>blog?blog_id=<%=b.getBlog_id()%>&page=1">查看全文></a></small>
										</div>
									</div>
								</article>
								<hr />
							</div>
							<%
								}
							%>
						</div>
					</div>

					<!-- 分页导航条 -->
					<%
						Page page_info = (Page) ActionContext.getContext().get("page_info");
						int totalpage = page_info.getTotalpage();
						int currentpage = page_info.getCurrentPage();
					%>
					<div class="col-lg-12 col-md-10 mx-auto">
						<div class="container">
							<ul class="pagination pagination"
								style="margin-right: auto;margin-left: auto;">
								<li style="margin-right: auto;margin-left: auto;"><a
									href="<%=basePath%>main?user_nickname=<%=user.getUser_nickname()%>&page=<%=currentpage - 1%>">&laquo;前一页</a></li>
								<%
									for (int i = 1; i <= totalpage; i++) {
								%>
								<li style="margin-right: auto;margin-left: auto;"><a
									href="<%=basePath%>main?user_nickname=<%=user.getUser_nickname()%>&page=<%=i%>"><%=i%></a></li>
								<%
									}
								%>
								<li style="margin-right: auto;margin-left: auto;"><a
									href="
									<%=basePath%>main?user_nickname=<%=user.getUser_nickname()%>
									&page=<%=totalpage%>">最后一页&raquo;</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
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
	</div>
	<!-- Bootstrap core JavaScript -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Custom scripts for this template -->
	<script src="js/clean-blog.min.js"></script>
</body>
</html>
