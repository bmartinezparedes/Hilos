package com.kiskos.Ejercicio1;

public class Pruebas {
    public static int valor=0;
    public static void main(String[] args) {
        new Pruebas.Hilo("Hilo1").start();
        new Pruebas.Hilo("Hilo2").start();
        new Pruebas.Hilo("Hilo3").start();
        new Pruebas.Hilo("Hilo4").start();
    }
    static class Hilo extends Thread{
        public Hilo(String name) {
            super(name);
        }

        public void run(){
            for (int i=0;i<4;i++){
                System.out.println(i+" "+getName());
                valor++;
                System.out.println(valor);
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
