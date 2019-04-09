/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifi.gde.base.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
//import javax.persistence.EntityManager;

/**
 *
 * @author User
 * @param <T>
 */
public interface InterfaceDAO<T> {
    
    void save (T entity);
    void update (T entity);
    void remove (T entity);
    void merge (T entity);
    T getEntity(Serializable id);
    T getEntityByDetachedCriteria(DetachedCriteria criteria);
    T getEntityByHQLQuery(String stringQuery);
    List<T> getEntities();
    List<T> getListByDetachedCriteria(DetachedCriteria criteria);   
    public void saveFile(InputStream inputStream, File file) throws FileNotFoundException, IOException;
    
}

