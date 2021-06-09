/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projet.ClassJava;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author idwar
 */
public class HistoriqueDeEchloController implements Initializable {
    private TableView<echelon> ModTableHistorique;
    @FXML
    private TextField ModNumEchelonTextField;
    
    private TextField ModDateEchelonTextField;
      @FXML
    private Button ModAjouterBtn;
    @FXML
    private Button ModModifierBtn;
    @FXML
    private Button ModSuprimerBtn;
   
    private connect_p2 conn = new connect_p2();
    @FXML
    private TableView<echelon> table_h;
    @FXML
    private TableColumn<echelon, Integer> num;
    @FXML
    private TableColumn<echelon, Date> ladate;
    @FXML
    private DatePicker selectdate;
    @FXML
    private Button printbtn;
    public String nom,prenom;
    
    @FXML
    private Label putnom;
    @FXML
    private Label putprenom;
    int Id=passer.data;
   
     private FxmlLoader loader = new FxmlLoader();
    @FXML
    private AnchorPane scene01;
    @FXML
    private Button reture;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        try {
            conn.showechelons(num, ladate, table_h, Id);
             putnom.setText(conn.getnom(Id));
           
           putprenom.setText(conn.getprenom(Id));
        } catch (SQLException ex) {
            Logger.getLogger(HistoriqueDeEchloController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       }     

       @FXML
    private void reture(ActionEvent event) {
     AnchorPane pane =  (AnchorPane) loader.getPage("recharche");
       scene01.getChildren().setAll(pane);
    }

    @FXML
    void buttonsClicked(ActionEvent event) throws SQLException, ParseException {
        if(event.getSource().equals(ModAjouterBtn)){
    conn.InsertRecord(Id, ModNumEchelonTextField, selectdate);
    conn.showechelons(num, ladate, table_h, Id);
    
         
          
 }else if(event.getSource().equals(ModSuprimerBtn)){
           conn.DeleteRecord(Id);
           conn.showechelons(num, ladate, table_h, Id);
    }else if(event.getSource().equals(ModModifierBtn)){
           conn.UpdateRecord(Id, ModNumEchelonTextField, selectdate);
           conn.showechelons(num, ladate, table_h, Id);
    }
    }
   

    @FXML
    private void showinfo(MouseEvent event) {
         conn.MouseClicked(ModNumEchelonTextField,selectdate , table_h);
    }

    @FXML
   
    //******************************************************************************************
    private void printit(ActionEvent event) throws ClassNotFoundException, SQLException, FileNotFoundException, DocumentException, IOException {
        
          Connection con;
    PreparedStatement pst;
   ResultSet rs;
    
               
        
        Class.forName("com.mysql.jdbc.Driver");
              
              con=DriverManager.getConnection("jdbc:mysql://localhost/echelondb?UseUnicode=yes&characterEncoding=UTF-8","root","");
              pst=con.prepareStatement("SELECT * FROM `historique`  where Id = "+Id);                       
              rs=pst.executeQuery();

        Document doc=new Document();
 
      
      PdfWriter.getInstance(doc, new FileOutputStream("C:\\echelons\\echelons.pdf"));
      doc.open();
      
      
        nom = conn.getnom(Id);
        prenom = conn.getprenom(Id);
        boolean add = doc.add(new Paragraph(" "));
        doc.add(new Paragraph("esi sba"));
        doc.add(new Paragraph(" "));
         doc.add(new Paragraph("Nom : "+nom));
          doc.add(new Paragraph(" "));
        doc.add(new Paragraph("Prenom : "+prenom));
          doc.add(new Paragraph(" "));
          PdfPTable table=new PdfPTable(2);
        
        table.setWidthPercentage(100);
        
        PdfPCell cell;
        
        //////////////////////////////////////////////////////
        
      
        
        cell= new PdfPCell(new Phrase("num echelon",FontFactory.getFont("Ariel",11)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.WHITE);
        table.addCell(cell);
        
        
        cell= new PdfPCell(new Phrase("date echelon",FontFactory.getFont("Ariel",11)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.WHITE);
        table.addCell(cell);
       
        //////////////////////////////////////////////////////////////////
        
         while(rs.next()) {  
        
      cell= new PdfPCell(new Phrase(rs.getString("num_echelon").toString(),FontFactory.getFont("Ariel",13)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.WHITE);
        table.addCell(cell);
        
        
        cell= new PdfPCell(new Phrase(rs.getString("date_echelon").toString(),FontFactory.getFont("Ariel",13)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.WHITE);
        table.addCell(cell);
        
        }
          
        doc.add(table);
        doc.close();
        Desktop.getDesktop().open(new File("C:\\echelons\\echelons.pdf"));
         
    } 
}
