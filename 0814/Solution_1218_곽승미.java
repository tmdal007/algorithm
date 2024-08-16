package day0814;
//SWEA 1218.괄호 짝짓기
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution_1218_곽승미 {
	static String[] left = {"{","[","(","<"};
	static String[] right = {"}","]",")",">"};
	static int N;
	static Stack<String> stack = new Stack<>();
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("res/s_1218_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for(int t=1; t<11; t++) {
			N = Integer.parseInt(br.readLine());
			String str = br.readLine();

			if(check(str)) {
				sb.append("#").append(t).append(" ").append(1).append("\n");
			}else {
				sb.append("#").append(t).append(" ").append(0).append("\n");
			}
		}
		System.out.println(sb.toString());
	}
	static boolean check(String str) {
		for(int n=0; n<N; n++) {
			char c = str.charAt(n);
			for(int i=0; i<4; i++) {
				if(String.valueOf(c).equals(left[i])) {
					stack.add(String.valueOf(c));
					break;
				}
				else if(String.valueOf(c).equals(right[i])) {
					if(stack.isEmpty()) return false;
					if(stack.peek().equals(left[i])) {
						stack.pop();
						break;
					}else {
						return false;
					}
				}
			}
		}
		return true;
	}
}

