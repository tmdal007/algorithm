package a0814.add;

import java.io.*;
import java.util.*;

public class Solution_d4_3234_준환이의양팔저울_지역_반_홍길동 {
	static int N,ans,w[],a[];
	static boolean[] v;
	
	static void subs(int cnt,int left,int right){
		if(left<right) return;
		if(cnt==N){
			ans++;
			return;
		}
		subs(cnt+1,left+a[cnt],right       );
		subs(cnt+1,left       ,right+a[cnt]);
	}
	static void perm(int cnt){
		if(cnt==N){
			subs(0,0,0);
			return;
		}
		for(int i=0; i<N; i++){
			if(v[i]) continue;
			v[i]=true;
			a[cnt]=w[i];
			perm(cnt+1);
			v[i]=false;
		}
	}
	public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("res/input_d4_3234.txt"));
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=null;
        StringBuilder sb=new StringBuilder();
        int T=Integer.parseInt(br.readLine());        
        for(int tc=1; tc<=T; tc++){
        	N=Integer.parseInt(br.readLine());
        	w=new int[N]; a=new int[N];
        	v=new boolean[N];
        	st=new StringTokenizer(br.readLine()," ");
        	for(int i=0; i<N; i++) w[i]=Integer.parseInt(st.nextToken());
        	ans=0;
        	perm(0);
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.print(sb.toString());
        br.close();
    }
}
