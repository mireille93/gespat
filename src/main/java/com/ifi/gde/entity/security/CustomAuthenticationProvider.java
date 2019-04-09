/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifi.gde.entity.security;

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

/**
 *
 * @author User
 */
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("Face Login est: " + authentication.getName());
        List<GrantedAuthority> auth = new ArrayList<>();
        //Use donc si on utilise le Spring Security 3.0.5.RELEASE
        auth.add(new GrantedAuthorityImpl("ROLE_USER"));
        //Déjà dans la version 3.1.3.RELEASE cette classe a été dépréciée et vous utilisez comme dans l'extrait ci-dessous 
        //auth.add(new SimpleGrantedAuthority("ROLE_USER"));
        if (authentication.getName().equals(authentication.getCredentials())) {
            return new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials(), auth);
        } else {
            return null;
        }

    }

    @Override
    public boolean supports(Class<? extends Object> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
