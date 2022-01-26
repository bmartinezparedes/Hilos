package com.kiskos.Comunicaciones.Ejercicio1;
import javax.sound.midi.Soundbank;
import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.ServerSocket;
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
            String men="";
            while(!new String(mensaje).equalsIgnoreCase("Fin")) {
                int tamaño = is.read();
                mensaje = new byte[tamaño];
                is.read(mensaje);
                System.out.println("Mensaje recibido: " + new String(mensaje));
                System.out.println("Fin");
                if(!new String(mensaje).equalsIgnoreCase("Fin")) {
                    System.out.println("Enviando mensaje");
                    men = JOptionPane.showInputDialog("Habla lo que quieras. \nPara finalizar pon 'Fin'");
                    tamaño = men.length();
                    os.write(tamaño);
                    os.write(men.getBytes());
                    mensaje = men.getBytes();
                    System.out.println("Mensaje enviado");
                }
            }
            System.out.println("Cerrando el nuevo socket");

            newSocket.close();

            System.out.println("Cerrando el socket servidor");

            serverSocket.close();

            System.out.println("Terminado");

        }catch (IOException e) {
        }
    }
}
