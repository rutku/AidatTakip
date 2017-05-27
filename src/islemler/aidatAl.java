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
public class aidatAl {
    private EntityManager em;

    public aidatAl(EntityManagerFactory emf){
        em = emf.createEntityManager();
    }

    public List alTarih(){
        Query sorgu = em.createQuery("SELECT a.tarih FROM Aidatlar a");
        List liste = sorgu.getResultList();
        return liste;
    }
    public List<Integer> alId(){
        Query sorgu = em.createQuery("SELECT a.id FROM Aidatlar a");
        List liste = sorgu.getResultList();
        return liste;
    }
    public List alDaire(){
        Query sorgu = em.createQuery("SELECT a.daire FROM Aidatlar a");
        List liste = sorgu.getResultList();
        return liste;
    }
    public List alOcak(){
        Query sorgu = em.createQuery("SELECT a.ocak FROM Aidatlar a");
        List liste = sorgu.getResultList();
        return liste;
    }
    public List alSubat(){
        Query sorgu = em.createQuery("SELECT a.subat FROM Aidatlar a");
        List liste = sorgu.getResultList();
        return liste;
    }
    public List alMart(){
        Query sorgu = em.createQuery("SELECT a.mart FROM Aidatlar a");
        List liste = sorgu.getResultList();
        return liste;
    }
    public List alNisan(){
        Query sorgu = em.createQuery("SELECT a.nisan FROM Aidatlar a");
        List liste = sorgu.getResultList();
        return liste;
    }
    public List alMayis(){
        Query sorgu = em.createQuery("SELECT a.mayis FROM Aidatlar a");
        List liste = sorgu.getResultList();
        return liste;
    }
    public List alHaziran(){
        Query sorgu = em.createQuery("SELECT a.haziran FROM Aidatlar a");
        List liste = sorgu.getResultList();
        return liste;
    }
    public List alTemmuz(){
        Query sorgu = em.createQuery("SELECT a.temmuz FROM Aidatlar a");
        List liste = sorgu.getResultList();
        return liste;
    }
    public List alAgustos(){
        Query sorgu = em.createQuery("SELECT a.agustos FROM Aidatlar a");
        List liste = sorgu.getResultList();
        return liste;
    }
    public List alEylul(){
        Query sorgu = em.createQuery("SELECT a.eylul FROM Aidatlar a");
        List liste = sorgu.getResultList();
        return liste;
    }
    public List alEkim(){
        Query sorgu = em.createQuery("SELECT a.ekim FROM Aidatlar a");
        List liste = sorgu.getResultList();
        return liste;
    }
    public List alKasim(){
        Query sorgu = em.createQuery("SELECT a.kasim FROM Aidatlar a");
        List liste = sorgu.getResultList();
        return liste;
    }
    public List alAralik(){
        Query sorgu = em.createQuery("SELECT a.aralik FROM Aidatlar a");
        List liste = sorgu.getResultList();
        return liste;
    }
    public int siraBul() {
        int[] idDizisi = new int[alTarih().size()];
        int b = 0;
        Query idAl = em.createQuery("SELECT a.id FROM Aidatlar a");
        List idList = idAl.getResultList();
        for (int i = 0; i < idList.size(); i++) {
            idDizisi[i] = Integer.parseInt(idList.get(i).toString());
        }
        for (int i = 0; i < 10000; i++) {
            int sonuc = Arrays.binarySearch(idDizisi, i);
            if (sonuc < 0) {
                if (0 != i) {
                    b = i;
                    break;
                }

            }
        }
        return b;
    }

}
