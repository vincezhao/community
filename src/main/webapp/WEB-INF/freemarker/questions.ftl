<ol class="faq-list">
<#if questions ??>
<#list questions as question>	
	<li>
			<h4>${question.authorname} : ${question.description}</h4>
			<p>${question.content} <a href="#">[答复]</a></p>
			<p id="answer">
				<textarea style="width:99%;height:50px;" cols="20"></textarea>
				<div id="button" class="btn btn-warning btn-large" onclick="answer(this)">提交</div>
			</p>
			<input type="hidden" value="${question.questionid}"/>
	</li>
</#list>
</#if>
</ol>