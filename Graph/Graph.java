package Graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.*;
import java.util.Queue;



public class Graph {
    public ArrayList<ArrayList<Integer>> printGraph(
        int V, ArrayList<ArrayList<Integer>> adj) {
        
            ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();

            for (int i = 0; i < adj.size(); i++) {
                ArrayList<Integer> aList = new ArrayList<>();
                aList.add(i);
                for (int j = 0; j < adj.get(i).size(); j++) {
                    aList.add(adj.get(i).get(j));
                }
                list.add(aList);
            }

            return list;
    }
    public ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        Queue<Integer> q=new LinkedList<>();
        ArrayList<Integer> list = new ArrayList<>();
    	boolean visited[] = new boolean[V+1];
    	visited[0] = true; 
    	q.add(0); 
    
    	while(q.isEmpty()==false) 
    	{ 
    		int u = q.poll(); 
    		list.add(u);
    		 
    		for(int v:adj.get(u)){
    		    if(visited[v]==false){
    		        visited[v]=true;
    		        q.add(v);
    		    }
    		} 
    	} 
        return list;
    }
    static void BFS(ArrayList<ArrayList<Integer>> adj,int s, boolean[] visited) 
	{ 
    	Queue<Integer> q=new LinkedList<>();
    	
    	visited[s] = true; 
    	q.add(s); 
    
    	while(q.isEmpty()==false) 
    	{ 
    		int u = q.poll(); 
    		 
    		for(int v:adj.get(u)){
    		    if(visited[v]==false){
    		        visited[v]=true;
    		        q.add(v);
    		    }
    		} 
    	} 
	} 
	
	static int BFSDin(ArrayList<ArrayList<Integer>> adj, int V){
	    boolean[] visited=new boolean[V];int count=0; 
    	for(int i = 0; i < V; i++) 
    		visited[i] = false;
    	for(int i=0;i<V;i++){
            if(visited[i]==false)
                {BFS(adj,i,visited);count++;}
        }
        return count;
	}

    public ArrayList<Integer> dfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited=new boolean[V];
        return DFS(V, adj, visited, 0);
    }
    public ArrayList<Integer> DFS(int V, ArrayList<ArrayList<Integer>> adj,boolean[] visited,int s){
        ArrayList<Integer> list = new ArrayList<>();
    	visited[s] = true; 
    	list.add(s);
    		for(int v:adj.get(s)){
    		    if(visited[v]==false){
                    ArrayList<Integer> list2 =    DFS(V, adj, visited, v);
                    list.addAll(list2);
    		    }
    		} 
        return list;
    }
	public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        // Code here
		boolean visited[] = new boolean[V];
		for (int i = 0; i < V; i++) {
			if(visited[i] == false){
				if(isCycleHelper(V, adj, i, -1, visited)){
					return true;
				}
			}
		}
		return false;
    }
	public static boolean isCycleHelper(int V, ArrayList<ArrayList<Integer>> adj,int s,int parent,boolean[] visited) {
		visited[s] = true;
		for (Integer integer : adj.get(s)) {
			if(visited[integer] == false ){
				if (isCycleHelper(V, adj, integer, s, visited)){
					return true;
				}
			}
			else if(integer != parent){
				return true;
			}
		}
		return false;
	}
	public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
        // code here
		boolean visited[] = new boolean[V];
		boolean isRec[] = new boolean[V];
		for (int i = 0; i < V; i++) {
			if(visited[i] == false){
				if(isCyclicHelper(V, adj, i,isRec , visited)){
					return true;
				}
			}
		}
		return false;
    }
	public static boolean isCyclicHelper(int V, ArrayList<ArrayList<Integer>> adj,int s,boolean[] isRec,boolean[] visited) {
		visited[s] = true;
		isRec[s] = true;
		for (Integer integer : adj.get(s)) {
			if(visited[integer] == false ){
				if (isCyclicHelper(V, adj, integer, isRec, visited)){
					return true;
				}
			}
			else if(isRec[integer] == true){
				return true;
			}
		}
		isRec[s] = false;
		return false;
	}

	static int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj) 
    {
        int res[] = new int[V];
		int degree[] = new int[V];
		for (int i = 0; i < V; i++) {
			for (Integer integer : adj.get(i)) {
				degree[integer]++;
			}
		}
		Queue<Integer> q = new LinkedList<>();
		for (int i = 0; i < V; i++) {
			if(degree[i] == 0){
				q.add(i);
			}
		}
		int index = 0;
		while (!q.isEmpty()) {
			int u = q.poll();
			res[index] =u;
			index++;
			for (Integer x : adj.get(u)) {
				if(--degree[x] == 0){
					q.add(x);
				}
			}
		}
		return res;
    }
	//prims algoritham
	static int spanningTree(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj) 
    {
		int res = 0;
        int key[] = new int[V];
		Arrays.fill(key, Integer.MAX_VALUE);
		boolean mvisted[] = new boolean[V];
		key[0] = 0;
		for (int count = 0; count < V; count++) {
			int u = -1;
			for (int i = 0; i < V; i++) {
				if(!mvisted[i] && (u==-1 ||key[u]>key[i])){
					u=i;
				}
			}
			mvisted[u] = true;
			res = res + key[u];
			for (ArrayList<Integer> list : adj.get(u)) {
				if(!mvisted[list.get(0)]){
					key[list.get(0)] = Math.min(key[list.get(0)], list.get(1));
				}
			}
		}
		return res;
    }
	// shortest distant
	static int[] dijkstra(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S)
    {
        int key[] = new int[V];
		Arrays.fill(key, Integer.MAX_VALUE);
		boolean mvisted[] = new boolean[V];
		key[S] = 0;
		for (int count = 0; count < V; count++) {
			int u = -1;
			for (int i = 0; i < V; i++) {
				if(!mvisted[i] && (u==-1 ||key[u]>key[i])){
					u=i;
				}
			}
			mvisted[u] = true;
			for (ArrayList<Integer> list : adj.get(u)) {
				if(!mvisted[list.get(0)]){
					key[list.get(0)] = Math.min(key[list.get(0)], key[u] + list.get(1));
				}
			}
		}
		return key;
    }
	static void orderDFS(int V,ArrayList<ArrayList<Integer>> adj,ArrayDeque<Integer> stack,boolean[] visited,int s){
		visited[s] = true;
		for (Integer b : adj.get(s)) {
			if(!visited[b]){
				orderDFS(V, adj, stack, visited, b);
			}
		}
		stack.push(s);
		return;
	}

	static ArrayList<ArrayList<Integer>> reverseEdges(int V, ArrayList<ArrayList<Integer>> adj){
		ArrayList<ArrayList<Integer>> reversedGraph = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < V; i++) {
			reversedGraph.add(new ArrayList<>());
		}
		for (int i = 0; i < V; i++) {
			for (Integer integer : adj.get(i)) {
				reversedGraph.get(integer).add(i);
			}
		}
		return reversedGraph;
	}
	public static void dfs(int V,ArrayList<ArrayList<Integer>> adj,boolean[] visit,int s){
		visit[s] = true;
		for (Integer b : adj.get(s)) {
			if(visit[b] == false){
				dfs(V, adj, visit, b);
			}
		}
	}

	// Strongly connected components
	public int kosaraju(int V, ArrayList<ArrayList<Integer>> adj)
    {
        int count  =0;
		ArrayDeque<Integer> s = new ArrayDeque<>();
		int[] orderArr = new int[V];
		boolean[] visited = new boolean[V];
		for (int i = 0; i < V; i++) {
			if(!visited[i]){
				orderDFS(V, adj, s, visited, i);
			}
		}
		int index =0;
		while(!s.isEmpty()){
			orderArr[index++] = s.pop();
		}

		ArrayList<ArrayList<Integer>> reversedGraph = reverseEdges(V, adj);
		boolean[] visit = new boolean[V];

		System.out.println(reversedGraph.toString());
		System.out.println(Arrays.toString(orderArr));
		for (int i = 0; i < V; i++) {
			if(!visit[orderArr[i]]){
				dfs(V, reversedGraph, visit, orderArr[i]);
				count++;
			}
		}

		// Arrays.fill(visit, false);
		// while (s.isEmpty() == false) 
		// { 
		// 	int v = (int)s.pop(); 

		// 	if (visited[v] == false) 
		// 	{ 
		// 		DFSUtil(V,reversedGraph, visit,v); 
		// 		System.out.println(); 
		// 	} 
		// } 
		

		return count;
    }
	static int time = 0;
	public void APUTIL(int u,boolean[] visted,int[] disc,int[] low,int[] parent,boolean[] ap,ArrayList<ArrayList<Integer>> adj){
		int childern= 0;
		visted[u] = true;
		disc[u] = low[u] = ++time;
		for (Integer integer : adj.get(u)) {
			if(!visted[integer]){
				childern++;
				parent[integer] = u;
				APUTIL(integer, visted, disc, low, parent, ap, adj);
				low[u] = Math.min(low[integer], low[u]);
				if(parent[u] == -1 && childern>1){
					ap[u] = true;
				}
				if(parent[u] != -1 && low[integer] >= disc[u]){
					ap[u] = true;
				}
			}
			else if (integer != parent[u]){
				low[u] = Math.min(low[u], disc[integer]);
			}
		}

	}
	public ArrayList<Integer> articulationPoints(int V,ArrayList<ArrayList<Integer>> adj)
    {
        int[] disc = new int[V];
		int[] low = new int[V];
		int[] parent = new int[V];
		boolean[] visted = new boolean[V];
		boolean[] ap = new boolean[V];

		Arrays.fill(parent, -1);

		for (int i = 0; i < V; i++) {
			if(!visted[i]){
			APUTIL(i, visted, disc, low, parent, ap, adj);
		}
		}
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < ap.length; i++) {
			if(ap[i]){
				list.add(i);
			}
		}
		if(list.size() == 0){
			list.add(-1);
		}
		return list;
    }
	
	public static void isBridgeUTIL(int u,boolean[] visted,int[] disc,int[] low,int[] parent,int[] bri,ArrayList<ArrayList<Integer>> adj){
		visted[u] = true;
		disc[u] = low[u] = ++time;
		for (Integer integer : adj.get(u)) {
			if(!visted[integer]){
				parent[integer] = u;
				isBridgeUTIL(integer, visted, disc, low, parent, bri, adj);
				low[u] = Math.min(low[integer], low[u]);
				
				if( low[integer] > disc[u]){
					bri[u] = integer;
					bri[integer] = u;
				}
			}
			else if (integer != parent[u]){
				low[u] = Math.min(low[u], disc[integer]);
			}
		}

	}
	static int isBridge(int V, ArrayList<ArrayList<Integer>> adj,int c,int d)
    {
        // code here
		int[] disc = new int[V];
		int[] low = new int[V];
		int[] parent = new int[V];
		boolean[] visted = new boolean[V];
		int[] bri = new int[V];

		Arrays.fill(parent, -1);
		Arrays.fill(bri, -1);

		for (int i = 0; i < V; i++) {
			if(!visted[i]){
				isBridgeUTIL(i, visted, disc, low, parent, bri, adj);
		}
		}
		System.out.println(

			Arrays.toString(bri)
		);
		return (bri[c] == d || bri[d] == c)?1:0;
    }
	public static int sockMerchant(int n, List<Integer> ar) {
		HashSet<Integer> hSet = new HashSet<>();
		int count = 0;
		for (Integer integer : ar) {
			if(hSet.contains(integer)){
				hSet.remove(integer);
				count++;
			}else{
				hSet.add(integer);
			}
		}
	return count;
		}
		
}
