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
@Table(name = "tipo_requisito", catalog = "casostpi", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoRequisito.findAll", query = "SELECT t FROM TipoRequisito t")
    , @NamedQuery(name = "TipoRequisito.findByIdTipoRequisito", query = "SELECT t FROM TipoRequisito t WHERE t.idTipoRequisito = :idTipoRequisito")
    , @NamedQuery(name = "TipoRequisito.findByNombre", query = "SELECT t FROM TipoRequisito t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "TipoRequisito.findByActivo", query = "SELECT t FROM TipoRequisito t WHERE t.activo = :activo")
    , @NamedQuery(name = "TipoRequisito.findByObservaciones", query = "SELECT t FROM TipoRequisito t WHERE t.observaciones = :observaciones")})
public class TipoRequisito implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTipoRequisito", nullable = false)
    private Integer idTipoRequisito;
    @Basic(optional = false)
    @Column(name = "Nombre", nullable = false, length = 25)
    private String nombre;
    @Basic(optional = false)
    @Column(name = "activo", nullable = false)
    private boolean activo;
    @Basic(optional = false)
    @Column(name = "observaciones", nullable = false, length = 100)
    private String observaciones;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoRequisito")
    private List<Requisito> requisitoList;

    public TipoRequisito() {
    }

    public TipoRequisito(Integer idTipoRequisito) {
        this.idTipoRequisito = idTipoRequisito;
    }

    public TipoRequisito(Integer idTipoRequisito, String nombre, boolean activo, String observaciones) {
        this.idTipoRequisito = idTipoRequisito;
        this.nombre = nombre;
        this.activo = activo;
        this.observaciones = observaciones;
    }

    public Integer getIdTipoRequisito() {
        return idTipoRequisito;
    }

    public void setIdTipoRequisito(Integer idTipoRequisito) {
        this.idTipoRequisito = idTipoRequisito;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @XmlTransient
    public List<Requisito> getRequisitoList() {
        return requisitoList;
    }

    public void setRequisitoList(List<Requisito> requisitoList) {
        this.requisitoList = requisitoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoRequisito != null ? idTipoRequisito.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoRequisito)) {
            return false;
        }
        TipoRequisito other = (TipoRequisito) object;
        if ((this.idTipoRequisito == null && other.idTipoRequisito != null) || (this.idTipoRequisito != null && !this.idTipoRequisito.equals(other.idTipoRequisito))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.valueOf(idTipoRequisito);
    }
    
}
