package Application.MaterialeDidattico;

import Application.Corso.ServiceCorso.CorsoService;
import Application.Corso.ServiceCorso.CorsoServiceImpl;
import Application.ListaPreferiti.ServiceListaPreferiti.ListaPreferitiImpl;
import Application.ListaPreferiti.ServiceListaPreferiti.ListaPreferitiService;
import Application.MaterialeDidattico.ServiceMaterialeDidattico.MaterialeDidatticoService;
import Application.MaterialeDidattico.ServiceMaterialeDidattico.MaterialeDidatticoServiceImpl;
import Application.Utente.ServiceUtente.UtenteService;
import Application.Utente.ServiceUtente.UtenteServiceImpl;
import Storage.Corso.CorsoBean;
import Storage.MaterialeDidattico.MaterialeDidatticoBean;
import Storage.Utente.UtenteBean;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Servlet dove utilizziamo i metodi per il singolo Materiale
 */
@WebServlet(name = "MaterialeDidatticoServlet", value = "/Materiale/*")
@MultipartConfig
public class MaterialeDidatticoServlet extends HttpServlet {

    private final MaterialeDidatticoService materialeDidattico = new MaterialeDidatticoServiceImpl();
    private final CorsoService corsoService = new CorsoServiceImpl();
    private final UtenteService utenteService = new UtenteServiceImpl();
    private final ListaPreferitiService listaPreferitiService = new ListaPreferitiImpl();

    /**
     * Metodo doGet della servlet MaterialeDidattico
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = (request.getPathInfo() != null) ? request.getPathInfo() : "/";
        switch (path){
            /*
            case "/inserireMateriale":{
                request.getRequestDispatcher("/WEB-INF/interface/interfacciaMateriale/crea.jsp").forward(request,response);
                break;
            }*/

            /**
             *  Caso per eliminare del materiale
             */
            case "/elimina":{
                HttpSession ssn = request.getSession();
                UtenteBean u = (UtenteBean) ssn.getAttribute("utente");
                if(u == null){
                    response.sendRedirect("/UniNotes_war_exploded/");
                    break;
                }
                int id = Integer.parseInt(request.getParameter("id"));
                if(materialeDidattico.eliminaMateriale(id)){
                    response.sendRedirect("/UniNotes_war_exploded/Materiale/visualizzaTutti");
                    break;
                }
                response.sendRedirect("/UniNotes_war_exploded/Libretto/visualizzaLibretto");//pagina di errore
                break;

            }
            /*
            case "/modificaMateriale":{
                request.getRequestDispatcher("/WEB-INF/interface/interfacciaMateriale/modifica.jsp").forward(request,response);
                break;
            }*/

            /**
             *  Caso per visualizzare del materiale
             */
            case "/visualizza":{
                HttpSession ssn = request.getSession();
                UtenteBean u = (UtenteBean) ssn.getAttribute("utente");
                if(u == null){
                    response.sendRedirect("/UniNotes_war_exploded/");
                    break;
                }
                if(u.isTipo()){
                    request.setAttribute("materiale",materialeDidattico.visualizzaTutti());
                }else{
                    request.setAttribute("materiale",materialeDidattico.visualizzaMaterialeDiUnUtente(u.getIdUtente()));
                }
                request.getRequestDispatcher("/WEB-INF/interface/interfacciaMateriale/visualizza.jsp").forward(request,response);
                break;
            }

            /**
             *  Caso per visualizzare tutti i materiali
             */
            case "/visualizzaTutti":{
                HttpSession ssn = request.getSession();
                UtenteBean u = (UtenteBean) ssn.getAttribute("utente");
                if(u == null){
                    response.sendRedirect("/UniNotes_war_exploded/");
                    break;
                }
                if(u.isTipo()){
                    HashMap<String,ArrayList<MaterialeDidatticoBean>> hashMapCorsiMateriale = new HashMap<>();
                    for(CorsoBean c : corsoService.visualizzaCorsi()){
                        for(MaterialeDidatticoBean m : materialeDidattico.visualizzaMaterialeDiUnCorso(c.getId())){
                            if(!hashMapCorsiMateriale.containsKey(c.getNome())){
                                hashMapCorsiMateriale.put(c.getNome(),new ArrayList<>());
                            }
                            hashMapCorsiMateriale.get(c.getNome()).add(m);
                        }
                    }
                    request.setAttribute("hashMapCorsiMateriale",hashMapCorsiMateriale);
                    request.setAttribute("materiale",materialeDidattico.visualizzaTutti());

                }else{
                    HashMap<String,ArrayList<MaterialeDidatticoBean>> hashMapCorsiMateriale = new HashMap<>();
                    ArrayList<MaterialeDidatticoBean> materialeDidatticoBeans = materialeDidattico.visualizzaMaterialeDiUnUtente(u.getIdUtente());
                    for(CorsoBean c : corsoService.visualizzaCorsi()){
                        for(MaterialeDidatticoBean m : materialeDidattico.visualizzaMaterialeDiUnCorso(c.getId())){
                            if(utenteService.verificaProprioMateriale(materialeDidatticoBeans,m)){
                                if(!hashMapCorsiMateriale.containsKey(c.getNome())){
                                    hashMapCorsiMateriale.put(c.getNome(),new ArrayList<>());
                                }
                                hashMapCorsiMateriale.get(c.getNome()).add(m);
                            }
                        }
                    }
                    request.setAttribute("hashMapCorsiMateriale",hashMapCorsiMateriale);
                    request.setAttribute("materiale",materialeDidattico.visualizzaMaterialeDiUnUtente(u.getIdUtente()));
                }





                //ho cambiato la path per farlo andare nella dashboard --> bisogna rivederla
                request.getRequestDispatcher("/WEB-INF/interface/interfacciaUtente/dashboard/materiale.jsp").forward(request,response);
                break;
            }
        }
    }

    /**
     * Metodo doPost della servlet MaterialeDidattico
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
             *  Caso per inserire del materiale
             */
            case "/inserireMateriale":{
                HttpSession ssn = request.getSession();
                UtenteBean u = (UtenteBean) ssn.getAttribute("utente");
                if(u == null){
                    response.sendRedirect("/UniNotes_war_exploded/");
                    break;
                }
                String nomePattern = "[a-zA-Z\\s]+$";
                String idCorso = request.getParameter("idCorso");
                int idUtente = u.getIdUtente();
                String nome = request.getParameter("Nome");
                Part filePart = request.getPart("File");
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

                ArrayList<String> errors = new ArrayList<>();
                ArrayList<String> success = new ArrayList<>();
/*
                if(materialeDidattico.inserireMateriale(nome,fileName,Integer.parseInt(idCorso),idUtente)){
                    String uploadRoot = "/Users/piosantosuosso/Desktop/apache-tomcat-9.0.43/uploads/";

                    try (InputStream fileStream = filePart.getInputStream()) {
                        File file = new File(uploadRoot + fileName);
                        Files.copy(fileStream, file.toPath());
                        success.add("Salvataggio avvenuto con successo");
                        ssn.setAttribute("success",success);
                        request.setAttribute("idCorso",idCorso);
                        request.getRequestDispatcher("/Corso/visualizza").forward(request,response);
                        break;
                    }catch (IOException e){//se il file esiste gia
                        System.out.println("qui");
                        success.add(e.getMessage());
                        ssn.setAttribute("success",success);
                        request.setAttribute("idCorso",idCorso);
                        request.getRequestDispatcher("/Corso/visualizza").forward(request,response);
                        break;
                    }
               }
*/

                if(!nome.matches(nomePattern)){
                    errors.add("Nome non valido");
                }


                String uploadRoot = "/Users/piosantosuosso/Desktop/apache-tomcat-9.0.43/uploads/";
                if(nome.matches(nomePattern) && fileName.length()>0){
                    try (InputStream fileStream = filePart.getInputStream()) {
                        File file = new File(uploadRoot + fileName);
                        Files.copy(fileStream, file.toPath());
                        materialeDidattico.inserireMateriale(nome,fileName,Integer.parseInt(idCorso),idUtente);

                        CorsoBean c = corsoService.visualizzaCorso(Integer.parseInt(idCorso));
                        c.setListaMateriale(materialeDidattico.visualizzaMaterialeDiUnCorso(Integer.parseInt(idCorso)));
                        c.setObservers(listaPreferitiService.visualizzaListaCorso(Integer.parseInt(idCorso)));
                        c.aggiungiMateriale(new MaterialeDidatticoBean(nome,fileName));

                        success.add("Salvataggio avvenuto con successo");
                        ssn.setAttribute("success",success);
                        request.setAttribute("idCorso",idCorso);
                        request.getRequestDispatcher("/Corso/visualizza").forward(request,response);
                        break;
                    }catch (IOException e) {//se il file esiste gia
                        errors.add("Esiste gia un file chiamato : " + e.getMessage().split("/")[(e.getMessage().split("/").length) - 1]);
                        ssn.setAttribute("errore", errors);
                        request.setAttribute("idCorso", idCorso);
                        request.getRequestDispatcher("/Corso/visualizza").forward(request, response);
                        break;
                    }
                }else{
                    ssn.setAttribute("errore", errors);
                    request.setAttribute("idCorso", idCorso);
                    request.getRequestDispatcher("/Corso/visualizza").forward(request, response);
                    break;
                }


            }

            /*
            case "/eliminaMateriale":{
                String id = request.getParameter("id");
                materialeDidattico.eliminaMateriale(Integer.parseInt(id));
                request.getRequestDispatcher("/WEB-INF/interface/interfacciaMateriale/elimina.jsp").forward(request,response);
                break;
            }
          
             */

            /**
             *  Caso per modificare del materiale
             */
            case "/modificaMateriale":{
                HttpSession ssn = request.getSession();
                UtenteBean u = (UtenteBean) ssn.getAttribute("utente");
                if(u == null){
                    response.sendRedirect("/UniNotes_war_exploded/");
                    break;
                }
                String nomePattern = "[a-zA-Z\\s]+$";
                String idMateriale = request.getParameter("idMateriale");
                int idUtente = u.getIdUtente();
                String nome = request.getParameter("Nome");
                Part filePart = request.getPart("File");
                MaterialeDidatticoBean m = materialeDidattico.visualizza(Integer.parseInt(idMateriale));
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                ArrayList<String> errors = new ArrayList<>();
                ArrayList<String> success = new ArrayList<>();

                if(!nome.matches(nomePattern)){
                    errors.add("Nome non valido");
                }

                if(fileName.length()>0){
                    if(nome.matches(nomePattern) && materialeDidattico.modificaMateriale(m.getId(),nome,fileName)){
                        String uploadRoot = "/Users/piosantosuosso/Desktop/apache-tomcat-9.0.43/uploads/";

                        try (InputStream fileStream = filePart.getInputStream()) {
                            File file = new File(uploadRoot + fileName);
                            Files.copy(fileStream, file.toPath(),StandardCopyOption.REPLACE_EXISTING);
                            success.add("Caricamento effettuato con successo");
                            ssn.setAttribute("success", success);
                            response.sendRedirect("/UniNotes_war_exploded/Materiale/visualizzaTutti");
                            break;
                        }
                    }else{
                        ssn.setAttribute("errors", errors);
                        response.sendRedirect("/UniNotes_war_exploded/Materiale/visualizzaTutti");
                        break;
                    }
                }else{
                    if(nome.matches(nomePattern) && materialeDidattico.modificaMateriale(m.getId(),nome,m.getPathFile())){
                        success.add("Caricamento effettuato con successo");
                        ssn.setAttribute("success", success);
                        response.sendRedirect("/UniNotes_war_exploded/Materiale/visualizzaTutti");
                        break;
                    }else{
                        ssn.setAttribute("errors", errors);
                        response.sendRedirect("/UniNotes_war_exploded/Materiale/visualizzaTutti");
                        break;
                    }
                }
            }
        }


    }
}