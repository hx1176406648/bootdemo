<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>即时性格记录仪</title>
    <link rel="stylesheet" href="css/bootstrap/3.3.6/bootstrap.css">
    <script type="text/javascript" src="js/jquery/2.0.0/jquery.min.js"></script>
    <script>
        $(function () {
            /*$("#login").click(function () {
                var href="user/login";
                $("#formdelete").attr("action",href).submit();
                return false;
            })*/
        });

        function sendCode() {
            $.post("/bootdemo/sendCode?phone=" + $("#phone").val().trim(), function (res) {
                alert(res);
            });
        }
    </script>
</head>
<body>
<h1>sms消息测试</h1>

<p></p><br><br><br><br>

<table width="100%">
    <tr>
        <td width="40%">
            <form method="post" action="testSms" id="formdelete" style="width: 55%;text-indent: 10px">
                <p>我的名字: <input name="userName" style="width: 50%;display: inline-block" class="form-control"></p>
                <p>请输入手机号: <input id="phone" name="phone" style="width: 50%;display: inline-block"
                                  class="form-control"><span><a href="#" onclick="sendCode()">  发送验证码</a></span></p>
                <p>验证码: <input name="checkCode" style="width: 50%;display: inline-block" class="form-control"></p>
                &nbsp;&nbsp;&nbsp;<button type="submit" class="btn btn-primary" id="login">开始测试</button>
                <br>
            </form></td>
        <td>
            <table class="table table-hover">
                <thead>
                <tr>
                    <td>序号</td>
                    <td>名字</td>
                    <td>电话</td>
                    <td>性格</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${page.list}" var="it" varStatus="i">
                    <tr>
                        <td>${it.id}</td>
                        <td>${it.name}</td>
                        <td>${it.phone}</td>
                        <td>${it.character}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <nav>
                <ul class="pager">
                    <li><a href="?start=0">首页</a></li>
                    <c:if test="${page.pageNum==1}">
                        <li><a href="javascript:;" disabled="disabled">上一页</a></li>
                    </c:if>
                    <c:if test="${!(page.pageNum==1)}">
                        <li><a href="?start=${page.pageNum-1}">上一页</a></li>
                    </c:if>
                    <c:if test="${page.pageNum==page.pages}">
                        <li><a href="javascript:;" class="disabled">下一页</a></li>
                    </c:if>
                    <c:if test="${!(page.pageNum==page.pages)}">
                        <li><a href="?start=${page.pageNum+1}">下一页</a></li>
                    </c:if>
                    <li><a href="?start=${page.pages}">末页</a></li><span>   </span>
                    <li>当前第<c:out value="${page.pageNum}"/>页</li><span>  </span>
                    <li>总<c:out value="${page.pages}"/>页</li>
                </ul>
            </nav>
        </td>
    </tr>
</table>
</body>
</html>