package application;

import java.io.File;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class usuario {
	private IntegerProperty id = new SimpleIntegerProperty(0);
	private StringProperty Nome = new SimpleStringProperty("");
	private StringProperty Data = new SimpleStringProperty("");
	private SimpleIntegerProperty Pontos = new SimpleIntegerProperty(0);
	
	
	public final IntegerProperty idProperty() {
		return this.id;
	}
	
	public final int getId() {
		return this.idProperty().get();
	}
	
	public final void setId(final int id) {
		this.idProperty().set(id);
	}
	
	
	public StringProperty nomeProperty() {
		return this.Nome;
	}
	public String getNome() {
		return this.nomeProperty().get();
	}
	public void setNome(final String nome) {
		this.nomeProperty().set(nome);
	} 
	
	
	public StringProperty dataProperty() {
		return this.Data;
	}
	public String getData() {
		return this.dataProperty().get();
	}
	public void setData(final String data) {
		this.dataProperty().set(data);
	} 
	
	
	public IntegerProperty pontosProperty() {
		return this.Pontos;
	}
	public Integer getPontos() {
		return this.pontosProperty().get();
	}
	public void setPontos(final Integer pontos) {
		this.pontosProperty().set(pontos);
	}
	
	
}
