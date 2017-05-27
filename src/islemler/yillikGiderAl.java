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
public class yillikGiderAl {

    private EntityManager em;

    public yillikGiderAl(EntityManagerFactory emf) {
        em = emf.createEntityManager();
    }

    public List alId() {
        Query s = em.createNamedQuery("SELECT y.id FROM Yillikgider y");
        List l = s.getResultList();
        return l;
    }

    public List alTarih() {
        Query s = em.createNamedQuery("SELECT y.tarih FROM Yillikgider y");
        List l = s.getResultList();
        return l;
    }

    public List alOcak() {
        Query s = em.createNamedQuery("SELECT y.ocak FROM Yillikgider y");
        List l = s.getResultList();
        return l;
    }

    public List alSubat() {
        Query s = em.createNamedQuery("SELECT y.subat FROM Yillikgider y");
        List l = s.getResultList();
        return l;
    }

    public List alMart() {
        Query s = em.createNamedQuery("SELECT y.mart FROM Yillikgider y");
        List l = s.getResultList();
        return l;
    }

    public List alNisan() {
        Query s = em.createNamedQuery("SELECT y.nisan FROM Yillikgider y");
        List l = s.getResultList();
        return l;
    }

    public List alMayis() {
        Query s = em.createNamedQuery("SELECT y.mayis FROM Yillikgider y");
        List l = s.getResultList();
        return l;
    }

    public List alHaziran() {
        Query s = em.createNamedQuery("SELECT y.haziran FROM Yillikgider y");
        List l = s.getResultList();
        return l;
    }

    public List alTemmuz() {
        Query s = em.createNamedQuery("SELECT y.temmuz FROM Yillikgider y");
        List l = s.getResultList();
        return l;
    }

    public List alAgustos() {
        Query s = em.createNamedQuery("SELECT y.agustos FROM Yillikgider y");
        List l = s.getResultList();
        return l;
    }

    public List alEylul() {
        Query s = em.createNamedQuery("SELECT y.eylul FROM Yillikgider y");
        List l = s.getResultList();
        return l;
    }

    public List alEkim() {
        Query s = em.createNamedQuery("SELECT y.ekim FROM Yillikgider y");
        List l = s.getResultList();
        return l;
    }

    public List alKasim() {
        Query s = em.createNamedQuery("SELECT y.kasim FROM Yillikgider y");
        List l = s.getResultList();
        return l;
    }

    public List alAralik() {
        Query s = em.createNamedQuery("SELECT y.aralik FROM Yillikgider y");
        List l = s.getResultList();
        return l;
    }
}
