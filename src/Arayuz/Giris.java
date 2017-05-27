/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Giris.java
 *
 * Created on 07.Haz.2009, 15:36:43
 */

package Arayuz;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import pojo.Uye;

/**
 *
 * @author racih
 */
public class Giris extends javax.swing.JDialog {
    private EntityManager em;
    private EntityManagerFactory emf;
    private static Logger log = Logger.getLogger("HataCiktisi");


    /** Creates new form Giris */
    public Giris() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        initComponents();
        emf = Persistence.createEntityManagerFactory("AidatTakipPU");
        int width = getWidth();
        int height = getHeight();
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - width) / 2;
        int y = (screen.height - height) / 2;
        setBounds(x, y, width, height);
        Baglanti();
    }
    private void Baglanti() {
        try {
            em = emf.createEntityManager();
        } catch (Exception e) {
            log.info("Hata");
            JOptionPane.showMessageDialog(this, "Veritabanı Bozuk !","Hata",1);
            emf.close();
            System.exit(0);
        }
    }

    private void kontrol() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        if (txt_kullaniciAdi.getText().isEmpty() != false) {
            JOptionPane.showMessageDialog(this, "Kullanıcı Adı Boş !","Hata",1);
            return;
        }
        if (txt_sifre.getPassword().length == 0) {
            JOptionPane.showMessageDialog(this, "Şifre Alanı Boş !","Hata",1);
            return;
        }
        char[] sifreli = txt_sifre.getPassword();
        String sifre = new String(sifreli);
        String kAdi = txt_kullaniciAdi.getText();
        String dbKAdi = null;
        String dbSifre = null;
        Uye uye = em.find(Uye.class, 1);
        if (uye != null) {
            dbKAdi = uye.getKullanici();
            dbSifre = uye.getSifre();
        }
        if (dbKAdi.equals(kAdi) != false) {
            if (dbSifre.equals(sifre) != false) {
                AnaPanel ap = new AnaPanel();
                ap.Baglanti(emf);
                ap.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Giriş Başarısız !","Hata",1);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Giriş Başarısız !","Hata",1);
        }

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_kullaniciAdi = new javax.swing.JTextField();
        txt_sifre = new javax.swing.JPasswordField();
        btn_giris = new javax.swing.JButton();
        btn_vazgec = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Aidat Takip Giriş");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setText("Kullanıcı Adı :");

        jLabel2.setText("Şifre :");

        txt_kullaniciAdi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_kullaniciAdiKeyPressed(evt);
            }
        });

        txt_sifre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_sifreKeyPressed(evt);
            }
        });

        btn_giris.setText("Giriş");
        btn_giris.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_girisActionPerformed(evt);
            }
        });

        btn_vazgec.setText("Vazgeç");
        btn_vazgec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_vazgecActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_giris, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(btn_vazgec, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txt_sifre, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_kullaniciAdi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_kullaniciAdi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_sifre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_giris)
                    .addComponent(btn_vazgec))
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_girisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_girisActionPerformed
        try {
            // TODO add your handling code here:
            kontrol();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Giris.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Giris.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Giris.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Giris.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_girisActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        if (em != null && em.isOpen() != false && emf.isOpen() != false) {
            em.close();
            emf.close();
        } else {
            System.exit(0);
        }
    }//GEN-LAST:event_formWindowClosing

    private void btn_vazgecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_vazgecActionPerformed
        // TODO add your handling code here:
        if (em != null && em.isOpen() != false && emf.isOpen() != false) {
            em.close();
            emf.close();
            System.exit(0);
        } else {
            System.exit(0);
        }
    }//GEN-LAST:event_btn_vazgecActionPerformed

    private void txt_sifreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_sifreKeyPressed
        // TODO add your handling code here:
        int Tus = evt.getKeyCode();
        if (Tus == KeyEvent.VK_ENTER) {
            try {
                kontrol();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Giris.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(Giris.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Giris.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(Giris.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (Tus == KeyEvent.VK_ESCAPE) {
            if (em != null && em.isOpen() != false && emf.isOpen() != false) {
                em.close();
                emf.close();
                System.exit(0);
            } else {
                System.exit(0);
            }
        }
    }//GEN-LAST:event_txt_sifreKeyPressed

    private void txt_kullaniciAdiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_kullaniciAdiKeyPressed
        // TODO add your handling code here:
        int Tus = evt.getKeyCode();
        if (Tus == KeyEvent.VK_ESCAPE) {
            if (em != null && em.isOpen() != false && emf.isOpen() != false) {
                em.close();
                emf.close();
                System.exit(0);
            } else {
                System.exit(0);
            }
        }
    }//GEN-LAST:event_txt_kullaniciAdiKeyPressed

    /**
    * @param args the command line arguments
    */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_giris;
    private javax.swing.JButton btn_vazgec;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField txt_kullaniciAdi;
    private javax.swing.JPasswordField txt_sifre;
    // End of variables declaration//GEN-END:variables

}