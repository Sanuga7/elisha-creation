/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.elisha.optionpane;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author Sanuga
 */
public class Message {
    
    public static void showError(Component parent,String e){
        JOptionPane.showMessageDialog(parent, e, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void showSucess(Component parent,String e){
        JOptionPane.showMessageDialog(parent, e, "Sucess", JOptionPane.INFORMATION_MESSAGE);
    }
}
