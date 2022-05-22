<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<!--edge浏览器H5兼容设置-->
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<!--360浏览器H5兼容设置-->
		<meta name="renderer" content="webkit" />
		<title>网上商城</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!--导入核心文件-->
		<script src="../bootstrap3/js/holder.js"></script>
		<link href="../bootstrap3/css/bootstrap.css" rel="stylesheet" type="text/css">
		<script src="../bootstrap3/jquery-1.9.1.min.js"></script>
		<script src="../bootstrap3/js/bootstrap.js"></script>
		<!-- 字体图标 -->
		<link rel="stylesheet" href="../bootstrap3/font-awesome-4.7.0/css/font-awesome.css" />
		<link rel="stylesheet" type="text/css" href="../css/layout.css" />
		<link rel="stylesheet" type="text/css" href="../css/top.css" />
		<script src="bootstrap3/jquery-1.9.1.min.js"></script>
				<script type="text/javascript">
			$(function () {
				$.ajax({
					url:"/user/getUserFromSession",
					type:"GET",
					dataType:"JSON",
					success:function (json) {
						if (json.data==null){
							$("#logout").hide();
						}else {
							$("#username").text(json.data.username);
							$("#login").hide();
						}
					}
				})
				$("#logout").click(function () {
					$.ajax({
						url:"/user/logout",
						type:"GET",
						dataType:"JSON",
						success:function () {
							alert("注销成功！");
						}
					})
				})
			})
		</script>
	</head>
	<body>
		<!--头部-->
		<header class="header">
			<!--网上商城logo-->
			<div class="row">
				<div class="col-md-3">
					<a href="index.html">
						<h1>网上商城</h1>
					</a>
				</div>
				<!--快捷选项-->
				<div class="col-md-9 top-item">
					<ul class="list-inline pull-right">

						<li><a href="orders.html"><span class="fa fa-file-text"></span>&nbsp;订单</a></li>
						<li class="li-split">|</li>
						<li><a href="cart.html"><span class="fa fa-cart-plus"></span>&nbsp;购物车</a></li>
						<li class="li-split">|</li>
						<li>
							<!--下列列表按钮 ：管理-->
							<div class="btn-group">
								<button type="button" class="btn btn-link dropdown-toggle" data-toggle="dropdown">
									<span id="top-dropdown-btn"><span class="fa fa-gears"></span>&nbsp;管理 <span class="caret"></span></span>
								</button>
								<ul class="dropdown-menu top-dropdown-ul" role="menu">

									<li><a href="address.html">收货管理	</a></li>
								</ul>
							</div>
						</li>
						<li class="li-split">|</li>
						<li><a href="login.html"><span class="fa fa-user"></span>&nbsp;
							<c:if test="${empty sessionScope.username}">
								登录
							</c:if>
							<c:if test="${not empty sessionScope.username}">
								注销
							</c:if>

							</a></li>
					</ul>
				</div>
			</div>
		</header>
		<!--导航 -->
		<!--分割导航和顶部-->
		<div class="row top-nav">

			<div class="col-md-6">
				<form action="search.html" class="form-inline pull-right" role="form">
					<div class="form-group">
						<input type="hidden" name="page" value=1>
						<input type="text" class="form-control" id="search" name="keyWord" placeholder="请输入商品名称进行搜索">
					</div>
					<button type="submit" class="btn btn-default btn-sm"><span class="fa fa-search"></span></button>
				</form>
			</div>
		</div>
		<!--头部结束-->
		<!--导航结束-->
	</body>
</html>