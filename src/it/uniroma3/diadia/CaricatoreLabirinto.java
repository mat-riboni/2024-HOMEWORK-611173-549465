
package it.uniroma3.diadia;

import java.io.*;
import java.util.*;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.ambienti.StanzaMagica;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class CaricatoreLabirinto {

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_MARKER = "Stanze:";             

	/* prefisso di una singola riga contenente il nome della stanza iniziale */
	private static final String STANZA_INIZIALE_MARKER = "Inizio:";    

	/* prefisso della riga contenente il nome stanza vincente */
	private static final String STANZA_VINCENTE_MARKER = "Vincente:";  

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeAttrezzo> <peso> <nomeStanza> */
	private static final String ATTREZZI_MARKER = "Attrezzi:";

	/* prefisso della riga contenente le specifiche dei collegamenti tra stanza nel formato <nomeStanzaDa> <direzione> <nomeStanzaA> */
	private static final String USCITE_MARKER = "Uscite:";
	
	private static final String PERSONAGGI_MARKER = "Personaggi:";
	
	private static final String STANZE_BUIE_MARKER = "Stanze buie:";
	
	private static final String STANZE_BLOCCATE_MARKER = "Stanze bloccate:";
	
	private static final String STANZE_MAGICHE_MARKER = "Stanze magiche:";
	
	

	/*
	 *  Esempio di un possibile file di specifica di un labirinto (vedi POO-26-eccezioni-file.pdf)

		Stanze: biblioteca, N10, N11
		Inizio: N10
		Vincente: N11
		Attrezzi: martello 10 biblioteca, pinza 2 N10
		Uscite: biblioteca nord N10, biblioteca sud N11

	 */
	private LineNumberReader reader;

	private Map<String, Stanza> nome2stanza;

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;
	
	private LabirintoBuilder builder;


	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		this(new LineNumberReader(new FileReader(nomeFile)));
		
	}
	
	public CaricatoreLabirinto(Reader reader) {
		this.nome2stanza = new HashMap<String,Stanza>();
		this.reader = new LineNumberReader(new BufferedReader(reader));
		this.builder = Labirinto.newBuilder();
	}

	public void carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			this.leggiInizialeEvincente();
			this.leggiECreaMagiche();
			this.leggiECreaBuie();
			this.leggiECreaBloccate();
			this.leggiEInserisciPersonaggi();
			this.leggiECollocaAttrezzi();
			this.leggiEImpostaUscite();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}

	private void leggiEInserisciPersonaggi() throws FormatoFileNonValidoException{
		String personaggi = this.leggiRigaCheCominciaPer(PERSONAGGI_MARKER);
		for(String personaggio : this.separaStringheAlleVirgole(personaggi)) {
			String tipo = null;
			String nome = null;
			String presentazione = null;
			String nomeStanza = null;
			try(Scanner scanner = new Scanner(personaggio)){
				check(scanner.hasNext(), msgTerminazionePrecoce("il tipo del personaggio)"));
				tipo = scanner.next();
				check(scanner.hasNext(), msgTerminazionePrecoce("il nome del personaggio"));
				nome = scanner.next();
				check(scanner.hasNext(), msgTerminazionePrecoce("la presentazione del personaggio"));
				presentazione = scanner.next();
				check(scanner.hasNext(), msgTerminazionePrecoce("il nome della stanza del personaggio"));
				nomeStanza = scanner.next();
				this.builder.addPersonaggio(tipo, nome, presentazione, nomeStanza);
			}
		}
		
		
	}

	private void leggiECreaBloccate() throws FormatoFileNonValidoException{
		String bloccate = this.leggiRigaCheCominciaPer(STANZE_BLOCCATE_MARKER);
		for(String bloccata : this.separaStringheAlleVirgole(bloccate)) {
			String nome = null;
			String nomeSblocco = null;
			String direzioneBloccata = null;
			try(Scanner scanner = new Scanner(bloccata)){
				check(scanner.hasNext(), msgTerminazionePrecoce("il nome della stanza bloccata"));
				nome = scanner.next();
				check(scanner.hasNext(), msgTerminazionePrecoce("il nome della direzione bloccata"));
				direzioneBloccata = null;
				check(scanner.hasNext(), msgTerminazionePrecoce("il nome dell'attrezzo sbloccante"));
				nomeSblocco = scanner.next();
				Stanza stanzaBuia = new StanzaBuia(nome, nomeSblocco);
				this.builder.addStanzaBloccata(nome, direzioneBloccata, nomeSblocco);
				this.nome2stanza.put(nome, stanzaBuia);
			}
		}
		
	}

	private void leggiECreaBuie() throws FormatoFileNonValidoException{
		String buie = this.leggiRigaCheCominciaPer(STANZE_BUIE_MARKER);
		for(String buia : separaStringheAlleVirgole(buie)) {
			String nome = null;
			String nomeLuce = null;
			try (Scanner scanner = new Scanner(buia)){
				check(scanner.hasNext(), msgTerminazionePrecoce("il nome della stanza buia"));
				nome = scanner.next();
				check(scanner.hasNext(), msgTerminazionePrecoce("il nome dell'attrezzo per fare luce"));
				nomeLuce = scanner.next();
				Stanza stanzaBuia = new StanzaBuia(nome, nomeLuce);
				this.builder.addStanzaBuia(nome, nomeLuce);
				this.nome2stanza.put(nome, stanzaBuia);
			}
		}
		
	}

	private void leggiECreaMagiche() throws FormatoFileNonValidoException {
		String magiche = this.leggiRigaCheCominciaPer(STANZE_MAGICHE_MARKER);
		for(String magica : separaStringheAlleVirgole(magiche)) {
			String nomeStanza = null;
			int sogliaMagica = 0;
			try (Scanner scanner = new Scanner(magica)){
				check(scanner.hasNext(),msgTerminazionePrecoce("il nome della stanza magica."));
				nomeStanza = scanner.next();
				check(scanner.hasNext(), msgTerminazionePrecoce("il valore della soglia magica"));
				sogliaMagica = Integer.parseInt(scanner.next());
				this.builder.addStanzaMagica(nomeStanza, sogliaMagica);
				Stanza stanzaMagica = new StanzaMagica(nomeStanza, sogliaMagica);
				this.nome2stanza.put(nomeStanza, stanzaMagica);
			}
		}
		
	}

	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			check(riga.startsWith(marker),"era attesa una riga che cominciasse per "+marker);
			return riga.substring(marker.length());
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	private void leggiECreaStanze() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			this.builder.addStanza(nomeStanza);
			Stanza stanza = new Stanza(nomeStanza);
			this.nome2stanza.put(nomeStanza, stanza);
		}
	}

	private List<String> separaStringheAlleVirgole(String string){
		List<String> result = new LinkedList<String>();
		Scanner scanner = new Scanner(string);
		scanner.useDelimiter(",");
		try (Scanner scannerDiParole = scanner) {
			while(scanner.hasNext()) {
				result.add(scannerDiParole.next());
			}
		}
		return result;
	}


	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = null;
		nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
		check(this.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale +" non definita");
		this.builder.addStanzaIniziale(nomeStanzaIniziale);
		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
		check(this.isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");
		this.builder.addStanzaVincente(nomeStanzaVincente);
		this.stanzaIniziale = this.nome2stanza.get(nomeStanzaIniziale);
		this.stanzaVincente = this.nome2stanza.get(nomeStanzaVincente);
	}

	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);

		for(String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo "+nomeAttrezzo+"."));
				nomeStanza = scannerLinea.next();			
			posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
				
			}
		}
	}

	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
			check(isStanzaValida(nomeStanza),"Attrezzo "+ nomeAttrezzo+" non collocabile: stanza " +nomeStanza+" inesistente");
			this.builder.addAttrezzo(nomeStanza, nomeAttrezzo, peso);
			this.nome2stanza.get(nomeStanza).addAttrezzo(attrezzo);
		}
		catch (NumberFormatException e) {
			check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
		}
	}


	private boolean isStanzaValida(String nomeStanza) {
		return this.nome2stanza.containsKey(nomeStanza);
	}

	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);
		for(String specificaUscita : separaStringheAlleVirgole(specificheUscite)) {
			try (Scanner scannerDiLinea = new Scanner(specificaUscita)) {			
				while (scannerDiLinea.hasNext()) {
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("le uscite di una stanza."));
					String stanzaPartenza = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la direzione di una uscita della stanza "+stanzaPartenza));
					String dir = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la destinazione di una uscita della stanza "+stanzaPartenza+" nella direzione "+dir));
					String stanzaDestinazione = scannerDiLinea.next();
					
					impostaUscita(stanzaPartenza, dir, stanzaDestinazione);
				}
			} 
		}
	}
	
	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere "+msg;
	}

	private void impostaUscita(String stanzaDa, String dir, String nomeA) throws FormatoFileNonValidoException {
		check(isStanzaValida(stanzaDa),"Stanza di partenza sconosciuta "+dir);
		check(isStanzaValida(nomeA),"Stanza di destinazione sconosciuta "+ dir);
		Stanza partenzaDa = this.nome2stanza.get(stanzaDa);
		Stanza arrivoA = this.nome2stanza.get(nomeA);
		partenzaDa.impostaStanzaAdiacente(dir, arrivoA);
		this.builder.addAdiacenza(stanzaDa, nomeA, dir);
	}


	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException("Formato file non valido [" + this.reader.getLineNumber() + "] "+messaggioErrore);		
	}

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}
	
	public Map<String, Stanza> getNome2Stanza(){
		return this.nome2stanza;
	}
	
	public LabirintoBuilder getBuilder() {
		return this.builder;
	}
	
}
