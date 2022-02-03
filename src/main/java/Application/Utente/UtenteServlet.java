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




/**
 * Servlet dove utilizziamo i metodi per il singolo Utente
 */
@WebServlet(name = "UtenteServlet", value = "/Utente/*")
public class UtenteServlet extends HttpServlet {

    private final UtenteService utenteService = new UtenteServiceImpl();
    private final LibrettoService librettoService = new LibrettoServiceImpl();
    private final CorsoService corsoService = new CorsoServiceImpl();
    private final MaterialeDidatticoService materialeService = new MaterialeDidatticoServiceImpl();

    /**
     * Metodo doGet della servlet Utente
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = (request.getPathInfo() != null) ? request.getPathInfo() : "/";
        switch (path){

            /**
             *  Caso per tornare alla login
             */
            case "/":{
                response.sendRedirect("/UniNotes_war_exploded/");
                break;
            }

            /**
             *  Caso per quiz Fia
             */
            case "/quiz":{
                UtenteBean u = (UtenteBean) request.getSession().getAttribute("utente");
                if(u.getMagistrale() != null){
                    request.getRequestDispatcher("/WEB-INF/interface/interfacciaUtente/predizione.jsp").forward(request, response);
                    break;
                }
                    request.getRequestDispatcher("/WEB-INF/interface/interfacciaUtente/Quiz.jsp").forward(request, response);
                    break;

            }

            /**
             *  Caso per tornare alla home
             */
            case "/home":{
                UtenteBean u = (UtenteBean) request.getSession().getAttribute("utente");
                if(u == null){
                    response.sendRedirect("/UniNotes_war_exploded/");
                    break;
                }
                request.getRequestDispatcher("/WEB-INF/interface/interfacciaUtente/home.jsp").forward(request,response);
                break;
            }

            /**
             *  Caso per visualizzare la dashboard
             */
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

            /**
             *  Caso per la registrazione
             */
            case "/registrazione":{
                request.getRequestDispatcher("/registrazione.jsp").forward(request,response);
                break;
            }

            /**
             *  Caso per la login
             */
            case "/login": {//login [adimn/studente]
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
            }

            /**
             *  Caso per la visualizzazione di tutti gli utenti [admin]
             */
            case "/visualizzaUtenti":{

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

            /**
             *  Caso per la visualizzazione della pagina personale
             */
            case "/visualizzaPaginaPersonale":{ //visualizza utenti registrati [adimn]

                request.getRequestDispatcher("/WEB-INF/interface/interfacciaUtente/dashboard/paginaPersonale.jsp").forward(request, response);
                break;
            }

            /**
             *  Caso per il logout
             */
            case "/logout": {//logout [adimn/cliente]
                HttpSession session = request.getSession(false);
                session.removeAttribute("utente");
                session.invalidate();
                response.sendRedirect("/UniNotes_war_exploded/index.jsp");
                break;
            }

            /**
             *  Caso per la visualizzazione delle faqs
             */
            case "/faqs":{

                UtenteBean u = (UtenteBean) request.getSession().getAttribute("utente");
                if(u == null){
                    response.sendRedirect("/UniNotes_war_exploded/");
                    break;
                }
                request.getRequestDispatcher("/WEB-INF/interface/partials/FAQs.jsp").forward(request,response);
                break;
            }

            /**
             *  Caso per la visualizzazione della storia di UniNotes
             */
            case "/un":{

                UtenteBean u = (UtenteBean) request.getSession().getAttribute("utente");
                if(u == null){
                    response.sendRedirect("/UniNotes_war_exploded/");
                    break;
                }
                request.getRequestDispatcher("/WEB-INF/interface/partials/UN.jsp").forward(request,response);
                break;
            }
            default:
                throw new RuntimeException("Unexpected value: " + path);


        }
    }

    /**
     * Metodo doPost della servlet Utente
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = (request.getPathInfo() != null) ? request.getPathInfo() : "/";

        switch (path){

            /**
             *  Caso per la registrazione
             */
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
                String checkbox = request.getParameter("checkbox");;
                ArrayList<String> errors = new ArrayList<>();

                if(!email.matches(emailPattern)){
                    errors.add("Email non valida");
                }
                if(!nome.matches(nomePattern)){
                    errors.add("Nome non valido");
                }
                if(!cognome.matches(cognomePattern) ){
                    errors.add("Cognome non valido");
                }
                if(!cf.matches(cfPattern) ){
                    errors.add("Il cf non è valido");
                }
                if(data.equals("")){
                    errors.add("La data non è valida");
                }
                if(!password.matches(passwordPattern)){
                    errors.add("La password non rispetta il formato. Dedeve essere di almeno 6 caratteri tra cui una maiuscola, una minuscola e un numero");
                }
                if(!confermaPassword.matches(passwordPattern)){
                    errors.add("La password non rispetta il formato. Dedeve essere di almeno 6 caratteri tra cui una maiuscola, una minuscola e un numero");
                }
                if(!password.equals(confermaPassword)){
                    errors.add("La password non coincide con conferma password");
                }
                if(checkbox == null){
                    errors.add("Accettare il regolamento");
                }

              if (nome.matches(nomePattern) && cognome.matches(cognomePattern) && cf.matches(cfPattern) && email.matches(emailPattern) && password.matches(passwordPattern) && confermaPassword.matches(passwordPattern) && password.equals(confermaPassword) && checkbox != null) {

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

            /**
             *  Caso per la login
             */
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
                    if(utente != null){    System.out.println("utente 1");
                        request.getSession(false).invalidate();
                        System.out.println("utente 2");
                        LibrettoBean librettoConIdUtente = librettoService.visualizzaLibrettoDiUtente(utente.getIdUtente());
                        System.out.println("utente 3");
                        HttpSession ssn = request.getSession(true);
                        System.out.println("utente 4");
                        String magistrale = utenteService.magistrale(utente);
                        System.out.println("utente 5" +magistrale);
                        utente.setLibretto(librettoService.visualizzaLibretto(librettoConIdUtente.getIdLibretto()));
                        ssn.setAttribute("libretto",librettoService.visualizzaLibretto(librettoConIdUtente.getIdLibretto()));
                        ssn.setAttribute("utente", utente);
                        ssn.setAttribute("magistrale",magistrale);
                        ssn.setMaxInactiveInterval(86400);
                        response.sendRedirect("/UniNotes_war_exploded/Utente/home");
                        break;
                    }else{
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


            /**
             *  Caso per la modifica
             */
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
                ArrayList<String> errors = new ArrayList<>();

                if(!email.matches(emailPattern)){
                    errors.add("Email non valida");
                }
                if(!nome.matches(nomePattern)){
                    errors.add("Il nome non valido");
                }
                if(!cognome.matches(cognomePattern) ){
                    errors.add("Il cognome non valido");
                }
                if(!cf.matches(cfPattern) ) {
                    errors.add("Il cf non è valido");
                }

                if(data.equals("")){
                    errors.add("Data non valida");
                }

                if (nome.matches(nomePattern) && cognome.matches(cognomePattern) && cf.matches(cfPattern) && email.matches(emailPattern) && !data.equals("")) {
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
                }else {
                    request.setAttribute("errors",errors);
                    request.getRequestDispatcher("/WEB-INF/interface/interfacciaUtente/dashboard/paginaPersonale.jsp").forward(request,response);
                    break;
                }
            }

            /**
             *  Caso per la modifica del tipo di utente [solo lato admin]
             */
            case "/toggleRuolo" : {

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

            /**
             *  Caso per la visualizzazione delle faqs
             */
            case "/faqs":{

                UtenteBean u = (UtenteBean) request.getSession().getAttribute("utente");
                if(u == null){
                    response.sendRedirect("/UniNotes_war_exploded/");
                    break;
                }
                request.getRequestDispatcher("/WEB-INF/interface/partials/FAQs.jsp").forward(request,response);
                break;
            }
            
            /**
             *  Caso per la visualizzazione della storia di UniNotes
             */
            case "/un":{

                UtenteBean u = (UtenteBean) request.getSession().getAttribute("utente");
                if(u == null){
                    response.sendRedirect("/UniNotes_war_exploded/");
                    break;
                }
                request.getRequestDispatcher("/WEB-INF/interface/partials/UN.jsp").forward(request,response);
                break;
            }
            default:
                throw new RuntimeException("Unexpected value: " + path);

        }
    }

}
