package com.kiskos.Ejercicio1;

public class Main {

    public static void main(String[] args) {
	System.out.println("Empieza el Programa");
    //Uso prioridades y no funciona
        EjecutoHilo h1=new EjecutoHilo("hilo1");
        h1.setPriority(10); //10 signifia maxima prioridad
        EjecutoHilo h2=new EjecutoHilo("hilo2");
        h2.setPriority(1);  //1 signifia minima prioridad
        h2.start();
        h1.start();

    //Ejecuto los Hilos
    /*new EjecutoHilo("si").start();
    new EjecutoHilo("no").start();*/
    System.out.println("FIN PROGRAMA");

    }
    static class EjecutoHilo extends Thread{
        public EjecutoHilo(String name) {
            super(name);
        }
        public void run(){
            for (int i=0;i<10;i++){
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