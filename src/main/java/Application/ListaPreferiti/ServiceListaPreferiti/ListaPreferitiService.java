package Application.ListaPreferiti.ServiceListaPreferiti;

import Storage.Corso.CorsoBean;
import Storage.ListaPreferiti.ListaPreferitiBean;
import Storage.MaterialeDidattico.MaterialeDidatticoBean;
import Storage.Utente.UtenteBean;

import java.util.ArrayList;

public interface ListaPreferitiService {


    public ListaPreferitiBean visualizzaListaPreferiti(int id);

    public ListaPreferitiBean visualizzaListaUtente(int idUtente);

    public ArrayList<UtenteBean> visualizzaListaCorso(int idCorso);

    public void inserisciInListaPreferiti(int idUtente, int idCorso);

    public void rimuoviDaListaPreferiti(int idUtente,int idCorso);

    public ArrayList<Integer> idCorsi(int idUtente);

}
