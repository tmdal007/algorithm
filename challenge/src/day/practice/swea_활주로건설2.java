package day.practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea_활주로건설2 {
    
    static int N, L, result;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  
        
        int T = Integer.parseInt(br.readLine());
        
        for (int tc = 1; tc <= T; tc++) {
        	
        	 StringTokenizer st = new StringTokenizer(br.readLine());
             
             N = Integer.parseInt(st.nextToken());
             L = Integer.parseInt(st.nextToken());
             
             map = new int[N][N];
             
             for (int i = 0; i < N; i++) {
                 st = new StringTokenizer(br.readLine());
                 for (int j = 0; j < N; j++) {
                     map[i][j] = Integer.parseInt(st.nextToken());
                 }
             }
             result = 0; 
             
             // 행 검사
             for (int i = 0; i < N; i++) {
                 if (cal(map[i])) {
                     result++;
                 }
             }
             
             // 열 검사
             for (int j = 0; j < N; j++) {
                 int[] col = new int[N];
                 for (int i = 0; i < N; i++) {
                     col[i] = map[i][j];
                 }
                 if (cal(col)) {
                     result++;
                 }
             }
             
             System.out.println("#"+tc+" "+result);
		}
       
    }

    
    // 설치 가능 여부를 확인 후 설치
    public static boolean cal(int[] line) {
        boolean[] visited = new boolean[N];  // 경사로 설치 여부 저장 배열
        
        for (int i = 0; i < N - 1; i++) {
            if (line[i] == line[i + 1]) {
                continue; // 같은 높이면 패스
            }
            else if (line[i] + 1 == line[i + 1]) { // 오르막 경사 확인
                if (!install(line, visited, i, -1)) {
                    return false;
                }
            }
            
            
            else if (line[i] - 1 == line[i + 1]) { // 내리막 경사 확인
                if (!install(line, visited, i + 1, 1)) {
                    return false;
                }
            }
            else { // 높이 차이가 1보다 크면 활주로 건설 불가
                return false;
            }
        }
        
        return true;
    }

    // 경사로 설치 가능 여부를 확인하고 설치
    public static boolean install(int[] line, boolean[] visited, int start, int d) {
        for (int i = 0; i < L; i++) {
            int check = start + i * d;
            if (check < 0 || check >= N || visited[check] || line[start] != line[check]) {
                return false;
            }
            visited[check] = true; // 경사로 설치
        }
        return true;
    }
}