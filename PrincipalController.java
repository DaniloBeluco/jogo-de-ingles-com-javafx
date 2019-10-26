package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.ConexaoDataBase;
import util.Constants;

public class PrincipalController {
@FXML
TextField txtNome;
@FXML
AnchorPane tela;
@FXML
Button btnStart;
@FXML
private Button btnSair;

public void inicia() {
	if (valida()) {
		abreJanela1();
	} else {
		abreInfo3("");
	}
}


@FXML
public void abreJanela1() {
	try {
		
		Stage stageJanela1 = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Janela1.fxml"));
		Parent root = loader.load();
		stageJanela1.setScene(new Scene(root));

		Janela1Controller controller = loader.getController();
		
		controller.trocaDados(txtNome.getText());
		stageJanela1.initModality(Modality.WINDOW_MODAL);
		stageJanela1.initStyle(StageStyle.UNDECORATED);
		stageJanela1.setResizable(false);
		stageJanela1.setMaximized(true);
		stageJanela1.show();
		fechaJanela();
	
	} catch (Exception e) {
		e.printStackTrace();
	}
}
	public void abrePontuacao() {
		try {
			
			Stage stageJanela1 = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Pontuacao.fxml"));
			Parent root = loader.load();
			stageJanela1.setScene(new Scene(root));

			
			stageJanela1.initModality(Modality.WINDOW_MODAL);
			stageJanela1.initStyle(StageStyle.UNDECORATED);
			stageJanela1.setResizable(false);
			stageJanela1.setMaximized(true);
			stageJanela1.show();
			fechaJanela();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	
}
	public void abreAdicionar() {
		if (txtNome.getText().equalsIgnoreCase("Professor")) {
		try {
			
			Stage stageJanela1 = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Adicionar.fxml"));
			Parent root = loader.load();
			stageJanela1.setScene(new Scene(root));
	
			
			stageJanela1.initModality(Modality.WINDOW_MODAL);
			stageJanela1.initStyle(StageStyle.UNDECORATED);
			stageJanela1.setResizable(false);
			stageJanela1.setMaximized(true);
			stageJanela1.show();
			fechaJanela();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		} else {
         abreInfo5("");
		}
	}
@FXML
public void fechaJanela() {
	Stage stage = (Stage) btnSair.getScene().getWindow();
	stage.close();
}

@FXML
public void initialize() {
	Constants.conn = ConexaoDataBase.conectaBd();
}

private boolean valida() {
	if (txtNome.getText().equals("")) {
	return false;
	}
	return true;
	
}

public void abreInfo3(String m) { 
	try {
		Stage stageJanela1 = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("info3.fxml"));
		
		Parent root = loader.load();
		stageJanela1.setScene(new Scene(root));
		stageJanela1.initModality(Modality.WINDOW_MODAL);
		stageJanela1.setResizable(false);
		stageJanela1.show();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public void abreInfo5(String m) { 
	try {
		Stage stageJanela1 = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("info5.fxml"));
		
		Parent root = loader.load();
		stageJanela1.setScene(new Scene(root));
		stageJanela1.initModality(Modality.WINDOW_MODAL);
		stageJanela1.setResizable(false);
		stageJanela1.show();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}

}
