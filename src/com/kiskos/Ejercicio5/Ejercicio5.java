package com.kiskos.Ejercicio5;

import com.kiskos.Ejercicio2.Ejercicio2;

public class Ejercicio5 {
    public static void main(String[] args) throws InterruptedException {
        Hilo h1=new Hilo("Hilo 1");
        Hilo h2=new Hilo("Hilo 2");

        //B com prioridades
        /*h1.setPriority(1);
        h2.setPriority(10);*/

        //A con join()
        h2.start();
        //el join hace que termine ese hilo y los demas esperan a que ese hilo temine
        h2.join();
        h1.start();

    }
    static class Hilo extends Thread{
        public Hilo(String name) {
            super(name);
        }
        public void run(){
            for (int i=0;i<3;i++){
                System.out.println(getName());
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
