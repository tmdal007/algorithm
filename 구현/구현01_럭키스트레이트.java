package algorithm.day0807;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 구현01_럭키스트레이트 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String left = s.substring(0,s.length()/2);
		String right = s.substring(s.length()/2);
		
		int leftsum = 0;
		int rightsum = 0;
		for(int i=0; i<left.length(); i++) {
			leftsum += left.charAt(i)-'0';
			rightsum += right.charAt(i)-'0';
		}
		
		if(leftsum == rightsum) {
			System.out.println("LUCKY");
		}else {
			System.out.println("READY");
		}
		
	}
}
