package com.valtsu;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private Socket soketti = null;
    private ServerSocket serveri = null;
    private DataInputStream in = null;

    //Konstruktori jossa annetaan portti
    public Server(int port) {

        //Käynnistää serverin ja odottaa yhteyttä
        try {
            serveri = new ServerSocket(port);
            System.out.println("Serveri käynnistetty");

            System.out.println("Odotetaan clienttia...");

            soketti = serveri.accept();
            System.out.println("Client hyväksytty");

            //Ottaa syötteen clien soketilta
            in = new DataInputStream(
                    new BufferedInputStream(soketti.getInputStream()));

            String line = "";

            //Luetaan viestejä clientilta kunnes näytöllä lukee "Over"

            while (!line.equals("Over")) {
                try {
                    line = in.readUTF();
                    System.out.println(line);
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
            System.out.println("Suljetaan yhteys");

            // Suljetaan yhteys
            soketti.close();
            in.close();
        } catch (IOException i) {
            System.out.println(i);
        }
    }

    public static void main(String args[]) {
        Server server = new Server(5000);
    }

}
