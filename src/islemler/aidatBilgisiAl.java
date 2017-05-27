/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package islemler;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author racih
 */
public class aidatBilgisiAl {
    private EntityManager em;

    public aidatBilgisiAl(EntityManagerFactory emf){
        em = emf.createEntityManager();
    }

    public List AlTarih(){
        Query sorgu = em.createQuery("SELECT a.tarih FROM Aidatbilgileri a");
        List liste = sorgu.getResultList();
        return liste;
    }
    public List AlDaire(){
        Query sorgu = em.createQuery("SELECT a.daire FROM Aidatbilgileri a");
        List liste = sorgu.getResultList();
        return liste;
    }
    public List AlOcak(){
        Query sorgu = em.createQuery("SELECT a.ocak FROM Aidatbilgileri a");
        List liste = sorgu.getResultList();
        return liste;
    }
    public List AlSubat(){
        Query sorgu = em.createQuery("SELECT a.subat FROM Aidatbilgileri a");
        List liste = sorgu.getResultList();
        return liste;
    }
    public List AlMart(){
        Query sorgu = em.createQuery("SELECT a.mart FROM Aidatbilgileri a");
        List liste = sorgu.getResultList();
        return liste;
    }
    public List AlNisan(){
        Query sorgu = em.createQuery("SELECT a.nisan FROM Aidatbilgileri a");
        List liste = sorgu.getResultList();
        return liste;
    }
    public List AlTMayis(){
        Query sorgu = em.createQuery("SELECT a.mayis FROM Aidatbilgileri a");
        List liste = sorgu.getResultList();
        return liste;
    }
    public List AlHaziran(){
        Query sorgu = em.createQuery("SELECT a.haziran FROM Aidatbilgileri a");
        List liste = sorgu.getResultList();
        return liste;
    }
    public List AlTemmuz(){
        Query sorgu = em.createQuery("SELECT a.temmuz FROM Aidatbilgileri a");
        List liste = sorgu.getResultList();
        return liste;
    }
    public List AlAgustos(){
        Query sorgu = em.createQuery("SELECT a.agustos FROM Aidatbilgileri a");
        List liste = sorgu.getResultList();
        return liste;
    }
    public List AlEylul(){
        Query sorgu = em.createQuery("SELECT a.eylul FROM Aidatbilgileri a");
        List liste = sorgu.getResultList();
        return liste;
    }
    public List AlEkim(){
        Query sorgu = em.createQuery("SELECT a.ekim FROM Aidatbilgileri a");
        List liste = sorgu.getResultList();
        return liste;
    }
    public List AlKasim(){
        Query sorgu = em.createQuery("SELECT a.kasim FROM Aidatbilgileri a");
        List liste = sorgu.getResultList();
        return liste;
    }
    public List AlAralik(){
        Query sorgu = em.createQuery("SELECT a.aralik FROM Aidatbilgileri a");
        List liste = sorgu.getResultList();
        return liste;
    }

}
