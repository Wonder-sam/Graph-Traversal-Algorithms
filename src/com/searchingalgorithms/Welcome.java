package com.searchingalgorithms;
import java.awt.Container;
import java.awt.event.*;

import javax.swing.*;

public class Welcome extends JFrame {
	JButton begin;
	JLabel welcome_text;
	JLabel info_text;
	protected static String edges;
	protected static String no_of_nodes;
	public Welcome(){
		begin = new JButton("Begin");
		welcome_text = new JLabel("Welcome to Searching Alogrithms", SwingConstants.CENTER);
		info_text = new JLabel("Hit the Begin button whenever you're ready", SwingConstants.CENTER);
		
		setTitle("Searching Algorithms");
		Begin proceed = new Begin();
		begin.addActionListener(proceed);
		Container pane = getContentPane();
		pane.setLayout(null);
		welcome_text.setSize(300,30);
		welcome_text.setLocation(30, 30);
		info_text.setSize(300,30);
		info_text.setLocation(30,60);
		begin.setSize(200, 50);
		begin.setLocation(70, 150);
		pane.add(welcome_text);
		pane.add(info_text);
		pane.add(begin);
		
		setSize(350,300);
		setLocationRelativeTo(null); //displays the window in he center of the screen
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}
	
	private class Begin implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dispose();
			edges = JOptionPane.showInputDialog("Enter the number of Edges for your Graph");
			no_of_nodes = JOptionPane.showInputDialog("Enter the number of elements for your Graph");
			new BuildGraph();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    new Welcome();
	}

}
