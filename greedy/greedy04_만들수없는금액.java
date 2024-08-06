package algorithm.day_0806;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class greedy04_만들수없는금액 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		ArrayList<Integer> money = new ArrayList<>();
		for(int i=0; i<N; i++) {
			money.add(Integer.parseInt(st.nextToken()));
		}
		Collections.sort(money); // 동전 리스트 오름차순 정렬
		
		int num = 1;
		
		for(int i=0; i<N; i++) {
			if(money.get(i) > num) {
				break;
			}
			num += money.get(i);
		}
		System.out.println(num);
	}

}
