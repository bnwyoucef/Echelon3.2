/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projet.ClassJava;

import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;

/**
 *
 * @author BOUNOUA
 */
public class FxmlLoader {
        private Pane view;
    
    public Pane getPage(String PageName){
        
        try{
            URL fileUrl = EchelonFinal.class.getResource("/Projet/FXMLFile/" + PageName + ".fxml");
          
            if(fileUrl == null){
                throw new java.io.FileNotFoundException("FXML file can't be found");
            }
            
            new FXMLLoader();
			view = FXMLLoader.load(fileUrl);
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "*_*" +ex);
        }
        return view;
    }
}
