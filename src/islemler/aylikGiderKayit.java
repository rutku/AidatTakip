/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package islemler;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import pojo.Aylikgider;

/**
 *
 * @author racih
 */
public class aylikGiderKayit {
    private EntityManager em;

    public aylikGiderKayit(EntityManagerFactory emf){
        em = emf.createEntityManager();
    }

    public void aylikGiderOlustur(Aylikgider a){
        if (em.getTransaction().isActive() != true) {
            em.getTransaction().begin();
        }
        em.merge(a);
        em.getTransaction().commit();
    }

    public void ekleTarih(int id, String tarih){
        if (em.getTransaction().isActive() != true) {
            em.getTransaction().begin();
        }
        Aylikgider a = em.find(Aylikgider.class, id);
        if (a!= null) {
            a.setTarih(tarih);
        }
        em.getTransaction().commit();
    }
    public void ekleSiraNo(int id, int siraNo){
        if (em.getTransaction().isActive() != true) {
            em.getTransaction().begin();
        }
        Aylikgider a = em.find(Aylikgider.class, id);
        if (a!= null) {
            a.setSirano(siraNo);
        }
        em.getTransaction().commit();
    }
    public void ekleFaturaNo(int id, String FaturaNo){
        if (em.getTransaction().isActive() != true) {
            em.getTransaction().begin();
        }
        Aylikgider a = em.find(Aylikgider.class, id);
        if (a!= null) {
            a.setFaturano(FaturaNo);
        }
        em.getTransaction().commit();
    }
    public void ekleCinsi(int id, String cinsi){
        if (em.getTransaction().isActive() != true) {
            em.getTransaction().begin();
        }
        Aylikgider a = em.find(Aylikgider.class, id);
        if (a!= null) {
            a.setCinsi(cinsi);
        }
        em.getTransaction().commit();
    }
    public void ekleAlinmaNedeni(int id, String nedeni){
        if (em.getTransaction().isActive() != true) {
            em.getTransaction().begin();
        }
        Aylikgider a = em.find(Aylikgider.class, id);
        if (a!= null) {
            a.setAlinmanedeni(nedeni);
        }
        em.getTransaction().commit();
    }
    public void ekleDurum(int id, int durum){
        if (em.getTransaction().isActive() != true) {
            em.getTransaction().begin();
        }
        Aylikgider a = em.find(Aylikgider.class, id);
        if (a!= null) {
            a.setDurum(durum);
        }
        em.getTransaction().commit();
    }
    public void ekleAciklama(int id, String aciklama){
        if (em.getTransaction().isActive() != true) {
            em.getTransaction().begin();
        }
        Aylikgider a = em.find(Aylikgider.class, id);
        if (a!= null) {
            a.setAciklama(aciklama);
        }
        em.getTransaction().commit();
    }
    public void ekleGider(int id, Integer gider){
        if (em.getTransaction().isActive() != true) {
            em.getTransaction().begin();
        }
        Aylikgider a = em.find(Aylikgider.class, id);
        if (a!= null) {
            a.setGider(gider);
        }
        em.getTransaction().commit();
    }
    
}
