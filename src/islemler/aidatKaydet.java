/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package islemler;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import pojo.Aidatbilgileri;
import pojo.Aidatlar;

/**
 *
 * @author racih
 */
public class aidatKaydet {

    private EntityManager em;
    private aidatAl aidatAl;

    public aidatKaydet(EntityManagerFactory emf) {
        em = emf.createEntityManager();
        aidatAl = new aidatAl(emf);
    }

    public void daireOlustur(Aidatlar a) {
        if (em.getTransaction().isActive() != true) {
            em.getTransaction().begin();
        }
        em.persist(a);
        em.getTransaction().commit();

    }
    public void daireSil(String daire, String tarih){
        int id = 0;
        for (int i = 0; i < aidatAl.alTarih().size(); i++) {
            String dbTarih = aidatAl.alTarih().get(i).toString();
            if (tarih.equals(dbTarih)!= false) {
                String dbDaire = aidatAl.alDaire().get(i).toString();
                if (dbDaire.equals(daire)!= false) {
                    id = aidatAl.alId().get(i);
                }
            }
        }
        if (em.getTransaction().isActive() != true) {
            em.getTransaction().begin();
        }
        List<Aidatlar> kaldir = new ArrayList<Aidatlar>(aidatAl.alTarih().size());
        Aidatlar a = em.find(Aidatlar.class, id);
        kaldir.add(a);
        Aidatlar daireKaldir = em.merge(a);
        em.remove(daireKaldir);
        List<Aidatbilgileri> akaldir = new ArrayList<Aidatbilgileri>(aidatAl.alTarih().size());
        Aidatbilgileri ab = em.find(Aidatbilgileri.class, id);
        akaldir.add(ab);
        Aidatbilgileri adaireKaldir = em.merge(ab);
        em.remove(adaireKaldir);
        em.getTransaction().commit();
    }

    public void ekleOcak(int id, int para) {
        if (em.getTransaction().isActive() != true) {
            em.getTransaction().begin();
        }
        Aidatlar a = em.find(Aidatlar.class, id);
        if (a != null) {
            a.setOcak(para);
        }
        em.merge(a);
        em.getTransaction().commit();
    }
    public void ekleSubat(int id, int para) {
        if (em.getTransaction().isActive() != true) {
            em.getTransaction().begin();
        }
        Aidatlar a = em.find(Aidatlar.class, id);
        if (a != null) {
            a.setSubat(para);
        }
        em.merge(a);
        em.getTransaction().commit();
    }
    public void ekleMart(int id, int para) {
        if (em.getTransaction().isActive() != true) {
            em.getTransaction().begin();
        }
        Aidatlar a = em.find(Aidatlar.class, id);
        if (a != null) {
            a.setMart(para);
        }
        em.merge(a);
        em.getTransaction().commit();
    }
    public void ekleNisan(int id, int para) {
        if (em.getTransaction().isActive() != true) {
            em.getTransaction().begin();
        }
        Aidatlar a = em.find(Aidatlar.class, id);
        if (a != null) {
            a.setNisan(para);
        }
        em.merge(a);
        em.getTransaction().commit();
    }
    public void ekleMayis(int id, int para) {
        if (em.getTransaction().isActive() != true) {
            em.getTransaction().begin();
        }
        Aidatlar a = em.find(Aidatlar.class, id);
        if (a != null) {
            a.setMayis(para);
        }
        em.merge(a);
        em.getTransaction().commit();
    }
    public void ekleHaziran(int id, int para) {
        if (em.getTransaction().isActive() != true) {
            em.getTransaction().begin();
        }
        Aidatlar a = em.find(Aidatlar.class, id);
        if (a != null) {
            a.setHaziran(para);
        }
        em.merge(a);
        em.getTransaction().commit();
    }
    public void ekleTemmuz(int id, int para) {
        if (em.getTransaction().isActive() != true) {
            em.getTransaction().begin();
        }
        Aidatlar a = em.find(Aidatlar.class, id);
        if (a != null) {
            a.setTemmuz(para);
        }
        em.merge(a);
        em.getTransaction().commit();
    }
    public void ekleAgustos(int id, int para) {
        if (em.getTransaction().isActive() != true) {
            em.getTransaction().begin();
        }
        Aidatlar a = em.find(Aidatlar.class, id);
        if (a != null) {
            a.setAgustos(para);
        }
        em.merge(a);
        em.getTransaction().commit();
    }
    public void ekleEylul(int id, int para) {
        if (em.getTransaction().isActive() != true) {
            em.getTransaction().begin();
        }
        Aidatlar a = em.find(Aidatlar.class, id);
        if (a != null) {
            a.setEylul(para);
        }
        em.merge(a);
        em.getTransaction().commit();
    }
    public void ekleEkim(int id, int para) {
        if (em.getTransaction().isActive() != true) {
            em.getTransaction().begin();
        }
        Aidatlar a = em.find(Aidatlar.class, id);
        if (a != null) {
            a.setEkim(para);
        }
        em.merge(a);
        em.getTransaction().commit();
    }
    public void ekleKasim(int id, int para) {
        if (em.getTransaction().isActive() != true) {
            em.getTransaction().begin();
        }
        Aidatlar a = em.find(Aidatlar.class, id);
        if (a != null) {
            a.setKasim(para);
        }
        em.merge(a);
        em.getTransaction().commit();
    }
    public void ekleAralik(int id, int para) {
        if (em.getTransaction().isActive() != true) {
            em.getTransaction().begin();
        }
        Aidatlar a = em.find(Aidatlar.class, id);
        if (a != null) {
            a.setAralik(para);
        }
        em.merge(a);
        em.getTransaction().commit();
    }
}
