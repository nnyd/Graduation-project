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
<div class="container" style="margin-top: 30px">
    <div class="row">
    	<div class="col-md-12" id="allContent">
    	
    	<ul class="list-group">
              <a class="list-group-item active">
                  帖子
              </a>
              <div class="list-group-item">
				<c:forEach items="${data.list}" var="posts">
	                 <a href="/bbs/getPostDetail?postId=${posts.postId}&pageNum=1" class="list-group-item">
	                    <h4 class="list-group-item-heading">[${posts.modelName1}]</h4>
	                    ${posts.postTitle}
	                    <p class="text-right" style="float: right;margin-right: 20px">浏览量:${posts.visitNum}&nbsp;评论量:${posts.comNum}&nbsp;发表日期:<fmt:formatDate value="${posts.createTime}" pattern="yyyy-MM-dd hh:mm:ss"/></p>
	                </a>
	           	</c:forEach>
              </div>
        </ul>
    	
    	
    	
    	
    	<ul class="pagination pagination-lg page-float" style="float:right;margin-right: 200px">

		    <li><a href="javascript:void(0)" id="befor" hasPreviousPage="${data.hasPreviousPage}">&laquo;</a></li>
		    <c:forEach items="${data.navigatepageNums}" var="pageNums">
		    <c:if test="${data.pageNum==pageNums}">
		    <li class="active"><a href="javascript:void(0)" pageNum="${pageNums}" id="changeNum">${pageNums}</a></li>
		    </c:if><c:if test="${data.pageNum!=pageNums}">
		    <li><a href="javascript:void(0)" pageNum="${pageNums}" id="changeNum">${pageNums}</a></li>
		    </c:if>
		    </c:forEach>
		    <li><a href="javascript:void(0)" id="after" hasNextPage="${data.hasNextPage}">&raquo;</a></li>
		   
		</ul>
    	</div>
    </div>
</div>
<script type="text/javascript">
$(document).on('click','#befor',function(){
	if($(this).attr('hasPreviousPage')=='true'){
		var nowPage=$('ul.pagination li.active a').attr('pageNum');//document.URL
		window.location.href=document.URL.substring(0,1+document.URL.lastIndexOf('='))+(parseInt(nowPage)-1);
	}
});
	$(document).on('click','#after',function(){
	if($(this).attr('hasNextPage')=='true'){
		var nowPage=$('ul.pagination li.active a').attr('pageNum');
		window.location.href=document.URL.substring(0,1+document.URL.lastIndexOf('='))+(parseInt(nowPage)+1);
	}
});
$(document).on('click','#changeNum',function(){
	var nowPage=$('ul.pagination li.active a').attr('pageNum');
	var changeNum=$(this).attr('pageNum')
	if(nowPage!=changeNum){
		window.location.href=document.URL.substring(0,1+document.URL.lastIndexOf('='))+(parseInt(changeNum));
	}
});
</script>
</body>
</html>