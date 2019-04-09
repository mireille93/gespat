/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifi.gde.entity.security;

import com.ifi.gde.base.dao.InterfaceDAO;
import com.ifi.gde.base.dao.HibernateDAO;
import com.ifi.gde.entity.entities.Utilisateur;
import com.ifi.gde.entity.util.FacesContextUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service("ifiGdeUserService")
public class UserServiceImpl implements UserDetailsService, Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username != null && username.isEmpty()) {
            throw new UsernameNotFoundException("Utilisateur introuvable!");
        } else {
            try {
                Utilisateur utilisateur = findUser(username);
                
                String login = utilisateur.getLogin();
                String password = utilisateur.getPassword();
                boolean enabled = true; //userBean.isActive()
                boolean accountNonExpired = true;//userBean.isActive()
                boolean credentialsNonExpired = true; //userBean.isActive()
                boolean accountNonLocked = true; //userBean.isActive()

                Collection<GrantedAuthority> authorities = new ArrayList<>();
                //A utiliser si on utilise Spring Security 3.0.5.RELEASE
                authorities.add(new GrantedAuthorityImpl(utilisateur.getPermission()));
                //Déjà dans la version 3.1.3.RELEASE cette classe a été dépréciée et vous utilisez comme dans l'extrait ci-dessous 
                //authorities.add(new SimpleGrantedAuthority(usuario.getPermissao()));
                User user = new User(
                        login,
                        password,
                        enabled,
                        accountNonExpired,
                        credentialsNonExpired,
                        accountNonLocked,
                        authorities);
                return user;
            } catch (Exception e) {
                return null;
            }
        }

    }

    public Utilisateur findUser(String login){
        String stringQuery = "from Utilisateur utilisateur where utilisateur.login = "+ login;
//        return pessoaDAO().getEntityByHQLQuery(stringQuery);
        Session session = FacesContextUtil.getRequestSession();
        Query query = session.createQuery(stringQuery);
        //query.setString(0, login);
        return (Utilisateur) query.uniqueResult();
    }
    
    private InterfaceDAO<Utilisateur> utilisateurDAO(){
        
        InterfaceDAO<Utilisateur> utilisateurDAO = new HibernateDAO<>(Utilisateur.class, FacesContextUtil.getRequestSession());
        return utilisateurDAO;
    }
}