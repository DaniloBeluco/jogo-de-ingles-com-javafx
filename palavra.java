package application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class palavra {
	private IntegerProperty id = new SimpleIntegerProperty(0);
	private StringProperty Palavra = new SimpleStringProperty("");
	private StringProperty Resposta = new SimpleStringProperty("");
	private StringProperty Dica = new SimpleStringProperty("");
	
	private byte[] imagem;
	
	
	
	
	public byte[] getImagem() {
		return imagem;
	}
	

	public final IntegerProperty idProperty() {
		return this.id;
	}
	
	public final int getId() {
		return this.idProperty().get();
	}
	
	public final void setId(final int id) {
		this.idProperty().set(id);
	}
	
	
	public StringProperty palavraProperty() {
		return this.Palavra;
	}
	public String getPalavra() {
		return this.palavraProperty().get();
	}
	public void setPalavra(final String palavra) {
		this.palavraProperty().set(palavra);
	}
	
	public StringProperty respostaProperty() {
		return this.Resposta;
	}
	public String getResposta() {
		return this.respostaProperty().get();
	}
	public void setResposta(final String resposta) {
		this.respostaProperty().set(resposta);
	} 
	
	
	public StringProperty dicaProperty() {
		return this.Dica;
	}
	public String getDica() {
		return this.dicaProperty().get();
	}
	public void setDica(final String dica) {
		this.dicaProperty().set(dica);
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
		
	}

}
