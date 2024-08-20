package day0820;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4796_곽승미 {
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/s_4796_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int w = 3;
		while(w<=9) {
			for(int i=0; i<N-3; i++) {
//				if(arr[i])
			}
		}
		
		
		
	
	}

}
