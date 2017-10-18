package it.unisannio.orange.libreria2;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Autore {

    public Autore(String nome, String cognome, String codice_autore, String bio, ImageIcon img) {
        libri = new DefaultListModel<Libro>();
        this.nome = nome;
        this.cognome = cognome;
        this.codice_autore = codice_autore;
        this.bio = bio;
        this.img = img;
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

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public ImageIcon getImg() {
        return img;
    }

    public void setImg(ImageIcon img) {
        this.img = img;
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
        String nome, cognome, codice_autore, bio, path_img, path_bio;

        if(!sc.hasNextLine()) return null;
        nome=sc.nextLine();
        if(!sc.hasNextLine()) return null;
        cognome=sc.nextLine();
        if(!sc.hasNextLine()) return null;
        codice_autore=sc.nextLine();
        if(!sc.hasNextLine()) return null;
        path_bio=sc.nextLine();
        try{
            Scanner sc2 = new Scanner(new File(path_bio));
            bio = sc2.nextLine();

        }catch (FileNotFoundException e){
            bio = "il file non è stato trovato";

        }

        if(!sc.hasNextLine()) return null;
        path_img=sc.nextLine();


        return new Autore(nome, cognome, codice_autore, bio, new ImageIcon(path_img));
    }

    private String nome;
    private String cognome;
    private String codice_autore;
    private String bio;
    private ImageIcon img;
    private DefaultListModel<Libro> libri;
}

