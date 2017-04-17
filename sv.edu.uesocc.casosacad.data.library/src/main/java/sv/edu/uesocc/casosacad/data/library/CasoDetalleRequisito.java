/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.casosacad.data.library;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author wxlter97
 */
@Entity
@Table(name = "caso_detalle_requisito", catalog = "casostpi", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CasoDetalleRequisito.findAll", query = "SELECT c FROM CasoDetalleRequisito c")
    , @NamedQuery(name = "CasoDetalleRequisito.findByIdCDR", query = "SELECT c FROM CasoDetalleRequisito c WHERE c.idCDR = :idCDR")
    , @NamedQuery(name = "CasoDetalleRequisito.findByIdPasoRequisito", query = "SELECT c FROM CasoDetalleRequisito c WHERE c.idPasoRequisito = :idPasoRequisito")
    , @NamedQuery(name = "CasoDetalleRequisito.findByEstadoRequisito", query = "SELECT c FROM CasoDetalleRequisito c WHERE c.estadoRequisito = :estadoRequisito")
    , @NamedQuery(name = "CasoDetalleRequisito.findByFecha", query = "SELECT c FROM CasoDetalleRequisito c WHERE c.fecha = :fecha")})
public class CasoDetalleRequisito implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCDR", nullable = false)
    private Integer idCDR;
    @Basic(optional = false)
    @Column(name = "idPasoRequisito", nullable = false)
    private int idPasoRequisito;
    @Basic(optional = false)
    @Column(name = "EstadoRequisito", nullable = false, length = 10)
    private String estadoRequisito;
    @Basic(optional = false)
    @Column(name = "Fecha", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCDR")
    private List<DbCdra> dbCdraList;
    @JoinColumn(name = "idCasoDetalle", referencedColumnName = "idCasoDetalle", nullable = false)
    @ManyToOne(optional = false)
    private CasoDetalle idCasoDetalle;

    public CasoDetalleRequisito() {
    }

    public CasoDetalleRequisito(Integer idCDR) {
        this.idCDR = idCDR;
    }

    public CasoDetalleRequisito(Integer idCDR, int idPasoRequisito, String estadoRequisito, Date fecha) {
        this.idCDR = idCDR;
        this.idPasoRequisito = idPasoRequisito;
        this.estadoRequisito = estadoRequisito;
        this.fecha = fecha;
    }

    public Integer getIdCDR() {
        return idCDR;
    }

    public void setIdCDR(Integer idCDR) {
        this.idCDR = idCDR;
    }

    public int getIdPasoRequisito() {
        return idPasoRequisito;
    }

    public void setIdPasoRequisito(int idPasoRequisito) {
        this.idPasoRequisito = idPasoRequisito;
    }

    public String getEstadoRequisito() {
        return estadoRequisito;
    }

    public void setEstadoRequisito(String estadoRequisito) {
        this.estadoRequisito = estadoRequisito;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @XmlTransient
    public List<DbCdra> getDbCdraList() {
        return dbCdraList;
    }

    public void setDbCdraList(List<DbCdra> dbCdraList) {
        this.dbCdraList = dbCdraList;
    }

    public CasoDetalle getIdCasoDetalle() {
        return idCasoDetalle;
    }

    public void setIdCasoDetalle(CasoDetalle idCasoDetalle) {
        this.idCasoDetalle = idCasoDetalle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCDR != null ? idCDR.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CasoDetalleRequisito)) {
            return false;
        }
        CasoDetalleRequisito other = (CasoDetalleRequisito) object;
        if ((this.idCDR == null && other.idCDR != null) || (this.idCDR != null && !this.idCDR.equals(other.idCDR))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.uesocc.casosacad.data.library.CasoDetalleRequisito[ idCDR=" + idCDR + " ]";
    }
    
}
