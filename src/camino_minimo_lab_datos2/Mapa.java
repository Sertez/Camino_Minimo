/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package camino_minimo_lab_datos2;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author danie
 */
public class Mapa extends javax.swing.JFrame {

    Graphics g2;
    Graphics2D g;
    ArrayList<Vertice> vertices = new ArrayList();
    ArrayList<Hitbox> hitboxes = new ArrayList();
    ArrayList<Arista> aristas = new ArrayList();
    DefaultComboBoxModel<Vertice> model = new DefaultComboBoxModel();
    DefaultComboBoxModel<Vertice> model1 = new DefaultComboBoxModel();
    DefaultComboBoxModel<Vertice> model2 = new DefaultComboBoxModel();
    DefaultComboBoxModel<Vertice> model3 = new DefaultComboBoxModel();
    int r = 20;
    Funciones f;
    int numver = 0;
    Font fuente;
    int Matrizad[][]= new int[99][99];

    /**
     * Creates new form Mapa
     */
    public Mapa() {
        initComponents();
        aggorigen.setModel(model);
        aggdestino.setModel(model1);
        g2 = img.getGraphics();
        g = (Graphics2D) g2;
        fuente = jLabel1.getFont();
        FontMetrics fm = g.getFontMetrics();
        g.setFont(fuente);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        f = new Funciones();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        img = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        aggorigen = new javax.swing.JComboBox<>();
        aggdestino = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        img.setBackground(new java.awt.Color(255, 255, 255));
        img.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout imgLayout = new javax.swing.GroupLayout(img);
        img.setLayout(imgLayout);
        imgLayout.setHorizontalGroup(
            imgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        imgLayout.setVerticalGroup(
            imgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 525, Short.MAX_VALUE)
        );

        jLabel1.setText("Agregar Camino");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel2.setText("Ejecutar Floyd-Warshall");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(img, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(aggorigen, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(aggdestino, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(jLabel1)
                .addGap(68, 68, 68)
                .addComponent(jLabel2)
                .addContainerGap(390, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(img, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(aggorigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(aggdestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void imgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgMouseClicked
        int x = evt.getX();
        int y = evt.getY();
        int sw = 0;
        for (Hitbox h : hitboxes) {
            if (x < h.xmax && x > h.xmin && y < h.ymax && y > h.ymin) {
                sw = 1;
            }
        }
        if (sw == 0) {
            Hitbox hb = new Hitbox(x - r, x + r, y - r, y + r);
            String nom = JOptionPane.showInputDialog("Escriba el nombre del vertice");
            if (nom != null && !nom.isEmpty()) {
                hitboxes.add(hb);
                Vertice v = new Vertice(nom, numver, hb, x, y);
                vertices.add(v);
                model.addElement(v);
                model1.addElement(v);
                f.Dibujar(g, vertices, aristas, r, fuente);
                numver++;
            }
        }
    }//GEN-LAST:event_imgMouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        Vertice origen = (Vertice) aggorigen.getSelectedItem();
        Vertice destino = (Vertice) aggdestino.getSelectedItem();
        int sw=0;
        for (Arista ar : aristas) {
            if (ar.destino.equals(destino) && ar.origen.equals(origen)) {
                sw=1;
            }
        }
        if (!origen.equals(destino) && sw==0) {
            String peso = JOptionPane.showInputDialog("Escriba el costo del camino");
            if (peso != null && !peso.isEmpty()) {
                int costo = Integer.parseInt(peso);
                Arista a = new Arista((Vertice) aggorigen.getSelectedItem(), (Vertice) aggdestino.getSelectedItem(), costo);
                aristas.add(a);
                f.Dibujar(g, vertices, aristas, r, fuente);
                f.Aggady(origen, a, vertices);
            }
        }
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        f.Matrizad(vertices, Matrizad, numver);
        for (int i = 0; i < numver; i++) {
            System.out.println();
            for (int j = 0; j < numver; j++) {
                System.out.print(Matrizad[i][j]+" , ");
            }
        }
    }//GEN-LAST:event_jLabel2MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(Mapa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Mapa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Mapa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Mapa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Mapa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Vertice> aggdestino;
    private javax.swing.JComboBox<Vertice> aggorigen;
    private javax.swing.JPanel img;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
