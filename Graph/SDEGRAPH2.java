package Graph;

import java.util.*;

class Pair implements Comparable<Pair> {
	int first;
	int second;

	public Pair(int x, int y) {
		this.first = x;
		this.second = y;
	}

	@Override
	public int compareTo(Pair o) {
		return this.second - o.second;
	}

}

public class SDEGRAPH2 {
	public static void topoSort(int node, ArrayList<ArrayList<Pair>> adjList, Stack<Integer> stack,
			boolean visited[]) {
		visited[node] = true;

		for (Pair adjNode : adjList.get(node)) {
			int an = adjNode.v;
			if (!visited[an]) {
				topoSort(an, adjList, stack, visited);
			}
		}

		stack.push(node);
	}

	public int[] shortestPath(int N, int M, int[][] edges) {
		// we need to do find the topo sort to make sure that before processing this
		// node and its upcoming node paths
		// we have minimum distance of this node and it is visited from all its possible
		// paths
		// so algorithms steps goes
		// make a adjlist with a pair of ajdNode and weight
		// find the toposort of the graph
		// in toposort order traverse the all paths of all node
		// and update the dist as Minimum

		// Note:- src is always 0

		ArrayList<ArrayList<Pair>> adjList = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			adjList.add(new ArrayList<>());
		}

		for (int i = 0; i < M; i++) {
			// there is an edge from u to v with wegith wg
			int u = edges[i][0];
			int v = edges[i][1];
			int wg = edges[i][2];
			adjList.get(u).add(new Pair(v, wg));
		}

		// find the toposort of this
		Stack<Integer> stack = new Stack<>();
		boolean[] visited = new boolean[N];

		topoSort(0, adjList, stack, visited);

		int[] dist = new int[N];
		for (int i = 0; i < N; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		dist[0] = 0;

		while (!stack.isEmpty()) {
			int node = stack.pop();

			for (Pair adjEdg : adjList.get(node)) {
				if (dist[node] + adjEdg.wg < dist[adjEdg.v]) {
					dist[adjEdg.v] = dist[node] + adjEdg.wg;
				}
			}
		}

		for (int i = 0; i < N; i++) {
			if (dist[i] == Integer.MAX_VALUE) {
				dist[i] = -1;
			}
		}

		return dist;

	}

	public int[] shortestPath(int[][] edges, int n, int m, int src) {
		/*
		 * Algo is that we first make the adjList and then start from src
		 * do the bfs and find the shortest dist
		 */

		ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			adjList.add(new ArrayList<>());
		}

		for (int i = 0; i < m; i++) {
			adjList.get(edges[i][0]).add(edges[i][1]);
			adjList.get(edges[i][1]).add(edges[i][0]);
		}

		int[] dist = new int[n];
		Arrays.fill(dist, Integer.MAX_VALUE);

		ArrayDeque<Integer> queue = new ArrayDeque<>();
		dist[src] = 0;
		queue.add(src);

		while (!queue.isEmpty()) {
			int node = queue.remove();

			for (Integer adjNode : adjList.get(node)) {
				if (dist[node] + 1 < dist[adjNode]) {
					dist[adjNode] = dist[node] + 1;
					queue.add(adjNode);
				}
			}
		}

		for (int i = 0; i < n; i++) {
			if (dist[i] == Integer.MAX_VALUE) {
				dist[i] = -1;
			}
		}

		return dist;
	}

	public int wordLadderLength(String startWord, String targetWord, String[] wordList) {
		/*
		 * we need traverse in  a bfs manner
		 * change the each char in word from 'a' to 'z' and check if exist in wordList 
		 * if yes add it into queue
		 * once found the word return the step
		 */

		HashSet<String> hSet = new HashSet<>(Arrays.asList(wordList));

		ArrayDeque<Pair> qDeque = new ArrayDeque<>();
		qDeque.add(new Pair(startWord, 1));
		
		// remove the start word
		if(hSet.contains(startWord))
			hSet.remove(startWord);

		while (!qDeque.isEmpty()) {
			Pair word =  qDeque.remove();

			if(word.first.equals(targetWord)) return word.second;

			for(int i = 0; i < word.first.length(); i++){
				char[] wordCharArr = word.first.toCharArray();
				for(char ch = 'a'; ch <='z'; ch++){
					wordCharArr[i] = ch;
					String replacedWord = new String(wordCharArr);
					if(hSet.contains(replacedWord)){
						qDeque.add(new Pair(replacedWord, word.second+1));
						hSet.remove(replacedWord);
					}
				}
			}
		}
		return 0;
	}

	public ArrayList<ArrayList<String>> findSequences(String startWord, String targetWord, String[] wordList)
    {
        /*
		 * instead of steps we need to return the sequence
		 * so instead of pair i will store an arraylist in queue where last element will be word and size of list will be size
		 */

		ArrayList<ArrayList<String>> ansList =  new ArrayList<>();

		 ArrayDeque<ArrayList<String>> qDeque = new ArrayDeque<>();
		 ArrayList<String> unsetWordsOnLevel =  new ArrayList<>();


		HashSet<String> hSet =  new HashSet<>(Arrays.asList(wordList));
		ArrayList<String> seqList = new ArrayList<>();
		seqList.add(startWord);
		qDeque.add(seqList);
		int level = 0;
		while (!qDeque.isEmpty()) {
			ArrayList<String> currSeqList =  qDeque.remove();
			//remove unsetWords from hset
			if(currSeqList.size() > level){
				for (String string : unsetWordsOnLevel) {
					hSet.remove(string);
				}
				level++;
			}

			String word =  currSeqList.get(currSeqList.size() - 1);
			if(word.equals(targetWord)){
				if(ansList.size() == 0){
					ansList.add(currSeqList);
				}else if(ansList.get(0).size() == currSeqList.size()){
					ansList.add(currSeqList);
				}
			}


			for (int i = 0; i < word.length(); i++) {
				char[] wordCharArr = word.toCharArray();
				for(char ch = 'a'; ch <='z'; ch++){
					wordCharArr[i] = ch;
					String replacedWord = new String(wordCharArr);
					if(hSet.contains(replacedWord)){
						currSeqList.add(replacedWord);
						qDeque.add(new ArrayList<>(currSeqList));
						unsetWordsOnLevel.add(replacedWord);
						currSeqList.remove(replacedWord);
					}
				}
			}
			
		}
		return ansList;
    }

	HashMap<String, Integer> stepCoutMap =  new HashMap<>();

	// public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    //     /*
	// 	 * we need traverse in  a bfs manner
	// 	 * change the each char in word from 'a' to 'z' and check if exist in wordList 
	// 	 * if yes add it into queue
	// 	 * once found the word return the step
	// 	 */

	// 	HashSet<String> hSet = new HashSet<>(wordList);

	// 	ArrayDeque<String> qDeque = new ArrayDeque<>();
	// 	qDeque.add(beginWord);
	// 	stepCoutMap.put(beginWord, 1);
	
		
	// 	// remove the start word
	// 	if(hSet.contains(beginWord))
	// 		hSet.remove(beginWord);

	// 	while (!qDeque.isEmpty()) {
	// 		String word =  qDeque.remove();
	// 		int steps =  stepCoutMap.get(word);

	// 		if(word.equals(endWord)) return steps;

	// 		for(int i = 0; i < word.length(); i++){
	// 			char[] wordCharArr = word.toCharArray();
	// 			for(char ch = 'a'; ch <='z'; ch++){
	// 				wordCharArr[i] = ch;
	// 				String replacedWord = new String(wordCharArr);
	// 				if(hSet.contains(replacedWord)){
	// 					qDeque.add(replacedWord);
	// 					stepCoutMap.put(replacedWord, steps + 1);
	// 					hSet.remove(replacedWord);
	// 				}
	// 			}
	// 		}
	// 	}
	// 	return 0;
    // }


	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        /*
		 * we need traverse in  a bfs manner
		 * change the each char in word from 'a' to 'z' and check if exist in wordList 
		 * if yes add it into queue
		 * once found the word return the step
		 */

		HashSet<String> hSet = new HashSet<>(wordList);

		ArrayDeque<Pair> qDeque = new ArrayDeque<>();
		qDeque.add(new Pair(beginWord, 1));
		
		// remove the start word
		if(hSet.contains(beginWord))
			hSet.remove(beginWord);

		while (!qDeque.isEmpty()) {
			Pair word =  qDeque.remove();

			if(word.first.equals(endWord)) return word.second;

			for(int i = 0; i < word.first.length(); i++){
				char[] wordCharArr = word.first.toCharArray();
				for(char ch = 'a'; ch <='z'; ch++){
					wordCharArr[i] = ch;
					String replacedWord = new String(wordCharArr);
					if(hSet.contains(replacedWord)){
						qDeque.add(new Pair(replacedWord, word.second+1));
						hSet.remove(replacedWord);
					}
				}
			}
		}
		return 0;
    }



	//Function to find the shortest distance of all the vertices
    //from the source vertex S.
    static int[] dijkstra(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S)
    {
       /*
		* Algorithm is very straight forward we need to go through the edges starting from source
		and traverse the shortest distance first so that we now we are firstly processing shortest distance
	    */

		// we will maintain shortest arr
		int[] res  = new int[V];
		Arrays.fill(res, Integer.MAX_VALUE);

		//We will use PriorityQueue (min heap) to always first get the minimum distance vertex
		// In pair first element will be vertex and second distance

		PriorityQueue<Pair> minHeap = new PriorityQueue<>();

		res[S] = 0;
		minHeap.add(new Pair(S, 0));

		while(!minHeap.isEmpty()){
			Pair curr = minHeap.remove();

			for(ArrayList<Integer> edge : adj.get(curr.first)){
				if (curr.second + edge.get(1) < res[edge.get(0)]){
					res[edge.get(0)] = curr.second + edge.get(1);
					minHeap.add(new Pair(edge.get(0), res[edge.get(0)]));
				}
			}
		}

		return res;
    }
}
