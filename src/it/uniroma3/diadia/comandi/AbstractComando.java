package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public abstract class AbstractComando implements Comando{

	private String nome;
	
	private String parametro;
	
	private IO io;
	
	public abstract void esegui(Partita partita);
	
	public void setIOConsole(IO io) {
		this.io = io;
	}
	
	public IO getIOConsole() {
		return this.io;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getParametro() {
		return parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}
	
	
	
}
