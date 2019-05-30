<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<script src='https://www.google.com/recaptcha/api.js'></script>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width", initial-scale="1">
<link rel="stylesheet" href="resources/css/jquery-ui.min.css">
<link rel="stylesheet" href="resources/css/bootstrap.css">
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/custom.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@7.32.2/dist/sweetalert2.min.css">
<script src="resources/js/jquery.js"></script>
<script src="resources/js/jquery.min.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/custom.js"></script>
<title>WSCHOI</title>
</head>
<script type="text/javascript">
function press()
{
		if(event.keyCode == 13)
		{ 
				joinFunction();
		} 
}

function joinFunction() 
{
		var userID = $('#userID').val();
		var userPassword = $('#userPassword').val();
		var userName = $('#userName').val();
		var userGender = $('#userGender').val();
		var userEmail = $('#userEmail').val();
		var entryCode = $('#entryCode').val();
		
			if(userID == null || userID == '' || userPassword == null || userPassword == '' ||
					userName == null || userName == '' || userGender == null || userGender == '' ||
					userEmail == null || userEmail == '' || entryCode == null || entryCode == '')
			{
					autoClosingAlert('#noInput', 2000);
			}	
			else 
			{
					submitFunction();
			}
}

function submitFunction() 
{
		var userID = $('#userID').val();
		var userPassword = $('#userPassword').val();
		var userName = $('#userName').val();
		var userGender = $('#userGender').val();
		var userEmail = $('#userEmail').val();
		var entryCode = $('#entryCode').val();
	
		if(userID != null && userID != '' && userPassword != null && userPassword != '' &&
				userName != null && userName != '' && userGender != null && userGender != '' &&
				userEmail != null && userEmail != '' && entryCode != null && entryCode != '')
		{
				$.ajax({
						type: "POST",
						url: "./doJoin",
						async : false,
						data: 
						{
								userID : encodeURIComponent(userID),
								userPassword : encodeURIComponent(userPassword),
								userName : encodeURIComponent(userName),
								userGender : encodeURIComponent(userGender),
								userEmail : encodeURIComponent(userEmail),
								entryCode : encodeURIComponent(entryCode)
						},
						success: function(result)
						{
								if(result == 0)
								{
									autoClosingAlert('#wrongCode', 2000);
								} 
								else if(result == -1)
								{
									autoClosingAlert('#idExists', 2000);
								}
								else if(result == 1)
								{
									var url = "main";
									window.location.replace(url);
								} 
								else if(result == -2) 
								{
									autoClosingAlert('#dbError', 2000);
								}
						}
				});
		}
}
function autoClosingAlert(selector, delay)
{
		var alert = $(selector).alert();
		alert.show();
		window.setTimeout(function(){ alert.hide()}, delay);
}
</script>
<body>
<c:choose>
	<c:when test="${darkMode eq null}">
		<script>
			$(function()
			{
					setBrightMode();
			});
		</script>
	</c:when>
	<c:when test="${darkMode eq 1}">
		<script>
			$(function()
			{
					setBrightMode();
			});
			$(function()
			{
					$('.example_2').removeAttr('checked');
			});
		</script>
	</c:when>
	<c:when test="${darkMode eq -1}">
		<script>
			$(function()
			{
				setDarkMode();
			});
			$(function()
			{
					$('#example_2').attr('checked', true);
			});
		</script>
	</c:when>
</c:choose>
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
				<li><a href="main" id="myFont5">메인</a></li>
				<li><a href="bbs"id="myFont1">게시판</a></li>
				<li><a href="menu" id="myFont6">학식메뉴</a></li>
				<li><a href="guestBook" id="myFont7">방명록</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle menu-dropicon" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expaneded="false" id="myDropdown">
                        <i id="settingIcon" class="fa fa-cog fa-spin fa-fw menu-icon"></i><span class="caret"></span>
                    </a>
					<ul class="dropdown-menu" id="myDropdown2">
						<li><a href="login" id="myFont2">로그인</a></li>
						<li><a href="join" id="myFont3">회원가입</a></li>
						<li>
							<div class="custom-switch custom-switch-label-onoff">
  				   				<input class="custom-switch-input" id="example_2" type="checkbox" onclick="darkmodeHandler();">
                   				<label class="custom-switch-btn" for="example_2"></label>
                   			</div>
                   		</li>
					</ul>
				</li>
			</ul>
		</div>
	  </div>
	</nav>
	<br><br><br><br><br><br>	
	<div class="container">
		<div class="col-lg-4"></div>
		<div class="col-lg-4">
			<div class="jumbotron" style="padding-top : 20px;" id="myJumbo">
				<form method="post" action="doJoin">
					<h3 style="text-align : center;">회원가입 화면</h3>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="아이디" id="userID" maxlength="20">
					</div>
					<div class="form-group">
						<input type="password" class="form-control" placeholder="비밀번호" id="userPassword" maxlength="20">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="이름" id="userName" maxlength="20">
					</div>
					<div class="form-group" style="text-align: center;">
						<div class="btn-group" data-toggle="buttons">
							<label class="btn btn-primary active">
								<input type="radio" id="userGender" autocomplete="off" value="남자" checked>남자
							</label>
							<label class="btn btn-primary">
								<input type="radio" id="userGender" autocomplete="off" value="여자" checked>여자
							</label>
						</div>
					</div>
					<div class="form-group">
						<input type="email" class="form-control" placeholder="이메일" id="userEmail" maxlength="50">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="가입코드" id="entryCode" maxlength="20" onkeypress="JavaScript:press()">
					</div>
					<button type="button" class="btn btn-primary form-control" onclick="joinFunction();">회원가입</button>
				</form>
		</div>
		<div class="alert alert-warning" id="idExists" style="display: none;">
			<strong>이미 존재하는 아이디 입니다.</strong>
		</div>
		<div class="alert alert-warning" id="noInput" style="display: none;">
			<strong>입력이 완성되지 않았습니다.</strong>
		</div>
		<div class="alert alert-danger" id="wrongCode" style="display: none;">
			<strong>가입코드가 일치하지 않습니다.</strong>
		</div>
		<div class="alert alert-warning" id="dbError" style="display: none;">
				<strong>데이터 베이스 오류가 발생했습니다.</strong>
		</div>
	</div>
	</div>
	<script>
		$("#myDropdown2").on('click', function (e) 
		{
		 	 e.stopPropagation();
		});
	</script>
</body>
</html> 