package com.kiskos.Comunicaciones.chat;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import static com.kiskos.Comunicaciones.chat.Chats.is;
import static com.kiskos.Comunicaciones.chat.Chats.mostar;
/**
 *
 * @author Braiskiskos
 * @version 2022.3.17
 */

/**
 * En esta clase se crea al cliente y se le pasan parametros a la clase Chats inicializada y se ejecuta un hilo que
 * leera todo_ lo que nos envie el servidor
 */
public class Cliente {
    public static void main(String[] args){
        String[] puertoIp;
        String ipPuerto;
        int contador=0;
        try{
            System.out.println("Creando socket cliente");
            Socket clienteSocket=new Socket();
            System.out.println("Estableciendo la conexion");
            boolean ipPuertoBien=false;
            do{
                if(contador== 0) {
                    ipPuerto = JOptionPane.showInputDialog("Introduce IP y Puerto");
                    puertoIp = ipPuerto.replace(" ", "").split(":");
                }else if(contador<3&&contador>0){
                    ipPuerto = JOptionPane.showInputDialog("Introduce IP y Puerto\nEjemplo->localhost:6666");
                    puertoIp = ipPuerto.replace(" ", "").split(":");
                }else{
                    ipPuerto = JOptionPane.showInputDialog("Pon esto -> localhost:6666");
                    puertoIp = ipPuerto.replace(" ", "").split(":");
                }
                if(puertoIp[0].equalsIgnoreCase("localhost")&&puertoIp[1].equalsIgnoreCase("6666")){
                    ipPuertoBien=true;
                }
                contador++;
            }while(!ipPuertoBien);


            InetSocketAddress addr=new InetSocketAddress(puertoIp[0],Integer.parseInt(puertoIp[1]));
            clienteSocket.connect(addr);

            InputStream is = clienteSocket.getInputStream();
            OutputStream os= clienteSocket.getOutputStream();
            String nick = JOptionPane.showInputDialog("Introduce Nick: ");
            os.write(nick.length());
            os.write(nick.getBytes());

            Chats c = new Chats(is,os,clienteSocket,nick);
            c.setVisible(true);

            new leer().start();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Clase leer que nos servira para leer todo_ lo que nos envie el server y mostrarlo en nuestro panel grafico para
     * que el usuario sea capaz de visualizar los mensajes y seguir interactuando
     */
    public static class leer extends Thread{
        public leer(){}
        public void run(){
            while(true){
                try {
                    System.out.println("Hilo dormir");
                    sleep(1000);
                    System.out.println("hilo leer");
                    byte[] mensaje=new byte[25];
                    while(!new String(mensaje).equalsIgnoreCase("/Fin")) {
                        System.out.println("antes de leer");
                        int tamaño = is.read();
                        mensaje = new byte[tamaño];
                        is.read(mensaje);
                        mostar.setText(mostar.getText() +
                                    "\n" + new String(mensaje));
                        System.out.println("leido");
                        System.out.println("Mensaje recibido: " + new String(mensaje));
                        System.out.println("Fin Mensaje");
                    }
                    sleep(1000L);
                    System.exit(0);
                } catch (SocketException desconexionServer){
                    mostar.setText(mostar.getText()+"\n"+"Servidor se ha desconectado");
                    try {
                        sleep(5000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    apagar();
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private static void apagar(){
        System.exit(0);
    }
}
