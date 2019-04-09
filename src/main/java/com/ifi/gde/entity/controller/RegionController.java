/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package com.ifi.gde.entity.controller;

import com.ifi.gde.base.dao.HibernateDAO;
import com.ifi.gde.base.dao.InterfaceDAO;
import com.ifi.gde.entity.entities.Region;
import com.ifi.gde.entity.util.FacesContextUtil;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author User
 */
@ManagedBean(name = "regionController")
@RequestScoped
public class RegionController implements Serializable {

    private static final long serialVersionUID = 1L;

    private Region region;
    private List<Region> regionList;

    @PostConstruct
    public void init() {
        region = new Region();
        regionList = getRegionList();

    }

    private InterfaceDAO<Region> regionDAO() {
        InterfaceDAO<Region> regionDAO = new HibernateDAO<>(Region.class,
                FacesContextUtil.getRequestSession());
        return regionDAO;
    }



    public String clearRegion() {
        region = new Region();
         return editRegion();
    }

    public String editRegion() {
        return "/restrict/ajouterRegion.faces";
    }

    public String addRegion() {
        if (region.getIdregion() == null || region.getIdregion() == 0) {
            insertRegion();
        } else {
            updateRegion();
        }
        clearRegion();
        return null;
    }

    private void insertRegion() {
        regionDAO().save(region);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Enregistré avec succès", ""));
    }

    private void updateRegion() {
        regionDAO().update(region);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Mise à jour effectuée avec succès", ""));
    }

    public String deleteRegion() {
        regionDAO().remove(region);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Enregistrement supprimé avec succès", ""));
        return null;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public List<Region> getRegionList() {
        regionList = regionDAO().getEntities();
        return regionList;
    }

    public void setRegionList(List<Region> regionList) {
        this.regionList = regionList;
    }


}

