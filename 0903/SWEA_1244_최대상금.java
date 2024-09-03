package day0903;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class SWEA_1244_최대상금 {

    static int N, L, answer;
    static int[] list;
    static Set<String> v;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            String[] input = br.readLine().split(" ");
            String st = input[0];
            N = Integer.parseInt(input[1]);

            L = st.length();
            list = new int[L];
            v = new HashSet<>();
            answer = 0;

            for (int i = 0; i < L; i++) {
            	list[i] = Character.getNumericValue(st.charAt(i));
            }

            dfs(0);
            System.out.println("#" + test_case + " " + answer);
        }
    }

    static void dfs(int n) {
        if (n == N) {
            int currentValue = toInt(list);
            answer = Math.max(answer, currentValue);
            return;
        }

        for (int i = 0; i < L - 1; i++) {
            for (int j = i + 1; j < L; j++) {
                // 두 자리의 숫자를 교환
                swap(i, j);

                int currentValue = toInt(list);
                String state = n + ":" + currentValue;

                // 중복된 상태가 아니면 탐색 진행
                if (!v.contains(state)) {
                    v.add(state);
                    dfs(n + 1);
                }

                // 원래 자리로 되돌림 (백트래킹)
                swap(i, j);
            }
        }
    }

    // 배열의 두 요소를 교환하는 함수
    static void swap(int i, int j) {
        int temp = list[i];
        list[i] = list[j];
        list[j] = temp;
    }

    // 현재 배열의 숫자들을 하나의 정수로 변환
    static int toInt(int[] lst) {
        int result = 0;
        for (int i = 0; i < lst.length; i++) {
            result = result * 10 + lst[i];
        }
        return result;
    }
}