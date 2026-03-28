import java.util.*;
import java.io.*;

// 2025 하반기 오전 1번
// 택배 하차
public class test2 {
    static int N, M;
    static int[][] map;
    static List<int[]> box;
    public static void main(String[] args) throws Exception {
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        //  [k, h, w, c]
        box = new ArrayList<int[]>();

        for(int m=0; m<M; m++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken())-1;
            box.add(new int[]{k, h, w, c});

            // map에 그리기
            for(int row=0; row<h; row++) {
                for(int col=c; col<w+c; col++) {
                    map[row][col] = k;
                }
            }
            // 중력으로 떨어뜨리기
            gravity();
        }
        //printMap();

        int dir = 0; // (0-좌측 1-우측)
        while(box.size()>0) {
            if(dir == 0) {
                // [idx, k]
                List<int[]> can = new ArrayList<int[]>();

                int idx=0;
                for(int[] b: box) {
                    if(checkLeft(b[0], b[1], b[2], b[3])) {
                        can.add(new int[]{idx, b[0]});
                    }
                    idx++;
                }

                if(can.size()==0) break;

                can.sort(Comparator.comparingInt((int[] n) -> n[1]));

                box.remove(can.get(0)[0]);
                remove(can.get(0)[1]);

                System.out.println(can.get(0)[1]);

                gravity();
                dir = 1;
            } else if(dir == 1) {
                // [idx, k]
                List<int[]> can = new ArrayList<int[]>();

                int idx=0;
                for(int[] b: box) {
                    if(checkRight(b[0], b[1], b[2], b[3])) {
                        can.add(new int[]{idx, b[0]});
                    }
                    idx++;
                }

                if(can.size()==0) break;

                can.sort(Comparator.comparingInt((int[] n) -> n[1]));

                box.remove(can.get(0)[0]);
                remove(can.get(0)[1]);

                System.out.println(can.get(0)[1]);

                gravity();
                dir = 0;
            }
        }
    }

    // 공간의 모든 물건 중력 이동
    // map과 box활용
    public static void gravity() {
        int N = map.length;

        // 들어온 순서대로 검사
        for(int[] b: box) {
            // [k, h, w, c]
            int k = b[0];   // 택배 번호
            int h = b[1];
            int w = b[2];
            int c = b[3];   // 좌측에서 떨어진 좌표

            // 가장 낮은 부분 구하기
            int min_r = N-1;
            for(int i=N-1; i>=0; i--) {
                if(map[i][c] == k) {
                    min_r = i;
                    break;
                }
            }
            //System.out.println("min_r: "+min_r);

            // 모든 밑면 아래 공간을 체크해서
            // 이동할 수 있는 만큼(최솟값) 이동
            int min = Integer.MAX_VALUE;
            for(int col=c; col<w+c; col++) {
                int val = 0;
                for(int row=min_r+1; row<N; row++) {
                    if(map[row][col] == 0) {
                        val++;
                    } else{
                        break;
                    }
                }
                min = Math.min(min, val);
            }
            // min만큼 아래로 이동
            // -map에 k 지우기
            // -map에 min만큼 아래에 k그리기

            for(int row=min_r; row>=min_r-h+1; row--) {
                for(int col=c; col<w+c; col++) {
                    map[row][col] = 0;
                }
            }

            for(int row=min_r+min; row>=min_r+min-h+1; row--) {
                for(int col=c; col<w+c; col++) {
                    map[row][col] = k;
                }
            }

        }
    }

    // box = [k, h, w, c]
    // 좌측으로 뺄 수 있는 택배인지 확인하는 함수
    public static boolean checkLeft(int k, int h, int w, int c) {
        int r;
        for(r=0; r<N; r++){
            if(map[r][c] == k) {
                break;
            }
        }

        //(r , 0) ~ (r+h-1 , c-1) 까지의 직사각형 구간이 모두 0이어야함
        boolean result = true;
        for(int i=r; i<r+h; i++){
            for(int j=0; j<c; j++) {
                if(map[i][j] != 0) {
                    return false;
                }
            }
        }
        return result;
    }

    // 우측으로 뺄 수 있는 택배인지 확인하는 함수
    public static boolean checkRight(int k, int h, int w, int c) {
        int r;
        for(r=0; r<N; r++){
            if(map[r][c] == k) {
                break;
            }
        }

        //(r , c+width-1) ~ (r+h-1 , N-1) 까지의 직사각형 구간이 모두 0이어야함
        boolean result = true;
        for(int i=r; i<r+h; i++){
            for(int j=c+w; j<N; j++) {
                if(map[i][j] != 0) {
                    return false;
                }
            }
        }
        return result;
    }

    // k번호 택배 빼기
    public static void remove(int k) {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(map[i][j] == k) {
                    map[i][j]=0;
                }
            }
        }
    }

    public static void printMap(){
        int n = map.length;
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println("");
        }

        System.out.println("");
    }
}