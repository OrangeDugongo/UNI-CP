package it.unisannio.orange.libreria2;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;

public class Test {

    public Test() {

        try{
            readLibri(new Scanner(new File ("res/file/libri.txt")));
        }catch(FileNotFoundException e){
            System.err.println("Il file non è stato trovato.");
            System.exit(1);
        }

        //Frame
        JFrame frame = new JFrame("Libreria");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(2,2));

        //Elenco 1x1
        JPanel elenco = new JPanel();
        elenco.setLayout(new GridLayout(1,2));
        //Lista autori
        lista_autori = new JList<Autore>();
        lista_autori.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lista_autori.addListSelectionListener(new ListaAutoriListener());
        JScrollPane scroll_autori = new JScrollPane();
        scroll_autori.setViewportView(lista_autori);
        //Lista libri
        lista_libri = new JList<Libro>();
        lista_libri.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lista_libri.addListSelectionListener(new ListaLibriListener());
        JScrollPane scroll_libri = new JScrollPane();
        scroll_libri.setViewportView(lista_libri);
        //Add
        elenco.add(scroll_autori);
        elenco.add(scroll_libri);

        //Info 1x2
        JPanel info = new JPanel();
        info.setLayout(new GridLayout(5,2));
        //Titolo
        JLabel titolo = new JLabel("Titolo:");
        titoloD = new JLabel();
        //anno
        JLabel anno = new JLabel("Anno:");
        annoD = new JLabel();
        //Genere
        JLabel genere = new JLabel("Genere:");
        genereD = new JLabel();
        //Casa_editrice
        JLabel casa_editrice = new JLabel("Casa Editrice:");
        casa_editriceD = new JLabel();
        //Load
        JLabel space = new JLabel();
        JButton load = new JButton("load");
        //Add
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
        //Foto
        JCheckBox foto = new JCheckBox("Foto", false);
        labelfoto = new JLabel();
        JScrollPane scroll_foto = new JScrollPane();
        scroll_foto.setViewportView(labelfoto);
        scroll_foto.setVisible(foto.isSelected());
        foto.addActionListener(new CheckListener(scroll_foto));
        //Biografia
        JCheckBox bio = new JCheckBox("Bio", false);
        biotxt = new JTextArea();
        biotxt.setFocusable(false);
        biotxt.setEditable(false);
        biotxt.setLineWrap(true);
        biotxt.setWrapStyleWord(true);
        biotxt.setVisible(bio.isSelected());
        bio.addActionListener(new CheckListener(biotxt));
        //Add
        m_autore.add(foto);
        m_autore.add(bio);
        m_autore.add(scroll_foto);
        m_autore.add(biotxt);

        //media libro 2x2
        JPanel m_libro = new JPanel();
        m_libro.setLayout(new GridLayout(2,2));
        //Copertina
        JCheckBox copertina = new JCheckBox("Copertina", false);
        labelcopertina = new JLabel();
        JScrollPane scroll_copertina = new JScrollPane();
        scroll_copertina.setViewportView(labelcopertina);
        scroll_copertina.setVisible(copertina.isSelected());
        copertina.addActionListener(new CheckListener(scroll_copertina));
        //Descrizione
        JCheckBox descrizione = new JCheckBox("Descrizione", false);
        descrizionetxt = new JTextArea();
        descrizionetxt.setFocusable(false);
        descrizionetxt.setEditable(false);
        descrizionetxt.setLineWrap(true);
        descrizionetxt.setWrapStyleWord(true);
        descrizionetxt.setVisible(copertina.isSelected());
        descrizione.addActionListener(new CheckListener(descrizionetxt));
        //Add
        m_libro.add(copertina);
        m_libro.add(descrizione);
        m_libro.add(scroll_copertina);
        m_libro.add(descrizionetxt);

        //Add
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

    public void readAutori(Scanner sc){

        Autore a = Autore.read(sc);
        while(a!=null){
            for(Libro l: tutti_libri)
                if(l.getCodice_autore().equals(a.getCodice_autore()))
                    a.addLibro(l);
            autori.addElement(a);
            a = Autore.read(sc);
        }
    }

    public void readLibri(Scanner sc){
        tutti_libri = new ArrayList<Libro>();
        Libro l = Libro.read(sc);
        while(l!=null)
        {
            tutti_libri.add(l);
            l=Libro.read(sc);
        }
    }


    private ArrayList<Libro> tutti_libri;
    private DefaultListModel<Autore> autori;
    private JList<Autore> lista_autori;
    private JList<Libro> lista_libri;
    private JTextArea biotxt;
    private JLabel labelcopertina;
    private JLabel labelfoto;
    private JLabel titoloD;
    private JLabel annoD;
    private JLabel genereD;
    private JLabel casa_editriceD;
    private JTextArea descrizionetxt;




    private class LoadListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            autori = new DefaultListModel<Autore>();

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showOpenDialog(null);

            try{
                readAutori(new Scanner(fileChooser.getSelectedFile()));
            }catch(FileNotFoundException e){
                System.err.println("File Not Found");
                System.exit(1);
            }

            lista_autori.setModel(autori);
        }
    }

    private class ListaAutoriListener implements javax.swing.event.ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent listSelectionEvent) {
            if (!lista_autori.isSelectionEmpty()) {
                Autore a = autori.get(lista_autori.getSelectedIndex());
                lista_libri.setModel(a.getLibri());
                biotxt.setText(a.getBio());
                labelfoto.setIcon(a.getImg());
            }
        }
    }

    private class ListaLibriListener implements javax.swing.event.ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent listSelectionEvent) {
            if (!lista_libri.isSelectionEmpty()){
                Libro l = autori.get(lista_autori.getSelectedIndex()).getLibri().get(lista_libri.getSelectedIndex());
                titoloD.setText(l.getTitolo());
                annoD.setText(l.getAnno());
                genereD.setText(l.getGenere());
                casa_editriceD.setText(l.getCasa_editrice());
                descrizionetxt.setText(l.getDescrizione());
                labelcopertina.setIcon(l.getCopertina());
            }
        }
    }

    private class CheckListener implements ActionListener {
        public CheckListener(JComponent c){
            this.c = c;
        }
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            c.setVisible(((JCheckBox)actionEvent.getSource()).isSelected());
        }

        JComponent c;
    }
}
