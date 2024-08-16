package day0816;

import java.util.Arrays;

public class PowerSet_Combination {
	static int[] nums = { 1, 2, 3 };
	// 최대 원소의 개수
	static int max_cnt;
	// 각 부분 집합을 저장할 배열
	static int[] subset;

	public static void main(String[] args) {
		// 원소를 선택하는 개수 0 ~ 3개.
		for (int i = 0; i <= 3; i++) {
			max_cnt = i;
			subset = new int[i];
			// 대상 집합에서 원소를 0 ~ 3개를 선택하는 조합을 모두 구한다.
			Combination(0, 0);
		}
	}

	private static void Combination(int cnt, int k) {
		if (cnt == max_cnt) {
			System.out.println(Arrays.toString(subset));
			return;
		}
		for (int i = k; i < nums.length; i++) {
			subset[cnt] = nums[i];
			Combination(cnt + 1, i + 1);
		}

	}
}