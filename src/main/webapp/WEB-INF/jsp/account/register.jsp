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
	<link href="/static/css/regist.css" rel="stylesheet">
	<script type="text/javascript" src="/static/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="/static/js/AllSchool.js"></script>
	<style>
     body{
         background-color: aliceblue;
     }
     select{
         width: 100%;
         height: 30px;
         border-radius: 5px;
         border-color: aliceblue;
         font-size: 14px;
         letter-spacing: 5px;
     }
     .content{
         width: 300px;
         margin: 5px auto;
     }
     .rows{
         width: 100%;
         height: 42px;
         border-radius: 14px;
         margin-top: 10px;
         background-color: #ffffff;
     }
     .label{
         display: inline-block;
         padding: 10px;
         color: #03A9F4;
         letter-spacing: 7px;
     }
     .text {
         display: inline-block;
         width: 70%;
         color: #3a3838;
     }
 </style>
</head>
<body>

<div class="regist">
	<form action="#" id="formReg">
		<div style="margin-top: 10px;margin-left: 10px;">
            <div style="float: left;margin-bottom: 10px">申请类型:</div>
            <div style="float:left;margin-left: 100px;margin-bottom: 10px">
                个人<input type="radio" name="type" value="1" checked="checked" class="type">
             </div>
            <div style="float: right;margin-right: 100px;margin-bottom: 10px">
                公司<input type="radio" name="type" value="2"  class="type">
            </div>
        </div>
        <!--<label for="name">用户名:</label>-->
        <input id="username" type="text" class="form-control" name="username" style="height: 40px; margin-top: 20px;"placeholder="请输入用户名"><div id="error1"></div>
        <!--<label for="name"></label>-->
        <input id="password" type="password" class="form-control" name="password" style="height: 40px;margin-top: 20px;"placeholder="请输入密码"><div id="error2"></div>
        <input id="confirm_password" type="password" class="form-control" name="password" style="height: 40px;margin-top: 20px;"placeholder="请重复密码"><div id="error3"></div>
        
        <input id="name" type="text" class="form-control" name="name" style="height: 40px; margin-top: 20px;"placeholder="请输入您的公司名"><div id="error10"></div>
        <input id="idnum" type="text" class="form-control" name="idnum" style="height: 40px; margin-top: 20px;" placeholder="请输入公司营业执照号" ><div id="error11"></div><!-- onpaste="return false;" -->
        
        <input id="realname" type="text" class="form-control" name="realname" style="height: 40px; margin-top: 20px;"placeholder="请输入您的真实姓名"><div id="error6"></div>
        <input id="idCard" type="text" class="form-control" name="idcard" style="height: 40px; margin-top: 20px;" placeholder="请输入您的身份证号"><div id="error4"></div><!--  onpaste="return false;" -->
        <input id="email" type="email" class="form-control" name="email" style="height: 40px; margin-top: 20px;"placeholder="请输入邮箱"><div id="error5"></div>
        <div style="margin-top: 10px;margin-left: 10px;">
            <div style="float: left;margin-bottom: 10px">性别:</div>
            <div style="float:left;margin-left: 60px;margin-bottom: 10px">
                男<input type="radio" name="sex" value="男" class="sex">
             </div>
            <div style="float: right;margin-right: 60px;margin-bottom: 10px">
                保密<input type="radio" name="sex" value="保密" checked="checked" class="sex">
            </div>
            <div style="float: right;margin-right: 60px;margin-bottom: 10px">
                女<input type="radio" name="sex" value="女" class="sex">
            </div>
        </div><div id="error7"></div>
        <input id="address" type="text" class="form-control" name="address" style="height: 40px;margin-top: 20px;"
               placeholder="请输入现住址"><div id="error8"></div>
        <input id="hobby" type="text" class="form-control" name="hobby" style="height: 40px;margin-top: 20px;"
               placeholder="请输入爱好"><div id="error9"></div>
	     <div class="content">
               选择所在学校:
		    <div class="rows">
		        <div class="label">省份</div><div style="padding-left:0px;padding-right:5px;display:inline-block;color: #ccc;text-align: center;font-weight: lighter;">|</div>
		        <div class="text"><select id="province" name="province"></select></div>
		    </div>
	       <div class="rows">
	           <div class="label">城市</div><div style="padding-left:0px;padding-right:5px;display:inline-block;color: #ccc;text-align: center;font-weight: lighter;">|</div>
	           <div class="text"><select id="city" name="city"></select></div>
	       </div>
	       <div class="rows">
	           <div class="label">学校</div><div style="padding-left:0px;padding-right:5px;display:inline-block;color: #ccc;text-align: center;font-weight: lighter;">|</div>
	           <div class="text"><select id="school" name="school"></select></div>
	       </div>
	     </div>
	     <div id="models">
	     	
	     </div>
        <div style="height: 80px;width: 100%;margin-top: 20px;margin-left: 30px;">
            <!--<div style="float:left;width: 100%;padding: 20px;">-->
                <input type="button" class="btn btn-primary" value="注册"
                   style="margin:auto;width: 80%;height: 40px;padding: 10px;" id="zc"></input>
            <!--</div>-->


        </div>
    </form>
</div>
 
  <!-- <div class="bottom" style="position:absolute;bottom:0px;margin-top: 20px;background-color: rgba(0,0,0,0.8);width:100%;height: 100px;color: darkgray">
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
</div> -->

 
  <script type="text/javascript">
  var idnum=false;
  $(document).on('change','.type',function(){
	  if($(".type:checked").val()==1){
		  //$(".regist").css("height","840px");
		  $(".content").show();
		  $("#models").show();
		  $("#name").hide();
		  $("#idnum").hide();
		  $("#error10").hide();
		  $("#error11").hide();
	  }else{
		  //$(".regist").css("height","780px");
		  $(".content").hide();
		  $("#models").hide();
		  $("#name").show();
		  $("#idnum").show();
		  $("#error10").show();
		  $("#error11").show();
	  }
  });
  var ok1=false;
  var ok2=false;
  var ok4=false;
  var ok5=false;
  var ok6=false;
  var ok7=false;
  var ok8=false;
  var ok9=false;
  var ok10=false;
  var ok11=false;
  var ok12=false;
  
  $(document).on('click','#zc',function(){
	  yz1();
  });
  $(document).on('change','#formReg',function(){
	  yz2();
  });
	function yz1(){
	  
	  var type=$(".type:checked").val();
	  if(!($('#password').val().length<6||$('#password').val().length>16)){
		  ok6=true;
          $("#error2").text("").css('color','green');
	  }else{
		  ok6=false;
          $("#error2").text("密码不为空且6到16位").css('color','red');
	  }
	  if(($("#confirm_password").val()==$("#password").val())){
		  ok4=true;
          $("#error3").text("").css('color','green');
	  }else{
		  ok4=false;
          $("#error3").text("两次密码输入不一致").css('color','red');
	  }
	  if($("#username").val()!=""){
		  ok5=true;
          $("#error1").text("").css('color','green');
	  }else{
		  ok5=false;
          $("#error1").text("请输入用户名").css('color','red');
	  }
	  if($("#realname").val()!=""){
		  ok7=true;
          $("#error6").text("").css('color','green');
	  }else{
		  ok7=false;
          $("#error6").text("请输入真实姓名").css('color','red');
	  }
	  if($("#idCard").val()!=""){
		  ok8=true;
          $("#error4").text("").css('color','green');
	  }else{
		  ok8=false;
          $("#error4").text("请输入身份证号").css('color','red');
	  }
	  if($("#email").val()!=""){
		  ok9=true;
          $("#error5").text("").css('color','green');
	  }else{
		  ok9=false;
          $("#error5").text("请输入邮箱").css('color','red');
	  }
	  if($("#address").val()!=""){
		  ok10=true;
          $("#error8").text("").css('color','green');
	  }else{
		  ok10=false;
          $("#error8").text("请输入现住址").css('color','red');
	  }
	  if(type==2){
		  if($("#name").val()!=""){
			  ok11=true;
	          $("#error10").text("").css('color','green');
		  }else{
			  ok11=false;
	          $("#error10").text("请输入公司名").css('color','red');
		  }
		  if($("#idnum").val()!=""){
			  ok12=true;
	          $("#error11").text("").css('color','green');
		  }else{
			  ok12=false;
	          $("#error11").text("请输入营业执照号").css('color','red');
		  }
	  }
	  if(ok5){
		  $.ajax({
	          type:"POST",
	          url:"/registryVerification",
	          data:{"accName":$('#username').val()},
	          async: false,
	          success:function(data){
	          	console.log(data);
	          	if(data.code==100)
	          	{
	          		ok1=true;
		            $('#error1').text('').css('color','green');
	          	}else{
	          		ok1=false;
	          		$('#'+data.extend.local+'').text(data.extend.error).css('color','red');
	          	}
	          },
	          error:function(e){
	              alert("对不起，服务器繁忙,请稍后重试！");                   
	          }
	      });
	  }
	  if(ok8){
		  $.ajax({
	          type:"POST",
	          url:"/registryVerification",
	          data:{"registerIDNum":$('#idCard').val()},
	          async: false,
	          success:function(data){
	          	console.log(data);
	          	if(data.code=="100")
	          	{
	          		ok8=true;
		                $('#error11').text('').css('color','green');
	          	}else{
	          		ok8=false;
	          		$('#'+data.extend.local+'').text(data.extend.error).css('color','red');
	          		return ;
	          	}
	          },
	          error:function(e){
	              alert("对不起，服务器繁忙,请稍后重试！");
	              return ;
	          }
	      });
	  }
	  if(type==2&&ok12){
		  $.ajax({
	          type:"POST",
	          url:"/registryVerification",
	          data:{"accIDNum":$('#idnum').val()},
	          async: false,
	          success:function(data){
	          	console.log(data);
	          	if(data.code=="100")
	          	{
	          		ok2=true;
		                $('#error11').text('').css('color','green');
	          	}else{
	          		ok2=false;
	          		$('#'+data.extend.local+'').text(data.extend.error).css('color','red');
	          	}
	          },
	          error:function(e){
	              alert("对不起，服务器繁忙,请稍后重试！");                   
	          }
	      });
	  }
	  if(ok9){
		  $.ajax({
	          type:"POST",
	          url:"/registryVerification",
	          data:{"email":$('#email').val()},
	          async: false,
	          success:function(data){
	          	console.log(data);
	          	if(data.code=="100")
	          	{
		            $('#error5').text('').css('color','green');
	          		var ok=type==1?(idnum&&ok1&&ok4&&ok5&&ok6&&ok7&&ok8&&ok9&&ok10):(idnum&&ok1&&ok2&&ok4&&ok5&&ok6&&ok7&&ok8&&ok9&&ok10&&ok11&&ok12);
	          		if(ok){
		          		regeister();
	          		}
	          	}else{
	          		ok3=false;
	          		$('#'+data.extend.local+'').text(data.extend.error).css('color','red');
	          	}
	          },
	          error:function(e){
	              alert("对不起，服务器繁忙,请稍后重试！");                   
	          }
	      });
	  }
  };
	function yz2(){
	  
	  var type=$(".type:checked").val();
	  console.log(type);
	  if($("#username").val()!=""){
		  ok5=true;
          $("#error1").text("").css('color','green');
	  }else{
		  ok5=false;
          $("#error1").text("请输入用户名").css('color','red');
          return ;
	  }
	  if(ok5){
		  $.ajax({
	          type:"POST",
	          url:"/registryVerification",
	          data:{"accName":$('#username').val()},
	          async: false,
	          success:function(data){
	          	console.log(data);
	          	if(data.code==100)
	          	{
	          		ok1=true;
		            $('#error1').text('').css('color','green');
	          	}else{
	          		ok1=false;
	          		$('#'+data.extend.local+'').text(data.extend.error).css('color','red');
	          	}
	          },
	          error:function(e){
	              alert("对不起，服务器繁忙,请稍后重试！");
	          }
	      });
	  }
	  if(!ok1){return ;}
	  if(!($('#password').val().length<6||$('#password').val().length>16)){
		  ok6=true;
          $("#error2").text("").css('color','green');
	  }else{
		  ok6=false;
          $("#error2").text("密码不为空且6到16位").css('color','red');
          return ;
	  }
	  if(($("#confirm_password").val()==$("#password").val())){
		  ok4=true;
          $("#error3").text("").css('color','green');
	  }else{
		  ok4=false;
          $("#error3").text("两次密码输入不一致").css('color','red');
          return ;
	  }
	  console.log(type==2);
	  if(type==2){
		  console.log("为什么");
		  if($("#name").val()!=""){
			  ok11=true;
	          $("#error10").text("").css('color','green');
		  }else{
			  ok11=false;
	          $("#error10").text("请输入公司名").css('color','red');
	          return ;
		  }
		  if($("#idnum").val()!=""){
			  ok12=true;
	          $("#error11").text("").css('color','green');
		  }else{
			  ok12=false;
	          $("#error11").text("请输入营业执照号").css('color','red');
	          return ;
		  }
		  if(ok12){
			  $.ajax({
		          type:"POST",
		          url:"/registryVerification",
		          data:{"accIDNum":$('#idnum').val()},
		          async: false,
		          success:function(data){
		          	console.log(data);
		          	if(data.code=="100")
		          	{
		          		ok2=true;
			                $('#error11').text('').css('color','green');
		          	}else{
		          		ok2=false;
		          		$('#'+data.extend.local+'').text(data.extend.error).css('color','red');
		          	}
		          },
		          error:function(e){
		              alert("对不起，服务器繁忙,请稍后重试！");
		          }
		      });
		  }
		  if(!ok2){return ;}
	  }
	  if($("#realname").val()!=""){
		  ok7=true;
          $("#error6").text("").css('color','green');
	  }else{
		  ok7=false;
          $("#error6").text("请输入真实姓名").css('color','red');
          return ;
	  }
	  if($("#idCard").val()!=""){
		  ok8=true;
          $("#error4").text("").css('color','green');
	  }else{
		  ok8=false;
          $("#error4").text("请输入身份证号").css('color','red');
          return ;
	  }
	  if(ok8){
		  $.ajax({
	          type:"POST",
	          url:"/registryVerification",
	          data:{"registerIDNum":$('#idCard').val()},
	          async: false,
	          success:function(data){
	          	console.log(data);
	          	if(data.code=="100")
	          	{
	          		ok8=true;
		                $('#error11').text('').css('color','green');
	          	}else{
	          		ok8=false;
	          		$('#'+data.extend.local+'').text(data.extend.error).css('color','red');
	          	}
	          },
	          error:function(e){
	              alert("对不起，服务器繁忙,请稍后重试！");
	          }
	      });
	  }
	  if(!ok8){return ;}
	  if($("#email").val()!=""){
		  ok9=true;
          $("#error5").text("").css('color','green');
	  }else{
		  ok9=false;
          $("#error5").text("请输入邮箱").css('color','red');
          return ;
	  }
	  if(ok9){
		  $.ajax({
	          type:"POST",
	          url:"/registryVerification",
	          data:{"email":$('#email').val()},
	          async: false,
	          success:function(data){
	          	console.log(data);
	          	if(data.code=="100")
	          	{
		            $('#error5').text('').css('color','green');
	          		/* var ok=type==1?(idnum&&ok1&&ok4&&ok5&&ok6&&ok7&&ok8&&ok9&&ok10):(idnum&&ok1&&ok2&&ok4&&ok5&&ok6&&ok7&&ok8&&ok9&&ok10&&ok11&&ok12);
	          		if(ok){
		          		regeister();
	          		} */
	          	}else{
	          		ok3=false;
	          		$('#'+data.extend.local+'').text(data.extend.error).css('color','red');
	          	}
	          },
	          error:function(e){
	              alert("对不起，服务器繁忙,请稍后重试！");
	          }
	      });
	  }
	  if(!ok9){return ;}
	  if($("#address").val()!=""){
		  ok10=true;
          $("#error8").text("").css('color','green');
	  }else{
		  ok10=false;
          $("#error8").text("请输入现住址").css('color','red');
          return ;
	  }
  };
  
  function regeister(){
	  var type=$(".type:checked").val();
	  var models="";
	  $("input[name='mid']:checked").each(function(){ 
		  models+= $(this).val()+",";
	  });
	  $.ajax({
          type:"POST",
          url:"/registry",
          data:{"accName":$('#username').val(),"realPassword":$('#password').val(),"accRealName":type==1?$('#realname').val():$('#name').val(),"accIDNum":type==1?$('#idCard').val():$('#idnum').val(),
        		  "registerName":$('#realname').val(),"registerIDNum":$('#idCard').val(),"accSex":$(".sex:checked").val(),"hobby":$('#hobby').val(),
        		  "address":$('#address').val(),"role":type==1?"student":"company","email":$('#email').val(),"schoolPro":type==1?$('#province').val():null,
        		  "schoolCity":type==1?$('#city').val():null,"schoolName":type==1?$('#school').val():null,"models":type==1?models:null},
          
          success:function(data){
          	console.log(data);
          	if(data.code=="100")
          	{
          		window.location.href="/index";
          	}else if(data.code=="200"){
          		var error=data.extend.errorInfo;
          		/* if(error.accName!=undefined){
          			$("#error1").text(error.accName).css('color','red');
          		} */
          		if(error.email!=undefined){
          			$("#error5").text(error.email).css('color','red');
          		}
          	}else if(data.code=="300"){
          		alert("对不起，服务器繁忙,请稍后重试！");
          	}
          },
          error:function(e){
              alert("对不起，服务器繁忙,请稍后重试！");                   
          }
      });
  }
  
  $(function (){
	    var inp = $('#idCard'), //输入框
	        tips = $('#error4');  //提示文本框
	    inp.on({
	        keyup : function () {
	            var val = $(this).val();
	            if(checkIdcard(val)){
	            	$.ajax({
	    	            type:"POST",
	    	            url:"/registryVerification",
	    	            data:{"registerIDNum":val},
	    	            success:function(data){
	    	            	console.log(data);
	    	            	if(data.code=="100")
	    	            	{
	    	            		idnum=true;
	    		                tips.text('格式正确').css('color','green');
	    	            	}else{
	    	            		idnum=false;
	    	            		tips.text(data.extend.error).css('color','red');
	    	            	}
	    	            },
	    	            error:function(e){
	    	                alert("对不起，服务器繁忙,请稍后重试！");                   
	    	            }
	    	        });
	            }else{
	            	idnum=false;
	                tips.text('格式有误').css('color','red');
	            }
	        },
	        blur : function () {
	            var val = $(this).val();
	            if(val.length === 0){
	                tips.text('').css('color','#000000');
	            }
	        }
	    });

	    function checkIdcard(num){
	        num = num.toUpperCase();
	        //身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X。
	        if (!(/(^\d{15}$)|(^\d{17}([0-9]|X)$)/.test(num))) {
	            console.log('输入的身份证号长度不对，或者号码不符合规定！\n15位号码应全为数字，18位号码末位可以为数字或X。');
	            return false;
	        }
	        //校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
	        //下面分别分析出生日期和校验位
	        var len, re;
	        len = num.length;
	        if (len == 15)
	        {
	            re = new RegExp(/^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/);
	            var arrSplit = num.match(re);
	            //检查生日日期是否正确
	            var dtmBirth = new Date('19' + arrSplit[2] + '/' + arrSplit[3] + '/' + arrSplit[4]);
				console.log(dtmBirth+"----====----"+dtmBirth.getYear());
	            var bGoodDay;
	            bGoodDay = (dtmBirth.getYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4]));
	            console.log(bGoodDay);
				if (!bGoodDay) {
	                console.log('输入的身份证号里出生日期不对！');
	                return false;
	            } else {
	                //将15位身份证转成18位
	                //校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
	                var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
	                var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
	                var nTemp = 0, i;
	                num = num.substr(0, 6) + '19' + num.substr(6, num.length - 6);
	                for (i = 0; i < 17; i ++) {
	                     nTemp += num.substr(i, 1) * arrInt[i];
	                }
	                num += arrCh[nTemp % 11];
	                return true;
	            }
	        }
	        if (len == 18) {
	            re = new RegExp(/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/);
	            var arrSplit = num.match(re);
	            //检查生日日期是否正确
	            var dtmBirth = new Date(arrSplit[2] + "/" + arrSplit[3] + "/" + arrSplit[4]);
	            console.log(dtmBirth+"----====----"+dtmBirth.getYear());
				console.log(new Date().getYear());
				if(dtmBirth>new Date()){
					console.log("时间超过当前时间");
					return false;
				}
				var bGoodDay;
	            bGoodDay = (dtmBirth.getFullYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4]));
	            console.log(bGoodDay);
				if (!bGoodDay) {
	                // console.log('输入的身份证号里出生日期不对！');
	                return false;
	            } else {
	                //检验18位身份证的校验码是否正确。
	                //校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
	                var valnum;
	                var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
	                var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
	                var nTemp = 0, i;
	                for(i = 0; i < 17; i ++)
	                {
	                    nTemp += num.substr(i, 1) * arrInt[i];
	                }
	                valnum = arrCh[nTemp % 11];
	                if (valnum != num.substr(17, 1)) {
	                    console.log('18位身份证的校验码不正确！应该为：' + valnum);
	                    return false;
	                }
	                return true;
	            }
	        }
	        return false;
	    }
	});
  //===================================
  window.onload=function(){
	  $("#name").hide();
	  $("#idnum").hide();
	  $("#error10").hide();
	  $("#error11").hide();
      var provinceArray = "";
      var provicneSelectStr = "";
      for(var i=0,len=province.length;i<len;i++){
          provinceArray = province[i];
          provicneSelectStr = provicneSelectStr + "<option value='"+provinceArray[0]+"'>"+provinceArray[1]+"</option>"
      }
      $("#province").html(provicneSelectStr);

      var selectCity = $("#province").val();
      var citylist=city[selectCity];
      var cityArray = "";
      var citySelectStr = "";
      for(var i=0,len=citylist.length;i<len;i++){
          cityArray = citylist[i];
          citySelectStr = citySelectStr + "<option value='"+cityArray[0]+"'>"+cityArray[1]+"</option>"
      }
      $("#city").html(citySelectStr);

      var selectschool = $("#city").val();
      var schoolUlStr = "";
      var schoolListStr = allschool[selectschool];
      for(var i=0,len=schoolListStr.length;i<len;i++){
          schoolUlStr = schoolUlStr + "<option value='"+schoolListStr[i][2]+"'>"+schoolListStr[i][2]+"</option>";
      }
      schoolUlStr = schoolUlStr + "<option value='999'>其它</option>";
      $("#school").html(schoolUlStr);
      //省切换事件
      $("#province").change(function(){
          var selectCity = $("#province").val();
          var citylist=city[selectCity];
          var cityArray = "";
          var citySelectStr = "";
          if(citylist!=null){
              for(var i=0,len=citylist.length;i<len;i++){
                  cityArray = citylist[i];
                  citySelectStr = citySelectStr + "<option value='"+cityArray[0]+"'>"+cityArray[1]+"</option>"
              }
          }

          $("#city").html(citySelectStr);
          $("#school1").show();
          $("#school2").hide();
          var selectschool = $("#city").val();
          var schoolUlStr = "";
          var schoolListStr = allschool[selectschool];
          for(var i=0,len=schoolListStr.length;i<len;i++){
              schoolUlStr = schoolUlStr + "<option >"+schoolListStr[i][2]+"</option>";
          }
          schoolUlStr = schoolUlStr + "<option value='999'>其它</option>";
          $("#school").html(schoolUlStr);
      });
      //切换城市事件
      $("#city").change(function(){
          $("#school1").show();
          $("#school2").hide();
          var selectschool = $("#city").val();
          var schoolUlStr = "";
          var schoolListStr = allschool[selectschool];
          for(var i=0,len=schoolListStr.length;i<len;i++){
              schoolUlStr = schoolUlStr + "<option >"+schoolListStr[i][2]+"</option>";
          }
          schoolUlStr = schoolUlStr + "<option value='999'>其它</option>";
          $("#school").html(schoolUlStr);
      });
      $("#school").change(function(){
          if($("#school").val()=="999"){
              $("#school1").hide();
              $("#school2").show();
          }
      });
      $("#second").show();
      $("#restart").css("backgorund","url('assets/images/chongxintijiao.jpg') no-repeat");
      
      $.ajax({
          type:"POST",
          url:"/bbs/getAllModel",
          data:{},
          success:function(data){
          	console.log(data);
          	if(data.code=="100")
          	{
          		if(data.extend.data.length>0){
          			$("#models").append("请勾选理想就业方向:");
          			$.each(data.extend.data,function(index,item){
          				$("#models").append("<input type='checkbox' name='mid' value='"+item.modelId+"'>"+item.modelName+"&nbsp\;&nbsp\;");
            		});
          		}
          	}else{
          		
          	}
          },
          error:function(e){
              alert("对不起，服务器繁忙,请稍后重试！");                   
          }
      });
  };
  </script>
  </body>
</html>
