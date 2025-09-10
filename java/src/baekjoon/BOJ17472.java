import java.util.*;
import java.io.*;
import java.math.*;

// 다리 만들기 2
class BOJ17472 {
    static int N, M;
    static int[] parent;
    static int[][] maps, visited, direct;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maps = new int[N][M];
        visited = new int[N][M];
        for(int r=0; r<N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c=0; c<M; c++) {
                maps[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        // 상하좌우 방향
        direct = new int[4][2];
        direct[0] = new int[]{-1, 0};
        direct[1] = new int[]{1, 0};
        direct[2] = new int[]{0, -1};
        direct[3] = new int[]{0, 1};

        // 섬 정보 찾기
        int land_count = 0;
        List<List<int[]>> lands = new ArrayList();
        for(int r=0; r<N; r++) {
            for(int c=0; c<M; c++) {
                if(maps[r][c] == 1 && visited[r][c] == 0){
                    land_count++;
                    List<int[]> new_land = new ArrayList<int[]>();
                    dfs(r, c, new_land);
                    lands.add(new_land);
                }
            }
        }

        // 섬의 갯수는 2~6 사이이므로 그냥 모든 섬과 섬 사이 간선을 추가해버리겟슴
        PriorityQueue<int[]> edges = new PriorityQueue<int[]>(Comparator.comparing(n -> n[2]));
        for(int a=0; a<land_count-1; a++) {
            for(int b=a+1; b<land_count; b++) {
                int length = calculate(lands.get(a), lands.get(b));

                if(length == -1) {
                    continue;
                }

                edges.add(new int[]{a, b, length});
            }
        }

        // MST 활용
        parent = new int[land_count];
        for(int i=0; i<land_count; i++) {
            parent[i] = i;
        }

        int result = 0;
        int edge_count = 0;
        int[] edge = new int[3];
        while(!edges.isEmpty()) {
            // N드 N-1간선
            if(edge_count == land_count-1) {
                break;
            }

            edge = edges.poll();
            int A = edge[0];
            int B = edge[1];
            int C = edge[2];

            if(find(A) != find(B)) {
                result += C;
                edge_count++;
                union(A, B);
            }
        }

        if(edge_count != land_count-1) {
            result = -1;
        }

        bw.write(result+"\n");
        bw.flush();

    }

    // 섬과 섬 사이 최소 거리 구하기 (섬AB)
    public static int calculate(List<int[]> A, List<int[]> B) {
        int length = Integer.MAX_VALUE;

        for(int[] a: A) {
            for(int[] b: B) {
                int new_length;
                // 가로 직선 체크
                if(a[0] == b[0]) {
                    boolean isOk = true;
                    if(a[1] < b[1]) {
                        for(int i=a[1]+1; i<b[1]; i++) {
                            if(maps[a[0]][i] == 1) {
                                isOk = false;
                                break;
                            }
                        }
                    }

                    if(a[1] > b[1]) {
                        for(int i=b[1]+1; i<a[1]; i++) {
                            if(maps[a[0]][i] == 1) {
                                isOk = false;
                                break;
                            }
                        }
                    }

                    if(!isOk) {
                        continue;
                    }

                    new_length = Math.abs(a[1] - b[1]) - 1;

                    if(new_length>1) {
                        length = Math.min(length, new_length);
                    }
                }

                // 세로 직선
                if(a[1] == b[1]) {
                    boolean isOk = true;
                    if(a[0] < b[0]) {
                        for(int i=a[0]+1; i<b[0]; i++) {
                            if(maps[i][a[1]] == 1) {
                                isOk = false;
                                break;
                            }
                        }
                    }

                    if(a[0] > b[0]) {
                        for(int i=b[0]+1; i<a[0]; i++) {
                            if(maps[i][a[1]] == 1) {
                                isOk = false;
                                break;
                            }
                        }
                    }

                    if(!isOk) {
                        continue;
                    }

                    new_length = Math.abs(a[0] - b[0]) - 1;

                    if(new_length>1) {
                        length = Math.min(length, new_length);
                    }
                }
            }
        }

        // 직선 경로가 없는 경우
        if(length == Integer.MAX_VALUE) {
            length = -1;
        }

        return length;
    }

    public static void dfs(int r, int c, List<int[]> land) {
        if(0<=r && r<N & 0<=c && c<M) {
            if(visited[r][c] == 1) {
                return;
            }

            if(maps[r][c] == 1) {
                land.add(new int[]{r, c});
                visited[r][c] = 1;

                for(int[] d: direct) {
                    dfs(r+d[0], c+d[1], land);
                }
            }
        }
    }

    public static void union(int a, int b) {
        int A = find(a);
        int B = find(b);

        if(A != B) {
            parent[B] = A;
        }
    }

    public static int find(int a) {
        if(parent[a] == a) {
            return a;
        }

        return parent[a] = find(parent[a]);
    }

}