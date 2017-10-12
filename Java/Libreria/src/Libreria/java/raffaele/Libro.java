package Libreria.java.raffaele;

import java.util.Scanner;

public class Libro {
	public Libro(String titolo, Autore autore, String anno, String genere, String casa_editrice) {
		this.titolo = titolo;
		this.autore = autore;
		this.anno = anno;
		this.genere = genere;
		this.casa_editrice = casa_editrice;
		
	}

	//Metodi get/set
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public Autore getAutore() {
		return autore;
	}
	public void setAutore(Autore autore) {
		this.autore = autore;
	}
	public String getAnno() {
		return anno;
	}
	public void setAnno(String anno) {
		this.anno = anno;
	}
	public String getGenere() {
		return genere;
	}
	public void setGenere(String genere) {
		this.genere = genere;
	}
	public String getCasa_editrice() {
		return casa_editrice;
	}
	public void setCasa_editrice(String casa_editrice) {
		this.casa_editrice = casa_editrice;
	}
	
	public static Libro read(Scanner s) {
		
		if(!s.hasNext()) return null;
		String titolo = s.nextLine();
		Autore autore= Autore.read(s);
		if(autore==null) return null;
		if(!s.hasNext()) return null;
		String anno=s.nextLine();
		if(!s.hasNext()) return null;
		String genere=s.nextLine();
		if(!s.hasNext()) return null;
		String casa_editrice=s.nextLine();
		return new Libro(titolo, autore, anno, genere, casa_editrice);
		
	}

	public String toString() {
		return titolo;
	}

	String titolo;
	Autore autore;
	String anno;
	String genere;
	String casa_editrice;
}
