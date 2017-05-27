/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package islemler;

import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author racih
 */
public class daireSahibiAl {
    private EntityManager em;

    public daireSahibiAl(EntityManagerFactory emf){
        em = emf.createEntityManager();
    }

    public int YerBul() {
        int cikanSayi = 0;
        List idList = alId();
        int[] idDizisi = new int[alId().size()];
        for (int i = 0; i < idList.size(); i++) {
            idDizisi[i] = Integer.parseInt(idList.get(i).toString());
        }
        for (int i = 0; i < 10000; i++) {
            int sonuc = Arrays.binarySearch(idDizisi, i);
            if (sonuc < 0) {
                if (0 != i) {
                    cikanSayi = i;
                    break;
                }
            }
        }
        return cikanSayi;

    }

    public List alId(){
        Query sorgu = em.createQuery("SELECT d.id FROM Dairesahibi d");
        List l = sorgu.getResultList();
        return l;
    }
    public List alIsim(){
        Query sorgu = em.createQuery("SELECT d.ad FROM Dairesahibi d");
        List l = sorgu.getResultList();
        return l;
    }
    public List alSoyad(){
        Query sorgu = em.createQuery("SELECT d.soyad FROM Dairesahibi d");
        List l = sorgu.getResultList();
        return l;
    }
    public List alDaire(){
        Query sorgu = em.createQuery("SELECT d.daire FROM Dairesahibi d");
        List l = sorgu.getResultList();
        return l;
    }
    public List alTarih(){
        Query sorgu = em.createQuery("SELECT d.tarih FROM Dairesahibi d");
        List l = sorgu.getResultList();
        return l;
    }
    public List alGirisTarihi(){
        Query sorgu = em.createQuery("SELECT d.giristarihi FROM Dairesahibi d");
        List l = sorgu.getResultList();
        return l;
    }
    public List alTelefon(){
        Query sorgu = em.createQuery("SELECT d.telefon FROM Dairesahibi d");
        List l = sorgu.getResultList();
        return l;
    }
    public List alDurum(){
        Query sorgu = em.createQuery("SELECT d.durum FROM Dairesahibi d");
        List l = sorgu.getResultList();
        return l;
    }

}
