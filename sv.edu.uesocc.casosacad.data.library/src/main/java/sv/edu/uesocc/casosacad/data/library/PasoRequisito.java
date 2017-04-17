/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.casosacad.data.library;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wxlter97
 */
@Entity
@Table(name = "paso_requisito", catalog = "casostpi", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PasoRequisito.findAll", query = "SELECT p FROM PasoRequisito p")
    , @NamedQuery(name = "PasoRequisito.findByIdPasoRequisito", query = "SELECT p FROM PasoRequisito p WHERE p.idPasoRequisito = :idPasoRequisito")
    , @NamedQuery(name = "PasoRequisito.findByIndice", query = "SELECT p FROM PasoRequisito p WHERE p.indice = :indice")})
public class PasoRequisito implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPasoRequisito", nullable = false)
    private Integer idPasoRequisito;
    @Basic(optional = false)
    @Column(name = "indice", nullable = false)
    private int indice;
    @JoinColumn(name = "idPaso", referencedColumnName = "idPaso", nullable = false)
    @ManyToOne(optional = false)
    private Paso idPaso;
    @JoinColumn(name = "idRequisito", referencedColumnName = "idRequisito", nullable = false)
    @ManyToOne(optional = false)
    private Requisito idRequisito;

    public PasoRequisito() {
    }

    public PasoRequisito(Integer idPasoRequisito) {
        this.idPasoRequisito = idPasoRequisito;
    }

    public PasoRequisito(Integer idPasoRequisito, int indice) {
        this.idPasoRequisito = idPasoRequisito;
        this.indice = indice;
    }

    public Integer getIdPasoRequisito() {
        return idPasoRequisito;
    }

    public void setIdPasoRequisito(Integer idPasoRequisito) {
        this.idPasoRequisito = idPasoRequisito;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public Paso getIdPaso() {
        return idPaso;
    }

    public void setIdPaso(Paso idPaso) {
        this.idPaso = idPaso;
    }

    public Requisito getIdRequisito() {
        return idRequisito;
    }

    public void setIdRequisito(Requisito idRequisito) {
        this.idRequisito = idRequisito;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPasoRequisito != null ? idPasoRequisito.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PasoRequisito)) {
            return false;
        }
        PasoRequisito other = (PasoRequisito) object;
        if ((this.idPasoRequisito == null && other.idPasoRequisito != null) || (this.idPasoRequisito != null && !this.idPasoRequisito.equals(other.idPasoRequisito))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.uesocc.casosacad.data.library.PasoRequisito[ idPasoRequisito=" + idPasoRequisito + " ]";
    }
    
}
