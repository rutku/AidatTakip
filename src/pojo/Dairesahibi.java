/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pojo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author racih
 */
@Entity
@Table(name = "DAIRESAHIBI", catalog = "", schema = "APP")
@NamedQueries({@NamedQuery(name = "Dairesahibi.findAll", query = "SELECT d FROM Dairesahibi d"), @NamedQuery(name = "Dairesahibi.findByTarih", query = "SELECT d FROM Dairesahibi d WHERE d.tarih = :tarih"), @NamedQuery(name = "Dairesahibi.findByDaire", query = "SELECT d FROM Dairesahibi d WHERE d.daire = :daire"), @NamedQuery(name = "Dairesahibi.findById", query = "SELECT d FROM Dairesahibi d WHERE d.id = :id"), @NamedQuery(name = "Dairesahibi.findByAd", query = "SELECT d FROM Dairesahibi d WHERE d.ad = :ad"), @NamedQuery(name = "Dairesahibi.findBySoyad", query = "SELECT d FROM Dairesahibi d WHERE d.soyad = :soyad"), @NamedQuery(name = "Dairesahibi.findByTelefon", query = "SELECT d FROM Dairesahibi d WHERE d.telefon = :telefon"), @NamedQuery(name = "Dairesahibi.findByGiristarihi", query = "SELECT d FROM Dairesahibi d WHERE d.giristarihi = :giristarihi"), @NamedQuery(name = "Dairesahibi.findByDurum", query = "SELECT d FROM Dairesahibi d WHERE d.durum = :durum")})
public class Dairesahibi implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "TARIH")
    private String tarih;
    @Basic(optional = false)
    @Column(name = "DAIRE")
    private String daire;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "AD")
    private String ad;
    @Column(name = "SOYAD")
    private String soyad;
    @Column(name = "TELEFON")
    private String telefon;
    @Column(name = "GIRISTARIHI")
    private String giristarihi;
    @Column(name = "DURUM")
    private Integer durum;

    public Dairesahibi() {
    }

    public Dairesahibi(Integer id) {
        this.id = id;
    }

    public Dairesahibi(Integer id, String tarih, String daire) {
        this.id = id;
        this.tarih = tarih;
        this.daire = daire;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getDaire() {
        return daire;
    }

    public void setDaire(String daire) {
        this.daire = daire;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getGiristarihi() {
        return giristarihi;
    }

    public void setGiristarihi(String giristarihi) {
        this.giristarihi = giristarihi;
    }

    public Integer getDurum() {
        return durum;
    }

    public void setDurum(Integer durum) {
        this.durum = durum;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dairesahibi)) {
            return false;
        }
        Dairesahibi other = (Dairesahibi) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pojo.Dairesahibi[id=" + id + "]";
    }

}
