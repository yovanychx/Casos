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
import javax.swing.JOptionPane;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import sv.edu.uesocc.casosacad.data.library.TipoRequisito;
import sv.edu.uesocc.casosacad.pojos.TipoRequisitoFacadeLocal;


/**
 *
 * @author yovany
 */
@Path("tiporequisito")
public class TipoRequisitoRest implements Serializable{
    
    @EJB
    private TipoRequisitoFacadeLocal ejbTiporequiLocal;
    
    @GET
    @Produces({MediaType.APPLICATION_JSON + "; charset=utf-8"})
    public List<TipoRequisito> findAll() {
        List<TipoRequisito> salida = null;

        try {
            if (ejbTiporequiLocal != null) {
                salida = ejbTiporequiLocal.findAll();
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
            if (ejbTiporequiLocal != null) {
                return ejbTiporequiLocal.count();
            }

        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        return 0;
    }
    
    @GET
    @Path("findbyid/{id}")
    @Produces({MediaType.APPLICATION_JSON + "; charset=utf-8"})
    public TipoRequisito findById( @PathParam("id") Integer id)
    {
        try {
            if (ejbTiporequiLocal != null) {
                return ejbTiporequiLocal.find(id);
            }

        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        return new TipoRequisito();
    }
    /*
    @POST
    @Consumes({MediaType.APPLICATION_JSON+ "; charset=utf-8"})
    public void create(TipoRequisito registro){
        ejbTiporequiLocal.create(registro); 
        
    }
    
    @PUT
    @Path("edit/{id}")
    @Consumes({MediaType.APPLICATION_JSON+ "; charset=utf-8"})
    public void edit(@PathParam("id")Integer id,TipoRequisito usuario){
        ejbTiporequiLocal.edit(usuario);
    }
    
    @DELETE
    @Path("delete/{id}")
    @Consumes({MediaType.APPLICATION_JSON+ "; charset=utf-8"})
    public void remove(@PathParam("id")Integer id){
        ejbTiporequiLocal.remove(ejbTiporequiLocal.find(id));
    }
    */

    /*
        @POST
    @Produces({MediaType.APPLICATION_JSON + "; charset=utf-8"})
    public TipoRequisito create(TipoRequisito registro) {
        if (registro != null && registro.getIdTipoRequisito()== null) {
            try {
                if (ejbTiporequiLocal != null) {
                    TipoRequisito d = ejbTiporequiLocal.create(registro);
                    if (d != null) {
                        return d;
                    }
                }
            } catch (Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            }
        }

        return new TipoRequisito();
    }
*/
    
//        @POST
//        @Path("create")
//    @Produces({MediaType.APPLICATION_JSON + "; charset=utf-8"})
//    public TipoRequisito create(TipoRequisito registro) {
//        if (registro != null && registro.getIdTipoRequisito()== null) {
//            try {
//                if (ejbTiporequiLocal != null) {
//                    TipoRequisito d = ejbTiporequiLocal.crear(registro);
//                    if (d != null) {
//                        return d;
//                    }
//                }
//            } catch (Exception e) {
//                Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
//            }
//        }
//
//        return new TipoRequisito();
//    }
//
//    @Path("delete/{id}")
//    @DELETE
//    @Produces({MediaType.APPLICATION_JSON + "; charset=utf-8"})
//    public TipoRequisito remove(@PathParam("id") Integer id) {
//        if (ejbTiporequiLocal != null) {
//            TipoRequisito d = ejbTiporequiLocal.find(id);
//            if (d != null) {
//            TipoRequisito td = ejbTiporequiLocal.remover(d);
//            return td;
//            }
//        }
//        return new TipoRequisito();
//    }
//
//    @Path("modificar")
//    @PUT
//    @Produces({MediaType.APPLICATION_JSON + "; charset=utf-8"})
//    public TipoRequisito edit(TipoRequisito registro) {
//        System.out.println("Recibe peticion");
//        if (ejbTiporequiLocal != null && registro != null) {
//            if (ejbTiporequiLocal.edit(registro)) {
//                return registro;
//            }
//        }
//
//        return new TipoRequisito();
//    }

    
}
