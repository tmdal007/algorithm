package algorithm.day_0805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class greedy03_문자열뒤집기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		int cnt0 = 0;
		int cnt1 = 1;
		int first = str.charAt(0) - '0';
		
		if(first == 0) {
			cnt0 = 1;
		}else {
			cnt1 = 1;
		}
		
		for(int i=0; i < str.length(); i++) {
			int next = str.charAt(i) - '0';
			if(next != first) {
				if(next == 0) {
					cnt0++;
				}else {
					cnt1++;
				}
				first = next;
			}
		}
		System.out.println(Math.min(cnt0, cnt1));
	}
}
