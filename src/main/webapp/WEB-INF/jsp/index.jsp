<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="css/bootstrap/3.3.6/bootstrap.css">
<script type="text/javascript" src="js/jquery/2.0.0/jquery.min.js"></script>
<script>
    $(function () {
        $("#delete").click(function () {
            var href = $(this).attr("href");
            $("#formForDelete").attr("action",href).submit();
            return false;
        });
    });
</script>

<h3 style="left: 46%;position: relative">数据处理</h3><br>

<table class="table table-hover">
    <thead>
    <tr>
        <td>序号</td>
        <td>名字</td>
        <td>价格</td>
        <td>数量</td>
        <td>最大数量</td>
        <td></td>
        <td></td>
    </tr>
    </thead>
    <tbody>
        <c:forEach items="${page.list}" var="it" varStatus="i">
            <tr>
                <td>${i.count + (page.pageNum - 1)* limit}</td>
                <td>${it.name}</td>
                <td>${it.price}</td>
                <td>${it.count}</td>
                <td>${it.maxLimit}</td>
                <td><a href="item/${it.id}">编辑</a></td>
                <td><a href="item/${it.id}" id="delete">删除</a></td>
            </tr>
        </c:forEach>
    </tbody>
</table><br>

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

<h3>添加新信息</h3>
<form method="post" action="item" style="left: 30%;position: relative;width: 50%">
    名字:<input name="name" class="form-control" type="text">
    价格:<input name="price" class="form-control" type="text">
    数量:<input name="count" class="form-control" type="text">
    最大数量:<input name="maxLimit" class="form-control" type="text"><br>
    <button type="submit" class="btn btn-primary" id="add">提交</button>
</form>

<form method="post" action="" id="formForDelete">
    <input type="hidden" name="_method" value="delete">
</form>