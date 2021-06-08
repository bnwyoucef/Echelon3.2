/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projet.ClassJava;

import java.sql.Date;

/**
 *
 * @author idwar
 */
public class echelon {
    private int Id;
    private int num_echelon;
    private Date date_echelon;    
    
    public echelon(int Id, int num_echelon, Date date_echelon) {
        this.Id = Id;
        this.num_echelon = num_echelon;
        this.date_echelon = date_echelon;
    }

    public int getId() {
        return Id;
    }

    public int getNum_echelon() {
        return num_echelon;
    }

    public Date getDate_echelon() {
        return date_echelon;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setNum_echelon(int num_echelon) {
        this.num_echelon = num_echelon;
    }

    public void setDate_echelon(Date date_echelon) {
        this.date_echelon = date_echelon;
    }
    
}
