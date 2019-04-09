/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifi.gde.entity.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author User
 */
@Entity
@Table(name="utilisateur")
@Inheritance(strategy=InheritanceType.JOINED)
public class Utilisateur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "utilisateurid", nullable = false)
    private Integer utilisateurid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateenregistrement")
    @Temporal(TemporalType.DATE)
    private Date dateenregistrement;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "login")
    private String login;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nom")
    private String nom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "permission")
    private String permission;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "prenom")
    private String prenom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "telephone")
    private String telephone;

    public Utilisateur() {
    }

    public Utilisateur(Integer utilisateurid) {
        this.utilisateurid = utilisateurid;
    }

    public Utilisateur(Integer utilisateurid, Date dateenregistrement, String email, String login, String nom, String password, String permission, String prenom, String telephone) {
        this.utilisateurid = utilisateurid;
        this.dateenregistrement = dateenregistrement;
        this.email = email;
        this.login = login;
        this.nom = nom;
        this.password = password;
        this.permission = permission;
        this.prenom = prenom;
        this.telephone = telephone;
    }

    public Integer getUtilisateurid() {
        return utilisateurid;
    }

    public void setUtilisateurid(Integer utilisateurid) {
        this.utilisateurid = utilisateurid;
    }

    public Date getDateenregistrement() {
        return dateenregistrement;
    }

    public void setDateenregistrement(Date dateenregistrement) {
        this.dateenregistrement = dateenregistrement;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "utilisateurid=" + utilisateurid + ", nom=" + nom +
                ", prenom=" + prenom + ", telephone=" +
                telephone + ", dateenregistrement=" + 
                dateenregistrement + ", email=" + email +
                ", login=" + login + ", password=" + password + ", permission=" + permission + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.utilisateurid);
        hash = 67 * hash + Objects.hashCode(this.nom);
        hash = 67 * hash + Objects.hashCode(this.prenom);
        hash = 67 * hash + Objects.hashCode(this.telephone);
        hash = 67 * hash + Objects.hashCode(this.dateenregistrement);
        hash = 67 * hash + Objects.hashCode(this.email);
        hash = 67 * hash + Objects.hashCode(this.login);
        hash = 67 * hash + Objects.hashCode(this.password);
        hash = 67 * hash + Objects.hashCode(this.permission);
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
        final Utilisateur other = (Utilisateur) obj;
        if (!Objects.equals(this.utilisateurid, other.utilisateurid)) {
            return false;
        }
        return true;
    }
    
}
