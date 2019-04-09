/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifi.gde.entity.util;

import java.sql.Connection;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import org.hibernate.Session;

/**
 *
 * @author dfkossi
 */
public class PhaseListenerIfiGde implements PhaseListener{
    
    //Avant la phase
    @Override
    public void beforePhase(PhaseEvent fase) {
        System.out.println("Avant la phase: "+ fase.getPhaseId());
        if (fase.getPhaseId().equals(PhaseId.RESTORE_VIEW)) {            
            Session session = HibernateIfiGdeUtils.getSessionFactory().openSession();
            session.beginTransaction();
            FacesContextUtil.setRequestSession(session);            
        }
    }
    
    //Après la phase
    @Override
    public void afterPhase(PhaseEvent fase) {
        System.out.println("Après la phase: "+ fase.getPhaseId());
        if (fase.getPhaseId().equals(PhaseId.RENDER_RESPONSE)) {
            Session session = FacesContextUtil.getRequestSession();
            try {
                session.getTransaction().commit();
            } catch (Exception e) {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
            } finally{
               session.close();
//                Connection close = session.close();
            }
        }
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }
}
