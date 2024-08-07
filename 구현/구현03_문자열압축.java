/*
 * 압축되는 문자열의 길이가 n/2까지
 * 다른 정답 코드 참조 후 풀이 진행
 */

package algorithm.day0807;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 구현03_문자열압축 {

    public static int solution(String s) {
        int n = s.length();
        int minLength = n;
        
        // 1부터 n/2 단위 길이까지
        for(int i=1; i<= n/2; i++){
            String result = "";
            String prev = s.substring(0,i); // 첫 문자열
            int cnt = 1;
            
            // 현재 단위 길이로 문자열 자르기
            for(int j=i; j<n; j+=i) {
            	String sub;
            	if(j+i <= n) {
            		sub = s.substring(j, j+i);
            	}else {
            		sub = s.substring(j,n);
            	}
            	
            	//prev(이전 문자열)과 sub(현재 문자열)이 같으면 cnt 증가
            	if(prev.equals(sub)) {
            		cnt++;
            	}else {
            		//다르면 result 문자열(압축)에 추가
            		//cnt가 1보다 크면 cnt와 prev를 result에 추가, 아니면 prev 추가
            		if(cnt > 1) {
            			result += cnt + prev;
            		}else {
            			result += prev;
            		}
            		prev = sub; // 현재 문자열 업데이트
            		cnt = 1; // 카운트 초기화
            	}
            }
            // cnt가 1보다 크면 cnt와 prev를 result에 추가, 아니면 prev 추가
    		if(cnt > 1) {
    			result += cnt + prev;
    		}else {
    			result += prev;
    		}
            //현재 길이 i로 압축한 문자열의 길이와 minLength 비교 후 업데이트
    		minLength = Math.min(minLength, result.length());
        }
        return minLength;
    }
    
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String example = br.readLine();
		System.out.println(solution(example));
	}

}

//참고--------------------------------------
//예제: "aabbaccc"
//i = 1일 때:
//
//prev = "a", sub = "a", cnt = 2
//prev = "b", sub = "b", cnt = 2
//prev = "a", sub = "a", cnt = 1
//prev = "c", sub = "c", cnt = 3
//result = "2a2ba3c"
//i = 2일 때:
//
//prev = "aa", sub = "bb", cnt = 1
//prev = "ac", sub = "cc", cnt = 1
//result = "aabbaccc" (압축되지 않음)
//i = 3일 때:
//
//prev = "aab", sub = "bac", cnt = 1
//result = "aabbaccc" (압축되지 않음)
//i = 4일 때:
//
//prev = "aabb", sub = "accc", cnt = 1
//result = "aabbaccc" (압축되지 않음)
//-----------------------------------------
