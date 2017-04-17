/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.casosacad.data.library;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author wxlter97
 */
@Entity
@Table(name = "caso", catalog = "casostpi", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Caso.findAll", query = "SELECT c FROM Caso c")
    , @NamedQuery(name = "Caso.findByIdCaso", query = "SELECT c FROM Caso c WHERE c.idCaso = :idCaso")})
public class Caso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdCaso", nullable = false)
    private Integer idCaso;
    @JoinColumn(name = "idSolicitud", referencedColumnName = "idSolicitud", nullable = false)
    @ManyToOne(optional = false)
    private Solicitudes idSolicitud;
    @JoinColumn(name = "idProceso", referencedColumnName = "idProceso", nullable = false)
    @ManyToOne(optional = false)
    private Proceso idProceso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCaso")
    private List<CasoDetalle> casoDetalleList;

    public Caso() {
    }

    public Caso(Integer idCaso) {
        this.idCaso = idCaso;
    }

    public Integer getIdCaso() {
        return idCaso;
    }

    public void setIdCaso(Integer idCaso) {
        this.idCaso = idCaso;
    }

    public Solicitudes getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Solicitudes idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public Proceso getIdProceso() {
        return idProceso;
    }

    public void setIdProceso(Proceso idProceso) {
        this.idProceso = idProceso;
    }

    @XmlTransient
    public List<CasoDetalle> getCasoDetalleList() {
        return casoDetalleList;
    }

    public void setCasoDetalleList(List<CasoDetalle> casoDetalleList) {
        this.casoDetalleList = casoDetalleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCaso != null ? idCaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Caso)) {
            return false;
        }
        Caso other = (Caso) object;
        if ((this.idCaso == null && other.idCaso != null) || (this.idCaso != null && !this.idCaso.equals(other.idCaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.uesocc.casosacad.data.library.Caso[ idCaso=" + idCaso + " ]";
    }
    
}
