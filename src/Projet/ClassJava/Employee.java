/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projet.ClassJava;

import java.sql.Date;

/**
 *
 * @author BOUNOUA
 */
public class Employee {
           private int id;
    private String nom;
    private String prénom;
    private String Grad;
    private Date dernier_echelon;
    private int historique;//numéro d'echelon;

    public Employee(int id, String nom, String prénom, Date dernier_echelon, int historique) {
        this.id = id;
        this.nom = nom;
        this.prénom = prénom;
        this.dernier_echelon = dernier_echelon;
        this.historique = historique;
    }
    

    public Employee(int id, String nom, String prénom, String Grad, Date dernier_echelon) {
        this.id = id;
        this.nom = nom;
        this.prénom = prénom;
        this.Grad = Grad;
        this.dernier_echelon = dernier_echelon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrénom() {
        return prénom;
    }

    public void setPrénom(String prénom) {
        this.prénom = prénom;
    }

    public String getGrad() {
        return Grad;
    }

    public void setGrad(String Grad) {
        this.Grad = Grad;
    }

    public Date getDernier_echelon() {
        return dernier_echelon;
    }

    public void setDernier_echelon(Date dernier_echelon) {
        this.dernier_echelon = dernier_echelon;
    }

    public int getHistorique() {
        return historique;
    }

    public void setHistorique(int historique) {
        this.historique = historique;
    }
    
    
}
