<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>seckill detail page</title>
  <%@include file="common/head.jsp"%>
</head>
<body>
  <div class="container">
    <div class="panel panel-default text-center">
      <div class="panel-heading">
        <h1>${seckill.name}</h1>
      </div>
      <div class="panel-body">
        <h2 class="text-danger">
          <span class="glyphicon glyphicon-time"></span>
          <span class="glyphicon" id="seckill-box"></span>
        </h2>
      </div>
    </div>
  </div>

<div id="killPhoneModal" class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h3 class="modal-title text-center">
          <span class="glyphicon glyphicon-phone"></span>秒杀电话：
        </h3>
      </div>
      <div class="modal-body">
        <div class="row">
          <div class="col-xs-8 col-xs-offset-2">
            <input type="text" name="killPhone" id="killPhoneKey"
                    placeholder="输入手机号码" class="form-control"/>
          </div>
        </div>
      </div>

      <div class="modal-footer">
        <span id="killPhoneMessage" class="glyphicon"></span>
        <button type="button" id="killPhoneBtn" class="btn btn-success">
          <span class="glyphicon glyphicon-phone"></span>
          Submit
        </button>
      </div>
    </div>
  </div>
</div>

</body>


<!-- 包括所有已编译的插件 -->
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>

<script src="http://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>

<script src="http://cdn.bootcss.com/jquery.countdown/2.1.0/jquery.countdown.min.js"></script>

<script src="/resource/script/seckill.js" type="text/javascript"></script>

<script type="text/javascript">
  $(function(){
    seckill.detail.init({
      seckillId : ${seckill.seckillId},
      startTime : ${seckill.startTime.time},
      endTime : ${seckill.endTime.time}
    });
  });

</script>


</html>






























































