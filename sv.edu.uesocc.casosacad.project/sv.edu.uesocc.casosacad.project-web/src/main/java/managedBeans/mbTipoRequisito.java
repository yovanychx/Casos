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
import sv.edu.uesocc.casosacad.data.library.TipoRequisito;
import sv.edu.uesocc.casosacad.pojos.TipoRequisitoFacadeLocal;

/**
 *
 * @author wxlter97
 * 
 * Gestion de Casos, Administracion Academica
 * 
 */


@Named(value = "mbTipoRequisito")
@ViewScoped
public class mbTipoRequisito implements Serializable {

    @EJB
    private TipoRequisitoFacadeLocal fl;
    private LazyDataModel<TipoRequisito> ldm;
    private TipoRequisito c = new TipoRequisito();
    private TipoRequisito selectedTipoRequisito;
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
    selectedTipoRequisito = new TipoRequisito();
        try {
            this.setLdm(new LazyDataModel<TipoRequisito>() {
                @Override
                public Object getRowKey(TipoRequisito object) {
                    if (object != null) {
                        return object.getIdTipoRequisito();
                    }
                    return null;
                }

                @Override
                public TipoRequisito getRowData(String rowKey) {
                    if (rowKey != null && !rowKey.isEmpty() && this.getWrappedData() != null) {
                        try {
                            Integer buscado = new Integer(rowKey);
                            for (TipoRequisito thi : (List<TipoRequisito>) getWrappedData()) {
                                if (thi.getIdTipoRequisito().compareTo(buscado) == 0) {
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
                public List<TipoRequisito> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                    List<TipoRequisito> salida = new ArrayList();

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
                        if (!filters.isEmpty() && (filters.containsKey("idTipoRequisito")||filters.containsKey("nombre")||filters.containsKey("activo")||filters.containsKey("observaciones"))) {
                            
                            if(filters.containsKey("idTipoRequisito")){
                                salida = fl.findBy("idTipoRequisito", filters.get("idTipoRequisito").toString());
                            if (ldm != null) {
                                ldm.setRowCount(salida.size());
                            }
                            } else if(filters.containsKey("nombre")){
                                salida = fl.findBy("nombre", filters.get("nombre").toString());
                            if (ldm != null) {
                                ldm.setRowCount(salida.size());
                            }
                            } else if(filters.containsKey("activo")){
                               
                                salida = fl.findBy("activo", filters.get("activo").toString());
                               
                                 if (ldm != null) {
                                ldm.setRowCount(salida.size());
                            }
                           
                            }else if(filters.containsKey("observaciones")){
                                salida = fl.findBy("observaciones", filters.get("observaciones").toString());
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

    public TipoRequisito getC() {
        return c;
    }

    public void setC(TipoRequisito c) {
        this.c = c;
    }

    public TipoRequisitoFacadeLocal getFl() {
        return fl;
    }

    public void setFl(TipoRequisitoFacadeLocal fl) {
        this.fl = fl;
    }

    public List<TipoRequisito> findAll() {
        return getFl().findAll();
    }

    public void create() {
        try {
            this.getFl().create(this.getSelectedTipoRequisito());
            selectedTipoRequisito= new TipoRequisito();
            setMessage("Registro guardado con éxito.");
            saveMessage();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void remove() {
        try {
            this.getFl().remove(this.getSelectedTipoRequisito());
            setMessage("Registro eliminado con éxito.");
            saveMessage();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        
    }

    public void edit(TipoRequisito t) {
        this.setC(t);
    }

    public void edit() {
        try {
            this.getFl().edit(this.getSelectedTipoRequisito());
            setMessage("Registro modificado con éxito.");
            saveMessage();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void empty() {
        this.setC(new TipoRequisito());
    }

    public TipoRequisito getSelectedTipoRequisito() {
        return selectedTipoRequisito;
    }

    public void setSelectedTipoRequisito(TipoRequisito selectedRequisito) {
        this.selectedTipoRequisito = selectedTipoRequisito;
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
                this.selectedTipoRequisito = (TipoRequisito) se.getObject();
                this.setShowDetail(true);
                this.setBtnAdd(false);
                this.setBtnEdit(true);

            } catch (Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            }
        }
    }

    public void nuevo() {

        this.selectedTipoRequisito = new TipoRequisito();
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