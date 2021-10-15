package com.kiskos.Ejercicio4;

public class Ejercicio4 {
    public static void main(String[] args) {
        Hilo1 h1=new Hilo1("Suma pares");
        Hilo2 h2=new Hilo2("Suma impares");
        Hilo3 h3=new Hilo3("Suma numeros acabados en 2 y 3");
        h1.start();
        h2.start();
        h3.start();
    }

    static class Hilo1 extends Thread{
        public Hilo1(String name) {
            super(name);
        }
        public void run(){
            int n=0;
            for (int i=0;i<1000;i++){
                if ((i%2)==0) {
                    n = n + i;
                    System.out.println(n);
                }
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Fin hilo");
        }
    }
    static class Hilo2 extends Thread{
        public Hilo2(String name) {
            super(name);
        }
        public void run(){
            int n=0;
            for (int i=0;i<1000;i++){
                if ((i%2)!=0) {
                    n = n + i;
                    System.out.println(n);
                }
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Fin hilo");
        }
    }
    static class Hilo3 extends Thread{
        public Hilo3(String name) {
            super(name);
        }
        public void run(){
            int n=0;
            for (int i=0;i<1000;i++){
                if ((i%10)==2 | (i%10)==3) {
                    n = n + i;
                    System.out.println(n);
                }
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
