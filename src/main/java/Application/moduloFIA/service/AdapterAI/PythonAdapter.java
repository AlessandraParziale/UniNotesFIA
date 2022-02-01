package Application.moduloFIA.service.AdapterAI;

import java.util.List;

public interface PythonAdapter {
    List<String> getAIPrediction(String d1, String d2, String d3, String d4, String d5);

}
