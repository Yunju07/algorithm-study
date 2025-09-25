import java.util.*;
import java.io.*;
import java.math.*;

// 미생물 연구
public class microbiologicalresearch {
    static int N, size;
    static int[][] maps, newMaps, dir;
    static boolean[][] visited;
    static List<int[]> info;
    static List<List<int[]>> shape;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        // 탐색 방향은 위 아래 양 옆
        dir = new int[4][2];
        dir[0] = new int[]{-1, 0};
        dir[1] = new int[]{1, 0};
        dir[2] = new int[]{0, -1};
        dir[3] = new int[]{0, 1};

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        maps = new int[N][N];

        //[종류, 크기] -> 유입시점(= 종류)
        info = new ArrayList<int[]>();
        info.add(new int[]{0, 0}); // 종류와 인덱스를 동일하게 하기 위해 빈 배열추가

        // 종류별(인덱스) 미생물의 형태 정보
        shape = new ArrayList<>();
        shape.add(new ArrayList<int[]>());

        for(int i=1; i<=Q; i++) {
            // 좌표 입력
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());

            // i 가 미생물 종류가 됨
            List<int[]> sp = new ArrayList<int[]>();
            int s = 0;
            for(int r=r1; r<r2; r++) {
                for(int c=c1; c<c2; c++) {
                    // 다른 미생물이 원래 있었으면 그 미생물 사이즈 줄이기
                    if(maps[r][c] != 0) {
                        info.get(maps[r][c])[1]--;
                        List<int[]>temp = shape.get(maps[r][c]);
                        int idx=0;
                        for(int[] tem: temp) {
                            if(tem[0] == r && tem[1] == c){
                                break;
                            }
                            idx++;
                        }
                        temp.remove(idx);
                    }
                    sp.add(new int[]{r, c});
                    maps[r][c] = i;
                    s++;
                }
            }
            shape.add(sp);
            info.add(new int[]{i, s});

            detect();

            // 배양용기 이동
            newMaps = new int[N][N];
            move();
            maps = newMaps;

            // 점수 계산
            int point = getPoint();
            bw.write(point+"\n");
            
        }
        bw.flush();
    }
    // 현재 maps에서 point 계산
    public static int getPoint() {
        int point = 0;

        int count = info.size()-1;
        for(int a=1; a<=count; a++) {
            if(info.get(a)[0] == 0) {
                continue;
            }
            for(int b=a+1; b<=count; b++) {
                if(info.get(b)[0] == 0) {
                    continue;
                }
                
                if(isNeighbor(a, b)) {
                    point += (info.get(a)[1] * info.get(b)[1]);
                }
            }
        }

        return point;
    }
    
    // a와 b가 maps상에서 붙어있는지
    public static boolean isNeighbor(int a, int b) {
       
        for(int[] s: shape.get(a)){
            for(int[] d: dir) {
                int nr = s[0] + d[0];
                int nc = s[1] + d[1]; 

                if(0<=nr && nr<N && 0<=nc && nc<N) {
                    if(maps[nr][nc] == b) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // 미생물 무리를 새로운 배양포드에 옮기기
    public static void move() {
        // 미생물 정보를 size순으로 정렬
        List<int[]> sorted = new ArrayList<int[]>(info);
        sorted.sort(Comparator.comparingInt((int[] n) -> n[1]).reversed()
                        .thenComparingInt(n->n[0]));
        
        int minr, minc, maxr, maxc;
        List<int[]> sp;
        for(int[] g: sorted) {
            if(g[0] == 0) {
                continue;
            }
            //g[0]에 대해 새로운 배양포드로 옮기기
            // 가장 작은 r과 가장 작은 c를 구하기 -> 이 최소 좌표를 기준으로 얼마큼 떨어졌는지가 미생물의 모양
            // 가장 큰 r과 가장 큰 c -> 배양 판단에서 범위가 벗어나는 경우를 빠르게 패스할 수 있음
            sp = shape.get(g[0]);
            minr = N; 
            minc = N;
            maxr = 0; 
            maxc = 0;
            for(int[] s: sp) {
                minr = Math.min(minr, s[0]);
                minc = Math.min(minc, s[1]);
                maxr = Math.max(minr, s[0]);
                maxc = Math.max(minc, s[1]);
            }
            
            for(int[] s: sp) {
                s[0] -= minr;
                s[1] -= minc;   //미생물의 좌표를 minr,minc에 대한 상대위치로 변경
            }
            maxr -= minr;
            maxc -= minc;

            // 최소 좌표를 (0,0) 부터 시작해서 (N-1, N-1)까지 넣어보면서 
            // 이 미생물이 새로운 배양포드에 갈 수 있는지 찾기
            boolean isFind = false;
            for(int r=0; r<N; r++) {
                for(int c=0; c<N; c++) {
                    if(r+maxr >= N || c+maxc >= N) {
                        continue;
                    }
                    boolean isOk = true;
                    for(int[] s: sp) {
                        int nr = r+s[0];
                        int nc = c+s[1];
                        if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
                            isOk = false;
                            break;
                        }

                        if(newMaps[r+s[0]][c+s[1]] != 0) {
                            isOk = false;
                            break;
                        }
                    }
                    // 찾은경우 - > 새 배양 포드로 이동
                    if(isOk) {
                        List<int[]> newsp = new ArrayList<int[]>();   
                        for(int[] s: sp) {
                            newMaps[r+s[0]][c+s[1]] = g[0];
                            newsp.add(new int[]{r+s[0], c+s[1]});
                        }
                        shape.set(g[0], newsp);
                        isFind = true;
                        break;
                    }
                }
                if(isFind) {
                    break;
                }
            }
            // 새로운 배양 포드에 이동하지 못한 미생물의 info 지우기
            if(!isFind) {
                g[0] = 0;
            }
        }
    }

    // 분리된 미생물 무리를 탐지하고 없애기
    public static void detect() {
        //[종류, 크기]
        for(int[] g: info) {
            if(g[0] == 0) {
                continue;
            }

            //g[0] 미생물이 map내부에서 분리되어 있는지 체크 -> dfs
            boolean isBreak = false;
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(maps[i][j] == g[0]){
                        visited = new boolean[N][N];
                        size = 0;
                        dfs(g[0], i, j);
                        isBreak = true;
                        break;
                    }
                }
                if(isBreak) {
                    break;
                }
            }

            // 미생물이 분리되어 있다~~
            if(size != g[1]){
                // 이 미생물 없애기 
                for(int i=0; i<N; i++) {
                    for(int j=0; j<N; j++) {
                        if(maps[i][j] == g[0]){
                            maps[i][j] = 0;
                        }
                    }
                }  
                g[0] = 0; 
            }
        }
        
    }
    public static void dfs(int g, int r, int c) {
        if(visited[r][c]){
            return;
        }
        visited[r][c] = true;

        if(maps[r][c] == g) {
            size++;
            for(int[] d: dir) {
                int a = r+d[0];
                int b = c+d[1];

                if(0<=a && a<N && 0<=b && b<N) {
                    dfs(g, a, b);
                }
            }
        }

    }
}

/*
미생물 모양: 가장 왼쪽이고 가장 아래인 좌표기준 상대 좌표
{
[[0,0], [...]]
}

용기 이동시
1. 가장 큰 무리 미생물 선택
2. 모양을 유지, 다른 미생물의 영역과 겹치지 않기
3. 위의 조건에 맞는 가장 작은 x좌표 y좌표
4. 둘 곳 없으면 사라져
*/
