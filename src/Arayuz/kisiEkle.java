/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * kisiEkle.java
 *
 * Created on 07.Haz.2009, 15:29:07
 */
package Arayuz;

import islemler.daireSahibiAl;
import islemler.daireSahibiKaydet;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import pojo.Dairesahibi;

/**
 *
 * @author racih
 */
public class kisiEkle extends javax.swing.JDialog {

    private EntityManager em;
    private daireSahibiAl dsa;
    private daireSahibiKaydet dsk;
    private String daire;

    /** Creates new form kisiEkle */
    public kisiEkle(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        int width = getWidth();
        int height = getHeight();
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - width) / 2;
        int y = (screen.height - height) / 2;
        setBounds(x, y, width, height);

    }

    public void Baglanti(EntityManagerFactory emf, String daireX) {
        em = emf.createEntityManager();
        dsa = new daireSahibiAl(emf);
        dsk = new daireSahibiKaydet(emf);
        daire = new String(daireX);
        listeyeEkle();
        lbl_daire.setText(daire);
        cb_evet.setSelected(true);
    }

    private DefaultListModel onTanimliListe() {
        DefaultListModel l = new DefaultListModel();
        for (int i = 0; i < dsa.alDaire().size(); i++) {
            if (dsa.alDaire().get(i).toString().equals(daire) != false) {
                l.addElement(dsa.alIsim().get(i).toString() + " " + dsa.alSoyad().get(i).toString());
            }
        }
        return l;
    }

    private String Giristarihi() {
        Date zaman;
        String tarih;
        SimpleDateFormat dformat;
        dformat = new SimpleDateFormat("dd-MM-yyyy");
        zaman = dp_tarih.getDate();
        tarih = dformat.format(zaman);
        return tarih;
    }

    private void listeyeEkle() {
        list_daireSahipleri.removeAll();
        list_daireSahipleri.setModel(onTanimliListe());
    }

    private int durum() {
        int sayi = 0;
        if (cb_evet.isSelected() != false) {
            sayi = 0;
        }
        if (cb_hayir.isSelected() != false) {
            sayi = 1;
        }
        return sayi;
    }

    private void kontrol() {

        if (dp_tarih.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Tarih Seçin !", "Hata", 1);
            return;
        }
        if (txt_ad.getText().isEmpty() != false) {
            JOptionPane.showMessageDialog(this, "İsim Girin !", "Hata", 1);
            return;
        }
        if (txt_soyad.getText().isEmpty() != false) {
            JOptionPane.showMessageDialog(this, "Soyad Girin !", "Hata", 1);
            return;
        }
        if (txt_telefon.getText().isEmpty() != false) {
            JOptionPane.showMessageDialog(this, "Telefon Numarası Girin !", "Hata", 1);
            return;
        }
    }

    private void Kaydet() {
        try {
            kontrol();
            Calendar c = Calendar.getInstance();
            String tarih = String.valueOf(c.get(Calendar.YEAR));
            Dairesahibi d = new Dairesahibi(dsa.YerBul(), tarih, daire);
            d.setAd(txt_ad.getText());
            d.setGiristarihi(Giristarihi());
            d.setSoyad(txt_soyad.getText());
            d.setTelefon(txt_telefon.getText());
            d.setDurum(durum());
            dsk.DaireSahibiOlustur(d);
            listeyeEkle();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void Duzelt() {
        try {
            kontrol();
            if (list_daireSahipleri.getSelectedIndex() < 0) {
                JOptionPane.showMessageDialog(this, "Kişi seçin !");
                return;
            }
            em.getTransaction().begin();
            String isimVeSoyad = list_daireSahipleri.getSelectedValue().toString();
            String isim = isimVeSoyad.substring(isimVeSoyad.indexOf(""), isimVeSoyad.indexOf(" "));
            String soyad = isimVeSoyad.substring(isimVeSoyad.indexOf(" ") + 1);
            for (int i = 0; i < dsa.alDaire().size(); i++) {
                if (dsa.alSoyad().get(i).toString().equals(soyad)) {
                    if (dsa.alIsim().get(i).toString().equals(isim)) {
                        int id = Integer.parseInt(dsa.alId().get(i).toString());
                        Dairesahibi sahib = em.find(Dairesahibi.class, id);
                        if (sahib != null) {
                            sahib.setAd(txt_ad.getText());
                            sahib.setSoyad(txt_soyad.getText());
                            sahib.setTelefon(txt_telefon.getText());
                            sahib.setGiristarihi(Giristarihi());
                            sahib.setDurum(durum());
                            em.merge(sahib);

                        }
                    }
                }
            }

            em.getTransaction().commit();

            listeyeEkle();
        } catch (Exception e) {
            e.printStackTrace();
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cb_evet = new javax.swing.JCheckBox();
        cb_hayir = new javax.swing.JCheckBox();
        txt_ad = new javax.swing.JTextField();
        txt_soyad = new javax.swing.JTextField();
        txt_telefon = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        dp_tarih = new org.jdesktop.swingx.JXDatePicker();
        jScrollPane1 = new javax.swing.JScrollPane();
        list_daireSahipleri = new javax.swing.JList();
        jLabel6 = new javax.swing.JLabel();
        lbl_daire = new javax.swing.JLabel();
        btn_ekle = new javax.swing.JButton();
        btn_duzelt = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Aidat Takip Kişi Ekle");

        jLabel1.setText("Ad :");

        jLabel2.setText("Soyad :");

        jLabel3.setText("Telefon :");

        jLabel4.setText("Yeni daire sahibi mi ?");

        buttonGroup1.add(cb_evet);
        cb_evet.setText("Evet");

        buttonGroup1.add(cb_hayir);
        cb_hayir.setText("Hayır, Eski");

        jLabel5.setText("Tarih :");

        dp_tarih.setBackground(new java.awt.Color(0, 0, 102));
        dp_tarih.setForeground(new java.awt.Color(153, 0, 0));

        list_daireSahipleri.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        list_daireSahipleri.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                list_daireSahipleriMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(list_daireSahipleri);

        jLabel6.setText("Daire :");

        lbl_daire.setText("-");

        btn_ekle.setText("Ekle");
        btn_ekle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ekleActionPerformed(evt);
            }
        });

        btn_duzelt.setText("Düzelt");
        btn_duzelt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_duzeltActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cb_evet)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn_duzelt)
                                    .addComponent(cb_hayir)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dp_tarih, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txt_telefon, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                                .addComponent(txt_ad, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                                .addComponent(txt_soyad, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)))))
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_daire, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(btn_ekle)
                .addContainerGap(304, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dp_tarih, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_ad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txt_soyad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_telefon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cb_evet)
                            .addComponent(cb_hayir)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(lbl_daire))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_duzelt)
                    .addComponent(btn_ekle))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void list_daireSahipleriMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_list_daireSahipleriMouseClicked
        // TODO add your handling code here:
        try {
            if (list_daireSahipleri.getSelectedIndex() < 0) {
                return;
            }
            String isimVeSoyad = list_daireSahipleri.getSelectedValue().toString();
            String isim = isimVeSoyad.substring(isimVeSoyad.indexOf(""), isimVeSoyad.indexOf(" "));
            String soyad = isimVeSoyad.substring(isimVeSoyad.indexOf(" ") + 1);
            for (int i = 0; i < dsa.alDaire().size(); i++) {
                if (dsa.alSoyad().get(i).toString().equals(soyad)) {
                    if (dsa.alIsim().get(i).toString().equals(isim)) {
                        int id = Integer.parseInt(dsa.alId().get(i).toString());
                        Dairesahibi sahib = em.find(Dairesahibi.class, id);
                        if (sahib != null) {
                            txt_ad.setText(sahib.getAd());
                            txt_soyad.setText(sahib.getSoyad());
                            if (sahib.getDurum() == 0) {
                                cb_evet.setSelected(true);
                            } else {
                                cb_hayir.setSelected(true);
                            }
                            dp_tarih.setDate(Giristarih(sahib.getGiristarihi()));
                            txt_telefon.setText(sahib.getTelefon());
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_list_daireSahipleriMouseClicked

    private void btn_ekleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ekleActionPerformed
        // TODO add your handling code here:
        Kaydet();
}//GEN-LAST:event_btn_ekleActionPerformed

    private void btn_duzeltActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_duzeltActionPerformed
        // TODO add your handling code here:
        Duzelt();
    }//GEN-LAST:event_btn_duzeltActionPerformed

    private Date Giristarih(String tarihim) throws ParseException {
        Date zaman;
        SimpleDateFormat dformat;
        dformat = new SimpleDateFormat("dd-MM-yyyy");
        zaman = dformat.parse(tarihim);
        return zaman;
    }
    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_duzelt;
    private javax.swing.JButton btn_ekle;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox cb_evet;
    private javax.swing.JCheckBox cb_hayir;
    private org.jdesktop.swingx.JXDatePicker dp_tarih;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_daire;
    private javax.swing.JList list_daireSahipleri;
    private javax.swing.JTextField txt_ad;
    private javax.swing.JTextField txt_soyad;
    private javax.swing.JTextField txt_telefon;
    // End of variables declaration//GEN-END:variables
}
