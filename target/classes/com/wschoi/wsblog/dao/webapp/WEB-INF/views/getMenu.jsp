<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width", initial-scale="1">
<link rel="stylesheet" href="resources/css/jquery-ui.min.css">
<link rel="stylesheet" href="resources/css/bootstrap.css">
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/custom.css">
<link rel="stylesheet" href="resources/css/datepicker3.css">
<link rel="stylesheet" href="resources/css/bootstrap-select.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@7.32.2/dist/sweetalert2.min.css">
<script src="resources/js/jquery.js"></script>
<script src="resources/js/jquery.min.js"></script>
<script src="resources/js/moment.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/bootstrap-select.js"></script>
<script src="resources/js/bootstrap-datepicker.js" charset="utf-8"></script>
<script src="resources/js/bootstrap-datepicker.kr.js"></script>
<script src="resources/js/custom.js"></script>
<title>WSCHOI</title>
<style type="text/css">
	.curDate{
  width:220px;
  height:30px;
}
</style>
<script type="text/javascript">
function checkFunction(){
	var myCafe = $('#myCafe').val();
	var myDate = $('#myDate').val();
	
	if(myCafe == null || myCafe == ''){
		autoClosingAlert('#noCafe', 2000);
		return false;
	}
	else if((myCafe != null && myCafe != '') && (myDate == null || myDate == '')){
		autoClosingAlert('#noDate', 2000);	
		return false;
	}
	else {
		return true;
	}
}
function autoClosingAlert(selector, delay){
	var alert = $(selector).alert();
	alert.show();
	window.setTimeout(function(){ alert.hide()}, delay);
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
				<li id="myActive"><a href="menu" id="myFont6">학식메뉴</a></li>
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
	<div class="col-lg-4"></div>
	<div class="col-lg-4">
	<form action="myMenu" style="text-align:center" method="post" onsubmit="return checkFunction();">
		</br>
			<h1>학식 메뉴 조회</h1>
		</br></br>
			<select class="selectpicker" name="cafe" id="myCafe">
			<option value="" disabled selected>건물 선택</option>
			<option value="h101인문관식당">인문관식당</option>
			<option value="h102교수회관식당">교수회관식당</option>
			<option value="h103스카이라운지">스카이라운지</option>
			<option value="h201국제사회교육원식당">국제사회교육원식당</option>
			<option value="h202후생관 교직원식당">후생관 교직원식당</option>
			<option value="h203후생관 학생식당">후생관 학생식당</option>
			<option value="h204어문관">어문관</option>
			<option value="h205HufsDorm 식당">HufsDorm 식당</option>
		</select>
		</br></br>
            <input class="curDate" placeholder="   날짜 선택" name="date" id="myDate">
            <script type='text/javascript'>
			    $(function(){
			        $(".curDate").datepicker({
			            format: 'yyyy/mm/dd',
			            autoclose: true
			        });
			    });
			</script>
        </br></br>
		<input class="btn btn-primary" type="submit" value="조회하기">
	</form>
	</br>
	<div class="alert alert-warning" id="noCafe" style="display: none;">
			<strong>건물을 고르세요.</strong>
		</div>
		<div class="alert alert-warning" id="noDate" style="display: none;">
			<strong>날짜를 선택하세요.</strong>
		</div>
	</div>
	</div>
	${menu}
	<script>
		$("#myDropdown2").on('click', function (e) {
			  e.stopPropagation();
			});
	</script>	
</body>
</html>