/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.casosacad.pojos;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sv.edu.uesocc.casosacad.data.library.DbCdra;

/**
 *
 * @author wxlter97
 */
@Stateless
public class DbCdraFacade extends AbstractFacade<DbCdra> implements DbCdraFacadeLocal {

    @PersistenceContext(unitName = "sv.edu.uesocc.casosacad_sv.edu.uesocc.casosacad.project-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DbCdraFacade() {
        super(DbCdra.class);
    }
    
}
