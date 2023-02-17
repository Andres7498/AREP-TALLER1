package org.example;


 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.net.HttpURLConnection;
 import java.net.URL;

public class HttpConnection {

    private static final String USER_AGENT = "Mozilla/5.0";

    /**
     * Metodo encargado de la conexion HTTP y consulta el API segun el URL como parametro de entrada en el encabezado del metodo
     * @paramURL
     * @return
     * @throws IOException
     */
    public static String getAPI( ) throws IOException {


        URL obj = new URL("http://www.omdbapi.com/?i=tt3896198&apikey=925c120b");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        //The following invocation perform the connection implicitly before getting the code
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            return response.toString();
        } else {
            System.out.println("GET request not worked");
        }
        System.out.println("GET DONE");
        return "GET DONE";
    }



}
