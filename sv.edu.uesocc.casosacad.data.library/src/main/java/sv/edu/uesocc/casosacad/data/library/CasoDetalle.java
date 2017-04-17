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
@Table(name = "caso_detalle", catalog = "casostpi", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CasoDetalle.findAll", query = "SELECT c FROM CasoDetalle c")
    , @NamedQuery(name = "CasoDetalle.findByIdCasoDetalle", query = "SELECT c FROM CasoDetalle c WHERE c.idCasoDetalle = :idCasoDetalle")
    , @NamedQuery(name = "CasoDetalle.findByEstado", query = "SELECT c FROM CasoDetalle c WHERE c.estado = :estado")
    , @NamedQuery(name = "CasoDetalle.findByIdProcesDetalle", query = "SELECT c FROM CasoDetalle c WHERE c.idProcesDetalle = :idProcesDetalle")
    , @NamedQuery(name = "CasoDetalle.findByFecha", query = "SELECT c FROM CasoDetalle c WHERE c.fecha = :fecha")})
public class CasoDetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCasoDetalle", nullable = false)
    private Integer idCasoDetalle;
    @Basic(optional = false)
    @Column(name = "Estado", nullable = false, length = 10)
    private String estado;
    @Basic(optional = false)
    @Column(name = "idProcesDetalle", nullable = false)
    private int idProcesDetalle;
    @Basic(optional = false)
    @Column(name = "Fecha", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCasoDetalle")
    private List<CasoDetalleRequisito> casoDetalleRequisitoList;
    @JoinColumn(name = "IdCaso", referencedColumnName = "IdCaso", nullable = false)
    @ManyToOne(optional = false)
    private Caso idCaso;

    public CasoDetalle() {
    }

    public CasoDetalle(Integer idCasoDetalle) {
        this.idCasoDetalle = idCasoDetalle;
    }

    public CasoDetalle(Integer idCasoDetalle, String estado, int idProcesDetalle, Date fecha) {
        this.idCasoDetalle = idCasoDetalle;
        this.estado = estado;
        this.idProcesDetalle = idProcesDetalle;
        this.fecha = fecha;
    }

    public Integer getIdCasoDetalle() {
        return idCasoDetalle;
    }

    public void setIdCasoDetalle(Integer idCasoDetalle) {
        this.idCasoDetalle = idCasoDetalle;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdProcesDetalle() {
        return idProcesDetalle;
    }

    public void setIdProcesDetalle(int idProcesDetalle) {
        this.idProcesDetalle = idProcesDetalle;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @XmlTransient
    public List<CasoDetalleRequisito> getCasoDetalleRequisitoList() {
        return casoDetalleRequisitoList;
    }

    public void setCasoDetalleRequisitoList(List<CasoDetalleRequisito> casoDetalleRequisitoList) {
        this.casoDetalleRequisitoList = casoDetalleRequisitoList;
    }

    public Caso getIdCaso() {
        return idCaso;
    }

    public void setIdCaso(Caso idCaso) {
        this.idCaso = idCaso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCasoDetalle != null ? idCasoDetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CasoDetalle)) {
            return false;
        }
        CasoDetalle other = (CasoDetalle) object;
        if ((this.idCasoDetalle == null && other.idCasoDetalle != null) || (this.idCasoDetalle != null && !this.idCasoDetalle.equals(other.idCasoDetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.uesocc.casosacad.data.library.CasoDetalle[ idCasoDetalle=" + idCasoDetalle + " ]";
    }
    
}
