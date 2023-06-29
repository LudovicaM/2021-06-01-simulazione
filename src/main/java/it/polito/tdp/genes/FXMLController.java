/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.genes;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import it.polito.tdp.genes.model.Genes;
import it.polito.tdp.genes.model.Model;
import it.polito.tdp.genes.model.PesoAdiacenti;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="cmbGeni"
    private ComboBox<Genes> cmbGeni; // Value injected by FXMLLoader

    @FXML // fx:id="btnGeniAdiacenti"
    private Button btnGeniAdiacenti; // Value injected by FXMLLoader

    @FXML // fx:id="txtIng"
    private TextField txtIng; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	model.creaGrafo();
    	txtResult.setText("Grafo creato\n");
    	txtResult.appendText("#vertici: "+model.getNumV()+"\n#archi: "+model.getNumE()+"\n\n");
    	
    	cmbGeni.getItems().addAll(model.getVertici());

    }

    @FXML
    void doGeniAdiacenti(ActionEvent event) {
    	Genes gene = cmbGeni.getValue();
    	if (gene == null) {
    		txtResult.appendText("Selezionare un gene se si vogliono calcolare i geni adiacenti\n");
    		return;
    	}
    	txtResult.appendText("Geni adiacenti a "+gene+":\n");
    	for (PesoAdiacenti pa : model.getAdiacenti(gene)) {
    		txtResult.appendText(pa+"\n");
    	}
    	
    }

    @FXML
    void doSimula(ActionEvent event) {
    	Genes gene = cmbGeni.getValue();
    	if (gene == null) {
    		txtResult.appendText("Selezionare un gene se si vogliono calcolare i geni adiacenti\n");
    		return;
    	}
    	String ingS = txtIng.getText();
    	if (ingS == "") {
    		txtResult.setText("Inserire un numero minimo di ingegneri\n");
    		return;
    	}
    	try {
    		int ing = Integer.parseInt(ingS);
    		Map<Genes, Integer> geniStudiati = model.simulaIngegneri(gene, ing);
    		txtResult.setText("Geni in corso di studio: \n\n");
    		for (Genes g : geniStudiati.keySet()) {
    			txtResult.appendText(g + " da "+geniStudiati.get(g)+" ingegneri\n");
    		}
    	}catch (NumberFormatException e) {
    		txtResult.setText("Inserire un numero minimo di ingegneri\n[valore numerico]");
    		return;
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbGeni != null : "fx:id=\"cmbGeni\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnGeniAdiacenti != null : "fx:id=\"btnGeniAdiacenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtIng != null : "fx:id=\"txtIng\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
    
}
