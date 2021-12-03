package com.kiskos.Ejercicio3;

public class Ejercicio3 {
    public static void main(String[] args) {
        Hilo h=new Hilo("brais");
        h.start();
    }
    public static class Hilo extends Thread{
        public Hilo(String nome){
            super(nome);
        }
        public synchronized void run(){
            for(int i=0;i<5;i++){
                System.out.println("Hilo: "+getName()+" "+i);
                cosas("Hilo2: "+i);

            }
        }
        public synchronized void cosas(String cosa){
            for(int i=0;i<10;i++){
                System.out.println(cosa+i);
            }
        }
    }
}
