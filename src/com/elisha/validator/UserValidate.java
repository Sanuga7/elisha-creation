/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.elisha.validator;

/**
 *
 * @author Sanuga
 */
public class UserValidate {
    
    public static boolean isEmailValid(String email){

           if(email.matches("^[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")){
             return true;
           }else{
             return false;
           }
    }  
    
    public static boolean isPwdValid(String pwd){
    
        if(pwd == null){
          return false;
        }
        
        if(pwd.length() <= 20){
            return true;
        }
    
        return false;
    }
}
