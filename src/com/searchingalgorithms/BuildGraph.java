package com.searchingalgorithms;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class BuildGraph extends JFrame{
	JLabel graph_build_text;
	JButton next_window;
	JTextField[] edge_inputs = new JTextField[Integer.parseInt(Welcome.edges)*2];
	protected static int[] graph_vertices = new int[Integer.parseInt(Welcome.edges)*2];
	protected static  Set<Integer> nodes;
	protected static Vector<String> heuristic_nodes;
	protected static Vector<String> heuristic_values;
	protected static Vector<Graph_Node> graph_nodes;
	protected static Vector<Integer> node_count;
	protected static Vector<Integer> weights;
	private Insets leftInset = new Insets(10,15,0,5);
	private Insets breakpair = new Insets(10,0,0,20);
	protected static Graph general_graph ;
	
 	public BuildGraph() {
		graph_build_text = new JLabel("enter the elements joined by each in each textbox");
		next_window = new JButton("Continue");
		for(int i=0; i<Integer.parseInt(Welcome.edges)*2; i++) {
			edge_inputs[i]= new JTextField();
		}
		SaveEdges save_vertices = new SaveEdges();
		next_window.addActionListener(save_vertices);
		Container pane = getContentPane();
		pane.add(graph_build_text);
		pane.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = leftInset;
		gbc.gridx = 0;
		gbc.gridy= 0;
		gbc.gridwidth = 4;
		pane.add(graph_build_text,gbc);
		int x = 0;
		int y = 1;
		for(int j=0; j<(Integer.parseInt(Welcome.edges)*2); j++) {

			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridx = x;
			gbc.ipady = 10;
			gbc.gridy= y;
			gbc.weightx =1;
			gbc.gridwidth = 1;
			pane.add(edge_inputs[j],gbc);
			x=x+1;
			if(x%2==1) {
				gbc.insets=breakpair;
			}
			else
				gbc.insets=leftInset;
			if(x>3) {
				x= 0;
				gbc.insets=leftInset;
				y=y+1;
			}
		}
		gbc.gridx=0;
		gbc.gridy= y+1;
		gbc.weightx = 1.5;
		gbc.gridwidth = 4;
		gbc.insets = new Insets(20,60,0,60);
		pane.add(next_window,gbc);
	
	
		setSize(350,300);
		setLocationRelativeTo(null); //displays the window in he center of the screen
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
 	
 	private class SaveEdges implements ActionListener{
 		public void actionPerformed(ActionEvent e) {
 			nodes = new HashSet<Integer>();
 			heuristic_values = new Vector<String>();
 			heuristic_nodes = new Vector<String>();
 			graph_nodes = new Vector<Graph_Node>();
 			node_count = new Vector<Integer>();
 			weights = new Vector<Integer>();
 			int[] indexes = new int[Integer.parseInt(Welcome.no_of_nodes)];
 			for (int i=0; i<Integer.parseInt(Welcome.edges)*2; i++) {
 				graph_vertices[i] = Integer.parseInt(edge_inputs[i].getText());
 				nodes.add(Integer.parseInt(edge_inputs[i].getText()));
 				if(!heuristic_nodes.contains(edge_inputs[i].getText())) {
 					heuristic_nodes.add(edge_inputs[i].getText());
 					String temp = JOptionPane.showInputDialog("enter heuritstic value for node " + edge_inputs[i].getText());
 					heuristic_values.add(temp);
 					graph_nodes.add(new Graph_Node(edge_inputs[i].getText(), Double.parseDouble(temp)));
 					
 				}
 			}

 			
 			System.out.println(nodes);
 			for(int j=0; j<nodes.size(); j++) {
 				indexes[j]=0;
 				int count_temp = Graph.no_of_elements(graph_vertices, Integer.parseInt(heuristic_nodes.elementAt(j)));
 				node_count.add(count_temp);
 				graph_nodes.elementAt(j).adjacencies = new Graph_Edge[count_temp];
 			}
 			System.out.println(node_count);
 			
 				
 			general_graph = new Graph(Integer.parseInt(Welcome.no_of_nodes)+1);
 			for(int j=0; j<Integer.parseInt(Welcome.edges)*2; j+=2) {
 				Edge.addEdge(graph_vertices[j],graph_vertices[j+1]);
 				weights.add(Integer.parseInt(JOptionPane.showInputDialog("enter weight for edge " +graph_vertices[j]+"--->"+graph_vertices[j+1])));
 			}
 			int step = 0;
 			for(int c=0; c<Integer.parseInt(Welcome.edges)*2; c+=2) {
				int targ_node1 = heuristic_nodes.indexOf(""+graph_vertices[c]+"");
				int targ_node2 = heuristic_nodes.indexOf(""+graph_vertices[c+1]+"");
				System.out.println(graph_nodes.elementAt(targ_node1)+" "+graph_nodes.elementAt(targ_node2));
				graph_nodes.elementAt(targ_node1).adjacencies[indexes[targ_node1]]=new Graph_Edge(graph_nodes.elementAt(targ_node2),weights.elementAt(step));
				indexes[targ_node1] +=1;
				graph_nodes.elementAt(targ_node2).adjacencies[indexes[targ_node2]]=new Graph_Edge(graph_nodes.elementAt(targ_node1),weights.elementAt(step));
				indexes[targ_node2]+=1;
				step+=1;
			}
 			
 			System.out.println(heuristic_values);
 			System.out.println(graph_nodes);
 			
 			dispose();
 			new Searches();
 		}
 		
 	}

}
