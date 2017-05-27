/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package islemler;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import pojo.Aidatbilgileri;


/**
 *
 * @author racih
 */
public class aidatBilgisiKaydet {
    private EntityManager em;

    public aidatBilgisiKaydet(EntityManagerFactory emf){
        em = emf.createEntityManager();
    }

    public void aidatBilgisiOlustur(Aidatbilgileri a){
        if (em.getTransaction().isActive() != true) {
            em.getTransaction().begin();
        }
        em.persist(a);
        em.getTransaction().commit();
    }

    public void ekleOcak(String yilFisFat, int id){
        if (em.getTransaction().isActive() != false) {
            em.getTransaction().begin();
        }
        Aidatbilgileri a = em.find(Aidatbilgileri.class, id);
        if (a!= null) {
            a.setOcak(yilFisFat);
        }
        em.merge(a);
        em.getTransaction().commit();
    }
    public void ekleSubat(String yilFisFat, int id){
        if (em.getTransaction().isActive() != false) {
            em.getTransaction().begin();
        }
        Aidatbilgileri a = em.find(Aidatbilgileri.class, id);
        if (a!= null) {
            a.setSubat(yilFisFat);
        }
        em.merge(a);
        em.getTransaction().commit();
    }
    public void ekleMart(String yilFisFat, int id){
        if (em.getTransaction().isActive() != false) {
            em.getTransaction().begin();
        }
        Aidatbilgileri a = em.find(Aidatbilgileri.class, id);
        if (a!= null) {
            a.setMart(yilFisFat);
        }
        em.merge(a);
        em.getTransaction().commit();
    }
    public void ekleNisan(String yilFisFat, int id){
        if (em.getTransaction().isActive() != false) {
            em.getTransaction().begin();
        }
        Aidatbilgileri a = em.find(Aidatbilgileri.class, id);
        if (a!= null) {
            a.setNisan(yilFisFat);
        }
        em.merge(a);
        em.getTransaction().commit();
    }
    public void ekleMayis(String yilFisFat, int id){
        if (em.getTransaction().isActive() != false) {
            em.getTransaction().begin();
        }
        Aidatbilgileri a = em.find(Aidatbilgileri.class, id);
        if (a!= null) {
            a.setMayis(yilFisFat);
        }
        em.merge(a);
        em.getTransaction().commit();
    }
    public void ekleHaziran(String yilFisFat, int id){
        if (em.getTransaction().isActive() != false) {
            em.getTransaction().begin();
        }
        Aidatbilgileri a = em.find(Aidatbilgileri.class, id);
        if (a!= null) {
            a.setHaziran(yilFisFat);
        }
        em.merge(a);
        em.getTransaction().commit();
    }
    public void ekleTemmuz(String yilFisFat, int id){
        if (em.getTransaction().isActive() != false) {
            em.getTransaction().begin();
        }
        Aidatbilgileri a = em.find(Aidatbilgileri.class, id);
        if (a!= null) {
            a.setTemmuz(yilFisFat);
        }
        em.merge(a);
        em.getTransaction().commit();
    }
    public void ekleAgustos(String yilFisFat, int id){
        if (em.getTransaction().isActive() != false) {
            em.getTransaction().begin();
        }
        Aidatbilgileri a = em.find(Aidatbilgileri.class, id);
        if (a!= null) {
            a.setAgustos(yilFisFat);
        }
        em.merge(a);
        em.getTransaction().commit();
    }
    public void ekleEylul(String yilFisFat, int id){
        if (em.getTransaction().isActive() != false) {
            em.getTransaction().begin();
        }
        Aidatbilgileri a = em.find(Aidatbilgileri.class, id);
        if (a!= null) {
            a.setEylul(yilFisFat);
        }
        em.merge(a);
        em.getTransaction().commit();
    }
    public void ekleEkim(String yilFisFat, int id){
        if (em.getTransaction().isActive() != false) {
            em.getTransaction().begin();
        }
        Aidatbilgileri a = em.find(Aidatbilgileri.class, id);
        if (a!= null) {
            a.setEkim(yilFisFat);
        }
        em.merge(a);
        em.getTransaction().commit();
    }
    public void ekleKasim(String yilFisFat, int id){
        if (em.getTransaction().isActive() != false) {
            em.getTransaction().begin();
        }
        Aidatbilgileri a = em.find(Aidatbilgileri.class, id);
        if (a!= null) {
            a.setKasim(yilFisFat);
        }
        em.merge(a);
        em.getTransaction().commit();
    }
    public void ekleAralik(String yilFisFat, int id){
        if (em.getTransaction().isActive() != false) {
            em.getTransaction().begin();
        }
        Aidatbilgileri a = em.find(Aidatbilgileri.class, id);
        if (a!= null) {
            a.setAralik(yilFisFat);
        }
        em.merge(a);
        em.getTransaction().commit();
    }

}
