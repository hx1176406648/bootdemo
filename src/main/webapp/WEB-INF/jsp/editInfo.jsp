<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="css/bootstrap/3.3.6/bootstrap.css">



<form method="post" action="../item" style="left: 30%;position: relative;width: 50%">
    <input type="hidden" name="_method" value="PUT">
    <input type="hidden" name="id" value="${item.id}">
    名字:<input name="name" class="form-control" type="text" value="${item.name}">
    价格:<input name="price" class="form-control" type="text" value="${item.price}">
    数量:<input name="count" class="form-control" type="text" value="${item.count}">
    最大数量:<input name="maxLimit" class="form-control" type="text" value="${item.maxLimit}"><br>
    <button type="submit" class="btn btn-primary">更改</button>
</form>