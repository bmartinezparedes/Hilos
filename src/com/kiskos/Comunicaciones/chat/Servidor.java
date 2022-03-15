package com.kiskos.Comunicaciones.chat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class Servidor {
    //Primero tener el servidor iniciado para tener algo a lo que el cliente conectarse
    static int conexion=0; //Variable contador que indica cuantas personas hay conectadas y que usaremos para que solo se conecten 10
    static boolean lleno=false, variable=true;//Lleno nos indicara si info a sido escrito o no y Variable nos indica si alguien esta escribiendo en info
    static String info=""; //Variable donde guardaremos los mensajes de los clientes para posteriormente ser reenviados
    static ArrayList<Conexion> sockets = new ArrayList(); //ArrayList donde guardaremos los socket de los clientes con su nick
    static boolean serverUsado=false; //Variable que nos permite saber si ya algien se habia conectado de antes al server

    public static void main(String[] args){
        try{
            System.out.println("Creando socket servidor");

            ServerSocket serverSocket=new ServerSocket();

            System.out.println("Realizando el bind");

            InetSocketAddress addr=new InetSocketAddress("localhost",6666);
            serverSocket.bind(addr);
            new escribir().start();
            while(true) {
                sleep(3000L);
                while (conexion!=2) {
                    conexion++;
                    new EjecutoHilo(serverSocket).start();
                }
                if(sockets.size()==0&&serverUsado){
                    System.exit(0);
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Hilo donde se creara la conexion del cliente y donde se tomaran datos para su posterior utilizacion en el servidor.
     */
    static class EjecutoHilo extends Thread{
        ServerSocket serverSocket;
        String nick;

        /**
         * Hilo donde se creara la conexion del cliente y donde se tomaran datos para su posterior utilizacion en el servidor.
         * @param serverSocket Parametro donde le pasamos el socket del server para crear la conexion con el cliente
         */
        public EjecutoHilo(ServerSocket serverSocket) {
            this.serverSocket= serverSocket;
        }
        public synchronized void run(){
            try{
                System.out.println("Aceptando conexiones");

                Socket newSocket= serverSocket.accept();

                System.out.println("Conexion recibida");

                InputStream is=newSocket.getInputStream();
                OutputStream os=newSocket.getOutputStream();

                byte[] mensaje;
                int tamaño = is.read();
                mensaje = new byte[tamaño];
                is.read(mensaje);
                nick = new String(mensaje);
                new Conectado(nick).start();
                sleep(1000L);
                sockets.add(new Conexion(newSocket,nick));
                os.write("Te has conectado al server".length());
                os.write("Te has conectado al server".getBytes());
                serverUsado=true;
                while(!new String(mensaje).equalsIgnoreCase(nick+": /Fin")) {
                    tamaño = is.read();
                    mensaje = new byte[tamaño];
                    is.read(mensaje);
                    System.out.println("Mensaje recibido: " + new String(mensaje));
                    System.out.println("Fin Mensaje");
                    info=new String(mensaje);
                    System.out.println("Imprimo info: "+info);
                    while(!variable || lleno){
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    variable=false;
                    lleno=true;
                    variable = true;
                    notify();
                    System.out.println("Imprimo lleno: "+lleno);
                }
                System.out.println("Cerrando el nuevo socket");
                sleep(1000L);
                conexion--;
                newSocket.close();

                System.out.println("Fin hilo");
            }catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * En el hilo escribir recogeremos lo que nos envien los clientes de la variable info y se la renviaremos a todos.
     * A demas si alguien se desconecta con un /Fin el servidor le enciara a todos que "Nick" se desconecto del server.
     */
    public static class escribir extends Thread{
        Boolean desconexion = false;
        String nickAux;
        Conexion socketAux;

        /**
         * En el hilo escribir recogeremos lo que nos envien los clientes de la variable info y se la renviaremos a todos.
         * A demas si alguien se desconecta con un /Fin el servidor le enciara a todos que "Nick" se desconecto del server.
         */
        public escribir(){}
        public synchronized void run(){
            while(true) {
                //sin el sleep no funciona porque no deja de ocupar la variable y siempre se la encontrara en falso
                try {
                    sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(lleno) {
                    System.out.println("dentro if");
                    /*
                    For que comprueba que no se haya desconectado nadie, en caso afirmativo sera eliminado y preparado
                    para que el proximo for trate devidamente el mensaje a enviar a todos
                     */
                    for (Conexion socket: sockets){
                        System.out.println(info+"=?"+socket.getNick()+": /Fin");
                        if(info.equalsIgnoreCase(socket.getNick()+": /Fin")){
                            nickAux = socket.getNick();
                            desconexion = true;
                            socketAux=socket;
                            try {
                                socket.getSocket().getOutputStream().write("/Fin".length());
                                socket.getSocket().getOutputStream().write("/Fin".getBytes());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    /*
                    Con este if trato de eleminar el socket que se desconecto sin que afecte al for de arriba,
                    sino daba error de que no daba seguido lellendo el arrayList
                     */
                    if(desconexion){sockets.remove(socketAux); System.out.println("elimino socket");}
                    //Este for envia a todos los conectados mensajes y en caso de desconexion lo informa
                    for (Conexion socket : sockets) {
                        System.out.println("dentro for sockets");
                        try {
                            OutputStream os = socket.getSocket().getOutputStream();
                            if(!desconexion){
                                int tamaño = info.length();
                                os.write(tamaño);
                                os.write(info.getBytes());
                                System.out.println("Mensaje enviado a todos: " + info);
                            }else {
                                String mensaje = "Server:"+nickAux+" deixou este chat.";
                                int tamaño = mensaje.length();
                                os.write(tamaño);
                                os.write(mensaje.getBytes());
                                System.out.println("Mensaje enviado a todos: " + info);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    nickAux="";
                    desconexion=false;
                    while(!variable){
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    variable = false;
                    lleno=false;
                    variable = true;
                    notify();
                }
            }
        }
    }

    /**
     * Conexion es una clase para recoger las conexiones al servidor con su nick name para asi posterior mente poder ser
     * utilizadas en el arrayList de sockets de tipo Conexion.
     */
    private static class Conexion{
        Socket socket;
        String nick;

        /**
         * Conexion es una clase para recoger las conexiones al servidor con su nick name para asi posterior mente poder ser
         * utilizadas en el arrayList de sockets de tipo Conexion.
         * @param socket Socket de conexion con el cliente
         * @param nick Nick del cliente para saber quien habla
         */
        public Conexion(Socket socket, String nick) {
            this.socket = socket;
            this.nick = nick;
        }

        /**
         * Metodo de retorno de socket de la clase Conexion
         * @return Retorna el socket
         */
        public Socket getSocket() {
            return socket;
        }
        /**
         * Metodo de retorno del Nick de la clase Conexion
         * @return Retorna el nick
         */
        public String getNick() {
            return nick;
        }
    }

    /**
     * Conectado es un hilo que al ejecutarse envia un mensaje al resto de clientes para indicarles quien se ha conectado
     */
    public static class Conectado extends Thread{
        /**
         * Conectado es un hilo que al ejecutarse envia un mensaje al resto de clientes para indicarles quien se ha conectado
         * @param name En este parametro introducimos el nick del que se conectó
         */
        public Conectado(String name){super(name);}
        public synchronized void run(){
            if(!sockets.isEmpty()){
                for (Conexion socket: sockets){
                    OutputStream os;
                    try {
                        os = socket.getSocket().getOutputStream();
                        String mensaje = getName()+" se conecto al server. Ahora somos "+(sockets.size()+1);
                        int tamaño = mensaje.length();
                        os.write(tamaño);
                        os.write(mensaje.getBytes());
                        System.out.println("Mensaje enviado a todos: " + mensaje);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }
}