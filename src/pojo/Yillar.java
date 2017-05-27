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
@Table(name = "YILLAR", catalog = "", schema = "APP")
@NamedQueries({@NamedQuery(name = "Yillar.findAll", query = "SELECT y FROM Yillar y"), @NamedQuery(name = "Yillar.findByYil", query = "SELECT y FROM Yillar y WHERE y.yil = :yil")})
public class Yillar implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "YIL")
    private Integer yil;

    public Yillar() {
    }

    public Yillar(Integer yil) {
        this.yil = yil;
    }

    public Integer getYil() {
        return yil;
    }

    public void setYil(Integer yil) {
        this.yil = yil;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (yil != null ? yil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Yillar)) {
            return false;
        }
        Yillar other = (Yillar) object;
        if ((this.yil == null && other.yil != null) || (this.yil != null && !this.yil.equals(other.yil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pojo.Yillar[yil=" + yil + "]";
    }

}
