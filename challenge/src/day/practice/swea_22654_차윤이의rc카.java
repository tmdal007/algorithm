package day.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class swea_22654_차윤이의rc카 {

    static int[] dx = {-1, 0, 1, 0}; // 북, 동, 남, 서 방향
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringBuilder sb = new StringBuilder();
            sb.append("#").append(tc).append(" ");
            int N = Integer.parseInt(br.readLine());
            char[][] field = new char[N][N];
            int sx = 0, sy = 0, ex = 0, ey = 0;

            // 필드 정보 입력
            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < N; j++) {
                    field[i][j] = line.charAt(j);
                    if (field[i][j] == 'X') {
                        sx = i;
                        sy = j;
                    }
                    if (field[i][j] == 'Y') {
                        ex = i;
                        ey = j;
                    }
                }
            }

            int Q = Integer.parseInt(br.readLine()); // 명령어 세트 수

            for (int q = 0; q < Q; q++) {
                String[] commandInfo = br.readLine().split(" ");
                int C = Integer.parseInt(commandInfo[0]); // 명령어 길이
                String commands = commandInfo[1]; // 명령어 문자열

                // RC카의 초기 위치 및 방향 (위쪽을 바라봄 = 북쪽, direction = 0)
                int x = sx, y = sy, direction = 0;

                for (int i = 0; i < C; i++) {
                    char command = commands.charAt(i); // 각 명령어를 순차적으로 처리

                    if (command == 'A') { // 앞으로 이동
                        int nx = x + dx[direction];
                        int ny = y + dy[direction];

                        // 맵 범위를 벗어나지 않고, 나무(T)가 아니면 이동
                        if (nx >= 0 && nx < N && ny >= 0 && ny < N && field[nx][ny] != 'T') {
                            x = nx;
                            y = ny;
                        }
                    } else if (command == 'L') { // 왼쪽 회전
                        direction = (direction + 3) % 4; // 왼쪽으로 90도 회전
                    } else if (command == 'R') { // 오른쪽 회전
                        direction = (direction + 1) % 4; // 오른쪽으로 90도 회전
                    }
                }

                // 명령어 수행 후, 목적지에 도착했는지 확인
                if (x == ex && y == ey) {
                    sb.append("1 ");
                } else {
                    sb.append("0 ");
                }
            }

            System.out.println(sb.toString().trim());
        }
    }
}
