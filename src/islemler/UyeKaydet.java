/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package islemler;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import pojo.Uye;

/**
 *
 * @author racih
 */
public class UyeKaydet {
    private EntityManager em;

    public UyeKaydet(EntityManagerFactory emf){
        em = emf.createEntityManager();
    }

    public void uyeOlustur(Uye u){
        if (em.getTransaction().isActive() != true) {
            em.getTransaction().begin();
        }
        em.persist(u);
        em.getTransaction().commit();
    }
    public void ekleKullanici(int id, String kullanici){
        if (em.getTransaction().isActive() != true) {
            em.getTransaction().begin();
        }
        Uye u = em.find(Uye.class, id);
        if (u !=null) {
            u.setKullanici(kullanici);
        }
    }
    public void ekleSifre(int id, String sifre){
        if (em.getTransaction().isActive() != true) {
            em.getTransaction().begin();
        }
        Uye u = em.find(Uye.class, id);
        if (u !=null) {
            u.setSifre(sifre);
        }
    }

}
