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
@Table(name = "AIDATLAR", catalog = "", schema = "APP")
@NamedQueries({@NamedQuery(name = "Aidatlar.findAll", query = "SELECT a FROM Aidatlar a"), @NamedQuery(name = "Aidatlar.findById", query = "SELECT a FROM Aidatlar a WHERE a.id = :id"), @NamedQuery(name = "Aidatlar.findByTarih", query = "SELECT a FROM Aidatlar a WHERE a.tarih = :tarih"), @NamedQuery(name = "Aidatlar.findByDaire", query = "SELECT a FROM Aidatlar a WHERE a.daire = :daire"), @NamedQuery(name = "Aidatlar.findByOcak", query = "SELECT a FROM Aidatlar a WHERE a.ocak = :ocak"), @NamedQuery(name = "Aidatlar.findBySubat", query = "SELECT a FROM Aidatlar a WHERE a.subat = :subat"), @NamedQuery(name = "Aidatlar.findByMart", query = "SELECT a FROM Aidatlar a WHERE a.mart = :mart"), @NamedQuery(name = "Aidatlar.findByNisan", query = "SELECT a FROM Aidatlar a WHERE a.nisan = :nisan"), @NamedQuery(name = "Aidatlar.findByMayis", query = "SELECT a FROM Aidatlar a WHERE a.mayis = :mayis"), @NamedQuery(name = "Aidatlar.findByHaziran", query = "SELECT a FROM Aidatlar a WHERE a.haziran = :haziran"), @NamedQuery(name = "Aidatlar.findByTemmuz", query = "SELECT a FROM Aidatlar a WHERE a.temmuz = :temmuz"), @NamedQuery(name = "Aidatlar.findByAgustos", query = "SELECT a FROM Aidatlar a WHERE a.agustos = :agustos"), @NamedQuery(name = "Aidatlar.findByEylul", query = "SELECT a FROM Aidatlar a WHERE a.eylul = :eylul"), @NamedQuery(name = "Aidatlar.findByEkim", query = "SELECT a FROM Aidatlar a WHERE a.ekim = :ekim"), @NamedQuery(name = "Aidatlar.findByKasim", query = "SELECT a FROM Aidatlar a WHERE a.kasim = :kasim"), @NamedQuery(name = "Aidatlar.findByAralik", query = "SELECT a FROM Aidatlar a WHERE a.aralik = :aralik")})
public class Aidatlar implements Serializable {
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
    private Integer ocak;
    @Column(name = "SUBAT")
    private Integer subat;
    @Column(name = "MART")
    private Integer mart;
    @Column(name = "NISAN")
    private Integer nisan;
    @Column(name = "MAYIS")
    private Integer mayis;
    @Column(name = "HAZIRAN")
    private Integer haziran;
    @Column(name = "TEMMUZ")
    private Integer temmuz;
    @Column(name = "AGUSTOS")
    private Integer agustos;
    @Column(name = "EYLUL")
    private Integer eylul;
    @Column(name = "EKIM")
    private Integer ekim;
    @Column(name = "KASIM")
    private Integer kasim;
    @Column(name = "ARALIK")
    private Integer aralik;

    public Aidatlar() {
    }

    public Aidatlar(Integer id) {
        this.id = id;
    }

    public Aidatlar(Integer id, String tarih, String daire) {
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

    public Integer getOcak() {
        return ocak;
    }

    public void setOcak(Integer ocak) {
        this.ocak = ocak;
    }

    public Integer getSubat() {
        return subat;
    }

    public void setSubat(Integer subat) {
        this.subat = subat;
    }

    public Integer getMart() {
        return mart;
    }

    public void setMart(Integer mart) {
        this.mart = mart;
    }

    public Integer getNisan() {
        return nisan;
    }

    public void setNisan(Integer nisan) {
        this.nisan = nisan;
    }

    public Integer getMayis() {
        return mayis;
    }

    public void setMayis(Integer mayis) {
        this.mayis = mayis;
    }

    public Integer getHaziran() {
        return haziran;
    }

    public void setHaziran(Integer haziran) {
        this.haziran = haziran;
    }

    public Integer getTemmuz() {
        return temmuz;
    }

    public void setTemmuz(Integer temmuz) {
        this.temmuz = temmuz;
    }

    public Integer getAgustos() {
        return agustos;
    }

    public void setAgustos(Integer agustos) {
        this.agustos = agustos;
    }

    public Integer getEylul() {
        return eylul;
    }

    public void setEylul(Integer eylul) {
        this.eylul = eylul;
    }

    public Integer getEkim() {
        return ekim;
    }

    public void setEkim(Integer ekim) {
        this.ekim = ekim;
    }

    public Integer getKasim() {
        return kasim;
    }

    public void setKasim(Integer kasim) {
        this.kasim = kasim;
    }

    public Integer getAralik() {
        return aralik;
    }

    public void setAralik(Integer aralik) {
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
        if (!(object instanceof Aidatlar)) {
            return false;
        }
        Aidatlar other = (Aidatlar) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pojo.Aidatlar[id=" + id + "]";
    }

}
