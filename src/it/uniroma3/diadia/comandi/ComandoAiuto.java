package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando {
	
	private static final String NOME = "aiuto";
	
	public ComandoAiuto() {
		super.setNome(NOME);
	}

	static final private String[] elencoComandi = {"vai", "aiuto", "fine","guarda", "prendi", "posa", "saluta", "interagisci", "regala"};

	@Override
	public void esegui(Partita partita) {
		for(int i=0; i < elencoComandi.length; i++) {
			super.getIOConsole().mostraMessaggio(" | " + elencoComandi[i]);
		}		
	}
}