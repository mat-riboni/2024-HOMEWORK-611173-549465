package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando {

	private String parametroNullo;

	@Override
	public void esegui(Partita partita) {
		System.out.println(partita.getStanzaCorrente().toString());

	}

	@Override
	public void setParametro(String parametro) {
		this.parametroNullo = parametro;

	}

	@Override
	public String getNome() {
		return "guarda";
	}

	@Override
	public String getParametro() {
		return this.parametroNullo;
	}

}
