<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page language="java" import="java.util.*" import=" domain.*"
	import="Utils.Page" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@  taglib prefix="s" uri="/struts-tags"%>

<!-- <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> -->
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<title>MyBlog - 博客</title>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="博客展示页。展示博客相关信息以及评论">

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
	background-color: #f5f5f5;
	border-top: 1px solid #ddd;
	border-bottom-right-radius: 3px;
	border-bottom-left-radius: 3px;
}
</style>

<%
	Blog blog = (Blog) ActionContext.getContext().get("blog");
%>
<script type="text/javascript">
	function quren() {
		var url = '<%=basePath%>blogmanage_delete?blog_id=<%=blog.getBlog_id()%>';
		if (window.confirm("是否删除博客")) {
			window.location.href = url;
			window.alert("删除成功");
		}
	}
</script>

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
	<header class="container masthead"
		style=" background-image: url('img/contact-bg.jpg') ">
		<div class="overlay"></div>
		<div class="container">
			<div class="row">
				<div class="col-lg-8 col-md-10 mx-auto">
					<div class="post-heading">
						<h1><%=blog.getTitle()%></h1>
						<h2 class="subheading"></h2>
						<span class="meta">Posted by <a
							href="<%=basePath%>main?user_nickname=<%=blog.getUser().getUser_nickname()%>&page=1"><%=blog.getUser().getUser_nickname()%></a>
							on <%=blog.getAdd_time()%>
						</span> <br />
						<%
							if (currentuser.getUser_nickname().equals(blog.getUser().getUser_nickname())) {
						%>
						<div class="container">
							<a class="btn btn-info"
								style="padding: 10px 30px;font-size: 18px;line-height: 1.33;border-radius: 6px;"
								href="<%=basePath%>blogmanage_query?modify=true&blog_id=<%=blog.getBlog_id()%>">修改博客</a>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="btn btn-danger"
								style="padding: 10px 30px;font-size: 18px;line-height: 1.33;border-radius: 6px;"
								onclick="quren()">删除博客</button>
						</div>
						<%
							} else if ((currentuser.getRole().getRole_authority() > 1)
									&& (!blog.getUser().getUser_nickname().equals("admin"))) {
						%>
						<div class="container">
							<button class="btn btn-danger"
								style="padding: 10px 30px;font-size: 18px;line-height: 1.33;border-radius: 6px;"
								onclick="quren()">删除博客</button>
						</div>
						<%
							}
						%>
					</div>
				</div>
			</div>
		</div>
	</header>
	<!-- 博客内容 -->
	<article>
		<div class="container">
			<div class="row">
				<div class="col-lg-8 col-md-10 mx-auto"><%=blog.getBlog_content()%>
				</div>
			</div>
		</div>
	</article>
	<hr />

	<!-- 评论发表表单 -->
	<div class="container">
		<form
			action="<%=basePath%>commentmanage_post?blog_id=<%=blog.getBlog_id()%>"
			method="post">
			<div class="form-group">
				<textarea class="form-control" rows="5" cols="75"
					name="comment_content"></textarea>
			</div>
			<button
				style="padding: 10px 25px;font-size: 18px;line-height: 1.33;border-radius: 6px;"
				class="btn btn-info" type="submit">发表评论</button>
		</form>
	</div>
	<hr />

	<!-- 评论展示 -->
	<div class="container">
		<%
			List<Comment> comments = (List<Comment>) request.getAttribute("comments");
			Iterator<Comment> it = comments.iterator();
			while (it.hasNext()) {
				Comment c = it.next();
		%>
		<div class="panel panel-info">
			<div class="panel-footer">
				<div class="row">
					<div class="col-md-10">
						发表时间:<%=c.getAdd_time()%></div>
					<div class="col-md-2">
						<form action="<%=basePath%>commentmanage_delete" method="post">
							<input type="hidden" name="comment_id"
								value="<%=c.getComment_id()%>"> <input type="hidden"
								name="blog_id" value="<%=blog.getBlog_id()%>">
							<%
								if (currentuser.getUser_nickname().equals(c.getUser().getUser_nickname())) {
							%>
							<button
								style="padding: 10px 25px;font-size: 13px;line-height: 1.33;border-radius: 6px;"
								class="btn btn-danger btn-block" type="submit">删除评论</button>
							<%
								} else if ((currentuser.getRole().getRole_authority() > 1)
											&& (!c.getUser().getUser_nickname().equals("admin"))) {
							%>
							<button
								style="padding: 10px 25px;font-size: 13px;line-height: 1.33;border-radius: 6px;"
								class="btn btn-danger btn-block" type="submit">删除评论</button>
							<%
								}
							%>
						</form>
					</div>
				</div>
			</div>
			<div class="panel-body">
				<%=c.getComment_content()%></div>
			<div class="panel-footer">
				评论人: <a
					href="<%=basePath%>main?user_nickname=<%=c.getUser().getUser_nickname()%>&page=1"><%=c.getUser().getUser_nickname()%></a>
			</div>
		</div>
		<%
			}
		%>
		<!-- 分页导航条 -->
		<%
			Page page_info = (Page) ActionContext.getContext().get("page_info");
			int totalpage = page_info.getTotalpage();
			int currentpage = page_info.getCurrentPage();
		%>
		<div class="container">
			<ul class="pagination pagination"
				style="margin-right: auto;margin-left: auto;">
				<li style="margin-right: auto;margin-left: auto;"><a
					href="<%=basePath%>blog?blog_id=<%=blog.getBlog_id()%>&page=<%=currentpage - 1%>">&laquo;前一页</a></li>
				<%
					for (int i = 1; i <= totalpage; i++) {
				%>
				<li style="margin-right: auto;margin-left: auto;"><a
					href="<%=basePath%>blog?blog_id=<%=blog.getBlog_id()%>&page=<%=i%>"><%=i%></a></li>
				<%
					}
				%>
				<li style="margin-right: auto;margin-left: auto;"><a
					href="<%=basePath%>blog?blog_id=<%=blog.getBlog_id()%>&page=<%=totalpage%>">最后一页&raquo;</a></li>
			</ul>
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
	<!-- Bootstrap core JavaScript -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Custom scripts for this template -->
	<script src="js/clean-blog.min.js"></script>
</body>
</html>
