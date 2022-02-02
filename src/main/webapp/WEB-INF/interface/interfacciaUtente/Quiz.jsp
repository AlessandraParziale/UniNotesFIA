
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">

    <link rel="canonical" href="https://getbootstrap.com/docs/5.1/examples/dashboard/">

    <jsp:include page="/WEB-INF/interface/partials/head.jsp">
        <jsp:param name="title" value="UniNotes-Quiz"/>
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

<main>
    <%@include file="/WEB-INF/interface/partials/header.jsp"%>

<form action="/UniNotes_war_exploded/MagistraleServlet" method="get">

<div class="container mt-sm-5 my-1">
    <br>
    <h1 style="color:#ff8400" >Compila il quiz per scoprire la magistrale più adatta a te!</h1>
    <br>
    <div class="question ml-sm-5 pl-sm-5 pt-2">
        <div class="py-2 h5"><b style="color:#ff8400" >Q1. Quanti anni hai ?</b></div><br>
        <div class="ml-md-3 ml-sm-3 pl-md-5 pt-sm-0 pt-3"> <label class="options">22 - 26<input value="0" type="radio" name="d1"> <span class="checkmark"></span> </label><br><br> <label class="options">27 - 31<input value="1" type="radio" name="d1"> <span class="checkmark"></span> </label><br><br> <label class="options">32 - 36<input value="2" type="radio" name="d1"> <span class="checkmark"></span> </label><br><br> <label class="options">36 - 40<input value="3" type="radio" name="d1"> <span class="checkmark"></span> </label><br><br> <label class="options"> > 40<input value="4" type="radio" name="d1"> <span class="checkmark"></span> </label><br><br></div>
    </div>

</div>

    <div class="container mt-sm-5 my-1">
    <div class="question ml-sm-5 pl-sm-5 pt-2">
        <div class="py-2 h5"><b style="color:#ff8400">Q2. Desideri allontanarti o restare nei pressi dell’università ?</b></div><br>
        <div class="ml-md-3 ml-sm-3 pl-md-5 pt-sm-0 pt-3"> <label class="options">Allontanarmi<input value="0" type="radio" name="d2"> <span class="checkmark"></span> </label><br><br> <label class="options">Restare nelle vicinanze<input value="1" type="radio" name="d2"> <span class="checkmark"></span> </label><br><br> </div>
    </div>
    </div>

    <div class="container mt-sm-5 my-1">
    <div class="question ml-sm-5 pl-sm-5 pt-2">
        <div class="py-2 h5"><b style="color:#ff8400">Q3. Perchè ?</b></div><br>
        <div class="ml-md-3 ml-sm-3 pl-md-5 pt-sm-0 pt-3"> <label class="options">Desidero restare vicino ai miei familiari e amici<input value="0" type="radio" name="d3"> <span class="checkmark"></span> </label><br><br> <label class="options">Desidero allontanarmi per conoscere un nuovo ambiente<input value="1" type="radio" name="d3"> <span class="checkmark"></span> </label><br><br> <label class="options">Ho preso questa decisione con il mio gruppo di amici<input value="2" type="radio" name="d3"> <span class="checkmark"></span> </label><br><br> </div>
    </div>
    </div>

    <div class="container mt-sm-5 my-1">
    <div class="question ml-sm-5 pl-sm-5 pt-2">
        <div class="py-2 h5"><b style="color:#ff8400" >Q4. Quanto i progetti organizzati dall’università di Salerno stanno influenzando il tuo percorso ?</b></div><br>
        <div class="ml-md-3 ml-sm-3 pl-md-5 pt-sm-0 pt-3"> <label class="options">0 (Per nulla)<input value="0" type="radio" name="d4"> <span class="checkmark"></span> </label><br><br> <label class="options">1 (più no che si)<input value="1" type="radio" name="d4"> <span class="checkmark"></span> </label><br><br> <label class="options">2 (Più si che no)<input value="2" type="radio" name="d4"> <span class="checkmark"></span> </label><br><br> <label class="options">3 (Moltissimo)<input value="3" type="radio" name="d4"> <span class="checkmark"></span> </label><br><br> </div>
    </div>
    </div>

    <div class="container mt-sm-5 my-1">
    <div class="question ml-sm-5 pl-sm-5 pt-2">
        <div class="py-2 h5"><b style="color:#ff8400" >Q5. Di che argomento trattava il progetto che sta influenzando il tuo percorso  di più ?</b></div><br>
        <div class="ml-md-3 ml-sm-3 pl-md-5 pt-sm-0 pt-3"> <label class="options">Cloud Computing<input value="0" type="radio" name="d5"> <span class="checkmark"></span> </label><br><br> <label class="options">Sicurezza Informatica<input value="1" type="radio" name="d5"> <span class="checkmark"></span> </label><br><br> <label class="options">Software Engineering and IT Management<input value="2" type="radio" name="d5"> <span class="checkmark"></span> </label><br><br> <label class="options">IoT<input value="3" type="radio" name="d5"> <span class="checkmark"></span> </label><br><br> <label class="options"> Data Science & Machine Learning <input value="4" type="radio" name="d5"> <span class="checkmark"></span> </label> <br><br> <label class="options"> Nessuno dei progetti svolti sta influenzando il tuo percorso<input value="5" type="radio" name="d5"> <span class="checkmark"></span> </label><br><br> </div>
    </div>
    </div>



    <div class="container mt-sm-5 my-1">
    <div class="d-flex align-items-center pt-3">
        <div class="ml-auto mr-sm-5"> <button style="background-color: #ff8400; border-bottom-color: white" class="btn btn-success">Calcola</button> </div>
    </div>
</div>
</form>

<%@include file="../partials/footer.jsp"%>

</body>
</html>
