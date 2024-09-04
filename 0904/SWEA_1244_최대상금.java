package day0904;
/*
 * SWEA 1244. 최대상금
 * DFS로 풀이
 * Set을 통한 중복 제거
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class SWEA_1244_최대상금 {
	
	static char[] c;
	static int result,cnt, nlist[];
	static Set<String> v;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			String num = st.nextToken();
			cnt = Integer.parseInt(st.nextToken());
			c = num.toCharArray();
			nlist = new int[c.length]; 
			for(int i=0; i<c.length; i++) {
				nlist[i] = c[i] -'0';
			}
			v = new HashSet<>();
			result = 0;
			dfs(0);
			System.out.println("#"+tc+" "+result);
		}

	}
	static int dfs(int n) {
		if(n == cnt) {
			int cur = cal(nlist);
//			System.out.println("nlist : " + Arrays.toString(nlist));
//			System.out.println("스왑 완료 후 값 : " +cur);
			result = Math.max(result,cur);
			return result;
		}
		for(int i=0; i<nlist.length-1; i++) {
			for(int j=i+1; j<nlist.length; j++) {
				swap(i,j);
				
				int curNum = cal(nlist);
				String state = n+":"+curNum;
//				System.out.println(curNum);
				
				if(!v.contains(state)) { // 위치 스왑 시 중복은 패스
					v.add(state);
					dfs(n+1);
				}
				swap(i,j); // 원복
			}
		}
		return -1;
	}
	static void swap(int i, int j) {
		int temp = nlist[i];
		nlist[i] = nlist[j];
		nlist[j] = temp;
	}
	static int cal(int[] num) {
		int ans = 0;
		int n = 1;
		for(int i=nlist.length-1; i>=0; i--) {
			ans += nlist[i]*n;
			n *= 10;
		}
		return ans;
	}
}
