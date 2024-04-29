package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido implements Comando {
	
	private String parametroNullo;
	private IO io;

	public ComandoNonValido(IO io) {
		this.io = io;
	}

	@Override
	public void esegui(Partita partita) {
		this.io.mostraMessaggio("Hai inserito un comando non valido!");

	}

	@Override
	public void setParametro(String parametro) {
		this.parametroNullo = parametro;
	}

	@Override
	public String getNome() {
		return "comando non valido";
	}

	@Override
	public String getParametro() {
		return this.parametroNullo;
	}

}
