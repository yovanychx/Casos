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
import sv.edu.uesocc.casosacad.data.library.Requisito;
import sv.edu.uesocc.casosacad.data.library.TipoRequisito;
import sv.edu.uesocc.casosacad.pojos.RequisitoFacadeLocal;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author wxlter97
 * 
 * Gestion de Casos, Administracion Academica
 * 
 */

@Named(value = "mbRequisito")
@ViewScoped
public class mbRequisito implements Serializable {

    @EJB
    private RequisitoFacadeLocal fl;
    private LazyDataModel<Requisito> ldm;
    private Requisito c = new Requisito();
    private Requisito selectedRequisito;
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
this.setSelectedRequisito(new Requisito());
        try {
            this.setLdm(new LazyDataModel<Requisito>() {
                @Override
                public Object getRowKey(Requisito object) {
                    if (object != null) {
                        return object.getIdRequisito();
                    }
                    return null;
                }

                @Override
                public Requisito getRowData(String rowKey) {
                    if (rowKey != null && !rowKey.isEmpty() && this.getWrappedData() != null) {
                        try {
                            Integer buscado = new Integer(rowKey);
                            for (Requisito thi : (List<Requisito>) getWrappedData()) {
                                if (thi.getIdRequisito().compareTo(buscado) == 0) {
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
                public List<Requisito> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                    List<Requisito> salida = new ArrayList();

                    if (filters == null || filters.isEmpty()) {
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
                    salida = null;
                    try {
                        if (!filters.isEmpty() && (filters.containsKey("idRequisito")||filters.containsKey("nombre")||filters.containsKey("descripcion")||filters.containsKey("prioridad")||filters.containsKey("idTipoRequisito.idTipoRequisito"))) {
                            
                            
                            
                            if(filters.containsKey("idRequisito")){
                                salida = fl.findBy("idRequisito", filters.get("idRequisito").toString());
                            if (ldm != null) {
                                ldm.setRowCount(salida.size());
                            }
                            } else if(filters.containsKey("nombre")){
                                salida = fl.findBy("nombre", filters.get("nombre").toString());
                            if (ldm != null) {
                                ldm.setRowCount(salida.size());
                            }
                            } else if(filters.containsKey("descripcion")){
                                salida = fl.findBy("descripcion", filters.get("descripcion").toString());
                            if (ldm != null) {
                                ldm.setRowCount(salida.size());
                            }
                            } else if(filters.containsKey("prioridad")){
                                salida = fl.findBy("prioridad", filters.get("prioridad").toString());
                            if (ldm != null) {
                                ldm.setRowCount(salida.size());
                            }
                            }
                            else if(filters.containsKey("idTipoRequisito.idTipoRequisito")){
                                salida = fl.findByJoined("idTipoRequisito", (TipoRequisito) filters.get("idTipoRequisito.idTipoRequisito"));
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

    public Requisito getC() {
        return c;
    }

    public void setC(Requisito c) {
        this.c = c;
    }

    public RequisitoFacadeLocal getFl() {
        return fl;
    }

    public String showName(TipoRequisito t) {

        return t.getNombre();

    }

    public void setFl(RequisitoFacadeLocal fl) {
        this.fl = fl;
    }

    public List<Requisito> findAll() {
        return getFl().findAll();
    }

    public void create() {
        try {
            this.getFl().create(this.getSelectedRequisito());
            selectedRequisito= new Requisito();
            setMessage("Registro guardado con éxito.");
            saveMessage();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }        
    }

    public void remove() {
        try {
            this.getFl().remove(this.getSelectedRequisito());
            setMessage("Registro eliminado con éxito.");
            saveMessage();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }         
    }

    public void edit(Requisito t) {
        this.setC(t);
    }

    public void edit() {
        try {
            this.getFl().edit(this.getSelectedRequisito());
            setMessage("Registro modificado con éxito.");
            saveMessage();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        } 
    }

    public void empty() {
        this.setC(new Requisito());
    }

    public Requisito getSelectedRequisito() {
        return selectedRequisito;
    }

    public void setSelectedRequisito(Requisito selectedRequisito) {
        this.selectedRequisito = selectedRequisito;
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
                this.setSelectedRequisito((Requisito) se.getObject());
                this.setShowDetail(true);
                this.setBtnAdd(false);
                this.setBtnEdit(true);

            } catch (Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            }
        }
    }

    public void nuevo() {

        this.setSelectedRequisito(new Requisito());
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