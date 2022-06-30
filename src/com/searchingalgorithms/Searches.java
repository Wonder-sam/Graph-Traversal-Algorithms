package com.searchingalgorithms;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.*;

public class Searches extends JFrame{
	private JLabel head;
	private JLabel source_text;
	private JLabel destination_text;
	private JTextField source;
	private JTextField destination;
	private JButton bfs;
	private JButton dfs;
	private JButton ids;
	private JButton aplus;
	protected static JTextPane path;
	private Insets leftInset = new Insets(10,15,0,5);
	private Insets breakpair = new Insets(10,0,0,15);
	private Insets around = new Insets(10,15,0,15);

	public Searches() {
		// TODO Auto-generated constructor stub
		head = new JLabel("Feel Free and Perform a Search", SwingConstants.CENTER);
		source_text = new JLabel("Start");
		destination_text = new JLabel("Goal");
		source = new JTextField();
		destination = new JTextField();
		bfs = new JButton("BF-Search");
		dfs = new JButton("DF-Search");
		ids = new JButton("ID-Search");
		aplus = new JButton("A*-Search");
		path = new JTextPane();
		path.setText("\n<path will appear here>");
		StyledDocument doc = path.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		Search performSearch = new Search();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		bfs.addActionListener(performSearch);
		dfs.addActionListener(performSearch);
		ids.addActionListener(performSearch);
		aplus.addActionListener(performSearch);
		
		
		setTitle("Searching Algorithms");
		Container pane = getContentPane();
		pane.setBackground(new Color(177,182,251));
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets= leftInset;
		c.gridx = 0;
		c.gridy= 0;
		c.gridwidth = 2;
		pane.add(head,c);
		c.insets = new Insets(10,15,0,0);
		c.gridx = 0;
		c.gridy= 1;
		c.weightx =1;
		c.gridwidth = 1;
		pane.add(source_text,c);
		c.insets = new Insets(10,0,0,0);
		c.gridx = 1;
		c.gridy= 1;
		c.weightx =1;
		c.gridwidth = 1;
		pane.add(destination_text,c);
		c.insets = new Insets(0,15,0,5);
		c.gridx = 0;
		c.gridy= 2;
		c.ipady=10;
		c.weightx =1;
		c.gridwidth = 1;
		pane.add(source,c);
		c.insets= new Insets(0,0,0,15);
		c.gridx = 1;
		c.gridy= 2;
		c.ipady=10;
		c.weightx =1;
		c.gridwidth = 1;
		pane.add(destination,c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = around;
		c.gridx = 0;
		c.gridy= 3;
		c.gridwidth = 2;
		c.gridheight=2;
		c.ipady=25;
		pane.add(path,c);
		c.insets = leftInset;
		c.gridx = 0;
		c.gridy= 5;
		c.gridwidth = 1;
		c.weightx =1;
		pane.add(bfs,c);
		c.insets = breakpair;
		c.gridx = 1;
		c.gridy= 5;
		c.gridwidth = 1;
		c.weightx =1;
		pane.add(ids,c);
		c.insets = leftInset;
		c.gridx = 0;
		c.gridy= 9;
		c.gridwidth = 1;
		c.weightx =1;
		pane.add(dfs,c);
		c.insets = breakpair;
		c.gridx = 1;
		c.gridy= 9;
		c.gridwidth = 1;
		c.weightx =1;
		pane.add(aplus,c);
		Border border = BorderFactory.createLineBorder(Color.GRAY, 1);
		path.setBorder(border);
		path.setEditable(false);
		setSize(400,450);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	private class Search implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int start = Integer.parseInt(source.getText());
			int goal = Integer.parseInt(destination.getText());
			String astart = source.getText();
			String agoal = destination.getText();
			if(e.getActionCommand()=="BF-Search") {
				 BuildGraph.general_graph.BFS(start,goal);
			}
			if(e.getActionCommand()=="DF-Search") {
				BuildGraph.general_graph.DFS(start,goal);
			}
			if(e.getActionCommand()=="ID-Search") {
				BuildGraph.general_graph.IDDFS(start, goal);
			}
			if(e.getActionCommand()=="A*-Search") {
	    		long tstart = System.nanoTime();
				A_starGraphSearch.AstarSearch(BuildGraph.graph_nodes.elementAt(BuildGraph.heuristic_nodes.indexOf(astart)), BuildGraph.graph_nodes.elementAt(BuildGraph.heuristic_nodes.indexOf(agoal)));
	 		    List<Graph_Node> search_path = A_starGraphSearch.printPath(BuildGraph.graph_nodes.elementAt(BuildGraph.heuristic_nodes.indexOf(agoal)));
	    		long end = System.nanoTime();
	    		long runtime  = end - tstart;
	 		     path.setText("\n"+search_path.toString()+"\nRunning time: "+runtime+"ns");
			}
				
			
		}
	}
}
