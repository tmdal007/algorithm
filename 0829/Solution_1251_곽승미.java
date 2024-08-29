package day0829;
/*
 * SWEA 1251. 하나로
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution_1251_곽승미 {

	static int V;
	static float L;
	static int[] parents;
	
	static void make() {
		parents = new int[V];
		for(int i=0; i<V; i++) {
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
		System.setIn(new FileInputStream("res/s_1251_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int tc=1; tc<=T; tc++) {
			V = Integer.parseInt(br.readLine());
			
			double[] x = new double[V];
			double[] y = new double[V];
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<V; i++) {
				x[i] = Long.parseLong(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<V; i++) {
				y[i] = Long.parseLong(st.nextToken());
			}
			L = Float.parseFloat(br.readLine());
			ArrayList<double[]> vlist = new ArrayList<>();
			
			for(int i=0; i<V; i++) {
				vlist.add(new double[] {x[i],y[i]});
			}
			
			ArrayList<Edge> elist = new ArrayList<>();
			
			for(int i=0; i<vlist.size()-1; i++) {
				for(int j=i+1; j<vlist.size(); j++) {
//					long d = Math.abs(vlist.get(i)[0] - vlist.get(j)[0]) + Math.abs(vlist.get(i)[1] - vlist.get(j)[1]);
					double x1 = Math.abs(vlist.get(i)[0] - vlist.get(j)[0]);
					double y1 = Math.abs(vlist.get(i)[1] - vlist.get(j)[1]);
					double d = x1*x1 + y1*y1;
					double weight = (d*L);
					elist.add(new Edge(i, j, weight));
				}
			}
			
			Collections.sort(elist);; // 간선의 가중치 기준 오름차순 정렬
			make(); // 모든 정점을 분리집합으로..(단위 서로소 집합 <트리> 생성)
			
			int cnt=0;
			double cost = 0; 

			for(int i=0; i<elist.size(); i++) {
				if(union(elist.get(i).start,elist.get(i).end)) {
					cost += elist.get(i).weight;
					if(++cnt == V-1) break;
				}
			}
			System.out.println("#"+tc+" "+(long)Math.round(cost));
		}

	}
	static class Edge implements Comparable<Edge>{
		double weight;
		int start, end;
		
		public Edge(int start, int end, double weight) {
			super();
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.weight, o.weight);
		}
		
	}

}
