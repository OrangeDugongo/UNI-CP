package it.unisannio.orange.libreria2;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Libro{

    public Libro(String titolo, String codice_autore, String anno, String genere, String casa_editrice,
                 String descrizione, ImageIcon copertina) {
        super();
        this.titolo = titolo;
        this.codice_autore = codice_autore;
        this.anno = anno;
        this.genere = genere;
        this.casa_editrice = casa_editrice;
        this.descrizione = descrizione;
        this.copertina = copertina;
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
    public String getDescrizione() {
        return descrizione;
    }
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
    public ImageIcon getCopertina() {
        return copertina;
    }
    public void setCopertina(ImageIcon copertina) {
        this.copertina = copertina;
    }



    @Override
    public String toString() {
        return titolo;
    }

    public static Libro read(Scanner s) {
        String titolo, codice_autore, anno, genere, casa_editrice, path_descrizione, path_copertina, descrizione;
        if(!s.hasNextLine()) return null;
        titolo = s.nextLine();
        if(!s.hasNextLine()) return null;
        codice_autore = s.nextLine();
        if(!s.hasNextLine()) return null;
        anno = s.nextLine();
        if(!s.hasNextLine()) return null;
        genere= s.nextLine();
        if(!s.hasNextLine()) return null;
        casa_editrice = s.nextLine();
        if(!s.hasNextLine()) return null;
        path_descrizione = s.nextLine();
        try{
            Scanner sc = new Scanner(new File(path_descrizione));
            descrizione = sc.nextLine();
        }catch(FileNotFoundException e){
            descrizione = "File not Found";
        }
        if(!s.hasNextLine()) return null;
        path_copertina = s.nextLine();

        return new Libro(titolo, codice_autore, anno, genere, casa_editrice,descrizione,  new ImageIcon(path_copertina) );
    }


    private String titolo;
    private String codice_autore;
    private String anno;
    private String genere;
    private String casa_editrice;
    private String descrizione;
    private ImageIcon copertina;

}
