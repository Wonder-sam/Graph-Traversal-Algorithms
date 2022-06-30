package com.searchingalgorithms;

public class Graph_Node {
	 public final String value;
     public double g_scores;
     public final double h_scores;
     public double f_scores = 0;
     public Graph_Edge[] adjacencies;
     public Graph_Node parent;

     public Graph_Node(String val, double hVal){
             value = val;
             h_scores = hVal;
     }

     public String toString(){
             return value;
     }
}
