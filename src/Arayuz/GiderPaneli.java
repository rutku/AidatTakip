/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * GiderPaneli.java
 *
 * Created on 07.Haz.2009, 13:34:16
 * list olayı oluşturulacak.
 * ardından düzenle butonu olayı yaratılacak.
 * 
 */
package Arayuz;

import islemler.aylikGiderAl;
import islemler.aylikGiderKayit;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import pojo.Aylikgider;

/**
 *
 * @author racih
 */
public class GiderPaneli extends javax.swing.JDialog {

    private EntityManager em;
    private aylikGiderAl aga;
    private aylikGiderKayit agk;
    private EntityManagerFactory emfx;
    private boolean durum = false;
    List<String> v;

    /** Creates new form GiderPaneli */
    public GiderPaneli(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        int width = getWidth();
        int height = getHeight();
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - width) / 2;
        int y = (screen.height - height) / 2;
        setBounds(x, y, width, height);
    }

    public void Baglanti(EntityManagerFactory emf) {
        emfx = emf;
        em = emf.createEntityManager();
        aga = new aylikGiderAl(emf);
        agk = new aylikGiderKayit(emf);
        onYukleme();
        listeyeEkle();
    }

    private void onYukleme() {
        DefaultComboBoxModel aylarCMB = new DefaultComboBoxModel();
        aylarCMB.addElement("Ocak");
        aylarCMB.addElement("Şubat");
        aylarCMB.addElement("Mart");
        aylarCMB.addElement("Nisan");
        aylarCMB.addElement("Mayıs");
        aylarCMB.addElement("Haziran");
        aylarCMB.addElement("Temmuz");
        aylarCMB.addElement("Ağustos");
        aylarCMB.addElement("Eylul");
        aylarCMB.addElement("Ekim");
        aylarCMB.addElement("Kasım");
        aylarCMB.addElement("Aralık");
        cmb_ay.setModel(aylarCMB);
        cmb_yil.removeAllItems();
        for (int i = 0; i < yilAl().size(); i++) {
            cmb_yil.addItem(yilAl().get(i));
        }
    }

    private void listeyeEkle() {
        list_aylikGider.setModel(liste());
    }

    private DefaultListModel liste() {
        DefaultListModel l = new DefaultListModel();
        String yil = cmb_yil.getSelectedItem().toString();
        int ay = cmb_ay.getSelectedIndex() + 1;
        String AyVeYil = "0" + ay + "-" + yil;
        for (int i = 0; i < aga.alTarih().size(); i++) {
            String ax = aga.alTarih().get(i).toString();
            String dbAvy = ax.substring(ax.indexOf("-") + 1);
            if (dbAvy.equals(AyVeYil) != false) {
                String trih = aga.alTarih().get(i).toString();
                String dbTarih = trih.substring(trih.indexOf("-") + 1);
                lbl_tarih.setText(dbTarih);
                l.addElement(aga.alSiraNo().get(i).toString());
            }
        }
        return l;
    }

    private Date tarih(String tarihim) throws ParseException {
        Date zaman;
        SimpleDateFormat dformat;
        dformat = new SimpleDateFormat("dd-MM-yyyy");
        zaman = dformat.parse(tarihim);
        return zaman;
    }

    private void Kaydet() {
        try {
            String tarih = Kayittarihi();
            Integer siraNo = Integer.parseInt(txt_siraNo.getText());
            String faturaNo = txt_faturaNo.getText();
            int gider = Integer.parseInt(txt_gider.getText());
            String aciklama = txt_aciklama.getText();
            String alinmaNedeni = txt_alinmaNedeni.getText();
            String cinsi = txt_cinsi.getText();
            Aylikgider ayg = new Aylikgider(aga.YerBul(), tarih, siraNo, faturaNo, cinsi, alinmaNedeni, kontrol(), aciklama);
            ayg.setGider(gider);
            agk.aylikGiderOlustur(ayg);
            listeyeEkle();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void devret() {
        try {
            int yil = 0;
            int ay = cmb_ay.getSelectedIndex();
            String devirTarihi = null;
            switch (ay) {
                case 0:
                    devirTarihi = "02";
                    break;
                case 1:
                    devirTarihi = "03";
                    break;
                case 2:
                    devirTarihi = "04";
                    break;
                case 3:
                    devirTarihi = "05";
                    break;
                case 4:
                    devirTarihi = "06";
                    break;
                case 5:
                    devirTarihi = "07";
                    break;
                case 6:
                    devirTarihi = "08";
                    break;
                case 7:
                    devirTarihi = "09";
                    break;
                case 8:
                    devirTarihi = "10";
                    break;
                case 9:
                    devirTarihi = "11";
                    break;
                case 10:
                    devirTarihi = "12";
                    break;
                case 11:
                    devirTarihi = "01";
                    sonrakiYiliAl();
                    break;
            }
            if (durum != false) {
                yilEkle y = new yilEkle(null, true);
                y.Baglanti(emfx, sonrakiYiliAl());
                y.setVisible(true);
                return;
            }
            if (devirTarihi.equals("01") != false) {
                yil = sonrakiYiliAl();
            }else{
                yil = Integer.parseInt(cmb_yil.getSelectedItem().toString());
            }

            String Tarih = "01-"+devirTarihi+"-"+yil;
            Aylikgider a = new Aylikgider(aga.YerBul(), Tarih, 1, txt_faturaNo.getText(),
                    txt_cinsi.getText(), txt_alinmaNedeni.getText(), 1, txt_aciklama.getText());
            a.setGider(Integer.parseInt(txt_gider.getText()));
            agk.aylikGiderOlustur(a);
           

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int sonrakiYiliAl(){
        int yil = 0;
            String suankiyil = cmb_yil.getSelectedItem().toString();
            int sonrakiYil = Integer.parseInt(suankiyil) + 1;
            for (int i = 0; i < yilSorgusu().size(); i++) {
                int dbYil = Integer.parseInt(yilSorgusu().get(i).toString());
                if (sonrakiYil == dbYil) {
                    yil = sonrakiYil;
                    durum = true;
                }
            }
            return yil;
    }

   

    private List yilSorgusu() {
        Query s = em.createQuery("SELECT y.yil FROM Yillar y");
        List l = s.getResultList();
        return l;
    }

    private int kontrol() {
        int sayi = 0;
        if (cb_demirbas.isSelected() != false) {
            sayi = 0;
        }
        if (cb_gelir.isSelected() != false) {
            sayi = 1;
        }
        return sayi;
    }

    private void VeriBellegi() {
        v = new ArrayList<String>();
        v.add(txt_siraNo.getText());
        v.add(Kayittarihi());
        v.add(txt_faturaNo.getText());
        v.add(txt_cinsi.getText());
        v.add(txt_alinmaNedeni.getText());
        if (cb_demirbas.isSelected() != false) {
            v.add("0");
        } else {
            v.add("1");
        }
        v.add(txt_gider.getText());
        v.add(txt_aciklama.getText());
    }

    private String Kayittarihi() {
        Date zaman;
        String tarih;
        SimpleDateFormat dformat;
        dformat = new SimpleDateFormat("dd-MM-yyyy");
        zaman = dp_tarih.getDate();
        tarih = dformat.format(zaman);
        return tarih;
    }

    private void Duzelt() {
        if (list_aylikGider.getSelectedIndex() < 0) {
            return;
        }
        String tarih = lbl_tarih.getText();
        String siraNo = list_aylikGider.getSelectedValue().toString();
        for (int i = 0; i < aga.alTarih().size(); i++) {
            String ax = aga.alTarih().get(i).toString();
            String as = aga.alSiraNo().get(i).toString();
            String dbTarih = ax.substring(ax.indexOf("-") + 1);
            if (dbTarih.equals(tarih) != false) {
                if (as.equals(siraNo) != false) {
                    int id = Integer.parseInt(aga.alId().get(i).toString());
                    if (v.get(0).hashCode() != txt_siraNo.getText().hashCode()) {
                        agk.ekleSiraNo(id, Integer.parseInt(txt_siraNo.getText()));
                    }
                    if (v.get(1).hashCode() != Kayittarihi().hashCode()) {
                        agk.ekleTarih(id, Kayittarihi());
                    }
                    if (v.get(2).hashCode() != txt_faturaNo.getText().hashCode()) {
                        agk.ekleFaturaNo(id, txt_faturaNo.getText());
                    }
                    if (v.get(3).hashCode() != txt_cinsi.getText().hashCode()) {
                        agk.ekleCinsi(id, txt_cinsi.getText());
                    }
                    if (v.get(4).hashCode() != txt_alinmaNedeni.getText().hashCode()) {
                        agk.ekleAlinmaNedeni(id, txt_alinmaNedeni.getText());
                    }
                    String sayi = "0";
                    if (v.get(5).hashCode() == sayi.hashCode()) {
                        agk.ekleDurum(id, 0);
                    } else {
                        agk.ekleDurum(id, 1);
                    }
                    if (v.get(6).hashCode() != txt_gider.getText().hashCode()) {
                        agk.ekleGider(id, Integer.parseInt(txt_gider.getText()));
                    }
                    if (v.get(7).hashCode() != txt_aciklama.getText().hashCode()) {
                        agk.ekleAciklama(id, txt_aciklama.getText());
                    }
                }
            }
        }
        listeyeEkle();
    }

    private void YeniKayit() {
        txt_aciklama.setText("");
        txt_alinmaNedeni.setText("");
        txt_cinsi.setText("");
        txt_faturaNo.setText("");
        txt_gider.setText("");
        txt_siraNo.setText("");
    }

    private List yilAl() {
        Query s = em.createQuery("SELECT y.yil FROM Yillar y");
        List l = s.getResultList();
        return l;
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
        cmb_yil = new javax.swing.JComboBox();
        cmb_ay = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        dp_tarih = new org.jdesktop.swingx.JXDatePicker();
        jScrollPane1 = new javax.swing.JScrollPane();
        list_aylikGider = new javax.swing.JList();
        lbl_tarih = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cb_gelir = new javax.swing.JCheckBox();
        cb_demirbas = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        txt_gider = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_aciklama = new javax.swing.JTextArea();
        btn_kaydet = new javax.swing.JButton();
        btn_duzelt = new javax.swing.JButton();
        txt_faturaNo = new javax.swing.JTextField();
        txt_cinsi = new javax.swing.JTextField();
        txt_alinmaNedeni = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_siraNo = new javax.swing.JTextField();
        btn_yeni = new javax.swing.JButton();
        btn_yilEkle = new javax.swing.JButton();
        btn_devret = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Aidat Takip : Gider Paneli");

        jLabel1.setText("Yıl :");

        jLabel2.setText("Ay :");

        cmb_yil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmb_ay.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmb_ay.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_ayİtemStateChanged(evt);
            }
        });

        jLabel3.setText("Kayıt Tarihi :");

        list_aylikGider.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        list_aylikGider.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                list_aylikGiderMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(list_aylikGider);

        lbl_tarih.setText("yyyy/mm");

        jLabel4.setText("Fatura No :");

        jLabel5.setText("Cinsi :");

        jLabel6.setText("Alınma Nedeni :");

        buttonGroup1.add(cb_gelir);
        cb_gelir.setText("Gider");

        buttonGroup1.add(cb_demirbas);
        cb_demirbas.setText("Demirbaş");

        jLabel7.setText("Tutar :");

        jLabel8.setText("Açıklama :");

        txt_aciklama.setColumns(20);
        txt_aciklama.setRows(5);
        jScrollPane2.setViewportView(txt_aciklama);

        btn_kaydet.setText("Kaydet");
        btn_kaydet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_kaydetActionPerformed(evt);
            }
        });

        btn_duzelt.setText("Düzelt");
        btn_duzelt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_duzeltActionPerformed(evt);
            }
        });

        jLabel9.setText("Sıra No :");

        btn_yeni.setText("Yeni");
        btn_yeni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_yeniActionPerformed(evt);
            }
        });

        btn_yilEkle.setText("Yil Ekle");
        btn_yilEkle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_yilEkleActionPerformed(evt);
            }
        });

        btn_devret.setText("Devret");
        btn_devret.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_devretActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addComponent(lbl_tarih, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btn_devret)
                        .addGap(16, 16, 16)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_cinsi)
                                    .addComponent(txt_alinmaNedeni)
                                    .addComponent(dp_tarih, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_faturaNo)
                                    .addComponent(txt_siraNo, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cb_gelir)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cb_demirbas))
                                    .addComponent(txt_gider, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(btn_yeni)
                                        .addGap(32, 32, 32)
                                        .addComponent(btn_kaydet)
                                        .addGap(31, 31, 31)
                                        .addComponent(btn_duzelt))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cmb_yil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_yilEkle))
                            .addComponent(cmb_ay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(35, 35, 35))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(lbl_tarih)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(98, 98, 98)
                        .addComponent(btn_devret))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmb_yil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(btn_yilEkle))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmb_ay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_siraNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(dp_tarih, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(txt_faturaNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(txt_cinsi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_alinmaNedeni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cb_gelir)
                            .addComponent(cb_demirbas))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txt_gider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_duzelt)
                            .addComponent(btn_yeni)
                            .addComponent(btn_kaydet))))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_yeniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_yeniActionPerformed

        YeniKayit();
    }//GEN-LAST:event_btn_yeniActionPerformed

    private void btn_kaydetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_kaydetActionPerformed
        // TODO add your handling code here:
        Kaydet();
    }//GEN-LAST:event_btn_kaydetActionPerformed

    private void btn_duzeltActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_duzeltActionPerformed
        // TODO add your handling code here:
        Duzelt();
    }//GEN-LAST:event_btn_duzeltActionPerformed

    private void btn_yilEkleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_yilEkleActionPerformed
        // TODO add your handling code here:
        yilEkle y = new yilEkle(null, true);
        y.Baglanti(emfx, Integer.parseInt(cmb_yil.getSelectedItem().toString()));
        y.setVisible(true);
    }//GEN-LAST:event_btn_yilEkleActionPerformed

    private void cmb_ayİtemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_ayİtemStateChanged
        // TODO add your handling code here:
        listeyeEkle();
    }//GEN-LAST:event_cmb_ayİtemStateChanged

    private void list_aylikGiderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_list_aylikGiderMouseClicked
        // TODO add your handling code here:
        try {
            int id = 0;
            String numara = list_aylikGider.getSelectedValue().toString();
            for (int i = 0; i < aga.alTarih().size(); i++) {
                String trih = aga.alTarih().get(i).toString();
                String dbTarih = trih.substring(trih.indexOf("-") + 1);
                if (dbTarih.equals(lbl_tarih.getText()) != false) {
                    if (aga.alSiraNo().get(i).toString().equals(numara) != false) {
                        id = Integer.parseInt(aga.alId().get(i).toString());
                    }
                }
            }
            Aylikgider a = em.find(Aylikgider.class, id);
            if (a != null) {
                txt_aciklama.setText(a.getAciklama());
                txt_alinmaNedeni.setText(a.getAlinmanedeni());
                txt_cinsi.setText(a.getCinsi());
                txt_faturaNo.setText(a.getFaturano());
                txt_gider.setText(a.getGider().toString());
                txt_siraNo.setText(String.valueOf(a.getSirano()));
                if (kontrol() == 0) {
                    cb_demirbas.setSelected(true);
                } else {
                    cb_gelir.setSelected(true);
                }
                dp_tarih.setDate(tarih(a.getTarih()));
            }
            VeriBellegi();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_list_aylikGiderMouseClicked

    private void btn_devretActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_devretActionPerformed
        // TODO add your handling code here:
        devret();
    }//GEN-LAST:event_btn_devretActionPerformed
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_devret;
    private javax.swing.JButton btn_duzelt;
    private javax.swing.JButton btn_kaydet;
    private javax.swing.JButton btn_yeni;
    private javax.swing.JButton btn_yilEkle;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox cb_demirbas;
    private javax.swing.JCheckBox cb_gelir;
    private javax.swing.JComboBox cmb_ay;
    private javax.swing.JComboBox cmb_yil;
    private org.jdesktop.swingx.JXDatePicker dp_tarih;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_tarih;
    private javax.swing.JList list_aylikGider;
    private javax.swing.JTextArea txt_aciklama;
    private javax.swing.JTextField txt_alinmaNedeni;
    private javax.swing.JTextField txt_cinsi;
    private javax.swing.JTextField txt_faturaNo;
    private javax.swing.JTextField txt_gider;
    private javax.swing.JTextField txt_siraNo;
    // End of variables declaration//GEN-END:variables
}
