package org.example;

import java.net.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

public class HttpServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        Socket clientSocket = null;
        while (true) {

            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));
            String inputLine, outputLine;

            while ((inputLine = in.readLine()) != null) {

                System.out.println("Received: " + inputLine);
                if (!in.ready()) {
                    break;
                }
            }


            outputLine = "HTTP/1.1 200 OK\r\n"
                    + "Content-Type: text/html\r\n"
                    + "\r\n"
                    + getForm();

            out.println(outputLine);

            out.close();
            in.close();
            clientSocket.close();

        }
        //serverSocket.close();
    }

    //public static String getForm2() {
    //    return Lectura.leer("index.html");


    public static String getForm() {

        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "    <head>\n" +
                "        <title>Form Example</title>\n" +
                "        <meta charset=\"UTF-8\">\n" +
                "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    </head>\n" +
                "    <body>\n" +
                "        <h1>Form with GET</h1>\n" +
                "        <form action=\"/hello\">\n" +
                "            <label for=\"name\">Name:</label><br>\n" +
                "            <input type=\"text\" id=\"name\" name=\"name\" value=\"John\"><br><br>\n" +
                "            <input type=\"button\" value=\"Submit\" onclick=\"loadGetMsg()\">\n" +
                "        </form> \n" +
                "        <div id=\"getrespmsg\"></div>\n" +
                "\n" +
                "        <script>\n" +
                "             function loadGetMsg() { \n" +
                "    console.log(\"ANDREEEEEEEEEEEEEEEEEEEEEEE\"); \n" +
                "    let vector_result = {}; \n" +
                "    let nameVar = document.getElementById(\"name\").value; \n" +
                "    fetch(\"https://www.omdbapi.com/?t=\"+nameVar+\"&apikey=925c120b\")\n" +
                "        .then(results => results.json()).then(data => vector_result = data).then(data => { \n" +
                "        var jsonStr = JSON.stringify(data, null, 4); \n" +
                "        const table_elements = document.querySelector('#table_elements') \n" +
                "        delete data ['Meta Data']; \n" +
                "        table_elements.innerHTML = jsonStr; \n" +
                "    })\n" +
                "}\n" +
                "        </script>\n" +
                "\n" +
                "        <h1>Form with POST</h1>\n" +
                "        <form action=\"/hellopost\">\n" +
                "            <label for=\"postname\">Name:</label><br>\n" +
                "            <input type=\"text\" id=\"postname\" name=\"name\" value=\"John\"><br><br>\n" +
                "            <input type=\"button\" value=\"Submit\" onclick=\"loadPostMsg(postname)\">\n" +
                "        </form>\n" +
                "        \n" +
                "        <div id=\"postrespmsg\"></div>\n" +
                "        \n" +
                "       < table cellspacing = \"4\" width = \"90%\" >\n" +
                "         <pre id = \"table_elements\" >\n" +
                "       </pre >\n" +
                "      </table >\n" +
                "    </body>\n" +
                "</html>";
    }
}