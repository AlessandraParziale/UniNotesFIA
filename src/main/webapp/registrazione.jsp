<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>

    <!-- basic -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- mobile metas -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">

    <jsp:include page="/WEB-INF/interface/partials/head.jsp">
        <jsp:param name="title" value="UniNotes-Home"/>
        <jsp:param name="style" value="A.style.css.pagespeed.cf.eQk9-CoeFP"/>
        <jsp:param name="script" value=""/>
    </jsp:include>

    <title>Login</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/A.style.css.pagespeed.cf.eQk9-CoeFP.css">
    <script>(function(w,d){!function(e,t,r,a,s){e[r]=e[r]||{},e[r].executed=[],e.zaraz={deferred:[]};var n=t.getElementsByTagName("title")[0];e[r].c=t.cookie,n&&(e[r].t=t.getElementsByTagName("title")[0].text),e[r].w=e.screen.width,e[r].h=e.screen.height,e[r].j=e.innerHeight,e[r].e=e.innerWidth,e[r].l=e.location.href,e[r].r=t.referrer,e[r].k=e.screen.colorDepth,e[r].n=t.characterSet,e[r].o=(new Date).getTimezoneOffset(),//
        e[s]=e[s]||[],e.zaraz._preTrack=[],e.zaraz.track=(t,r)=>e.zaraz._preTrack.push([t,r]),e[s].push({"zaraz.start":(new Date).getTime()});var i=t.getElementsByTagName(a)[0],o=t.createElement(a);o.defer=!0,o.src="/cdn-cgi/zaraz/s.js?"+new URLSearchParams(e[r]).toString(),i.parentNode.insertBefore(o,i)}(w,d,"zarazData","script","dataLayer");})(window,document);</script></head>


<style>

    alert{
        border-radius: 20px;
    }

</style>

<body body class="img js-fullheight">

<main>


    <section class="ftco-section">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-6 text-center mb-5">
                    <h1 class="heading-section" style="color: #e1853f"><strong> <em>Benvenuto su UniNotes! </em></strong></h1>
                </div>
            </div>
            <div class="row justify-content-center">
                <div class="col-md-6 text-center mb-5">
                    <h2 class="heading-section"></h2>
                </div>
            </div>
            <div class="row justify-content-center">
                <div class="col-md-6 col-lg-4">
                    <div class="login-wrap p-0">


                        <h3 class="mb-4 text-center">Registrati</h3>

                        <c:if test="${errors != null}">
                            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                                <c:forEach items="${errors}" var="e">
                                    <p><strong>Error: </strong>${e}</p>
                                </c:forEach>
                                ${errors = null}
                                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                            </div>
                        </c:if>


                        <form action="/UniNotes_war_exploded/Utente/registrazione" method="post" class="signin-form">
                            <div class="form-group">
                                <input name="Nome" type="text" class="form-control" placeholder="Nome" value="${Nome}" required >
                            </div>

                            <div class="form-group">
                                <input name="Cognome" type="text" class="form-control" placeholder="Cognome" value="${Cognome}" required>
                            </div>

                            <div class="form-group">
                                <input name="CF" type="text" class="form-control" placeholder="Codice fiscale" value="${CF}" required>
                            </div>

                            <div class="form-group">
                                <input name="DataDiNascita" type="text" class="form-control" placeholder="Data di nascita" value="${DataDiNascita}" required>
                            </div>

                            <div class="form-group">
                                <input name="Email" type="text" class="form-control" placeholder="E-mail" value="${Email}" required>
                            </div>

                            <div class="form-group">
                                <input name="Password" id="password-field" type="password" class="form-control" placeholder="Password" value="${Password}" required>
                                <span toggle="#password-field" class="fa fa-fw fa-eye field-icon toggle-password"></span>
                            </div>

                            <div class="form-group">
                                <input name="CPassword" id="password-confirm" type="password" class="form-control" placeholder="Conferma password" value="${CPassword}"  required>
                                <span toggle="#password-field" class="fa fa-fw fa-eye field-icon toggle-password"></span>
                            </div>


                            <div class="form-group">
                                <button type="submit" class="form-control btn btn-primary submit px-3">Sign In</button>
                            </div>

                            <p class="w-100 text-center">&mdash; Sei già registrato ad UniNotes? <a href="/UniNotes_war_exploded/Utente/login">Accedi !</a> -</p>

                        </form>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <script src="js/jquery.min.js"></script>
    <script src="js/popper.js+bootstrap.min.js+main.js.pagespeed.jc.9eD6_Mep8S.js"></script><script>eval(mod_pagespeed_T07FyiNNgg);</script>
    <script>eval(mod_pagespeed_zB8NXha7lA);</script>
    <script>eval(mod_pagespeed_xfgCyuItiV);</script>
    <script defer src="https://static.cloudflareinsights.com/beacon.min.js/v652eace1692a40cfa3763df669d7439c1639079717194" integrity="sha512-Gi7xpJR8tSkrpF7aordPZQlW2DLtzUlZcumS8dMQjwDHEnw9I7ZLyiOj/6tZStRBGtGgN6ceN6cMH8z7etPGlw==" data-cf-beacon='{"rayId":"6c6cbf48de1e3754","token":"cd0b4b3a733644fc843ef0b185f98241","version":"2021.12.0","si":100}' crossorigin="anonymous"></script>

</main>

</body>
</html>