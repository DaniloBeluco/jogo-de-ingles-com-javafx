package application;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import dao.PalavraDAO;
import dao.UsuarioDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.ConexaoDataBase;
import util.Constants;

public class AdicionarController {
	@FXML
	private Button btnSair;
	@FXML
	private Button btnSair1;
	@FXML
	private Button btnAdd;
	@FXML 
	private TextField txtPalavra;
	@FXML 
	private TextField txtResposta;
	@FXML 
	private TextField txtDica;
	@FXML
	private ImageView img;
	String txtPath;
	private PalavraDAO dao = new PalavraDAO();
	private palavra palavra = null;
	@FXML
	public void initialize() {
		Constants.conn = ConexaoDataBase.conectaBd();
	}
	
    @FXML
		public void fechaJanela() {
			Stage stage = (Stage) btnSair.getScene().getWindow();
			stage.close();
		}
    @FXML
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
    
	private palavra tela4palavra() throws IOException { 
		palavra a = new palavra();
		a.setPalavra(txtPalavra.getText()); 
		a.setResposta(txtResposta.getText()); 
		a.setDica(txtDica.getText()); 
		a.setImagem(getBytes(selecionaImagem()));
		
		return a;
	}
	
	public static byte[] getBytes(File f) throws IOException {
		byte[] buffer = new byte[1024];
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		FileInputStream fis = new FileInputStream(f);
		int read;
		while((read = fis.read(buffer)) != -1) {
			os.write(buffer, 0, read);
		}
		fis.close();
		os.close();
		return os.toByteArray();
		
	}
	
	@FXML
	private void inserir() throws IOException {
		palavra a = tela4palavra(); 
		dao.inserir(a); 
		System.out.println("Enviando:"+a.getPalavra());
		System.out.println("Enviando:"+a.getResposta());
		
		palavra = null; 
	        
	}
    
    @FXML 
    public void adicionarPalavras() throws IOException {
    	if (valida()) {
    	inserir();
    	limpaTela();
    	abreInfo6("");
    	} else {
    		abreInfo4("");
    	}
    }
    
	@FXML
	public void abreImagem() {
		File f = selecionaImagem();
		if (f != null) {
			txtPath = f.toURI().toString();
			Image i = new Image(f.toURI().toString());
			img.setImage(i);
			img.setFitWidth(i.getWidth());
			img.setFitHeight(i.getHeight());

		}
	}

	private File selecionaImagem() {
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagens", "*.jpg", "*.JPG", "*.png", "*.PNG",
				"*.gif", "*.GIF", "*.bmp", "*.BMP"));
		fc.setInitialDirectory(new File("C:\\Users")); 
		return fc.showOpenDialog(null);
	}
	
	public void limpaTela() {
		
		txtPalavra.setText("");
		txtResposta.setText("");
		txtDica.setText("");
	//	Image i = new Image("file:/C:/Users/Danilo/Desktop/Project%20Java/EnglishQuizz/images/branco.png");
	}
	
	private boolean valida() {
		if (txtPalavra.getText().equals("")) {
		return false;
		}
		if (txtResposta.getText().equals("")) {
			return false;
			}
		if (txtDica.getText().equals("")) {
			return false;
			}
		return true;
		
	}
	
	public void abreInfo4(String m) { 
		try {
			Stage stageJanela1 = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("info4.fxml"));
			
			Parent root = loader.load();
			stageJanela1.setScene(new Scene(root));
			stageJanela1.initModality(Modality.WINDOW_MODAL);
			stageJanela1.setResizable(false);
			stageJanela1.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void abreInfo6(String m) {
		try {
			Stage stageJanela1 = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("info6.fxml"));

			Parent root = loader.load();
			stageJanela1.setScene(new Scene(root));
			stageJanela1.initModality(Modality.WINDOW_MODAL);
			stageJanela1.setResizable(false);
			stageJanela1.show();

			infoController controller = loader.getController();
			controller.trocaDados(m);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
