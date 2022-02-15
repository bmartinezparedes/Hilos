package com.kiskos.Comunicaciones.calculadoraServidor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    private static float segundoNumero=0.0f,primerNumero=0.0f;
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
            String operador="";
            while(!new String(mensaje).equalsIgnoreCase("Fin")) {
                int tamaño = is.read();
                mensaje = new byte[tamaño];
                is.read(mensaje);
                System.out.printf("Primer numero antes: "+primerNumero);
                System.out.println("Segundo numero antes: "+ segundoNumero);
                switch(new String(mensaje)){
                    case "igual":switch(operador){
                                    case "mas":primerNumero = quitarCero(primerNumero+segundoNumero);
                                        System.out.println(primerNumero);
                                        tamaño=String.valueOf(primerNumero).length();
                                        os.write(tamaño);
                                        os.write(String.valueOf(primerNumero).getBytes());
                                        break;
                                    case "menos":primerNumero =  quitarCero(primerNumero-segundoNumero);
                                        tamaño=String.valueOf(primerNumero).length();
                                        os.write(tamaño);
                                        os.write(String.valueOf(primerNumero).getBytes());
                                        break;
                                    case "entre":if(segundoNumero==0){
                                        System.out.println("ERROR");
                                        }else
                                        primerNumero = quitarCero(primerNumero/segundoNumero);
                                        tamaño=String.valueOf(primerNumero).length();
                                        os.write(tamaño);
                                        os.write(String.valueOf(primerNumero).getBytes());
                                        break;
                                    case "por":primerNumero = quitarCero(primerNumero*segundoNumero);
                                        tamaño=String.valueOf(primerNumero).length();
                                        os.write(tamaño);
                                        os.write(String.valueOf(primerNumero).getBytes());
                                    }
                        break;
                    case "ac":primerNumero = 0.00F;segundoNumero=0.00F; operador="";
                        break;
                    case "Fin":
                        System.out.println("Fin");
                        break;
                    case "mas": operador = "mas";
                        break;
                    case "menos":operador = "menos";
                        break;
                    case "entre":operador = "entre";
                        break;
                    case "por":operador = "por";
                        break;
                    default: if(operador==""){
                        primerNumero= (float) Double.parseDouble(new String(mensaje));
                    }else{segundoNumero= (float) Double.parseDouble(new String(mensaje));}
                }
                System.out.printf("Primer numero despues: "+primerNumero);
                System.out.println("Segundo numero despues: "+ segundoNumero);
            }
            int tamaño="OFF".length();
            os.write(tamaño);
            os.write("OFF".getBytes());

            System.out.println("Cerrando el nuevo socket");

            newSocket.close();

            System.out.println("Cerrando el socket servidor");

            serverSocket.close();

            System.out.println("Terminado");

        }catch (IOException e) {
        }
    }
    public static Float quitarCero(float resultado){
        String retorno="";
        retorno=Float.toString(resultado);
        if(resultado%1==0){
            retorno=retorno.substring(0,retorno.length()-2);
        }
        return Float.parseFloat(retorno);
    }
}