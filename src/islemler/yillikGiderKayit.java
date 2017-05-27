/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package islemler;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import pojo.Yillikgider;

/**
 *
 * @author racih
 */
public class yillikGiderKayit {
    private EntityManager em;

    public yillikGiderKayit(EntityManagerFactory emf){
        em = emf.createEntityManager();
    }

    public void yillikGiderOlustur(Yillikgider y){
        if (em.getTransaction().isActive() != true) {
            em.getTransaction().begin();
        }
        em.persist(y);
        em.getTransaction().commit();
    }

    public void ekleTarih(int id, String tarih){
        if (em.getTransaction().isActive() != true) {
            em.getTransaction().begin();
        }
        Yillikgider y = em.find(Yillikgider.class, id);
        if (y != null) {
            y.setTarih(tarih);
        }
        em.getTransaction().commit();
    }
    public void ekleOcak(int id, int ocak){
        if (em.getTransaction().isActive() != true) {
            em.getTransaction().begin();
        }
        Yillikgider y = em.find(Yillikgider.class, id);
        if (y != null) {
            y.setOcak(ocak);
        }
        em.getTransaction().commit();
    }
    public void ekleSubat(int id, int subat){
        if (em.getTransaction().isActive() != true) {
            em.getTransaction().begin();
        }
        Yillikgider y = em.find(Yillikgider.class, id);
        if (y != null) {
            y.setSubat(subat);
        }
        em.getTransaction().commit();
    }
    public void ekleMart(int id, int mart){
        if (em.getTransaction().isActive() != true) {
            em.getTransaction().begin();
        }
        Yillikgider y = em.find(Yillikgider.class, id);
        if (y != null) {
            y.setMart(mart);
        }
        em.getTransaction().commit();
    }
    public void ekleNisan(int id, int nisan){
        if (em.getTransaction().isActive() != true) {
            em.getTransaction().begin();
        }
        Yillikgider y = em.find(Yillikgider.class, id);
        if (y != null) {
            y.setNisan(nisan);
        }
        em.getTransaction().commit();
    }
    public void ekleMayis(int id, int mayis){
        if (em.getTransaction().isActive() != true) {
            em.getTransaction().begin();
        }
        Yillikgider y = em.find(Yillikgider.class, id);
        if (y != null) {
            y.setMayis(mayis);
        }
        em.getTransaction().commit();
    }
    public void ekleHaziran(int id, int haziran){
        if (em.getTransaction().isActive() != true) {
            em.getTransaction().begin();
        }
        Yillikgider y = em.find(Yillikgider.class, id);
        if (y != null) {
            y.setHaziran(haziran);
        }
        em.getTransaction().commit();
    }
    public void ekleTemmuz(int id, int temmuz){
        if (em.getTransaction().isActive() != true) {
            em.getTransaction().begin();
        }
        Yillikgider y = em.find(Yillikgider.class, id);
        if (y != null) {
            y.setTemmuz(temmuz);
        }
        em.getTransaction().commit();
    }
    public void ekleAgustos(int id, int agustos){
        if (em.getTransaction().isActive() != true) {
            em.getTransaction().begin();
        }
        Yillikgider y = em.find(Yillikgider.class, id);
        if (y != null) {
            y.setAgustos(agustos);
        }
        em.getTransaction().commit();
    }
    public void ekleEylul(int id, int eylul){
        if (em.getTransaction().isActive() != true) {
            em.getTransaction().begin();
        }
        Yillikgider y = em.find(Yillikgider.class, id);
        if (y != null) {
            y.setEylul(eylul);
        }
        em.getTransaction().commit();
    }

}
