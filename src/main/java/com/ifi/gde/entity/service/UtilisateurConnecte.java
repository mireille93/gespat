/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifi.gde.entity.service;

import com.ifi.gde.entity.entities.Utilisateur;
import com.ifi.gde.entity.util.FacesContextUtil;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author dfkossi
 */
@ManagedBean(name = "utilisateurConnecte")
@SessionScoped
public class UtilisateurConnecte implements Serializable {

    private static final long serialVersionUID = 1L;
    private String login;
    SecurityContext context = SecurityContextHolder.getContext();

    public UtilisateurConnecte() {
    }
    
    public Utilisateur procurerUtilisateur(){
        if (context instanceof SecurityContext) {
            Authentication authentication = context.getAuthentication();
            if (authentication instanceof Authentication) {
                login = (((User) authentication.getPrincipal()).getUsername());
            }
        }
        Session session = FacesContextUtil.getRequestSession();
        Query query = session.createQuery("from Utilisateur user where user.login like ?");
        query.setString(0, login);
        return (Utilisateur) query.uniqueResult();
    }
    
}
