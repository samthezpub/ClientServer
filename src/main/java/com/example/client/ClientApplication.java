package com.example.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.net.Socket;

@SpringBootApplication
public class ClientApplication {

    private static Socket clientSocket;
    private static BufferedReader reader;
    private static BufferedReader in;
    private static BufferedWriter out;

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);

        try {
            clientSocket = new Socket("localhost", 5007);

            reader = new BufferedReader(new InputStreamReader(System.in));

            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            System.out.println("Вы что-то хотели сказать? Введите это здесь:");
            String word = reader.readLine();

            out.write(word + "\n");
            out.flush();

            String serverWord = in.readLine();
            System.out.println(serverWord);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
