package com.valtsu;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    private Socket soketti = null;
    private DataInputStream input = null;
    private DataOutputStream out = null;

    // Konstruktori IP:n ja portin asettamiseksi
    public  Client(String osoite, int port) {
        //Luodaan yhteys
        try {
            soketti = new Socket(osoite, port);
            System.out.println("Yhdistetty");

            //Ottaa syötteen komentoriviltä
            input = new DataInputStream(System.in);

            //Lähettää outputin soketille
            out = new DataOutputStream((soketti.getOutputStream()));
        } catch (UnknownHostException u) {
            System.out.println(u);
        } catch (IOException i){
            System.out.println(i);
        }

        //String syötteen lukemiseksi
        String line = "";

        //Lukee syötettä kunnes näytöllä lukee "Over"
        while (!line.equals("Over")){
            try {
                line = input.readLine();
                out.writeUTF(line);
            } catch (IOException i) {
                System.out.println(i);
            }
        }

        //Suljetaan yhteys
        try {
            input.close();
            out.close();
            soketti.close();
        } catch (IOException i) {
            System.out.println(i);
        }
    }

    public static void main(String args[]) {

        Client client = new Client("127.0.0.1", 5000);
    }
}
