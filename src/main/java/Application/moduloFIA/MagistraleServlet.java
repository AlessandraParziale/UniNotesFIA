package Application.moduloFIA;

import Application.moduloFIA.service.MagistraleServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "MagistraleServlet", value = "/MagistraleServlet")
public class MagistraleServlet extends HttpServlet {
    MagistraleServiceImpl ms= new MagistraleServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String d1 = request.getParameter("d1");
        String d2 = request.getParameter("d2");
        String d3 = request.getParameter("d3");
        String d4 = request.getParameter("d4");
        String d5 = request.getParameter("d5");

    String s = ms.predizione(d1, d2, d3, d4, d5);
    request.setAttribute("predizione", s);
    request.getRequestDispatcher("/WEB-INF/interface/interfacciaUtente/predizione.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
