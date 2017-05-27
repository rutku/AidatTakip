/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package islemler;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import pojo.Dairesahibi;

/**
 *
 * @author racih
 */
public class daireSahibiKaydet {
    private EntityManager em;

    public daireSahibiKaydet(EntityManagerFactory emf){
        em = emf.createEntityManager();
    }

    public void DaireSahibiOlustur(Dairesahibi d){
        if (em.getTransaction().isActive() != true) {
            em.getTransaction().begin();
        }
        em.persist(d);
        em.getTransaction().commit();
    }

    public void ekleAd(int id, String isim){
        if (em.getTransaction().isActive() != true) {
            em.getTransaction().begin();
        }
        Dairesahibi d = em.find(Dairesahibi.class, id);
        if (d!=null) {
            d.setAd(isim);
        }
    }
    public void ekleSoyad(int id, String soyad){
        if (em.getTransaction().isActive() != true) {
            em.getTransaction().begin();
        }
        Dairesahibi d = em.find(Dairesahibi.class, id);
        if (d!=null) {
            d.setSoyad(soyad);
        }
    }
    public void ekleTelefon(int id, String telefon){
        if (em.getTransaction().isActive() != true) {
            em.getTransaction().begin();
        }
        Dairesahibi d = em.find(Dairesahibi.class, id);
        if (d!=null) {
            d.setTelefon(telefon);
        }
    }
    public void ekleGirisTarihi(int id, String girisTarihi){
        if (em.getTransaction().isActive() != true) {
            em.getTransaction().begin();
        }
        Dairesahibi d = em.find(Dairesahibi.class, id);
        if (d!=null) {
            d.setGiristarihi(girisTarihi);
        }
    }
    public void ekleDurum(int id, int durum){
        if (em.getTransaction().isActive() != true) {
            em.getTransaction().begin();
        }
        Dairesahibi d = em.find(Dairesahibi.class, id);
        if (d!=null) {
            d.setDurum(durum);
        }
    }

}
