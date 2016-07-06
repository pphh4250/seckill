<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
  <title>seckill page</title>
  <%@include file="common/head.jsp"%>
</head>
<body>
    <div class="container">
      <div class="panel panel-default">
        <div class="panel-heading text-center">
          <h2>秒杀列表</h2>
        </div>
        <div class="panel-body">
          <table class="table table-hover">
            <thead>
              <tr>
                <th>名称</th>
                <th>库存</th>
                <th>开始时间</th>
                <th>结束时间</th>
                <th>创建时间</th>
                <th>详情</th>

              </tr>

            </thead>
            <tbody>
              <c:forEach var="sk" items="${list}">

              <tr>
                <td>${sk.name}</td>
                <td>${sk.number}</td>
                <td>
                  < fmt:formatDate value = "${sk.startTime}" pattern = "yyyy-MM-dd HH:mm:ss"/>
                </td>
                <td>
                  < fmt:formatDate value = "${sk.endTime}" pattern = "yyyy-MM-dd HH:mm:ss"/>
                </td>
                <td>
                  < fmt:formatDate value = "${sk.createTime}" pattern = "yyyy-MM-dd HH:mm:ss"/>
                </td>
                <td>
                  <a class="btn btn-info" href="/seckill/${sk.seckillId}/detail" target="_blank"/>
                </td>
              </tr>

              </c:forEach>

            </tbody>



          </table>
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

</html>






























































