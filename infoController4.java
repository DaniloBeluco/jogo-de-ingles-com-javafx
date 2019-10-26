package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class infoController4 implements Initializable {
	@FXML
	Label lbl;
	@FXML
	Label lb2;
	@FXML
	Button btn;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		

	}

	@FXML
	public void fechaJanela() {
		Stage stage = (Stage) btn.getScene().getWindow();
		stage.close();
	}
}
