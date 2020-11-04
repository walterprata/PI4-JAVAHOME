<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Java Home - Sua loja de jogos</title>
    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/webjars/bootstrap/4.5.2/css/bootstrap.min.css"/>" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="<c:url value="/css/shop-homepage.css"/>" rel="stylesheet">

</head>
<!-- Navigation -->
<header>
    <nav class="navbar navbar-expand-lg navbar-dark fixed-top bg-javahome-dark" style="background-color: black">
        <div class="container-fluid">
            <a class="navbar-brand" href="/">Java Home</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                    aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarResponsive">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="/">Home
                            <span class="sr-only">(current)</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Sobre</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Contato</a>
                    </li>
                    <c:if test="${sessionScope.get('cargo') == null}">
                        <li class="nav-item">
                            <a class="nav-link" href="/javaHome/login">Entrar</a>
                        </li>
                    </c:if>
                    <c:if test="${sessionScope.get('cargo') != null}">
                        <li class="nav-item">
                            <div class="dropdown">
                                <a class="nav-link" type="button" id="dropdownMenuButton"
                                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        ${sessionScope.get("nome")}
                                </a>
                                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                    <c:if test="${sessionScope.get('cargo') != 'Cliente'}">
                                        <a class="dropdown-item" href="/produto/cadastrar">Produtos</a>
                                        <c:if test="${sessionScope.get('cargo') != 'estoque' && sessionScope.get('cargo') != 'Cliente'}">
                                            <a class="dropdown-item" href="/javaHome/login/cadastrar">Usuários</a>
                                        </c:if>
                                    </c:if>
                                    <a class="dropdown-item" href="/javaHome/logon">Sair</a>
                                </div>
                            </div>
                        </li>
                    </c:if>
                </ul>
            </div>
        </div>
    </nav>
</header>
<body id="body">