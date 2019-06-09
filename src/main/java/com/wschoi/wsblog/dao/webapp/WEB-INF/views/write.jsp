<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width" , initial-scale="1">
<link rel="stylesheet" href="resources/css/jquery-ui.min.css">
<link rel="stylesheet" href="resources/css/bootstrap.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/custom.css">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.2.0/css/all.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/sweetalert2@7.32.2/dist/sweetalert2.min.css">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote.css"
	rel="stylesheet">
<link rel="stylesheet" href="resources/css/darkly-summernote.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote.js"></script>
<script src="resources/js/summernote-ko-KR.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<script src="resources/js/custom.js"></script>
<style type="text/css">
.navbar {
	border: 1px solid #000;
}
</style>
<script type="text/javascript">
	function sendFile(file, editor) {
		// 파일 전송을 위한 폼생성
		data = new FormData();
		data.append("uploadFile", file);
		$.ajax({ // ajax를 통해 파일 업로드 처리
			data : data,
			method : "POST",
			url : "./process.jsp",
			cache : false,
			dataType : 'json',
			contentType : false,
			processData : false,
			success : function(data) { // 처리가 성공할 경우
				alert(data);
				$('#summernote').summernote("insertImage", data.url);
			}
		});
	}

	function checkFunction() {
		var myTitle = $('#myTitle').val();
		var myContent = $('#myContent').val();

		if (myTitle == null || myTitle == '') {
			autoClosingAlert('#noTitle', 2000);
			return false;
		} else {
			$.ajax({
				type : "POST",
				url : "./doWrite",
				async : false,
				data : {
					myTitle : encodeURIComponent(myTitle),
					myContent : encodeURIComponent(myContent)
				},
				success : function(result) {
					if (result == -1) {
						alert('failed to write!');
						history.back()

					} else if (result == 1) {
						var url = "bbs";
						window.location.replace(url);
					}
				}
			});
			return true;
		}
	}
	function autoClosingAlert(selector, delay) {
		var alert = $(selector).alert();
		alert.show();

		window.setTimeout(function() {
			alert.hide()
		}, delay);
	}
</script>
<title>WSCHOI</title>
<link rel="icon" href="data:;base64,iVBORw0KGgo=">
</head>
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
		if (userID == "") {
			alert('login first!');
			location.href = 'login';
		}
	</script>
	<nav class="navbar navbar-inverse navbar-fixed-top" id="myNav">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false" id="myCollapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="main" id="myFont">WSCHOI BLOG</a>
			</div>
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="main" id="myFont5">메인</a></li>
					<li id="myActive"><a href="bbs" id="myFont1">게시판</a></li>
					<li><a href="menu" id="myFont6">학식메뉴</a></li>
					<li><a href="guestBook" id="myFont7">방명록</a></li>
				</ul>
				<c:choose>
					<c:when test="${userID eq null}">
						<ul class="nav navbar-nav navbar-right">
							<li class="dropdown"><a href="#"
								class="dropdown-toggle menu-dropicon" data-toggle="dropdown"
								role="button" aria-haspopup="true" aria-expaneded="false"
								id="myDropdown"> <i id="settingIcon"
									class="fa fa-cog fa-spin fa-fw menu-icon"></i><span
									class="caret"></span>
							</a>
								<ul class="dropdown-menu" id="myDropdown2">
									<li><a href="login" id="myFont2">로그인</a></li>
									<li><a href="join" id="myFont3">회원가입</a></li>
									<li><div class="custom-switch custom-switch-label-onoff">
											<input class="custom-switch-input" id="example_2"
												type="checkbox" onclick="darkmodeHandler();"> <label
												class="custom-switch-btn" for="example_2"></label>
										</div></li>
								</ul></li>
						</ul>
					</c:when>
					<c:otherwise>
						<ul class="nav navbar-nav navbar-right">
							<li class="dropdown"><a href="#"
								class="dropdown-toggle menu-dropicon" data-toggle="dropdown"
								role="button" aria-haspopup="true" aria-expaneded="false"
								id="myDropdown"> <i id="settingIcon"
									class="fa fa-cog fa-spin fa-fw menu-icon"></i><span
									class="caret"></span>
							</a>
								<ul class="dropdown-menu" id="myDropdown2">
									<li><a href="doLogout" id="myFont2">로그아웃</a></li>
									<li><div class="custom-switch custom-switch-label-onoff">
											<input class="custom-switch-input" id="example_2"
												type="checkbox" onclick="darkmodeHandler();"> <label
												class="custom-switch-btn" for="example_2"></label>
										</div></li>
								</ul></li>
						</ul>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</nav>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<div class="container">
		<div class="row">
			<form method="post" action="doWrite">
				<table class="table table-striped" style="border: 1px solid #dddddd">
					<thead>
						<tr>
							<th colspan="2"
								style="background-color: #eeeeee; text-align: center"
								id="myJumbo">게시판 글쓰기 양식</th>
						</tr>
					</thead>
					<tbody id="myFont10">
						<tr id="myFont11">
							<td><input type="text" class="form-control"
								placeholder="글 제목" name="bbsTitle" maxlength="50" id="myTitle"></td>
						</tr>
						<tr>
							<td><textarea class="form-control" placeholder="글 내용"
									name="bbsContent" maxlength="2048" style="height: 350px;"
									id="myContent"></textarea></td>
						</tr>
					</tbody>
				</table>
				<button type="button" class="btn btn-primary pull-right"
					onclick="checkFunction();">글쓰기</button>
			</form>
		</div>
		</br>
		<div class="alert alert-warning" id="noTitle" style="display: none;">
			<strong>제목을 입력하세요.</strong>
		</div>
		<div class="alert alert-warning" id="noContent" style="display: none;">
			<strong>내용을 입력하세요.</strong>
		</div>
		<div class="alert alert-warning" id="dbError" style="display: none;">
			<strong>데이터 베이스 오류가 발생했습니다.</strong>
		</div>
	</div>
	<script>
		$("#myDropdown2").on('click', function(e) {
			e.stopPropagation();
		});
		$(document).ready(function() {
			$('#summernote').summernote({ // summernote를 사용하기 위한 선언
				lang : 'ko-KR',
				height : '300px',
				callbacks : { // 콜백을 사용
					// 이미지를 업로드할 경우 이벤트를 발생
					onImageUpload : function(files, editor, welEditable) {
						sendFile(files[0], this);
					}
				}
			});
		});
	</script>
</body>
</html>