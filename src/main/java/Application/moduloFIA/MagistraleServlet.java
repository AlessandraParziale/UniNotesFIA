package Application.moduloFIA;

import Application.Utente.ServiceUtente.UtenteServiceImpl;
import Application.moduloFIA.service.MagistraleServiceImpl;
import Storage.Utente.UtenteBean;
import Storage.Utente.UtenteDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "MagistraleServlet", value = "/MagistraleServlet")
public class MagistraleServlet extends HttpServlet {
    MagistraleServiceImpl ms= new MagistraleServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

                UtenteServiceImpl utenteimpl = new UtenteServiceImpl();

                HttpSession ssn = request.getSession();

                UtenteBean u = (UtenteBean) ssn.getAttribute("utente");

                        String d1 = request.getParameter("d1");
                        String d2 = request.getParameter("d2");
                        String d3 = request.getParameter("d3");
                        String d4 = request.getParameter("d4");
                        String d5 = request.getParameter("d5");
                        String d6 = request.getParameter("d5");
                        String d7 = request.getParameter("d5");
                        String d8 = request.getParameter("d5");

                                String s = ms.predizione(d1, d2, d3, d4, d5, d6, d7, d8);
                                String p = "";
                                if(s.equals("0")) {
                                    p = "Cloud Computing";
                                }if(s.equals("1")) {
                                    p = "Sicurezza Informatica";
                                }if(s.equals("2")) {
                                    p = "Software Engineering and IT Management";
                                }if(s.equals("3")) {
                                    p = "IoT";
                                }if(s.equals("4")) {
                                    p = "Data Science & Machine Learning";
                                }

                                u.setMagistrale(p);

                            try {
                                utenteimpl.salvaMagistrale(u);
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                            request.setAttribute("predizione", p);

                            ssn.setAttribute("utente", u);
                        request.getRequestDispatcher("/WEB-INF/interface/interfacciaUtente/predizione.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
