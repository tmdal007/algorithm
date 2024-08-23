package day0823;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class AdjListTest {

	static class Node{
		int to;
		Node next;
		
		public Node(int to, Node next) {
			super();
			this.to = to;
			this.next = next;
		}

		@Override
		public String toString() {
			return "Node [to=" + to + ", next=" + next + "]";
		}	
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int V = sc.nextInt(); // 정점 개수
		int E = sc.nextInt(); // 간선 개수
		
		ArrayList<Integer>[] adjList1 = new ArrayList[V]; // 각 노드의 리스트
		for (int i = 0; i < V; i++) {
			adjList1[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < E; ++i) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			
			// 첫노드로 삽입하는 알고리즘
			adjList1[from].add(to);
			adjList1[to].add(from); 
		}
		
//		Node[] adjList = new Node[V]; // 각 노드의 연결리스트의 헤드리스트
//		
//		for (int i = 0; i < E; ++i) {
//			int from = sc.nextInt();
//			int to = sc.nextInt();
//			
//			// 첫노드로 삽입하는 알고리즘
//			adjList[from] = new Node(to, adjList[from]);
//			adjList[to] = new Node(from, adjList[to]); 
//		}
		
		for (int i = 0; i < V; i++) {
			System.out.println(adjList1[i]);
		}
	}

}
