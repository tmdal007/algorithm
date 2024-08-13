package day0813;

import java.io.*;
import java.util.*;

public class Solution_d9_4013_특이한자석_광주_4반_곽승미{
	static int[][] a=new int[4][8];
	
	static void rotate(int n, int d){
		if(d==1){//1: 시계(7 0 1 2 3 4 5 6)
			int T=a[n][7]; for(int i=7; i>0; i--) a[n][i]=a[n][i-1]; a[n][0]=T;
		}else{//-1: 반시계(1 2 3 4 5 6 7 0)
			int T=a[n][0]; for(int i=0; i<7; i++) a[n][i]=a[n][i+1]; a[n][7]=T;
		}
	}
    public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_d9_4013.txt"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=null;
        StringBuilder sb=new StringBuilder();
        int T=Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++){
        	int K=Integer.parseInt(br.readLine());
        	for(int i=0; i<4; i++){
        		st=new StringTokenizer(br.readLine()," ");
        		for(int j=0; j<8; j++){
            		a[i][j]=Integer.parseInt(st.nextToken());
            	}
        	}
        	//for(int[] b:a) System.out.println(Arrays.toString(b));System.out.println();
			for(int k=0; k<K; k++){
				st=new StringTokenizer(br.readLine()," ");
				int n=Integer.parseInt(st.nextToken())-1;//인덱스화(0,1,2,3)
				int d=Integer.parseInt(st.nextToken());//1시계,-1반시계
				int[] dir=new int[4];//회전방향기억
				dir[n]=d;
				for(int i=n; i<3; i++){//오른쪽체크
					if(a[i][2]!=a[i+1][6]) dir[i+1]=-dir[i];
				}
				for(int i=n; i>0; i--){//왼쪽체크
					if(a[i-1][2]!=a[i][6]) dir[i-1]=-dir[i];
				}
				for(int i=0; i<4; i++){
					if(dir[i]!=0) rotate(i,dir[i]);
				}
			}
			sb.append("#").append(tc).append(" ").append(a[0][0]+2*a[1][0]+4*a[2][0]+8*a[3][0]).append("\n");
        }
        System.out.print(sb.toString());
        br.close();
    }
}