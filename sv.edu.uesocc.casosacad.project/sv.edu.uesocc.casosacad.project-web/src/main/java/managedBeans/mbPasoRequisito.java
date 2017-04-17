package managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.omnifaces.cdi.ViewScoped;
import org.primefaces.model.SortOrder;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import sv.edu.uesocc.casosacad.data.library.PasoRequisito;
import sv.edu.uesocc.casosacad.pojos.PasoRequisitoFacadeLocal;

/**
 *
 * @author wxlter97
 * 
 * Gestion de Casos, Administracion Academica
 * 
 */

@Named(value = "mbPasoRequisito")
@ViewScoped
public class mbPasoRequisito implements Serializable {

    @EJB
    private PasoRequisitoFacadeLocal fl;
    private LazyDataModel<PasoRequisito> ldm;
    private PasoRequisito c = new PasoRequisito();
    private PasoRequisito selectedPasoRequisito;
    private String mensaje;
    private boolean showDetail = false;
    private boolean btnAdd = false;
    private boolean btnEdit = false;
    
   

    @PostConstruct
    private void init() {
this.setSelectedPasoRequisito(new PasoRequisito());
        try {
            this.setLdm(new LazyDataModel<PasoRequisito>() {
                @Override
                public Object getRowKey(PasoRequisito object) {
                    if (object != null) {
                        return object.getIdPasoRequisito();
                    }
                    return null;
                }

                @Override
                public PasoRequisito getRowData(String rowKey) {
                    if (rowKey != null && !rowKey.isEmpty() && this.getWrappedData() != null) {
                        try {
                            Integer buscado = new Integer(rowKey);
                            for (PasoRequisito thi : (List<PasoRequisito>) getWrappedData()) {
                                if (thi.getIdPasoRequisito().compareTo(buscado) == 0) {
                                    return thi;
                                }
                            }
                        } catch (Exception e) {
                            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
                        }
                    }
                    return null;
                }

                @Override
                public List<PasoRequisito> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                    List<PasoRequisito> salida = new ArrayList();

                    
                        try {
                            if (fl != null) {
                                this.setRowCount(getFl().count());
                                salida = getFl().findRange(first, pageSize);
                            }

                        } catch (Exception e) {
                            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
                        }
                        return salida;
                    }
                    
            });

        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public PasoRequisito getC() {
        return c;
    }

    public void setC(PasoRequisito c) {
        this.c = c;
    }

    public PasoRequisitoFacadeLocal getFl() {
        return fl;
    }

    public void setFl(PasoRequisitoFacadeLocal fl) {
        this.fl = fl;
    }

    public List<PasoRequisito> findAll() {
        return getFl().findAll();
    }

    public void create() {
        this.getFl().create(this.getSelectedPasoRequisito());
    }

    public void remove() {
        this.getFl().remove(this.getSelectedPasoRequisito());
    }

    public void edit(PasoRequisito t) {
        this.setC(t);
    }

    public void edit() {
        this.getFl().edit(this.getSelectedPasoRequisito());

    }

    public void empty() {
        this.setC(new PasoRequisito());
    }

    public PasoRequisito getSelectedPasoRequisito() {
        return selectedPasoRequisito;
    }

    public void setSelectedPasoRequisito(PasoRequisito selectedPasoRequisito) {
        this.selectedPasoRequisito = selectedPasoRequisito;
    }

    public LazyDataModel getLdm() {
        return ldm;
    }

    public void setLdm(LazyDataModel ldm) {
        this.ldm = ldm;
    }

    public void changeSelected(SelectEvent se) {
        if (se.getObject() != null) {
            try {
                this.setSelectedPasoRequisito((PasoRequisito) se.getObject());
                this.setShowDetail(true);
                this.setBtnAdd(false);
                this.setBtnEdit(true);

            } catch (Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            }
        }
    }

    public void nuevo() {

        this.setSelectedPasoRequisito(new PasoRequisito());
        this.setBtnAdd(true);
        this.setBtnEdit(false);
        this.setShowDetail(true);
    }

    

    public boolean isShowDetail() {
        return showDetail;
    }

    public void setShowDetail(boolean showDetail) {
        this.showDetail = showDetail;
    }

    public boolean isBtnAdd() {
        return btnAdd;
    }

    public void setBtnAdd(boolean btnAdd) {
        this.btnAdd = btnAdd;
    }

    public boolean isBtnEdit() {
        return btnEdit;
    }

    public void setBtnEdit(boolean btnEdit) {
        this.btnEdit = btnEdit;
    }


    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void sendMessage() {
        FacesContext context = FacesContext.getCurrentInstance();         
        context.addMessage(null, new FacesMessage(this.getMensaje()) );
}
}