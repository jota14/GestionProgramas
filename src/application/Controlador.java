package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.*;

import javafx.application.Platform;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class Controlador implements Initializable{

    @FXML
    private JFXButton btnExcel;

    @FXML
    private JFXButton btnWord;

    @FXML
    private JFXButton btnPowerP;

    @FXML
    private JFXButton btnBuscar;

    @FXML
    private JFXTextField txfUrl;
    
    @FXML
    private TableView<FicheroTexto> tvHistorial;

    @FXML
    private TableColumn<FicheroTexto, String> columUrl;
    
    
   
    @FXML
    void abrirExcel(ActionEvent event) {
    	try {
			Process p = Runtime.getRuntime().exec ("C:\\Program Files (x86)\\Microsoft Office\\Office15\\excel.exe");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @FXML
    void abrirPowerPoint(ActionEvent event) {
    	try {
			Process p = Runtime.getRuntime().exec ("C:\\Program Files (x86)\\Microsoft Office\\Office15\\powerpnt.exe");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void abrirWord(ActionEvent event) {
    	try {
			Process p = Runtime.getRuntime().exec ("C:\\Program Files (x86)\\Microsoft Office\\Office15\\winword.exe");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
   
    
  

    
	@FXML
    void buscarURL(ActionEvent event) {

    	FileWriter fw;
    	FileReader fr;
    	String url, linea, last = null;
		
    		try {
				Process p = Runtime.getRuntime().exec ("rundll32 url.dll,FileProtocolHandler " +("http://"+txfUrl.getText()));
				url = txfUrl.getText();
		    
				fw = new FileWriter("urls.txt",true);
				fr = new FileReader("urls.txt");
				
				BufferedReader bfr = new BufferedReader(fr);
				
				fw.write(url+"\r\n");
				fw.flush();	
				
			
				linea=bfr.readLine();
				
				 while (linea != null) { 
				 last = linea; 
				 linea = bfr.readLine(); 
				 } 
				FicheroTexto f1 = new FicheroTexto(last);
				tvHistorial.getItems().addAll(f1);
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
    }		
    

	@FXML
    void cerrarVentana(ActionEvent event) {
    	Platform.exit();
    }
	
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		String linea;
		FileReader fr;
		
		ObservableList<FicheroTexto> urlData = FXCollections.observableArrayList();
    	tvHistorial.getItems().addAll(urlData);
		columUrl.setCellValueFactory(new PropertyValueFactory<>("url"));
		
		try {
			fr = new FileReader("urls.txt");
			
			BufferedReader bfr = new BufferedReader(fr);
			while((linea=bfr.readLine()) != null) {
				FicheroTexto f1 = new FicheroTexto(linea);
				tvHistorial.getItems().addAll(f1);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		tvHistorial.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				//System.out.println(tvHistorial.getSelectionModel().getSelectedItem().getUrl());
				String data = (tvHistorial.getSelectionModel().getSelectedItem().getUrl())+"";
			
				txfUrl.setText(data);
				
			}});
		 
	}	

 
    
}