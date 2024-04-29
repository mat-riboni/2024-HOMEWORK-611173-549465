package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza {
	
	private String nomeAttrezzoPerIlluminare;

	public StanzaBuia(String nome, String nomeAttrezzoPerIlluminare) {
		super(nome);
		this.nomeAttrezzoPerIlluminare = nomeAttrezzoPerIlluminare;
	}
	
	@Override
	public String toString() {
		if(this.hasAttrezzo(nomeAttrezzoPerIlluminare))
			return super.toString();
		else 
			return "Qui c'è buio pesto...";
	}
}
