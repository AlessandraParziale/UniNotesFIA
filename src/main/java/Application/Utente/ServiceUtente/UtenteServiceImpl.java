package Application.Utente.ServiceUtente;




import Storage.Libretto.LibrettoBean;
import Storage.ListaPreferiti.ListaPreferitiDao;
import Storage.MaterialeDidattico.MaterialeDidatticoBean;
import Storage.Utente.UtenteBean;
import Storage.Utente.UtenteDao;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Implementa la classe che esplicita i metodi
 * definiti nell'interfaccia service
 */
public class UtenteServiceImpl implements UtenteService {

    /**
     * Implementa la funzionalità di registrazione di un singolo utente
     * @param nome
     * @param cognome
     * @param cf
     * @param email
     * @param password
     * @param data
     * @param l
     * @return un UtenteBean
     * @throws SQLException
     * @throws NoSuchAlgorithmException
     */
    public UtenteBean registrazione(String nome, String cognome, String cf, String email, String password, String data, LibrettoBean l) throws SQLException, NoSuchAlgorithmException {
        UtenteBean utente = new UtenteBean();
        UtenteDao ud = new UtenteDao();

        utente.setNome(nome);
        utente.setCognome(cognome);
        utente.setCf(cf);
        utente.setEmail(email);
        utente.setPassword(password);
        utente.setDdn(LocalDate.parse(data));
        utente.setLibretto(l);

        utente = ud.doSave(utente);
        return utente;

    }

    /**
     * Implementa la funzionalità di login di un singolo utente
     * @param email
     * @param password
     * @return un UtenteBean
     */
    public UtenteBean login(String email, String password) {
        UtenteDao ud = new UtenteDao();
        return ud.findAccount(email, password);
    }

    /**
     * Implementa la funzionalità di aggiornamento di un singolo utente
     * @param u
     * @return un UtenteBean
     */
    @Override
    public UtenteBean aggiorna(UtenteBean u) {
        UtenteDao ud = new UtenteDao();
        if(ud.doUpdate(u))
            return u;
        return null;
    }


    /*
    public void interireInListaPreferiti(int u, int c) {
        ListaPreferitiDao ld = new ListaPreferitiDao();
        ld.doSave(u,c);
    }

    public void rimuoviDaListaPreferiti(int idUtente,int idCorso) {
        ListaPreferitiDao ld = new ListaPreferitiDao();
        ld.doDelateCorso( idUtente, idCorso);
    }
*/

    /**
     * Implementa la funzionalità di verifica del materiale di un singolo utente
     * @param materiale
     * @param m
     * @return un boolean true se il materiale è presente, false altrimenti
     */
    @Override
    public boolean verificaProprioMateriale(ArrayList<MaterialeDidatticoBean> materiale, MaterialeDidatticoBean m) {
        for(MaterialeDidatticoBean ma : materiale){
            if(ma.getId() == m.getId())
                return true;
        }
        return false;
    }

    /**
     * Implementa la funzionalità di visualizzaizione di tutti gli utenti
     * @return un ArrayList di UtenteBean
     */
    public ArrayList<UtenteBean> visualizzaUtenti(){
        ArrayList<UtenteBean> utenti = new ArrayList<>();
        UtenteDao ud = new UtenteDao();
        utenti = ud.doRetriveAll();
        return utenti;
    }

    /**
     *
     * @param
     * @return
     */
    public String magistrale(int id) {
        System.out.println("utente 4.1");
        UtenteDao ud = new UtenteDao();
        System.out.println("utente 5");
        return ud.doRetriveMagistrale(id);
    }

    /**
     * Implementa la funzionalità di rendere amministratore un utente
     * @param u
     */
    public void rendiAdmin(UtenteBean u) {
        UtenteDao ud = new UtenteDao();
        ud.doUpdateAdmin(u);
    }

    /**
     * Implementa la funzionalità di salvare la magistrale
     * @param u
     */
    public void salvaMagistrale(UtenteBean u) throws SQLException {
        UtenteDao ud = new UtenteDao();
        ud.salvaMagistrale(u);
    }

}
