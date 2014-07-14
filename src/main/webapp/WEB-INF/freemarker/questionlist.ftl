<#if questions??>
<#list questions as question>
<div class="widget">
	
	<div class="widget-header">
		<h3>${question_index+1} : ${question.description}</h3>
	</div> <!-- /widget-header -->
										
	<div class="widget-content">
		<#if answermap??>
		<#list answermap?keys as key>
			<#if key=question.questionid>
			<#assign item = answermap[key]>   
				<#list item as answer>
				<#if answer_index != 0>
				<hr/>
				</#if>
				<p>${answer.username} : ${answer.content}</p>
				</#list>
			<#else>
			暂无回复
			</#if>
		</#list>
		</#if>
	</div> <!-- /widget-content -->
	
</div> <!-- /widget -->
</#list>
</#if>