package Libreria.java.raffaele;

import java.util.Scanner;

public class Autore {
	public Autore(String nome, String cognome) {
		this.nome = nome;
		this.cognome = cognome;
	}

	//Metodi get/set
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public static Autore read(Scanner s) {
		if(!s.hasNext()) return null;
		String nome = s.nextLine();
		if(!s.hasNext()) return null;
		String cognome = s.nextLine();
		
		return new Autore(nome, cognome);
		
	}
	
	public String toString() {
		return nome + " " + cognome;
	}

	String nome;
	String cognome;
}
