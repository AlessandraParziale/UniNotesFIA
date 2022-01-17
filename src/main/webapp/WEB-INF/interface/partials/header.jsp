<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="/UniNotes_war_exploded/Utente/home"><img src="../../../img/logo.png" width="100" height="100"></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/UniNotes_war_exploded/Utente/home">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/UniNotes_war_exploded/Corso/visualizzaTuttiUtente">Corsi</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/UniNotes_war_exploded/Utente/dashboard">Dashboard</a>
                </li>

                <c:if test="${!utente.isTipo()}">
                    <li class="nav-item">
                        <a class="nav-link" href="/UniNotes_war_exploded/Libretto/visualizzaLibretto">Libretto</a>
                    </li>
                </c:if>
                <c:if test="${!utente.isTipo()}">
                    <li class="nav-item">
                        <a class="nav-link" href="/UniNotes_war_exploded/ListaPreferiti/visualizza">Favorite List</a>
                    </li>
                </c:if>


            </ul>
                <h4 style="color:#dfdfdf;">Salve ${utente.getNome()}</h4>
        </div>
    </div>
</nav>