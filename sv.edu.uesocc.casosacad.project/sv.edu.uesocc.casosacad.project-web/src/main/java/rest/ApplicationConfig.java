package rest;

import java.util.Set;
import javax.ws.rs.core.Application;


/**
 *
 * @author yovany
 */
@javax.ws.rs.ApplicationPath("ws")
public class ApplicationConfig extends Application{
    
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
//                addRestResourceClasses(resources);
        resources.add(TipoRequisitoRest.class);
        resources.add(TipoPasoRest.class);
        return resources;
    }
    
    
     private void addRestResourceClasses(Set<Class<?>> resources){
        resources.add(rest.TipoPasoRest.class);
        resources.add(rest.TipoRequisitoRest.class);
    }
    
}
