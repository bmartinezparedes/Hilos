package com.kiskos.Ejercicio1;



public class antesDelExamen {
    public static void main(String[] args) {
        /*Hilo h1=new Hilo("Brais");
        Hilo h2=new Hilo("Kiskos");
        h1.start();
        h2.start();*/
        Fibonacci f=new Fibonacci("10");
        f.start();
    }
    static class Hilo extends Thread{
        public Hilo (String nome){super(nome);}
        public void run(){
            for(int i=0;i<10;i++){
                System.out.println(i+" "+getName());
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Fin de hilo: "+getName());
        }
    }
    static class Fibonacci extends Thread{
        public Fibonacci (String numero){super(numero);}
        public void run(){
            int n1=1,n2=1,aux1=0,aux2=0;
            for(int i=0;i<Integer.parseInt(getName());i++){
                if((i+1)==1)
                    System.out.println(n1);
                else if((i+1)==2)
                    System.out.println(n2);
                else if((i+1)==3) {
                    aux1 = n1 + n2;
                    System.out.println(aux1);
                }else if((i+1)==4) {
                    aux2=n2+aux1;
                    System.out.println(aux2);
                }else if((i+1)>=5&&(i+1)%2==1){
                    aux1=aux2+aux1;
                    System.out.println(aux1);
                }else if((i+1)>=6&&(i+1)%2==0){
                    aux2=aux1+aux2;
                    System.out.println(aux2);
                }else{
                    System.out.println("Fallo en "+(i+1));
                }

            }
        }
    }
}
