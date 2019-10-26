package application;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import dao.PalavraDAO;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.ConexaoDataBase;
import util.Constants;

public class Janela1Controller implements Initializable {
	@FXML
	private Button btnSair;
	@FXML
	private Label lbl;
	@FXML
	private Label lblDica;
	@FXML
	private Label lblNome;
	@FXML
	private Label lblPontos;
	@FXML
	private TextField txt;
	@FXML
	private ImageView img;
	@FXML
	private TextField txtFocus;
	@FXML
	private TableView<usuario> usuarios;
	@FXML
	private TableColumn<usuario, String> colNome;
	@FXML
	private TableColumn<usuario, Number> colPontos;
	@FXML
	private Button btnDica;

	private int jaUsadas[] = new int[99999];
	private ArrayList<usuario> lista = new ArrayList<usuario>();
	private PalavraDAO daop = new PalavraDAO();
	private UsuarioDAO dao = new UsuarioDAO();
	private usuario usuario = null;

	int c1 = 0;

	public void trocaDados(String v) {
		lblNome.setText(v);

	}

	public void pergunta(int c1) {

		lbl.setText(daop.selecionarPalavra(c1));

	}	

	int contErro = 0;
	int pontos = 0;
	int cc = 0;

	usuario u = new usuario();

	public void confirmar() throws IOException {

        if (txt.getText().equals("")) {
        	abreInfo3("");
        } else {
		if (txt.getText().equalsIgnoreCase(daop.selecionarResposta(c1))) {
			if (contErro == 0) {
				pontos = pontos + 3;
				abreInfo("E GANHOU 3 PONTOS!");
			} else if (contErro == 1) {
				pontos = pontos + 2;
				abreInfo("E GANHOU 2 PONTOS!");
			} else if (contErro == 2) {
				pontos = pontos + 1;
				abreInfo("E GANHOU 1 PONTO!");
			}
			lblPontos.setText(Integer.toString(pontos));

			u.setNome(lblNome.getText());
			u.setPontos(Integer.parseInt(lblPontos.getText()));

			btnDica.setText("Pedir Dica");
			contErro = 0;

			jaUsadas[cc] = c1;
			cc++;

			if (cc == daop.retornarQuantidade() - 1) {
				System.out.println(cc);
				abreMenu();
               

			} else {

				int n = 0;
				int n1 = 0;
				do {
					Random random = new Random();
					n1 = random.nextInt(daop.retornarQuantidade());

					n = jaUsadas(n1);
				} while (n == 1);
				c1 = n1;

				pergunta(c1);
				System.out.println(jaUsadas(c1));
				limpaTela();
			}

		} else {
			abreInfo2("");

		}
        }
	}

	// verifica se a palavra ja foi usada
	public int jaUsadas(int a) {
		int v = 0;
		for (int i = 0; i != jaUsadas.length; i++) {

			if (a == jaUsadas[i]) {
				v = 1;
			} else {

			}
		}

		return v;
	}

	public void pedirDica() throws IOException {
		btnDica.setText("Pedir outra Dica");
		if (contErro == 0) {
			lblDica.setText(daop.selecionarDica(c1));
			contErro++;
		} else {
			contErro++;
			btnDica.setText("---------");
			lblDica.setText(daop.selecionarDica(c1));
			// writeByte(daop.selecionarImagem(12));
			///
			 createImage();
			//abreImagem();
			 abreImagem();

		}
	}
String txtPath;
	@FXML
	
	public void abreImagem() {
		// antigo: "file:/C:/Users/Danilo/Desktop/Project Java/Projeto_Final/images/new.jpg"
			Image i = new Image("file:..//Projeto_Final/images/new.jpg");
			img.setImage(i);
			img.setFitWidth(i.getWidth());
			img.setFitHeight(i.getHeight());

		
	}

	
	public void limpaTela() {
		lblDica.setText("");
		txt.setText("");
		// antigo: "file:/C:/Users/Danilo/Desktop/Project%20Java/Projeto_Final/images/branco.png"
		Image i = new Image("file:..//Projeto_Final/images/branco.png");

		img.setImage(i);
		img.setFitWidth(i.getWidth());
		img.setFitHeight(i.getHeight());
	}

	@FXML
	public void fechaJanela() {
		Stage stage = (Stage) btnSair.getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Constants.conn = ConexaoDataBase.conectaBd();
		Random random = new Random();
		c1 = random.nextInt(daop.retornarQuantidade());
		pergunta(c1);

	}

	public void abreInfo(String m) {
		try {
			Stage stageJanela1 = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("info.fxml"));

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

	public void abreInfo2(String m) {
		try {
			Stage stageJanela1 = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("info2.fxml"));

			Parent root = loader.load();
			stageJanela1.setScene(new Scene(root));
			stageJanela1.initModality(Modality.WINDOW_MODAL);
			stageJanela1.setResizable(false);
			stageJanela1.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private usuario tela4usuario() {
		usuario a = new usuario();
		a.setNome(lblNome.getText());
		a.setPontos(Integer.parseInt(lblPontos.getText()));
		
		return a;
	}

	@FXML
	private void inserir() {
		usuario a = tela4usuario();
		dao.inserir(a);
		usuario = null;

	}
	
	@FXML
	private void inserirUpdate() {
		usuario a = tela4usuario();
		dao.inserirUpdate(a);

		usuario = null;

	}

	public void abreMenu() {
		
	     System.out.println("Pontuação Atual: "+ Integer.parseInt(lblPontos.getText()));
	     System.out.println("Pontuação no banco: "+dao.retornarPontuacao(lblNome.getText()));
	     
		try {
			  if ((dao.retornarQuantidade(lblNome.getText()) > 0) && (Integer.parseInt(lblPontos.getText()) > dao.retornarPontuacao(lblNome.getText()))) {
				  
				  inserirUpdate();
				 
              } else if((dao.retornarQuantidade(lblNome.getText()) > 0) && (Integer.parseInt(lblPontos.getText()) < dao.retornarPontuacao(lblNome.getText()))) {
            	  
            	  System.out.println("!Pontuação menor!");
            	  
              } else {
			inserir();
              }
			Stage stageJanela1 = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Pontuacao.fxml"));
			Parent root = loader.load();
			stageJanela1.setScene(new Scene(root));

			PontuacaoController controller = loader.getController();
			controller.trocaDados(lblNome.getText());
			controller.trocaDados2(Integer.parseInt(lblPontos.getText()));
			

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

	public void createImage() throws IOException {
		try {

			byte[] imageInByte = daop.selecionarImagem(c1);

			// convert byte array back to BufferedImage
			InputStream in = new ByteArrayInputStream(imageInByte);
			BufferedImage bImageFromConvert = ImageIO.read(in);

			ImageIO.write(bImageFromConvert, "jpg",
					//antigo: "C:/Users/Danilo/Desktop/Project Java/Projeto_Final/images/new.jpg"
					new File("..//Projeto_Final/images/new.jpg"));
			

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}


//	public static File toFile(byte[] data, File destination) throws IOException {
//		try (FileOutputStream fos = new FileOutputStream(destination)) {
//			fos.write(data);
//		} catch (Exception e) {
//			System.out.println("Erro na imagem");
//		}
//		return destination.getAbsoluteFile();
//	}

	// Path of a file

//	static String FILEPATH = "C:/Users/Danilo/Desktop/Project%20Java/Projeto_Final/images/banana.jpg";
//	static File file = new File(FILEPATH);

	// Method which write the bytes into a file
//	public void writeByte(byte[] bytes) {
//
//		try {
//
//			// Initialize a pointer
//			// in file using OutputStream
//			OutputStream os = new FileOutputStream(file);
//
//			// Starts writing the bytes in it
//			os.write(bytes);
//			System.out.println("Successfully" + " byte inserted");
//
//			// Close the file
//			os.close();
//		}
//
//		catch (Exception e) {
//			System.out.println("Exception: " + e);
//		}
//	}
	
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

}
