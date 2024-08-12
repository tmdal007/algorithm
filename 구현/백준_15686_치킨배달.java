package algorithm.day0812;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 백준_15686_치킨배달 {
	
	static ArrayList<int[]> house = new ArrayList<>(); 	// 집의 좌표 저장
	static ArrayList<int[]> chicken = new ArrayList<>();// 치킨집의 좌표 저장
	static ArrayList<int[]> checked = new ArrayList<>();// M개의 치킨집 저장
	static boolean[] visited; // 방문 여부 저장
	static int result = Integer.MAX_VALUE;
	static int N,M;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("../res/input_백준_15686.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][N];
		
		

		// 집과 치킨집의 좌표 저장
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					house.add(new int[] {i,j});
				}else if(map[i][j] == 2) {
					chicken.add(new int[] {i,j});
				}
			}
		}
		visited = new boolean[chicken.size()];
		backtracking(0,0); // M개의 치킨집 뽑기
		System.out.println(result);
	}
	
	// M개를 뽑아 checked 리스트에 저장했다면 모든 집들과 치킨집과의 최소거리를 계산
	// 선택한 M개의 치킨집과 집의 거리를 계산해 최소 거리 구하기
	static void backtracking(int cnt, int idx) {
		if(cnt == M) {
			int total = 0;
			for(int[] h : house) {
				int min = Integer.MAX_VALUE;
				for(int[] c : checked) {
					int distance = Math.abs(h[0] - c[0]) + Math.abs(h[1]-c[1]);
					min = Math.min(distance, min);
				}
				total += min; // 구한 최소거리를 total(치킨거리)에 저장
			}
			result = Math.min(result, total); // 현재 조합에서의 최소 치킨 거리 갱신
			return;
		}
		for(int i=idx; i<chicken.size(); i++) { // 모든 치킨집 탐색
			if(!visited[i]) {
				visited[i] = true;
				checked.add(chicken.get(i)); // 선택된 치킨집 추가
				backtracking(cnt+1, i+1);	 // 다음 치킨집 선택
				checked.remove(checked.size() -1); // 탐색이 끝나면 리스트 비우기(제거)
				visited[i] = false;
			}
		}
	}
}
