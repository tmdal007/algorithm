package day0822;

import java.util.Iterator;
import java.util.Scanner;


// 같은 행에 퀸을 두지 않는 방식
public class NQueenTest {
	
	static int N, col[], ans;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		col = new int[N+1]; // 1행부터 사용
		
		setQueens(1);
		System.out.println(ans);
	
	}
	static void setQueens(int rowNo) {
		
		if(!isAavailable(rowNo-1)) return;
		
		if(rowNo > N) {
			// 무조건 답
			ans++;
			return;
		}
		
		for (int c = 1; c <= N; c++) {
			col[rowNo] = c;
			setQueens(rowNo+1);
			
		}
	}
	static boolean isAavailable(int rowNo) {
		
		for (int k = 1; k < rowNo; k++) {
			if(col[rowNo] == col[k] || rowNo-k == Math.abs(col[rowNo]-col[k])) {
				return false;
			}
		}
		
		return true;
	}

}
