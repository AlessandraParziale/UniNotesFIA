package Storage.Utente;

import Application.util.EmailSender;
import Application.util.Observer;
import Application.util.Subject;
import Storage.Corso.CorsoBean;
import Storage.Libretto.LibrettoBean;
import Storage.ListaPreferiti.ListaPreferitiBean;
import Storage.MaterialeDidattico.MaterialeDidatticoBean;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Questa classe rappresenta un Utente.
 * Un utente possiede un nome,un cognome, il cf, l'email
 * la data di nascita, la password, il tipo: se è amministratore
 * oppure studente, il libretto, la list
 * Questa classe estende Observer che invierà un email alla modifica
 * dello stato di materiale, ogni qual volta un utente inserirà nuovo materiale
 * La notifica sarà inviata solo se l'utente avrà nella lista dei preferiti
 * il corso in cui tale materiale è aggiunto
 */

public class UtenteBean implements Observer{
    private int idUtente;
    private String nome;
    private String cognome;
    private String cf;
    private String email;
    private LocalDate ddn;
    private String password;
    private boolean tipo;
    private LibrettoBean libretto;
    private ListaPreferitiBean listaPreferiti;
    private ArrayList<MaterialeDidatticoBean> materiale;
    private String magistrale;

    /**
     * Costruttore di UtenteBean
     */
    public UtenteBean(){ }

    /**
     * Costruttore di UtenteBean
     * @param nome è il nome dell'utente
     * @param cognome è il cognome dell'utente
     * @param cf è il cofice fiscale dell'utente
     * @param email dell'utente
     * @param ddn la data di nascita dell'utente
     * @param password dell'utente
     * @param tipo dell'utente che lo distingue
     * l'amministratore e lo studente
     */
    public UtenteBean(String nome, String cognome, String cf, String email, LocalDate ddn, String password, boolean tipo) {
        this.nome = nome;
        this.cognome = cognome;
        this.cf = cf;
        this.email = email;
        this.ddn = ddn;
        this.password = password;
        this.tipo = tipo;
    }
    /**
     * Costruttore di UtenteBean
     * @param nome è il nome dell'utente
     * @param cognome è il cognome dell'utente
     * @param cf è il cofice fiscale dell'utente
     * @param email dell'utente
     * @param ddn la data di nascita dell'utente
     * @param password dell'utente
     * @param tipo dell'utente che lo distingue
     * l'amministratore e lo studente
     * @param idUtente id dell'utente
     */
    public UtenteBean(int idUtente, String nome, String cognome, String cf, String email, LocalDate ddn, String password, boolean tipo) {
        this.idUtente = idUtente;
        this.nome = nome;
        this.cognome = cognome;
        this.cf = cf;
        this.email = email;
        this.ddn = ddn;
        this.password = password;
        this.tipo = tipo;
    }

    /**
     *
     * Costruttore di UtenteBean
     * @param nome è il nome dell'utente
     * @param cognome è il cognome dell'utente
     * @param cf è il cofice fiscale dell'utente
     * @param email dell'utente
     * @param ddn la data di nascita dell'utente
     * @param password dell'utente
     * @param tipo dell'utente che lo distingue
     * l'amministratore e lo studente
     * @param magistarle è la predizione di una futura magistale
     */
    public UtenteBean(String nome, String cognome, String cf, String email, LocalDate ddn, String password, boolean tipo, String magistarle) {
        this.nome = nome;
        this.cognome = cognome;
        this.cf = cf;
        this.email = email;
        this.ddn = ddn;
        this.password = password;
        this.tipo = tipo;
        this.magistrale=magistarle;
    }

    /**
     * Costruttore di UtenteBean
     * @param nome è il nome dell'utente
     * @param cognome è il cognome dell'utente
     * @param cf è il cofice fiscale dell'utente
     * @param email dell'utente
     * @param ddn la data di nascita dell'utente
     * @param password dell'utente
     * @param tipo dell'utente che lo distingue
     * l'amministratore e lo studente
     * @param idUtente id dell'utente
     * @param libretto è il libretto dell'utente
     */
    public UtenteBean(int idUtente, String nome, String cognome, String cf, String email, LocalDate ddn, String password, boolean tipo,LibrettoBean libretto) {
        this.idUtente = idUtente;
        this.nome = nome;
        this.cognome = cognome;
        this.cf = cf;
        this.email = email;
        this.ddn = ddn;
        this.password = password;
        this.tipo = tipo;
        this.libretto = libretto;
    }

    /**
     * Costruttore di UtenteBean
     * @param nome è il nome dell'utente
     * @param cognome è il cognome dell'utente
     * @param cf è il cofice fiscale dell'utente
     * @param email dell'utente
     * @param ddn la data di nascita dell'utente
     * @param password dell'utente
     * @param tipo dell'utente che lo distingue
     * l'amministratore e lo studente
     * @param idUtente id dell'utente
     * @param libretto è il libretto dell'utente
     * @param listaPreferiti è la lista dei corsi preferiti dell'utente
     */

    public UtenteBean(int idUtente, String nome, String cognome, String cf, String email, LocalDate ddn, String password, boolean tipo,LibrettoBean libretto,ListaPreferitiBean listaPreferiti) {
        this.idUtente = idUtente;
        this.nome = nome;
        this.cognome = cognome;
        this.cf = cf;
        this.email = email;
        this.ddn = ddn;
        this.password = password;
        this.tipo = tipo;
        this.libretto = libretto;
        this.listaPreferiti = listaPreferiti;
    }

    /**
     * Metodo che restituisce il libretto
     * @return il libretto
     */

    public LibrettoBean getLibretto() {
        return libretto;
    }

    /**
     * Metodo che restituisce la lista preferita
     * @return la lista preferita
     */

    public ListaPreferitiBean getListaPreferiti() {
        return listaPreferiti;
    }

    /**
     * Metodo che restituisce il materiale
     * @return una lista di materiale didattico
     */
    public ArrayList<MaterialeDidatticoBean> getMateriale() {
        return materiale;
    }

    /**
     * Metodo che setta il materiale
     * @param materiale è una lista di materiale didattico
     */
    public void setMateriale(ArrayList<MaterialeDidatticoBean> materiale) {
        this.materiale = materiale;
    }

    /**
     * Metodo che restituisce il codice fiscale
     * @return il codice fiscale dell'utente
     */
    public String getCf() {
        return cf;
    }

    /**
     * Metodo che setta il codice fiscale
     * @param cf è il codice fiscale dell'utente
     */
    public void setCf(String cf) {
        this.cf = cf;
    }

    /**
     * Metodo che setta l'id
     * @param idUtente è l'id dell'utente
     */
    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    /**
     * Metodo che setta il nome dell'utente
     * @param nome è il nome dell'utente
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Metodo che setta il nome dell'utente
     * @param cognome è il cognome dell'utente
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * Metodo che setta l'email
     * @param email è l'email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Metodo che setta la data di nascita
     * @param ddn è la data di nascita
     */
    public void setDdn(LocalDate ddn) {
        this.ddn = ddn;
    }

    /**
     * Metodo che setta la password
     * @param password è la password
     * @throws NoSuchAlgorithmException
     */
    public void setPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-512");
        byte[] hashedPad = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        StringBuilder builder = new StringBuilder();
        for(byte bit : hashedPad){
            builder.append(String.format("%02x", bit)); //inseriemo in ogni bit del bit formattata "%02x" caratteri miniuscoli
        }
        this.password = builder.toString();
    }

    /**
     * Metodo che setta la password
     * @param password la password
     */
    public void setPss(String password){
        this.password = password;
    }

    /**
     * Metodo che setta il tipo
     * @param tipo è il tipo di utente( amministratore, studente)
     */
    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    /**
     * Metodo che restituisce l'id dell'utente
     * @return l'id dell'utente
     */
    public int getIdUtente() {
        return idUtente;
    }

    /**
     *  Metodo che restituisce il nome
     * @return il nome dell'utente
     */
    public String getNome() {
        return nome;
    }

    /**
     * Metodo che restituisce il cognome
     * @return il cognome dell'utente
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Metodo che restituisce l'email
     * @return l'email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Metodo che restituisce la data di nascita
     * @return la data di nascita
     */
    public LocalDate getDdn() {
        return ddn;
    }

    /**
     * Metodo che restituisce la password
     * @return la password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Metodo che restituisce il tipo
     * @return true se è amministratore, false se è studente
     */
    public boolean isTipo() {
        return tipo;
    }

    /**
     * Metodo che setta il libretto dell'utente
     * @param libretto è il libretto dell'utente
     */
    public void setLibretto(LibrettoBean libretto) {
       this.libretto = libretto;
       if(this.libretto.getListaEsami()!=null){
           this.libretto.aggiorna();
       }
    }

    /**
     * Metodo che setta la lista dei corsi preferiti  dell'utente
     * @param listaPreferiti è la lista dei corsi preferiti dell'utente
     */
    public void setListaPreferiti(ListaPreferitiBean listaPreferiti) {
        for(CorsoBean c : listaPreferiti.getCorsi()){
            c.addObserver(this);
        }
        this.listaPreferiti = listaPreferiti;
    }

    /**
     * Metodo invocato quando c'è una modifica
     * dello stato del Subject.
     */
    @Override
    public void update() {
        EmailSender emailSender = EmailSender.GetInstance();
        emailSender.SendEmail("Aggiornamento corso", "Gentile " + this.nome + ", è stata aggiunto del nuovo materiale. Saluti da UniNotes.", this.email);
    }

    /**
     * metodo per restituire una predizione
     * @return una stringa che si riferisce alla magistarle ottenuta dalla predizione
     */
    public String getMagistrale() {
        return magistrale;
    }

}
