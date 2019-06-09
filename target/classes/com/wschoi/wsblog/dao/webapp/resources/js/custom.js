function darkmodeHandler(){
	$.ajax({
		type: "POST",
		url: "./setDarkMode",
		
		success: function(result){
			if(result == 1){
				setBrightMode();
			} else if(result == -1){
				setDarkMode();
			}
		}
	});
}
function setBrightMode(){
	
	if (document.getElementById("myNav") !=null)
	{
	document.getElementById("myNav").style.backgroundColor = "white";
	}
	if(document.getElementById("myActive") != null)
	{
	document.getElementById("myActive").style.backgroundColor = "#DEDEDE";
	}
	if(document.getElementById("myDropdown") != null)
	{
	document.getElementById("myDropdown").style.backgroundColor = "white";
	}
	if(document.getElementById("myDropdown2") != null)
	{
	document.getElementById("myDropdown2").style.backgroundColor = "white";
	}
	if(document.getElementById("settingIcon") != null)
	{
	document.getElementById("settingIcon").style.color = "#292b2c";
	}
	if(document.getElementById("myJumbo") != null)
	{
	document.getElementById("myJumbo").style.color = "black";
	document.getElementById("myJumbo").style.backgroundColor = "#DEDEDE";
	}
	if(document.getElementById("chatList") != null)
	{
	document.getElementById("chatList").style.color = "black";
	document.getElementById("chatList").style.backgroundColor = "#DEDEDE";
	}
	if(document.getElementById("myCollapse") != null)
	{
	document.getElementById("myCollapse").style.backgroundColor = "#DEDEDE";
	document.getElementById("myCollapse").style.border = "#DEDEDE";
	}
	if(document.getElementById("myFont") != null)
	{
	document.getElementById("myFont").style.color = "black";
	document.getElementById("myFont").style.backgroundColor = "#DEDEDE";
	}
	if(document.getElementById("myFont1") != null)
	{
	document.getElementById("myFont1").style.color = "black";
	}
	if(document.getElementById("myFont2") != null)
	{
	document.getElementById("myFont2").style.color = "#292b2c";
	document.getElementById("myFont2").style.backgroundColor ="white";
	}
	if (document.getElementById("myFont3") !=null)
	{
	document.getElementById("myFont3").style.color = "#292b2c";
	document.getElementById("myFont3").style.backgroundColor = "white";
	}
	if(document.getElementById("myFont4") != null)
	{
	document.getElementById("myFont4").style.color = "#292b2c";
	}
	if(document.getElementById("myFont5") != null)
	{
	document.getElementById("myFont5").style.color = "#292b2c";
	}
	if(document.getElementById("myFont6") != null)
	{
	document.getElementById("myFont6").style.color = "#292b2c";
	}
	if(document.getElementById("myFont7") != null)
	{
	document.getElementById("myFont7").style.color = "#292b2c";
	}
	if(document.getElementById("myFont8") != null)
	{
	document.getElementById("myFont8").style.color = "black";
	document.getElementById("myFont8").style.backgroundColor = "#DEDEDE";
	}
	if(document.getElementById("myContent") != null)
	{
		var oldlink = document.getElementsByTagName("link").item(7);

	    var newlink = document.createElement("link");
	    newlink.setAttribute("rel", "stylesheet");
	    newlink.setAttribute("type", "text/css");
	    newlink.setAttribute("href", 'resources/css/flaty-summernote.css');

	    document.getElementsByTagName("head").item(0).replaceChild(newlink, oldlink);
	    $(document).ready(function() {
			  $('#summernote').summernote({
				    lang: 'ko-KR',
				    height: '300px',
				    callbacks: { // 콜백을 사용
	                    // 이미지를 업로드할 경우 이벤트를 발생
					    onImageUpload: function(files, editor, welEditable) {
						    sendFile(files[0], this);
						}
					}
				});
			});
	}
	if(document.getElementById("myFont9") != null)
	{
	document.getElementById("myFont9").style.color = "black";
	document.getElementById("myFont9").style.backgroundColor = "#DEDEDE";
	}
	if(document.getElementById("myTitle") != null)
	{
	document.getElementById("myTitle").style.color = "black";
	document.getElementById("myTitle").style.backgroundColor = "#DEDEDE";
	}
	if(document.getElementById("myFont10") != null)
	{
	document.getElementById("myFont10").style.backgroundColor = "white";
	}
	if(document.getElementById("myFont11") != null)
	{
	document.getElementById("myFont11").style.backgroundColor = "white";
	}
	if(document.getElementById("myFont12") != null)
	{
	document.getElementById("myFont12").style.backgroundColor = "#e0e7e8";
	}
	document.body.style.backgroundColor = "white";
	}
function setDarkMode(){
	if (document.getElementById("myNav") !=null)
	{
	document.getElementById("myNav").style.backgroundColor = "#292b2c";
	}
	if(document.getElementById("myActive") != null)
	{
	document.getElementById("myActive").style.backgroundColor = "#777777";
	}
	if(document.getElementById("myDropdown") != null)
	{
	document.getElementById("myDropdown").style.backgroundColor = "#292b2c";
	}
	if(document.getElementById("myDropdown2") != null)
	{
	document.getElementById("myDropdown2").style.backgroundColor = "#292b2c";
	}
	if(document.getElementById("settingIcon") != null)
	{
	document.getElementById("settingIcon").style.color = "white";
	}
	if(document.getElementById("myJumbo") != null)
	{
	document.getElementById("myJumbo").style.color = "white";
	document.getElementById("myJumbo").style.backgroundColor = "#292b2c";
	}
	if(document.getElementById("chatList") != null)
	{
	document.getElementById("chatList").style.color = "white";
	document.getElementById("chatList").style.backgroundColor = "#292b2c";
	}
	if(document.getElementById("myCollapse") != null)
	{
	document.getElementById("myCollapse").style.backgroundColor = "black";
	document.getElementById("myCollapse").style.backgroundColor = "black";
	}
	if(document.getElementById("myFont") != null)
	{
	document.getElementById("myFont").style.color = "white";
	document.getElementById("myFont").style.backgroundColor = "#777777";
	}
	if(document.getElementById("myFont1") != null)
	{
	document.getElementById("myFont1").style.color = "white";
	}
	if(document.getElementById("myFont2") != null)
	{
	document.getElementById("myFont2").style.color = "white";
	document.getElementById("myFont2").style.backgroundColor = "#292b2c";
	}
	if (document.getElementById("myFont3") !=null)
	{ 
	document.getElementById("myFont3").style.color = "white";
	document.getElementById("myFont3").style.backgroundColor = "#292b2c";
	}
	if(document.getElementById("myFont4") != null)
	{
	document.getElementById("myFont4").style.color = "white";
	}
	if(document.getElementById("myFont5") != null)
	{
	document.getElementById("myFont5").style.color = "white";
	}
	if(document.getElementById("myFont6") != null)
	{
	document.getElementById("myFont6").style.color = "white";
	}
	if(document.getElementById("myFont7") != null)
	{
	document.getElementById("myFont7").style.color = "white";
	}
	if(document.getElementById("myFont8") != null)
	{
	document.getElementById("myFont8").style.color = "white";
	document.getElementById("myFont8").style.backgroundColor = "#292b2c";
	}
	if(document.getElementById("myContent") != null)
	{
		var oldlink = document.getElementsByTagName("link").item(7);

	    var newlink = document.createElement("link");
	    newlink.setAttribute("rel", "stylesheet");
	    newlink.setAttribute("type", "text/css");
	    newlink.setAttribute("href", 'css/darkly-summernote.css');

	    document.getElementsByTagName("head").item(0).replaceChild(newlink, oldlink);
	    
	    $(document).ready(function() {
			  $('#summernote').summernote({
				    lang: 'ko-KR',
				    height: '300px',
				    callbacks: { // 콜백을 사용
	                    // 이미지를 업로드할 경우 이벤트를 발생
					    onImageUpload: function(files, editor, welEditable) {
						    sendFile(files[0], this);
						}
					}
				});
			});
	}
	if(document.getElementById("myTitle") != null)
	{
	document.getElementById("myTitle").style.color = "white";
	document.getElementById("myTitle").style.backgroundColor = "#292b2c";
	}
	if(document.getElementById("myFont9") != null)
	{
	document.getElementById("myFont9").style.color = "white";
	document.getElementById("myFont9").style.backgroundColor = "#292b2c";
	}
	if(document.getElementById("myFont10") != null)
	{
	document.getElementById("myFont10").style.backgroundColor = "#DEDEDE";
	}
	if(document.getElementById("myFont11") != null)
	{
	document.getElementById("myFont11").style.backgroundColor = "#DEDEDE";
	}
	if(document.getElementById("myFont12") != null)
	{
	document.getElementById("myFont12").style.backgroundColor = "#DEDEDE";
	}
	document.body.style.backgroundColor = "#777777";
}

if (document.location.protocol == 'http:') {
     document.location.href = document.location.href.replace('http:', 'https:');
}

