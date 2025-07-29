import java.io.*;
import java.util.*;

// 두 배열의 합
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        // 배열 A
        int N = Integer.parseInt(br.readLine());
        long[] A = new long[N];
        long[] A_prefix = new long[N];

        st = new StringTokenizer(br.readLine());
        for(int i =0; i < N; i++) {
            A[i] = Long.parseLong(st.nextToken());

            if (i == 0) {
                A_prefix[i] = A[i];
            } else {
                A_prefix[i] = A[i] + A_prefix[i-1];
            }
        }

        // 배열 B
        int M = Integer.parseInt(br.readLine());
        long[] B = new long[M];
        long[] B_prefix = new long[M];

        st = new StringTokenizer(br.readLine());
        for(int i =0; i < M; i++) {
            B[i] = Long.parseLong(st.nextToken());

            if (i == 0) {
                B_prefix[i] = B[i];
            } else {
                B_prefix[i] = B[i] + B_prefix[i-1];
            }
        }

        // 누적합을 활용하여, 부분 배열의 합을 또 몽땅 저장
        Map<Long, Long> map = new HashMap<>();
        for(int a = 0; a < N; a ++) {
            for(int b = a; b < N; b++) {
                // A 부분 배열의 합
                long t1;
                if (a == 0) {
                    t1 = A_prefix[b];
                } else {
                    t1 = A_prefix[b] - A_prefix[a-1];
                }
                map.put(t1, map.getOrDefault(t1, 0L) + 1);
            }
        }

        Map<Long, Long> map2 = new HashMap<>();
        for(int c = 0;  c < M; c++) {
            for(int d = c; d < M ; d++) {
                // B 부분 배열의 합
                long t2;
                if (c == 0) {
                    t2 = B_prefix[d];
                } else {
                    t2 = B_prefix[d] - B_prefix[c-1];
                }
                map2.put(t2, map2.getOrDefault(t2, 0L) + 1);
            }
        }

        long res = 0;
        // A + B 조합
        for (Long key: map.keySet()) {
            long key2 = T - key;

            long a = map.getOrDefault(key, 0L);
            long b = map2.getOrDefault(key2, 0L);

            if(a*b > 0) {
                res += (a * b);
            }
        }

        // A만 구성 or B만 구성 -> 안돼
        bw.write(res+"");
        bw.flush();

    }
}