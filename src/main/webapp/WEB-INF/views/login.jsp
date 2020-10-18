<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8"
%>
<jsp:include page="header.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${message != null}">
  <div class="alert alert-danger" role="alert">
            ${message}
    </div>
</c:if>
<body class="text-center">
<form class="form-signin" action="/javaHome/auth/login" method="post">
    <img class="mb-4" src="https://getbootstrap.com/docs/4.0/assets/brand/bootstrap-solid.svg" alt="" width="72"
         height="72">
    <h1 class="h3 mb-3 font-weight-normal">Entrar</h1>
    <label for="email" class="sr-only">Email address</label>
    <input id="email" type="email" name="email" class="form-control" placeholder="Email address" required=""
           autofocus="">
    <label for="senha" class="sr-only">Password</label>
    <input id="senha" type="password" name="senha" class="form-control" placeholder="Password" required="">
    <div class="checkbox mb-3">
        <label>
            <input type="checkbox" value="remember-me"> Lembre de min
        </label>
    </div>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Entrar</button>
    <p class="mt-5 mb-3 text-muted">© 2017-2018</p>
</form>

<script src="<c:url value="/webjars/jquery/3.5.1/jquery.min.js"/>"></script>
<script src="<c:url value="/webjars/bootstrap/4.5.2/js/bootstrap.bundle.min.js"/>"></script>
<script src="<c:url value="/webjars/jquery-mask-plugin/1.14.15/src/jquery.mask.js"/>"></script>

</body>

</html>