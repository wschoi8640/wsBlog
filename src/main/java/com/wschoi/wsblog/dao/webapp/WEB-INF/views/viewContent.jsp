<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.io.PrintWriter" %>

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
<script src="resources/js/jquery.popconfirm.js"></script>
<script src="resources/js/custom.js"></script>
<title>WSCHOI</title>
<script type="text/javascript">
	function getArticleContent(){
		var bbsID = "${bbsID}";
		$.ajax({
			type: "POST",
			url: "./getArticleContent",
			success: function(data){
				if(data=="") return;
				var parsed = JSON.parse(data);
				var result = parsed.result;
				for(var i = 0; i < result.length; i++){
					addArticleContent(result[i][0].value, result[i][1].value, result[i][2].value, result[i][3].value);
				}
				var pageNumber = Number(parsed.last);
				var bbsUserID = "${bbsUserID}";
			}
			
		});
	}	
	
	function addArticleContent(bbsTitle, userID, bbsDate, bbsContent){
		$('#contentList').append(
			'<tr>' + 
				'<td style ="width: 20%;">' + '글 제목' + '</td>' +
				'<td colspan="2">' + bbsTitle + '</td>' + 
			'</tr>' + 
			'<tr>' +
				'<td>' + '작성자' + '</td>' + 
				'<td colspan="2">' + userID + '</td>' + 
			'</tr>' + 
			'<tr>' + 
				'<td>' + '작성일자' + '</td>' + 
				'<td colspan="2">' + bbsDate + '</td>' + 
			'</tr>' + 
			'<tr>' + 
				'<td>' + '내용 ' + '</td>' + 
				'<td colspan="2" style="min-height : 200px; text-align: left;">' + bbsContent + '</td>' +
			'</tr>');
	}
	
	window.onload = function(){
		getArticleContent()
	}
</script>
</head>
<script type="text/javascript">
$(document).ready(function() {
	$("#delete").popConfirm({
	    title: "<strong>확인</strong>",
	    content: "게시물을 삭제하시겠습니까?",
	    placement: "right",
	    yesBtn: '네',
	    noBtn: '아니오'
	});  
});
</script>
<style type="text/css">
body {
  height: 756px;
}
</style>
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
			var bbsID = "${bbsID}";
			
			if(bbsID == null){
				alert('유효하지 않은 값 입니다.');
				location.href = 'bbs';
			}
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
				<li id="myActive"><a href="bbs" id="myFont1">게시판</a></li>
				<li><a href="menu" id="myFont6">학식메뉴</a></li>
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
	<br><br><br><br><br><br>
	<div class="container">
		<div class="row">
			<table class="table table-striped" style="text-align : center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="3" style="background-color: #eeeeee; text-align : center" id="myJumbo">게시판 글보기</th>
					</tr>
				</thead>
				<tbody id="contentList">
				</tbody>
			</table>
			<a href="bbs" class="btn btn-primary" id="btnList">목록</a>
			<c:choose>
				<c:when test="${userID eq bbsUserID}">	
					<a href="update?bbsID=${bbsID}" class="btn btn-primary">수정</a>
					<a href="delete.do?bbsID=${bbsID}" id="delete" class="btn btn-primary">삭제</a>
				</c:when>
			</c:choose>
		</div>
	</div>
		<script>
	$("#myDropdown2").on('click', function (e) {
		  e.stopPropagation();
		});
	</script>			
</body>
</html>