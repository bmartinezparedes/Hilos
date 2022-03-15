/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.kiskos.Comunicaciones.chat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import static java.awt.event.KeyEvent.VK_ENTER;
import static java.lang.Thread.sleep;

/**
 *
 * @author Braiskiskos
 * @version 2022.0.0
 */
public class Chats extends javax.swing.JFrame {
    static InputStream is;
    OutputStream os;
    static Socket clienteSocket;
    static javax.swing.JTextArea mostar;
    String nick;
    /**
     * Creates new form Chat2
     */
    public Chats() {initComponents();}

    /**
     *
     * @param is InputStream que usaremos para leer lo que nos envie el server
     * @param os OutputStream que usaremos para escribir al server
     * @param clienteSocket Socket socket del cliente
     * @param nick String que sera el nombre o alias del cliente conectado
     */
    public Chats(InputStream is, OutputStream os, Socket clienteSocket,String nick){
        this.is=is;
        this.os=os;
        this.clienteSocket=clienteSocket;
        initComponents();
        this.mostar= tMostrar;
        this.nick = nick;
        this.setTitle("Usuario "+nick);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tMostrar = new javax.swing.JTextArea();
        bEnviar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tEnviar = new javax.swing.JTextArea();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tMostrar.setColumns(20);
        tMostrar.setRows(5);
        jScrollPane1.setViewportView(tMostrar);

        bEnviar.setText("Enviar");
        bEnviar.setToolTipText("");
        bEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEnviarActionPerformed(evt);
            }
        });
        bEnviar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bEnviarKeyPressed(evt);
            }
        });

        tEnviar.setColumns(20);
        tEnviar.setRows(5);
        jScrollPane2.setViewportView(tEnviar);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bEnviar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEnviarActionPerformed
        String mensaje = nick+": "+tEnviar.getText();
        if(!mensaje.equalsIgnoreCase(nick+": /Fin")){
        int tamaño = mensaje.length();
        try {
            os.write(tamaño);
            os.write(mensaje.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        tEnviar.setText("");
        }else{
            System.out.println("Cerrando el socket cliente");

            try {
                int tamaño = mensaje.length();
                os.write(tamaño);
                os.write(mensaje.getBytes());
                sleep(2000);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            } finally {
                try {
                    clienteSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("Terminado");
            System.exit(0);
        }

    }//GEN-LAST:event_bEnviarActionPerformed

    private void bEnviarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bEnviarKeyPressed
    }//GEN-LAST:event_bEnviarKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws IOException {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Chats.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Chats.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Chats.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Chats.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Chats().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bEnviar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextArea tEnviar;
    private javax.swing.JTextArea tMostrar;
    // End of variables declaration//GEN-END:variables
}
