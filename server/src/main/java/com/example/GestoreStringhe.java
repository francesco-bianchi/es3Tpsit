
package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class GestoreStringhe extends Thread {
    Socket socket;

    GestoreStringhe(Socket socket){
        this.socket = socket;
    }

    public void run(){
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            String stringaRicOpz ="";

            do {
                stringaRicOpz = in.readLine();
                if (!stringaRicOpz.equals("!")) {
                    String fraseRic = in.readLine();
                    switch (stringaRicOpz) {
                        case "1":
                            String fraseMaiusc = fraseRic.toUpperCase();
                            out.writeBytes(fraseMaiusc + "\n");
                        break;
                        case "2":
                            String fraseMinusc = fraseRic.toLowerCase();
                            out.writeBytes(fraseMinusc + "\n");
                        break;
                        case "3":
                            StringBuilder strb = new StringBuilder(fraseRic);
                            String fraseRib = strb.reverse().toString();
                            out.writeBytes(fraseRib + "\n");
                        break;
                        case "4":
                            String lungFrase = "" +fraseRic.length();
                            out.writeBytes(lungFrase + "\n");
                        break;
                        default:
                            out.writeBytes("Inserire un numero da 1 a 4" + "\n");
                            break;
                    }
                }
            } while (!stringaRicOpz.equals("!"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Client disconnesso");
    }
}