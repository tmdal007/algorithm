package algorithm.day0808;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 구현04_상하좌우 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String[] input = new String[st.countTokens()];
		
		for(int i=0; i<input.length; i++) {
			input[i] = st.nextToken();
		}
		
		
		int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}}; //상,하,좌,우
		String[] move = {"U","D","L","R"}; // 상 하 좌 우
		
		int x = 1;
		int y = 1;
		int nx = 0;
		int ny = 0;
		
		// 입력받은 문자열에서 하나씩 읽어와서 상,하,좌,우 확인 후 dir 인덱스에 맞춰 이동 진행
		for(String m : input) {
			for(int j=0; j<move.length; j++) {
				if(m.equals(move[j])) {
					nx = x + dir[j][0];
					ny = y + dir[j][1];
					
                    // 좌표 범위 체크 - (1,1)부터 시작하는 것에 주의!
                    if(nx < 1 || nx > N || ny < 1 || ny > N) {
                        continue;
                    }

				x = nx;
				y = ny;
				}
			}
		}
		System.out.println(x + " " + y);
	}
}

