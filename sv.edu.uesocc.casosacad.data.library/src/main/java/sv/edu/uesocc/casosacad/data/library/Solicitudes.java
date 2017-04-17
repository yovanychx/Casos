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
import javax.persistence.Lob;
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
@Table(name = "solicitudes", catalog = "casostpi", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Solicitudes.findAll", query = "SELECT s FROM Solicitudes s")
    , @NamedQuery(name = "Solicitudes.findByIdSolicitud", query = "SELECT s FROM Solicitudes s WHERE s.idSolicitud = :idSolicitud")
    , @NamedQuery(name = "Solicitudes.findByCarnet", query = "SELECT s FROM Solicitudes s WHERE s.carnet = :carnet")
    , @NamedQuery(name = "Solicitudes.findByNit", query = "SELECT s FROM Solicitudes s WHERE s.nit = :nit")
    , @NamedQuery(name = "Solicitudes.findByFechaRecibida", query = "SELECT s FROM Solicitudes s WHERE s.fechaRecibida = :fechaRecibida")
    , @NamedQuery(name = "Solicitudes.findByUsuario", query = "SELECT s FROM Solicitudes s WHERE s.usuario = :usuario")})
public class Solicitudes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSolicitud", nullable = false)
    private Integer idSolicitud;
    @Column(name = "carnet", length = 7)
    private String carnet;
    @Basic(optional = false)
    @Column(name = "NIT", nullable = false)
    private int nit;
    @Basic(optional = false)
    @Column(name = "FechaRecibida", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaRecibida;
    @Basic(optional = false)
    @Column(name = "Usuario", nullable = false)
    private int usuario;
    @Basic(optional = false)
    @Lob
    @Column(name = "DescripcionSolicitud", nullable = false, length = 65535)
    private String descripcionSolicitud;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSolicitud")
    private List<Caso> casoList;

    public Solicitudes() {
    }

    public Solicitudes(Integer idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public Solicitudes(Integer idSolicitud, int nit, Date fechaRecibida, int usuario, String descripcionSolicitud) {
        this.idSolicitud = idSolicitud;
        this.nit = nit;
        this.fechaRecibida = fechaRecibida;
        this.usuario = usuario;
        this.descripcionSolicitud = descripcionSolicitud;
    }

    public Integer getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Integer idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public int getNit() {
        return nit;
    }

    public void setNit(int nit) {
        this.nit = nit;
    }

    public Date getFechaRecibida() {
        return fechaRecibida;
    }

    public void setFechaRecibida(Date fechaRecibida) {
        this.fechaRecibida = fechaRecibida;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public String getDescripcionSolicitud() {
        return descripcionSolicitud;
    }

    public void setDescripcionSolicitud(String descripcionSolicitud) {
        this.descripcionSolicitud = descripcionSolicitud;
    }

    @XmlTransient
    public List<Caso> getCasoList() {
        return casoList;
    }

    public void setCasoList(List<Caso> casoList) {
        this.casoList = casoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSolicitud != null ? idSolicitud.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Solicitudes)) {
            return false;
        }
        Solicitudes other = (Solicitudes) object;
        if ((this.idSolicitud == null && other.idSolicitud != null) || (this.idSolicitud != null && !this.idSolicitud.equals(other.idSolicitud))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.uesocc.casosacad.data.library.Solicitudes[ idSolicitud=" + idSolicitud + " ]";
    }
    
}
