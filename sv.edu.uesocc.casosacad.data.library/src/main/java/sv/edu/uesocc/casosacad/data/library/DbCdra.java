/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.casosacad.data.library;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wxlter97
 */
@Entity
@Table(name = "db_cdra", catalog = "casostpi", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DbCdra.findAll", query = "SELECT d FROM DbCdra d")
    , @NamedQuery(name = "DbCdra.findByIdCDRA", query = "SELECT d FROM DbCdra d WHERE d.idCDRA = :idCDRA")
    , @NamedQuery(name = "DbCdra.findByFecha", query = "SELECT d FROM DbCdra d WHERE d.fecha = :fecha")})
public class DbCdra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCDRA", nullable = false)
    private Integer idCDRA;
    @Basic(optional = false)
    @Column(name = "Fecha", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "IdCDR", referencedColumnName = "idCDR", nullable = false)
    @ManyToOne(optional = false)
    private CasoDetalleRequisito idCDR;

    public DbCdra() {
    }

    public DbCdra(Integer idCDRA) {
        this.idCDRA = idCDRA;
    }

    public DbCdra(Integer idCDRA, Date fecha) {
        this.idCDRA = idCDRA;
        this.fecha = fecha;
    }

    public Integer getIdCDRA() {
        return idCDRA;
    }

    public void setIdCDRA(Integer idCDRA) {
        this.idCDRA = idCDRA;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public CasoDetalleRequisito getIdCDR() {
        return idCDR;
    }

    public void setIdCDR(CasoDetalleRequisito idCDR) {
        this.idCDR = idCDR;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCDRA != null ? idCDRA.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DbCdra)) {
            return false;
        }
        DbCdra other = (DbCdra) object;
        if ((this.idCDRA == null && other.idCDRA != null) || (this.idCDRA != null && !this.idCDRA.equals(other.idCDRA))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.uesocc.casosacad.data.library.DbCdra[ idCDRA=" + idCDRA + " ]";
    }
    
}
