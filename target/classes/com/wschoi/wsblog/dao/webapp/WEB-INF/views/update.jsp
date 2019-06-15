<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.io.PrintWriter" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width", initial-scale="1">
<link rel="stylesheet" href="https://dnjstjr.site/resources/css/jquery-ui.min.css">
<link rel="stylesheet" href="https://dnjstjr.site/resources/css/bootstrap.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://dnjstjr.site/resources/css/custom.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@7.32.2/dist/sweetalert2.min.css">
<link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote.css" rel="stylesheet">
<link rel="stylesheet" href="https://dnjstjr.site/resources/css/darkly-summernote.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote.js"></script>
<script src="https://dnjstjr.site/resources/js/summernote-ko-KR.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<script src="https://dnjstjr.site/resources/js/custom.js"></script>
<style type="text/css">
.navbar{
      border:1px solid #000;
 	}
</style>
<title>WSCHOI</title>
</head>
<script type="text/javascript">
function checkFunction(){
	var bbsID = "${bbsID}";
	var pageNumber = "${pageNumber}";
	var myTitle = $('#myFont9').val();
	var myContent = $($('#summernote').summernote('code')).text();
	var myContent1 = $('#summernote').val();
	$('#summernote').val(myContent);
	
	if((myContent == null && myContent == '') && (myContent1 != null && myContent1 != '')){
		$('#summernote').val(myContent1);
	}
	
	if(myTitle == null || myTitle == ''){
		autoClosingAlert('#noTitle', 2000);
		return false;
	}
	else if((myTitle != null && myTitle != '') && (myContent == null || myContent == '') && (myContent1 == null || myContent1 == '')){
		autoClosingAlert('#noContent', 2000);	
		return false;
	}
	else {
		$.ajax({
			type : "POST",
			url : "/article/doUpdate/" + bbsID,
			async : false,
			data : {
				myTitle : encodeURIComponent(myTitle),
				myContent : encodeURIComponent(myContent1)
			},
			success : function(result) {
				if (result == -1) {
					alert('failed to update!');
					history.back()

				} else if (result == 1) {
					var url = "/bbs/" + pageNumber;
					window.location.replace(url);
				}
			}
		});
		return true;
	}
}
function autoClosingAlert(selector, delay){
	var alert = $(selector).alert();
	alert.show();
	window.setTimeout(function(){ alert.hide()}, delay);
}
</script>
<body>
	<c:choose>
		<c:when test="${darkMode eq null}">
			<script>
				$(function() {
					setBrightMode();
				});
			</script>
		</c:when>
		<c:when test="${darkMode eq 1}">
			<script>
				$(function() {
					setBrightMode();
				});
				$(function() {
					$('.example_2').removeAttr('checked');
				});
			</script>
		</c:when>
		<c:when test="${darkMode eq -1}">
			<script>
				$(function() {
					setDarkMode();
				});
				$(function() {
					$('#example_2').attr('checked', true);
				});
			</script>
		</c:when>
	</c:choose>
	<script type="text/javascript">
		var userID = "${userID}";
		var pageNumber = "${pageNumber}";
		var bbsUserID = "${bbsUserID}";

		if (userID == "") {
			alert('login first!');
			window.location.replace('/login');
		}		
		else if(bbsUserID != userID){
			alert('유효하지 않은 접근 입니다.');
			window.location.replace('/bbs/' + pageNumber);
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
			<a class="navbar-brand" href="/main"  id="myFont">WSCHOI BLOG</a>
		</div>
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="/main" id="myFont5">메인</a></li>
				<li id="myActive"><a href="/bbs/1" id="myFont1">게시판</a></li>
				<li><a href="/findMenu" id="myFont6">학식메뉴</a></li>
				<li><a href="/guestBook" id="myFont7">방명록</a></li>
			</ul>	
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle menu-dropicon" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expaneded="false" id="myDropdown">
                        <i id="settingIcon" class="fa fa-cog fa-spin fa-fw menu-icon"></i><span class="caret"></span>
                    </a>	
					<ul class="dropdown-menu" id="myDropdown2">
						<li><a href="/doLogout" id="myFont2">로그아웃</a></li>
						<li><div class="custom-switch custom-switch-label-onoff">
  				   <input class="custom-switch-input" id="example_2" type="checkbox" onclick="darkmodeHandler();">
                   <label class="custom-switch-btn" for="example_2"></label>
                   </div></li>
					</ul>
				</li>
			</ul>
		</div>
	  </div>
	</nav>
	<br><br><br><br><br><br>
	<div class="container">
		<div class="row">
		<form method = "post" action="doUpdate">
			<table class="table table-striped" style="border : 1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="2" style="background-color: #eeeeee; text-align : center" id="myJumbo">게시판 글 수정 양식</th>
					</tr>
				</thead>
				<tbody id="myFont10">
					<tr id="myFont11">
						<td><input type="text" class="form-control" placeholder="글 제목" name="bbsTitle" maxlength="50" value="${bbsTitle}" id="myFont9"></td>
					</tr>
					<tr>
						<td><textarea class="form-control" placeholder="글 내용" name="bbsContent" maxlength="2048" style="height : 350px;" id="summernote">${bbsContent}</textarea></td>
					</tr>
				</tbody>
			</table>
			<button type="button" class="btn btn-primary pull-right"
					onclick="checkFunction();">글수정</button>
		</form>
		</br>
		</div>
		<div class="alert alert-warning" id="noTitle" style="display: none;">
			<strong>제목을 입력하세요.</strong>
		</div>
		<div class="alert alert-warning" id="noContent" style="display: none;">
			<strong>내용을 입력하세요.</strong>
		</div>
	</div>
		<script>
	$("#myDropdown2").on('click', function (e) {
		  e.stopPropagation();
		});
	</script>			
</body>
</html>