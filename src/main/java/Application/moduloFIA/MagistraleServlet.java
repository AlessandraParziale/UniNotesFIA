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


    String s = ms.predizione(request.getParameter("d1"),request.getParameter("d2"),request.getParameter("d3"),request.getParameter("d4"),request.getParameter("d5"));
    System.out.println(s);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
