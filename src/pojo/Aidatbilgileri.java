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
@Table(name = "AIDATBILGILERI", catalog = "", schema = "APP")
@NamedQueries({@NamedQuery(name = "Aidatbilgileri.findAll", query = "SELECT a FROM Aidatbilgileri a"), @NamedQuery(name = "Aidatbilgileri.findById", query = "SELECT a FROM Aidatbilgileri a WHERE a.id = :id"), @NamedQuery(name = "Aidatbilgileri.findByTarih", query = "SELECT a FROM Aidatbilgileri a WHERE a.tarih = :tarih"), @NamedQuery(name = "Aidatbilgileri.findByDaire", query = "SELECT a FROM Aidatbilgileri a WHERE a.daire = :daire"), @NamedQuery(name = "Aidatbilgileri.findByOcak", query = "SELECT a FROM Aidatbilgileri a WHERE a.ocak = :ocak"), @NamedQuery(name = "Aidatbilgileri.findBySubat", query = "SELECT a FROM Aidatbilgileri a WHERE a.subat = :subat"), @NamedQuery(name = "Aidatbilgileri.findByMart", query = "SELECT a FROM Aidatbilgileri a WHERE a.mart = :mart"), @NamedQuery(name = "Aidatbilgileri.findByNisan", query = "SELECT a FROM Aidatbilgileri a WHERE a.nisan = :nisan"), @NamedQuery(name = "Aidatbilgileri.findByMayis", query = "SELECT a FROM Aidatbilgileri a WHERE a.mayis = :mayis"), @NamedQuery(name = "Aidatbilgileri.findByHaziran", query = "SELECT a FROM Aidatbilgileri a WHERE a.haziran = :haziran"), @NamedQuery(name = "Aidatbilgileri.findByTemmuz", query = "SELECT a FROM Aidatbilgileri a WHERE a.temmuz = :temmuz"), @NamedQuery(name = "Aidatbilgileri.findByAgustos", query = "SELECT a FROM Aidatbilgileri a WHERE a.agustos = :agustos"), @NamedQuery(name = "Aidatbilgileri.findByEylul", query = "SELECT a FROM Aidatbilgileri a WHERE a.eylul = :eylul"), @NamedQuery(name = "Aidatbilgileri.findByEkim", query = "SELECT a FROM Aidatbilgileri a WHERE a.ekim = :ekim"), @NamedQuery(name = "Aidatbilgileri.findByKasim", query = "SELECT a FROM Aidatbilgileri a WHERE a.kasim = :kasim"), @NamedQuery(name = "Aidatbilgileri.findByAralik", query = "SELECT a FROM Aidatbilgileri a WHERE a.aralik = :aralik")})
public class Aidatbilgileri implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "TARIH")
    private String tarih;
    @Basic(optional = false)
    @Column(name = "DAIRE")
    private String daire;
    @Column(name = "OCAK")
    private String ocak;
    @Column(name = "SUBAT")
    private String subat;
    @Column(name = "MART")
    private String mart;
    @Column(name = "NISAN")
    private String nisan;
    @Column(name = "MAYIS")
    private String mayis;
    @Column(name = "HAZIRAN")
    private String haziran;
    @Column(name = "TEMMUZ")
    private String temmuz;
    @Column(name = "AGUSTOS")
    private String agustos;
    @Column(name = "EYLUL")
    private String eylul;
    @Column(name = "EKIM")
    private String ekim;
    @Column(name = "KASIM")
    private String kasim;
    @Column(name = "ARALIK")
    private String aralik;

    public Aidatbilgileri() {
    }

    public Aidatbilgileri(Integer id) {
        this.id = id;
    }

    public Aidatbilgileri(Integer id, String tarih, String daire) {
        this.id = id;
        this.tarih = tarih;
        this.daire = daire;
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

    public String getDaire() {
        return daire;
    }

    public void setDaire(String daire) {
        this.daire = daire;
    }

    public String getOcak() {
        return ocak;
    }

    public void setOcak(String ocak) {
        this.ocak = ocak;
    }

    public String getSubat() {
        return subat;
    }

    public void setSubat(String subat) {
        this.subat = subat;
    }

    public String getMart() {
        return mart;
    }

    public void setMart(String mart) {
        this.mart = mart;
    }

    public String getNisan() {
        return nisan;
    }

    public void setNisan(String nisan) {
        this.nisan = nisan;
    }

    public String getMayis() {
        return mayis;
    }

    public void setMayis(String mayis) {
        this.mayis = mayis;
    }

    public String getHaziran() {
        return haziran;
    }

    public void setHaziran(String haziran) {
        this.haziran = haziran;
    }

    public String getTemmuz() {
        return temmuz;
    }

    public void setTemmuz(String temmuz) {
        this.temmuz = temmuz;
    }

    public String getAgustos() {
        return agustos;
    }

    public void setAgustos(String agustos) {
        this.agustos = agustos;
    }

    public String getEylul() {
        return eylul;
    }

    public void setEylul(String eylul) {
        this.eylul = eylul;
    }

    public String getEkim() {
        return ekim;
    }

    public void setEkim(String ekim) {
        this.ekim = ekim;
    }

    public String getKasim() {
        return kasim;
    }

    public void setKasim(String kasim) {
        this.kasim = kasim;
    }

    public String getAralik() {
        return aralik;
    }

    public void setAralik(String aralik) {
        this.aralik = aralik;
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
        if (!(object instanceof Aidatbilgileri)) {
            return false;
        }
        Aidatbilgileri other = (Aidatbilgileri) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pojo.Aidatbilgileri[id=" + id + "]";
    }

}
