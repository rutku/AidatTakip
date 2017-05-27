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
public class uyeAl {
    private EntityManager em;

    public uyeAl(EntityManagerFactory emf){
        em = emf.createEntityManager();
    }

    public List alId(){
        Query s = em.createNamedQuery("SELECT u.id FROM Uye u");
        List l = s.getResultList();
        return l;
    }
    public List alKullanici(){
        Query s = em.createNamedQuery("SELECT u.kullanici FROM Uye u");
        List l = s.getResultList();
        return l;
    }
    public List alSifre(){
        Query s = em.createNamedQuery("SELECT u.sifre FROM Uye u");
        List l = s.getResultList();
        return l;
    }

}
