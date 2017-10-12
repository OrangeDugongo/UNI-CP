package it.unisannio.orange.libreria2;

import javax.swing.*;
import java.util.Scanner;

public class Autore {

    public Autore(String nome, String cognome, String codice_autore, String path_bio, String path_img) {
        this.nome = nome;
        this.cognome = cognome;
        this.codice_autore = codice_autore;
        this.path_bio = path_bio;
        this.path_img = path_img;
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

    public String getCodice_autore() {
        return codice_autore;
    }

    public void setCodice_autore(String codice_autore) {
        this.codice_autore = codice_autore;
    }

    public String getPath_bio() {
        return path_bio;
    }

    public void setPath_bio(String path_bio) {
        this.path_bio = path_bio;
    }

    public String getPath_img() {
        return path_img;
    }

    public void setPath_img(String path_img) {
        this.path_img = path_img;
    }

    public DefaultListModel<Libro> getLibri() {
        return libri;
    }

    public void setLibri(DefaultListModel<Libro> libri) {
        this.libri = libri;
    }


    public String toString() {
        return nome + " " + cognome;
    }


    public void addLibro(Libro libro) {
        libri.addElement(libro);
    }


    public static Autore read(Scanner sc) {
        String nome, cognome, codice_autore, path_bio, path_img;

        if(!sc.hasNextLine()) return null;
        nome=sc.nextLine();
        if(!sc.hasNextLine()) return null;
        cognome=sc.nextLine();
        if(!sc.hasNextLine()) return null;
        codice_autore=sc.nextLine();
        if(!sc.hasNextLine()) return null;
        path_bio=sc.nextLine();
        if(!sc.hasNextLine()) return null;
        path_img=sc.nextLine();

        return new Autore(nome, cognome, codice_autore, path_bio, path_img);
    }

    private String nome;
    private String cognome;
    private String codice_autore;
    private String path_bio;
    private String path_img;
    private DefaultListModel<Libro> libri;
}

