/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifi.gde.entity.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.annotations.ForeignKey;

/**
 *
 * @author User
 */
@Entity
@Table(name="dossier")
@Inheritance(strategy=InheritanceType.JOINED)

public class Dossier implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idossier", nullable = false)
    private Integer idossier;
    @Size(max = 255)
    @Column(name = "descriptiondossier")
    private String descriptiondossier;
    @Size(max = 255)
    @Column(name = "nivodossier")
    private String nivodossier;
    @ManyToOne(optional=false, fetch = FetchType.LAZY)
    @ForeignKey(name = "dosmedecin") 
    @JoinColumn(name="idmedecin", referencedColumnName = "idmedecin")
    private Medecin dosmedecin;
    @ManyToOne(optional=false, fetch = FetchType.LAZY)
    @ForeignKey(name = "dospatient") 
    @JoinColumn(name="idpatient", referencedColumnName = "idpatient")
    private Patient dospatient;

    public Dossier() {
        this.dosmedecin = new Medecin();
        this.dospatient = new Patient();
    }

    public Dossier(Integer idossier) {
        this.idossier = idossier;
    }

    public Integer getIdossier() {
        return idossier;
    }

    public void setIdossier(Integer idossier) {
        this.idossier = idossier;
    }

    public String getDescriptiondossier() {
        return descriptiondossier;
    }

    public void setDescriptiondossier(String descriptiondossier) {
        this.descriptiondossier = descriptiondossier;
    }

    public String getNivodossier() {
        return nivodossier;
    }

    public void setNivodossier(String nivodossier) {
        this.nivodossier = nivodossier;
    }

    public Medecin getDosmedecin() {
        return dosmedecin;
    }

    public void setDosmedecin(Medecin dosmedecin) {
        this.dosmedecin = dosmedecin;
    }

    public Patient getDospatient() {
        return dospatient;
    }

    public void setDospatient(Patient dospatient) {
        this.dospatient = dospatient;
    }

    @Override
    public String toString() {
        return "Dossier{" + "idossier=" + idossier + ", descriptiondossier=" + descriptiondossier +
                ", nivodossier=" + nivodossier +'}';
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.idossier);
        hash = 11 * hash + Objects.hashCode(this.descriptiondossier);
        hash = 11 * hash + Objects.hashCode(this.nivodossier);
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
        final Dossier other = (Dossier) obj;
        return Objects.equals(this.idossier, other.idossier);
    }

}
