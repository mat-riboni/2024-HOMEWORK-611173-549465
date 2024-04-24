package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza {
	
	private String nomeAttrezzoPerIlluminare;

	public StanzaBuia(String nome, String nomeAttrezzoPerIlluminare) {
		super(nome);
		this.nomeAttrezzoPerIlluminare = nomeAttrezzoPerIlluminare;
	}
	
	@Override
	public String getDescrizione() {
		if(this.hasAttrezzo(nomeAttrezzoPerIlluminare))
			return super.getDescrizione();
		else 
			return "Qui c'è buio pesto...";
	}
}
