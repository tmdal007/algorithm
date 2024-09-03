package day0903;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * 백준 1753. 최단경로
 */
public class Main_1753_곽승미 {
	static class Node{
		int vertex,weight;
		Node next;
		public Node(int vertex, int weight, Node next) {
			super();
			this.vertex = vertex;
			this.weight = weight;
			this.next = next;
		}
	}
	static int minDistance[];
	static boolean[] visited;
	static final int INF = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(br.readLine());
		
		Node[] adjList = new Node[V+1];
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			adjList[from] = new Node(to, weight, adjList[from]);
		}
		getMinDistance(adjList, start);
		for(int i=1; i<V+1; i++) {
			if(minDistance[i] == INF) {
				System.out.println("INF");
			}else {
				System.out.println(minDistance[i]);
			}
		}
	}
	static void getMinDistance(Node[] adjList, int start) {
		final int V = adjList.length;
		minDistance = new int[V];
		visited = new boolean[V];
		
		Arrays.fill(minDistance, INF);
		minDistance[start] = 0; // 시작점 0으로
		
		for (int i = 0; i < V; i++) {
			//step1 : 미방문 정점 중 시작정점에서 가장 가까운 정점(stopOver) 선택
			int min = INF;
			int stopOver = -1;
			for (int j = 0; j < V; j++) {
				if(!visited[j] && min > minDistance[j]) {
					min = minDistance[j];
					stopOver = j;
				}
			}
			if(stopOver == -1) break;
			visited[stopOver] = true;
			
			//step2 : 선택된 정점(stopOver)을 경유해서 미방문 인접한 정점으로의 최소비용을 갱신할 수 있는지 체크
			for(Node temp = adjList[stopOver]; temp != null; temp = temp.next) {
				if(!visited[temp.vertex] && minDistance[temp.vertex] > min + temp.weight) {
					minDistance[temp.vertex] = min + temp.weight;
				}
			}
		}		
	}
}
