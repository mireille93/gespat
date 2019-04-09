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
@Table(name="region")
@Inheritance(strategy=InheritanceType.JOINED)
public class Region implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idregion", nullable = false)
    private Integer idregion;
    @Size(max = 255)
    @Column(name = "nomregion")
    private String nomregion;
    @Size(max = 255)
    @Column(name = "paysregion")
    private String paysregion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patientregion")
    private List<Patient> patientList;

    public Region() {
    }

    public Region(Integer idregion) {
        this.idregion = idregion;
    }

    public Integer getIdregion() {
        return idregion;
    }

    public void setIdregion(Integer idregion) {
        this.idregion = idregion;
    }

    public String getNomregion() {
        return nomregion;
    }

    public void setNomregion(String nomregion) {
        this.nomregion = nomregion;
    }

    public String getPaysregion() {
        return paysregion;
    }

    public void setPaysregion(String paysregion) {
        this.paysregion = paysregion;
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
        return String.format("%s[id=%d]", getClass().getSimpleName(), getIdregion());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.idregion);
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
        final Region other = (Region) obj;
        if (!Objects.equals(this.idregion, other.idregion)) {
            return false;
        }
        return true;
    }
    
}
