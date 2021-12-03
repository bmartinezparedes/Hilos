package com.kiskos.Ejercicio2;

public class antesDelExamen {
    public static void main(String[] args) {
        Hilo h1=new Hilo("Brais");
        Hilo h2=new Hilo("Kiskos");
        Hilo h3=new Hilo("Brais2");
        Hilo h4=new Hilo("Kiskos2");

        try {
            h1.start();
            h1.join();
            h2.start();
            h2.join();
            h3.start();
            h3.join();
            h4.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    static class Hilo extends Thread{
        public Hilo(String nome){super(nome);}
        public void run() {
            for(int i=0;i<5;i++){
                System.out.println(i+" "+getName());
            }
        }
    }
}
