package Application.Utente;

import Application.Corso.ServiceCorso.CorsoService;
import Application.Corso.ServiceCorso.CorsoServiceImpl;
import Application.Libretto.ServiceLibretto.LibrettoService;
import Application.Libretto.ServiceLibretto.LibrettoServiceImpl;
import Application.MaterialeDidattico.ServiceMaterialeDidattico.MaterialeDidatticoService;
import Application.MaterialeDidattico.ServiceMaterialeDidattico.MaterialeDidatticoServiceImpl;
import Application.Utente.ServiceUtente.UtenteService;
import Application.Utente.ServiceUtente.UtenteServiceImpl;
import Storage.Corso.CorsoBean;
import Storage.Libretto.LibrettoBean;
import Storage.MaterialeDidattico.MaterialeDidatticoBean;
import Storage.Utente.UtenteBean;
import Storage.Utente.UtenteDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;



//Manca lista preferiti

@WebServlet(name = "UtenteServlet", value = "/Utente/*")
public class UtenteServlet extends HttpServlet {

    private final UtenteService utenteService = new UtenteServiceImpl();
    private final LibrettoService librettoService = new LibrettoServiceImpl();
    private final CorsoService corsoService = new CorsoServiceImpl();
    private final MaterialeDidatticoService materialeService = new MaterialeDidatticoServiceImpl();
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = (request.getPathInfo() != null) ? request.getPathInfo() : "/";

        switch (path){
            case "/":{
                response.sendRedirect("/UniNotes_war_exploded/");
                break;
            }

            case "/home":{
                UtenteBean u = (UtenteBean) request.getSession().getAttribute("utente");
                if(u == null){
                    response.sendRedirect("/UniNotes_war_exploded/");
                    break;
                }
                request.getRequestDispatcher("/WEB-INF/interface/interfacciaUtente/home.jsp").forward(request,response);
                break;
            }

            case "/dashboard":{
                UtenteBean u = (UtenteBean) request.getSession().getAttribute("utente");
                if(u == null){
                    response.sendRedirect("/UniNotes_war_exploded/");
                    break;
                }
                if(u.isTipo()){
                   ArrayList<MaterialeDidatticoBean> materiale = materialeService.visualizzaTutti();
                   request.setAttribute("totMateriale",materiale.size());
                   ArrayList<UtenteBean> utenti = utenteService.visualizzaUtenti();
                   request.setAttribute("totUtenti",utenti.size());
                   ArrayList<CorsoBean> corsi = corsoService.visualizzaCorsi();
                   request.setAttribute("totCorsi",corsi.size());
                }
                u.setMateriale(materialeService.visualizzaMaterialeDiUnUtente(u.getIdUtente()));
                request.getSession().setAttribute("utente",u);
                request.getRequestDispatcher("/WEB-INF/interface/interfacciaUtente/dashboard/dashboard.jsp").forward(request,response);
                break;
            }

            case "/registrazione":{
                request.getRequestDispatcher("/registrazione.jsp").forward(request,response);
                break;
            }

            case "/login": {//login [adimn/studente]
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
            }

            case "/visualizzaUtenti":{ //visualizza utenti registrati [adimn]

                UtenteBean u = (UtenteBean) request.getSession().getAttribute("utente");
                if(u == null){
                    response.sendRedirect("/UniNotes_war_exploded/");
                    break;
                }
                ArrayList<UtenteBean> utenti = utenteService.visualizzaUtenti();
                UtenteBean ut = null;
                for(UtenteBean utente : utenti){
                    if(utente.getIdUtente() == u.getIdUtente())
                        ut = utente;
                }
                utenti.remove(ut);
                request.setAttribute("utenti", utenti);
                request.getRequestDispatcher("/WEB-INF/interface/interfacciaUtente/dashboard/utenti.jsp").forward(request, response);
                break;
            }

            case "/visualizzaPaginaPersonale":{ //visualizza utenti registrati [adimn]

                request.getRequestDispatcher("/WEB-INF/interface/interfacciaUtente/dashboard/paginaPersonale.jsp").forward(request, response);
                break;
            }

            case "/logout": {//logout [adimn/cliente]
                HttpSession session = request.getSession(false);
                session.removeAttribute("utente");
                session.invalidate();
                response.sendRedirect("/UniNotes_war_exploded/index.jsp");
                break;
            }

        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = (request.getPathInfo() != null) ? request.getPathInfo() : "/";

        switch (path){

            case "/registrazione":{ //login [adimn/studente/guest]

                UtenteBean u;

                String nomePattern = "[a-zA-Z\\s]+$";// pattern vecchio [A-Z a-z]
                String cognomePattern = "[a-zA-Z\\s]+$";// pattern vecchio [A-Z a-z]
                String cfPattern = "^([A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]$)"; // qui ho aggiunto delle patentesi in piu all'inizio e alla fine
                String emailPattern = "(^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$)"; //pattern vecchio ^[a-zA-Z0-9.!#$%&’*+/=?^_`{}~-]+@(?:[a-zA-Z0-9-]+\.)*$
                String dataPattern = ""; //rivedere
                String passwordPattern = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])\\w{6,}$"; // pattern vecchio [A-Za-z0-9.]&


                String nome = request.getParameter("Nome");
                String cognome = request.getParameter("Cognome");
                String cf = request.getParameter("CF");
                String email = request.getParameter("Email");
                String password = request.getParameter("Password");
                String confermaPassword = request.getParameter("CPassword");
                String data = request.getParameter("DataDiNascita");

                ArrayList<String> errors = new ArrayList<>();

                if(!email.matches(emailPattern)){
                    errors.add("Email non valida");
                }
                if(!nome.matches(nomePattern)){
                    errors.add("Il nome non deve contenere numeri");
                }
                if(!cognome.matches(cognomePattern) ){
                    errors.add("Il cognome non deve contenere numeri");
                }
                if(!cf.matches(cfPattern) ){
                    errors.add("Il cf non è valido");
                }
                if(!password.matches(passwordPattern)){
                    errors.add("La password non rispetta il formato giusto");
                }
                if(!password.equals(confermaPassword)){
                    errors.add("Le due password non coincidono");
                }

              if (nome.matches(nomePattern) && cognome.matches(cognomePattern) && cf.matches(cfPattern) && email.matches(emailPattern) && password.matches(passwordPattern) && confermaPassword.matches(passwordPattern) && password.equals(confermaPassword)) {

                    try {
                        LibrettoBean l = librettoService.inserisciLibretto(0,0,0);
                        u = utenteService.registrazione(nome, cognome, cf, email, password, data,l);
                        request.getSession(false).invalidate();
                        HttpSession ssn = request.getSession(true);
                        ssn.setAttribute("utente", u);
                        ssn.setMaxInactiveInterval(86400);

                    } catch (SQLException | NoSuchAlgorithmException throwables) {
                        throwables.printStackTrace();
                    }
                    response.sendRedirect("/UniNotes_war_exploded/Utente/home");
                  break;
               }else {
                  request.setAttribute("errors",errors);
                  request.setAttribute("Nome",nome);
                  request.setAttribute("Cognome",cognome);
                  request.setAttribute("CF",cf);
                  request.setAttribute("Email",email);
                  request.setAttribute("DataDiNascita",data);
                  request.setAttribute("Password",password);
                  request.setAttribute("CPassword",confermaPassword);
                  request.getRequestDispatcher("/registrazione.jsp").forward(request,response);
                  break;
              }

            }

            case "/login" : {//rivedere
                UtenteBean utente = new UtenteBean();

                String emailPattern = "(^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$)"; //pattern vecchio ^[a-zA-Z0-9.!#$%&’*+/=?^_`{}~-]+@(?:[a-zA-Z0-9-]+\.)*$
                String passwordPattern = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])\\w{6,}$"; // pattern vecchio [A-Za-z0-9.]&



                String email = request.getParameter("email");
                String password = request.getParameter("password");
                request.setAttribute("errors",null);
                ArrayList<String> errors = new ArrayList<>();

                if (email.matches(emailPattern) && password.matches(passwordPattern)) {
                    utente.setEmail(email);
                    try {
                        utente.setPassword(password);
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }
                    utente = utenteService.login(email, utente.getPassword());
                    if(utente != null){
                        request.getSession(false).invalidate();
                        LibrettoBean librettoConIdUtente = librettoService.visualizzaLibrettoDiUtente(utente.getIdUtente());
                        utente.setLibretto(librettoService.visualizzaLibretto(librettoConIdUtente.getIdLibretto()));
                        HttpSession ssn = request.getSession(true);
                        ssn.setAttribute("utente", utente);
                        ssn.setMaxInactiveInterval(86400);
                        response.sendRedirect("/UniNotes_war_exploded/Utente/home");
                        break;
                    }else{
                        System.out.println("sono qui");
                        errors.add("Non c'è una corrispondenza per queste credenziali");
                        request.setAttribute("Email",email);
                        request.setAttribute("Password",password);
                        request.getSession().setAttribute("errors",errors);
                        response.sendRedirect("/UniNotes_war_exploded/");
                        break;
                    }


                }else{
                    errors.add("Email o password non validi");
                    request.setAttribute("Email",email);
                    request.setAttribute("Password",password);
                    request.getSession().setAttribute("errors",errors);
                    response.sendRedirect("/UniNotes_war_exploded/");

                    break;
                }
            }



            case "/modifica":{ //login [adimn/studente/guest]

                HttpSession ssn = request.getSession();

                UtenteBean u = (UtenteBean) ssn.getAttribute("utente");
                if(u == null){
                    response.sendRedirect("/UniNotes_war_exploded/");
                    break;
                }

                String nomePattern = "[a-zA-Z\\s]+$";// pattern vecchio [A-Z a-z]
                String cognomePattern = "[a-zA-Z\\s]+$";// pattern vecchio [A-Z a-z]
                String cfPattern = "(^[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]$)"; // qui ho aggiunto delle patentesi in piu all'inizio e alla fine
                String emailPattern = "(^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$)"; //pattern vecchio ^[a-zA-Z0-9.!#$%&’*+/=?^_`{}~-]+@(?:[a-zA-Z0-9-]+\.)*$

                String nome = request.getParameter("Nome");
                String cognome = request.getParameter("Cognome");
                String cf = request.getParameter("CF");
                String email = request.getParameter("Email");
                String data = request.getParameter("DataDiNascita");

                if (nome.matches(nomePattern) && cognome.matches(cognomePattern) && cf.matches(cfPattern) && email.matches(emailPattern)) {
                        u.setNome(nome);
                        u.setCognome(cognome);
                        u.setCf(cf);
                        u.setEmail(email);
                        u.setDdn(LocalDate.parse(data));
                        u = utenteService.aggiorna(u);
                        if(u != null){
                            ssn.setAttribute("utente", u);
                            response.sendRedirect("/UniNotes_war_exploded/Utente/visualizzaPaginaPersonale");
                            break;
                        }else{
                            response.sendError(400, "La modifica non è andata a buon fine");
                            break;
                        }
                }
                break;
            }
            case "/toggleRuolo" : { //modifica stato studente [adimn]

                UtenteDao ud = new UtenteDao();
                UtenteBean utente;
                String idAccount = request.getParameter("id");
                HttpSession ssn = request.getSession();

                UtenteBean u = (UtenteBean) ssn.getAttribute("utente");
                if(u == null){
                    response.sendRedirect("/UniNotes_war_exploded/");
                    break;
                }

                if (idAccount != null) {
                    utente = ud.doRetriveById(Integer.parseInt(idAccount));
                    utenteService.rendiAdmin(utente);
                }
                response.sendRedirect("/UniNotes_war_exploded/Utente/visualizzaUtenti"); //NON so se è giusto

                break;
            }

            case "/faqs":{

                UtenteBean u = (UtenteBean) request.getSession().getAttribute("utente");
                if(u == null){
                    response.sendRedirect("/UniNotes_war_exploded/");
                    break;
                }
                request.getRequestDispatcher("/WEB-INF/interface/partials/FAQs.jsp").forward(request,response);
                break;
            }
            case "/un":{

                UtenteBean u = (UtenteBean) request.getSession().getAttribute("utente");
                if(u == null){
                    response.sendRedirect("/UniNotes_war_exploded/");
                    break;
                }
                request.getRequestDispatcher("/WEB-INF/interface/partials/UN.jsp").forward(request,response);
                break;
            }

        }
    }

}
