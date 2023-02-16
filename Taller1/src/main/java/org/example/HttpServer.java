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
        outputLine = "";
        Path file = Paths.get("src/main/resources/index.html");
        System.out.println(file);
        try (InputStream in2 = Files.newInputStream(file);
             BufferedReader reader =
                     new BufferedReader(new InputStreamReader(in2))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                outputLine += line;
            }
        } catch (NoSuchFileException x) {
            } catch (IOException e) {
        }
        out.println(outputLine);

        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}