package a0814.add;

import java.io.*;
import java.util.*;

public class Solution_d9_5644_무선충전_지역_반_홍길동{
	static int[] dx={0,0,1,0,-1};
	static int[] dy={0,-1,0,1,0};//문제 그대로 상우하좌
	static int M,AP,bc[][],A[],pathA[],B[],pathB[];
	
	static int check(int ap, int x, int y) { //해당ap 거리로 충전량 체크
		return Math.abs(bc[ap][0]-x)+Math.abs(bc[ap][1]-y)<=bc[ap][2]? bc[ap][3]:0;
	}
	static int getMaxCharge() { //중복순열 3ㅠ2=3^2=9  x  1  2  3
		int max=0;                                  // 1 11 12 13
		for(int a=0; a<AP; a++){                    // 2 21 22 23
			for(int b=0; b<AP; b++){                // 3 31 32 33
				int sum=0;
				int amountA=check(a,A[0],A[1]);
				int amountB=check(b,B[0],B[1]);
				if(a!=b) sum=amountA+amountB;
				else     sum=Math.max(amountA,amountB);
				max=Math.max(max,sum);
			}
		}
		return max;
	}
	static int move() {
		int total=0;
		for(int t=0; t<=M; t++){
			A[0]+=dx[pathA[t]]; A[1]+=dy[pathA[t]];
			B[0]+=dx[pathB[t]]; B[1]+=dy[pathB[t]];
			total+=getMaxCharge();
		}
		return total;
	}
	public static void main(String[] args) throws Exception{
    	System.setIn(new FileInputStream("res/input_d9_5644.txt"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=null;
		StringBuilder sb=new StringBuilder();
		int T=Integer.parseInt(br.readLine());		
		for(int tc=1; tc<=T; tc++){
			st=new StringTokenizer(br.readLine()," ");
			M=Integer.parseInt(st.nextToken());
			AP=Integer.parseInt(st.nextToken());			
			A=new int[]{1,1};
			B=new int[]{10,10};
			pathA=new int[M+1];
			st=new StringTokenizer(br.readLine()," ");
			for(int i=1; i<=M; i++) pathA[i]=Integer.parseInt(st.nextToken());
			pathB=new int[M+1];
			st=new StringTokenizer(br.readLine()," ");
			for(int i=1; i<=M; i++) pathB[i]=Integer.parseInt(st.nextToken());			
			bc=new int[AP][4];
			for(int i=0; i<AP; i++){
				st=new StringTokenizer(br.readLine()," ");
				bc[i][0]=Integer.parseInt(st.nextToken());//X
				bc[i][1]=Integer.parseInt(st.nextToken());//Y
				bc[i][2]=Integer.parseInt(st.nextToken());//C거리
				bc[i][3]=Integer.parseInt(st.nextToken());//P충전량
			}			
			sb.append("#").append(tc).append(" ").append(move()).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
}