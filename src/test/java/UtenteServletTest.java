import Application.Corso.ServiceCorso.CorsoService;
import Application.Libretto.ServiceLibretto.LibrettoService;
import Application.MaterialeDidattico.ServiceMaterialeDidattico.MaterialeDidatticoService;
import Application.Utente.ServiceUtente.UtenteService;
import Application.Utente.ServiceUtente.UtenteServiceImpl;
import Application.Utente.UtenteServlet;
import Storage.Corso.CorsoBean;
import Storage.Libretto.LibrettoBean;
import Storage.MaterialeDidattico.MaterialeDidatticoBean;
import Storage.Utente.UtenteBean;
import Storage.Utente.UtenteDao;
import net.bytebuddy.asm.Advice;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class UtenteServletTest {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private RequestDispatcher requestDispatcher;
    private MaterialeDidatticoService materialeDidatticoService;
    private LibrettoService librettoService;
    private CorsoService corsoService;
    private UtenteService utenteService;
    private UtenteServlet us;
    private UtenteDao ud;


    @Before
    public void setUp(){
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        us = new UtenteServlet();
        requestDispatcher = Mockito.mock(RequestDispatcher.class);
        materialeDidatticoService = Mockito.mock(MaterialeDidatticoService.class);
        utenteService = Mockito.mock(UtenteService.class);
        corsoService = Mockito.mock(CorsoService.class);
        librettoService = Mockito.mock(LibrettoService.class);
        ud = Mockito.mock(UtenteDao.class);
    }

    @Test
    public void DoGethomeTest() throws ServletException, IOException {
        UtenteBean u = new UtenteBean();
        u.setIdUtente(2);
        when(request.getPathInfo()).thenReturn("/home");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("utente")).thenReturn(u);
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        us.doGet(request,response);
        verify(requestDispatcher,atLeastOnce()).forward(request,response);
    }

    @Test
    public void DoGethomeTestUtenteNull() throws ServletException, IOException {
        UtenteBean u = new UtenteBean();
        u.setIdUtente(2);
        when(request.getPathInfo()).thenReturn("/home");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("utente")).thenReturn(null);
        us.doGet(request,response);
        verify(response,atLeastOnce()).sendRedirect(anyString());
    }


    @Test
    public void DoGetdashboardTest() throws ServletException, IOException {

        UtenteBean u = new UtenteBean();
        u.setIdUtente(2);
        u.setTipo(true);
        ArrayList<MaterialeDidatticoBean> materiali = new ArrayList<>();

        materiali.add(new MaterialeDidatticoBean("ProvaMateriale","provaPath"));

        when(request.getPathInfo()).thenReturn("/dashboard");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("utente")).thenReturn(u);
        when(materialeDidatticoService.visualizzaTutti()).thenReturn(materiali);
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);

        us.doGet(request,response);
        verify(requestDispatcher,atLeastOnce()).forward(request,response);


    }

    @Test
    public void DoGetdashboardTestUtenteNull() throws ServletException, IOException {
        when(request.getPathInfo()).thenReturn("/dashboard");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("utente")).thenReturn(null);

        us.doGet(request,response);
        verify(response,atLeastOnce()).sendRedirect(anyString());
    }


    @Test
    public void DoGetdashboardTestUtenteIsTipo() throws ServletException, IOException {//riga 61 dashboard/utenteisTipo

        UtenteBean u = new UtenteBean();
        u.setIdUtente(2);
        ArrayList<MaterialeDidatticoBean> materiali = new ArrayList<>();
        ArrayList<UtenteBean> utenti = new ArrayList<>();
        ArrayList<CorsoBean> corsi = new ArrayList<>();

        utenti.add(new UtenteBean());
        corsi.add(new CorsoBean());
        materiali.add(new MaterialeDidatticoBean("ProvaMateriale","provaPath"));

        when(request.getPathInfo()).thenReturn("/dashboard");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("utente")).thenReturn(u);
        when(materialeDidatticoService.visualizzaMaterialeDiUnUtente(2)).thenReturn(materiali);
        when(utenteService.visualizzaUtenti()).thenReturn(utenti);
        when(corsoService.visualizzaCorsi()).thenReturn(corsi);
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);

        us.doGet(request,response);
        verify(requestDispatcher,atLeastOnce()).forward(request,response);
    }



    @Test
    public void DoGetregistrazioneTest() throws ServletException, IOException {

        when(request.getPathInfo()).thenReturn("/registrazione");
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);

        us.doGet(request,response);
        verify(requestDispatcher,atLeastOnce()).forward(request,response);
    }

    @Test
    public void DoGetloginTest() throws ServletException, IOException {

        when(request.getPathInfo()).thenReturn("/login");
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);

        us.doGet(request,response);
        verify(requestDispatcher,atLeastOnce()).forward(request,response);
    }


    @Test
    public void DoGetvisualizzaUtentiTest() throws ServletException, IOException {

        request = Mockito.mock(HttpServletRequest.class);
        ArrayList<UtenteBean> utenti = new ArrayList<>();
        UtenteBean utente = Mockito.mock(UtenteBean.class);

        UtenteBean utente1 = new UtenteBean("nome", "cognome","cf","email",LocalDate.of(2000,12,21), "prova",true);
        utente1.setIdUtente(2);
        UtenteBean utente2 = new UtenteBean("nome", "cognome","cf","email",LocalDate.of(2000,12,21), "prova",true);
        utente2.setIdUtente(3);
        UtenteBean utente3 = new UtenteBean("nome", "cognome","cf","email",LocalDate.of(2000,12,21), "prova",true);
        utente3.setIdUtente(4);
        utenti.add(utente1);
        utenti.add(utente2);
        utenti.add(utente3);

        when(request.getPathInfo()).thenReturn("/visualizzaUtenti");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("utente")).thenReturn(utente1);
        when(utenteService.visualizzaUtenti()).thenReturn(utenti);
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);

        us.doGet(request,response);
        verify(requestDispatcher,atLeastOnce()).forward(request,response);
    }



    @Test
    public void DoGetvisualizzaUtentiTestUtenteNull() throws ServletException, IOException {

        when(request.getPathInfo()).thenReturn("/visualizzaUtenti");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("utente")).thenReturn(null);

        us.doGet(request,response);
        verify(response,atLeastOnce()).sendRedirect(anyString());
    }


    @Test
    public void DoGetvisualizzaPaginaPersonaleTest() throws ServletException, IOException {

        when(request.getPathInfo()).thenReturn("/visualizzaPaginaPersonale");
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);

        us.doGet(request,response);
        verify(requestDispatcher,atLeastOnce()).forward(request,response);
    }


    @Test
    public void DoGetlogoutTest() throws ServletException, IOException {
        when(request.getPathInfo()).thenReturn("/logout");
        when(request.getSession(false)).thenReturn(session);
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);

        us.doGet(request,response);
        verify(response,atLeastOnce()).sendRedirect(anyString());
    }


    @Test
    public void DoPostRegistrazioneTest() throws ServletException, IOException {//riga 61 dashboard/utenteisTipo

        UtenteBean u = new UtenteBean();
        u.setIdUtente(2);


        ArrayList<MaterialeDidatticoBean> materiali = new ArrayList<>();
        ArrayList<UtenteBean> utenti = new ArrayList<>();
        ArrayList<CorsoBean> corsi = new ArrayList<>();

        utenti.add(new UtenteBean());
        corsi.add(new CorsoBean());
        materiali.add(new MaterialeDidatticoBean("ProvaMateriale","provaPath"));

        when(request.getPathInfo()).thenReturn("/registrazione");
        when(request.getParameter("Nome")).thenReturn("String");
        when(request.getParameter("Cognome")).thenReturn("String");
        when(request.getParameter("CF")).thenReturn("MWKZPY95B02M196F");
        when(request.getParameter("Email")).thenReturn("prova123@gmail.com");
        when(request.getParameter("Password")).thenReturn("password123");
        when(request.getParameter("CPassword")).thenReturn("password123");
        when(request.getParameter("DataDiNascita")).thenReturn("2000-12-12");
        when(request.getParameter(anyString())).thenReturn("parola");
        when(request.getRequestDispatcher("/registrazione.jsp")).thenReturn(requestDispatcher);


        us.doPost(request,response);
        verify(requestDispatcher,atLeastOnce()).forward(request,response);

    }



    @Test
    public void DoPostRegistrazioneTestIfMatches() throws ServletException, IOException, SQLException, NoSuchAlgorithmException {//riga 168
        UtenteBean u = new UtenteBean();
        u.setIdUtente(2);


        ArrayList<MaterialeDidatticoBean> materiali = new ArrayList<>();
        ArrayList<UtenteBean> utenti = new ArrayList<>();
        ArrayList<CorsoBean> corsi = new ArrayList<>();

        utenti.add(new UtenteBean());
        corsi.add(new CorsoBean());
        materiali.add(new MaterialeDidatticoBean("ProvaMateriale","provaPath"));

        when(request.getPathInfo()).thenReturn("/registrazione");
        when(request.getParameter("Nome")).thenReturn("Mario");
        when(request.getParameter("Cognome")).thenReturn("Rossi");
        when(request.getParameter("CF")).thenReturn("MWKZPY95B02M196F");
        when(request.getParameter("Email")).thenReturn("prova123@gmail.com");
        when(request.getParameter("Password")).thenReturn("Password123");
        when(request.getParameter("CPassword")).thenReturn("Password123");
        when(request.getParameter("DataDiNascita")).thenReturn("2000-12-12");
        when(utenteService.registrazione(anyString(), anyString(), anyString(), anyString(), anyString(), any(),any())).thenReturn(u);
        when(request.getSession(false)).thenReturn(session);
        when(request.getSession(true)).thenReturn(session);
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);

        us.doPost(request,response);
        verify(response,atLeastOnce()).sendRedirect(anyString());
    }

    //test degli if che non matchano
    @Test
    public void DoPostRegistrazioneTestIfNotMatchesNull() throws ServletException, IOException, SQLException, NoSuchAlgorithmException {//riga 168
        UtenteBean u = new UtenteBean();
        u.setIdUtente(2);


        ArrayList<MaterialeDidatticoBean> materiali = new ArrayList<>();
        ArrayList<UtenteBean> utenti = new ArrayList<>();
        ArrayList<CorsoBean> corsi = new ArrayList<>();

        utenti.add(new UtenteBean());
        corsi.add(new CorsoBean());
        materiali.add(new MaterialeDidatticoBean("ProvaMateriale","provaPath"));

        when(request.getPathInfo()).thenReturn("/registrazione");
        when(request.getParameter("Nome")).thenReturn("1234");
        when(request.getParameter("Cognome")).thenReturn("1234");
        when(request.getParameter("CF")).thenReturn("MWKZ02M196F");
        when(request.getParameter("Email")).thenReturn("prova123gmail.com");
        when(request.getParameter("Password")).thenReturn("sswo");
        when(request.getParameter("CPassword")).thenReturn("123");
        when(request.getParameter("DataDiNascita")).thenReturn("2000-12-12");

        when(request.getRequestDispatcher("/registrazione.jsp")).thenReturn(requestDispatcher);


        us.doPost(request,response);
        verify(requestDispatcher,atLeastOnce()).forward(request,response);
    }


    @Test
    public void DoPostLoginTest() throws ServletException, IOException, SQLException, NoSuchAlgorithmException {//riga 168
        UtenteBean u = new UtenteBean();
        u.setIdUtente(2);
        u.setNome("Mario");


        ArrayList<MaterialeDidatticoBean> materiali = new ArrayList<>();
        ArrayList<UtenteBean> utenti = new ArrayList<>();
        ArrayList<CorsoBean> corsi = new ArrayList<>();

        utenti.add(new UtenteBean());
        corsi.add(new CorsoBean());
        materiali.add(new MaterialeDidatticoBean("ProvaMateriale","provaPath"));

        when(request.getPathInfo()).thenReturn("/login");
        when(request.getParameter("email")).thenReturn("prova123@gmail.com");
        when(request.getParameter("password")).thenReturn("Password123");
        when(utenteService.login("prova123@gmail.com","Password123")).thenReturn(u);
        when(request.getSession(false)).thenReturn(session);
        when(librettoService.visualizzaLibretto(2)).thenReturn(new LibrettoBean(0,0,0));
        when(request.getSession(true)).thenReturn(session);


        us.doPost(request,response);
        verify(response,atLeastOnce()).sendRedirect(anyString());
    }

/*
    @Test
    public void DoPostLoginTestUtenteNull() throws ServletException, IOException, SQLException, NoSuchAlgorithmException {//riga 168
        UtenteBean u = null;


        when(request.getPathInfo()).thenReturn("/login");
        when(request.getParameter("email")).thenReturn("");
        when(request.getParameter("password")).thenReturn("23");
        when(utenteService.login("Prova123@gmail.com","Password123")).thenReturn(u);
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);


        us.doPost(request,response);
        verify(requestDispatcher,atLeastOnce()).forward(request,response);
    }
*/
    //quando email e password non soddisfano i matches
    @Test
    public void DoPostLoginTestNoMatches() throws ServletException, IOException, SQLException, NoSuchAlgorithmException {//riga 168
        UtenteBean u = new UtenteBean();
        u.setIdUtente(2);


        ArrayList<MaterialeDidatticoBean> materiali = new ArrayList<>();
        ArrayList<UtenteBean> utenti = new ArrayList<>();
        ArrayList<CorsoBean> corsi = new ArrayList<>();

        utenti.add(new UtenteBean());
        corsi.add(new CorsoBean());
        materiali.add(new MaterialeDidatticoBean("ProvaMateriale","provaPath"));

        when(request.getPathInfo()).thenReturn("/login");
        when(request.getParameter("email")).thenReturn("prova123gmail.com");
        when(request.getParameter("password")).thenReturn("123");
        when(utenteService.login("prova123@gmail.com","Password123")).thenReturn(null);
        when(request.getSession(false)).thenReturn(session);
        when(librettoService.visualizzaLibretto(2)).thenReturn(new LibrettoBean(0,0,0));
        when(request.getSession(true)).thenReturn(session);
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);


        us.doPost(request,response);
        verify(requestDispatcher,atLeastOnce()).forward(request,response);
    }


    @Test
    public void DoPostModificaTestIfMatches() throws ServletException, IOException, SQLException, NoSuchAlgorithmException {//riga 168
        UtenteBean u = new UtenteBean();
        u.setIdUtente(2);


        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("utente")).thenReturn(u);
        when(request.getPathInfo()).thenReturn("/modifica");
        when(request.getParameter("Nome")).thenReturn("Mario");
        when(request.getParameter("Cognome")).thenReturn("Rossi");
        when(request.getParameter("CF")).thenReturn("MWKZPY95B02M196F");
        when(request.getParameter("Email")).thenReturn("prova123@gmail.com");
        when(request.getParameter("Password")).thenReturn("Password123");
        when(request.getParameter("CPassword")).thenReturn("Password123");
        when(request.getParameter("DataDiNascita")).thenReturn("2000-12-12");
        when(utenteService.aggiorna(any())).thenReturn(u);
        us.doPost(request,response);
        verify(response,atLeastOnce()).sendRedirect(anyString());
    }

    @Test
    public void DoPostModificaTestUtenteNull() throws ServletException, IOException, SQLException, NoSuchAlgorithmException {//riga 168
        when(request.getPathInfo()).thenReturn("/modifica");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("utente")).thenReturn(null);

        us.doPost(request,response);
        verify(response,atLeastOnce()).sendRedirect(anyString());
    }

    //tutti i campi soddisfano i match ma aggiorna restituisce un utente null
  /*
    @Test
    public void DoPostModificaTestIfMatchesUtenteNull() throws ServletException, IOException, SQLException, NoSuchAlgorithmException {//riga 168
        UtenteBean u = new UtenteBean();
        u.setIdUtente(2);


        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("utente")).thenReturn(u);
        when(request.getPathInfo()).thenReturn("/modifica");
        when(request.getParameter("Nome")).thenReturn("Mario");
        when(request.getParameter("Cognome")).thenReturn("Rossi");
        when(request.getParameter("CF")).thenReturn("MWKZPY95B02M196F");
        when(request.getParameter("Email")).thenReturn("prova123@gmail.com");
        when(request.getParameter("Password")).thenReturn("Password123");
        when(request.getParameter("CPassword")).thenReturn("Password123");
        when(request.getParameter("DataDiNascita")).thenReturn("2000-12-12");
       //when(utenteService.aggiorna(any())).thenReturn(null);

        us.doPost(request,response);
        verify(response,atLeastOnce()).sendError(400,"La modifica non è andata a buon fine");
    }
*/

    @Test
    public void DoPostRendiAdminTest() throws ServletException, IOException, SQLException, NoSuchAlgorithmException {//riga 168
        UtenteBean u = new UtenteBean();
        u.setIdUtente(2);

        when(request.getPathInfo()).thenReturn("/toggleRuolo");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("utente")).thenReturn(u);
        when(request.getParameter("cambio")).thenReturn("2");
        when(ud.doRetriveById(2)).thenReturn(u);
        doNothing().when(utenteService).rendiAdmin(any());

        us.doPost(request,response);
        verify(response,atLeastOnce()).sendRedirect(anyString());
    }

    @Test
    public void DoPostRendiAdminTestUtenteNull() throws ServletException, IOException, SQLException, NoSuchAlgorithmException {//riga 168
        UtenteBean u = new UtenteBean();
        u.setIdUtente(2);

        when(request.getPathInfo()).thenReturn("/toggleRuolo");
        when(request.getSession()).thenReturn(session);

        us.doPost(request,response);
        verify(response,atLeastOnce()).sendRedirect(anyString());
    }


    @Test
    public void DoPostfaqsTest() throws ServletException, IOException, SQLException, NoSuchAlgorithmException {//riga 168
        UtenteBean u = new UtenteBean();
        u.setIdUtente(2);

        when(request.getPathInfo()).thenReturn("/faqs");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("utente")).thenReturn(u);
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);


        us.doPost(request,response);
        verify(requestDispatcher,atLeastOnce()).forward(request,response);
    }

    @Test
    public void DoPostfaqsTestUtenteNull() throws ServletException, IOException, SQLException, NoSuchAlgorithmException {//riga 168
        UtenteBean u = new UtenteBean();
        u.setIdUtente(2);

        when(request.getPathInfo()).thenReturn("/faqs");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("utente")).thenReturn(null);

        us.doPost(request,response);
        verify(response,atLeastOnce()).sendRedirect(anyString());
    }


    @Test
    public void DoPostUnTest() throws ServletException, IOException, SQLException, NoSuchAlgorithmException {//riga 168
        UtenteBean u = new UtenteBean();
        u.setIdUtente(2);

        when(request.getPathInfo()).thenReturn("/un");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("utente")).thenReturn(u);
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);


        us.doPost(request,response);
        verify(requestDispatcher,atLeastOnce()).forward(request,response);
    }

    @Test
    public void DoPostUnTestUtenteNull() throws ServletException, IOException, SQLException, NoSuchAlgorithmException {//riga 168
        UtenteBean u = new UtenteBean();
        u.setIdUtente(2);

        when(request.getPathInfo()).thenReturn("/un");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("utente")).thenReturn(null);

        us.doPost(request,response);
        verify(response,atLeastOnce()).sendRedirect(anyString());
    }






}
