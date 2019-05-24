<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.io.PrintWriter"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width", initial-scale="1">
<link rel="stylesheet" href="resources/css/jquery-ui.min.css">
<link rel="stylesheet" href="resources/css/bootstrap.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/custom.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@7.32.2/dist/sweetalert2.min.css">
<script src="resources/js/jquery.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="resources/js/custom.js"></script>
<title>WSCHOI</title>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" id="myNav">
	  <div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false" id="myCollapse">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="main" id="myFont">WSCHOI BLOG</a>
		</div>
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li id="myActive"><a href="main" id="myFont1">메인</a></li>
				<li><a href="bbs"id="myFont5">게시판</a></li>
				<li><a href="findMenu" id="myFont6">학식메뉴</a></li>
				<li><a href="guestBook" id="myFont7">방명록</a></li>
			</ul>
			<c:choose>
				<c:when test="${userID eq null}">
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown">
							<a href="#" class="dropdown-toggle menu-dropicon" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expaneded="false" id="myDropdown">
		                        <i id="settingIcon" class="fa fa-cog fa-spin fa-fw menu-icon"></i><span class="caret"></span>
		                    </a>
							<ul class="dropdown-menu" id="myDropdown2">
								<li><a href="login" id="myFont2">로그인</a></li>
								<li><a href="join" id="myFont3">회원가입</a></li>
								<li><div class="custom-switch custom-switch-label-onoff">
		  				   <input class="custom-switch-input" id="example_2" type="checkbox" onclick="darkmodeHandler();">
		                   <label class="custom-switch-btn" for="example_2"></label>
		                   </div></li>
							</ul>
						</li>
					</ul>
				</c:when>
				<c:otherwise> 
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown">
							<a href="#" class="dropdown-toggle menu-dropicon" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expaneded="false" id="myDropdown">
		                        <i id="settingIcon" class="fa fa-cog fa-spin fa-fw menu-icon"></i><span class="caret"></span>
		                    </a>
							<ul class="dropdown-menu" id="myDropdown2">
								<li><a href="logout.do" id="myFont2">로그아웃</a></li>
								<li><div class="custom-switch custom-switch-label-onoff">
		  				   <input class="custom-switch-input" id="example_2" type="checkbox" onclick="darkmodeHandler();">
		                   <label class="custom-switch-btn" for="example_2"></label>
		                   </div></li>
							</ul>
						</li>
					</ul>
				</c:otherwise>
			</c:choose>
		</div>
	 </div>
	</nav>
	<br><br>
		<div id="myCarousel" class="carousel slide" data-ride="carousel">
			<ol class="carousel-indicators">
				<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				<li data-target="#myCarousel" data-slide-to="1"></li>
			</ol>
			<div class="carousel-inner">
				<div class="item active">
					<img src="resources/images/6.jpg">
				</div>
				<div class="item">
					<img src="resources/images/7.jpg">
				</div>
			</div>
			<a class="left carousel-control" href="#myCarousel" data-slide="prev">
				<span class="glyphicon glyphicon-chevron-left">
				</span>
			</a>
			<a class="right carousel-control" href="#myCarousel" data-slide="next">
				<span class="glyphicon glyphicon-chevron-right">
				</span>
			</a>
		</div>
	<br><br>
	<div class="container">
		<div class="jumbotron" id="myJumbo">
			<div class="container" id="myFont4">
				<h1>웹 사이트 소개</h1>
				<p>이 웹사이트는 부트스트랩으로 만든 JSP 웹 사이트 입니다. 최소한의 간단한 로직만을 이용해서 개발했습니다. 디자인 템플릿으로는 부트스트랩을 이용했습니다.</p>
				<p><a class="btn btn-primary btn-pull" href="#" role="button">자세히 알아보기</a></p>
			</div>
		</div>
	</div>
	<c:choose>
		<c:when test="${darkMode eq null}">
			<script>
				$(function() {setBrightMode();});
			</script>
		</c:when>
		<c:when test="${darkMode eq 1}">
			<script>
				$(function() {setBrightMode();});
				$(function() {$('.example_2').removeAttr('checked');});
			</script>
		</c:when>
		<c:when test="${darkMode eq -1}">
			<script>
				$(function() {setDarkMode();});
				$(function() {$('#example_2').attr('checked', true);});
			</script>
		</c:when>
	</c:choose>
	<script>
			$("#myDropdown2").on('click', function (e) {
				  e.stopPropagation();
				});
	</script>
	<script type="text/javascript">
			var userID = "${userID}";
	</script>
</body>
</html>