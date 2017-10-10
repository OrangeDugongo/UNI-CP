package Libreria.java.raffaele;

import java.awt.BorderLayout;
import java.awt.Container;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.*;
import javax.swing.event.*;
import java.util.Scanner;

public class Test extends JFrame{
	public Test(){
		
		//Inizializzo l'array per la lista
		libri = new DefaultListModel<Libro>();
		//Inizializzo la lista
		lista = new JList<Libro>(libri);
		//Imposto la modalità di selezione
		lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	//SINGLE_SELECTION=uno per volta
		//Listner che specifica cosa fare quando seleziono un elemento
		lista.addListSelectionListener(new ShowElemListener());  
		
		try {
			//Leggo gli elementi da file per popolare la lista
			read(new Scanner(new File("list.txt")));
		}catch(FileNotFoundException e) {
			System.err.println("File not found");
			System.exit(1);
		}
		
		//Inizializzo lo ScrollPane
		JScrollPane scroll = new JScrollPane();
		//Aggiungo la lista allo ScrollPane
		scroll.setViewportView(lista);  

		//Inizializzo il frame
		JFrame frame = new JFrame("Libreria");
		//Imposto la chiusura 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Estraggo il ContentPane dal frame
		Container c = frame.getContentPane();
		//Aggiungo una label
		c.add(new JLabel ("Lista Libri"), BorderLayout.NORTH);
		//Aggiungo lo ScrollPane al frame
		c.add(scroll, BorderLayout.CENTER);
		
		//Rendo visibile la GUI
		frame.pack();
		frame.setVisible(true);
		
		
	}
	
	public static void main(String[] args){
		new Test();
	}
	
	public void read(Scanner s) {
		Libro l = Libro.read(s);
		while(l!=null) {
			libri.addElement(l);
			l = Libro.read(s);
		}
		
	}
	
	
	public class ShowElemListener implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			Libro l = libri.get(lista.getSelectedIndex());
			//Creo una finestra di dialogo con le info sul libro selezionato
			JOptionPane.showMessageDialog(null, "Titolo: "+l.getTitolo()+"\nAutore: "+l.getAutore().toString()+"\nGenere: "+l.getGenere()+"\nCasa Editrice: "+l.getCasa_editrice()+"\nAnno: "+l.getAnno());
		}
		
	}
	
	DefaultListModel<Libro> libri;
	JList<Libro> lista;
	
}
