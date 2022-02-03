package Application.moduloFIA.service;

import Application.moduloFIA.service.AdapterAI.PythonAdapter;
import Application.moduloFIA.service.AdapterAI.PythonAdapterImpl;

import java.util.ArrayList;

public interface MagistraleService {

    ArrayList<String> predizione(String d1, String d12, String d3, String d4, String d5, String d6, String d7, String d8);

}
