package application;

import java.net.URL;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dao.UsuarioDAO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.ConexaoDataBase;
import util.Constants;

public class PontuacaoController {
	@FXML
	Label lbl1;
	@FXML
	Label lbl2;
	@FXML
	private Button btnSair;
	
	public void trocaDados(String string) {
		lbl1.setText(string);
		
	}
	public void trocaDados2(int i) {
		lbl2.setText(Integer.toString(i));
//		atualizarTabela();
	}

	 @FXML
	    private TableView<usuario> usuarios;
	    @FXML
	    private TableColumn<usuario, String> colNome;
	    @FXML
	    private TableColumn<usuario, Number> colPontos;
	    @FXML
	    private TableColumn<usuario, String> colData;
	    
	    
		private UsuarioDAO dao = new UsuarioDAO();
	    usuario u = new usuario();
	    
	    @FXML
		public void initialize() {
	
			usuarios.setItems(FXCollections.observableArrayList(dao.listarTodas()));
			configuraTabela();
		}
	    
	    private void configuraTabela() {
			
			colNome.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
			colPontos.setCellValueFactory(cellData -> cellData.getValue().pontosProperty());
			colData.setCellValueFactory(cellData -> cellData.getValue().dataProperty());
				
		}
	    
	    @FXML
		public void fechaJanela() {
			Stage stage = (Stage) btnSair.getScene().getWindow();
			stage.close();
		}
	    public void abreMenu() {
	    	try {
	    		
	    		Stage stageJanela1 = new Stage();
	    		FXMLLoader loader = new FXMLLoader(getClass().getResource("Principal.fxml"));
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
	    
}
