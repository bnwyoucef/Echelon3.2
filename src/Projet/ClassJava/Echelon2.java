/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projet.ClassJava;

import java.sql.SQLException;
import javafx.scene.control.TextField;

/**
 *
 * @author BOUNOUA
 */
public class Echelon2 {
    
      private int numEchelon;

    public void miseAjourEchelon(TextField date, TextField id,TextField nom,TextField prenom) throws SQLException{
        numEchelon++;
        DbConnection conn = new DbConnection();
        conn.miseHistorique(id, nom, prenom, numEchelon, date);
    }
}
