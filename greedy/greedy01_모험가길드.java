package algorithm.day_0805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.StringTokenizer;

public class greedy01_모험가길드 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		ArrayList<Integer> nlist = new ArrayList<>();
		for(int i=0; i<N; i++) {
			nlist.add(Integer.parseInt(st.nextToken()));
		}
		
		Collections.sort(nlist);
		
		int res = 0;
		int cnt = 0;
		
		for(int i=0; i<N; i++) {
			cnt += 1;
			if(cnt >= nlist.get(i)) {
				res += 1;
				cnt = 0;
			}
		}
		System.out.println(res);
	}

}
