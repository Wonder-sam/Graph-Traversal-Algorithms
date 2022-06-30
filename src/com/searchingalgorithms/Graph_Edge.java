package com.searchingalgorithms;

public class Graph_Edge {
	public final double cost;
    public final Graph_Node target;

    public Graph_Edge(Graph_Node targetNode, double costVal){
            target = targetNode;
            cost = costVal;
    }
}
