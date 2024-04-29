package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando {
	
	private IO io;
	
	public ComandoAiuto(IO io) {
		this.io = io;
	}

	static final private String[] elencoComandi = {"vai", "aiuto", "fine","guarda", "prendi", "posa"};
	private String parametroNullo;

	@Override
	public void esegui(Partita partita) {
		for(int i=0; i < elencoComandi.length; i++) {
			this.io.mostraMessaggio(" | " + elencoComandi[i]);
		}


	}

	@Override
	public void setParametro(String parametro) {
		this.parametroNullo = parametro;

	}

	@Override
	public String getNome() {
		return "aiuto";
	}

	@Override
	public String getParametro() {
		return this.parametroNullo;
	}

}
