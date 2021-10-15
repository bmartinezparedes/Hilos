package com.kiskos.Ejercicio2;

import com.kiskos.Ejercicio1.Main;

public class Ejercicio2 {
    public static void main(String[] args) {
        System.out.println("Empieza el Programa");
        Hilo h1=new Hilo("Hilo1");
        Hilo h2=new Hilo("Hilo2");
        Hilo h3=new Hilo("Hilo3");
        Hilo h4=new Hilo("Hilo4");
        h1.start();
        h2.start();
        h3.start();
        h4.start();
        System.out.println("FIN PROGRAMA");
    }
    static class Hilo extends Thread{
        public Hilo(String name) {
            super(name);
        }
        public void run(){
            for (int i=0;i<5;i++){
                System.out.println(i+" "+getName());
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Fin hilo");
        }
    }
}
