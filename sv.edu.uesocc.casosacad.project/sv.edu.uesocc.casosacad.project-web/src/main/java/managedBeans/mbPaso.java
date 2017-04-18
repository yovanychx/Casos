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
import sv.edu.uesocc.casosacad.data.library.Paso;
import sv.edu.uesocc.casosacad.data.library.TipoPaso;
import sv.edu.uesocc.casosacad.pojos.PasoFacadeLocal;

/**
 *
 * @author wxlter97
 * 
 * Gestion de Casos, Administracion Academica
 * 
 */

@Named(value = "mbPaso")
@ViewScoped
public class mbPaso implements Serializable {

    @EJB
    private PasoFacadeLocal fl;
    private LazyDataModel<Paso> ldm;
    private Paso c = new Paso();
    private Paso selectedPaso;
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
    selectedPaso = new Paso();
        try {
            this.setLdm(new LazyDataModel<Paso>() {
                @Override
                public Object getRowKey(Paso object) {
                    if (object != null) {
                        return object.getIdPaso();
                    }
                    return null;
                }

                @Override
                public Paso getRowData(String rowKey) {
                    if (rowKey != null && !rowKey.isEmpty() && this.getWrappedData() != null) {
                        try {
                            Integer buscado = new Integer(rowKey);
                            for (Paso thi : (List<Paso>) getWrappedData()) {
                                if (thi.getIdPaso().compareTo(buscado) == 0) {
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
                public List<Paso> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                    List<Paso> salida = new ArrayList();

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
                        if (!filters.isEmpty() && (filters.containsKey("idPaso")||filters.containsKey("nombre")||filters.containsKey("descripcion")||filters.containsKey("idTipoPaso.idTipoPaso"))) {
                            
                            if(filters.containsKey("idPaso")){
                                salida = fl.findBy("idPaso", filters.get("idPaso").toString());
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
                           
                            }
                            else if(filters.containsKey("idTipoPaso.idTipoPaso")){
                                salida = fl.findByJoined("idTipoPaso", (TipoPaso) filters.get("idTipoPaso.idTipoPaso"));
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

    public Paso getC() {
        return c;
    }

    public void setC(Paso c) {
        this.c = c;
    }

    public PasoFacadeLocal getFl() {
        return fl;
    }

    public String showName(TipoPaso t) {

        return t.getNombre();

    }

    public void setFl(PasoFacadeLocal fl) {
        this.fl = fl;
    }

    public List<Paso> findAll() {
        return getFl().findAll();
    }

    public void create() {
        try {
            this.getFl().create(this.getSelectedPaso());
            selectedPaso = new Paso();
            setMessage("Registro guardado con éxito.");
            saveMessage();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void remove() {
        try {
            this.getFl().remove(this.getSelectedPaso());
            setMessage("Registro eliminado con éxito.");
            saveMessage();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }        
    }

    public void edit(Paso t) {
        this.setC(t);
    }

    public void edit() {
        try {
            this.getFl().edit(this.getSelectedPaso());
            setMessage("Registro modificado con éxito.");
            saveMessage();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void empty() {
        this.setC(new Paso());
    }

    public Paso getSelectedPaso() {
        return selectedPaso;
    }

    public void setSelectedPaso(Paso selectedPaso) {
        this.selectedPaso = selectedPaso;
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
                this.selectedPaso = (Paso) se.getObject();
                this.setShowDetail(true);
                this.setBtnAdd(false);
                this.setBtnEdit(true);

            } catch (Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            }
        }
    }

    public void nuevo() {

        this.selectedPaso = new Paso();
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