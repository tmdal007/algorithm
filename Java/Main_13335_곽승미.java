package algorithm.day_0805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_13335_곽승미 {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		Queue<Integer> tlist = new ArrayDeque<>(); 	// 트럭 큐
		Queue<Integer> bridge = new ArrayDeque<>(); // 다리 큐
		
		StringTokenizer s = new StringTokenizer(br.readLine()," "); 
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(s.nextToken());
			tlist.offer(num); // 트럭 입력 받아 트럭 큐에 저장
		}
		
		for(int i=0; i<W; i++) {
			bridge.offer(0); // 다리 길이만큼 다리 큐에 '0' 저장
		}
		
		int bweight = 0;
		int tweight = 0;
		int cnt = 0;
		while(!bridge.isEmpty()) { // 다리 큐가 모두 비워질 때까지
			cnt++; // while문 도는 동안 시간 증가
			bweight -= bridge.poll(); // 다리 큐에서 하나 꺼내서 다리 무게에 빼기
			if(tlist.peek() != null) { // 트럭 큐가 비어있지 않다면 현재 트럭 값 확인
				tweight = tlist.peek();
			}else {
				continue; // 비어있다면 다음으로
			}

			// 다리 무게와 트럭 무게가 최대하중보다 작거나 같고,트럭 큐가 비어있지 않다면
			// 트럭 큐에서 하나 꺼내 다리 큐에 넣고, 다리무게에 트럭 무게 더하기
			if(tweight+bweight <= L && !tlist.isEmpty()) { 
				bridge.offer(tlist.poll());
				bweight += tweight;
			}
			// 그렇지 않다면 다리 큐에 0 추가(한 칸 이동)
			else {
				bridge.offer(0);
				
			}
//			System.out.printf("cnt : %d, bweight = %d, tweight = %d, N: %d\n", cnt,bweight,tweight,N);
//			System.out.println(bridge);
		}
		System.out.println(cnt);
		
	}
}
