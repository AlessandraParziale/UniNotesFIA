package Application.Corso;

import Application.Corso.ServiceCorso.CorsoService;
import Application.Corso.ServiceCorso.CorsoServiceImpl;
import Application.MaterialeDidattico.ServiceMaterialeDidattico.MaterialeDidatticoService;
import Application.MaterialeDidattico.ServiceMaterialeDidattico.MaterialeDidatticoServiceImpl;
import Storage.Corso.CorsoBean;
import Storage.Utente.UtenteBean;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "CorsoServlet", value = "/Corso/*")
public class CorsoServlet extends HttpServlet {

    private final CorsoService corsoService = new CorsoServiceImpl();
    private final MaterialeDidatticoService materialeDidattico = new MaterialeDidatticoServiceImpl();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = (request.getPathInfo() != null) ? request.getPathInfo() : "/";
        switch (path){
            case "/crea":{
                request.getRequestDispatcher("/WEB-INF/interface/interfacciaCorso/crea.jsp").forward(request,response);
                break;
            }
            case "/elimina":{
                request.getRequestDispatcher("/WEB-INF/interface/interfacciaCorso/elimina.jsp").forward(request,response);
                break;
            }
            case "/modifica":{
                request.getRequestDispatcher("/WEB-INF/interface/interfacciaCorso/modifica.jsp").forward(request,response);
                break;
            }
            case "/visualizza":{
                HttpSession ssn = request.getSession();
                UtenteBean u = (UtenteBean) ssn.getAttribute("utente");
                if(u == null){
                    response.sendRedirect("/UniNotes_war_exploded/");
                    break;
                }
                int id = Integer.parseInt(request.getParameter("idCorso"));
                CorsoBean c = corsoService.visualizzaCorso(id);
                System.out.println(id);
                c.setListaMateriale(materialeDidattico.visualizzaMaterialeDiUnCorso(id));
                request.setAttribute("Corso",c);
                request.setAttribute("Materiale",materialeDidattico.visualizzaMaterialeDiUnCorso(id));
                //togliere l'id statico --> mettendone uno dinamico
                request.getRequestDispatcher("/WEB-INF/interface/interfacciaCorso/visualizza.jsp").forward(request,response);
                break;
            }
            case "/visualizzaTutti":{

                HttpSession ssn = request.getSession();
                UtenteBean u = (UtenteBean) ssn.getAttribute("utente");
                if(u == null){
                    response.sendRedirect("/UniNotes_war_exploded/");
                    break;
                }

                ArrayList<CorsoBean> c = corsoService.visualizzaCorsi();
                for(CorsoBean corso : c){
                    corso.setListaMateriale(materialeDidattico.visualizzaMaterialeDiUnCorso(corso.getId()));
                }
                request.setAttribute("corsi",c);
                request.getRequestDispatcher("/WEB-INF/interface/interfacciaUtente/dashboard/corsi.jsp").forward(request,response);
                break;
            }
            case "/visualizzaTuttiUtente":{

                HttpSession ssn = request.getSession();
                UtenteBean u = (UtenteBean) ssn.getAttribute("utente");
                if(u == null){
                    response.sendRedirect("/UniNotes_war_exploded/");
                    break;
                }


                request.setAttribute("corsi",corsoService.visualizzaCorsi());
                request.getRequestDispatcher("/WEB-INF/interface/interfacciaCorso/visualizzaTutti.jsp").forward(request,response);
                break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = (request.getPathInfo() != null) ? request.getPathInfo() : "/";
        switch (path){
            case "/crea":{

                UtenteBean u = (UtenteBean) request.getSession().getAttribute("utente");
                if(u == null){
                    response.sendRedirect("/UniNotes_war_exploded/");
                    break;
                }

                CorsoBean c;

                String nomePattern = "[a-zA-Z\\s]+$";// pattern vecchio [A-Z a-z]


                String nome = request.getParameter("Nome");
                String descrizione = request.getParameter("Descrizione");
                String nomeProfessore = request.getParameter("NomeProfessore");


                if (nome.matches(nomePattern) && descrizione.matches(nomePattern) && nomeProfessore.matches(nomePattern)) {


                    c = corsoService.inserisciCorso(nome,descrizione,nomeProfessore);


                    response.sendRedirect("/UniNotes_war_exploded/Corso/visualizzaTutti");
                    break;
                }

                //da rivedere quando non lo crea
                response.sendRedirect("/UniNotes_war_exploded/");
                break;
            }
            case "/elimina":{
                String id = request.getParameter("id");
                corsoService.eliminaCorso(Integer.parseInt(id));
                request.getRequestDispatcher("/UniNotes_war_exploded/").forward(request,response);
                break;
            }
            case "/modifica":{
                System.out.println("Sono in modifica");
                UtenteBean u = (UtenteBean) request.getSession().getAttribute("utente");
                if(u == null){
                    response.sendRedirect("/UniNotes_war_exploded/");
                    break;
                }

                CorsoBean c;

                String nomePattern = "[a-zA-Z\\s]+$";// pattern vecchio [A-Z a-z]

                String id = request.getParameter("id");
                String nome = request.getParameter("Nome");
                String descrizione = request.getParameter("Descrizione");
                String nomeProfessore = request.getParameter("NomeProfessore");



                if (nome.matches(nomePattern) && descrizione.matches(nomePattern) && nomeProfessore.matches(nomePattern)) {
                    c = corsoService.modificaCorso(Integer.parseInt(id),nome,descrizione,nomeProfessore);
                    System.out.println("SOno qui");
                    response.sendRedirect("/UniNotes_war_exploded/Corso/visualizzaTutti");
                    break;
                }

                //da rivedere quando non lo crea
                response.sendRedirect("/UniNotes_war_exploded/");
                break;
            }
            case "/visualizza":{
                request.getRequestDispatcher("/WEB-INF/interface/interfacciaCorso/visualizza.jsp").forward(request,response);
                break;
            }
            case "/visualizzaTutti":{
                request.getRequestDispatcher("/WEB-INF/interface/interfacciaCorso/visualizzaTutti.jsp").forward(request,response);
                break;
            }
        }
    }

}
