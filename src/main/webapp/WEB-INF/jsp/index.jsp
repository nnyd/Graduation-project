<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"   prefix="fmt" %>
<!doctype html>
<html lang="zh" class="no-js">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>技术论坛</title>
	<link rel="/static/stylesheet" type="text/css" href="css/zzsc-demo.css">
    <link href="/static/css/index.css" rel="stylesheet">
	<link href="/static/css/bootstrap.min.css" rel="stylesheet">
	<link href="/static/css/titlebar.css" rel="stylesheet">
	<script src="/static/js/jquery.min.js"></script>
    <script src="/static/js/bootstrap.min.js"></script>
</head>
<body>
	<%@ include file="/nav.jsp" %>
	<!-- ------------------------------------------------------------------------ -->
	<div style="width: 100%;">
		 
	<div class="container user-info">
    <div class="row">
        <div class="col-xs-12">
            <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                <!-- Indicators -->
                <ol class="carousel-indicators">
                    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                </ol>

                <!-- Wrapper for slides -->
                <div class="carousel-inner" role="listbox">
                    <div class="item active">
                        <img src="/static/img/2.jpg" alt="...">
                        <div class="carousel-caption">
                        </div>
                    </div>
                    <div class="item">
                        <img src="/static/img/1.jpg" alt="...">
                        <div class="carousel-caption">
                        </div>
                    </div>

                    <div class="item">
                        <img src="/static/img/2.jpg" alt="...">
                        <div class="carousel-caption">
                        </div>
                    </div>
                </div>

                <!-- Controls -->
                <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div>
    </div>
<!-- =========================================================================================================== -->
   
<div class="container" style="margin-top: 30px;">
    <div class="row">
        <div class="col-md-9">

            <ul class="list-group">
                <div class="list-group-item active">
                    新帖推荐
                    <a href="/getAllNewBbsPosts?pageNum=1" style="float: right;color: white">更多>></a>
                    <!--<p style="float: right"></p>-->
                </div>
				<c:forEach items="${newBbsPosts}" var="newBbsPost">
					<a href="/bbs/getPostDetail?postId=${newBbsPost.postId}&pageNum=1" class="list-group-item">
	                    <h4 class="list-group-item-heading">[${newBbsPost.modelName1}]</h4>
	                    ${newBbsPost.postTitle}<span class="badge">新</span>
	                    <p class="text-right" style="float: right;margin-right: 20px">浏览量:${newBbsPost.visitNum}&nbsp;评论量:${newBbsPost.comNum}&nbsp;发表日期:<fmt:formatDate value="${newBbsPost.createTime}" pattern="yyyy-MM-dd hh:mm:ss"/></p>
	                </a>
				</c:forEach>                
				<!-- <a href="/pages/post.jsp" class="list-group-item">
                    <h4 class="list-group-item-heading">[惺惺惜惺]</h4>
                    新兴小学校详细信息线下<span class="badge">新</span>
                    <p class="text-right" style="float: right;margin-right: 20px">浏览量:1&nbsp;评论量:1&nbsp;发表日期:1900-01-01</p>
                </a>
				
				<a href="/pages/post.jsp" class="list-group-item">
                    <h4 class="list-group-item-heading">[惺惺惜惺]</h4>
                    新兴小学校详细信息线下<span class="badge">新</span>
                    <p class="text-right" style="float: right;margin-right: 20px">浏览量:1&nbsp;评论量:1&nbsp;发表日期:1900-01-01</p>
                </a> -->
            </ul>

        </div>

        <div class="col-md-3">
            <ul class="list-group">
                <div class="list-group-item active">
                    友情链接
                </div>
                  
                         <a href="https://www.zhipin.com/" class="list-group-item">BOSS直聘</a>
						 <a href="http://ts.zhaopin.com/jump/index_new.html" class="list-group-item">智联招聘</a>
						 <a href="https://www.liepin.com" class="list-group-item">猎聘网</a>
						 <a href="https://www.51job.com/" class="list-group-item">前程无忧(51Job)</a>
                 
            </ul>
               <a href="/bbs/toCreatePost" ><button type="button" class="btn btn-primary" style="width: 200px;height:50px;margin-left: 30px">我要发帖</button></a>
               <a href="javascript:void(0)"  id="yijian"><button type="button" class="btn btn-primary" style="width: 200px;height:50px;margin-left: 30px;margin-top: 4px;">意见建议</button></a>
        </div>

        <div class="row">
            <div class="col-md-9" style="margin-left: 15px">
                <ul class="list-group">
                    <div class="list-group-item active">
                        精华帖
                        <a href="/getAllJhBbsPosts?pageNum=1" style="float: right;color: white">更多>></a>
                    </div>
                     
	                <c:forEach items="${jhBbsPosts}" var="newBbsPost">
						<a href="/bbs/getPostDetail?postId=${newBbsPost.postId}&pageNum=1" class="list-group-item">
		                    <h4 class="list-group-item-heading">[${newBbsPost.modelName1}]</h4>
		                    ${newBbsPost.postTitle}<span class="badge">精华</span>
		                    <p class="text-right" style="float: right;margin-right: 20px">浏览量:${newBbsPost.visitNum}&nbsp;评论量:${newBbsPost.comNum}&nbsp;发表日期:<fmt:formatDate value="${newBbsPost.createTime}" pattern="yyyy-MM-dd hh:mm:ss"/></p>
		                </a>
					</c:forEach> 
                    <!-- <a href="/pages/post.jsp" class="list-group-item">
                        <h4 class="list-group-item-heading">[柔柔弱弱]</h4>
                        融入分公司电饭锅<span class="badge">热</span>
                        <p class="text-right" style="float: right;margin-right: 20px">浏览量:1&nbsp;评论量:1&nbsp;发表日期:1900-01-01</p>
                    </a>
                    
					<a href="/pages/post.jsp" class="list-group-item">
                        <h4 class="list-group-item-heading">[柔柔弱弱]</h4>
                        融入分公司电饭锅<span class="badge">热</span>
                        <p class="text-right" style="float: right;margin-right: 20px">浏览量:1&nbsp;评论量:1&nbsp;发表日期:1900-01-01</p>
                    </a>
					
					<a href="/pages/post.jsp" class="list-group-item">
                        <h4 class="list-group-item-heading">[柔柔弱弱]</h4>
                        融入分公司电饭锅<span class="badge">热</span>
                        <p class="text-right" style="float: right;margin-right: 20px">浏览量:1&nbsp;评论量:1&nbsp;发表日期:1900-01-01</p>
                    </a>
					
					<a href="/pages/post.jsp" class="list-group-item">
                        <h4 class="list-group-item-heading">[柔柔弱弱]</h4>
                        融入分公司电饭锅<span class="badge">热</span>
                        <p class="text-right" style="float: right;margin-right: 20px">浏览量:1&nbsp;评论量:1&nbsp;发表日期:1900-01-01</p>
                    </a> -->
					
                </ul>
            </div>

            
    </div>
     <h3 style="margin-top: 20px;margin-left: 15px">板块分类</h3>
    <hr/>
      <div  class="container">
			<!-- 换行标签 4个换行 -->
         <div class="row">
      		<c:forEach items="${models}" var="model">
	      		<div class="col-md-3 col-sm-12">
	                <a href="/getPostByModel?modelId=${model.modelId}&pageNum=1">
	                <div class="main-forum">
	                    <h3>${model.pareName}</h3>
						${model.modelName}
	                </div>
	                </a>
	
	            </div>
      		</c:forEach>
		<!-- 			
			<div class="col-md-3 col-sm-12">
                <a href="#">
                <div class="main-forum">
                    <h3>板块2</h3>
					第三方的范德萨发的
                </div>
                </a>

            </div>
			
			<div class="col-md-3 col-sm-12">
                <a href="#">
                <div class="main-forum">
                    <h3>板块3</h3>
					第三方的范德萨发的
                </div>
                </a>

            </div>
			
			<div class="col-md-3 col-sm-12">
                <a href="#">
                <div class="main-forum">
                    <h3>板块4</h3>
					第三方的范德萨发的
                </div>
                </a>

            </div>
			
         </div>
		 
		 <div class="row">
      
            <div class="col-md-3 col-sm-12">
                <a href="#">
                <div class="main-forum">
                    <h3>板块5</h3>
					第三方的范德萨发的
                </div>
                </a>

            </div>
         </div>
            
           -->
  

		</div>
    </div>
    
   </div></div></div>

 
 <div  style="margin-top: 80px;background-color: rgba(0,0,0,0.8);height: 100px;color: darkgray;width: 100%">
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
</div>
<div class="modal fade"  id="myModal" tabindex="-1" role="dialog"
        aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button"  class="close"  data-dismiss="modal"  aria-hidden="true">
                &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">请填写意见详细信息</h4>
            </div>
            <div class="modal-body">
                感谢您为我们提出宝贵的意见：<br/>
                <textarea rows="3" cols="20" id="ideaContent" style="width: 500px;">
				</textarea>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" id="close">关闭</button>
                <button type="button" class="btn btn-primary" id="qdjb">确定</button>
            </div>
        </div>
    </div>
</div>
	
	
<script type="text/javascript">
$(document).on('click','#qdjb',function(){
	if($('#ideaContent').val()==''){
		$('#close').click();
		return ;
	}
	$.ajax({
        type:"POST",
        url:"/bbs/createIdea",
        data:{"ideaContent":$('#ideaContent').val()},
        async: false,
        success:function(data){
        	console.log(data);
        	if(data.code=="100")
        	{
        		alert('上报成功');
        		$('#close').click();
        	}else{
        		alert("上报失败");
        	}
        },
        error:function(e){
            alert("对不起，服务器繁忙,请稍后重试！");                   
        }
    });
});
$(document).on('click','#yijian',function(){
	if("${USER_LOGIN_KEY.role}"==""){
		window.location.href="/toLogin";
		return ;
	}
	$('#ideaContent').val('');
	$('#myModal').modal();
});

</script>
</body>
</html>