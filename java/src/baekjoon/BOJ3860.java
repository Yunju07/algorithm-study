import java.io.*;
import java.util.*;

// 할로윈 묘지
public class BOJ3860 {
    static long maps[][];
    static List<int[]> edges, holl;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        while(true) {
            st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());

            // 입력 종료
            if (W==0 && H==0) {
                break;
            }

            edges = new ArrayList<>();
            holl = new ArrayList<>();
            // 묘지를 뜻하는 이차원 배열
            // 입구 (0, 0) / 출구 (W-1, H-1)
            // 각 값의 의미
            // 방문전: Integer.MAX_VALUE
            // 묘비: Long.MAX_VALUE
            maps = new long[W][H];

            for(int i=0; i<W; i++) {
                for(int j=0; j<H; j++) {
                    maps[i][j] = Integer.MAX_VALUE;	// 방문 전
                }
            }
            maps[0][0] = 0;

            // 묘비 갯수
            int G = Integer.parseInt(br.readLine());
            for(int i=0; i<G; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                maps[x][y] = Long.MAX_VALUE; // 묘비
            }

            // 귀신 구멍
            int E = Integer.parseInt(br.readLine());
            for(int i=0; i<E; i++) {
                // 들어가는 위치
                st = new StringTokenizer(br.readLine());
                int x1 = Integer.parseInt(st.nextToken());
                int y1 = Integer.parseInt(st.nextToken());

                // 나오는 위치
                int x2 = Integer.parseInt(st.nextToken());
                int y2 = Integer.parseInt(st.nextToken());

                int t = Integer.parseInt(st.nextToken());	// 이동에 걸리는 시간

                edges.add(new int[] {x1, y1, x2, y2, t});
                holl.add(new int[] {x1, y1});
            }

            // 간선 저장
            List<int[]> move = new ArrayList<>();
            move.add(new int[]{-1, 0});
            move.add(new int[]{1, 0});
            move.add(new int[]{0, -1});
            move.add(new int[]{0, 1});

            for(int i=0; i<W; i++) {
                for(int j=0; j<H; j++) {
                    // 각 지점에서 이동할 수 있는 칸에 대한 이동을 저장 (edges)
                    // 묘비(에서/로는) 이동할 수 없으며, 맵밖에 나가지 않도록

                    // 도착 위치면 더 안나가
                    if(i == W-1 && j == H-1) {
                        continue;
                    }

                    // 지금 위치가 귀신 구멍인지
                    // 각 위치의 정보를 새로운 이차원 배열에 저장하면
                    // 여기서도 시간을 줄일 수 있따.
                    boolean isHoll = false;
                    for(int[] h: holl) {
                        if(h[0] == i && h[1] == j) {
                            isHoll = true;
                            break;
                        }
                    }
                    if (isHoll) {
                        continue;
                    }
                    // 지금 위치가 묘비인지
                    if(maps[i][j] == Long.MAX_VALUE) {
                        continue;
                    }

                    int nx, ny;
                    for(int[] d: move) {
                        nx = i + d[0];
                        ny = j + d[1];
                        // maps 를 벗어나는지
                        if (nx < 0 || nx >= W || ny <0 || ny >= H) {
                            continue;
                        }

                        // 이동 위치가 묘비인지
                        if(maps[nx][ny] == Long.MAX_VALUE) {
                            continue;
                        }
                        edges.add(new int[] {i, j, nx, ny, 1});	// 새로운 위치로 1시간에 걸쳐 이동하는 간선 추가
                    }
                }
            }

            // 벨만 포드 - 최단 거리 업데이트
            int N = W*H;			// 총 노드 갯수
            int M = edges.size();	// 간선 갯수
            int sx, sy, ex, ey, w;
            for(int i=0; i<N-1; i++) {
                for(int j=0; j<M; j++) {
                    int[] edge = edges.get(j);
                    sx = edge[0];
                    sy = edge[1];
                    ex = edge[2];
                    ey = edge[3];
                    w = edge[4];

                    if(maps[sx][sy] == Integer.MAX_VALUE) {
                        continue;
                    }

                    maps[ex][ey] = Math.min(maps[ex][ey], maps[sx][sy]+w);
                }
            }

            // 벨만 포드 - 음수 사이클 확인
            boolean isCycle = false;
            for(int j=0; j<M; j++) {
                int[] edge = edges.get(j);
                sx = edge[0];
                sy = edge[1];
                ex = edge[2];
                ey = edge[3];
                w = edge[4];

                if(maps[sx][sy] == Integer.MAX_VALUE) {
                    continue;
                }

                if(maps[ex][ey] > maps[sx][sy]+w) {
                    isCycle = true;
                    break;
                }
            }
            if (isCycle) {
                bw.write("Never\n");
                continue;
            }

            if(maps[W-1][H-1] == Integer.MAX_VALUE) {
                bw.write("Impossible\n");
            } else {
                bw.write(maps[W-1][H-1]+"\n");
            }
        }

        bw.flush();
    }

}
