package it.unisannio.orange.libreria2;


import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.*;

public class Test {

    public Test() {

        //Frame
        JFrame frame = new JFrame("Libreria");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(2,2));

        //Elenco 1x1
        JPanel elenco = new JPanel();
        elenco.setLayout(new FlowLayout());
        //Lista autori
        lista_autori = new JList<Autore>();
        JScrollPane scroll_autori = new JScrollPane();
        scroll_autori.setViewportView(lista_autori);
        //Lista libri
        lista_libri = new JList<Libro>();
        JScrollPane scroll_libri = new JScrollPane();
        scroll_libri.setViewportView(lista_libri);
        elenco.add(scroll_autori);
        elenco.add(scroll_libri);

        //Info 1x2
        JPanel info = new JPanel();
        info.setLayout(new GridLayout(5,2));
        //Titolo
        JLabel titolo = new JLabel("Titolo:");
        JLabel titoloD = new JLabel();
        //anno
        JLabel anno = new JLabel("Anno:");
        JLabel annoD = new JLabel();
        //Genere
        JLabel genere = new JLabel("Genere:");
        JLabel genereD = new JLabel();
        //Casa_editrice
        JLabel casa_editrice = new JLabel("Casa Editrice:");
        JLabel casa_editriceD = new JLabel();
        //Load
        JLabel space = new JLabel();
        JButton load = new JButton("load");
        load.addActionListener(new LoadListener());

        info.add(titolo);
        info.add(titoloD);
        info.add(anno);
        info.add(annoD);
        info.add(genere);
        info.add(genereD);
        info.add(casa_editrice);
        info.add(casa_editriceD);
        info.add(space);
        info.add(load);

        //media autore 2x1
        JPanel m_autore = new JPanel();
        m_autore.setLayout(new GridLayout(2,2));
        JCheckBox bio = new JCheckBox("Bio", false);
        JCheckBox foto = new JCheckBox("Foto", false);
        JLabel biotxt = new JLabel("test bio");
        ImageIcon imgfoto = new ImageIcon();
        JLabel labelfoto = new JLabel("test foto");
        m_autore.add(foto);
        m_autore.add(bio);
        m_autore.add(labelfoto);
        m_autore.add(biotxt);

        //media libro 2x2
        JPanel m_libro = new JPanel();
        m_libro.setLayout(new GridLayout(2,2));
        JCheckBox descrizione = new JCheckBox("Descrizione", false);
        JCheckBox copertina = new JCheckBox("Copertina", false);
        JLabel descrizionetxt = new JLabel("test descrizione");
        ImageIcon imgcopertina = new ImageIcon();
        JLabel labelcopertina = new JLabel("test copertina");
        m_libro.add(copertina);
        m_libro.add(descrizione);
        m_libro.add(labelcopertina);
        m_libro.add(descrizionetxt);

        frame.getContentPane().add(elenco);
        frame.getContentPane().add(info);
        frame.getContentPane().add(m_autore);
        frame.getContentPane().add(m_libro);


        frame.pack();
        frame.setVisible(true);

    }



    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new Test();
    }

    public void read(Scanner sc){

        Autore a = Autore.read(sc);
        while(a!=null){
            autori.addElement(a);
            a = Autore.read(sc);
        }
    }


    private JList<Autore> lista_autori;
    private JList<Libro> lista_libri;
    private DefaultListModel<Autore> autori;
    private DefaultListModel<Libro> libri;

    private class LoadListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            autori = new DefaultListModel<Autore>();

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showOpenDialog(null);

            try{
                  read(new Scanner(fileChooser.getSelectedFile()));
            }catch(FileNotFoundException e){
                System.err.println("File Not Found");
                System.exit(1);
            }

            lista_autori.setModel(autori);
        }
    }
}
