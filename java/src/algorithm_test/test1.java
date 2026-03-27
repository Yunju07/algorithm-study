import java.util.*;
import java.io.*;

//로봇 청소기
public class test1 {
    public static int N, K, L;
    public static int[][] maps, robot, dir;
    public static int[][][] clean_dir;
    public static boolean[][] robot_m;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        dir = new int[][] {
                new int[]{-1, 0},
                new int[]{1, 0},
                new int[]{0, -1},
                new int[]{0, 1}
        };

        clean_dir = new int[4][4][2];
        // 오른 / 아래 / 왼/ 위
        clean_dir[0] = new int[][]{
                new int[]{0, 0},
                new int[]{-1, 0},
                new int[]{1, 0},
                new int[]{0, 1}
        };

        clean_dir[1] = new int[][]{
                new int[]{0, 0},
                new int[]{0, 1},
                new int[]{1, 0},
                new int[]{0, -1}
        };

        clean_dir[2] = new int[][]{
                new int[]{0, 0},
                new int[]{1, 0},
                new int[]{0, -1},
                new int[]{-1, 0}
        };

        clean_dir[3] = new int[][]{
                new int[]{0, 0},
                new int[]{0, -1},
                new int[]{-1, 0},
                new int[]{0, 1}
        };

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        maps = new int[N][N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        robot = new int[K][2];
        robot_m = new boolean[N][N];
        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            robot[i][0] = Integer.parseInt(st.nextToken())-1;
            robot[i][1] = Integer.parseInt(st.nextToken())-1;
            robot_m[robot[i][0]][robot[i][1]] = true;
        }

        // L번의 테스트 진행
        for(int t=0; t<L; t++) {
            move();
            clean();
            plus();
            diffuse();

            // 총먼지
            int result = 0;
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(maps[i][j] == -1) continue;
                    result += maps[i][j];
                }
            }

            System.out.println(result);
        }

    }

    // 1. 청소기 이동 - bfs
    public static void move() {
        for(int r=0; r<K; r++) {
            int x = robot[r][0];
            int y = robot[r][1];

            boolean[][] visited = new boolean[N][N];
            visited[x][y] = true;
            robot_m[x][y] = false;

            // [x, y, d(이동거리)]
            Queue<int[]> PQ = new LinkedList<int[]>();
            List<int[]> lis = new ArrayList<>();

            PQ.add(new int[]{x, y, 0});
            while(!PQ.isEmpty()) {
                int[] node = PQ.poll();
                int row = node[0];
                int col = node[1];
                int dis = node[2];

                if(maps[row][col] > 0) {
                    lis.add(new int[]{row, col, dis});
                }

                for(int[] d: dir) {
                    int nr = row + d[0];
                    int nc = col + d[1];

                    if(check(nr, nc)) {
                        if(!visited[nr][nc] && maps[nr][nc]>-1 && !robot_m[nr][nc]) {
                            PQ.add(new int[]{nr, nc, dis+1});
                            visited[nr][nc] = true;
                        }
                    }
                }
            }
            if(lis.size()==0) {
                robot_m[x][y] = true;
                continue;
            }

            lis.sort(Comparator.comparingInt((int[] n) -> n[2])
                    .thenComparingInt(n -> n[0])
                    .thenComparingInt(n -> n[1]));

            int nx = lis.get(0)[0];
            int ny = lis.get(0)[1];
            robot[r][0] = nx;
            robot[r][1] = ny;
            robot_m[nx][ny] = true;
        }
    }

    // 2. 청소
    // 청소 가능 먼지량이 큰순 -> 오른쪽0, 아래쪽1, 왼쪽2, 위쪽3
    public static void clean() {
        // 4방향에 대해서 먼지량 구하기
        // [방향번호, 먼지량]

        for(int r=0; r<K; r++) {
            int x = robot[r][0];
            int y = robot[r][1];
            List<int[]> count = new ArrayList<>();

            for(int num=0; num<4; num++) {
                int total = 0;
                for(int[] d: clean_dir[num]) {
                    int nx = x+d[0];
                    int ny = y+d[1];

                    if(!check(nx, ny)) continue;

                    if(maps[nx][ny] == -1) continue;

                    if(maps[nx][ny] > 20) {
                        total += 20;
                    }
                    else {
                        total += maps[nx][ny];
                    }
                }
                count.add(new int[]{num, total});
            }

            // 먼지량(내림차순) -> 방향번호 순으로 정렬
            count.sort(Comparator.comparingInt((int[] n) -> -n[1])
                    .thenComparingInt(n -> n[0]));

            // 청소 실시
            int num = count.get(0)[0];
            // int clean_count = 0;
            for(int[] d: clean_dir[num]) {
                int nx = x+d[0];
                int ny = y+d[1];

                if(!check(nx, ny)) continue;

                if(maps[nx][ny] == -1) continue;

                if(maps[nx][ny] >= 20) {
                    maps[nx][ny] -= 20;
                } else {
                    maps[nx][ny] = 0;
                }
            }
        }
    }

    // 3. 먼지 축적
    public static void plus() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(maps[i][j] > 0) {
                    maps[i][j] += 5;
                }
            }
        }
    }

    // 4. 먼지 동시 확산
    public static void diffuse() {
        int[][] munji = new int[N][N];

        // 확산될 먼지 구하기
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                // 빈칸이면
                if(maps[i][j] == 0) {
                    int sum = 0;
                    for(int[] d: dir) {
                        int ni = i+d[0];
                        int nj = j+d[1];

                        if(0<=ni && ni<N && 0<=nj && nj<N) {
                            if(maps[ni][nj]>0) sum += maps[ni][nj];
                        }
                    }

                    munji[i][j] = sum/10;
                }
            }
        }

        // 확산
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                maps[i][j] += munji[i][j];
            }
        }
    }

    public static boolean check(int r, int c) {
        if(0<=r && r<N && 0<=c && c<N) {
            return true;
        }
        return false;
    }

    // public static void printmap(){
    //     for(int i=0; i<N; i++) {
    //         for(int j=0; j<N; j++) {
    //             System.out.print(maps[i][j]+" ");
    //         }
    //         System.out.print("\n");
    //     }
    //     System.out.print("\n");
    // }

    // public static void printrobot(){
    //     for(int i=0; i<N; i++) {
    //         for(int j=0; j<N; j++) {
    //             String s;
    //             if(robot_m[i][j]) {
    //                 s = "R";
    //             }else {
    //                 s = "-";
    //             }
    //             System.out.print(s+" ");
    //         }
    //         System.out.print("\n");
    //     }
    //     System.out.print("\n");
    // }
}

