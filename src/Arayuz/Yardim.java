/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Yardim.java
 *
 * Created on 06.Haz.2009, 14:07:27
 */

package Arayuz;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *
 * @author Ramazan
 */
public class Yardim extends javax.swing.JDialog {

    /** Creates new form Yardim */
    public Yardim(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        int width = getWidth();
        int height = getHeight();
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - width) / 2;
        int y = (screen.height - height) / 2;
        setBounds(x, y, width, height);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_yardim = new javax.swing.JTextPane();
        btn_kapat = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Aidat Takip Yardım");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setText("Aidat Takip Yardımına Hoş Geldiniz !");

        jScrollPane1.setViewportBorder(javax.swing.BorderFactory.createTitledBorder("Öneriler"));

        txt_yardim.setEditable(false);
        txt_yardim.setText("Programın yaptığı işler;\n  1) Aidat sekmesinde Blok ve Aylara ayrılmış seçeneklerle, seçilen dairenin aidatı girilir.\n  2) Aidat verenlerin ve vermeyenlerin ana tabloda bilgisi listelenir.\n  3) Gelir sekmesinde, yıl içinde toplanan aidatların toplam geliri listelenir.\n  4) Gelir sekmesinde, aylık ve toplam yıllık olmak üzere gider eklenebilir.\n  5) Gider Ekle Penceresinde eklenen giderlere ait notlar girilebilir.\n  6) Aidat ve Gelir sekmeleri istenirse \"Excel'e Çevir\" butonuyla Excel'e aktarılır.\n  7) İşlemler > Daire Ekle sekmesinden daire eklenir/silinir.\n  8) Daire Ekle penceresinde listelenen dairelere oturan kişilerde eklenir/silinir/düzeltilir.\n\nYapılmaması gerekenler;\n  1) Programın kurulu olduğu dizinde bulunan dosyalarda herhangir bir değişiklik yapılmamalıdır.\n  2) Programın amacına aykırı işlemler uygulanmayın.\n  3) Bilgisayarı kapatmadan önce programı kapatın. Programı Görev Yöneticisinden zorla kapatmayın.\n\nProgramın Kullanım Şekli;\n  1) Aidat sekmesinde bir dairenin gözükmesi için, İşlemler > Daire Ekle sekmesinden  \n      Bulunduğu bloğu seçerek daire numarasını girin.\n  2) Aidat işlemlerinde mutlaka, dairenin ödeyeceği ayı seçerek girdi yapın.\n  3) Mutlaka her işlem sonrası \"Excel'e Çevir\" butonuna basarak, seçilen bloğun aidat bilgisini Excel'e aktarın.");
        jScrollPane1.setViewportView(txt_yardim);

        btn_kapat.setText("Kapat");
        btn_kapat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_kapatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(680, Short.MAX_VALUE)
                .addComponent(btn_kapat)
                .addGap(89, 89, 89))
            .addGroup(layout.createSequentialGroup()
                .addGap(201, 201, 201)
                .addComponent(jLabel1)
                .addContainerGap(348, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jScrollPane1)
                .addGap(64, 64, 64))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btn_kapat)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_kapatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_kapatActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btn_kapatActionPerformed

    /**
    * @param args the command line arguments
    */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_kapat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane txt_yardim;
    // End of variables declaration//GEN-END:variables

}
