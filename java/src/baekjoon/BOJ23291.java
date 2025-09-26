import java.util.*;
import java.lang.*;
import java.io.*;
import java.math.*;

// 어항 정리
class BOJ23291 {
    static int N, K;
    static int[] maps;
    static int[][] newMaps, temp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        maps = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            maps[i] = Integer.parseInt(st.nextToken());
        }

        int count = 0;
        while(true) {
            count++;

            // 어항 정리
            get_clean();
            
            // 물고기 수 차이
            int min = Integer.MAX_VALUE;
            int max = 0;
            for(int a: maps) {
                min = Math.min(min, a);
                max = Math.max(max, a);
            }
            
            int k = max - min;
            if(k <= K) {
                break;
            }
        }

        bw.write(count+"\n");
        bw.flush();
    }
    
    // 어항을 한 번 정리
    public static void get_clean(){
        // 1. 물고기 더하기
        add_fish();

        // 2. 첫번째 어항 쌓기 및 물고기 조절 작업
        job1();
        // for(int i=0; i<N; i++) {
        //     System.out.print(maps[i]+" ");
        // }
        // System.out.print("\n");

        // 3. 두번째 작업
        job2();
        // for(int i=0; i<N; i++) {
        //     System.out.print(maps[i]+" ");
        // }
        // System.out.print("\n");
    }

    // 2. 어항 쌓기 while(w2 >= h1)
    // 2-1. 가장 왼쪽에 있는 어항을 그 어항의 오른쪽에 있는 어항의 위에 올림
    // 2-2. 2개 이상 쌓여있는 어항을 모두 공중 부양시킨 다음, 전체를 시계방향으로 90도 회전
    public static void job1() {
        // 2-1. 가장 왼쪽에 있는 어항을 그 어항의 오른쪽에 있는 어항의 위에 올림
        newMaps = new int[2][N-1];
        newMaps[0][0] = maps[0];
        for(int i=0; i<N-1; i++){
            newMaps[1][i] = maps[i+1];
        }

        // 여러개 쌓인 어항의 너비와 높이
        int w1 = 1;
        int h1 = 2;
       
        // 높이가 1인 어항들의 너비
        int w2 = N-h1;

        int tw1=0, th1=0, tw2=0;
        while(w2 >= h1) {
            temp = new int[w1+1][w2];

            // 맨 밑 채우기
            for(int i=0; i<w2; i++) {
                temp[w1][i] = newMaps[h1-1][i+w1];
            }

            // 쌓은 부분 채우기
            for(int i=0; i<w1; i++){
                for(int j=0; j<h1; j++) {
                    temp[i][j] = newMaps[h1-1-j][i];
                }
            }
            newMaps = temp;

            // 너비와 높이 재계산
            tw1 = w1;
            th1 = h1;
            tw2 = w2;
            
            w1 = th1;
            h1 = tw1+1;
            w2 -= th1;
        }
        
        // 물고기 수 조절
        resize();

        // 다시 일자로 펴서 maps에 저장
        updateMaps();
    }

    /*
    이번에는 가운데를 중심으로 왼쪽 N/2개를 공중 부양시켜 
    전체를 시계 방향으로 180도 회전 시킨 다음, 
    오른쪽 N/2개의 위에 놓아야 한다.
    -> 이를 두번 반복
    */
    public static void job2() {
        newMaps = new int[1][N];
        for(int i=0; i<N; i++){
            newMaps[0][i] = maps[i];
        }

        // 두번 반복
        int w, h;
        for(int c=0; c<2; c++) {
            h = newMaps.length;
            w = newMaps[0].length/2;

            temp = new int[2*h][w];

            // 360도 회전 부분 채우기
            for(int i=0; i<h; i++) {
                for(int j=0; j<w; j++) {
                    temp[i][j] = newMaps[h-1-i][w-1-j];
                }
            }

            // 그냥 부분 채우기
            for(int i=h; i<2*h; i++) {
                for(int j=0; j<w; j++) {
                    temp[i][j] = newMaps[i-h][j+w];
                }
            }
            newMaps = temp;
        }
        
        // 물고기 수 조절
        resize();

        // 다시 일자로 펴서 maps에 저장
        updateMaps();
    }

    public static void updateMaps(){
        int h = newMaps.length;
        int w = newMaps[0].length;
        
        int a = 0;
        for(int x=0; x<w; x++) {
            for(int y=h-1; y>=0; y--) {
                if(newMaps[y][x] != 0) {
                    maps[a] = newMaps[y][x];
                    a++;
                }
            }
        }
    }
    
    // newMaps에 저장된 물고기 수를 조절
    // 모든 인접한 두 어항에 대해서, 물고기 수의 차이 / 5 = d 만큼 이동 시키기
    public static void resize() {
        int h = newMaps.length;
        int w = newMaps[0].length;

        temp = new int[h][w];
        for(int i=0; i<h; i++) {
            for(int j=0; j<w; j++) {
                temp[i][j] = newMaps[i][j];
            }
        }

        // 모든 인접 자신의 오른쪽과 아래만 검사
        int[][] direction = new int[2][2];
        direction[0] = new int[]{1, 0};
        direction[1] = new int[]{0, 1};

        int r2, c2, d;
        for(int r1=0; r1<h; r1++) {
            for(int c1=0; c1<w; c1++) {
                if(newMaps[r1][c1] == 0) {
                    continue;
                }
                    
                for(int[] dir: direction){
                    r2 = r1 + dir[0];
                    c2 = c1 + dir[1];   

                    if(r2>=h || c2>=w) {
                        continue;
                    }
 
                    if(newMaps[r2][c2] == 0) {
                        continue;
                    }
                    
                    d = (newMaps[r1][c1] - newMaps[r2][c2]) / 5;
                    if(d!=0) {
                        temp[r1][c1] -= d;
                        temp[r2][c2] += d;
                    }
                }
            }
        }
        newMaps = temp;
        return;
    }

    // 물고기 수가 가장 적은 어항에 물고기를 한마리 넣기
    public static void add_fish() {
        int min = Integer.MAX_VALUE;
        for(int a: maps) {
            min = Math.min(min, a);
        }

        for(int i=0; i<maps.length; i++) {
            if(maps[i] == min) {
                maps[i]++;
            }
        }
    }
}
