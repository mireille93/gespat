/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifi.gde.entity.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author User
 */
@Entity
@Table(name="maladie")
@Inheritance(strategy=InheritanceType.JOINED)
public class Maladie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idmaladie", nullable = false)
    private Integer idmaladie;
    @Size(max = 255)
    @Column(name = "descriptionmaladie")
    private String descriptionmaladie;
    @Size(max = 255)
    @Column(name = "nomaladie")
    private String nomaladie;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patientmaladie")
    private List<Patient> patientList;

    public Maladie() {
    }

    public Maladie(Integer idmaladie) {
        this.idmaladie = idmaladie;
    }

    public Integer getIdmaladie() {
        return idmaladie;
    }

    public void setIdmaladie(Integer idmaladie) {
        this.idmaladie = idmaladie;
    }

    public String getDescriptionmaladie() {
        return descriptionmaladie;
    }

    public void setDescriptionmaladie(String descriptionmaladie) {
        this.descriptionmaladie = descriptionmaladie;
    }

    public String getNomaladie() {
        return nomaladie;
    }

    public void setNomaladie(String nomaladie) {
        this.nomaladie = nomaladie;
    }

    @XmlTransient
    public List<Patient> getPatientList() {
        return patientList;
    }

    public void setPatientList(List<Patient> patientList) {
        this.patientList = patientList;
    }

    @Override
    public String toString() {
        return "Maladie{" + "idmaladie=" + idmaladie + ", nomaladie=" + nomaladie +
                ", descriptionmaladie=" + descriptionmaladie +'}';
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.idmaladie);
        hash = 11 * hash + Objects.hashCode(this.nomaladie);
        hash = 11 * hash + Objects.hashCode(this.descriptionmaladie);
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
        final Maladie other = (Maladie) obj;
        return Objects.equals(this.idmaladie, other.idmaladie);
    }
    
}
