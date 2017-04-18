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
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.primefaces.event.SelectEvent;
import sv.edu.uesocc.casosacad.data.library.TipoPaso;
import sv.edu.uesocc.casosacad.pojos.TipoPasoFacadeLocal;
/**
 *
 * @author wxlter97
 * 
 * Gestion de Casos, Administracion Academica
 * 
 */

@Named(value = "mbTipoPaso")
@ViewScoped
public class mbTipoPaso implements Serializable {

    @EJB
    private TipoPasoFacadeLocal fl;
    private LazyDataModel<TipoPaso> ldm;
    private TipoPaso c = new TipoPaso();
    private TipoPaso selectedTipoPaso;
    private boolean showDetail = false;
    private boolean btnAdd = false;
    private boolean btnEdit = false;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public void saveMessage() {
        FacesContext context = FacesContext.getCurrentInstance();         
        context.addMessage(null, new FacesMessage(message) );
    }
    
   

    @PostConstruct
    private void init() {
    selectedTipoPaso = new TipoPaso();
        try {
            this.setLdm(new LazyDataModel<TipoPaso>() {
                @Override
                public Object getRowKey(TipoPaso object) {
                    if (object != null) {
                        return object.getIdTipoPaso();
                    }
                    return null;
                }

                @Override
                public TipoPaso getRowData(String rowKey) {
                    if (rowKey != null && !rowKey.isEmpty() && this.getWrappedData() != null) {
                        try {
                            Integer buscado = new Integer(rowKey);
                            for (TipoPaso thi : (List<TipoPaso>) getWrappedData()) {
                                if (thi.getIdTipoPaso().compareTo(buscado) == 0) {
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
                public List<TipoPaso> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                    List<TipoPaso> salida = new ArrayList();

                    if (filters == null || filters.isEmpty()) {
                        try {
                            if (fl != null) {
                                this.setRowCount(fl.count());
                                salida = fl.findRange(first, pageSize);
                            }

                        } catch (Exception e) {
                            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
                        }
                        return salida;
                    }
                    salida = null;
                    try {
                        if (!filters.isEmpty() && (filters.containsKey("idTipoPaso")||filters.containsKey("descripcion")||filters.containsKey("activo"))) {
                            
                            if(filters.containsKey("idTipoPaso")){
                                salida = fl.findBy("idTipoPaso", filters.get("idTipoPaso").toString());
                            if (ldm != null) {
                                ldm.setRowCount(salida.size());
                            }
                            } else if(filters.containsKey("descripcion")){
                                salida = fl.findBy("descripcion", filters.get("descripcion").toString());
                            if (ldm != null) {
                                ldm.setRowCount(salida.size());
                            }
                            } else if(filters.containsKey("activo")){
                               
                                salida = fl.findBy("activo", filters.get("activo").toString());
                               
                                 if (ldm != null) {
                                ldm.setRowCount(salida.size());
                            }
                           
                            }
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
                    } finally {
                        if (salida == null) {
                            salida = new ArrayList();
                        }
                    }
                    return salida;
                }
            });

        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public TipoPaso getC() {
        return c;
    }

    public void setC(TipoPaso c) {
        this.c = c;
    }

    public TipoPasoFacadeLocal getFl() {
        return fl;
    }

    public void setFl(TipoPasoFacadeLocal fl) {
        this.fl = fl;
    }

    public List<TipoPaso> findAll() {
        return getFl().findAll();
    }

    public void create() {
        try {
            this.getFl().create(this.getSelectedTipoPaso());
            setMessage("Registro guardado con éxito.");
            saveMessage();
            selectedTipoPaso = new TipoPaso();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void remove() {
        try {
            this.getFl().remove(this.getSelectedTipoPaso());
            setMessage("Registro eliminado con éxito.");
            saveMessage();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }        
    }

    public void edit(TipoPaso t) {
        this.setC(t);
    }

    public void edit() {
        
        try {
            this.getFl().edit(this.getSelectedTipoPaso());
            setMessage("Registro modificado con éxito.");
            saveMessage();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        } 
    }

    public void empty() {
        this.setC(new TipoPaso());
    }

    public TipoPaso getSelectedTipoPaso() {
        return selectedTipoPaso;
    }

    public void setSelectedTipoPaso(TipoPaso selectedPaso) {
        this.selectedTipoPaso = selectedTipoPaso;
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
                this.selectedTipoPaso = (TipoPaso) se.getObject();
                this.setShowDetail(true);
                this.setBtnAdd(false);
                this.setBtnEdit(true);

            } catch (Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            }
        }
    }

    public void nuevo() {

        this.selectedTipoPaso = new TipoPaso();
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
}