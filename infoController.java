package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class infoController {
	@FXML
	Label lbl;
	@FXML
	Label lb2;
	@FXML
	Button btn;
	
	public void trocaDados(String m) {
		
		lb2.setText(m);
	}

	@FXML
	public void fechaJanela() {
		Stage stage = (Stage) btn.getScene().getWindow();
		stage.close();
	}
}
