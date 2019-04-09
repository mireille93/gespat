/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifi.gde.entity.controller;

import com.ifi.gde.base.dao.HibernateDAO;
import com.ifi.gde.base.dao.InterfaceDAO;
import com.ifi.gde.entity.entities.Maladie;
import com.ifi.gde.entity.util.FacesContextUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author User
 */
@ManagedBean(name = "maladieController")
@RequestScoped
public class MaladieController implements Serializable {

    private static final long serialVersionUID = 1L;
    private Maladie maladie = new Maladie();;
    private List<Maladie> maladieList = new ArrayList<>();


    public MaladieController() {
    }

    private InterfaceDAO<Maladie> maladieDAO() {
        InterfaceDAO<Maladie> maladieDAO = new HibernateDAO<>(Maladie.class, FacesContextUtil.getRequestSession());
        return maladieDAO;
    }

    public String clearMaladie() {
        maladie = new Maladie();
        return editMaladie();
    }

    public String editMaladie() {
        return "/restrict/ajouterMaladie.faces";
    }

    public String addMaladie() {
        if (maladie.getIdmaladie()== null || maladie.getIdmaladie()== 0) {
            insertMaladie();
        } else {
            updateMaladie();
        }
        clearMaladie();
        return null;
    }

    private void insertMaladie() {
        maladieDAO().save(maladie);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Enregistré avec succès", ""));
    }

    private void updateMaladie() {
        maladieDAO().update(maladie);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Mise à jour effectuée avec succès", ""));
    }

    public String deleteMaladie() {
        maladieDAO().remove(maladie);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Enregistrement supprimé avec succès", ""));
        return null;
    }

    public Maladie getMaladie() {
        return maladie;
    }

    public void setMaladie(Maladie maladie) {
        this.maladie = maladie;
    }

    public List<Maladie> getMaladieList() {
        maladieList = maladieDAO().getEntities();
        return maladieList;
    }

    public void setMaladieList(List<Maladie> maladieList) {
        this.maladieList = maladieList;
    }
    
  
   
     

}
