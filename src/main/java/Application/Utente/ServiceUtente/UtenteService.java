package Application.Utente.ServiceUtente;


import Storage.Libretto.LibrettoBean;
import Storage.MaterialeDidattico.MaterialeDidatticoBean;
import Storage.Utente.UtenteBean;
import Storage.Utente.UtenteDao;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Interfaccia per i metodi del sottosistema Utente
 */
public interface UtenteService {

    /**
     * Firma del metodo che implementa la funzionalità di registrazione di un singolo utente
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
    public UtenteBean registrazione(String nome, String cognome, String cf, String email, String password, String data, LibrettoBean l) throws SQLException, NoSuchAlgorithmException;

    /**
     * Implementa la funzionalità di login di un singolo utente
     * @param email
     * @param password
     * @return un UtenteBean
     */
    public UtenteBean login(String email, String password);

    /**
     * Implementa la funzionalità di aggiornamento di un singolo utente
     * @param u
     * @return un UtenteBean
     */
    public UtenteBean aggiorna(UtenteBean u);
/*
    public void interireInListaPreferiti(int u, int c);

    public  void rimuoviDaListaPreferiti(int idUtente,int idCorso);
*/
    /**
     * Implementa la funzionalità di verifica del materiale di un singolo utente
     * @param materiale
     * @param m
     * @return un boolean true se il materiale è presente, false altrimenti
     */
    public boolean verificaProprioMateriale(ArrayList<MaterialeDidatticoBean> materiale, MaterialeDidatticoBean m);

    /**
     * Implementa la funzionalità di visualizzaizione di tutti gli utenti
     * @return un ArrayList di UtenteBean
     */
    public ArrayList<UtenteBean> visualizzaUtenti();

    /**
     * Implementa la funzionalità di rendere amministratore un utente
     * @param u
     */
    public void rendiAdmin(UtenteBean u);

    /**
     * Implementa la funzionalità di salvare la magistrale
     * @param u
     */
    public void salvaMagistrale(UtenteBean u) throws SQLException;

    public String magistrale(UtenteBean u);


}
