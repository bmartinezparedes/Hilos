package com.kiskos.supermercado;

public class UnaEntradaUnaSalida2 {
    static int capacidad=0;
    static boolean pasa=true;
    public static void main(String[] args) {

        Entrada h1=new Entrada("persona1");
        Entrada h2=new Entrada("persona2");
        Entrada h3=new Entrada("persona3");
        Entrada h4=new Entrada("persona4");
        Entrada h5=new Entrada("persona5");
        h1.start();

        h2.start();
        h3.start();
        h4.start();
        h5.start();
        Salida h6=new Salida("persona");
        h6.start();

    }
    public static class Entrada extends Thread {

        public Entrada(String name) {
            super(name);
        }

        public synchronized void run() {
            //si pasa es false el hilo se duerme hasta que alguien lo despierte
            try {
                sleep((long) Math.floor(Math.random()*2000+1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if ((capacidad>=4)) {
                try {
                    wait();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            pasa=false;
            capacidad ++;
            System.out.println("Entra una persona");
            System.out.println(capacidad);
            pasa=true;
        }
    }
    public static class Salida extends Thread {

        public Salida(String name) {
            super(name);
        }

        public synchronized void run() {
            //si el pasa esta false el hilo se duerme hasta que alguien lo despierte
            try {
            sleep(20000);

            pasa=false;
            capacidad --;
            System.out.println("Sale una persona");
            System.out.println(capacidad);
            pasa=true;

            sleep(1000);


            notify();
            } catch (IllegalMonitorStateException e) {
                System.out.println(e);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
