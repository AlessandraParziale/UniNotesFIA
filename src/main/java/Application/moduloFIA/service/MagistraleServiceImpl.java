package Application.moduloFIA.service;

import Application.moduloFIA.service.AdapterAI.PythonAdapter;
import Application.moduloFIA.service.AdapterAI.PythonAdapterImpl;

import java.util.ArrayList;
import java.util.List;

public class MagistraleServiceImpl implements MagistraleService{
    /**
     * Si occupa della chiamata al modulo di IA.
     */
    private final PythonAdapter pythonAdapter = new PythonAdapterImpl();

    /**
     * Implementa la funzionalità di chiamare lo script di Python
     * che effettua le predizioni.
     * @return la lista di domande
     */

    @Override
    public ArrayList<String> predizione(String d1, String d2, String d3, String d4, String d5, String d6, String d7, String d8) {
        ArrayList risposte = pythonAdapter.getAIPrediction(d1, d2, d3, d4, d5, d6, d7, d8);

        return risposte;
    }
}
