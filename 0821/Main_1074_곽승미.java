package day0821;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1074_곽승미 {
	static int cnt;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		find(r,c, (int) Math.pow(2, N)); // r:행, c:열, size:2^N
		
		System.out.println(cnt);
	}
	static void find(int r, int c, int size) {
		if(size == 1) {//size가 1이되면 종료
			return;
		}
		// (r,c)가 1사분면에 위치
		if(r<size/2 && c<size/2) {
			find(r,c,size/2);
		// (r,c)가 2사분면에 위치
		}else if(r<size/2 && size/2 <=c) {
			cnt += (size*size/4);
			find(r,c-size/2,size/2);
		// (r,c)가 3사분면에 위치
		}else if(size/2 <= r && c < size/2) {
			cnt += (size*size/4)*2;
			find(r-size/2,c,size/2);
		// (r,c)가 4사분면에 위치
		}else {
			cnt += (size*size/4)*3;
			find(r-size/2,c-size/2,size/2);
		}
	}

}
