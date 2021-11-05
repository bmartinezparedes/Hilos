package com.kiskos.supermercado;

public class UnaEntradaUnaSalida3 {
    static int capacidad = 0;
    static boolean pasa = true;

    public static void main(String[] args) {
        UnaEntradaUnaSalida3.Entrada h1=new UnaEntradaUnaSalida3.Entrada("persona1");
        UnaEntradaUnaSalida3.Entrada h2=new UnaEntradaUnaSalida3.Entrada("persona2");
        UnaEntradaUnaSalida3.Entrada h3=new UnaEntradaUnaSalida3.Entrada("persona3");
        UnaEntradaUnaSalida3.Entrada h4=new UnaEntradaUnaSalida3.Entrada("persona4");
        UnaEntradaUnaSalida3.Entrada h5=new UnaEntradaUnaSalida3.Entrada("persona5");
        h1.start();

        h2.start();
        h3.start();
        h4.start();
        h5.start();
        UnaEntradaUnaSalida3.Salida h6=new UnaEntradaUnaSalida3.Salida("persona");
        h6.start();
    }

    public static class Entrada extends Thread {

        public Entrada(String name) {
            super(name);
        }

        public synchronized void run() {
            while ((capacidad >= 4) || !pasa) {
                try {
                    wait();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            pasa = false;
            capacidad++;
            System.out.println("Entra una persona");
            System.out.println(capacidad);
            pasa = true;
        }
    }

    public static class Salida extends Thread {

        public Salida(String name) {
            super(name);
        }

        public synchronized void run() {

            try {
                while ((!pasa)) {

                    wait();

                }
                pasa = false;
                capacidad--;
                System.out.println("Sale una persona");
                System.out.println(capacidad);
                pasa = true;
                notify();
            } catch (IllegalMonitorStateException e) {
                System.out.println(e);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
