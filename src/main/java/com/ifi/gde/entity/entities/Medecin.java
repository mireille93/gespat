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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author User
 */
@Entity
@Table(name="medecin")
@PrimaryKeyJoinColumn(name="idmedecin", referencedColumnName="utilisateurid")
public class Medecin extends Utilisateur implements Serializable {


    @Column(name="centremedecin", nullable= false, length=30)
    private String centremedecin;
    @Column(name="codemedecin", nullable= false, length=30)
    private String codemedecin;
   
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dosmedecin")
    private List<Dossier> dossierList;
    
    
    public Medecin() {
    }

    public Medecin(String centremedecin, String codemedecin) {
        this.centremedecin = centremedecin;
        this.codemedecin = codemedecin;
       
    }

    public String getCentremedecin() {
        return centremedecin;
    }

    public void setCentremedecin(String centremedecin) {
        this.centremedecin = centremedecin;
    }
    
    public String getCodemedecin() {
        return codemedecin;
    }

    public void setCodemedecin(String codemedecin) {
        this.codemedecin = codemedecin;
    }

    @XmlTransient
    public List<Dossier> getDossierList() {
        return dossierList;
    }

    public void setDossierList(List<Dossier> dossierList) {
        this.dossierList = dossierList;
    }
      @Override
    public String toString() {
              return "Medecin{" + "centremedecin=" + centremedecin + ", codemedecin=" + codemedecin + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.centremedecin);
        hash = 67 * hash + Objects.hashCode(this.codemedecin);
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
        final Medecin other = (Medecin) obj;
        if (!Objects.equals(this.codemedecin, other.codemedecin)) {
            return false;
        }
        return true;
    }
}
