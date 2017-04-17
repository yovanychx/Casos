/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import sv.edu.uesocc.casosacad.data.library.TipoPaso;
import sv.edu.uesocc.casosacad.pojos.TipoPasoFacadeLocal;

/**
 *
 * @author yovany
 */

@Path("tipopaso")
public class TipoPasoRest implements Serializable{
    @EJB
    private TipoPasoFacadeLocal ejbTipoPaso;
    
    @GET
    @Produces({MediaType.APPLICATION_JSON + "; charset=utf-8"})
    public List<TipoPaso> findAll() {
        List<TipoPaso> salida = null;

        try {
            if (ejbTipoPaso != null) {
                salida = ejbTipoPaso.findAll();
            }
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            if (salida == null) {
                salida = new ArrayList<>();
            }
        }
        System.out.println(""+salida);
        return salida;
    }
    
    @Path("count")
    @GET
    @Produces({MediaType.TEXT_PLAIN})
    public Integer count() {

        try {
            if (ejbTipoPaso != null) {
                return ejbTipoPaso.count();
            }

        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        return 0;
    }
    
    @GET
    @Path("findbyid/{id}")
    @Produces({MediaType.APPLICATION_JSON + "; charset=utf-8"})
    public TipoPaso findById( @PathParam("id") Integer id)
    {
        try {
            if (ejbTipoPaso != null) {
                return ejbTipoPaso.find(id);
            }

        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        return new TipoPaso();
    }
    
//        @POST
//        @Path("create")
//    @Produces({MediaType.APPLICATION_JSON + "; charset=utf-8"})
//    public TipoPaso create(TipoPaso registro) {
//        if (registro != null && registro.getIdTipoPaso()== null) {
//            try {
//                if (ejbTipoPaso != null) {
//                    TipoPaso d = ejbTipoPaso.crear(registro);
//                    if (d != null) {
//                        return d;
//                    }
//                }
//            } catch (Exception e) {
//                Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
//            }
//        }
//
//        return new TipoPaso();
//    }
//
//    @Path("delete/{id}")
//    @DELETE
//    @Produces({MediaType.APPLICATION_JSON + "; charset=utf-8"})
//    public TipoPaso remove(@PathParam("id") Integer id) {
//        if (ejbTipoPaso != null) {
//            TipoPaso d = ejbTipoPaso.find(id);
//            if (d != null) {
//            TipoPaso td = ejbTipoPaso.remover(d);
//            return td;
//            }
//        }
//        return new TipoPaso();
//    }
//
//    @Path("modificar")
//    @PUT
//    @Produces({MediaType.APPLICATION_JSON + "; charset=utf-8"})
//    public TipoPaso edit(TipoPaso registro) {
//        System.out.println("Recibe peticion");
//        if (ejbTipoPaso != null && registro != null) {
//            if (ejbTipoPaso.edit(registro)) {
//                return registro;
//            }
//        }
//
//        return new TipoPaso();
//    }

    
}
