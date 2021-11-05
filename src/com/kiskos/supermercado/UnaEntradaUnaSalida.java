package com.kiskos.supermercado;

import javax.swing.*;
import java.io.RandomAccessFile;

public class UnaEntradaUnaSalida {
    static int capacidad=0;
    static int accion=0;
    static int enEspera=0;
    public static void main(String[] args) {

        /*
        for (int i=0;i<5;i++){
            Entrada h=new Entrada("persona");
            h.run();
            System.out.println(capacidad);
        }
        */

        accion=Integer.parseInt(JOptionPane.showInputDialog("1 Entra \n2 Sale"));
        do{
            if(accion==1){
                Entrada h=new Entrada("persona");
                Capacidad c=new Capacidad("JAJAJJA");
                h.start();
                c.start();
            }else if(accion==2){
                Salida h2=new Salida("persona");
                Capacidad c=new Capacidad("JAJAJJA");
                h2.start();
                c.start();
            }
            if(enEspera>0){
                accion=2;
            }else{
                accion=Integer.parseInt(JOptionPane.showInputDialog("1 Entra \n2 Sale"));
            }
        }while(accion!=0);

    }
    public static class Entrada extends Thread {

        public Entrada(String name) {
            super(name);
        }

        public synchronized void run() {
            //si el pasa esta false el hilo se duerme hasta que alguien lo despierte

            if ((capacidad>=4)) {
                try {
                    wait();
                    enEspera++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            capacidad ++;
            System.out.println("Entra una persona");
            try {
                notify();
            } catch (IllegalMonitorStateException e) {
                e.toString();
            }
        }
    }
    public static class Salida extends Thread {

        public Salida(String name) {
            super(name);
        }

        public synchronized void run() {
            //si el pasa esta false el hilo se duerme hasta que alguien lo despierte
            try {
                sleep((long) Math.floor(Math.random()*3000+1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            capacidad --;
            System.out.println("Sale una persona");
            try {
                notify();
                if(capacidad+1>=4){
                    enEspera--;
                }
            } catch (IllegalMonitorStateException e) {
                e.toString();
            }
        }
    }
    public static class Capacidad extends Thread {

        public Capacidad(String name) {
            super(name);
        }

        public synchronized void run() {
            System.out.println(capacidad);
        }
    }
}
