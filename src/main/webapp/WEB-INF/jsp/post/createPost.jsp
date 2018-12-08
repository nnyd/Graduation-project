<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>技术论坛</title>
    <link href="/static/css/post-detail.css" rel="stylesheet">
    <script type="text/javascript" src="/static/ckeditor/ckeditor.js"></script>
	
	<link rel="/static/stylesheet" type="text/css" href="css/zzsc-demo.css">
    <link href="/static/css/index.css" rel="stylesheet">
	<link href="/static/css/bootstrap.min.css" rel="stylesheet">
	<link href="/static/css/titlebar.css" rel="stylesheet">
	<script src="/static/js/jquery.min.js"></script>
	<script src="/static/js/ajaxfileupload.js"></script>
    <script src="/static/js/bootstrap.min.js"></script>
</head>
<body>
<%@ include file="/nav.jsp" %>
<div style="height: 200px;margin: 70px auto; width: 800px;">
<form action="#" method="post">
		<div style="margin: 5px auto;height: 40px; width: 800px">
		请选择板块: 主板块：<select id="model1"></select>&nbsp;子板块：<select id="model2"></select>
		</div>
		<div style="margin: 5px auto;height: 40px; width: 800px">
			标题：<input type="text" id="postTitle" style="width: 550px">
		</div>
        <div style="margin: 5px auto;height: 100px; width: 800px">
        详细内容：
            <textarea id="TextArea1" cols="20" rows="1" name="content" class="ckeditor"></textarea>
        </div>
        <div style="margin: 80px auto;height: 40px; width: 800px">
        	 添加文件:<input type="file" id="file" name="file">
        </div>
	    <div style="float:right;">
	        <input type="button" class="btn btn-primary" style="width: 60px;" value="发布" id="hf"></input>
	        <input type="button" class="btn btn-primary" style="width: 60px;" value="返回" onclick="history.back(-1);"></input>
	    </div>
</form>
</div>
<script type="text/javascript">
var fileId="";
var allModel;
$(document).on('click','#hf',function(){
	$.ajax({
        type:"POST",
        url:"/bbs/createBbsPost",
        data:{"modelId":$('#model2').val(),"postTitle":$('#postTitle').val(),"postDetil":CKEDITOR.instances.TextArea1.getData(),"fileId":fileId==""?-1:fileId},
        async: false,
        success:function(data){
        	console.log(data);
        	if(data.code=="100")
        	{
        		window.location.href="/bbs/getPostDetail?postId="+data.extend.data.postId+"&pageNum=1";
        	}else{
        		alert('操作失败');
        	}
        },
        error:function(e){
            alert("对不起，服务器繁忙,请稍后重试！");                   
        }
    });
})
$(document).on('change','#file',function(){
	if($('#file').val()!=''){
		$.ajaxFileUpload({
	        url : '/upload',
	        secureuri : false,
	        fileElementId : 'file',
	        dataType : 'json',//此时指定的是后台需要返回json字符串,前端自己解析,可以注释掉.后台直接返回map
	        success : function(data, status) {
	           if(data!='false'){
	        	   var arr = data.split('***');
	     		   fileId=arr[0];
	     		   //$("#head").attr('src',arr[1]);
	     			console.log(data);
	           }else{alert('上传失败');}
	        },
	        error : function(data, status, e) {
	            //这里处理的是网络异常，返回参数解析异常，DOM操作异常  
	            alert("上传发生异常");
	        }
   		})
	}else{
		fileId="";
	}
});
$(document).on('change','#model1',function(){
	$('#model2').empty();
	$(allModel).each(function(index,item) {
		if($('#model1').val()==this.modelId){
			$(this.childList).each(function(index,item) {
				$('#model2').append('<option value="'+this.modelId+'">'+this.modelName+'</option>');
			});
		}
	});
});
window.onload=function(){
	$.ajax({
        type:"POST",
        url:"/bbs/getAllModel",
        data:{},
        async: false,
        success:function(data){
        	allModel=data.extend.data;
        	console.log(data);
        	if(data.code=="100")
        	{
        		var a=0;
        		$(data.extend.data).each(function(index,item) {
        			$('#model1').append('<option value="'+this.modelId+'">'+this.modelName+'</option>');
        		});
   				$(allModel[0].childList).each(function(index,item) {
   					$('#model2').append('<option value="'+this.modelId+'">'+this.modelName+'</option>');
   				});
        	}else{
        		alert('操作失败');
        	}
        },
        error:function(e){
            alert("对不起，服务器繁忙,请稍后重试！");                   
        }
    });
}
</script>
</body>
</html>