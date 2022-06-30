package com.searchingalgorithms;

import java.util.LinkedList;

public class Edge {
	protected static LinkedList<Integer> adjLists[];
	
	
	public Edge(int vertices) {
		// TODO Auto-generated constructor stub
		 adjLists = new LinkedList[vertices];
	}
	static void addEdge(int src, int dest) {
	    adjLists[src].add(dest);
	  }
}
