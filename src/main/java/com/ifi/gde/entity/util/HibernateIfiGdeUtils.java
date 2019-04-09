/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifi.gde.entity.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 *
 * @author User
 */
public class HibernateIfiGdeUtils {
    
    
    private static final SessionFactory sessionFactory;
    public static final String HIBERNATE_SESSION = "hibernate_session";
    
    static{
        
        try {
            
            System.out.println("Essayer de configurer SF");
            
            Configuration configuration = new Configuration().configure();
            
            ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().
                applySettings(configuration.getProperties()).buildServiceRegistry();
            
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            
            System.out.println("Session factory a été créé correctement");
        } catch (Exception ex) {
            System.out.println("Une erreur est survenue lors du démarrage de SF. " + ex);
            throw new ExceptionInInitializerError(ex);
        }
        
        
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
