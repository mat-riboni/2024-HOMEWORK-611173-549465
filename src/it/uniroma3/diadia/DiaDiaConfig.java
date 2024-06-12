package it.uniroma3.diadia;

import java.io.IOException;
import java.util.Properties;

public class DiaDiaConfig {

	private static final String DIADIA_PROPERTIES = "diadia.properties";
	private static final String PESO_MAX = "peso_max";
	private static final String CFU_INIZIALI = "cfu_iniziali";
	private static Properties prop = null;



	public static int getCfuIniziali() {
		if(prop == null)
			carica();
		return Integer.parseInt(prop.getProperty(CFU_INIZIALI));
	}
	
	public static int getPesoMax() {
		if(prop == null)
			carica();
		return Integer.parseInt(prop.getProperty(PESO_MAX));
	}

	private static void carica() {
		prop = new Properties();
		try {
			prop.load(DiaDiaConfig.class.getClassLoader().getResourceAsStream(DIADIA_PROPERTIES));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}




}
