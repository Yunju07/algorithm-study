import java.util.*;
import java.lang.*;
import java.io.*;

// 상어 중학교
class BOJ21609 {
    static int N, M, idx;
    static int size, count; // 그룹의 블록 갯수와 무지개 블록갯수
    static List<int[]>[] rc; // 블록의 좌표 저장
    static int[][] maps, newMaps, dir;
    static boolean[][][] visited;
    static List<int[]> groups;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        dir = new int[4][2];
        dir[0] = new int[]{-1, 0};
        dir[1] = new int[]{1, 0};
        dir[2] = new int[]{0, -1};
        dir[3] = new int[]{0, 1};

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maps = new int[N][N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int point = 0;
        while(true) {
            groups = new ArrayList<>();
            rc = new List[N*N];
            findGroup();

            // 블록 그룹이 없으면 게임 종료
            if(groups.size()==0) {
                break;
            }
            
            // 블록 그룹 후보들 중 하나 선정하기
            groups.sort(Comparator.comparingInt((int[] n) -> -n[0])
                                  .thenComparingInt(n -> -n[1])
                                  .thenComparingInt(n -> -n[2])
                                  .thenComparingInt(n -> -n[3]));
            int[] group = groups.get(0);

            // 블록 그룹 값 제거하기
            for(int[] p: rc[group[4]]) {
                maps[p[0]][p[1]] = -2;  // 비어있음을 의미
            }
            
            // 점수 획득
            point += (group[0]*group[0]);
            
            // 격자 중력 작용
            gravity();
            
            // 격자 90도 회전
            transform();
            
            // 격자 중력 작용
            gravity();
        }

        bw.write(point+"\n");
        bw.flush();
    }

    // maps 90도 회전
    public static void transform() {
        newMaps = new int[N][N];

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                newMaps[i][j] = maps[j][N-1-i];
            }
        }
        maps = newMaps;
        
        return;
    }

    // maps에 중력 작용
    public static void gravity() {
        newMaps = new int[N][N];

        int max;
        for(int c=0; c<N; c++) {
            max = N-1;
            for(int r=N-1; r>=0; r--) {
                newMaps[r][c] = maps[r][c];

                // 중력으로의 이동 위치 변경
                if(maps[r][c] == -1) {
                    max = r-1;
                }
                if(max < 0){
                    continue;
                }
                if(maps[max][c] != -2 && r == max){
                    max--;
                }

                // 중력에 의해 이동할 수 있는 경우
                if(maps[r][c] >= 0 && max > r){
                    newMaps[max][c] = maps[r][c];
                    newMaps[r][c] = -2;
                    max--;
                }
                //System.out.println(r+" "+c+" "+max);
            }
        }
        
        maps = newMaps;
    }
    
    // 블록 그룹을 찾는 함수
    public static void findGroup() {
        // 블록 그룹 저장형태 [size, count, i, j];

        visited = new boolean[M+1][N][N];
        idx = 0;
        for(int r=0; r<N; r++) {
            for(int c=0; c<N; c++) {
                // r, c가 기준인 블록 그룹이 가능한 지
                int val = maps[r][c];
                if(val <= 0) {
                    continue;
                }

                if(visited[val][r][c]) {
                    continue;
                }

                size = 0; 
                count = 0;
                rc[idx] = new ArrayList<>();
                
                dfs(val, r, c);

                if(size >= 2) {
                    groups.add(new int[]{size, count, r, c, idx});
                    idx++;
                }
            }
        }
    }

    public static void dfs(int val, int r, int c) {
        if(visited[val][r][c]) {
            return;
        }
        visited[val][r][c] = true;

        rc[idx].add(new int[]{r, c});

        size++;
        if(maps[r][c] == 0) {
            count++;
        }

        int nr, nc;
        for(int[] d: dir) {
            nr = r+d[0];
            nc = c+d[1];

            if(0<=nr && nr<N && 0<=nc && nc<N) {
                if((maps[nr][nc] == 0) || (maps[nr][nc] == val)) {
                    dfs(val, nr, nc);
                }
            }
        }  
    }

    public static void printMap() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                System.out.print(maps[i][j]+" ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }
}
