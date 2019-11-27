<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>index</title>

    <script src="/static/js/jquery-3.0.0.js"></script>
    <script src="/static/bootstrap/js/bootstrap.js"></script>
<%--    rel = "stylesheet" 要加这个东西才会有效果--%>
    <link href="/static/bootstrap/css/bootstrap.css" rel="stylesheet">

    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
</head>
<body>
    index
<ul class="pagination">
    <c:forEach var="stu" items="${pageInfo.list}">
        <li>name:${stu.name},是不是男的:${stu.sex eq 男},age:${stu.age},date:${stu.date}</li>
    </c:forEach>
</ul>
<ul class="pagination">
    <li><a href="/index?pageNum=1">首页</a></li>
    <li><a href="/index?pageNum=${pageInfo.prePage}">上一页</a></li>
    <c:forEach var="page" items="${pageInfo.navigatepageNums}">
        <li><a href="/index?pageNum=${page}">${page}</a></li>
    </c:forEach>
    <li><a href="/index?pageNum=${pageInfo.nextPage}">下一页</a></li>
    <li><a href="/index?pageNum=${pageInfo.pages}">尾页</a></li>
</ul>


<div id="root">
    <h1>我点尼玛的{{msg}}</h1>
    <h2>这难道是覆盖吗</h2>
    <button v-on:click="handleclick">点击我</button>
</div>

<script>
    new Vue({
        // 让vue接管页面上的哪一部分内容,哪一个dom节点绑定
        el:"#root",
        //
        data:{
            msg:"hello world my Vue haah"
        },
        methods:{
            handleclick:function () {
                alert("弹出提示")
            }
        }

    })
</script>

</body>
</html>
