/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifi.gde.base.dao;

import static com.ifi.gde.entity.util.HibernateIfiGdeUtils.getSessionFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
//import org.springframework.stereotype.Repository;

/**
 *
 * @author User
 * @param <T>
 */
//@Repository
public class HibernateDAO<T> implements InterfaceDAO<T>, Serializable {
       

    private static final long serialVersionUID = 1L;
    
    private Class<T> classe;
    private Session session ;

    public HibernateDAO(Class<T> classe, Session session) {
        super();
        this.classe = classe;
        this.session = session;
    }
    
    
    @Override
    public void save(T entity) {
//        session.flush();
        session.save(entity);
        session.evict(entity);
        session.clear();
    }

    @Override
    public void update(T entity) {

        session.merge(entity);

    }

    

    @Override
    public void remove(T entity) {
        session.delete(entity);
    }

    @Override
    public void merge(T entity) {
        session.merge(entity);
    }

    @Override
    public T getEntity(Serializable id) {
        T entity = (T)session.get(classe, id);
        return entity;
    }

    @Override
    public T getEntityByDetachedCriteria(DetachedCriteria criteria) {
        T entity = (T)criteria.getExecutableCriteria(session).uniqueResult();
        return entity;
    }

        
    @Override
    public T getEntityByHQLQuery(String stringQuery) {
        org.hibernate.Query query = session.createQuery(stringQuery);        
        return (T) query.uniqueResult();
    }

    @Override
    public List<T> getListByDetachedCriteria(DetachedCriteria criteria) {
        return criteria.getExecutableCriteria(session).list();
    }
    
    @Override
    public List<T> getEntities() {
        List<T> enties = (List<T>) session.createCriteria(classe).list();
        return enties;
    }    
    
    @Override
    public void saveFile(InputStream inputStream, File file) throws FileNotFoundException, IOException{
        OutputStream output = new FileOutputStream(file);
        IOUtils.copy(inputStream, output);
    }
}
    
