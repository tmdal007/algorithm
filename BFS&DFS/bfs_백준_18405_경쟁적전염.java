package day0814;

/*
 * S초 뒤에 (x,y)에 위치하는 바이러스 종류 출력
 * BFS로 풀이 - 낮은 번호의 바이러스부터 퍼짐
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class bfs_백준_18405_경쟁적전염 {

    public static int N, K;
    public static int[][] map;

    // 상, 우, 하, 좌
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
//    	System.setIn(new FileInputStream("res/input_18405.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 맵의 크기
        K = Integer.parseInt(st.nextToken()); // 바이러스의 종류 수

        // 맵 정보를 저장할 2차원 배열
        map = new int[N][N];

        // 바이러스 정보를 담을 리스트
        List<int[]> viruses = new ArrayList<>();

        // 맵 입력 받기
        for (int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
            	map[i][j] = Integer.parseInt(st.nextToken());
                // 해당 위치가 0이 아니면 바이러스 존재 -> 리스트에 저장
                if (map[i][j] != 0) {
                    // 바이러스의 종류, 시간, X 좌표, Y 좌표 저장
                    viruses.add(new int[]{map[i][j], 0, i, j});
                }
            }
        }

        // 바이러스 정보를 정렬 - 낮은 번호의 바이러스가 먼저 확산
        Collections.sort(viruses, Comparator.comparingInt(a -> a[0]));
        
        // 큐에 바이러스 정보 넣기
        Queue<int[]> q = new LinkedList<>();
        q.addAll(viruses);
        
        // 목표 시간, X, Y 좌표 입력
        st = new StringTokenizer(br.readLine());
        int targetS = Integer.parseInt(st.nextToken()); // 목표 시간
        // X,Y 좌표가 1,1부터 시작되므로 각각 -1해주어 배열 인덱스에 맞게 조정
        int targetX = Integer.parseInt(st.nextToken()) - 1; // 목표 X 좌표
        int targetY = Integer.parseInt(st.nextToken()) - 1; // 목표 Y 좌표

        // BFS 수행
        while (!q.isEmpty()) {
            // 큐에서 바이러스 정보를 하나씩 꺼내기
            int[] virus = q.poll();
            int virusType = virus[0]; // 바이러스 종류
            int time = virus[1];      // 현재 시간
            int x = virus[2];         // 현재 X 좌표
            int y = virus[3];         // 현재 Y 좌표

            // 만약 현재 시간이 목표 시간에 도달하면 종료
            if (time == targetS) break;

            // 현재 위치에서 상하좌우로 이동하며 바이러스 퍼뜨리기
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 새로운 위치가 맵의 범위 안에 있는지 확인
                if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                    // 아직 바이러스가 퍼지지 않은 위치라면
                    if (map[nx][ny] == 0) {
                        // 새로운 위치에 바이러스 확산시키고, 큐에 삽입
                    	map[nx][ny] = virusType;
                        q.offer(new int[]{virusType, time + 1, nx, ny});
                    }
                }
            }
        }
        System.out.println(map[targetX][targetY]);
    }
}
