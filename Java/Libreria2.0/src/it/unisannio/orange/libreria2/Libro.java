package it.unisannio.orange.libreria2;

import java.util.Scanner;

public class Libro{

    public Libro(String titolo, String codice_autore, String anno, String genere, String casa_editrice,
                 String path_descrizione, String path_copertina) {
        super();
        this.titolo = titolo;
        this.codice_autore = codice_autore;
        this.anno = anno;
        this.genere = genere;
        this.casa_editrice = casa_editrice;
        this.path_descrizione = path_descrizione;
        this.path_copertina = path_copertina;
    }

    public String getTitolo() {
        return titolo;
    }
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }
    public String getCodice_autore() {
        return codice_autore;
    }
    public void setCodice_autore(String codice_autore) {
        this.codice_autore = codice_autore;
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
    public String getPath_descrizione() {
        return path_descrizione;
    }
    public void setPath_descrizione(String path_descrizione) {
        this.path_descrizione = path_descrizione;
    }
    public String getPath_copertina() {
        return path_copertina;
    }
    public void setPath_copertina(String path_copertina) {
        this.path_copertina = path_copertina;
    }



    @Override
    public String toString() {
        return titolo;
    }

    public static Libro read(Scanner s) {
        if(!s.hasNext())
            return null;
        String titolo = s.nextLine();
        if(!s.hasNext())
            return null;

        String codice_autore = s.nextLine();
        if(!s.hasNext())
            return null;
        String anno = s.nextLine();
        if(!s.hasNext())
            return null;
        String genere= s.nextLine();
        if(!s.hasNext())
            return null;
        String casa_editrice = s.nextLine();
        if(!s.hasNext())
            return null;
        String path_descrizione = s.nextLine();
        if(!s.hasNext())
            return null;
        String path_copertina = s.nextLine();

        return new Libro(titolo, codice_autore, anno, genere, casa_editrice,path_descrizione, path_copertina );
    }


    private String titolo;
    private String codice_autore;
    private String anno;
    private String genere;
    private String casa_editrice;
    private String path_descrizione;
    private String path_copertina;

}
