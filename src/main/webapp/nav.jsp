<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- nav start -->
	<nav class="navbar navbar-inverse" role="navigation">
            <div class="navbar-header">
                <a class="navbar-brand" href="/">BBS求职论坛</a>
            </div>
            
		<div>
		  <ul class="nav navbar-nav">
			 <li><a href="/index">首页</a></li>
			 <li class="dropdown">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown">
				   精选板块 <b class="caret"></b>
				</a>
			   
				<ul class="dropdown-menu" id="jxmodel">
				   <!-- <li class="divider"></li>
				   <li><a href="#">板块1</a></li>
				   
				   <li class="divider"></li>
				   <li><a href="#">板块2</a></li>
				   
				   <li class="divider"></li>
				   <li><a href="#">板块3</a></li> -->
				</ul>
				
			 </li>
			  <li><a href="/getAllHotBbsPosts?pageNum=1">论坛热帖</a></li>
			  <li><a href="/getAllNewBbsPosts?pageNum=1">论坛新帖</a></li>
			  <li><a href="/getAllJhBbsPosts?pageNum=1">精华帖</a></li>
		  </ul>
		</div>
		<!-- if -->
		<c:choose>
			<c:when test="${USER_LOGIN_KEY.role eq 'admin'}">
				<ul class="nav navbar-nav navbar-right user">

					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							<b class="caret"></b>
						</a>
						<ul class="dropdown-menu">
							<li ><a href="/myFeatures">论坛管理</a></li>
							<!-- <li  ><a href="/manage/change-admin.jsp">资料修改</a></li>
							<li ><a href="/manage/newpost.jsp">查看新帖</a></li>
							<li><a href="/manage/bestpost.jsp">精华帖请求</a></li>
							<li><a href="/manage/limit.jsp">封锁用户</a></li>
							<li><a href="/manage/create_discuss.jsp">创建讨论区</a></li> -->
							<li class="divider"></li>
							<li><a href="#" id="logout">退出登陆</a></li>
						</ul>
					</li>
				</ul>
				<p class="navbar-text navbar-right">尊敬的管理员您好！&nbsp;${USER_LOGIN_KEY.accName}</p>
			</c:when>
			<c:when test="${USER_LOGIN_KEY.role eq 'student'}">
				<ul class="nav navbar-nav navbar-right user">
					
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							<b class="caret"></b>
						</a>
						<ul class="dropdown-menu">
							<li><a href="/myFeatures">我的权限</a></li>
							<li><a href="/bbs/toCreatePost">我要发帖</a></li>
							<!-- <li><a href="#">我的帖子</a></li>
							<li><a href="#">我要发帖</a></li> -->
							<li class="divider"></li>
							<li><a href="#" id="logout">退出登陆</a></li>
						</ul>
					</li>
				</ul>
				<p class="navbar-text navbar-right">尊敬的学生您好！&nbsp;${USER_LOGIN_KEY.accName}</p>
			</c:when>
			<c:when test="${USER_LOGIN_KEY.role eq 'company'}">
				<ul class="nav navbar-nav navbar-right user">

					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							<b class="caret"></b>
						</a>
						<ul class="dropdown-menu">
							<li><a href="/myFeatures">我的权限</a></li>
							<li><a href="/bbs/toCreatePost">我要发帖</a></li>
							<!-- <li><a href="#">我的帖子</a></li>
							<li><a href="#">我要发帖</a></li> -->
							<li class="divider"></li>
							<li><a href="#" id="logout">退出登陆</a></li>
						</ul>
					</li>
				</ul>
				<p class="navbar-text navbar-right">尊敬的公司您好！&nbsp;${USER_LOGIN_KEY.accName}</p>
			</c:when>
			<c:otherwise>
				<ul class="nav navbar-nav navbar-right user">
					<li><a href="/toLogin">登陆</a></li>
					<li><a href="/toRegist">注册</a></li>
				</ul>
				<p class="navbar-text navbar-right">尊敬的游客您好！</p>
			</c:otherwise>
		</c:choose>
		 <!-- end if -->
		 <form class="navbar-form navbar-right" role="search" action="#" onsubmit="return false;">
			<div class="input-group">
				<input id="search" type="text" class="form-control" name="keywords" placeholder="搜索您想要的帖子">
				<span class="input-group-addon" id="goSear"><span class="glyphicon glyphicon-search" id="goSear"></span> </span>
			</div>
		</form>
	</nav>
	<!-- nav end -->
<script type="text/javascript">
  	$(document).on('click',"#goSear",function(){
  		window.location.href="/search?word="+$('#search').val()+"&pageNum=1";
  	});
  	$(document).on('click',"#logout",function(){
  		$.ajax({
            type:"POST",
            url:"/logout",
            data:{},
            success:function(data){
            	//console.log(data);
            	if(data.code=="100")
            	{
            		//window.location.href="/index";
            		window.location.reload();
            	}else{
            		
            	}
            },
            error:function(e){
                alert("对不起，服务器繁忙,请稍后重试！");                   
            }
        });
  	})
  	window.onload=function(){
  		$.ajax({
            type:"POST",
            url:"/getJxModelList",
            data:{},
            success:function(data){
            	//console.log(data);
            	if(data.code=="100")
            	{
            		$(data.extend.jxModels).each(function(index,item) { 
            			$('#jxmodel').append('<li class="divider"></li><li><a href="/getPostByModel?modelId='+this.modelId+'&pageNum=1">'+this.modelName+'</a></li>');
            		});
            	}else{
            		
            	}
            },
            error:function(e){
                alert("对不起，服务器繁忙,请稍后重试！");                   
            }
        });
  	}
  </script>