function submit(){
	var title = $("#answertitle").val();
	var content = $("#answercontent").val();
	if(title == ''){
		alert("标题不能为空");
		return;
	} else if(content == ''){
		alert("内容不能为空");
		return;
	}
	$.ajax({
		type : "POST",
		url : "http://127.0.0.1:8080/community/publish.htm",
		data : {"title":title,"content":content},
		success : function(msg) {
			alert(msg);
			if("succeed".equal(msg)){
				window.location.href = "http://127.0.0.1:8080/community/faq.htm";
			}
			//
		}
	});
}


function answer(param){
	var t1 = $(param).parent();
	var answers = $(param).parent().find("#answer").find("textarea").val();
	var questionid = $(param).parent().find("input").val();
	if(answers == ''){
		alert("回复不能为空");
		return;
	}
	$.ajax({
		type : "POST",
		url : "http://127.0.0.1:8080/community/answer.htm",
		data : {"answers":answers,"questionid":questionid},
		success : function(msg) {
			alert(msg);
			if("succeed".equal(msg)){
				window.location.href = "http://127.0.0.1:8080/community/faq.htm";
			}
			//
		}
	});
}