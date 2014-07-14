function setprofiler() {
	var username = $("#username").val();
	var firstname = $("#firstname").val();
	var sex = $("#sex").val();
	var email = $("#email").val();
	var avatar = $("#avatar").val();
	var address = $("#address").val();
	var telephone = $("#telephone").val();
	var community = $("#community").val();
	if(firstname==null || firstname==''){
		alert("姓名不能为空");
		return;
	}
	if(sex == null || sex == ''){
		alert("性别不能为空");
		return;		
	}
	if(email == null || email == ''){
		alert("邮箱不能为空");
		return;		
	}
	if(avatar == null || avatar == ''){
		alert("头像不能为空");
		return;
	}
	if(address == null || address == ''){
		alert("地址不能为空");
		return;
	}
	if(telephone == null || telephone == ''){
		alert("联系方式不能为空");
		return;
	}
	if(community == null || community == ''){
		alert("社区不能为空");
		return;
	}
	$.ajax({
		type : "POST",
		url : "http://127.0.0.1:8080/community/update.htm",
		data : {
			"username" : username,
			"firstname" : firstname,
			"sex" : sex,
			"email" : email,
			"avatar" : avatar,
			"address" : address,
			"telephone" : telephone,
			"community" : community
		},
		success : function(msg) {
			window.location.href = "http://127.0.0.1:8080/community/account.htm";
		}
	});
}

function changepwd() {
	var username = $("#username").val();
	var password1 = $("#password1").val();
	var password2 = $("#password2").val();
	if(password1 == ''){
		alert("请输入密码");
		return;
	} else if(password2 == ''){
		alert("请输入确认密码");
		return;
	} else if(password1 != password2){
		alert("两次密码不一致");
		return;
	}
	$.ajax({
		type : "POST",
		url : "http://127.0.0.1:8080/community/change_password.htm",
		data : {
			"username" : username,
			"password" : password1
		},
		success : function(msg) {
			window.location.href = "http://127.0.0.1:8080/community/logout.htm";
		}
	});
}