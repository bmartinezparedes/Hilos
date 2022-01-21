package com.kiskos.Comunicaciones.UDP;
import java.net.*;
import java.io.*;

public class ServidorUDP {

    public static void main (String args[]) {

        try {

            DatagramSocket socketUDP = new DatagramSocket(6789);
            byte[] bufer = new byte[1000];

            while (true) {
                // Construimos o DatagramPacket para recibir peticións
                DatagramPacket peticion =
                        new DatagramPacket(bufer, bufer.length);

                // Leemos una petición del DatagramSocket
                socketUDP.receive(peticion);

                System.out.print("Datagrama recibido do host: " +
                        peticion.getAddress());
                System.out.println(" desde o porto remoto: " +
                        peticion.getPort());

                // Construimos o DatagramPacket para enviar a resposta
                DatagramPacket resposta =
                        new DatagramPacket(peticion.getData(), peticion.getLength(),
                                peticion.getAddress(), peticion.getPort());

                // Enviamos a resposta, que é un eco
                socketUDP.send(resposta);
            }

        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
    }

}
