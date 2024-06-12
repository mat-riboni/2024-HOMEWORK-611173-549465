package it.uniroma3.diadia.comandi;

import java.util.Scanner;

import it.uniroma3.diadia.IO;

public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi{

	IO io;

	public FabbricaDiComandiRiflessiva(IO io) {
		this.io = io;
	}

	@Override
	public AbstractComando costruisciComando(String istruzione) {
		Scanner scannerDiParole = new Scanner(istruzione);
		String nomeComando = null;
		String parametro = null;
		AbstractComando comando = null;
		if (scannerDiParole.hasNext())
			nomeComando = scannerDiParole.next(); // prima parola: nome del comando
		if (scannerDiParole.hasNext())
			parametro = scannerDiParole.next(); // seconda parola: eventuale parametro
		StringBuilder nomeClasse = new StringBuilder("it.uniroma3.diadia.comandi.Comando");
		nomeClasse.append(Character.toUpperCase(nomeComando.charAt(0)));
		nomeClasse.append(nomeComando.substring(1));
		try {
			comando = (AbstractComando) Class.forName(nomeClasse.toString()).newInstance();
		} catch (Exception e){
			comando = new ComandoNonValido();
		} finally {
			comando.setParametro(parametro);
			comando.setIOConsole(this.io);
		}
		
		
		return comando;
	}
	
}
