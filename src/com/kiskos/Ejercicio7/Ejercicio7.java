package com.kiskos.Ejercicio7;

public class Ejercicio7 {
    static String mensaje=null;
    static Boolean acceso=true;
    public static void main(String[] args) {
        Ejercicio7.Modifica m1=new Ejercicio7.Modifica("hola");
        Ejercicio7.Modifica m2=new Ejercicio7.Modifica("si");
        Ejercicio7.Modifica m3=new Ejercicio7.Modifica("muy bien");

        Ejercicio7.Ler l1=new Ejercicio7.Ler("leo");
        Ejercicio7.Ler l2=new Ejercicio7.Ler("leo");
        Ejercicio7.Ler l3=new Ejercicio7.Ler("leo");

        m1.start();
        m2.start();
        l1.start();
        l2.start();
        m3.start();
        l3.start();

    }

    public static class Modifica extends Thread {

        public Modifica(String name) {
            super(name);
        }

        public synchronized void run() {
            while(mensaje!=null||!acceso){
                try {
                    wait(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            acceso=false;
            mensaje=getName();
            System.out.println("modifica");
            acceso=true;
            notify();
        }
    }

    public static class Ler extends Thread {

        public Ler(String name) {
            super(name);
        }

        public synchronized void run() {
            while (mensaje==null||!acceso){
                try {
                    wait(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            acceso=false;
            System.out.println(mensaje);
            mensaje=null;
            acceso=true;
            notify();
        }
    }
}
