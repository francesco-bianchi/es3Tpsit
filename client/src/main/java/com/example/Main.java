package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnknownHostException, IOException {
        Socket socket = new Socket("localhost", 3000);
        
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        String opzione = "";
        String frase = "";
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Scegliere un'opzione (da 1 a 4), inserire 0 per terminare l'esecuzione");
            opzione = sc.nextLine();
            if(opzione.equals("0")){
                out.writeBytes("!" + "\n");
            }
            else{
                System.out.println("Inserire una frase");
                frase = sc.nextLine();
                out.writeBytes(opzione + "\n");
                out.writeBytes(frase + "\n");
                String stringaRic = in.readLine();
                System.out.println("La stringa ricevuta è: " + stringaRic);
            }

        } while (!opzione.equals("0"));
        socket.close();
    }
}