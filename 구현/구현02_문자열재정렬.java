package algorithm.day0807;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 구현02_문자열재정렬 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		
		List<Character> list = new ArrayList<>();
		int sum = 0;
		for(int i=0; i<s.length(); i++) {
			if(s.charAt(i)-'0' >= 10) {
				list.add(s.charAt(i));
			}else {
				sum += s.charAt(i)-'0';
			}
		}
		Collections.sort(list);
		
		String res = "";
		for(char c : list) {
			res += c;
		}
		System.out.println(res+""+sum);
	}
}
