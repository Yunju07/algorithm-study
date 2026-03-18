import java.util.*;
import java.lang.*;
import java.io.*;
import java.math.*;

// 치킨 거리
// 구현, 조합
class BOJ15686 {
    public static int[] output;
    public static int[][] maps;
    public static List<int[]> house, store, cand, nstore;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        maps = new int[N][N]; // 빈칸 0, 집 1, 치킨집 2
        //int h = 0;
        int s = 0;
        house = new ArrayList<>();
        store = new ArrayList<>();
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
                if(maps[i][j] == 1) {
                    house.add(new int[]{i, j});
                }else if(maps[i][j] == 2) {
                    s++;
                    store.add(new int[]{i, j});
                }
            }
        }

        // 순서 상관없이 M개 치킨집 고르기 -> 조합 (중복x)
        // s개 중에 M개 고르기
        cand = new ArrayList<int[]>();
        output = new int[M];
        combination(0, s, M, 0);

        // 모든 안폐업 조합의 치킨 거리 구하고 최소값 알기
        //List<Integer> len = new ArrayList<>();
        int result = Integer.MAX_VALUE;
        for(int[] out: cand) {
            // out은 M개의 폐업시키 ㅇ낳을 조합 치킨 집들의 리스트 인덱스가 저장
            //System.out.println(Arrays.toString(out));

            // 남아있는 치킨집 구하기
            nstore = new ArrayList<>();
            for(int i=0; i<s; i++) {
                //boolean isDrop = false;
                for(int j: out) {
                    if(i==j) nstore.add(store.get(i));
                }
            }

            // 남아있는 치킨집에 기반해서 마을의 치킨거리 구하기
            int val = 0;
            for(int[] h: house) {
                // h[0], h[1] 위치의 집의 최소 치킨거리
                int min = 300;

                for(int[] ns: nstore){
                    min = Math.min(min, calculate(ns[0], ns[1], h[0], h[1]));
                }
                val += min;
            }
            result = Math.min(val, result);
        }
        System.out.println(result);

    }

    public static int calculate(int r1, int c1, int r2, int c2) {
        return Math.abs(r1-r2) + Math.abs(c1-c2);
    }

    public static void combination(int depth, int n, int r, int start) {
        if(depth == r) {
            int[] arr = output.clone();
            cand.add(arr);
            return;
        }

        for(int i=start; i<n; i++) {
            output[depth] = i;
            combination(depth+1, n, r, i+1);
        }

    }
}