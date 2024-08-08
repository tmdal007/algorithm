package algorithm.day0808;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 구현05_왕실의나이트 {

	public static void main(String[] args) throws IOException {
		// 나이트가 움직일 수 있는 방향(8가지 방향)
      	int[][] deltas = {{-2, -1}, {-2, 1}, {2, -1}, {2, 1},
					{-1, -2}, {1, -2}, {-1, 2}, {1, 2}};
      	
      	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      	String input = br.readLine();
      	
      	// 입력받은 문자열 첫번째 문자(알파벳->숫자), 두번째 숫자 저장
      	int y = input.charAt(0) - 'a' + 1;
      	int x = Integer.parseInt(input.substring(1));
      	
		int cnt = 0;
		for(int i=0; i<8; i++) {
			int nx = x + deltas[i][0];
			int ny = y + deltas[i][1];
			
			// 이동 후 범위 내에 있으면 cnt 증가
			if(nx >= 1 && nx<=8 && ny >= 1 && ny <= 8) {
				cnt++;
			}
		}
		System.out.println(cnt);
	}
}
