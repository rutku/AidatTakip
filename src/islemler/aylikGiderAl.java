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
public class aylikGiderAl {
    private EntityManager em;

    public aylikGiderAl(EntityManagerFactory emf){
        em = emf.createEntityManager();
    }

    public List alId(){
        Query s = em.createQuery("SELECT a.id FROM Aylikgider a");
        List l = s.getResultList();
        return l;
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
    public List alTarih(){
        Query s = em.createQuery("SELECT a.tarih FROM Aylikgider a");
        List l = s.getResultList();
        return l;
    }
    public List alSiraNo(){
        Query s = em.createQuery("SELECT a.sirano FROM Aylikgider a");
        List l = s.getResultList();
        return l;
    }
    public List alFaturaNo(){
        Query s = em.createQuery("SELECT a.faturano FROM Aylikgider a");
        List l = s.getResultList();
        return l;
    }
    public List alCinsi(){
        Query s = em.createQuery("SELECT a.cinsi FROM Aylikgider a");
        List l = s.getResultList();
        return l;
    }
    public List alAlinmaNedeni(){
        Query s = em.createQuery("SELECT a.alinmanedeni FROM Aylikgider a");
        List l = s.getResultList();
        return l;
    }
    public List alDurum(){
        Query s = em.createQuery("SELECT a.durum FROM Aylikgider a");
        List l = s.getResultList();
        return l;
    }
    public List alAciklama(){
        Query s = em.createQuery("SELECT a.aciklama FROM Aylikgider a");
        List l = s.getResultList();
        return l;
    }

}
