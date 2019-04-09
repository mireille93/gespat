/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifi.gde.entity.security;

import com.ifi.gde.entity.entities.Utilisateur;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author User
 */

@Service("assembler")
public class Assembler {

    public Assembler() {
    }

    @Transactional(readOnly = true)
    User buildUserFromUserEntity(Utilisateur utilisateur) {

        String username = utilisateur.getLogin();
        String password = utilisateur.getPassword();
        boolean enabled = true; //userBean.isActive()
        boolean accountNonExpired = true;//userBean.isActive()
        boolean credentialsNonExpired = true; //userBean.isActive()
        boolean accountNonLocked = true; //userBean.isActive()

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new GrantedAuthorityImpl(utilisateur.getPermission()));      

        User user = new User(
                username,
                password,
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                authorities);
        return user;
    }
}

