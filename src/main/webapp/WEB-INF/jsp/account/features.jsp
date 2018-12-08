<%@page import="com.cgn.bbs.beans.Account"%>
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
	<%
	   if(session.getAttribute("USER_LOGIN_KEY")!=null){
		   if(((Account) session.getAttribute("USER_LOGIN_KEY")).getAccId()==null||((Account) session.getAttribute("USER_LOGIN_KEY")).getAccId()==0)
		    	response.sendRedirect("/toLogin");
	   }else{
		   response.sendRedirect("/toLogin");
	   }
	%>
	<style>
	#box{
			width:44px;
			height:44px;
			line-height: 44px;
			text-align: center;
			position:absolute;
			left:20%;
			top:20%;
			background: url(/static/img/investjd.png);
		}
	</style>
	<script type="text/javascript">
	//console.log($("li.active").children("a").attr("state"));获取状态码
	//var object = document.getElementById('box')
	var allModel;
	var fileId="";
	$(document).ready(function(){
			$("#box").hide();
			var state=$("ul.nav li.active a").attr("state");
			switch (state){
			case ("1"):case ("4"):toChangeUserInfo();break;case ("8"):getAllModel();break;default :alert("页面加载错误");}
	});
		$(document).on("click",".nav-stacked li a",function(){
			var stateOld=$("li.active").children("a").attr("state");
			var state=$(this).attr("state");
			if(state==stateOld)
				return ;
			$("li.active").attr("class","");
			$(this).parent().attr("class","active");
			changePage(state,1);
		});
		function changePage(state,pageNum){
			switch (state){
			case ("1"):
			case ("4"):toChangeUserInfo();break;
			case ("2"):
			case ("6"):getMyPost(pageNum);break;
			case ("3"):
			case ("7"):getMyExamines(pageNum);break;
			case ("5"):addErModel();break;
			case ("8"):getAllModel();break;
			case ("9"):getAllPost(pageNum);break;
			case ("10"):getAllUser(pageNum);break;
			case ("11"):getAllExamines(pageNum);break;
			case ("12"):getAllIdea(pageNum);break;
			case ("13"):getAllReport(pageNum);break;
			case ("14"):getAllBadRecord(pageNum);break;
			case ("15"):getAllLog(pageNum);break;
			default :alert("页面错误");
			}
		}
		/* <li role="presentation" class="active"><a href="javascript:void(0)" state="4">资料修改</a></li>
        <li role="presentation" ><a href="javascript:void(0)" state="5">申请板块</a></li>
        <li role="presentation" ><a href="javascript:void(0)" state="6">我的发帖</a></li>
        <li role="presentation"><a href="javascript:void(0)" state="7">申请记录</a></li>
		<li role="presentation" class="active"><a href="javascript:void(0)" state="8">板块管理</a></li>
        <li role="presentation" ><a href="javascript:void(0)" state="9">帖子管理</a></li>
        <li role="presentation" ><a href="javascript:void(0)" state="10">用户管理</a></li>
        <li role="presentation"><a href="javascript:void(0)" state="11">申请管理</a></li>
        <li role="presentation"><a href="javascript:void(0)" state="12">意见建议</a></li>
        <li role="presentation"><a href="javascript:void(0)" state="13">举报查看</a></li>
        <li role="presentation"><a href="javascript:void(0)" state="14">用户不良记录</a></li>
        <li role="presentation"><a href="javascript:void(0)" state="15">日志查看</a></li> */
        function getAllLog(pageNum){
        	$.ajax({
	            type:"POST",
	            url:"/bbs/getAllLog",
	            data:{"pageNum":pageNum},
	            async: false,
	            success:function(data){
	            	console.log(data);
	            	if(data.code=="100")
	            	{
	            		$("#allContent").empty();
	            		$("#allContent").append('<ul class="list-group">'+
			                '<a class="list-group-item active">'+
			                 '  日志查看'+
			                '</a>'+
			                '<div class="list-group-item"><table class="table table-hover">'+
			                '<thead><tr><td>日志内容</td><td>记录时间</td></tr></thead><tbody id="postItem"></tbody></table>');
	            		$("#allContent").append('</div></ul>');
	            		$(data.extend.data.list).each(function(index,item) { 
	                         var ctime = new Date(this.createTime);
			                $("#postItem").append('<tr>'+
			                		'<td>'+this.logInfo+'</td><td>'+ctime.toLocaleString()+'</td>'+
			                		'</tr>');
	            		});
	            		setPageInfo(data.extend.data);
	            		console.log(data);
	            	}else{
	            		alert('操作失败');
	            		//$("#error2").html("<font color='red'>"+data.extend.info+"</font>");
	            	}
	            },
	            error:function(e){
	                alert("对不起，服务器繁忙,请稍后重试！");                   
	            }
	        });
        }
        function getAllBadRecord(pageNum){
        	$.ajax({
	            type:"POST",
	            url:"/bbs/getAllBadRecord",
	            data:{"pageNum":pageNum},
	            async: false,
	            success:function(data){
	            	console.log(data);
	            	if(data.code=="100")
	            	{
	            		$("#allContent").empty();
	            		$("#allContent").append('<ul class="list-group">'+
			                '<a class="list-group-item active">'+
			                 '  用户不良记录'+
			                '</a>'+
			                '<div class="list-group-item"><table class="table table-hover">'+
			                '<thead><tr><td>不良记录者</td><td>被记录处</td><td>记录内容</td><td>记录时间</td></tr></thead><tbody id="postItem"></tbody></table>');
	            		$("#allContent").append('</div></ul>');
	            		$(data.extend.data.list).each(function(index,item) { 
			                $("#postItem").append('<tr>'+
			                		'<td>'+this.accName+'</td><td>'+this.postTitle+'</td><td>'+this.recordDetail+'</td><td>'+this.recordTime+'</td>'+
			                		'</tr>');
	            		});
	            		setPageInfo(data.extend.data);
	            		console.log(data);
	            	}else{
	            		alert('操作失败');
	            		//$("#error2").html("<font color='red'>"+data.extend.info+"</font>");
	            	}
	            },
	            error:function(e){
	                alert("对不起，服务器繁忙,请稍后重试！");                   
	            }
	        });
        }
        function getAllReport(pageNum){
        	$.ajax({
	            type:"POST",
	            url:"/bbs/getReportInfo",
	            data:{"pageNum":pageNum},
	            async: false,
	            success:function(data){
	            	console.log(data);
	            	if(data.code=="100")
	            	{
	            		$("#allContent").empty();
	            		$("#allContent").append('<ul class="list-group">'+
			                '<a class="list-group-item active">'+
			                 '  举报查看'+
			                '</a>'+
			                '<div class="list-group-item"><table class="table table-hover">'+
			                '<thead><tr><td>举报者</td><td>被举报者</td><td>被举类型</td><td>举报内容</td><td>举报时间</td></tr></thead><tbody id="postItem"></tbody></table>');
	            		$("#allContent").append('</div></ul>');
	            		$(data.extend.data.list).each(function(index,item) { 
			                $("#postItem").append('<tr>'+
			                		'<td>'+this.reportaccName+'</td><td>'+this.accName+'</td><td>'+this.reportType+'</td><td>'+this.reportDetail+'</td><td>'+this.reportTime+'</td>'+
			                		'</tr>');
	            		});
	            		setPageInfo(data.extend.data);
	            		console.log(data);
	            	}else{
	            		alert('操作失败');
	            		//$("#error2").html("<font color='red'>"+data.extend.info+"</font>");
	            	}
	            },
	            error:function(e){
	                alert("对不起，服务器繁忙,请稍后重试！");                   
	            }
	        });
        }
        function getAllIdea(pageNum){
        	$.ajax({
	            type:"POST",
	            url:"/bbs/getIdeaInfo",
	            data:{"pageNum":pageNum},
	            async: false,
	            success:function(data){
	            	console.log(data);
	            	if(data.code=="100")
	            	{
	            		$("#allContent").empty();
	            		$("#allContent").append('<ul class="list-group">'+
			                '<a class="list-group-item active">'+
			                 '  意见建议'+
			                '</a>'+
			                '<div class="list-group-item"><table class="table table-hover">'+
			                '<thead><tr><td>意见内容</td><td>提出者</td><td>提出时间</td></tr></thead><tbody id="postItem"></tbody></table>');
	            		$("#allContent").append('</div></ul>');
	            		$(data.extend.data.list).each(function(index,item) { 
			                $("#postItem").append('<tr>'+
			                		'<td>'+this.ideaContent+'</td><td>'+this.accName+'</td><td>'+this.createTime+'</td>'+
			                		'</tr>');
	            		});
	            		setPageInfo(data.extend.data);
	            		console.log(data);
	            	}else{
	            		alert('操作失败');
	            		//$("#error2").html("<font color='red'>"+data.extend.info+"</font>");
	            	}
	            },
	            error:function(e){
	                alert("对不起，服务器繁忙,请稍后重试！");                   
	            }
	        });
        }
        function getAllExamines(pageNum){
        	$.ajax({
	            type:"POST",
	            url:"/bbs/getAllExamines",
	            data:{"pageNum":pageNum},
	            async: false,
	            success:function(data){
	            	console.log(data);
	            	if(data.code=="100")
	            	{
	            		$("#allContent").empty();
	            		$("#allContent").append('<ul class="list-group">'+
			                '<a class="list-group-item active">'+
			                 '  申请管理'+
			                '</a>'+
			                '<div class="list-group-item"><table class="table table-hover">'+
			                '<thead><tr><td>申请项</td><td>申请内容</td><td>申请人</td><td>申请时间</td><td>操作</td></tr></thead><tbody id="postItem"></tbody></table>');
	            		$("#allContent").append('</div></ul>');
	            		$(data.extend.data.list).each(function(index,item) { 
			                $("#postItem").append('<tr>'+
			                		(this.examineType=='ZD'?'<td>置顶帖</td><td>【'+this.postModel+'】'+this.postTitle+'</td>':(this.examineType=='JH'?'<td>精华帖</td><td>【'+this.postModel+'】'+this.postTitle+'</td>':'<td>板块</td><td>【'+this.modelName+'】'+this.name+'</td>'))+
			                		'<td>['+(this.role=='company'?'公司':'学生')+']'+this.accName+'</td><td>'+this.createTime+'</td><td>'+(this.status=='DSP'?('<a href="javascript:void(0)" examId="'+this.examineId+'" id="examtg" examType="'+this.examineType+'">通过</a>　<a href="javascript:void(0)" examId="'+this.examineId+'" id="exambtg">不通过</a>'):(this.status=='TG'?'已通过':'未通过'))+'</td></tr>');
	            		});
	            		setPageInfo(data.extend.data);
	            		console.log(data);
	            	}else{
	            		alert('操作失败');
	            		//$("#error2").html("<font color='red'>"+data.extend.info+"</font>");
	            	}
	            },
	            error:function(e){
	                alert("对不起，服务器繁忙,请稍后重试！");                   
	            }
	        });
        }
        $(document).on('click','#examtg',function(){
        	if(confirm('确定?')){
        		var examineId=$(this).attr('examId');
        		var examineType=$(this).attr('examType');
        		$.ajax({
    	            type:"POST",
    	            url:"/bbs/updateExamine",
    	            data:{"examineId":examineId,"examineType":examineType,"status":"TG"},
    	            async: false,
    	            success:function(data){
   	            		console.log(data);
   	            		//alert(data.massage);
   	            		if(data.code=="100"){
   	            			getAllExamines(1);
   	            		}else{
   	            			alert('操作失败');
   	            		}
    	            },
    	            error:function(e){
    	                alert("对不起，服务器繁忙,请稍后重试！");                   
    	            }
    	        });
        	}
        });
        $(document).on('click','#exambtg',function(){
        	if(confirm('确定?')){
        		var examineId=$(this).attr('examId');
        		$.ajax({
    	            type:"POST",
    	            url:"/bbs/updateExamine",
    	            data:{"examineId":examineId,"status":"WTG"},
    	            async: false,
    	            success:function(data){
   	            		console.log(data);
   	            		//alert(data.massage);
   	            		if(data.code=="100"){
   	            			getAllExamines(1);
   	            		}else{
   	            			alert('操作失败');
   	            		}
    	            },
    	            error:function(e){
    	                alert("对不起，服务器繁忙,请稍后重试！");                   
    	            }
    	        });
        	}
        });
        function getAllUser(pageNum){
        	$.ajax({
	            type:"POST",
	            url:"/bbs/getAllUser",
	            data:{"pageNum":pageNum},
	            async: false,
	            success:function(data){
	            	console.log(data);
	            	if(data.code=="100")
	            	{
	            		$("#allContent").empty();
	            		$("#allContent").append('<ul class="list-group">'+
			                '<a class="list-group-item active">'+
			                 '  用户管理'+
			                '</a>'+
			                '<div class="list-group-item"><table class="table table-hover">'+
			                '<thead><tr><td>用户名</td><td>邮箱</td><td>注册时间</td><td>类型</td><td>操作</td></tr></thead><tbody id="postItem"></tbody></table>');
	            		$("#allContent").append('</div></ul>');
	            		$(data.extend.data.list).each(function(index,item) { 
	            			var fu=this.modelName;
			                $("#postItem").append('<tr>'+
			                		'<td>'+this.accName+'</td><td>'+this.email+'</td><td>'+this.createTime+'</td><td>'+(this.role=='student'?'学生':'公司')+'</td><td><a href="javascript:void(0)" id="dongjie" accId="'+this.accId+'">'+(this.isInUse?'冻结用户':'恢复用户')+'</a></td>'+
			                		'</tr>');
	            		});
	            		setPageInfo(data.extend.data);
	            		console.log(data);
	            	}else{
	            		alert('操作失败');
	            		//$("#error2").html("<font color='red'>"+data.extend.info+"</font>");
	            	}
	            },
	            error:function(e){
	                alert("对不起，服务器繁忙,请稍后重试！");                   
	            }
	        });
        }
        $(document).on('click','#dongjie',function(){
        	if(confirm('确定?')){
        		var accId=$(this).attr('accId');
        		$.ajax({
    	            type:"POST",
    	            url:"/bbs/changeAccState",
    	            data:{"accId":accId},
    	            async: false,
    	            success:function(data){
    	            		console.log(data);
    	            		//alert(data.massage);
    	            		getAllUser(1);
    	            },
    	            error:function(e){
    	                alert("对不起，服务器繁忙,请稍后重试！");                   
    	            }
    	        });
        	}
        });
        function getAllPost(pageNum){
        	$.ajax({
	            type:"POST",
	            url:"/bbs/getAllPost",
	            data:{"pageNum":pageNum},
	            async: false,
	            success:function(data){
	            	console.log(data);
	            	if(data.code=="100")
	            	{
	            		$("#allContent").empty();
	            		$("#allContent").append('<ul class="list-group">'+
			                '<a class="list-group-item active">'+
			                 '  帖子管理'+
			                '</a>'+
			                '<div class="list-group-item" id="postItem">');
	            		$("#allContent").append('</div></ul>');
	            		$(data.extend.data.list).each(function(index,item) { 
			                $("#postItem").append('<a href="/bbs/getPostDetail?postId='+this.postId+'&pageNum=1" style="color:grey">'+
			                 '       <h4 class="list-group-item-heading" style="color:black">['+this.modelName1+']</h4>'+
			                  ''+this.postTitle+''+
			                   ' </a>'+
			                 '   <a href="javascript:void(0)" style="float: right" postId="'+this.postId+'" id="addjh">添加至精华贴&nbsp;</a><a href="javascript:void(0)" style="float: right" postId="'+this.postId+'" id="addzd">置顶&nbsp;</a>'+
			                 '   <a href="javascript:void(0)" style="float: right" postId="'+this.postId+'" id="delpost">删除&nbsp;</a><p style="float: right;margin-right: 50px">浏览量:'+this.visitNum+'&nbsp;发表日期:'+this.createTime+'</p>');
	            		});
	            		setPageInfo(data.extend.data);
	            		console.log(data);
	            	}else{
	            		alert('操作失败');
	            		//$("#error2").html("<font color='red'>"+data.extend.info+"</font>");
	            	}
	            },
	            error:function(e){
	                alert("对不起，服务器繁忙,请稍后重试！");                   
	            }
	        });
        }
        $(document).on('click','#addjh',function(){
        	upPost($(this).attr('postId'),'JH');
        });
        $(document).on('click','#addzd',function(){
        	upPost($(this).attr('postId'),'ZD');
        });
        function upPost(tid,dictType){
        	$.ajax({
	            type:"POST",
	            url:"/bbs/upPost",
	            data:{"tid":tid,"dictType":dictType,"beizhu":""},
	            async: false,
	            success:function(data){
	            		console.log(data);
	            		alert(data.massage);
	            },
	            error:function(e){
	                alert("对不起，服务器繁忙,请稍后重试！");                   
	            }
	        });
        }
        $(document).on('click','#delpost',function(){
        	if(confirm('确定要删除该帖子吗?')){
        		var id=$(this).attr('postId');
        		$.ajax({
    	            type:"POST",
    	            url:"/bbs/deletePost",
    	            data:{"postId":id},
    	            async: false,
    	            success:function(data){
    	            		console.log(data);
    	            		getAllPost(1)
    	            },
    	            error:function(e){
    	                alert("对不起，服务器繁忙,请稍后重试！");                   
    	            }
    	        });
        	}
        });
		function getAllModel(){//bbs/getAllModel
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
	            		$("#allContent").empty();
	            		$("#allContent").append('<ul class="list-group">'+
			                '<a class="list-group-item active">'+
			                 '  板块管理　<span id="addModel" style="cursor: pointer;margin-left:700px;">添加</span>'+
			                '</a>'+
			                '<div class="list-group-item"><table class="table table-hover">'+
			                '<thead><tr><td>板块名</td><td>父板块</td><td>创建时间</td><td>创建者</td><td>操作</td></tr></thead><tbody id="postItem"></tbody></table>');
	            		$("#allContent").append('</div></ul>');
	            		$(data.extend.data).each(function(index,item) { 
	            			var fu=this.modelName;
			                $("#postItem").append('<tr>'+
			                		'<td>'+this.modelName+'</td><td>无</td><td>'+this.createTime+'</td><td>'+this.accName+'</td><td><a href="javascript:void(0)" id="modeledit" modId="'+this.modelId+'" isfu="true">修改</a> <a href="javascript:void(0)" id="modeldel" modId="'+this.modelId+'" title="删除操作将会同时删除所有子版块及包含帖子" alt="删除操作将会同时删除所有子版块及包含帖子">删除</a></td>'+
			                		'</tr>');
			                $(this.childList).each(function(index,item) { 
			                	$("#postItem").append('<tr>'+
				                		'<td>'+this.modelName+'</td><td>'+fu+'</td><td>'+this.createTime+'</td><td>'+this.accName+'</td><td><a href="javascript:void(0)" id="modeledit" modId="'+this.modelId+'" isfu="false">修改</a> <a href="javascript:void(0)" id="modeldel" modId="'+this.modelId+'" title="删除操作将会同时删除所有子版块及包含帖子" alt="删除操作将会同时删除所有子版块及包含帖子">删除</a></td>'+
				                		'</tr>');
			                });
	            		});
	            		//setPageInfo(data.extend.data);
	            		console.log(data);
	            	}else{
	            		alert('操作失败');
	            		//$("#error2").html("<font color='red'>"+data.extend.info+"</font>");
	            	}
	            },
	            error:function(e){
	                alert("对不起，服务器繁忙,请稍后重试！");                   
	            }
	        });
		}
        $(document).on('click','#modeldel',function(){
        	if(confirm('确定要删除该内容吗?\n删除操作将会同时删除所有子版块及包含帖子')){
        		var id=$(this).attr('modId');
        		$.ajax({
    	            type:"POST",
    	            url:"/bbs/deleteModel",
    	            data:{"modelId":id},
    	            async: false,
    	            success:function(data){
    	            		console.log(data);
    	            		getAllModel();
    	            },
    	            error:function(e){
    	                alert("对不起，服务器繁忙,请稍后重试！");                   
    	            }
    	        });
        	}
        });
        $(document).on('click','#modeledit',function(){
	        var id=$(this).attr('modId');
	        var isfu=$(this).attr('isfu');
	        var model;
	        $(allModel).each(function(index,item) { 
	        	if(this.modelId==id){
	        		model=this;
	        	}
                $(this.childList).each(function(index,item) { 
                	if(this.modelId==id){
    	        		model=this;
    	        	}
                });
    		});
	        $("#allContent").empty();
    		$("#allContent").append('<ul class="list-group">'+
                '<a class="list-group-item active">'+
                 '  修改板块'+
                '</a>'+
                '<div class="list-group-item controls docs-input-sizes"><form class="bs-docs-example">'+
                (isfu=='true'?('<input style="margin: 5px;width: 200px;height: 35px;font-size: medium;" class="span2" type="text" id="ermodel" placeholder="板块名" value="'+model.modelName+'">'):('<select id="postItem" class="modelselect" style="margin: 5px;width: 200px;height: 35px;font-size: medium;"></select></br><input style="margin: 5px;width: 200px;height: 35px;font-size: medium;" class="span2" type="text" id="ermodel" placeholder="板块名" value="'+model.modelName+'"></from></div></ul>'))+
                '<input type="buttom" value="保存" class="btn btn-primary" id="editmodelbc" isfu="'+isfu+'" modid="'+id+'">');
    		if(isfu=='false'){
	    		$(allModel).each(function(index,item) {
	                $("#postItem").append('<option value="'+this.modelId+'" '+(model.pid==this.modelId?'selected="selected"':'')+'>'+this.modelName+'</option>');
	    		});
    		}
        });
        $(document).on('click','#editmodelbc',function(){
        	var id=$(this).attr('modId');
	        var isfu=$(this).attr('isfu');
	        $.ajax({
	            type:"POST",
	            url:"/bbs/updateModel",
	            data:{"modelName":$("#ermodel").val(),"pid":isfu=='true'?-1:$("#postItem").val(),"modelId":id},
	            async: false,
	            success:function(data){
	            		console.log(data);
	            		getAllModel();
	            },
	            error:function(e){
	                alert("对不起，服务器繁忙,请稍后重试！");                   
	            }
	        });
        });
        $(document).on('click','#addModel',function(){
        	$("#allContent").empty();
    		$("#allContent").append('<ul class="list-group">'+
                '<a class="list-group-item active">'+
                 '  添加板块'+
                '</a>'+
                '<div class="list-group-item controls docs-input-sizes"><form class="bs-docs-example"><input type="radio" name="type" value="1" class="type">一级板块<input type="radio" name="type" value="2" checked="checked" class="type">二级板块</br>'+
                '<select id="postItem" class="modelselect" style="margin: 5px;width: 200px;height: 35px;font-size: medium;"></select></br><input style="margin: 5px;width: 200px;height: 35px;font-size: medium;" class="span2" type="text" id="ermodel" placeholder="板块名"></from></div></ul>'+
                '<input type="buttom" value="保存" class="btn btn-primary" id="modelbc">');
    		$(allModel).each(function(index,item) {
                $("#postItem").append('<option value="'+this.modelId+'">'+this.modelName+'</option>');
    		});
        });
        $(document).on('change','.type',function(){
        	if($(".type:checked").val()==1){
        		$('.modelselect').hide();
        	}
        	if($(".type:checked").val()==2){
        		$(".modelselect").show();
        	}
        });
        $(document).on('click','#modelbc',function(){
        	$.ajax({
	            type:"POST",
	            url:"/bbs/addModel",
	            data:{"modelName":$("#ermodel").val(),"pid":$(".type:checked").val()==1?-1:$("#postItem").val()},
	            async: false,
	            success:function(data){
	            		console.log(data);
	            		getAllModel();
	            },
	            error:function(e){
	                alert("对不起，服务器繁忙,请稍后重试！");                   
	            }
	        });
        });
		function addErModel(){//bbs/getAllModel
			$.ajax({
	            type:"POST",
	            url:"/bbs/getAllModel",
	            data:{},
	            async: false,
	            success:function(data){
	            	console.log(data);
	            	if(data.code=="100")
	            	{
	            		$("#allContent").empty();
	            		$("#allContent").append('<ul class="list-group">'+
			                '<a class="list-group-item active">'+
			                 '  添加板块'+
			                '</a>'+
			                '<div class="list-group-item controls docs-input-sizes"><form class="bs-docs-example"><select id="postItem" class="span2" style="margin: 5px;width: 200px;height: 35px;font-size: medium;"></select></br><input style="margin: 5px;width: 200px;height: 35px;font-size: medium;" class="span2" type="text" id="ermodel" placeholder="板块名"></from></div></ul>'+
			                '<input type="buttom" value="保存" class="btn btn-primary" id="ermodelbc">');
	            		$(data.extend.data).each(function(index,item) {
			                $("#postItem").append('<option value="'+this.modelId+'">'+this.modelName+'</option>');
	            		});
	            		//setPageInfo(data.extend.data);
	            		console.log(data);
	            	}else{
	            		alert('操作失败');
	            		//$("#error2").html("<font color='red'>"+data.extend.info+"</font>");
	            	}
	            },
	            error:function(e){
	                alert("对不起，服务器繁忙,请稍后重试！");                   
	            }
	        });
		}
		$(document).on('click','#ermodelbc',function(){
			$.ajax({
	            type:"POST",
	            url:"/bbs/addModel",
	            data:{"modelName":$("#ermodel").val(),"pid":$("#postItem").val()},
	            async: false,
	            success:function(data){
	            		alert(data.extend.tishi);
	            		console.log(data);
	            },
	            error:function(e){
	                alert("对不起，服务器繁忙,请稍后重试！");                   
	            }
	        });
		});
		function getMyExamines(pageNum){
			$.ajax({
	            type:"POST",
	            url:"/bbs/getMyExamines",
	            data:{"pageNum":pageNum},
	            async: false,
	            success:function(data){
	            	console.log(data);
	            	if(data.code=="100")
	            	{
	            		$("#allContent").empty();
	            		$("#allContent").append('<ul class="list-group">'+
			                '<a class="list-group-item active">'+
			                 '  申请记录'+
			                '</a>'+
			                '<div class="list-group-item"><table class="table table-hover">'+
			                '<thead><tr><td>申请项</td><td>申请内容</td><td>申请时间</td><td>审批状态</td></tr></thead><tbody id="postItem"></tbody></table>');
	            		$("#allContent").append('</div></ul>');
	            		$(data.extend.data.list).each(function(index,item) { 
			                $("#postItem").append('<tr>'+
			                		(this.examineType=='ZD'?'<td>置顶帖</td><td>【'+this.postModel+'】'+this.postTitle+'</td>':(this.examineType=='JH'?'<td>精华帖</td><td>【'+this.postModel+'】'+this.postTitle+'</td>':'<td>板块</td><td>【'+this.modelName+'】'+this.name+'</td>'))+
			                		'<td>'+this.createTime+'</td><td>'+(this.status=='DSP'?'审核中':(this.status=='TG'?'通过':'未通过'))+'</td></tr>');
	            		});
	            		setPageInfo(data.extend.data);
	            		console.log(data);
	            	}else{
	            		alert('操作失败');
	            		//$("#error2").html("<font color='red'>"+data.extend.info+"</font>");
	            	}
	            },
	            error:function(e){
	                alert("对不起，服务器繁忙,请稍后重试！");                   
	            }
	        });
		}
		function getMyPost(pageNum){
			$.ajax({
	            type:"POST",
	            url:"/bbs/getMyPost",
	            data:{"pageNum":pageNum},
	            async: false,
	            success:function(data){
	            	console.log(data);
	            	if(data.code=="100")
	            	{
	            		$("#allContent").empty();
	            		$("#allContent").append('<ul class="list-group">'+
			                '<a class="list-group-item active">'+
			                 '  我的帖子'+
			                '</a>'+
			                '<div class="list-group-item" id="postItem">');
	            		$("#allContent").append('</div></ul>');
	            		$(data.extend.data.list).each(function(index,item) { 
			                $("#postItem").append('<a href="/bbs/getPostDetail?postId='+this.postId+'&pageNum=1" style="color:grey">'+
			                 '       <h4 class="list-group-item-heading" style="color:black">['+this.modelName1+']</h4>'+
			                  ''+this.postTitle+''+
			                   ' </a>'+
			                 '   <a href="javascript:void(0)" style="float: right" postId="'+this.postId+'">编辑</a>'+
			                 '   <a href="javascript:void(0)" style="float: right" postId="'+this.postId+'" id="sqjh">申请精华贴&nbsp;</a><a href="javascript:void(0)" style="float: right" postId="'+this.postId+'" id="sqzd">申请置顶贴&nbsp;</a>'+
			                 '   <p style="float: right;margin-right: 50px">浏览量:'+this.visitNum+'&nbsp;发表日期:'+this.createTime+'</p>');
	            		});
	            		setPageInfo(data.extend.data);
	            		console.log(data);
	            	}else{
	            		alert('操作失败');
	            		//$("#error2").html("<font color='red'>"+data.extend.info+"</font>");
	            	}
	            },
	            error:function(e){
	                alert("对不起，服务器繁忙,请稍后重试！");                   
	            }
	        });
		}
		$(document).on('click','#sqjh',function(){//sqzd   sqjh
			addExamPost('JH',$(this).attr('postId'));
		});
		$(document).on('click','#sqzd',function(){//sqzd   sqjh
			addExamPost('ZD',$(this).attr('postId'));
		});
		function addExamPost(examineType,postId){
			$.ajax({
	            type:"POST",
	            url:"/bbs/addExamPost",
	            data:{"examineType":examineType,"pid":postId},
	            async: false,
	            success:function(data){
	            	console.log(data);
	            	if(data.code=="100"){
	            		alert('已上报您的申请！');
	            	}else{
	            		alert('您已上报过该申请！');
	            	}
	            },
	            error:function(e){
	                alert("对不起，服务器繁忙,请稍后重试！");                   
	            }
	        });
		}
		function setPageInfo(data){
			if(data.pages!=0){
	            $("#allContent").append('<ul class="pagination pagination-lg" style="float:right" id="pageinfo">'+
				    '<li><a '+(data.hasPreviousPage?'href="javascript:void(0)"':'')+' id="befor" hasPreviousPage="'+data.hasPreviousPage+'">&laquo;</a></li></ul>');
	            $(data.navigatepageNums).each(function(index,item){
		            $("#pageinfo").append('<li '+(data.pageNum==item?'class="active"':'')+'><a href="javascript:void(0)" pageNum="'+item+'" id="changeNum">'+item+'</a></li>');
	            });
	            $("#pageinfo").append('<li><a '+(data.hasNextPage?'href="javascript:void(0)"':'')+' id="after" hasNextPage="'+data.hasNextPage+'">&raquo;</a></li>');
			}
		}
		$(document).on('click','#befor',function(){
			if($(this).attr('hasPreviousPage')=='true'){
				var nowPage=$('ul.pagination li.active a').attr('pageNum');
				var state=$("li.active").children("a").attr("state");
				changePage(state,parseInt(nowPage)-1);
			}
		});
		$(document).on('click','#after',function(){
			if($(this).attr('hasNextPage')=='true'){
				var nowPage=$('ul.pagination li.active a').attr('pageNum');
				var state=$("li.active").children("a").attr("state");
				changePage(state,parseInt(nowPage)+1);
			}
		});
		$(document).on('click','#changeNum',function(){
			var nowPage=$('ul.pagination li.active a').attr('pageNum');
			var changeNum=$(this).attr('pageNum')
			if(nowPage!=changeNum){
				var state=$("li.active").children("a").attr("state");
				changePage(state,parseInt(changeNum));
			}
		});
		$(document).on('click','#post',function(){
			var postId=$(this).attr('postId');
		});
		$(document).on('change','#file',function(){
			/* var form = new FormData();
     		form.append("file",document.getElementById('file').files[0]);
			alert(234); */
     		/* $.ajax({  
     		    url: "/upload",  
     		    type: 'post',  
     		    data: form,  
     		    processData: false,//用来回避jquery对formdata的默认序列化，XMLHttpRequest会对其进行正确处理  
     		    contentType: false,//设为false才会获得正确的conten-Type  
     		    xhr: function() { //用以显示上传进度  
     		        /* var xhr = $.ajaxSettings.xhr();  
     		        if (xhr.upload) {  
     		            xhr.upload.addEventListener('progress', function(e) {  
     		               var i=parseInt((e.loaded/e.total*100).toFixed(2));
      		               var imgLeft = -(i*44+(i*10))+'px'
     					   object.style.backgroundPosition = imgLeft+'\t'+'0px'
     					   object.innerHTML = i+'%';
     		            }, false);  
     		        }  
     		        return xhr;   
     		        alert('asd');
     		    },  
     		    async: true  
     		}).then(function(data) {  
     		   /*  if (data.IsSuccessful == false) {
     		    	alert(data);
     		    } else {  
     		    	alert(data);
     		    }   
     		   var arr = data.split('***');
     		   fileId=arr[0];
     		   $("#head").attr('src',arr[1]);
     		   	//fileId=data;
     			console.log(data);
     		}) */
     		$.ajaxFileUpload({
                url : '/upload',
                secureuri : false,
                fileElementId : 'file',
                dataType : 'json',//此时指定的是后台需要返回json字符串,前端自己解析,可以注释掉.后台直接返回map
                /* data : {
                    name : $('#name').val()
                }, */
                success : function(data, status) {
                   if(data!='false'){
                	   var arr = data.split('***');
             		   fileId=arr[0];
             		   $("#head").attr('src',arr[1]);
             			console.log(data);
                   }else{alert('上传失败');}
                },
                error : function(data, status, e) {
                    //这里处理的是网络异常，返回参数解析异常，DOM操作异常  
                    alert("上传发生异常");
                }
            })
		});
		var ok1=false;
		var ok2=false;
		$(document).on('click','#tj',function(){
			if($("#address").val()==""){
				ok1=false;
				$('#error11').text('请填写现地址').css('color','red');
			}else{
				ok1=true;
				$('#error11').text('').css('color','green');
			}
			if($("#email").val()==""){
				ok2=false;
				$('#error2').text('请填写您的邮箱').css('color','red');
			}else{
				$.ajax({
			          type:"POST",
			          url:"/registryVerification",
			          data:{"email":$('#email').val()},
			          async: false,
			          success:function(data){
			          	console.log(data);
			          	if(data.code=="100")
			          	{
			          		ok2=true;
				            $('#error2').text('').css('color','green');
			          	}else{
			          		ok3=false;
			          		$('#error2').text(data.extend.error).css('color','red');
			          	}
			          },
			          error:function(e){
			              alert("对不起，服务器繁忙,请稍后重试！");                   
			          }
			      });
			}
			if(ok1&&ok2){
				$.ajax({
			          type:"POST",
			          url:"/bbs/changeAccountInfo",
			          data:{"headImg":fileId==""?"${USER_LOGIN_KEY.headImg}":fileId,"accName":$("#username").val(),"email":$("#email").val(),"address":$("#address").val(),"hobby":$("#hobby").val()},
			          async: false,
			          success:function(data){
			          	console.log(data);
			          	if(data.code=="100")
			          	{
			          		alert("修改成功");
			          	}else{
			          		alert("修改失败"+data);
			          	}
			          },
			          error:function(e){
			              alert("对不起，服务器繁忙,请稍后重试！");                   
			          }
			      });
			}
		});
		function toChangeUserInfo(){
			$("#allContent").empty();
			$("#allContent").append('<div class="panel panel-default">'+
					'<div class="panel-heading">'+
                    '<h3 class="panel-title">'+
                      '  个人信息修改'+
                   ' </h3>'+
               ' </div>'+
                '<div class="panel-body">'+
				'<form id="form1" action="#" method="post" enctype="multipart/form-data">'+
                 '       <div class="form-group">'+
                  '          <img id="head" class="avatar left" height="70" src="${USER_LOGIN_KEY.headPath}" width="70" />'+
                   '         请上传你的头像<br/>'+
                   '     </div>'+
                   '     <input type="file" id="file" accept="image/*" name="file"><br/>'+
                   '     <div class="form-group">'+
                   '         <label for="name">用户名</label>'+
                   '         <input id="username" type="text" class="form-control" name="username" id="name" width="200px" height="80px" value="${USER_LOGIN_KEY.accName}"'+
                   '                placeholder="请输入名称" disabled="disabled"> <p class="help-block"><div id="error1"></div></p>'+
                   '     </div>'+
                   '     <div class="form-group">'+
                   '         <label for="name">住 址</label><br/>'+
                   '         <input type="text" name="address" id="address" value="${USER_LOGIN_KEY.address}"><p class="help-block"><div id="error11"></div></p> '+
                   '     </div>'+
                   '     <dl class="form-group">'+
                   '         <dt><label for="user_profile_blog">邮箱</label></dt>'+
                   '         <dd><input id="email" type="email" class="form-control" id="user_profile_blog" name="email" size="30" value="${USER_LOGIN_KEY.email}" />'+
                   '         <p class="help-block"><div id="error2"></div></p>'+
                   '         </dd>'+
                   '     </dl>'+
                   '     <dl class="form-group">'+
                   '         <dt><label for="user_profile_company">爱好</label></dt>'+
                   '         <dd><input class="form-control" id="hobby" name="hobby" size="30" type="text" width="200px" value="${USER_LOGIN_KEY.hobby}" /></dd>'+
                   '     </dl>'+
                   '     <input type="button" id="tj" value="提交">'+
                   ' </form>'+
                '</div>'+
           ' </div>');
		}
	</script>
</head>
<body>
<%@ include file="/nav.jsp" %>
<div class="container" style="margin-top: 30px">
    <div class="row">
        <div class="col-xs-3">
            <ul class="nav nav-pills nav-stacked">
	            <c:choose>
					<c:when test="${USER_LOGIN_KEY.role eq 'student'}"><!-- 学生 -->
		                <li role="presentation" class="active"><a href="javascript:void(0)" state="1">资料修改</a></li>
		                <li role="presentation" ><a href="javascript:void(0)" state="2">我的帖子</a></li>
		                <li role="presentation"><a href="javascript:void(0)" state="3">申请记录</a></li>
					</c:when>
					<c:when test="${USER_LOGIN_KEY.role eq 'company'}"><!-- 公司 -->
		                <li role="presentation" class="active"><a href="javascript:void(0)" state="4">资料修改</a></li>
		                <li role="presentation" ><a href="javascript:void(0)" state="5">申请板块</a></li>
		                <li role="presentation" ><a href="javascript:void(0)" state="6">我的发帖</a></li>
		                <li role="presentation"><a href="javascript:void(0)" state="7">申请记录</a></li>
					</c:when>
					<c:otherwise><!-- 管理员 -->
						<li role="presentation" class="active"><a href="javascript:void(0)" state="8">板块管理</a></li>
		                <li role="presentation" ><a href="javascript:void(0)" state="9">帖子管理</a></li>
		                <li role="presentation" ><a href="javascript:void(0)" state="10">用户管理</a></li>
		                <li role="presentation"><a href="javascript:void(0)" state="11">申请管理</a></li>
		                <li role="presentation"><a href="javascript:void(0)" state="12">意见建议</a></li>
		                <li role="presentation"><a href="javascript:void(0)" state="13">举报查看</a></li>
		                <li role="presentation"><a href="javascript:void(0)" state="14">用户不良记录</a></li>
		                <li role="presentation"><a href="javascript:void(0)" state="15">日志查看</a></li>
					</c:otherwise>
				</c:choose>
            </ul>
        </div>

        <div class="col-md-9" id="allContent">
        
			<!-- 我的帖子 -->
            <!-- <ul class="list-group">
		                <a class="list-group-item active">
		                    我的帖子
		                </a>
							
		                <div class="list-group-item">
		                    <a href="/pages/post.jsp" style="color:grey">
		                        <h4 class="list-group-item-heading" style="color:black">[板块]</h4>
		                        标题
		                    </a>
		                    <a href="javascript:void(0)" style="float: right">编辑</a>
		                    
		                    <a href="3" style="float: right">申请精华贴&nbsp;</a><a href="3" style="float: right">申请置顶贴&nbsp;</a>
		                    <p style="float: right;margin-right: 50px">浏览量:1&nbsp;评论量:1&nbsp;发表日期:1900-01-01</p>
		                </div>
            </ul>
            资料修改
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        个人信息修改
                    </h3>
                </div>
                <div class="panel-body">
				
				<form id="form1" action="#" method="post" enctype="multipart/form-data">


                        <div class="form-group">
                            <img alt="@zhangjianhao" class="avatar left" height="70" src="" width="70" />
                            请上传你的头像<br/>

                        </div>
                        <input type="file" id="inputfile" accept="image/*" name="photoImg"><br/>

                        <div class="form-group">
                            <label for="name">用户名</label>
                            <input id="username" type="text" class="form-control" name="username" id="name" width="200px" height="80px" value="用户名"
                                   placeholder="请输入名称" disabled="disabled"> <p class="help-block"><div id="error1"></div></p>
                        </div>

                        <div class="form-group">
                            <label for="name">性 别</label><br/>
                            
                            男<input type="radio" name="sex" value="男" checked="checked"> 
                             &nbsp;&nbsp;女<input type="radio" name="sex" value="女">
                            
                        </div>


                        <dl class="form-group">
                            <dt><label for="user_profile_blog">邮箱</label></dt>
                            <dd><input id="email" type="email" class="form-control" id="user_profile_blog" name="email" size="30" value="邮箱地址" />
                            <p class="help-block"><div id="error2"></div></p>
                            </dd>
                        </dl>
                        <dl class="form-group">
                            <dt><label for="user_profile_company">密码</label></dt>
                            <dd><input class="form-control" id="password" name="password" size="30" type="password" width="200px" /></dd>
                        </dl>
                        <dl class="form-group">
                            <dt><label for="user_profile_location">重复密码</label></dt>
                            <dd><input class="form-control" id="confirm_password" name="confirm_password" size="30" type="password" width="200px" /></dd>
                        </dl>
                        <input type="submit" value="提交">
                    </form>
                </div>
            </div>
            申请记录
   <ul class="pagination pagination-lg" style="float:right">
分页

    <li><a href="javascript:void(0)">&laquo;</a></li>
    
    <li class="active"><a href="javascript:void(0)">1</a></li>
    
    <li><a href="javascript:void(0)">2</a></li>
    
     <li><a href="javascript:void(0)">&raquo;</a></li>
   
	</ul>
	<br>
 -->
        </div>
    </div>
</div>
</body>
</html>