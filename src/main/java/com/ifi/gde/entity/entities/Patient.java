/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifi.gde.entity.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.ForeignKey;

/**
 *
 * @author User
 */
@Entity
@Table(name="patient")
@PrimaryKeyJoinColumn(name="idpatient", referencedColumnName="utilisateurid")
public class Patient extends Utilisateur implements Serializable {

    private static final long serialVersionUID = 1L;
  
    @Column(name="codepatient", nullable= false, length=30)
    private String codepatient;
    
    @Column(name="sexepatient", nullable= false, length=10)
    private String sexepatient;
    
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @ForeignKey(name = "patientmaladie")
    @JoinColumn(name = "idmaladie", referencedColumnName = "idmaladie")
    private Maladie patientmaladie;
    @ManyToOne(optional=false, fetch = FetchType.LAZY)
    @ForeignKey(name = "patientregion") 
    @JoinColumn(name="idregion", referencedColumnName = "idregion")
    private Region patientregion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dospatient")
    private List<Dossier> dossierList;

    public Patient() {
        this.patientmaladie = new Maladie();
        this.patientregion = new Region();
    }

    public String getSexepatient() {
        return sexepatient;
    }

    public void setSexepatient(String sexepatient) {
        this.sexepatient = sexepatient;
    }

    public Maladie getPatientmaladie() {
        return patientmaladie;
    }

    public void setPatientmaladie(Maladie patientmaladie) {
        this.patientmaladie = patientmaladie;
    }

    public Region getPatientregion() {
        return patientregion;
    }

    public void setPatientregion(Region patientregion) {
        this.patientregion = patientregion;
    }

    public Patient(String codepatient, String sexepatient) {
        //this.idpatient = idpatient;
        this.codepatient = codepatient;
        this.sexepatient = sexepatient;
    }
   
    public String getCodepatient() {
        return codepatient;
    }

    public void setCodepatient(String codepatient) {
        this.codepatient = codepatient;
    }

    @Override
    public String toString() {
         return "Patient{" + "codepatient=" + codepatient + ", sexepatient=" + sexepatient + '}';
    }
    
    @XmlTransient
    public List<Dossier> getDossierList() {
        return dossierList;
    }

    public void setDossierList(List<Dossier> dossierList) {
        this.dossierList = dossierList;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.sexepatient);
        hash = 67 * hash + Objects.hashCode(this.codepatient);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Patient other = (Patient) obj;
        if (!Objects.equals(this.codepatient, other.codepatient)) {
            return false;
        }
        return true;
    }
    
}
