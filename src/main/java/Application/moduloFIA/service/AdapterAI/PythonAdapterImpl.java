package Application.moduloFIA.service.AdapterAI;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;



    /**
     *
     * Rappresenta l'interfaccia dello Adapter usata
     * dalle classi di UniNotes per la chiamata ad un API di Python
     *
     */
    public class PythonAdapterImpl implements PythonAdapter{

        @Override
        public List<String> getAIPrediction(String d1, String d2, String d3, String d4, String d5) {

            List<String> risposte = new ArrayList<>();
            try {
                URL url = new URL("http://127.0.0.1:5000/");

                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json");
                con.setRequestProperty("Accept", "application/json");
                con.setDoOutput(true);

                String jsonInputString = "{\"d1\": \"" + d1 +
                        "\", \"d2\": \"" + d2 +
                        "\", \"d3\": \"" + d3 +
                        "\", \"d4\": \"" + d4 +
                        "\", \"d5\": \"" + d5 + "\"}";

                System.out.println(jsonInputString);

                try(OutputStream os = con.getOutputStream()) {
                    byte[] input = jsonInputString.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }

                int status = con.getResponseCode();
                Reader sr = null;
                if (status > 299) {
                    sr = new InputStreamReader(con.getErrorStream());

                } else {
                    sr = new InputStreamReader(con.getInputStream());
                    BufferedReader bf = new BufferedReader(sr);
                    String inputLine;
                    StringBuilder stringBuilder = new StringBuilder();
                    while ((inputLine = bf.readLine()) != null) {
                        stringBuilder.append(inputLine);
                    }
                    bf.close();
                    con.disconnect();

                    JSONParser parser = new JSONParser();
                    Object obj = parser.parse(stringBuilder.toString());
                    JSONArray jsonData = (JSONArray) obj;

                    for(Object o : jsonData)
                        risposte.add(o.toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return risposte;
        }
    }

