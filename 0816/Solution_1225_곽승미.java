package day0816;
// SWEA 1225. 암호생성기
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1225_곽승미 {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/s_1225.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for(int tc=1; tc<=10; tc++) {
			int n = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			int[] num = new int[8];
			for(int i=0; i<8; i++) {
				num[i] = Integer.parseInt(st.nextToken());
			}
			boolean flag = true;
			while(flag) {
				for(int t=1; t<6; t++) {
					if(num[0] !=0) {
						int tmp = num[0];
						for(int idx=0; idx<7; idx++) {
							num[idx] = num[idx+1];
						}
						num[7] = tmp-t;
					if(num[7] <=0) {
						num[7] = 0;
						flag = false;
						break;
					}
					}
				}
			}
			System.out.printf("#%d",tc);
			for(int i=0; i<8; i++) {
				System.out.print(" "+num[i]);
			}
			System.out.println();
		}
	}
}
