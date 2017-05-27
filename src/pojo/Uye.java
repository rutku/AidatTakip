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
@Table(name = "UYE", catalog = "", schema = "APP")
@NamedQueries({@NamedQuery(name = "Uye.findAll", query = "SELECT u FROM Uye u"), @NamedQuery(name = "Uye.findById", query = "SELECT u FROM Uye u WHERE u.id = :id"), @NamedQuery(name = "Uye.findByKullanici", query = "SELECT u FROM Uye u WHERE u.kullanici = :kullanici"), @NamedQuery(name = "Uye.findBySifre", query = "SELECT u FROM Uye u WHERE u.sifre = :sifre")})
public class Uye implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "KULLANICI")
    private String kullanici;
    @Basic(optional = false)
    @Column(name = "SIFRE")
    private String sifre;

    public Uye() {
    }

    public Uye(Integer id) {
        this.id = id;
    }

    public Uye(Integer id, String kullanici, String sifre) {
        this.id = id;
        this.kullanici = kullanici;
        this.sifre = sifre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKullanici() {
        return kullanici;
    }

    public void setKullanici(String kullanici) {
        this.kullanici = kullanici;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
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
        if (!(object instanceof Uye)) {
            return false;
        }
        Uye other = (Uye) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pojo.Uye[id=" + id + "]";
    }

}
