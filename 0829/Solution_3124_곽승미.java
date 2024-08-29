package day0829;
/*
 * SWEA 3124. 최소 스패닝 트리
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_3124_곽승미 {

	static int V,E;
	static int[] parents;
	
	static void make() {
		parents = new int[V+1];
		for(int i=1; i<V+1; i++) {
			parents[i] = -1;
		}// Arrays.fill(parents,-1);
	}
	static int findSet(int a) {
		if(parents[a] < 0) return a;
		return parents[a] = findSet(parents[a]); // 집합의 대표자를 자신의 부모로 변경 : 패스 압축!
		
	}
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if(aRoot == bRoot) {
			return false;
		}
		// 편의상 a집합에 b집합을 붙임 -> 집합에 크기에따라 붙이도록 처리 가능!
		parents[aRoot] += parents[bRoot]; // 집합 크기관리(절대값을 사용하면 집합의 크기가 됨)
		parents[bRoot] = aRoot;
		return true;
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=1; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			Edge[] edges = new Edge[E];
			for(int i=0; i<E; i++) {
				st = new StringTokenizer(br.readLine());
				edges[i] = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			Arrays.sort(edges); // 간선의 가중치 기준 오름차순 정렬
			make(); // 모든 정점을 분리집합으로..(단위 서로소 집합 <트리> 생성)
			
			int cnt=0;
			long cost = 0; // 정점의 개수와 간선의 개수가 많기 때문에 long으로 선언...
			for(Edge edge : edges) {
				if(union(edge.start, edge.end)) {
					cost += edge.weight;
					if(++cnt == V) break; // 최소신장트리 완성!
				}
			}
			System.out.println("#"+tc+" "+cost);
		}

	}
	static class Edge implements Comparable<Edge>{
		int start,end,weight;
		
		public Edge(int start, int end, int weight) {
			super();
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
}
