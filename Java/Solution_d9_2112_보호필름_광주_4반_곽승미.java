package day0813;

import java.io.*;
import java.util.*;

public class Solution_d9_2112_보호필름_광주_4반_곽승미{
	static int D,W,K,min,inject[],map[][];
	
	static boolean check(){
		outer:for(int w=0; w<W; w++){
			int cnt=1;
			for(int d=0; d<D-1; d++){
				int curr=inject[d  ]==-1? map[d  ][w]:inject[d  ];
				int next=inject[d+1]==-1? map[d+1][w]:inject[d+1];
				if(curr==next){
					cnt++;
					//if(cnt>=K) break;
					if(cnt>=K) continue outer;
				}else{
					cnt=1;
				}
			}
			if(cnt<K) return false;
		}
		return true;
	}
	static void subs(int row, int cnt){
		if(cnt>=min) return;
		if(row==D){
			if(check()) min=Math.min(min,cnt);
			return;
		}
		inject[row]=-1;//그대로
		subs(row+1,cnt+0);
		inject[row]=0;//A투입
		subs(row+1,cnt+1);
		inject[row]=1;//B투입
		subs(row+1,cnt+1);
	}
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_d9_2112.txt"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=null;
		StringBuilder sb=new StringBuilder();		
        int T=Integer.parseInt(br.readLine());        
        for(int tc=1; tc<=T; tc++){
        	st=new StringTokenizer(br.readLine()," ");
        	D=Integer.parseInt(st.nextToken());
        	W=Integer.parseInt(st.nextToken());
        	K=Integer.parseInt(st.nextToken());
        	map=new int[D][W];
        	inject=new int[D];
        	for(int d=0; d<D; d++){
        		st=new StringTokenizer(br.readLine()," ");
        		for(int w=0; w<W; w++){
        			map[d][w]=Integer.parseInt(st.nextToken());
        		}
        	}
        	//for(int[] a:map) System.out.println(Arrays.toString(a));System.out.println();
        	min=K;
        	subs(0,0);//row행,cnt주입수
            sb.append("#").append(tc).append(" ").append(min).append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }
}