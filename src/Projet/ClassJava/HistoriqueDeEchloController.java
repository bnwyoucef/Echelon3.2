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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


/**
 * FXML Controller class
 *
 * @author idwar
 */
public class HistoriqueDeEchloController implements Initializable {
    private TableView<echelon> ModTableHistorique;
    @FXML
    private TextField ModIdTextField;
    @FXML
    private TextField ModNumEchelonTextField;
    
    private TextField ModDateEchelonTextField;
      @FXML
    private Button ModAjouterBtn;
    @FXML
    private Button ModModifierBtn;
    @FXML
    private Button ModSuprimerBtn;
    @FXML
    private Button searchBtn;
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
    @FXML
    private Label test;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
       }    

 

    @FXML
    void buttonsClicked(ActionEvent event) throws SQLException, ParseException {
        if(event.getSource().equals(ModAjouterBtn)){
    conn.InsertRecord(ModIdTextField, ModNumEchelonTextField, selectdate);
    conn.showechelons(num, ladate, table_h, ModIdTextField);
    }else if(event.getSource().equals(searchBtn)){
           conn.showechelons(num, ladate, table_h, ModIdTextField);
         
           putnom.setText(conn.getnom(ModIdTextField));
           
           putprenom.setText(conn.getprenom(ModIdTextField));
 }else if(event.getSource().equals(ModSuprimerBtn)){
           conn.DeleteRecord(ModIdTextField);
           conn.showechelons(num, ladate, table_h, ModIdTextField);
    }else if(event.getSource().equals(ModModifierBtn)){
           conn.UpdateRecord(ModIdTextField, ModNumEchelonTextField, selectdate);
           conn.showechelons(num, ladate, table_h, ModIdTextField);
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
              
              con=DriverManager.getConnection("jdbc:mysql://localhost/echelon?UseUnicode=yes&characterEncoding=UTF-8","root","");
              pst=con.prepareStatement("SELECT * FROM `historique`  where Id = "+ModIdTextField.getText());                       
              rs=pst.executeQuery();

        Document doc=new Document();
 
      
      PdfWriter.getInstance(doc, new FileOutputStream("C:\\echelons\\echelons.pdf"));
      doc.open();
      
      
        nom = conn.getnom(ModIdTextField);
        prenom = conn.getprenom(ModIdTextField);
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
