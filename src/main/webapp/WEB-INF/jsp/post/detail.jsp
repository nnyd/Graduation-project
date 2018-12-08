<%@page import="com.cgn.bbs.beans.Report"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"   prefix="fmt" %>
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
<div style="height:100%">

<div class="container" style="margin-top: 50px">
  	  
    <div class="row">
       <div class="col-md-1 post-border">
       </div>
        <div class="col-md-2 post-head">
                <!--<img src="img/tm-bg-slide-1.jpg" width="80px" height="80px" class="img-responsive img-circle">-->
            <img  alt="" class="img-responsive img-circle" src="${data.post.headPath}"
                  style="margin:1px 1px;width: 120px;height: 120px;margin: 30px auto;"/>

                <span class="user-info">
                            <span class="badge" style="background: #f1c40f;margin-top: 5px">发帖者</span>
                            :<span class="badge" style="background: #f1c40f;margin-top: 5px">${data.post.accName}</span>
                        </span><br/>
                        <span class="user-info">
                            <span class="badge" style="background: #2ecc71;margin-top: 5px">性别</span>
                            :<span class="badge" style="background: #2ecc71;margin-top: 5px">${data.post.accSex}</span>
                        </span><br/>
                        <span class="user-info">
                             <span class="badge" style="background: #ff6927;margin-top: 5px">
                             <c:if test="${data.post.role eq 'company'}">公司</c:if>
                             <c:if test="${data.post.role eq 'student'}">学生</c:if>
                             </span>
                        </span>
            <br>
            <c:if test="${data.post.role eq 'company'}">
				<span class="user-info">
					<a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=2865176958&site=qq&menu=yes"><img border="0" src="http://wpa.qq.com/pa?p=2:2865176958:41" alt="点击这里联系公司客服" title="点击这里联系公司客服"></a>
				</span>
			</c:if>
			
        </div>
        <div class="col-md-8 post-content">
			
            <div class="post-title">
                <h2 style="margin-left:20px;color:black">[${data.post.modelName1}]${data.post.postTitle}</h2>
                <div style="margin-left:20px" >

                    <span class="glyphicon glyphicon-comment"></span>&nbsp;&nbsp;回复数 &nbsp;${data.post.comNum}|&nbsp;<span>发表于:<fmt:formatDate value="${data.post.createTime}" pattern="yyyy-MM-dd hh:mm:ss"/></span>
                    <!-- 判断是否登录 -->
                    <c:if test="${USER_LOGIN_KEY.role!=null&&USER_LOGIN_KEY.accId==data.post.accId}">
						<a style="float:right;margin-right: 20px;" href="#">编辑</a>
					</c:if>
                </div>
                <strong style=" float:right;margin-right:10px;margin-top: 10px">
                    <span class="badge" style="background: #ff6927;width: 50px;">楼主</span></strong>

            </div>
            <div style="margin: 20px">
               ${data.post.postDetil}
               <br/>
               <br/>
               <c:if test="${USER_LOGIN_KEY!=null&&USER_LOGIN_KEY.role!=null&&data.files!=null}">
               		<a href="#" id="downloadFile">${data.files.filePath}</a>
               </c:if>
               <c:if test="${(USER_LOGIN_KEY==null||USER_LOGIN_KEY.role==null)&&data.files!=null}">
               		<a href="/toLogin">隐藏内容登陆后查看</a>
               </c:if>
            </div>

        </div>
         <div class="col-md-1 post-border">
       </div>

        


    </div>
    </div>
    
	<!-- ------------------------------------------------------------------- -->
    <!-- 回复内容 -->
    
  	 <div class="container">
    <div class="row" style="margin-top: 5px">
        
        <c:forEach items="${data.comment.list}" var="comment"> 
        <div class="col-md-1 reply-border">

        </div>
		        <div class="col-md-2 reply-head">
		            <img  alt="" class="img-responsive img-circle" src="${comment.headPath}"
		                  style="margin:1px 1px;width: 120px;height: 120px;margin: 30px auto;"/>
		
		            <span class="user-info">
		                        <span class="badge" style="background: #f1c40f;margin-top: 5px">姓名</span>
		                        :<span class="badge" style="background: #f1c40f;margin-top: 5px">${comment.accName}</span>
		                    </span><br/>
		                    <span class="user-info">
		                        <span class="badge" style="background: #2ecc71;margin-top: 5px">性别</span>
		                        :<span class="badge" style="background: #2ecc71;margin-top: 5px">${comment.accSex}</span>
		                    </span><br/>
		                    <span class="user-info">
		                         <span class="badge" style="background: #ff6927;margin-top: 5px">
		                         	<c:if test="${comment.role eq 'company'}">公司</c:if>
                             		<c:if test="${comment.role eq 'student'}">学生</c:if>
		                         </span>
		                    </span>
		            <br>
		
		
		        </div>
		        <div class="col-md-8 reply-content">
		
		            <div class="reply-time">
		                <span style="color: gray">
		                	回复于:<fmt:formatDate value="${comment.createTime}" pattern="yyyy-MM-dd hh:mm:ss"/>
		                	<c:if test="${comment.pid!=-1}">回复${comment.louPare}楼：${comment.accNamePare}</c:if>
		                </span>
		                <c:choose>
		                	<c:when test="${comment.lou eq '1'}">
			                <!-- if -->
			                <span class="badge" style="float:right;margin-right:10px;background: #ff6927;width: 50px;">沙发</span>
			                </c:when>
			                <c:when test="${comment.lou eq '2'}">
			                <!-- else if -->
			                <span class="badge" style="float:right;margin-right:10px;background: #ff5959;width: 50px;">板凳</span>
			                </c:when>
			                <c:when test="${comment.lou eq '3'}">
			                <!-- else if -->
			                <span class="badge" style="float:right;margin-right:10px;background: #4b9ded;width: 50px;">地板</span>
			                </c:when>
			                <c:otherwise>
			                <!-- else if -->
			                <span class="badge" style="float:right;margin-right:10px;background: #4b9ded;width: 50px;">${comment.lou}楼</span>
			                <!-- end if -->
			                </c:otherwise>
		                </c:choose>
		            </div>
		            <div style="margin: 20px;">
		                ${comment.commentContent}
		            </div>
		            <c:if test="${USER_LOGIN_KEY!=null&&USER_LOGIN_KEY.role!='admin'&&comment.accId!=USER_LOGIN_KEY.accId}">
						&nbsp;<a style="float:right;margin-right: 20px;margin-top: 200px;" href="#" id="jubao" acc="${comment.accId}">&nbsp;举报&nbsp;</a>&nbsp;
					</c:if>
					&nbsp;<a style="float:right;margin-right: 20px;margin-top: 200px;" href="/bbs/tohuifu?pid=${comment.commentId}">&nbsp;回复&nbsp;</a>&nbsp;
		        </div>
		        <div class="col-md-1 reply-border">

        </div>
		</c:forEach>
        


    </div>
     </div>
    
<!--   
$("#allContent").append('<ul class="pagination pagination-lg" style="float:right" id="pageinfo">'+
'<li><a '+(data.hasPreviousPage?'href="#"':'')+' id="befor">&laquo;</a></li></ul>');
     $(data.navigatepageNums).each(function(index,item){
      $("#pageinfo").append('<li '+(data.pageNum==item?'class="active"':'')+'><a href="#">'+item+'</a></li>');
     });
     $("#pageinfo").append('<li><a '+(data.hasNextPage?'href="#"':'')+' id="after">&raquo;</a></li>'); -->
  	 
<!-- 分页 -->
<ul class="pagination pagination-lg page-float" style="float:right;margin-right: 300px">

    <li><a href="javascript:void(0)" id="befor" hasPreviousPage="${data.comment.hasPreviousPage}">&laquo;</a></li>
    <c:forEach items="${data.comment.navigatepageNums}" var="pageNums">
    <c:if test="${data.comment.pageNum==pageNums}">
    <li class="active"><a href="javascript:void(0)" pageNum="${pageNums}" id="changeNum">${pageNums}</a></li>
    </c:if><c:if test="${data.comment.pageNum!=pageNums}">
    <li><a href="javascript:void(0)" pageNum="${pageNums}" id="changeNum">${pageNums}</a></li>
    </c:if>
    </c:forEach>
    <li><a href="javascript:void(0)" id="after" hasNextPage="${data.comment.hasNextPage}">&raquo;</a></li>
   
</ul>
<br>
  	
  	
<div style="height: 200px;margin: 70px auto; width: 800px;">
<form action="#" method="post">
        <div style="margin: 5px auto;height: 100px; width: 800px">
            <textarea id="TextArea1" cols="20" rows="1" name="content" class="ckeditor"></textarea>

        </div>
	    <div style="float:right;margin: 60px auto">
	        <input type="button" class="btn btn-primary" style="width: 60px;" value="回复" id="hf"></input>
	    </div>
</form>
</div>



  	
</div>


<!-- ================ -->

<div class="modal fade"  id="myModal" tabindex="-1" role="dialog"
        aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button"  class="close"  data-dismiss="modal"  aria-hidden="true">
                &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">请填写举报详细信息</h4>
            </div>
            <div class="modal-body">
                举报类型：
                <select id="reportType">
                    <c:forEach items="<%=Report.map.entrySet()%>" var="entry">
                    	<option value="${entry.key}">${entry.value}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="modal-footer">
                <div id="thdiv"  align="left">举报详细信息:
					<input type="text" id="reportDetail">
				</div>
                <button type="button" class="btn btn-default" data-dismiss="modal" id="close">关闭</button>
                <button type="button" class="btn btn-primary" id="qdjb">确定</button>
            </div>
        </div>
    </div>
</div>

<!-- ================ -->
    <script type="text/javascript">
    	var accId;
/*    		alert(document.URL);
   		alert(document.referrer); */
    	$(document).on('click','#befor',function(){
    		if($(this).attr('hasPreviousPage')=='true'){
				var nowPage=$('ul.pagination li.active a').attr('pageNum');
				window.location.href="/bbs/getPostDetail?postId=${data.post.postId}&pageNum="+(parseInt(nowPage)-1);
			}
    	});
   		$(document).on('click','#after',function(){
			if($(this).attr('hasNextPage')=='true'){
				var nowPage=$('ul.pagination li.active a').attr('pageNum');
				window.location.href="/bbs/getPostDetail?postId=${data.post.postId}&pageNum="+(parseInt(nowPage)+1);
			}
		});
		$(document).on('click','#changeNum',function(){
			var nowPage=$('ul.pagination li.active a').attr('pageNum');
			var changeNum=$(this).attr('pageNum')
			if(nowPage!=changeNum){
				window.location.href="/bbs/getPostDetail?postId=${data.post.postId}&pageNum="+(parseInt(changeNum));
			}
		});
    	$(document).on('click','#qdjb',function(){
    		$.ajax({
		          type:"POST",
		          url:"/bbs/createReport",
		          data:{"reportType":$('#reportType').val(),"reportDetail":$('#reportDetail').val(),"accId":accId},
		          async: false,
		          success:function(data){
		          	console.log(data);
		          	if(data.code=="100")
		          	{
		          		alert('举报成功');
		          		$('#close').click();
		          	}else{
		          		alert("举报失败");
		          	}
		          },
		          error:function(e){
		              alert("对不起，服务器繁忙,请稍后重试！");                   
		          }
		      });
    	});
    	$(document).on('click','#jubao',function(){
    		accId=$(this).attr('acc');
    		$('#reportDetail').val('');
    		$('#myModal').modal();
    	});
    	$(document).on('click','#downloadFile',function(){
    		window.open("http:${pageContext.request.contextPath}/download?fileId=${data.files.fileId}");
    	});
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
			          data:{"postId":"${data.post.postId}","pid":-1,"commentRealContent":CKEDITOR.instances.TextArea1.getData()},
			          async: false,
			          success:function(data){
			          	console.log(data);
			          	if(data.code=="100")
			          	{
			          		window.location.href="/bbs/getPostDetail?postId=${data.post.postId}&pageNum=99999";
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