package algorithm.day_0805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_2493_곽승미 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] height = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(st.nextToken());
			height[i] = num; // 탑 높이 받기
		}
		
		Stack<Integer> stack = new Stack<>();
		int[] result = new int[N];
		
		for(int i=0; i<N; i++) {
			// 현재 탑의 높이와 스택에 저장된 탑의 높이 비교한 후,
			// 현재 탑보다 낮은 높이의 탑은 스택에서 제거(현재 탑보다 높은 첫번째 탑 찾기)
			while(!stack.isEmpty() && height[stack.peek()] <= height[i]) {
				stack.pop();
			}
			if(!stack.isEmpty()) {
				result[i] = stack.peek()+1;
			}else {
				result[i] = 0;
			}
			stack.push(i);
		}
		// 결과 출력
		for(int i=0; i<N; i++) {
			System.out.printf(result[i]+" ");
		}
	}
}
