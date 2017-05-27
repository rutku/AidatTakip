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
@Table(name = "YILLIKGIDER", catalog = "", schema = "APP")
@NamedQueries({@NamedQuery(name = "Yillikgider.findAll", query = "SELECT y FROM Yillikgider y"), @NamedQuery(name = "Yillikgider.findById", query = "SELECT y FROM Yillikgider y WHERE y.id = :id"), @NamedQuery(name = "Yillikgider.findByTarih", query = "SELECT y FROM Yillikgider y WHERE y.tarih = :tarih"), @NamedQuery(name = "Yillikgider.findByOcak", query = "SELECT y FROM Yillikgider y WHERE y.ocak = :ocak"), @NamedQuery(name = "Yillikgider.findBySubat", query = "SELECT y FROM Yillikgider y WHERE y.subat = :subat"), @NamedQuery(name = "Yillikgider.findByMart", query = "SELECT y FROM Yillikgider y WHERE y.mart = :mart"), @NamedQuery(name = "Yillikgider.findByNisan", query = "SELECT y FROM Yillikgider y WHERE y.nisan = :nisan"), @NamedQuery(name = "Yillikgider.findByMayis", query = "SELECT y FROM Yillikgider y WHERE y.mayis = :mayis"), @NamedQuery(name = "Yillikgider.findByHaziran", query = "SELECT y FROM Yillikgider y WHERE y.haziran = :haziran"), @NamedQuery(name = "Yillikgider.findByTemmuz", query = "SELECT y FROM Yillikgider y WHERE y.temmuz = :temmuz"), @NamedQuery(name = "Yillikgider.findByAgustos", query = "SELECT y FROM Yillikgider y WHERE y.agustos = :agustos"), @NamedQuery(name = "Yillikgider.findByEylul", query = "SELECT y FROM Yillikgider y WHERE y.eylul = :eylul"), @NamedQuery(name = "Yillikgider.findByEkim", query = "SELECT y FROM Yillikgider y WHERE y.ekim = :ekim"), @NamedQuery(name = "Yillikgider.findByKasim", query = "SELECT y FROM Yillikgider y WHERE y.kasim = :kasim"), @NamedQuery(name = "Yillikgider.findByAralik", query = "SELECT y FROM Yillikgider y WHERE y.aralik = :aralik")})
public class Yillikgider implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "TARIH")
    private String tarih;
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

    public Yillikgider() {
    }

    public Yillikgider(Integer id) {
        this.id = id;
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
        if (!(object instanceof Yillikgider)) {
            return false;
        }
        Yillikgider other = (Yillikgider) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pojo.Yillikgider[id=" + id + "]";
    }

}
