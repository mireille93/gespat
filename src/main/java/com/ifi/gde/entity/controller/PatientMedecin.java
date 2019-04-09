package com.ifi.gde.entity.controller;

import com.ifi.gde.base.dao.HibernateDAO;
import com.ifi.gde.base.dao.InterfaceDAO;
import com.ifi.gde.entity.entities.Patient;
import com.ifi.gde.entity.util.FacesContextUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="patientmedecin")
@ViewScoped
public class PatientMedecin implements Serializable {
     
    private List<Patient> patients;
     private Patient patient;
    private List<Patient> filteredPatients;
     
    @PostConstruct
    public void init() {
        patient = new Patient();
        patients = getPatients();
        
    }
    @ManagedProperty("#{patient}")
    private Patient service;
    
    private InterfaceDAO<Patient> patientDAO() {
        InterfaceDAO<Patient> patientDAO = new HibernateDAO<>(Patient.class,
                FacesContextUtil.getRequestSession());
        return patientDAO;
    }

    public String clearPasMed() {
        patient = new Patient();
        return editPatient();
    }

    public String editPatient() {
        return "/restrict/patmed.faces";
    }
    public String getNom(){
        return service.getNom();
    }
    
    public String getPrenom(){
        return service.getPrenom();
    }
    public String getTelephone(){
        return service.getTelephone();
    }
     
    public String getCodepatient() {
        return service.getCodepatient();
    } 
    public String getDosmedecin() {
        return service.getPatientmaladie().getNomaladie();
    }
    public String getPatientregion() {
        return service.getPatientregion().getNomregion();
    }
     
    public List<Patient> getPatients() {
          patients = patientDAO().getEntities();
        return patients;
    }
 
    public List<Patient> getFilteredPatients() {
        return filteredPatients;
    }
 
    public void setFilteredPatients(List<Patient> filteredPatients) {
        this.filteredPatients = filteredPatients;
    }
 
    public void setService(Patient service) {
        this.service = service;
    }
}