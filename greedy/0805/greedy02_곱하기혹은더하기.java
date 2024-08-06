package algorithm.day_0805;

import java.util.Scanner;

public class greedy02_곱하기혹은더하기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String str = sc.nextLine();
		
		long res = str.charAt(0) - '0';
		
		for(int i=1; i<str.length(); i++) {
			int num = str.charAt(i) - '0';
			
			if(num <=1 || res <= 1) {
				res += num;
			}else {
				res *= num;
			}
		}
		System.out.println(res);
	}

}
