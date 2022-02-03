package Application.moduloFIA;

import Application.Utente.ServiceUtente.UtenteServiceImpl;
import Application.moduloFIA.service.MagistraleServiceImpl;
import Storage.Utente.UtenteBean;
import Storage.Utente.UtenteDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "MagistraleServlet", value = "/Magistrale/*")
public class MagistraleServlet extends HttpServlet {
    MagistraleServiceImpl ms= new MagistraleServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = (request.getPathInfo() != null) ? request.getPathInfo() : "/";
        switch (path) {

            /**
             *  Caso per inserire e rimuovere da lista preferiti
             */
            case "/magistrale": {
                UtenteServiceImpl utenteimpl = new UtenteServiceImpl();

                HttpSession ssn = request.getSession();

                UtenteBean u = (UtenteBean) ssn.getAttribute("utente");
                if(u == null){
                    response.sendRedirect("/UniNotes_war_exploded/");
                    break;
                }

                String d1 = request.getParameter("d1");
                String d2 = request.getParameter("d2");
                String d3 = request.getParameter("d3");
                String d4 = request.getParameter("d4");
                String d5 = request.getParameter("d5");
                String d6 = request.getParameter("d6");
                String d7 = request.getParameter("d7");
                String d8 = request.getParameter("d8");

                ArrayList<String> risposte = ms.predizione(d1, d2, d3, d4, d5, d6, d7, d8);
                ArrayList<String> ris = new ArrayList<>();
                for (String s : risposte) {
                    if (s.equals("0")) {
                        ris.add("Cloud Computing");
                    }
                    if (s.equals("1")) {
                        ris.add("Sicurezza Informatica");
                    }
                    if (s.equals("2")) {
                        ris.add("Software Engineering and IT Management");
                    }
                    if (s.equals("3")) {
                        ris.add("IoT");
                    }
                    if (s.equals("4")) {
                        ris.add("Data Science & Machine Learning");
                    }
                }


                u.setMagistrale(ris.get(0));

                try {
                    utenteimpl.salvaMagistrale(u);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                request.setAttribute("predizione", ris);

                ssn.setAttribute("utente", u);
                request.getRequestDispatcher("/WEB-INF/interface/interfacciaUtente/predizione.jsp").forward(request, response);
                break;
            }
            case "/reset": {
                HttpSession ssn = request.getSession();

                UtenteBean u = (UtenteBean) ssn.getAttribute("utente");
                if(u == null){
                    response.sendRedirect("/UniNotes_war_exploded/");
                    break;
                }

                u.setMagistrale(null);
                response.sendRedirect("/UniNotes_war_exploded/Utente/quiz");
                break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
