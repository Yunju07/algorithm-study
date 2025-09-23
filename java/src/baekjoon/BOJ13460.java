import java.util.*;
import java.io.*;

// 구슬 탈출 2
// bfs + 큐
class BOJ13460 {
    static int N, M;
    static int[][] d;
    static String[][] maps, map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        //방향키 
        d = new int[4][2];
        d[0] = new int[]{0, -1};  // 왼
        d[1] = new int[]{0, 1};  // 오
        d[2] = new int[]{-1, 0};  // 위
        d[3] = new int[]{1, 0};  // 아래

        maps = new String[N][M];  // 지도
        int rx=0, ry=0, bx=0, by=0; //red와 blue 공의 좌표
        int ox=0, oy=0; // 구멍의 좌표
        for(int i=0; i<N; i++) {
            String[] input = br.readLine().split("");
            for(int j=0; j<M; j++) {               
                maps[i][j] = input[j];
                
                if(input[j].equals("R")) {
                    rx = i;
                    ry = j;
                    maps[i][j] = ".";
                }
                if(input[j].equals("B")) {
                    bx = i;
                    by = j;
                    maps[i][j] = ".";
                }
                if(input[j].equals("O")) {
                    ox = i;
                    oy = j;
                } 
            }
        }

        int step = 1;
        int result = -1;
        PriorityQueue<int[]> PQ = new PriorityQueue<int[]>(Comparator.comparing(n -> n[0]));
        // [step, d, rx, ry, bx, by]
        // 처음 위치에서 방향 4군데 이동작업 큐에 넣기
        PQ.add(new int[]{step, 0, rx, ry, bx, by});
        PQ.add(new int[]{step, 1, rx, ry, bx, by});
        PQ.add(new int[]{step, 2, rx, ry, bx, by});
        PQ.add(new int[]{step, 3, rx, ry, bx, by});

        int[] m = new int[6];
        int[] moveR = new int[2]; 
        int[] moveB = new int[2]; //red와 blue 공의 이동한 좌표
        while(!PQ.isEmpty()) {
            m = PQ.poll();
            rx = m[2];
            ry = m[3];
            bx = m[4];
            by = m[5];

            // 디버깅
            //bw.write(m[0]+" "+m[1]+" "+m[2]+" "+m[3]+" "+m[4]+" "+m[5]+"\n");

            // step 초과
            if(m[0]>10) {
                result = -1;
                break;
            }
            
            // 방향에 따라 먼저 이동해야할 공이 다름 
            if(m[1] == 0) {
                // 왼쪽으로의 이동: y가 더 작은 공 먼저 이동
                if(m[3] < m[5]) {
                    moveR = move(m[1], m[2], m[3], m[4], m[5]);
                    moveB = move(m[1], m[4], m[5], moveR[0], moveR[1]);
                } else {
                    moveB = move(m[1], m[4], m[5], m[2], m[3]);
                    moveR = move(m[1], m[2], m[3], moveB[0], moveB[1]);
                }
            } else if(m[1] == 1) {
                // 오른쪽으로의 이동: y가 더 큰 공 먼저 이동
                if(m[3] > m[5]) {
                    moveR = move(m[1], m[2], m[3], m[4], m[5]);
                    moveB = move(m[1], m[4], m[5], moveR[0], moveR[1]);
                } else {
                    moveB = move(m[1], m[4], m[5], m[2], m[3]);
                    moveR = move(m[1], m[2], m[3], moveB[0], moveB[1]);
                }
                
            } else if(m[1] == 2) {
                // 위쪽으로의 이동: x가 더 작은 공 먼저 이동
                if(m[2] < m[4]) {
                    moveR = move(m[1], m[2], m[3], m[4], m[5]);
                    moveB = move(m[1], m[4], m[5], moveR[0], moveR[1]);
                } else {
                    moveB = move(m[1], m[4], m[5], m[2], m[3]);
                    moveR = move(m[1], m[2], m[3], moveB[0], moveB[1]);
                }
                
            } else {
                // 아래쪽으로의 이동: x가 더 큰 공 먼저 이동
                if(m[2] > m[4]) {
                    moveR = move(m[1], m[2], m[3], m[4], m[5]);
                    moveB = move(m[1], m[4], m[5], moveR[0], moveR[1]);
                } else {
                    moveB = move(m[1], m[4], m[5], m[2], m[3]);
                    moveR = move(m[1], m[2], m[3], moveB[0], moveB[1]);
                }
            }

            //bw.write("R is "+moveR[0]+" "+moveR[1]+"\n");
            //bw.write("B is "+moveB[0]+" "+moveB[1]+"\n");

            // 움직였는데 두 공 모두 변화 없는 경우
            if(rx==moveR[0] && ry==moveR[1] && bx==moveB[0] && by==moveB[1]) {
                //bw.write("not move! pass. \n\n");
                continue;
            }


            // 파란 구슬도 구멍에 도착했다면 해당 경로는 버리기
            if(moveB[0] == ox && moveB[1] == oy) {
                continue;
            }
            // 빨간 구슬이 구멍에 도착했는가
            if(moveR[0] == ox && moveR[1] == oy) {
                result = m[0];
                break;
            }

            // 그렇지 않다면 현위치에서 3방향으로 이동 추가 -> 방금 왔던 쪽은 안됨
            // d (0-왼 / 1-오 / 2-위 / 3-아래)
            for(int d=0; d<4; d++) {
                if(d == m[1]){
                    continue;
                }else if(d==0 && m[1]==1) {
                    continue;
                } else if(d==1 && m[1]==0) {
                    continue;
                } else if(d==2 && m[1]==3) {
                    continue;
                } else if(d==3 && m[1]==2) {
                    continue;
                }
                //bw.write("move:"+d+"\n");
                PQ.add(new int[]{m[0]+1, d, moveR[0], moveR[1], moveB[0], moveB[1]});
            }

            //bw.write("\n");
        }
            
        bw.write(result+" ");
        bw.flush();
    }

    // 특정 좌표에서 시작해서 특정 방향으로 기울였을때, 도착하는 곳
    // direct (0-왼 / 1-오 / 2-위 / 3-아래)
    public static int[] move(int direct, int x, int y, int dx, int dy) {
         
        while(true) {
            x += d[direct][0];
            y += d[direct][1];

            // 범위 벗어나기 - 사실상 여기 닿을 일 없음
            if(x<0 || N<=x || y<0 || M<=y) {
                break;
            }
            
            if(maps[x][y].equals("O")) {
                return new int[]{x, y};
            }

            // "." 이 아니면 이동못해
            if(!maps[x][y].equals(".")) {
                x -= d[direct][0];
                y -= d[direct][1];
                return new int[]{x, y};
            }
            
            // 상대 공이 있는 곳도 안돼
            if(x==dx && y==dy) {
                x -= d[direct][0];
                y -= d[direct][1];
                return new int[]{x, y};
            }
        }
        return new int[]{0, 0};
    }
}
