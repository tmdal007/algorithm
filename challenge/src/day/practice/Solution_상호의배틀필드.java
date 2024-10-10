package day.practice;

import java.util.Scanner;

public class Solution_상호의배틀필드 {
    static int ti = 0;
    static int tj = 0;
    static int si = 0;
    static int sj = 0;

    static int H = 0;
    static int W = 0;
    static int N = 0;

    private static String[][] matrix;

    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상하좌우

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        
        for (int test_case = 1; test_case <= T; test_case++) {
            H = sc.nextInt();
            W = sc.nextInt();
            sc.nextLine(); // 다음 줄로 넘어가기

            matrix = new String[H][W];

            for (int i = 0; i < H; i++) {
                String line = sc.nextLine();
                for (int j = 0; j < W; j++) {
                    matrix[i][j] = String.valueOf(line.charAt(j));
                    if (matrix[i][j].equals("^") || matrix[i][j].equals("v") || matrix[i][j].equals("<") || matrix[i][j].equals(">")) {
                        ti = i;
                        tj = j;
                        si = i;
                        sj = j;
                    }
                }
            }

            N = sc.nextInt();
            String input = sc.next();

            for (int i = 0; i < N; i++) {
                char command = input.charAt(i);
                if (command == 'S') {
                    shoot();
                } else {
                    moveGame(command);
                }
            }

            System.out.print("#" + test_case + " ");
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    System.out.print(matrix[i][j]);
                }
                System.out.println();
            }
        }
        sc.close();
    }

    public static void shoot() {
        int ni = ti;
        int nj = tj;
        if (matrix[ti][tj].equals("^")) {
            while (--ni >= 0) {
                if (matrix[ni][nj].equals("*")) {
                    matrix[ni][nj] = ".";
                    break;
                } else if (matrix[ni][nj].equals("#")) {
                    break;
                }
            }
        } else if (matrix[ti][tj].equals("v")) {
            while (++ni < H) {
                if (matrix[ni][nj].equals("*")) {
                    matrix[ni][nj] = ".";
                    break;
                } else if (matrix[ni][nj].equals("#")) {
                    break;
                }
            }
        } else if (matrix[ti][tj].equals("<")) {
            while (--nj >= 0) {
                if (matrix[ti][nj].equals("*")) {
                    matrix[ti][nj] = ".";
                    break;
                } else if (matrix[ti][nj].equals("#")) {
                    break;
                }
            }
        } else if (matrix[ti][tj].equals(">")) {
            while (++nj < W) {
                if (matrix[ti][nj].equals("*")) {
                    matrix[ti][nj] = ".";
                    break;
                } else if (matrix[ti][nj].equals("#")) {
                    break;
                }
            }
        }
    }

    public static void moveGame(char command) {
        int ni = si;
        int nj = sj;

        switch (command) {
            case 'U':
                ni = si - 1;
                nj = sj;
                matrix[si][sj] = "^";
                if (ni >= 0 && matrix[ni][nj].equals(".")) {
                    matrix[ni][nj] = "^";
                    matrix[si][sj] = ".";
                    si = ni;
                    sj = nj;
                }
                break;
            case 'D':
                ni = si + 1;
                nj = sj;
                matrix[si][sj] = "v";
                if (ni < H && matrix[ni][nj].equals(".")) {
                    matrix[ni][nj] = "v";
                    matrix[si][sj] = ".";
                    si = ni;
                    sj = nj;
                }
                break;
            case 'L':
                ni = si;
                nj = sj - 1;
                matrix[si][sj] = "<";
                if (nj >= 0 && matrix[ni][nj].equals(".")) {
                    matrix[ni][nj] = "<";
                    matrix[si][sj] = ".";
                    si = ni;
                    sj = nj;
                }
                break;
            case 'R':
                ni = si;
                nj = sj + 1;
                matrix[si][sj] = ">";
                if (nj < W && matrix[ni][nj].equals(".")) {
                    matrix[ni][nj] = ">";
                    matrix[si][sj] = ".";
                    si = ni;
                    sj = nj;
                }
                break;
        }
        ti = si;
        tj = sj;
    }
}