package com.searchingalgorithms;

import java.util.*;

public class A_starGraphSearch {
	

public static List<Graph_Node> printPath(Graph_Node target){
    List<Graph_Node> path = new ArrayList<Graph_Node>();

for(Graph_Node node = target; node!=null; node = node.parent){
path.add(node);
}

Collections.reverse(path);

return path;
}

public static void AstarSearch(Graph_Node source, Graph_Node goal){

    Set<Graph_Node> explored = new HashSet<Graph_Node>();

    PriorityQueue<Graph_Node> queue = new PriorityQueue<Graph_Node>(20, 
            new Comparator<Graph_Node>(){
                     //override compare method
     public int compare(Graph_Node i, Graph_Node j){
        if(i.f_scores > j.f_scores){
            return 1;
        }

        else if (i.f_scores < j.f_scores){
            return -1;
        }

        else{
            return 0;
        }
     }

            }
            );

    //cost from start
    source.g_scores = 0;

    queue.add(source);

    boolean found = false;

    while((!queue.isEmpty())&&(!found)){

            //the node in having the lowest f_score value
            Graph_Node current = queue.poll();

            explored.add(current);

            //goal found
            if(current.value.equals(goal.value)){
                    found = true;
            }

            //check every child of current node
            for(Graph_Edge e : current.adjacencies){
                    Graph_Node child = e.target;
                    double cost = e.cost;
                    double temp_g_scores = current.g_scores + cost;
                    double temp_f_scores = temp_g_scores + child.h_scores;


                    /*if child node has been evaluated and 
                    the newer f_score is higher, skip*/
                    
                    if((explored.contains(child)) && 
                            (temp_f_scores >= child.f_scores)){
                            continue;
                    }

                    /*else if child node is not in queue or 
                    newer f_score is lower*/
                    
                    else if((!queue.contains(child)) || 
                            (temp_f_scores < child.f_scores)){

                            child.parent = current;
                            child.g_scores = temp_g_scores;
                            child.f_scores = temp_f_scores;

                            if(queue.contains(child)){
                                    queue.remove(child);
                            }

                            queue.add(child);

                    }

            }

    }

}

}
