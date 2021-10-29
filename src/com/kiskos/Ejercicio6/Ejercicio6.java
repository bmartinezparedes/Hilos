package com.kiskos.Ejercicio6;

public class Ejercicio6 {

    static int caja = 100;
    static boolean pasa = true;

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Ingreso("5").start();
        }
        for (int i = 0; i < 5; i++) {
            new Extraccion("5").start();
        }
    }

    public static class Ingreso extends Thread {
        /**
         *
         * @param name Valor que introducimos para saber cuanto sumar
         */
        public Ingreso(String name) {
            super(name);
        }

        public synchronized void run() {
            if (pasa == false) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            pasa = false;
            caja += Integer.parseInt(getName());
            System.out.println("Ingreso realizado con éxito, total en caja : " + caja);
            pasa = true;

            try {
                notify();
            } catch (IllegalMonitorStateException e) {
                e.toString();
            }
        }
    }

    public static class Extraccion extends Thread {
        public Extraccion(String name) {
            super(name);
        }

        public synchronized void run() {
            if (pasa == false) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }catch (IllegalMonitorStateException i){
                    i.toString();
                }
            }
            pasa = false;
            if (caja > Integer.parseInt(getName())) {
                caja -= Integer.parseInt(getName());
                System.out.println("Extracción  realizado con éxito, total en caja : " + caja);
            } else
                System.out.println("no tienes suficiente dinero");

            pasa = true;

            try {
                notify();
            } catch (IllegalMonitorStateException e) {
                e.toString();
            }
        }
    }
}
