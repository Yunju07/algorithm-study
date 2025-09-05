import java.util.*;
import java.math.*;
import java.io.*;

// 새로 생성한 정점 찾는 법
// 1. 나가는 간선은 2개이상(3개부터는 무조건 얘가)
// 2. 들어오는 간선 0

// 각 그래프의 모양 판별하는 법: 정점 갯수와 간선 갯수
// 도넛 - n: n정점 n간선
// 막대 - n: n정점 n-1간선
// 8자 - n: 2n+1정점 2n+2간선
class PRO258711 {
    static int[] parent, edge;
    static Set<Integer>[] nodes;
    public int[] solution(int[][] edges) throws Exception {

        // 새로 생성한 정점 찾기
        int newV = -1;
        int[] out = new int[1_000_001];
        int[] in = new int[1_000_001];

        for(int[] edge: edges) {
            int a = edge[0];
            int b = edge[1];

            out[a] += 1;
            in[b] += 1;
            if(out[a]>2) {
                newV = a;
            }
        }
        if(newV == -1) {
            for(int a=1; a<1_000_00 ; a++){
                if(out[a] >= 2 && in[a] == 0){
                    newV = a;
                }
            }
        }

        // 남은 그래프의 모양과 갯수 파악하기
        parent = new int[1_000_001];
        for(int i=1; i<=parent.length-1; i++) {
            parent[i] = i;
        }

        edge = new int[1_000_001];   // 간선 갯수 저장
        nodes = new Set[1_000_001];  // 노드 갯수 저장
        for(int i=1; i<=nodes.length-1; i++) {
            nodes[i] = new HashSet();
        }
        int[] root = new int[1_000_001];

        for(int[] e: edges){
            int a = e[0];
            int b = e[1];

            if(a == newV) {
                if(parent[b] == b){
                    nodes[b].add(b);
                    root[b] = 1;
                }
                continue;
            }

            if(b == newV) {
                if(parent[a] == a){
                    nodes[a].add(a);
                    root[a] = 1;
                }
                continue;
            }

            // 정렬은 여기서 해야함
            int A = find(a);
            int B = find(b);

            if(A > B) {
                int temp = B;
                B = A;
                A = temp;
            }

            // 이미 한 그룹 - 간선만 증가
            if(A == B) {
                if(a == b) {
                    nodes[A].add(a);
                    root[A] = 1;
                }
                edge[A] += 1;

            } else {
                // 두 그래프 합치기
                edge[A] = edge[A] + edge[B] + 1;

                root[A] = 1;
                root[B] = 0;

                nodes[A].add(a);
                nodes[A].add(b);
                for(int c: nodes[B]){
                    nodes[A].add(c);
                }
                union(A, B);
            }
        }

        // 각 그래프들 모양 판별
        int d = 0;
        int m = 0;
        int p = 0;
        for(int i=1; i<root.length; i++) {
            if(root[i]==1) {
                int n = nodes[i].size();
                int v = edge[i];

                if(n == 0){
                    continue;
                }
                d += checkD(n, v);
                m += checkM(n, v);
                p += checkP(n, v);
            }
        }

        int[] answer = new int[]{newV, d, m, p};
        return answer;
    }

    public static int checkD(int n, int v){

        if(n == v) {
            return 1;
        }
        return 0;
    }
    public static int checkM(int n, int v){
        if(n-1 == v) {
            return 1;
        }
        return 0;
    }
    public static int checkP(int n, int v){
        if(n % 2 == 0 || n < 3) {
            return 0;
        }
        if((n-1)/2 == (v-2)/2) {
            return 1;
        }
        return 0;
    }

    public static void union(int a, int b) {
        int A = find(a);
        int B = find(b);

        if(A != B) {
            parent[B] = A;
        }
    }

    public static int find(int a) {
        if(a == parent[a]) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }
}