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
 * @author Racih
 */
@Entity
@Table(name = "AYLIKGIDER", catalog = "", schema = "APP")
@NamedQueries({@NamedQuery(name = "Aylikgider.findAll", query = "SELECT a FROM Aylikgider a"), @NamedQuery(name = "Aylikgider.findById", query = "SELECT a FROM Aylikgider a WHERE a.id = :id"), @NamedQuery(name = "Aylikgider.findByTarih", query = "SELECT a FROM Aylikgider a WHERE a.tarih = :tarih"), @NamedQuery(name = "Aylikgider.findBySirano", query = "SELECT a FROM Aylikgider a WHERE a.sirano = :sirano"), @NamedQuery(name = "Aylikgider.findByFaturano", query = "SELECT a FROM Aylikgider a WHERE a.faturano = :faturano"), @NamedQuery(name = "Aylikgider.findByCinsi", query = "SELECT a FROM Aylikgider a WHERE a.cinsi = :cinsi"), @NamedQuery(name = "Aylikgider.findByAlinmanedeni", query = "SELECT a FROM Aylikgider a WHERE a.alinmanedeni = :alinmanedeni"), @NamedQuery(name = "Aylikgider.findByDurum", query = "SELECT a FROM Aylikgider a WHERE a.durum = :durum"), @NamedQuery(name = "Aylikgider.findByAciklama", query = "SELECT a FROM Aylikgider a WHERE a.aciklama = :aciklama"), @NamedQuery(name = "Aylikgider.findByGider", query = "SELECT a FROM Aylikgider a WHERE a.gider = :gider")})
public class Aylikgider implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "TARIH")
    private String tarih;
    @Basic(optional = false)
    @Column(name = "SIRANO")
    private int sirano;
    @Basic(optional = false)
    @Column(name = "FATURANO")
    private String faturano;
    @Basic(optional = false)
    @Column(name = "CINSI")
    private String cinsi;
    @Basic(optional = false)
    @Column(name = "ALINMANEDENI")
    private String alinmanedeni;
    @Basic(optional = false)
    @Column(name = "DURUM")
    private int durum;
    @Basic(optional = false)
    @Column(name = "ACIKLAMA")
    private String aciklama;
    @Column(name = "GIDER")
    private Integer gider;

    public Aylikgider() {
    }

    public Aylikgider(Integer id) {
        this.id = id;
    }

    public Aylikgider(Integer id, String tarih, int sirano, String faturano, String cinsi, String alinmanedeni, int durum, String aciklama) {
        this.id = id;
        this.tarih = tarih;
        this.sirano = sirano;
        this.faturano = faturano;
        this.cinsi = cinsi;
        this.alinmanedeni = alinmanedeni;
        this.durum = durum;
        this.aciklama = aciklama;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public int getSirano() {
        return sirano;
    }

    public void setSirano(int sirano) {
        this.sirano = sirano;
    }

    public String getFaturano() {
        return faturano;
    }

    public void setFaturano(String faturano) {
        this.faturano = faturano;
    }

    public String getCinsi() {
        return cinsi;
    }

    public void setCinsi(String cinsi) {
        this.cinsi = cinsi;
    }

    public String getAlinmanedeni() {
        return alinmanedeni;
    }

    public void setAlinmanedeni(String alinmanedeni) {
        this.alinmanedeni = alinmanedeni;
    }

    public int getDurum() {
        return durum;
    }

    public void setDurum(int durum) {
        this.durum = durum;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public Integer getGider() {
        return gider;
    }

    public void setGider(Integer gider) {
        this.gider = gider;
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
        if (!(object instanceof Aylikgider)) {
            return false;
        }
        Aylikgider other = (Aylikgider) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pojo.Aylikgider[id=" + id + "]";
    }

}
