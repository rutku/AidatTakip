/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AnaPanel.java
 *
 * Created on 07.Haz.2009, 12:43:07
 */
package Arayuz;

import islemler.aidatAl;
import islemler.aidatKaydet;
import islemler.aylikGiderAl;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import pojo.Aidatbilgileri;
import pojo.Aidatlar;
import pojo.Aylikgider;

/**
 *
 * @author racih
 */
public class AnaPanel extends javax.swing.JFrame {

    private EntityManager em;
    private EntityManagerFactory emf;
    private aidatAl aa;
    private aidatKaydet ak;
    private boolean durum;

    /** Creates new form AnaPanel */
    public AnaPanel() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        
        initComponents();
        Image im = Toolkit.getDefaultToolkit().getImage("simge/ak.png");
        this.setIconImage(im);
        int width = getWidth();
        int height = getHeight();
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - width) / 2;
        int y = (screen.height - height) / 2;
        setBounds(x, y, width, height);
        
    }

    private void OnYukleme() {
        
        Sorgu();
        DefaultComboBoxModel aylarCMB = new DefaultComboBoxModel();
        DefaultComboBoxModel yilCMB = new DefaultComboBoxModel();
        DefaultComboBoxModel blokCMB = new DefaultComboBoxModel();
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
        blokCMB.addElement("A");
        blokCMB.addElement("B");
        blokCMB.addElement("C");
        blokCMB.addElement("D");
        cmb_blok.setModel(blokCMB);
        if (yilAl() != null) {
            if (yilAl().size() > 0) {
                for (int i = 0; i < yilAl().size(); i++) {
                    yilCMB.addElement(yilAl().get(i).toString());
                }
            }
        }
        cmb_yil.setModel(yilCMB);
        if (aa.alId().size() == 0) {
            return;
        } else {
            tbl_gelirGider.setModel(giderEkle());
        }
    }

    public  void Baglanti(EntityManagerFactory emfx) {
        emf = emfx;
        em = emf.createEntityManager();
        aa = new aidatAl(emf);
        ak = new aidatKaydet(emf);
        OnYukleme();
    }

    private void Sorgu() {
        int bl = cmb_blok.getSelectedIndex();
        switch (bl) {
            case 0:
                lbl_blokTable.setText("59");
                aidatlar("59");
                break;
            case 1:
                lbl_blokTable.setText("61");
                aidatlar("61");
                break;
            case 2:
                lbl_blokTable.setText("63");
                aidatlar("63");
                break;
            case 3:
                lbl_blokTable.setText("25");
                aidatlar("25");
                break;
        }
        tabloyaEkle();
    }

    private void aidatlar(String blok) {
        DefaultListModel liste = new DefaultListModel();
        for (int i = 0; i < aa.alTarih().size(); i++) {
            String AyveYil = aa.alTarih().get(i).toString();
            String cAyveYil = cmb_yil.getSelectedItem().toString();
            if (AyveYil.hashCode() == cAyveYil.hashCode()) {
                String blokx = aa.alDaire().get(i).toString();
                String bloky = blokx.substring(blokx.indexOf(""), blokx.lastIndexOf("/"));
                if (blok.hashCode() == bloky.hashCode()) {
                    String dairx = aa.alDaire().get(i).toString();
                    int id = aa.alId().get(i);
                    if (Aylar(id) == durum) {
                        String daire = dairx.substring(dairx.indexOf("/") + 1);
                        liste.addElement(daire);
                    }

                }
            }
        }
        list_daireler.setModel(liste);
    }

    private void tabloyaEkle() {
        DefaultTableModel t = new DefaultTableModel();
        t.addColumn("Daire");
        t.addColumn("Ocak");
        t.addColumn("Şubat");
        t.addColumn("Mart");
        t.addColumn("Nisan");
        t.addColumn("Mayıs");
        t.addColumn("Haziran");
        t.addColumn("Temmuz");
        t.addColumn("Ağustos");
        t.addColumn("Eylül");
        t.addColumn("Ekim");
        t.addColumn("Kasım");
        t.addColumn("Aralık");
        String blok = lbl_blokTable.getText();
        for (int i = 0; i < aa.alTarih().size(); i++) {
            String blokx = aa.alDaire().get(i).toString();
            String dbBlok = blokx.substring(blokx.indexOf(""), blokx.lastIndexOf("/"));
            if (blok.equals(dbBlok) != false) {
                int id = aa.alId().get(i);
                Aidatlar a = em.find(Aidatlar.class, id);
                Object[] veri = {a.getDaire(), a.getOcak(), a.getSubat(), a.getMart(),
                    a.getNisan(), a.getMayis(), a.getHaziran(), a.getTemmuz(), a.getAgustos(),
                    a.getEylul(), a.getEkim(), a.getKasim(), a.getAralik()};
                t.addRow(veri);
            }
        }
        tbl_daireler.setModel(t);
    }

    private boolean Aylar(int id) {
        boolean d = false;
        int ay = cmb_ay.getSelectedIndex();
        Aidatlar a = em.find(Aidatlar.class, id);
        switch (ay) {
            case 0:
                if (a.getOcak() == null) {
                    d = false;
                } else {
                    d = true;
                }
                break;
            case 1:
                if (a.getSubat() == null) {
                    d = false;
                } else {
                    d = true;
                }
                break;
            case 2:
                if (a.getMart() == null) {
                    d = false;
                } else {
                    d = true;
                }
                break;
            case 3:
                if (a.getNisan() == null) {
                    d = false;
                } else {
                    d = true;
                }
                break;
            case 4:
                if (a.getMayis() == null) {
                    d = false;
                } else {
                    d = true;
                }
                break;
            case 5:
                if (a.getHaziran() == null) {
                    d = false;
                } else {
                    d = true;
                }
                break;
            case 6:
                if (a.getTemmuz() == null) {
                    d = false;
                } else {
                    d = true;
                }
                break;
            case 7:
                if (a.getAgustos() == null) {
                    d = false;
                } else {
                    d = true;
                }
                break;
            case 8:
                if (a.getEylul() == null) {
                    d = false;
                } else {
                    d = true;
                }
                break;
            case 9:
                if (a.getEkim() == null) {
                    d = false;
                } else {
                    d = true;
                }
                break;
            case 10:
                if (a.getKasim() == null) {
                    d = false;
                } else {
                    d = true;
                }
                break;
            case 11:
                if (a.getAralik() == null) {
                    d = false;
                } else {
                    d = true;
                }
                break;

        }
        return d;
    }

    private List yilAl() {
        Query sorgu = em.createQuery("SELECT y.yil FROM Yillar y");
        List l = sorgu.getResultList();
        return l;
    }

    private Date tarih(String tarihim) throws ParseException {
        Date zaman;
        SimpleDateFormat dformat;
        dformat = new SimpleDateFormat("dd-MM-yyyy");
        zaman = dformat.parse(tarihim);
        return zaman;
    }

    private String Kayittarihi() {
        Date zaman;
        String tarih;
        SimpleDateFormat dformat;
        dformat = new SimpleDateFormat("dd-MM-yyyy");
        zaman = dp_odemeTarihi.getDate();
        tarih = dformat.format(zaman);
        return tarih;
    }

    private void listedenAl(int id) {
        try {
            int ay = cmb_ay.getSelectedIndex();
            Aidatlar af = em.find(Aidatlar.class, id);
            Aidatbilgileri ab = em.find(Aidatbilgileri.class, id);

            switch (ay) {
                case 0:
                    if (af.getOcak() != null) {
                        txt_aidat.setText(af.getOcak().toString());
                        FM(ab.getOcak());
                    }
                    break;
                case 1:
                    if (af.getSubat() != null) {
                        txt_aidat.setText(af.getSubat().toString());
                        FM(ab.getSubat());
                    }
                    break;
                case 2:
                    if (af.getMart() != null) {
                        txt_aidat.setText(af.getMart().toString());
                        FM(ab.getMart());
                    }
                    break;
                case 3:
                    if (af.getNisan() != null) {
                        txt_aidat.setText(af.getNisan().toString());
                        FM(ab.getNisan());
                    }
                    break;
                case 4:
                    if (af.getMayis() != null) {
                        txt_aidat.setText(af.getMayis().toString());
                        FM(ab.getMayis());
                    }
                    break;
                case 5:
                    if (af.getHaziran() != null) {
                        txt_aidat.setText(af.getHaziran().toString());
                        FM(ab.getHaziran());
                    }
                    break;
                case 6:
                    if (af.getTemmuz() != null) {
                        txt_aidat.setText(af.getTemmuz().toString());
                        FM(ab.getTemmuz());
                    }
                    break;
                case 7:
                    if (af.getAgustos() != null) {
                        txt_aidat.setText(af.getAgustos().toString());
                        FM(ab.getAgustos());
                    }
                    break;
                case 8:
                    if (af.getEylul() != null) {
                        txt_aidat.setText(af.getEylul().toString());
                        FM(ab.getEylul());
                    }
                    break;
                case 9:
                    if (af.getEkim() != null) {
                        txt_aidat.setText(af.getEkim().toString());
                        FM(ab.getEkim());
                    }
                    break;
                case 10:
                    if (af.getKasim() != null) {
                        txt_aidat.setText(af.getKasim().toString());
                        FM(ab.getKasim());
                    }
                    break;
                case 11:
                    if (af.getAralik() != null) {
                        txt_aidat.setText(af.getAralik().toString());
                        FM(ab.getAralik());
                    }
                    break;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void FM(String fm) {
        try {
            String f = fm.substring(fm.indexOf(""), fm.lastIndexOf("//"));
            String m = fm.substring(fm.indexOf("//") + 2, fm.lastIndexOf("/"));
            String t = fm.substring(fm.lastIndexOf("/") + 1);
            txt_fisNo.setText(f);
            txt_makbuzNo.setText(m);
            dp_odemeTarihi.setDate(tarih(t));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void aidatEkle() {
        String daire = lbl_blokTable.getText() + "/" + list_daireler.getSelectedValue().toString();
        String tarih = cmb_yil.getSelectedItem().toString();
        for (int i = 0; i < aa.alTarih().size(); i++) {
            String dbTarih = aa.alTarih().get(i).toString();
            if (tarih.equals(dbTarih) != false) {
                String dbDaire = aa.alDaire().get(i).toString();
                if (daire.equals(dbDaire) != false) {
                    int id = aa.alId().get(i);
                    aylaraAidatEkle(id);
                }
            }
        }

    }

    private void aylaraAidatEkle(int id) {
        em.getTransaction().begin();
        int ay = cmb_ay.getSelectedIndex();
        Aidatlar a = em.find(Aidatlar.class, id);
        Aidatbilgileri ab = em.find(Aidatbilgileri.class, id);
        if (a != null && ab != null) {
            switch (ay) {
                case 0:
                    a.setOcak(Integer.parseInt(txt_aidat.getText()));
                    ab.setOcak(txt_fisNo.getText() + "//" + txt_makbuzNo.getText() + "/" + Kayittarihi());
                    break;
                case 1:
                    a.setSubat(Integer.parseInt(txt_aidat.getText()));
                    ab.setSubat(txt_fisNo.getText() + "//" + txt_makbuzNo.getText() + "/" + Kayittarihi());
                    break;
                case 2:
                    a.setMart(Integer.parseInt(txt_aidat.getText()));
                    ab.setMart(txt_fisNo.getText() + "//" + txt_makbuzNo.getText() + "/" + Kayittarihi());
                    break;
                case 3:
                    a.setNisan(Integer.parseInt(txt_aidat.getText()));
                    ab.setNisan(txt_fisNo.getText() + "//" + txt_makbuzNo.getText() + "/" + Kayittarihi());
                    break;
                case 4:
                    a.setMayis(Integer.parseInt(txt_aidat.getText()));
                    ab.setMayis(txt_fisNo.getText() + "//" + txt_makbuzNo.getText() + "/" + Kayittarihi());
                    break;
                case 5:
                    a.setHaziran(Integer.parseInt(txt_aidat.getText()));
                    ab.setHaziran(txt_fisNo.getText() + "//" + txt_makbuzNo.getText() + "/" + Kayittarihi());
                    break;
                case 6:
                    a.setTemmuz(Integer.parseInt(txt_aidat.getText()));
                    ab.setTemmuz(txt_fisNo.getText() + "//" + txt_makbuzNo.getText() + "/" + Kayittarihi());
                    break;
                case 7:
                    a.setAgustos(Integer.parseInt(txt_aidat.getText()));
                    ab.setAgustos(txt_fisNo.getText() + "//" + txt_makbuzNo.getText() + "/" + Kayittarihi());
                    break;
                case 8:
                    a.setEylul(Integer.parseInt(txt_aidat.getText()));
                    ab.setEylul(txt_fisNo.getText() + "//" + txt_makbuzNo.getText() + "/" + Kayittarihi());
                    break;
                case 9:
                    a.setEkim(Integer.parseInt(txt_aidat.getText()));
                    ab.setEkim(txt_fisNo.getText() + "//" + txt_makbuzNo.getText() + "/" + Kayittarihi());
                    break;
                case 10:
                    a.setKasim(Integer.parseInt(txt_aidat.getText()));
                    ab.setKasim(txt_fisNo.getText() + "//" + txt_makbuzNo.getText() + "/" + Kayittarihi());
                    break;
                case 11:
                    a.setAralik(Integer.parseInt(txt_aidat.getText()));
                    ab.setAralik(txt_fisNo.getText() + "//" + txt_makbuzNo.getText() + "/" + Kayittarihi());
                    break;
            }
            em.merge(a);
            em.merge(ab);
            em.getTransaction().commit();
        }
    }

    private DefaultTableModel GiderTablosunaEkle() {
        DefaultTableModel t = new DefaultTableModel();
        t.addColumn("Blok");
        t.addColumn("Ocak");
        t.addColumn("Şubat");
        t.addColumn("Mart");
        t.addColumn("Nisan");
        t.addColumn("Mayıs");
        t.addColumn("Haziran");
        t.addColumn("Temmuz");
        t.addColumn("Ağustos");
        t.addColumn("Eylül");
        t.addColumn("Ekim");
        t.addColumn("Kasım");
        t.addColumn("Aralık");
        t.addColumn("Gelir");
        String blok[] = {"59", "61", "63", "25"};

        for (int i = 0; i < blok.length; i++) {
            List<Integer> aylikAidat = new ArrayList<Integer>();
            int ocak = 0, subat = 0, mart = 0, nisan = 0, mayis = 0, haziran = 0,
                    temmuz = 0, agustos = 0, eylul = 0, ekim = 0, kasim = 0, aralik = 0;

            for (int k = 0; k < aa.alDaire().size(); k++) {
                String dbD = aa.alDaire().get(k).toString();
                String dbBlok = dbD.substring(dbD.indexOf(""), dbD.lastIndexOf("/"));
                if (dbBlok.equals(blok[i]) != false) {
                    int id = aa.alId().get(k);
                    Aidatlar a = em.find(Aidatlar.class, id);
                    if (a.getOcak() != null) {
                        ocak = a.getOcak() + ocak;
                    }
                    if (a.getSubat() != null) {
                        subat = a.getSubat() + subat;
                    }
                    if (a.getMart() != null) {
                        mart = a.getMart() + mart;
                    }
                    if (a.getNisan() != null) {
                        nisan = a.getNisan() + nisan;
                    }
                    if (a.getMayis() != null) {
                        mayis = a.getMayis() + mayis;
                    }
                    if (a.getHaziran() != null) {
                        haziran = a.getHaziran() + haziran;
                    }
                    if (a.getTemmuz() != null) {
                        temmuz = a.getTemmuz() + temmuz;
                    }
                    if (a.getAgustos() != null) {
                        agustos = a.getAgustos() + agustos;
                    }
                    if (a.getEylul() != null) {
                        eylul = a.getEylul() + eylul;
                    }
                    if (a.getEkim() != null) {
                        ekim = a.getEkim() + ekim;
                    }
                    if (a.getKasim() != null) {
                        kasim = a.getKasim() + kasim;
                    }
                    if (a.getAralik() != null) {
                        aralik = a.getAralik() + aralik;
                    }

                }
                aylikAidat.add(ocak);
                aylikAidat.add(subat);
                aylikAidat.add(mart);
                aylikAidat.add(nisan);
                aylikAidat.add(mayis);
                aylikAidat.add(haziran);
                aylikAidat.add(temmuz);
                aylikAidat.add(agustos);
                aylikAidat.add(eylul);
                aylikAidat.add(ekim);
                aylikAidat.add(kasim);
                aylikAidat.add(aralik);


            }
            int toplam = aylikAidat.get(0) + aylikAidat.get(1) +
                    aylikAidat.get(2) + aylikAidat.get(3) + aylikAidat.get(4) + aylikAidat.get(5) +
                    aylikAidat.get(6) + aylikAidat.get(7) + aylikAidat.get(8) + aylikAidat.get(9) +
                    aylikAidat.get(10) + aylikAidat.get(11);
            Object[] veri = {blok[i], aylikAidat.get(0), aylikAidat.get(1),
                aylikAidat.get(2), aylikAidat.get(3), aylikAidat.get(4), aylikAidat.get(5),
                aylikAidat.get(6), aylikAidat.get(7), aylikAidat.get(8), aylikAidat.get(9),
                aylikAidat.get(10), aylikAidat.get(11), toplam};
            t.addRow(veri);
        }
        return t;
    }

    public DefaultTableModel giderEkle() {
        aylikGiderAl aga = new aylikGiderAl(emf);
        DefaultTableModel gider = GiderTablosunaEkle();
        int para = Integer.parseInt(gider.getValueAt(0, 13).toString()) +
                Integer.parseInt(gider.getValueAt(1, 13).toString()) +
                Integer.parseInt(gider.getValueAt(2, 13).toString()) +
                Integer.parseInt(gider.getValueAt(3, 13).toString());
        Object[] veri = {"", "", "", "", "", "", "", "", "", "", "", "Genel", "Toplam", para};
        Object[] veri1 = {"", "", "", "", "", "", "", "Gider", "Tablosu", "", "", "", "", ""};
        Object[] veri2 = {"Blok", "Ocak", "Şubat", "Mart", "Nisan", "Mayıs",
            "Haziran", "Temmuz", "Ağustos", "Eylül", "Ekim", "Kasım", "Aralık", "Toplam"};
        gider.insertRow(4, veri);
        gider.insertRow(5, veri1);
        gider.insertRow(6, veri2);

        int ocak = 0, subat = 0, mart = 0, nisan = 0, mayis = 0, haziran = 0, temmuz = 0,
                agustos = 0, eylul = 0, ekim = 0, kasim = 0, aralik = 0;
        for (int i = 0; i < aga.alId().size(); i++) {
            int id = Integer.parseInt(aga.alId().get(i).toString());
            Aylikgider g = em.find(Aylikgider.class, id);
            String trih = g.getTarih();
            String dbTarih = trih.substring(trih.indexOf("-") + 1);
            String yil = cmb_yil.getSelectedItem().toString();
            if (dbTarih.equals("01-" + yil) != false) {
                ocak = g.getGider() + ocak;
            }
            if (dbTarih.equals("02-" + yil) != false) {
                subat = g.getGider() + subat;
            }
            if (dbTarih.equals("03-" + yil) != false) {
                mart = g.getGider() + mart;
            }
            if (dbTarih.equals("04-" + yil) != false) {
                nisan = g.getGider() + nisan;
            }
            if (dbTarih.equals("05-" + yil) != false) {
                mayis = g.getGider() + mayis;
            }
            if (dbTarih.equals("06-" + yil) != false) {
                haziran = g.getGider() + haziran;
            }
            if (dbTarih.equals("07-" + yil) != false) {
                temmuz = g.getGider() + temmuz;
            }
            if (dbTarih.equals("08-" + yil) != false) {
                agustos = g.getGider() + agustos;
            }
            if (dbTarih.equals("09-" + yil) != false) {
                eylul = g.getGider() + eylul;
            }
            if (dbTarih.equals("10-" + yil) != false) {
                ekim = g.getGider() + ekim;
            }
            if (dbTarih.equals("11-" + yil) != false) {
                kasim = g.getGider() + kasim;
            }
            if (dbTarih.equals("12-" + yil) != false) {
                aralik = g.getGider() + aralik;
            }
        }
        int giderToplami = ocak + subat + mart + nisan + mayis + haziran + temmuz + agustos + eylul + ekim + kasim + aralik;
        Object[] veri3 = {"", ocak, subat, mart, nisan, mayis, haziran, temmuz, agustos,
            eylul, ekim, kasim, aralik, giderToplami};
        gider.insertRow(7, veri3);

        Object[] veri4 = {"", "", "", "", "", "", "", "", "", "", "", "", "", ""};
        gider.insertRow(8, veri4);
        Object[] veri5 = {"", "", "", "", "", "", "", "Kasa", "", "", "", "", "", ""};
        gider.insertRow(9, veri5);
        Object[] veri6 = {"Blok", "Ocak", "Şubat", "Mart", "Nisan", "Mayıs",
            "Haziran", "Temmuz", "Ağustos", "Eylül", "Ekim", "Kasım", "Aralık", "Toplam"};
        gider.insertRow(10, veri6);
        //Bütün Blokların Gelirlerinin Toplamı
        int xocak = 0, xsubat = 0, xmart = 0, xnisan = 0, xmayis = 0, xhaziran = 0, xtemmuz = 0,
                xagustos = 0, xeylul = 0, xekim = 0, xkasim = 0, xaralik = 0;
        for (int i = 1; i < 13; i++) {
            int a = Integer.parseInt(gider.getValueAt(0, i).toString());
            int b = Integer.parseInt(gider.getValueAt(1, i).toString());
            int c = Integer.parseInt(gider.getValueAt(2, i).toString());
            int d = Integer.parseInt(gider.getValueAt(3, i).toString());
            int gelirToplam = a + b + c + d;
            if (i == 1) {
                xocak = gelirToplam;
            }
            if (i == 2) {
                xsubat = gelirToplam;
            }
            if (i == 3) {
                xmart = gelirToplam;
            }
            if (i == 4) {
                xnisan = gelirToplam;
            }
            if (i == 5) {
                xmayis = gelirToplam;
            }
            if (i == 6) {
                xhaziran = gelirToplam;
            }
            if (i == 7) {
                xtemmuz = gelirToplam;
            }
            if (i == 8) {
                xagustos = gelirToplam;
            }
            if (i == 9) {
                xeylul = gelirToplam;
            }
            if (i == 10) {
                xekim = gelirToplam;
            }
            if (i == 11) {
                xkasim = gelirToplam;
            }
            if (i == 12) {
                xaralik = gelirToplam;
            }
        }
        int gelirToplami = xocak + xsubat + xmart + xnisan + xmayis + xhaziran + xtemmuz +
                xagustos + xeylul + xekim + xkasim + xaralik;
        //Giderleri Alıyorum
        int yocak = 0, ysubat = 0, ymart = 0, ynisan = 0, ymayis = 0, yhaziran = 0,
                ytemmuz = 0, yagustos = 0, yeylul = 0, yekim = 0, ykasim = 0, yaralik = 0;
        for (int i = 1; i < 13; i++) {
            int a = Integer.parseInt(gider.getValueAt(0, i).toString());

            int giderToplam = a;
            if (i == 1) {
                yocak = giderToplam;
            }
            if (i == 2) {
                ysubat = giderToplam;
            }
            if (i == 3) {
                ymart = giderToplam;
            }
            if (i == 4) {
                ynisan = giderToplam;
            }
            if (i == 5) {
                ymayis = giderToplam;
            }
            if (i == 6) {
                yhaziran = giderToplam;
            }
            if (i == 7) {
                ytemmuz = giderToplam;
            }
            if (i == 8) {
                yagustos = giderToplam;
            }
            if (i == 9) {
                yeylul = giderToplam;
            }
            if (i == 10) {
                yekim = giderToplam;
            }
            if (i == 11) {
                ykasim = giderToplam;
            }
            if (i == 12) {
                yaralik = giderToplam;
            }
        }
        int xyocak = xocak - yocak;
        int xysubat = xsubat - ysubat;
        int xymart = xmart - ymart;
        int xynisan = xnisan - ynisan;
        int xymayis = xmayis - ymayis;
        int xyhaziran = xhaziran - yhaziran;
        int xytemmuz = xtemmuz - ytemmuz;
        int xyagustos = xagustos - yagustos;
        int xyeylul = xeylul - yeylul;
        int xyekim = xekim - yekim;
        int xykasim = xkasim - ykasim;
        int xyaralik = xaralik - yaralik;
        int kasayaKalan = gelirToplami - giderToplami;
        Object[] veri7 = {"", xyocak, xysubat, xymart, xynisan, xymayis,
            xyhaziran, xytemmuz, xyagustos, xyeylul, xyekim, xykasim, xyaralik, kasayaKalan};
        gider.insertRow(11, veri7);
        return gider;
    }

    private void alanlariTemizle() {
        txt_aidat.setText("");
        txt_fisNo.setText("");
        txt_makbuzNo.setText("");
        dp_odemeTarihi.removeAll();
        lbl_daireAdi.setText("-");
        OnYukleme();
    }

    private void exceleAidatYazdir() throws IOException, WriteException {
        WritableWorkbook wwb = Workbook.createWorkbook(new File("Aidatlar.xls"));
        WritableSheet ws = wwb.createSheet("Aidatlar", 0);
        Label e1 = new Label(8, 1, "Aidatlar");
        Label e2 = new Label(2, 2, "Daire");
        Label e3 = new Label(3, 2, "Ocak");
        Label e4 = new Label(4, 2, "Şubat");
        Label e5 = new Label(5, 2, "Mart");
        Label e6 = new Label(6, 2, "Nisan");
        Label e7 = new Label(7, 2, "Mayıs");
        Label e8 = new Label(8, 2, "Haziran");
        Label e9 = new Label(9, 2, "Temmuz");
        Label e10 = new Label(10, 2, "Ağustos");
        Label e11 = new Label(11, 2, "Eylül");
        Label e12 = new Label(12, 2, "Ekim");
        Label e13 = new Label(13, 2, "Kasım");
        Label e14 = new Label(14, 2, "Aralık");
        ws.addCell(e1);
        ws.addCell(e2);
        ws.addCell(e3);
        ws.addCell(e4);
        ws.addCell(e5);
        ws.addCell(e6);
        ws.addCell(e7);
        ws.addCell(e8);
        ws.addCell(e9);
        ws.addCell(e10);
        ws.addCell(e11);
        ws.addCell(e12);
        ws.addCell(e13);
        ws.addCell(e14);

        int boyut = tbl_daireler.getRowCount() + 3;

        int uzunluk = tbl_daireler.getColumnCount() + 2;
        int x = 0;
        for (int y = 2; y < uzunluk; y++) {
            int a = 0;
            for (int i = 3; i < boyut; i++) {
                if (tbl_daireler.getValueAt(a, x) != null) {
                    String veri = tbl_daireler.getValueAt(a, x).toString();
                    ws.addCell(new Label(y, i, veri));
                } else {
                    ws.addCell(new Label(y, i, ""));
                }
                a++;
            }
            x++;
        }
        wwb.write();
        wwb.close();
    }

    private void exceleGelirGiderYazdir() throws IOException, WriteException {
        WritableWorkbook wwb = Workbook.createWorkbook(new File("GelirGider.xls"));
        WritableSheet ws = wwb.createSheet("Gelir-Gider", 0);
        Label e1 = new Label(8, 1, "Gelir Tablosu");
        Label e2 = new Label(2, 2, "Blok");
        Label e3 = new Label(3, 2, "Ocak");
        Label e4 = new Label(4, 2, "Şubat");
        Label e5 = new Label(5, 2, "Mart");
        Label e6 = new Label(6, 2, "Nisan");
        Label e7 = new Label(7, 2, "Mayıs");
        Label e8 = new Label(8, 2, "Haziran");
        Label e9 = new Label(9, 2, "Temmuz");
        Label e10 = new Label(10, 2, "Ağustos");
        Label e11 = new Label(11, 2, "Eylul");
        Label e12 = new Label(12, 2, "Ekim");
        Label e13 = new Label(13, 2, "Kasım");
        Label e14 = new Label(14, 2, "Aralık");
        Label e15 = new Label(15, 2, "Gelir");
        ws.addCell(e1);
        ws.addCell(e2);
        ws.addCell(e3);
        ws.addCell(e4);
        ws.addCell(e5);
        ws.addCell(e6);
        ws.addCell(e7);
        ws.addCell(e8);
        ws.addCell(e9);
        ws.addCell(e10);
        ws.addCell(e11);
        ws.addCell(e12);
        ws.addCell(e13);
        ws.addCell(e14);
        ws.addCell(e15);

        int boyut = tbl_gelirGider.getRowCount() + 3;

        int uzunluk = tbl_gelirGider.getColumnCount() + 2;
        int x = 0;
        for (int y = 2; y < uzunluk; y++) {
            int a = 0;
            for (int i = 3; i < boyut; i++) {
                if (tbl_gelirGider.getValueAt(a, x) != null) {
                    String veri = tbl_gelirGider.getValueAt(a, x).toString();
                    ws.addCell(new Label(y, i, veri));
                } else {
                    ws.addCell(new Label(y, i, ""));
                }
                a++;
            }
            x++;
        }
        wwb.write();
        wwb.close();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        chk_odemisleriGoster = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        cmb_blok = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        list_daireler = new javax.swing.JList();
        jLabel2 = new javax.swing.JLabel();
        cmb_ay = new javax.swing.JComboBox();
        lbl_blokyazi = new javax.swing.JLabel();
        txt_aidat = new javax.swing.JTextField();
        btn_aidatEkle = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        lbl_blokTable = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btn_excel = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        lbl_daireAdi = new javax.swing.JLabel();
        lbl_daireAdi1 = new javax.swing.JLabel();
        txt_fisNo = new javax.swing.JTextField();
        txt_makbuzNo = new javax.swing.JTextField();
        lbl_daireAdi2 = new javax.swing.JLabel();
        lbl_daireAdi3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cmb_yil = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_daireler = new javax.swing.JTable();
        dp_odemeTarihi = new org.jdesktop.swingx.JXDatePicker();
        btn_yeni = new javax.swing.JButton();
        btn_yenileDaire = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_gelirGider = new javax.swing.JTable();
        btn_giderEkle = new javax.swing.JButton();
        btn_excel2 = new javax.swing.JButton();
        btn_yenileKasa = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jmi_sifreDegistir = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jmi_hakkinda = new javax.swing.JMenuItem();
        jmi_yardim = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Aidat Takip Programı");
        setForeground(new java.awt.Color(153, 0, 0));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        chk_odemisleriGoster.setText(" Aidat ödeyen daireleri listele");
        chk_odemisleriGoster.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_odemisleriGosterActionPerformed(evt);
            }
        });

        jLabel1.setText("Blok :");

        cmb_blok.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmb_blok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_blokActionPerformed(evt);
            }
        });

        list_daireler.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        list_daireler.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                list_dairelerMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(list_daireler);

        jLabel2.setText("Ay :");

        cmb_ay.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmb_ay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_ayActionPerformed(evt);
            }
        });

        lbl_blokyazi.setText("Tarih :");

        btn_aidatEkle.setText("Tamam");
        btn_aidatEkle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_aidatEkleActionPerformed(evt);
            }
        });

        jLabel3.setText("TL");

        lbl_blokTable.setText("-");

        jLabel7.setText("Bloğundaki Dairelerin Listesi");

        btn_excel.setText("Excele Çevir");
        btn_excel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excelActionPerformed(evt);
            }
        });

        jLabel4.setText("Daire :");

        lbl_daireAdi.setText("-");

        lbl_daireAdi1.setText("Fiş No :");

        lbl_daireAdi2.setText("Makbuz No :");

        lbl_daireAdi3.setText("Aidat :");

        jLabel5.setText("Yıl :");

        cmb_yil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmb_yil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_yilActionPerformed(evt);
            }
        });

        tbl_daireler.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbl_daireler);

        btn_yeni.setText("Yeni");
        btn_yeni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_yeniActionPerformed(evt);
            }
        });

        btn_yenileDaire.setText("Yenile");
        btn_yenileDaire.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_yenileDaireActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmb_yil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmb_blok, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmb_ay, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(chk_odemisleriGoster)
                        .addGap(112, 112, 112)
                        .addComponent(lbl_blokTable, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn_yeni)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_aidatEkle))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lbl_blokyazi)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dp_odemeTarihi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_daireAdi, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(lbl_daireAdi1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_fisNo))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lbl_daireAdi3)
                                            .addComponent(lbl_daireAdi2))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txt_aidat, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_makbuzNo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
                                .addGap(55, 55, 55))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_excel)
                                .addGap(6, 6, 6)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_yenileDaire)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmb_blok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmb_ay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(cmb_yil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chk_odemisleriGoster)
                            .addComponent(jLabel7)
                            .addComponent(lbl_blokTable))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbl_blokyazi)
                                    .addComponent(dp_odemeTarihi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(lbl_daireAdi))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_fisNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbl_daireAdi1))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_makbuzNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbl_daireAdi2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_aidat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)
                                    .addComponent(lbl_daireAdi3))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btn_yeni)
                                    .addComponent(btn_aidatEkle)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                                .addGap(42, 42, 42)))
                        .addGap(58, 58, 58))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btn_yenileDaire)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 445, Short.MAX_VALUE)
                        .addComponent(btn_excel)
                        .addGap(40, 40, 40)))
                .addGap(24, 24, 24))
        );

        jTabbedPane1.addTab("Aidat", jPanel1);

        tbl_gelirGider.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tbl_gelirGider);

        btn_giderEkle.setText("Gider Ekle");
        btn_giderEkle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_giderEkleActionPerformed(evt);
            }
        });

        btn_excel2.setText("Excele Çevir");
        btn_excel2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excel2ActionPerformed(evt);
            }
        });

        btn_yenileKasa.setText("Yenile");
        btn_yenileKasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_yenileKasaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(593, Short.MAX_VALUE)
                        .addComponent(btn_excel2)
                        .addGap(46, 46, 46)
                        .addComponent(btn_giderEkle, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 803, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_yenileKasa)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_excel2)
                            .addComponent(btn_giderEkle)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btn_yenileKasa)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Kasa", jPanel2);

        jMenu1.setText("İşlemler");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Daire Ekle");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jmi_sifreDegistir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jmi_sifreDegistir.setText("Şifre Değiştir");
        jmi_sifreDegistir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmi_sifreDegistirActionPerformed(evt);
            }
        });
        jMenu1.add(jmi_sifreDegistir);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Yardım");

        jmi_hakkinda.setText("Hakkında");
        jmi_hakkinda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmi_hakkindaActionPerformed(evt);
            }
        });
        jMenu2.add(jmi_hakkinda);

        jmi_yardim.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jmi_yardim.setText("Program Yardım");
        jmi_yardim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmi_yardimActionPerformed(evt);
            }
        });
        jMenu2.add(jmi_yardim);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 933, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        daireEkle d = new daireEkle(this, true);
        d.Baglanti(emf, cmb_blok.getSelectedIndex());
        d.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void cmb_yilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_yilActionPerformed
        // TODO add your handling code here:
        Sorgu();
    }//GEN-LAST:event_cmb_yilActionPerformed

    private void cmb_blokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_blokActionPerformed
        // TODO add your handling code here:
        Sorgu();
    }//GEN-LAST:event_cmb_blokActionPerformed

    private void cmb_ayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_ayActionPerformed
        // TODO add your handling code here:
        Sorgu();
    }//GEN-LAST:event_cmb_ayActionPerformed

    private void list_dairelerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_list_dairelerMouseClicked
        // TODO add your handling code here:
        if (list_daireler.getSelectedIndex() < 0) {
            return;
        }
        String daire = lbl_blokTable.getText() + "/" + list_daireler.getSelectedValue().toString();
        lbl_daireAdi.setText(list_daireler.getSelectedValue().toString());
        for (int i = 0; i < aa.alTarih().size(); i++) {
            String dairez = aa.alDaire().get(i).toString();
            if (daire.equals(dairez) != false) {
                int id = aa.alId().get(i);
                listedenAl(id);
            }
        }

    }//GEN-LAST:event_list_dairelerMouseClicked

    private void btn_aidatEkleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_aidatEkleActionPerformed
        // TODO add your handling code here:
        if (list_daireler.getSelectedIndex() < 0) {
            JOptionPane.showMessageDialog(this, "Daire Seçin !", "Hata !", 2);
            return;
        }
        if (dp_odemeTarihi.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Tarih Seçin !", "Hata !", 2);
            return;
        }
        if (txt_aidat.getText().isEmpty() != false) {
            JOptionPane.showMessageDialog(this, "Aidat Girin !", "Hata !", 2);
            return;
        }
        if (txt_fisNo.getText().isEmpty() != false) {
            JOptionPane.showMessageDialog(this, "Fiş No Girin !", "Hata !", 2);
            return;
        }
        if (txt_makbuzNo.getText().isEmpty() != false) {
            JOptionPane.showMessageDialog(this, "Makbuz Girin !", "Hata !", 2);
            return;
        }
        aidatEkle();
        tabloyaEkle();
        GiderTablosunaEkle();
        alanlariTemizle();
    }//GEN-LAST:event_btn_aidatEkleActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        if (em.isOpen() != false) {
            em.close();
            emf.close();
        }
    }//GEN-LAST:event_formWindowClosing

    private void chk_odemisleriGosterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_odemisleriGosterActionPerformed
        // TODO add your handling code here:
        if (chk_odemisleriGoster.isSelected() != false) {
            durum = true;
            Sorgu();
        } else {
            durum = false;
            Sorgu();
        }
    }//GEN-LAST:event_chk_odemisleriGosterActionPerformed

    private void btn_giderEkleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_giderEkleActionPerformed
        // TODO add your handling code here:
        GiderPaneli gp = new GiderPaneli(this, true);
        gp.Baglanti(emf);
        gp.setVisible(true);
    }//GEN-LAST:event_btn_giderEkleActionPerformed

    private void btn_yenileDaireActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_yenileDaireActionPerformed
        // TODO add your handling code here:
        OnYukleme();
    }//GEN-LAST:event_btn_yenileDaireActionPerformed

    private void btn_yenileKasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_yenileKasaActionPerformed
        // TODO add your handling code here:
        OnYukleme();
    }//GEN-LAST:event_btn_yenileKasaActionPerformed

    private void btn_yeniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_yeniActionPerformed
        // TODO add your handling code here:
        alanlariTemizle();
    }//GEN-LAST:event_btn_yeniActionPerformed

    private void btn_excelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excelActionPerformed
        try {
            // TODO add your handling code here:
            exceleAidatYazdir();
        } catch (IOException ex) {
            Logger.getLogger(AnaPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (WriteException ex) {
            Logger.getLogger(AnaPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_excelActionPerformed

    private void btn_excel2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excel2ActionPerformed
        try {
            // TODO add your handling code here:
            exceleGelirGiderYazdir();
        } catch (IOException ex) {
            Logger.getLogger(AnaPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (WriteException ex) {
            Logger.getLogger(AnaPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_excel2ActionPerformed

    private void jmi_sifreDegistirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmi_sifreDegistirActionPerformed
        // TODO add your handling code here:
        sifreDegistir sd = new sifreDegistir(this, true);
        sd.Baglanti(emf);
        sd.setVisible(true);

    }//GEN-LAST:event_jmi_sifreDegistirActionPerformed

    private void jmi_hakkindaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmi_hakkindaActionPerformed
        // TODO add your handling code here:
        new hakkinda(this, true).setVisible(true);
    }//GEN-LAST:event_jmi_hakkindaActionPerformed

    private void jmi_yardimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmi_yardimActionPerformed
        // TODO add your handling code here:
        new Yardim(this, true).setVisible(true);
    }//GEN-LAST:event_jmi_yardimActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_aidatEkle;
    private javax.swing.JButton btn_excel;
    private javax.swing.JButton btn_excel2;
    private javax.swing.JButton btn_giderEkle;
    private javax.swing.JButton btn_yeni;
    private javax.swing.JButton btn_yenileDaire;
    private javax.swing.JButton btn_yenileKasa;
    public javax.swing.JCheckBox chk_odemisleriGoster;
    private javax.swing.JComboBox cmb_ay;
    private javax.swing.JComboBox cmb_blok;
    private javax.swing.JComboBox cmb_yil;
    private org.jdesktop.swingx.JXDatePicker dp_odemeTarihi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JMenuItem jmi_hakkinda;
    private javax.swing.JMenuItem jmi_sifreDegistir;
    private javax.swing.JMenuItem jmi_yardim;
    private javax.swing.JLabel lbl_blokTable;
    private javax.swing.JLabel lbl_blokyazi;
    private javax.swing.JLabel lbl_daireAdi;
    private javax.swing.JLabel lbl_daireAdi1;
    private javax.swing.JLabel lbl_daireAdi2;
    private javax.swing.JLabel lbl_daireAdi3;
    private javax.swing.JList list_daireler;
    private javax.swing.JTable tbl_daireler;
    private javax.swing.JTable tbl_gelirGider;
    private javax.swing.JTextField txt_aidat;
    private javax.swing.JTextField txt_fisNo;
    private javax.swing.JTextField txt_makbuzNo;
    // End of variables declaration//GEN-END:variables
}
