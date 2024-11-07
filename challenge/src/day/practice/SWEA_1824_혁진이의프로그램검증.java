package day.practice;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1824_혁진이의프로그램검증 {

    static char[][] map;
    static int memory, R, C;
    static boolean[][][][] visited;
    static int[] dx = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
    	System.setIn(new FileInputStream("res/s_1824.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            map = new char[R][C];
            memory = 0;
            visited = new boolean[R][C][4][16];  // 방문 상태 추가

            for (int i = 0; i < R; i++) {
                String line = br.readLine();
                for (int j = 0; j < C; j++) {
                    map[i][j] = line.charAt(j);
                }
            }

            boolean check = sol();

            if (check == true) {
                System.out.println("#" + tc + " YES");
            } else {
                System.out.println("#" + tc + " NO");
            }
        }
    }

    static boolean sol() {
        Queue<int[]> q = new LinkedList<>();
        if(map[0][0] >= '0' && map[0][0] <= '9') {
        	q.offer(new int[] {0,0,3,map[0][0] - '0'});
        }else {
        	q.offer(new int[]{0, 0, 3, 0});  // {x, y, direction, memory}
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int dir = cur[2];
            int mem = cur[3];

            int[] check;
            
            if (String.valueOf(map[x][y]).equals("@")) { // @에 도착할 수 있으면 true 반환
                return true;
            }
            if(map[x][y] >= '0' && map[x][y] <= '9') {
            	mem = map[x][y]-'0';
            }
            
            // 이미 방문한 상태라면 continue
            if (visited[x][y][dir][mem]) continue;
            visited[x][y][dir][mem] = true;
            
            if(String.valueOf(map[x][y]).equals("?")) {
                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    // nx와 ny가 유효한 범위인지 확인
                    if (nx < 0) nx = R - 1;
                    else if (nx >= R) nx = 0;
                    if (ny < 0) ny = C - 1;
                    else if (ny >= C) ny = 0;
                    check = new int[]{nx, ny, i, mem};
                    q.offer(check);
                }
            }else {
            	if (String.valueOf(map[x][y]).equals(".")) {
                   
                } else if (String.valueOf(map[x][y]).equals("<")) {
                    dir = 2;
                } else if (String.valueOf(map[x][y]).equals(">")) {
                	dir = 3;
                } else if (String.valueOf(map[x][y]).equals("^")) {
                	dir = 0;
                } else if (String.valueOf(map[x][y]).equals("v")) {
                	dir = 1;
                } else if (String.valueOf(map[x][y]).equals("_")) {
                	if(mem == 0) {
                		dir = 3;
                	}else {
                		dir = 2;
                	}
                } else if (String.valueOf(map[x][y]).equals("|")) {
                	if(mem == 0) {
                		dir = 1;
                	}else {
                		dir = 0;
                	}
                } else if (String.valueOf(map[x][y]).equals("+")) {
                	if (mem==15) mem=0;
					else mem++;
                } else if (String.valueOf(map[x][y]).equals("-")) {
                	if (mem==0) mem=15;
					else mem--;
                }
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                // nx와 ny가 유효한 범위인지 확인
                if (nx < 0) nx = R - 1;
                else if (nx >= R) nx = 0;
                if (ny < 0) ny = C - 1;
                else if (ny >= C) ny = 0;
                
                check = new int[]{nx, ny, dir, mem};
                q.offer(check);
            }

        }
        return false;
    }
}
