import java.io.*;
import java.util.*;

// 교수님은 기다리지 않는다
public class BOJ3830 {
    static int[] parent;
    static long[] diff;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        while(true) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            if(N==0 && M==0) {
                break;
            }

            // N 종류의 샘플
            parent = new int[N+1];
            diff = new long[N+1];
            for(int i=0; i<=N; i++) {
                parent[i] = i;
                diff[i] = 0;	// 초기에는 자기 자신이 루트니까 차이는 0
            }

            // M 번의 수행
            for(int i=0; i<M; i++) {
                st = new StringTokenizer(br.readLine());
                String cmd = st.nextToken();

                if(cmd.equals("!")) {
                    int a = Integer.parseInt(st.nextToken());
                    int b = Integer.parseInt(st.nextToken());
                    long w = Long.parseLong(st.nextToken());

                    union(a, b, w);

                } else if(cmd.equals("?")) {
                    int a = Integer.parseInt(st.nextToken());
                    int b = Integer.parseInt(st.nextToken());

                    if (find(a) == find(b)) {
                        long res = diff[b] - diff[a];
                        bw.write(res+"\n");

                    } else {
                        bw.write("UNKNOWN\n");
                    }
                }
            }
        }
        bw.flush();

    }

    static void union(int a, int b, long w) {
        int A = find(a);
        int B = find(b);

        if(A != B) {
            parent[B] = A;
            diff[B] =  diff[a] - diff[b] + w;
        }
    }

    static int find(int a) {
        if (a == parent[a]) {
            return a;
        }
        int originParent = parent[a];
        parent[a] = find(parent[a]);	// 집합의 루트로 갱신

        diff[a] += diff[originParent];

        return parent[a];
    }
}
