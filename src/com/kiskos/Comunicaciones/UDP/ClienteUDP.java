package com.kiskos.Comunicaciones.UDP;
import java.net.*;
import java.io.*;

public class ClienteUDP {

    // Os argumentos proporcionan a mensaxe e o nome do servidor
    public static void main(String args[]) {

        try {
            DatagramSocket socketUDP = new DatagramSocket();
            byte[] mensaje = args[0].getBytes();
            InetAddress hostServidor = InetAddress.getByName(args[1]);
            int puertoServidor = 6789;

            // Construimos un datagrama para enviar a mensaxe ao servidor
            DatagramPacket peticion =
                    new DatagramPacket(mensaje, args[0].length(), hostServidor,
                            puertoServidor);

            // Enviamos o datagrama
            socketUDP.send(peticion);

            // Construimos o DatagramPacket que contendrá a resposta
            byte[] bufer = new byte[1000];
            DatagramPacket resposta =
                    new DatagramPacket(bufer, bufer.length);
            socketUDP.receive(resposta);

            // Enviamos a respuesta do servidor á salida estándar
            System.out.println("Resposta: " + new String(resposta.getData()));

            // Pechamos o socket
            socketUDP.close();

        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
    }
}
