function register(){
	var username = $("#username").val();
	var password = $("#password").val();
	var password2 = $("#password2").val();
	if(username == ''){
		alert("用户名不能为空");
		return;
	} else if(password == ''){
		alert("请输入密码");
		return;
	} else if(password2 == ''){
		alert("请输入确认密码");
		return;
	}
	if(password != password2){
		alert("两次密码不一致");
		return;
	}
	$.ajax({
		type : "POST",
		url : "http://127.0.0.1:8080/community/register.htm",
		data : {"username":username,"password":password},
		success : function(msg) {
			window.location.href = "http://127.0.0.1:8080/community/login.htm";
		}
	});
}

function login(){
	var username = $("#username").val();
	var password = $("#password").val();
	if(username == ''){
		alert("用户名不能为空");
		return;
	} else if(password == ''){
		alert("请输入密码");
		return;
	}
	$.ajax({
		type : "POST",
		url : "http://127.0.0.1:8080/community/logon.htm",
		data : {"username":username,"password":password},
		success : function(msg) {
			window.location.href = "http://127.0.0.1:8080/community/index.htm";
		}
	});
}

function checkname(){
	var username = $("#username").val();
	if(username == ''){
		return;
	}
	$.ajax({
		type : "POST",
		url : "http://127.0.0.1:8080/community/checkname.htm",
		data : {"username":username},
		success : function(msg) {
			if(msg == "exist"){
				alert("用户名已经存在");
			}
		}
	});
}