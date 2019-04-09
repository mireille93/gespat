package com.ifi.gde.entity.controller;

import com.ifi.gde.base.dao.HibernateDAO;
import com.ifi.gde.base.dao.InterfaceDAO;
import com.ifi.gde.entity.entities.Dossier;
import com.ifi.gde.entity.util.FacesContextUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="dtFilterView")
@ViewScoped
public class FilterView implements Serializable {
     
    private List<Dossier> dossiers;
     private Dossier dossier;
    private List<Dossier> filteredDossiers;
     
    @PostConstruct
    public void init() {
        dossier = new Dossier();
        dossiers = getDossiers();
        
    }
    @ManagedProperty("#{dossier}")
    private Dossier service;
    
    private InterfaceDAO<Dossier> dossierDAO() {
        InterfaceDAO<Dossier> dossierDAO = new HibernateDAO<>(Dossier.class,
                FacesContextUtil.getRequestSession());
        return dossierDAO;
    }

    public String clearDos() {
        dossier = new Dossier();
        return editDossier();
    }

    public String editDossier() {
        return "/restrict/nivodossier.faces";
    }
    public String getNivodossier(){
        return service.getNivodossier();
    }
     
    public String getDescriptiondossier() {
        return service.getDescriptiondossier();
    }
    public String getDospatient(){
        return service.getDospatient().getNom();
    }
     
    public String getDosmedecin() {
        return service.getDosmedecin().getNom();
    }
     
    public List<Dossier> getDossiers() {
          dossiers = dossierDAO().getEntities();
        return dossiers;
    }
 
    public List<Dossier> getFilteredDossiers() {
        return filteredDossiers;
    }
 
    public void setFilteredDossiers(List<Dossier> filteredDossiers) {
        this.filteredDossiers = filteredDossiers;
    }
 
    public void setService(Dossier service) {
        this.service = service;
    }
}