<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">

    <link rel="canonical" href="https://getbootstrap.com/docs/5.1/examples/dashboard/">

    <jsp:include page="/WEB-INF/interface/partials/head.jsp">
        <jsp:param name="title" value="UniNotes-Predizione"/>
        <jsp:param name="style" value="quiz"/>
        <jsp:param name="script" value=""/>
    </jsp:include>

    <!-- Bootstrap core CSS
    <link href="/docs/5.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
-->
    <!-- Favicons -->
    <link rel="apple-touch-icon" href="/docs/5.1/assets/img/favicons/apple-touch-icon.png" sizes="180x180">
    <link rel="icon" href="/docs/5.1/assets/img/favicons/favicon-32x32.png" sizes="32x32" type="image/png">
    <link rel="icon" href="/docs/5.1/assets/img/favicons/favicon-16x16.png" sizes="16x16" type="image/png">
    <link rel="manifest" href="/docs/5.1/assets/img/favicons/manifest.json">
    <link rel="mask-icon" href="/docs/5.1/assets/img/favicons/safari-pinned-tab.svg" color="#7952b3">
    <link rel="icon" href="/docs/5.1/assets/img/favicons/favicon.ico">
    <meta name="theme-color" content="#7952b3">


    <!-- Custom styles for this template -->
    <link href="dashboard.css" rel="stylesheet">
</head>


<body style="background-color: white">

    <%@include file="/WEB-INF/interface/partials/header.jsp"%>



<form action="/UniNotes_war_exploded/Magistrale/reset" method="get">

    <div class="container mt-sm-5 my-1">
        <br>
        <h1 style="color:#ff8400" >La magistrale che ti consigliamo è: </h1>
        <br>

        <br>
        <h3 style="color:#ff8400">Predizione KMeans: ${predizione.get(0)}</h3>
        <br>

        <br>
        <h3 style="color:#ff8400">Predizione Decision Tree: ${predizione.get(1)}</h3>
        <br>

        <div class="container mt-sm-5 my-1">
            <div class="d-flex align-items-center pt-3">
                <div class="ml-auto mr-sm-5"> <button style="background-color: #ff8400; border-bottom-color: white" class="btn btn-success">Reset</button> </div>
            </div>
        </div>

    </div>
</form>

<%@include file="../partials/footer.jsp"%>
</body>


</html>
