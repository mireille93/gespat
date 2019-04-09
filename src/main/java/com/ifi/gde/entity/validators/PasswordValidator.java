/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifi.gde.entity.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author dfkossi
 */
@FacesValidator(value="passwordValidator")
public class PasswordValidator implements Validator{
    int num = 0;
    int carac = 0;

    //Définit un tableau de caractères spéciaux
    char[] caractereSpeciaux={'=','|','!','@','#','$','%','^','&','*','(',')','{','}','[',']',';',':','.',',','<','>','?','~','+','-','_','\'','"'};
    
    @Override
    public void validate(FacesContext context,UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            return;
        }      
        
        String password = (String)value;
        
        /*Assemble un tableau de caractères avec le contenu du mot de passe
         * et faites-le défiler en comptant combien de chiffres le mot de passe a*/        
        for(int i=0;i<password.length();i++) {            
            if(password.charAt(i)>=48 && password.charAt(i)<=57) {
                num++;
            }
        }
        System.out.println("Nous avons trouvé des chiffres "+ num +" dans le mot de passe!");                                        
        
        /*Assemble un tableau de caractères avec le contenu du mot de passe
         * et le fait défiler en comptant combien de caractères spéciaux le mot de passe a*/        
        for(int i=0;i<password.length();i++) {
            for(int j=0;j<caractereSpeciaux.length;j++) {
                if(password.charAt(i)==caractereSpeciaux[j]) {
                    carac++;
                }
            }
        }
        System.out.println("Nous avons trouvé "+carac+" caractères spéciaux dans le mot de passe!");
        
        //Vérifie si le mot de passe n'est pas vide
        if(password == null || password.equals("")){
             throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                "Le mot de passe ne peut pas être nul!", ""));             
             
        //Vérifie si au moins un chiffre et un caractère spécial ont été trouvés
        } else if(!(carac > 0)|| !(num > 0)){            
             throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Le mot de passe doit contenir des chiffres, des caractères spéciaux et des lettres!", ""));
             
        //Vérifie si le mot de passe comporte au moins 7 caractères et un maximum de 22 caractères
        } else if(password.length() < 7){
             throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Le mot de passe doit comporter au moins 7 caractères!", ""));
        } else if(password.length() >= 22){
             throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Le mot de passe ne peut avoir au maximum que 22 caractères !!!", ""));
        } 
    }
}
