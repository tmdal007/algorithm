package day.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution_나무베기 {

    static class State {
        int x, y, dir, moves, chopped;

        public State(int x, int y, int dir, int moves, int chopped) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.moves = moves;
            this.chopped = chopped;
        }
    }

    // 북, 동, 남, 서 방향
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수

        for (int t = 1; t <= T; t++) {
            String[] firstLine = br.readLine().split(" ");
            int N = Integer.parseInt(firstLine[0]); // 필드 크기
            int K = Integer.parseInt(firstLine[1]); // 최대 나무를 벨 수 있는 횟수

            char[][] field = new char[N][N];
            int startX = 0, startY = 0, endX = 0, endY = 0;

            // 필드 입력 및 X와 Y 좌표 저장
            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < N; j++) {
                    field[i][j] = line.charAt(j);
                    if (field[i][j] == 'X') {
                        startX = i;
                        startY = j;
                    } else if (field[i][j] == 'Y') {
                        endX = i;
                        endY = j;
                    }
                }
            }

            // BFS 탐색
            int result = bfs(field, N, K, startX, startY, endX, endY);
            System.out.println("#" + t + " " + result);
        }
    }

    private static int bfs(char[][] field, int N, int K, int startX, int startY, int endX, int endY) {
        // (x, y, 방향, 나무 벤 횟수) 에 대한 방문 체크 배열
        boolean[][][][] visited = new boolean[N][N][4][K + 1];
        Queue<State> queue = new LinkedList<>();
        queue.add(new State(startX, startY, 0, 0, 0)); // 처음 시작은 (x, y, 북쪽(0), 0번 움직임, 0번 나무를 벤 상태)
        visited[startX][startY][0][0] = true;

        while (!queue.isEmpty()) {
            State current = queue.poll();

            // 목적지에 도착하면 최소 조작 횟수 리턴
            if (current.x == endX && current.y == endY) {
                return current.moves;
            }

            // 1. 앞으로 이동
            int nx = current.x + dx[current.dir];
            int ny = current.y + dy[current.dir];

            if (isInBounds(nx, ny, N)) {
                // 나무가 없을 때(G)를 만나면 이동
                if (field[nx][ny] == 'G' && !visited[nx][ny][current.dir][current.chopped]) {
                    visited[nx][ny][current.dir][current.chopped] = true;
                    queue.add(new State(nx, ny, current.dir, current.moves + 1, current.chopped));
                } 
                // 나무(T)가 있고, 나무를 벨 수 있는 경우
                else if (field[nx][ny] == 'T' && current.chopped < K && !visited[nx][ny][current.dir][current.chopped + 1]) {
                    visited[nx][ny][current.dir][current.chopped + 1] = true;
                    queue.add(new State(nx, ny, current.dir, current.moves + 1, current.chopped + 1));
                }
            }

            // 2. 왼쪽 회전 (L)
            int leftDir = (current.dir + 3) % 4;
            if (!visited[current.x][current.y][leftDir][current.chopped]) {
                visited[current.x][current.y][leftDir][current.chopped] = true;
                queue.add(new State(current.x, current.y, leftDir, current.moves + 1, current.chopped));
            }

            // 3. 오른쪽 회전 (R)
            int rightDir = (current.dir + 1) % 4;
            if (!visited[current.x][current.y][rightDir][current.chopped]) {
                visited[current.x][current.y][rightDir][current.chopped] = true;
                queue.add(new State(current.x, current.y, rightDir, current.moves + 1, current.chopped));
            }
        }

        // 목적지에 도달하지 못하면 -1 반환
        return -1;
    }

    // 좌표가 필드 내에 있는지 확인하는 함수
    private static boolean isInBounds(int x, int y, int N) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}