package com.kiskos.Comunicaciones.Ejercicio2;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    //Primero tener el servidor iniciado para tener algo a lo que el cliente conectarse
    public static void main(String[] args){
        try{
            System.out.println("Creando socket servidor");

            ServerSocket serverSocket=new ServerSocket();

            System.out.println("Realizando el bind");

            InetSocketAddress addr=new InetSocketAddress("localhost",6666);
            serverSocket.bind(addr);

            System.out.println("Aceptando conexiones");

            Socket newSocket= serverSocket.accept();

            System.out.println("Conexion recibida");

            InputStream is=newSocket.getInputStream();
            OutputStream os=newSocket.getOutputStream();

            byte[] mensaje=new byte[25];
            int numero=0;
            for(int i=0;i<5;i++) {
                numero = numero + is.read();
                System.out.println("Mensaje recibido: " + new String(mensaje));
                System.out.println("Fin");

            }
            System.out.println("Suma total: "+numero);
            System.out.println("Cerrando el nuevo socket");

            newSocket.close();

            System.out.println("Cerrando el socket servidor");

            serverSocket.close();

            System.out.println("Terminado");

        }catch (IOException e) {
        }
    }
}
