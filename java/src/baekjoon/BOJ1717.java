import java.io.*;
import java.util.*;

// 집합의 표현
public class BOJ1717 {
    static int[] parent;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 크기가 N+1인 집합
        parent = new int[N+1];
        for(int i = 0; i< N+1; i++) {
            parent[i] = i;
        }

        for(int j=0; j<M; j++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(cmd == 0) {
                union(a, b);
            } else if(cmd == 1) {
                if(find(a) == find(b)) {
                    bw.write("YES\n");
                } else {
                    bw.write("NO\n");
                }
            }
        }
        bw.flush();
    }

    static void union(int a, int b) {
        int A = find(a);
        int B = find(b);

        if(A != B) {
            parent[B] = A;
        }
    }

    static int find(int a) {
        if (a == parent[a]) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }

}
