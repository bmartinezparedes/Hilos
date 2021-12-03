package com.kiskos.Parking;

import java.sql.SQLOutput;

public class Parking {
    static int plazas=0;
    static int[] prazasA=new int [5];
    static boolean hil=true;
    public static void main(String[] args) {

        Entrar e1=new Entrar("Coche1");
        Entrar e2=new Entrar("Coche2");
        Entrar e3=new Entrar("Coche3");
        Entrar e4=new Entrar("Coche4");
        Entrar e5=new Entrar("Coche5");
        Entrar e6=new Entrar("Coche6");
        Salir s1=new Salir("Coche1");

        e1.start();
        e2.start();
        e3.start();
        e4.start();
        e5.start();
        e6.start();

        s1.start();
    }

    public static class Entrar extends Thread {

        public Entrar(String name) {
            super(name);
        }

        public synchronized void run() {
           while(plazas>=5||!hil){
               try {
                   System.out.println("Estoy esperando");
                   wait();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
           hil=false;
           plazas++;
           System.out.println("Entra "+getName());
           hil=true;
           notify();
        }
    }
    public static class Salir extends Thread {

        public Salir(String name) {
            super(name);
        }

        public synchronized void run() {
            while(!hil){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            hil=false;
            plazas--;
            System.out.println("Sale "+getName());
            hil=true;
            notify();
        }
    }
}
