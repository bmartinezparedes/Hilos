package com.kiskos.ascensor;

import java.util.Random;

public class Ascesnsor {
    static int piso=0;
    static boolean movimiento=true;
    public static void main(String[] args) {
        Mover m1=new Mover("5");
        Mover m2=new Mover("9");
        Mover m3=new Mover("2");
        Mover m4=new Mover("18");
        Mover m5=new Mover("6");
        m1.start();
        m2.start();
        m3.start();
        m4.start();
        m5.start();
    }
    public static class Mover extends Thread {

        public Mover(String name) {super(name);}

        public synchronized void run() {
            while(!movimiento){
                try {
                    wait(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            movimiento=false;
            if(piso>Integer.parseInt(getName())){
                System.out.println("Baja");
            }else if(piso<Integer.parseInt(getName())){
                System.out.println("Sube");
            }else{
                System.out.println("No se desplaza");
            }
            piso=Integer.parseInt(getName());
            System.out.println(piso);
            movimiento=true;
            notify();
        }
    }
}
