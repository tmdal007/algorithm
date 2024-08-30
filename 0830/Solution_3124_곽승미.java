package day0830;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge>{
	int w;
	int cost;
	
	Edge(int  w, int cost){
		this.w = w;
		this.cost = cost;
	}
	
	@Override
	public int compareTo(Edge o) {
		return this.cost - o.cost;
	}
}

public class Solution_3124_곽승미 {
	static List<Edge>[] graph;
	
	public static long prim(int start, int n) {
		boolean[] visit = new boolean[n + 1];
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(start, 0));
		
		long total = 0;
		while(!pq.isEmpty()) {
			Edge edge = pq.poll();
			int v = edge.w;
			int cost = edge.cost;
			
			if(visit[v]) continue;
            
			visit[v] = true;
			total += cost;
			
			for(Edge e : graph[v]) {
				if(!visit[e.w]) {
					pq.add(e);
				}
			}
		}
		return total;
	}

	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/s_3124_input.txt"));
   		// 그래프 입력, 저장
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
	        // 그래프 선언, 간선 리스트로 표현
			graph = new ArrayList[V + 1];
			for (int i = 0; i < graph.length; i++) graph[i] = new ArrayList<>();
			
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int v = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				
				graph[v].add(new Edge(w, cost));
				graph[v].add(new Edge(v, cost));
			}
			
	        // 프림 알고리즘 수행
			long res = prim(1, V);
			
			System.out.println("#"+tc+" "+res);
		}
		
	}
}
