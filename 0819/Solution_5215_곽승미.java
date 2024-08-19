package day0819;
/*
 *  SWEA 5215. 햄버거 다이어트
 *  NextPermutation 활용하여 조합 생성
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Solution_5215_곽승미 {
	static int N,L;
	static int[] score, cal;
	static int[][] food;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/s_5215_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());

			score = new int[N];
			cal = new int[N];
			food = new int[N][2];
			
			for(int n=0; n<N; n++) {
				st = new StringTokenizer(br.readLine());
				score[n] = Integer.parseInt(st.nextToken());
				cal[n] = Integer.parseInt(st.nextToken());
				food[n][0] = score[n];
				food[n][1] = cal[n];
			}
			
			// food 리스트를 score(맛)을 기준으로 오름차순 정렬
			Arrays.sort(food, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					
					return o1[0]-o2[0];
				}
			});
			
			int max_score = Integer.MIN_VALUE;
			
			// 원소 크기와 같은 크기의 배열을 생성
			// 원소를 가질 수 있는 r개(1~5개) 만큼 뒤에서 1로 값 초기화
			for(int i=1; i<=N; i++) {
				int[] sort_score = new int[N];
				for(int j=N-1; j>N-1-i; j--) {
					sort_score[j] = 1;
				}
				do {
					int total_score = 0;
					int total_cal = 0;
					// 현재 조합의 맛 점수와 칼로리의 각각의 합
					for(int k=0; k<sort_score.length; k++) {
						if(sort_score[k] == 1) {
							total_score += food[k][0];
							total_cal += food[k][1];
						}
					}
					// 칼로리가 제한 칼로리를 넘지 않고, 최대 맛점수보다 total 맛점수가 클 경우
					if(total_cal < L && max_score < total_score) {
						max_score = total_score;
					}
					
//					System.out.println("sort_score : "+Arrays.toString(sort_score));
//					System.out.println();
				} while (np(sort_score));
				
			}
			System.out.println("#"+tc+" "+max_score);
		}
		
		
	}
	
	static boolean np(int[] p) { // boolean : true(다음순열 존재), false(다음순열 없음)
				
		int N = p.length;
		// step1) 꼭대기 찾기
		int i = N-1;
		while(i>0 && p[i-1]>=p[i]) --i;
		
		if(i==0) return false;
		
		// step2) 꼭대기 앞 교환위치에 교활할 값(i-1위치 값보다 큰 값 중 가장 작은 값) 자리 찾기
		int j = N-1;
		while(p[i-1] >= p[j]) j--;
		
		// step3) 두 위치의 수 교환
		swap(p,i-1, j);
		
		// step4) 꼭대기부터 맨 뒤까지 오름차순의 형태로 만듦
		int k = N-1;
		while(i<k) {
			swap(p, i++, k--);
		}
		return true;
	}
	
	static void swap(int[] p, int i, int j) {
		int temp = p[i];
		p[i] = p[j];
		p[j] = temp;
	}

}
