
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
    <c:if test="${utente.getMagistrale()==null}">
        <form action="/UniNotes_war_exploded/Magistrale/magistrale" method="get">

<div class="container mt-sm-5 my-1">
    <br>
    <h1 style="color:#ff8400" >Compila il quiz per scoprire la magistrale più adatta a te!</h1>
    <br>
    <div class="question ml-sm-5 pl-sm-5 pt-2">
        <div class="py-2 h5"><b style="color:#ff8400" >Q1. Quanti anni hai ?</b></div><br>
        <div class="ml-md-3 ml-sm-3 pl-md-5 pt-sm-0 pt-3"> <label class="options">18 - 23<input value="0" type="radio" name="d1"> <span class="checkmark"></span> </label><br><br> <label class="options">24 - 30<input value="1" type="radio" name="d1"> <span class="checkmark"></span> </label><br><br> <label class="options">31 - 35<input value="2" type="radio" name="d1"> <span class="checkmark"></span> </label><br><br> <label class="options"> >35<input value="3" type="radio" name="d1"> <span class="checkmark"></span> </label><br><br> </div>
    </div>

</div>

    <div class="container mt-sm-5 my-1">
    <div class="question ml-sm-5 pl-sm-5 pt-2">
        <div class="py-2 h5"><b style="color:#ff8400">Q2. Quanto è importante per te  la conoscenza della lingua inglese ?</b></div><br>
        <div class="ml-md-3 ml-sm-3 pl-md-5 pt-sm-0 pt-3"> <label class="options">Per nulla<input value="0" type="radio" name="d2"> <span class="checkmark"></span> </label><br><br> <label class="options">Più no che si<input value="1" type="radio" name="d2"> <span class="checkmark"></span> </label><br><br>  <label class="options">Più si che no<input value="0" type="radio" name="d2"> <span class="checkmark"></span> </label><br><br> <label class="options">Moltissimo<input value="0" type="radio" name="d2"> <span class="checkmark"></span> </label><br><br> </div>
    </div>
    </div>

    <div class="container mt-sm-5 my-1">
    <div class="question ml-sm-5 pl-sm-5 pt-2">
        <div class="py-2 h5"><b style="color:#ff8400">Q3. Qual è il tuo livello della lingua inglese ?</b></div><br>
        <div class="ml-md-3 ml-sm-3 pl-md-5 pt-sm-0 pt-3"> <label class="options">Principiante (A1-A2)<input value="0" type="radio" name="d3"> <span class="checkmark"></span> </label><br><br> <label class="options">Intermedio (B1-B2)<input value="1" type="radio" name="d3"> <span class="checkmark"></span> </label><br><br> <label class="options">Avanzato (C1-C2)<input value="2" type="radio" name="d3"> <span class="checkmark"></span> </label><br><br> </div>
    </div>
    </div>

    <div class="container mt-sm-5 my-1">
    <div class="question ml-sm-5 pl-sm-5 pt-2">
        <div class="py-2 h5"><b style="color:#ff8400" >Q4. Quanto è importante per te lo sbocco lavorativo e la relativa retribuzione ?</b></div><br>
        <div class="ml-md-3 ml-sm-3 pl-md-5 pt-sm-0 pt-3"> <label class="options">Per nulla<input value="0" type="radio" name="d4"> <span class="checkmark"></span> </label><br><br> <label class="options">Più no che si<input value="1" type="radio" name="d4"> <span class="checkmark"></span> </label><br><br>  <label class="options">Più si che no<input value="2" type="radio" name="d4"> <span class="checkmark"></span> </label><br><br> <label class="options">Moltissimo<input value="3" type="radio" name="d4"> <span class="checkmark"></span> </label><br><br> </div>
    </div>
    </div>

    <div class="container mt-sm-5 my-1">
        <div class="question ml-sm-5 pl-sm-5 pt-2">
            <div class="py-2 h5"><b style="color:#ff8400" >Q5. Quali di questi corsi a scelta ti piace di piu' ?</b></div><br>
            <div class="ml-md-3 ml-sm-3 pl-md-5 pt-sm-0 pt-3">
                <label class="options">Calcolo Scientifico<input value="0" type="radio" name="d5"> <span class="checkmark"></span> </label><br><br>
                <label class="options">Fisica<input value="1" type="radio" name="d5"> <span class="checkmark"></span> </label><br><br>
                <label class="options">Fondamenti di Intelligenza Artificiale<input value="2" type="radio" name="d5"> <span class="checkmark"></span> </label><br><br>
                <label class="options">Grafica ed Interattività<input value="3" type="radio" name="d5"> <span class="checkmark"></span></label><br><br>
                <label class="options">Interazione Uomo-Macchina<input value="4" type="radio" name="d5"> <span class="checkmark"></span></label><br><br>
                <label class="options">Mobile Programming<input value="5" type="radio" name="d5"> <span class="checkmark"></span></label><br><br>
                <label class="options">Programmazione Avanzata<input value="6" type="radio" name="d5"> <span class="checkmark"></span></label><br><br>
                <label class="options">Sicurezza<input value="7" type="radio" name="d5"> <span class="checkmark"></span></label><br><br>
                <label class="options">Simulazione<input value="8" type="radio" name="d5"> <span class="checkmark"></span></label><br><br>
            </div>
        </div>
    </div>

    <div class="container mt-sm-5 my-1">
        <div class="question ml-sm-5 pl-sm-5 pt-2">
            <div class="py-2 h5"><b style="color:#ff8400" >Q6. E' stato l'esame dove hai conseguito uno dei voti più alti ?</b></div><br>
            <div class="ml-md-3 ml-sm-3 pl-md-5 pt-sm-0 pt-3">
                <label class="options">No<input value="0" type="radio" name="d6"> <span class="checkmark"></span> </label><br><br>
                <label class="options">Si<input value="1" type="radio" name="d6"> <span class="checkmark"></span> </label><br><br>
        </div>
    </div>
    </div>

    <div class="container mt-sm-5 my-1">
    <div class="question ml-sm-5 pl-sm-5 pt-2">
        <div class="py-2 h5"><b style="color:#ff8400" >Q7. Di tutti i progetti realizzati, il proggetto che ti è piaciuto di più di che argomento trattava ?</b></div><br>
        <label class="options">Cloud Computing<input value="0" type="radio" name="d7"> <span class="checkmark"></span> </label><br><br>
        <label class="options">Sicurezza Informatica<input value="1" type="radio" name="d7"> <span class="checkmark"></span> </label><br><br>
        <label class="options">Software Engineering and IT Management<input value="2" type="radio" name="d7"> <span class="checkmark"></span> </label><br><br>
        <label class="options">IoT<input value="3" type="radio" name="d7"> <span class="checkmark"></span></label><br><br>
        <label class="options">Data Science & Machine Learning<input value="4" type="radio" name="d7"> <span class="checkmark"></span></label><br><br>
        <label class="options">Nessuno dei progetti svolti mi ha incentivato a scegliere un determinato corso magistrale<input value="5" type="radio" name="d7"> <span class="checkmark"></span></label><br><br>
    </div>
    </div>




    <div class="container mt-sm-5 my-1">
        <div class="question ml-sm-5 pl-sm-5 pt-2">
            <div class="py-2 h5"><b style="color:#ff8400" >Q8. Su quale argomento pensi che si incentrerà la tua tesi ?</b></div><br>
            <label class="options">Cloud Computing<input value="0" type="radio" name="d8"> <span class="checkmark"></span> </label><br><br>
            <label class="options">Sicurezza Informatica<input value="1" type="radio" name="d8"> <span class="checkmark"></span> </label><br><br>
            <label class="options">Software Engineering and IT Management<input value="2" type="radio" name="d8"> <span class="checkmark"></span> </label><br><br>
            <label class="options">IoT<input value="3" type="radio" name="d8"> <span class="checkmark"></span></label><br><br>
            <label class="options">Data Science & Machine Learning<input value="4" type="radio" name="d8"> <span class="checkmark"></span></label><br><br>
        </div>
    </div>

    <div class="container mt-sm-5 my-1">
    <div class="d-flex align-items-center pt-3">
        <div class="ml-auto mr-sm-5"> <button style="background-color: #ff8400; border-bottom-color: white" class="btn btn-success">Calcola</button> </div>
    </div>
</div>

</form>
     </c:if>
        <c:if test="${utente.getMagistrale()!=null}">
        <form action="/UniNotes_war_exploded/Magistrale/reset" method="get">

            <div class="container mt-sm-5 my-1">
                <br>
                <h1 style="color:#ff8400" >La magistrale che ti consigliamo è: </h1>
                <br>

                <br>
                <h3 style="color:#ff8400">Predizione KMeans: ${utente.getMagistrale()}</h3>
                <br>


                <div class="container mt-sm-5 my-1">
                    <div class="d-flex align-items-center pt-3">
                        <div class="ml-auto mr-sm-5"> <button style="background-color: #ff8400; border-bottom-color: white" class="btn btn-success">Reset</button> </div>
                    </div>
                </div>

            </div>
        </form>
        </c:if>
<%@include file="../partials/footer.jsp"%>

</body>
</html>
