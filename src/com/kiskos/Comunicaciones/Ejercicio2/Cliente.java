package com.kiskos.Comunicaciones.Ejercicio2;
import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Random;

public class Cliente {
    public static void main(String[] args){
        try{
            System.out.println("Creando socket cliente");
            Socket clienteSocket=new Socket();
            System.out.println("Estableciendo la conexion");

            InetSocketAddress addr=new InetSocketAddress("localhost",6666);
            clienteSocket.connect(addr);

            InputStream is = clienteSocket.getInputStream();
            OutputStream os= clienteSocket.getOutputStream();

            int numero;
            for(int i=0; i<5;i++) {

                System.out.println("Enviando mensaje");
                numero= (int) Math.floor(Math.random()*10);
                System.out.println(numero);
                os.write(numero);
                System.out.println("Mensaje enviado");
            }
            System.out.println("Cerrando el socket cliente");

            clienteSocket.close();

            System.out.println("Terminado");

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
