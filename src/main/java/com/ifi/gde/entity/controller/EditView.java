/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifi.gde.entity.controller;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author dfkossi
 */
@ManagedBean(name="dtEditView")
@ViewScoped
public class EditView implements Serializable {
    

    
    
    @PostConstruct
    public void init() {
    }
    
}
