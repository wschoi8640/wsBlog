<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.io.PrintWriter" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
<head>
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
<style type="text/css">
	a, a:hover {
		color : #000000;
		text-decoration : none;
	}
</style>
	<script type="text/javascript">
	function press(){
		if(event.keyCode == 13){ 
			submitFunction();
		} 
	}

		var lastID = 0;
		function submitFunction() {
			var chatName = $('#chatName').val();
			var chatContent = $('#chatContent').val();
			$.ajax({
				type: "POST",
				url: "./chatSubmit",
				data: {
					chatName : encodeURIComponent(chatName),
					chatContent : encodeURIComponent(chatContent)
				},
				success: function(result){
					if(result == 1){
						autoClosingAlert('#successMessage', 2000);
						$('#chatContent').val('');
						$('#chatName').val('');
					} else if(result == 0){
						autoClosingAlert('#failMessage', 2000);
					} else {
						autoClosingAlert('#warningMessage', 2000);
					}
				}
			});
			
		}
		function chatListFunction(type){
				var listType = type;
				$.ajax({
				type: "POST",
				url: "./chatFetch",
				data: {
		 			listType: listType,
				},
				success: function(data) {
					if(data=="") return;
					var parsed = JSON.parse(data);
					var result = parsed.result;
					for(var i = 0; i < result.length; i++){
						var random = Math.floor(Math.random() * 4) + 1;			
						addChat(result[i][0].value, result[i][1].value, result[i][2].value, random);
					}
					lastID = Number(parsed.last);
				}
			});
		}
		function addChat(chatName, chatContent, chatTime, random){
			$('#chatList').append('<div class="row">' +
					'<div class="col-lg-12">' +
					'<div class="media">' + 
					'<a class="pull-left" href="#">' +
					'<img class="media-object img-circle" src="resources/images/icon' +
					random +
					'.png" alt="">' +
					'</a>' +
					'<div class="media-body">' +
					'<h4 class="media-heading">' +
					chatName +
					'<span class="small pull-right">' +
					chatTime +
					'</span>' +
					'</h4>' +
					'<p>' +
					chatContent +
					'</p>' +
					'</div>' +
					'</div>' +
					'</div>' +
					'</div>' +
					'<hr>');
			$('#chatList').scrollTop($('#chatList')[0].scrollHeight)
		}
		function getRealTimeChat(){
			setInterval(function(){
				chatListFunction(lastID);	
			},1000);	
		}
		function autoClosingAlert(selector, delay){
			var alert = $(selector).alert();
			alert.show();
			window.setTimeout(function(){ alert.hide()}, delay);
		}

		window.onload = function(){
			getRealTimeChat();
		}
	</script>
</head>
<body>
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
	<script type="text/javascript">
			var userID = "${userID}";
	</script>
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
				<li id="myActive"><a href="guestBook" id="myFont7">방명록</a></li>
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
								<li><a href="doLogout" id="myFont2">로그아웃</a></li>
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
	<br><br>
	<div class="container">
		<div class="container bootstrap snippet">
			<div class="row">
				<div class="col-xs-12">
					<div class="portlet portlet-default">
						<div class="portlet-heading">
							<div class="portlet-title">
								<h4><i class="fa fa-circle text-green"></i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;실시간 방명록</h4>
							</div>
							<div class="clearfix"></div>
						</div>
						<div id="chat" class="panel-collapse collapse in">
							<div id="chatList" class="portlet-body chat-widget" style="overflow-y: auto; width: auto; height: 600px;">
							</div>
							<div class="portlet-footer" id="myFont12">
								<div class="row">
									<div class="form-group col-xs-4">
										<input style="height: 40px;" type="text" id="chatName" class="form-control" placeholder="이름" maxlength="8">
									</div>
								</div>
								<div class="row" style="height: 90px">
									<div class="form-group col-xs-10">
										<textarea style="height : 80px;" id="chatContent" class="form-control" placeholder="메시지를 입력하세요" maxlength="100" onkeypress="JavaScript:press()"></textarea>
									</div>
									<div class="form-group col-xs-1">
										<button type="button" style="width: 80px; height: 80px; font-size : 20px" class="btn btn-primary pull-right" onclick="submitFunction();" >전송</button>
										<div class="clearfix"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="alert alert-success" id="successMessage" style="display: none;">
			<strong>메시지 전송에 성공하였습니다.</strong>
		</div>
		<div class="alert alert-danger" id="failMessage" style="display: none;">
			<strong>이름과 내용을 모두 입력해주세요.</strong>
		</div>
		<div class="alert alert-warning" id="warningMessage" style="display: none;">
			<strong>데이터 베이스 오류가 발생했습니다.</strong>
		</div>
	</div>
			<script>
	$("#myDropdown2").on('click', function (e) {
		  e.stopPropagation();
		});
	</script>	
</body>
</html>