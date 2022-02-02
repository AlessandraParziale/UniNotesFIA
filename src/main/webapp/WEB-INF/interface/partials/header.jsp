<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="/UniNotes_war_exploded/Utente/home"><img src="../img/logo.png" width="100" height="100"></a>
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

                <li class="nav-item">
                    <a class="nav-link" href="/UniNotes_war_exploded/Libretto/visualizzaLibretto">Libretto</a>
                </li>

                <li class="nav-item">
                        <a class="nav-link" href="/UniNotes_war_exploded/ListaPreferiti/visualizza">Favorite List</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="/UniNotes_war_exploded/Utente/quiz">Quiz magistrale</a>
                </li>


            </ul>
        <ul style="padding-right: 30px">
            <ul class="dropdown">
                <a class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false" style="color: #c5c5c5">
                    Salve ${utente.getNome()}
                </a>
                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <li><a class="dropdown-item" href="/UniNotes_war_exploded/Utente/logout">Logout</a></li>
                    <li><a class="dropdown-item" href="/UniNotes_war_exploded/Utente/dashboard">Dashboard</a></li>
                </ul>
            </ul>
        </ul>

        </div>
    </div>
</nav>