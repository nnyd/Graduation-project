<%@page import="com.cgn.bbs.beans.Account"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="zh" class="no-js">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>技术论坛</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="/static/css/bootstrap.min.css" rel="stylesheet">
	<link href="/static/css/login.css" rel="stylesheet">
	<script type="text/javascript" src="/static/js/jquery-1.9.1.min.js"></script>
</head>

<body>
   <%
	   if(session.getAttribute("USER_LOGIN_KEY")!=null){
		   if(((Account) session.getAttribute("USER_LOGIN_KEY")).getAccId()!=null&&((Account) session.getAttribute("USER_LOGIN_KEY")).getAccId()!=0)
		    	response.sendRedirect("/");
	   }
   %>
 <div class="login">

    <form id="form1" role="form" action="#" method="post">
            <!--<label for="name">用户名:</label>-->
            <input id="username" required type="text" class="form-control" name="username" style="height: 55px; margin-top: 30px;"
                   placeholder="请输入用户名"><div id="error1"></div>
            <!--<label for="name"></label>-->
            <input id="password" type="password" required class="form-control" name="password" style="height: 55px;margin-top: 30px;"
                   placeholder="请输入密码"><div id="error2"></div>
        <div style="height: 100px;width: 100%;margin-top: 30px;">
            <div style="float:left;width: 50%;padding: 20px;">
                <input type="button" class="btn btn-primary" value="登陆"
                   style="margin:auto;width: 80%;height: 50px;padding: 13px;" id="dl"></input>
            </div>
            <div style="float:right;width: 50%;padding: 20px;">
                <a href="/toRegist" type="button" class="btn btn-primary"
                   style="margin:auto;width: 80%;height: 50px;padding: 13px;">注册</a>
            </div>

        </div>

    </form>

</div>
<div class="bottom" style="position:absolute;bottom:0px;margin-top: 20px;background-color: rgba(0,0,0,0.8);width:100%;height: 100px;color: darkgray">
    <div style="width: 400px;padding-top: 35px;padding-left:40px;padding-right: 40px;margin:auto;">
        <div>
            友情链接：
            <a href="https://github.com/zhangjianhao" style="color: darkgray">&nbsp;github&nbsp;|&nbsp;</a>
            <a href="http://www.csdn.net/" style="color: darkgray">csdn&nbsp;|&nbsp;</a>
            <a href="http://www.oschina.net/" style="color: darkgray">开源中国&nbsp;|&nbsp;</a>
            <a href="http://stackoverflow.com/" style="color: darkgray">stackflow</a><br>
            小组成员：楠
        </div>

    </div>
	</body>
  <script type="text/javascript">
  	$(document).on('click',"#dl",function(){
  		if($("#username").val()==""){
  			$("#error1").html("<font color='red'>用户名不能为空</font>");
  			ok1=false;
  		}else{$("#error1").empty();ok1=true;}
  		if($("#password").val().length<6||$("#password").val().length>16){
  			$("#error2").html("<font color='red'>密码长度为6到16位</font>");
  			ok2=false;
  		}else{$("#error2").empty();ok2=true;}
  		if(ok1&&ok2){
	  		$.ajax({
	            type:"POST",
	            url:"/login",
	            data:{"accName":$("#username").val(),"accPassword":$("#password").val(),"type":"2"},
	            success:function(data){
	            	console.log(data);
	            	if(data.code=="100")
	            	{
	            		window.location.href=(data.extend.url==undefined||data.extend.url==null)?"/index":data.extend.url;
	            	}else{
	            		$("#error2").html("<font color='red'>"+data.extend.info+"</font>");
	            	}
	            },
	            error:function(e){
	                alert("对不起，服务器繁忙,请稍后重试！");                   
	            }
	        });
  		}
  	})
  </script>
</html>