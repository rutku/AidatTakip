/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AcilisEkrani.java
 *
 * Created on 13.Mar.2009, 17:36:19
 */
package Arayuz;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JWindow;

/**
 *
 * @author racih
 */
public class AcilisEkrani extends JWindow {

    /** Creates new form AcilisEkrani */
    public AcilisEkrani() {
        initComponents();
        int width = getWidth() - 10;
        int height = getHeight();
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - width) / 2;
        int y = (screen.height - height) / 2;
        setBounds(x, y, width, height);
        Yukle();
    }

    private void IsCubugu(int yuzde) {
        islemCubugu.setValue(yuzde);
    }

    public void Yukle() {
        Thread t = new Thread(new Runnable() {

            public void run() {
                try {
                    islemCubugu.setStringPainted(true);
                    IsCubugu(10);
                    Thread.sleep(10L);
                    IsCubugu(20);
                    Thread.sleep(10L);
                    IsCubugu(30);
                    Thread.sleep(18L);
                    IsCubugu(40);
                    Thread.sleep(11L);
                    IsCubugu(50);
                    Thread.sleep(15L);
                    IsCubugu(60);
                    Thread.sleep(13L);
                    IsCubugu(70);
                    islemCubugu.setString("Program Açılıyor ...");
                    Thread.sleep(180L);
                    IsCubugu(80);
                    new Giris().setVisible(true);
                    Thread.sleep(900L);
                    islemCubugu.setStringPainted(true);
                    IsCubugu(90);
                    Thread.sleep(120L);
                    islemCubugu.setString("Yüklendi ...");
                    IsCubugu(100);

                    setVisible(false);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new AcilisEkrani().setVisible(true);
            }
        });
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        islemCubugu = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        islemCubugu.setForeground(new java.awt.Color(153, 0, 0));
        islemCubugu.setName("islemCubugu"); // NOI18N

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/simge/splash_screen.jpg"))); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(islemCubugu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(islemCubugu, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar islemCubugu;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
