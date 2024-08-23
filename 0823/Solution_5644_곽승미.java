package day0823;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Solution_5644_곽승미 {

	static int[] a,b;
	static int M,A,chargeA,chargeB,maxValue,sumValue,res;
	static int[][] map = new int[11][11];
	static int[][] move = {{0,0},{-1,0},{0,1},{1,0},{0,-1}};
	static int[][] AP;
	static ArrayList<Integer> possibleA,possibleB;
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/s_5644_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			
			// 사용자 A,B의 이동 정보 입력 받기
			a = new int[M];
			b = new int[M];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<M; i++) {
				a[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<M; i++) {
				b[i] = Integer.parseInt(st.nextToken());
			}
			// AP 입력 받기
			AP = new int[A][4];
			for(int i =0; i<A; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<4; j++) {
					AP[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			int ax = 1;
			int ay = 1;
			
			int bx = 10;
			int by = 10;
			
//			chargeA = 0;
//			chargeB = 0;
			sumValue = 0;
			maxValue = Integer.MIN_VALUE;
			res = 0;
			cal_distance(ax,ay,bx,by);
			
			for(int i=0; i<possibleA.size(); i++) {
				System.out.println("A : " +possibleA.get(i));
			}
			for(int i=0; i<possibleB.size(); i++) {
				System.out.println("B : "+possibleB.get(i));
			}
			res += choice(possibleA,possibleB);

			System.out.println(res);
			
			for(int i=0; i<M; i++) {
				int anx = ax + move[a[i]][0];
				int any = ay + move[a[i]][1];
				int bnx = bx + move[b[i]][0];
				int bny = by + move[b[i]][1];
				
				cal_distance(anx,any,bnx,bny);
				for(int j=0; j<possibleA.size(); j++) {
				System.out.println("A : " +possibleA.get(j));
				}
				for(int j=0; j<possibleB.size(); j++) {
					System.out.println("B : "+possibleB.get(j));
				}
				System.out.println("---------------");
				res += choice(possibleA,possibleB);
				ax = anx;
				ay = any;
				bx = bnx;
				by = bny;
			}
			
			System.out.println("#" + tc + " " + res);
		}
	}
	static void cal_distance(int ax, int ay, int bx, int by) {
		possibleA = new ArrayList<>();
		possibleB = new ArrayList<>();
		for(int i=0; i<A; i++) {
			int calxA = Math.abs(ax - AP[i][0]);
			int calyA = Math.abs(ay - AP[i][1]);
			int calxB = Math.abs(bx - AP[i][0]);
			int calyB = Math.abs(by - AP[i][1]);
			
			if(calxA+calyA <= AP[i][2]) {

				possibleA.add(i);
			}
			if(calxB+calyB <= AP[i][2]) {
				possibleB.add(i);			
			}
		}
	}

	static int choice(ArrayList<Integer> p1, ArrayList<Integer> p2) {
		if(p1.size() == 0 && p2.size() == 0) {
			sumValue += 0;
			return sumValue;
		}
		if(p1.size() > 0 && p2.size() == 0) {
			int [] list = new int[p1.size()];
			int s = 0;
			for(int i=0; i<p1.size(); i++) {
				list[i] = AP[p1.get(i)][3];
				s = Math.max(s, list[i]);
			}
			sumValue += s;
			return sumValue;
		}else if(p1.size() == 0 && p2.size() > 0) {
			int [] list = new int[p2.size()];
			int s = 0;
			for(int i=0; i<p2.size(); i++) {
				list[i] = AP[p2.get(i)][3];
				s = Math.max(s, list[i]);
			}
			sumValue += s;
			return sumValue;
		}else {
			for(int i=0; i<p1.size(); i++) {
				for(int j=0; j<p2.size(); j++) {
					int sum = 0;
					
					if(p1.get(i) == p2.get(j)) {
						sum += AP[p1.get(i)][3];
					}else {
						sum += AP[p1.get(i)][3] + AP[p2.get(j)][3];
					}
					
					if(maxValue < sum) {
						maxValue = sum;
					}
				}
			}
		}
		sumValue += maxValue;
		return sumValue;
	}
	

}
