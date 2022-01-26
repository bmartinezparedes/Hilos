package com.kiskos.Comunicaciones.Ejercicio1;
import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
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


            String mensaje="";
            int tamaño;
            byte[] men=new byte[25];
            while(!mensaje.equalsIgnoreCase("Fin")) {

                System.out.println("Enviando mensaje");
                mensaje = JOptionPane.showInputDialog("Habla lo que quieras. \nPara finalizar pon 'Fin'");
                tamaño = mensaje.length();
                os.write(tamaño);
                os.write(mensaje.getBytes());
                System.out.println("Mensaje enviado");
                if(!mensaje.equalsIgnoreCase("Fin")) {
                    tamaño = is.read();
                    men = new byte[tamaño];
                    is.read(men);
                    System.out.println("Mensaje recibido: " + new String(men));
                    mensaje=new String(men);
                    System.out.println("Fin");
                }
            }
            System.out.println("Cerrando el socket cliente");

            clienteSocket.close();

            System.out.println("Terminado");

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
