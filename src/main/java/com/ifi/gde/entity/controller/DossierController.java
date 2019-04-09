/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifi.gde.entity.controller;

import com.ifi.gde.base.dao.HibernateDAO;
import com.ifi.gde.base.dao.InterfaceDAO;
import com.ifi.gde.entity.entities.Dossier;
import com.ifi.gde.entity.entities.Patient;
import com.ifi.gde.entity.entities.SendEmail;
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
@ManagedBean(name = "dossierController")
@RequestScoped
public class DossierController implements Serializable {

    private static final long serialVersionUID = 1L;
    private SendEmail sendEmail;
    private Patient patient;
    private Dossier dossier;
    private List<Dossier> dossierList;

    
    @PostConstruct
    public void init() {
       dossier = new Dossier();
       patient = new Patient();
       sendEmail = new SendEmail();
        dossierList = getDossierList();
}

    private InterfaceDAO<Dossier> dossierDAO() {
        InterfaceDAO<Dossier> dossierDAO = new HibernateDAO<>(Dossier.class,
                FacesContextUtil.getRequestSession());
        return dossierDAO;
    }
    private InterfaceDAO<Patient> patientDAO() {
        InterfaceDAO<Patient> patientDAO = new HibernateDAO<>(Patient.class, FacesContextUtil.getRequestSession());
        return patientDAO;
    }

    public String clearDossier() {
        dossier = new Dossier();
        return editDossier();
    }

    public String editDossier() {
        return "/restrict/ajouterDossier.faces";
    }

    public String addDossier() {
        if (dossier.getIdossier()== null || dossier.getIdossier() == 0) {
            insertDossier();
        } else {
            updateDossier();
        }
        clearDossier();
         return null;
    }

    private void insertDossier() {
        Patient p=dossier.getDospatient();
   
        dossierDAO().save(dossier);
        
        // ajout de code eric
        String mail = patientDAO().getEntity(p.getUtilisateurid()).getEmail();        
        SendEmail.sendEmail(mail, "Nous vous souhaitons un bon retablissement...\n "
                + "Pour le suivi de votre etat de santé un dossier médical est créé.\n"
                + " Ce dossier est cree et est en Etat... \n:  "+this.dossier.getNivodossier()
                + " Voici le commentaire que votre medecin a fait sur votre dossier medical... :\n:  "+this.dossier.getDescriptiondossier());
          
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Enregistré avec succès", ""));
    }

    private void updateDossier() {
        Patient p=dossier.getDospatient();
   
        dossierDAO().update(dossier);
        
        // ajout de code eric
        String mail = patientDAO().getEntity(p.getUtilisateurid()).getEmail();        
        SendEmail.sendEmail(mail, "Nous vous souhaitons un bon retablissement...\n"
                + " Votre dossier medical est modifie et est en Etat... \n:  "+this.dossier.getNivodossier()
        + " Voici le commentaire que votre medecin a fait sur votre dossier medical... :\n:  "+this.dossier.getDescriptiondossier());
        
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Mise à jour effectuée avec succès", ""));
    }

    public String deleteDossier() {
        dossierDAO().remove(dossier);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Enregistrement supprimé avec succès", ""));
        return null;
    }

    public Dossier getDossier() {
        return dossier;
    }

    public void setDossier(Dossier dossier) {
        this.dossier = dossier;
    }
     public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
  

    public List<Dossier> getDossierList() {
        dossierList = dossierDAO().getEntities();
        return dossierList;
    }

    public void setDossierList(List<Dossier> dossierList) {
        this.dossierList = dossierList;
    }


}

