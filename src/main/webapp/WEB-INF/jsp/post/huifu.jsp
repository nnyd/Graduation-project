<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<!doctype html>
<html lang="zh" class="no-js">
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
        <div style="margin: 5px auto;height: 100px; width: 800px">
            <textarea id="TextArea1" cols="20" rows="1" name="content" class="ckeditor"></textarea>

        </div>
	    <div style="float:right;margin: 60px auto">
	        <input type="button" class="btn btn-primary" style="width: 60px;" value="回复" id="hf"></input>
	        <input type="button" class="btn btn-primary" style="width: 60px;" value="返回" onclick="history.back(-1);"></input>
	    </div>
</form>
</div>
<script type="text/javascript">
$(document).on('click','#hf',function(){
	//alert();CKEDITOR.instances.TextArea1.getData()
	if("${USER_LOGIN_KEY==null||USER_LOGIN_KEY.role==null}"=="true"){
		window.location.href="/toLogin";
		return ;
	}//postId,accId,commentContent,commentRealContent,pid,lou
	if(CKEDITOR.instances.TextArea1.getData()==""){
		alert("请填写回复内容");
	}else{
		$.ajax({
	          type:"POST",
	          url:"/bbs/createComment",
	          data:{"postId":"${comment.postId}","pid":"${comment.pid}","commentRealContent":CKEDITOR.instances.TextArea1.getData()},
	          async: false,
	          success:function(data){
	          	console.log(data);
	          	if(data.code=="100")
	          	{
	          		window.location.href="/bbs/getPostDetail?postId=${comment.postId}&pageNum=99999";
	          	}else{
	          		alert("评论失败");
	          	}
	          },
	          error:function(e){
	              alert("对不起，服务器繁忙,请稍后重试！");                   
	          }
	      });
	}
});
</script>
</body>
</html>