package copy.java.raffaele;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test {
	
	private Test(){
		JFrame frame = new JFrame("GUI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		label = new JLabel("Hello");
		textField = new JTextField(20);
		
		JButton b1= new JButton("Copy");
		b1.addActionListener(new ActionListenerButton1());
		
		JButton b2 = new JButton("Delete");
		b2.addActionListener(new ActionListenerButton2());
		
		JPanel panel = new JPanel(new FlowLayout());
		panel.add(b1);
		panel.add(b2);
		
		Container c= frame.getContentPane();
		c.add(label, BorderLayout.NORTH);
		c.add(textField, BorderLayout.CENTER);
		c.add(panel, BorderLayout.SOUTH);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		Test test = new Test();
	}
	
	public class ActionListenerButton1 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			label.setText(textField.getText());
			textField.setText("");
		}
	}
	
	public class ActionListenerButton2 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			textField.setText("");
		}
		
	}
	
	private JLabel label;
	private JTextField textField;
}

