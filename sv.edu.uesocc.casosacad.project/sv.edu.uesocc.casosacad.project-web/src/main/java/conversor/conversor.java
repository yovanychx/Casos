/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conversor;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import sv.edu.uesocc.casosacad.data.library.TipoRequisito;
import sv.edu.uesocc.casosacad.pojos.TipoRequisitoFacadeLocal;

/**
 *
 * @author wxlter97
 */

@FacesConverter("convertir")
public class conversor implements Converter{

    TipoRequisito tr;
    TipoRequisitoFacadeLocal fl;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
       
     if (value == null || value.isEmpty()) {
        return null;
    }

    try {
        return fl.find(Long.valueOf(value));
    } catch (NumberFormatException e) {
        throw new ConverterException(new FacesMessage(value + " is not a valid Warehouse ID"), e);
    }
        
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
    if (value == null) {
        return "";
    }

    if (value instanceof TipoRequisito) {
        return String.valueOf(((TipoRequisito) value).getIdTipoRequisito());
    } else {
        throw new ConverterException(new FacesMessage(value + " is not a valid Warehouse"));
    }
    
    }
    
}
