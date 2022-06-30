package com.searchingalgorithms;
import java.util.*;

public class Graph {
//	  private LinkedList<Integer> adjLists[];
	  private boolean visited[];
	  private boolean found =false;
	  boolean reached = false;
	  boolean startSearch = true;
	  Vector<Integer> path;
	  private int Vertices;

	  // Graph creation
	  Graph(int vertices) {;
	  Vertices = vertices;
	    Edge.adjLists = new LinkedList[Vertices];
	    

	    for (int i = 0; i < vertices; i++)
	      Edge.adjLists[i] = new LinkedList<Integer>();
	  }

	  // DFS algorithm
	  void DFS(int vertex, int stopp) {
		  long start = System.nanoTime();
		visited = new boolean[Vertices];
	    visited[vertex] = true;
	    if(startSearch == true) {
	    	path = new Vector<Integer>();
	    	startSearch = false;
	    }
	    found=false;
	    if(BuildGraph.nodes.contains(stopp)!=false) {
	    	path.add(vertex);
	    	if(vertex == stopp) {
	    		long end = System.nanoTime();
	    		long runtime = end -start;
		    	found = true;
		    	startSearch = true;
		    	Searches.path.setText("\n"+path.toString()+"\nRunning time: "+runtime+"ns");
	    	}
		    	

		    Iterator<Integer> iterator = Edge.adjLists[vertex].listIterator();
		    while (iterator.hasNext()) {
		    	if(found == true) {
		    		break;
		    	}
		      int adj = iterator.next();
		      if (!visited[adj])
		        DFS(adj, stopp);
		      
		    }
	    }
	    else
			  System.out.println("Element is NOT present in graph");
	    
	  }
	  
	  void BFS(int source, int dest) {
		  long start = System.nanoTime();
		  visited = new boolean[Vertices];
		  path = new Vector<Integer>();
		  startSearch = true;
		    LinkedList<Integer> queue = new LinkedList();

		    if(BuildGraph.nodes.contains(dest)!=false) {
		    	visited[source] = true;
		    	queue.add(source);

			    while (queue.size() != 0) {
			      source = queue.poll();
			      path.add(source);
			      if(source == dest) {
			    		long end = System.nanoTime();
			    		long runtime  = end - start;
			    	  Searches.path.setText("\n"+path.toString()+"\nRunning time: "+runtime+"ns");
				    	break;
			      }
			      

			      Iterator<Integer> i = Edge.adjLists[source].listIterator();
			      while (i.hasNext()) {
			        int n = i.next();
			        if (!visited[n]) {
			          visited[n] = true;
			          queue.add(n);
			        }
			      }
			    }
		    }
		    else
				  System.out.println("Element is NOT present in graph");
		    
	  }
	  
	  int DLS(int src, int target, int limit) { 
		  path.add(src);
	      if (src == target) {
	    	  reached = true;
	          return target;
	      }

	      // If reached the maximum depth, stop recursing. 
	      if (limit <= 0) {
	          return -1; 
	      }

	      // Recur for all the vertices adjacent to source vertex 
	      Iterator<Integer> c = Edge.adjLists[src].listIterator();
	     
	      while (c.hasNext()) {
	    	  int j = c.next();
	    	  if(DLS(j, target, limit-1)==target) {
	    		  reached = true;
	    		  return target;
	    	  }
	      }
	      return -999;
	  } 
	  void IDDFS(int src, int target) { 
		  long start = System.nanoTime();
		  reached = false;
		  startSearch = true;
	      // Repeatedly depth-limit search till the 
	      // maximum depth.
		  int depth_allowed =0;
		  if(BuildGraph.nodes.contains(target)!=false) {
			  while(depth_allowed >=0 ) {
			    	if(reached ==true) {
			    		long end = System.nanoTime();
			    		long runtime  = end - start;
			    		Searches.path.setText("\n"+path.toString()+"\nRunning time: "+runtime+"ns");
			    		break;
			    	}
					 path = new Vector<Integer>();
					 
			         DLS(src, target, depth_allowed);
			         depth_allowed +=1;
			    }
		  }
		  else
			  System.out.println("Element is NOT present in graph");
		    

	  } 
	  
	  public static int no_of_elements(int[] array, int element) {
		  int count = 0;
		  for(int i=0; i<array.length; i++) {
			  if(array[i] == element) {
				  count++;
			  }
		  }
		  return count;
	  }
}

