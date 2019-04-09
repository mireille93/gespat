/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifi.gde.entity.controller;

import com.ifi.gde.base.dao.InterfaceDAO;
import com.ifi.gde.base.dao.HibernateDAO;
import com.ifi.gde.entity.converter.ConverterSHA1;
import com.ifi.gde.entity.entities.Medecin;
import com.ifi.gde.entity.entities.Patient;
import com.ifi.gde.entity.entities.SendEmail;
import com.ifi.gde.entity.entities.Utilisateur;
import com.ifi.gde.entity.util.FacesContextUtil;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


/**
 *
 * @author User
 */
@ManagedBean
@SessionScoped
public class UtilisateurController implements Serializable {

    private static final long serialVersionUID = 1L;
    private String pwdConverter;

    private Utilisateur utilisateur;
    private Medecin medecin;
    private Patient patient;
    private SendEmail sendEmail;
     
    private List<Medecin> medecinList;
    private List<Patient> patientList;
    private List<Utilisateur> utilisateurList;
    
    @PostConstruct
    public void init() {
        patient = new Patient();
         medecin = new Medecin();
         sendEmail = new SendEmail();
        utilisateur = new Utilisateur();
        
//        file= new UploadedFileWrapper(); disabled="true" 
        medecinList = getMedecinList();
        patientList = getPatientList();
        utilisateurList = getUtilisateurList();

    }

    private InterfaceDAO<Utilisateur> utilisateurDAO() {
        InterfaceDAO<Utilisateur> utilisateurDAO = new HibernateDAO<>(Utilisateur.class, FacesContextUtil.getRequestSession());
        return utilisateurDAO;
    }

    private InterfaceDAO<Medecin> medecinDAO() {
        InterfaceDAO<Medecin> medecinDAO = new HibernateDAO<>(Medecin.class, FacesContextUtil.getRequestSession());
        return medecinDAO;
    }
    private InterfaceDAO<Patient> patientDAO() {
        InterfaceDAO<Patient> patientDAO = new HibernateDAO<>(Patient.class, FacesContextUtil.getRequestSession());
        return patientDAO;
    }

    public String clearUtilisateur() {
        utilisateur = new Utilisateur();
        return editUtilisateur();
    }

    public String clearMedecin() {
        medecin = new Medecin();
        return editMedecin();
    }
    public String clearPatient() {
        patient = new Patient();
        return editPatient();
    }
  
    public String editUtilisateur() {
        return "/restrict/ajouterUtilisateur.faces";
    }

    public String editMedecin() {
        return "/restrict/ajouterMedecin.faces";
    }

    public String editPatient() {
        return "/restrict/ajouterPatient.faces";
    }

    public String addUtilisateur() {
        Date date = new Date();
        if (utilisateur.getUtilisateurid()== null || utilisateur.getUtilisateurid()== 0) {
            utilisateur.setDateenregistrement(date);
            insertUtilisateur();
        } else {
            updateUtilisateur();
        }
        clearUtilisateur();
        return null;
    }

    public String addMedecin() {
        Date date = new Date();
        if (medecin.getUtilisateurid()== null || medecin.getUtilisateurid()== 0) {
            medecin.setDateenregistrement(date);
            insertMedecin();
        } else {
            updateMedecin();
        }
        clearMedecin();
        return null;
    }
    public String addPatient() {
        Date date = new Date();
        if (patient.getUtilisateurid()== null || patient.getUtilisateurid()== 0) {
            patient.setDateenregistrement(date);
            insertPatient();
        } else {
            updatePatient();
        }
        clearPatient();
        return null;
    }
    

    private void insertUtilisateur() {
        utilisateur.setPassword(ConverterSHA1.cipher(utilisateur.getPassword()));
        if (utilisateur.getPassword() == null ? pwdConverter == null
                : utilisateur.getPassword().equals(ConverterSHA1.cipher(pwdConverter))) {
            utilisateur.setPermission("ROLE_ADMIN");
            utilisateurDAO().save(utilisateur);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Enregistré avec succès", ""));
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Les mots de passe ne correspondent pas.", ""));
        }
    }
    private void insertPatient() {
        String p=patient.getPassword();
        patient.setPassword(ConverterSHA1.cipher(patient.getPassword()));
        if (patient.getPassword() == null ? pwdConverter == null
                : patient.getPassword().equals(ConverterSHA1.cipher(pwdConverter))) {
            patient.setPermission("ROLE_USER");
            patientDAO().save(patient);
            SendEmail.sendEmail(patient.getEmail(), "Merci de vous etre fait enregistré...\n"
                    + " Vous devez continuer votre traitement...\n "
                    + "vos informations de connexon sont...\n: Nom  :" +this.patient.getNom()+ "\n    Prenom: "+this.patient.getPrenom()+
                    "\n login  :" +this.patient.getLogin()+ "\n    password: "+p);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Enregistré avec succès", ""));
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Les mots de passe ne correspondent pas.", ""));
        }
    }

    private void insertMedecin() {
        medecin.setPassword(ConverterSHA1.cipher(medecin.getPassword()));
        if (medecin.getPassword() == null ? pwdConverter == null
                : medecin.getPassword().equals(ConverterSHA1.cipher(pwdConverter))) {
            medecin.setPermission("ROLE_ADMIN");
            medecinDAO().save(medecin);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Enregistré avec succès", ""));
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Les mots de passe ne correspondent pas.", ""));
        }
    }

   

    private void updateMedecin() {
        medecinDAO().update(medecin);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Mise à jour effectuée avec succès", ""));
    }

    private void updatePatient() {
        patientDAO().update(patient);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Mise à jour effectuée avec succès", ""));
    }
    
    private void updateUtilisateur() {
        utilisateurDAO().update(utilisateur);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Mise à jour effectuée avec succès", ""));
    }


    public String deleteUtilisateur() {
        utilisateurDAO().remove(utilisateur);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Enregistrement supprimé avec succès", ""));
        return null;
    }

    public String deleteMedecin() {
        medecinDAO().remove(medecin);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Enregistrement supprimé avec succès", ""));
        return null;
    }

    public String deletePatient() {
        patientDAO().remove(patient);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Enregistrement supprimé avec succès", ""));
        return null;
    }

    
//    public void handleFileUpload(FileUploadEvent event) {
//        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
//        FacesContext.getCurrentInstance().addMessage(null, message);
//    }
   

    public String getPwdConverter() {
        return pwdConverter;
    }

    public void setPwdConverter(String pwdConverter) {
        this.pwdConverter = pwdConverter;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    public List<Medecin> getMedecinList() {
        medecinList = medecinDAO().getEntities();
        return medecinList;
    }

    public void setMedecinList(List<Medecin> medecinList) {
        this.medecinList = medecinList;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<Patient> getPatientList() {
        patientList = patientDAO().getEntities();
        return patientList;
    }

    public void setPatientList(List<Patient> patientList) {
        this.patientList = patientList;
    }
    
    public List<Utilisateur> getUtilisateurList() {
        utilisateurList = utilisateurDAO().getEntities();
        return utilisateurList;
    }

    public void setUtilisateurList(List<Utilisateur> utilisateurList) {
        this.utilisateurList = utilisateurList;
    }

   

}
