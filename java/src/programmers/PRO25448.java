import java.util.*;

// DFS
// 양과 늑대
class PRO25448 {
    int N, sheep, wolf, max_s;
    int[] Info;
    List<Integer>[] edge;
    public int solution(int[] info, int[][] edges) {
        
        N = info.length;
        Info = info;
        
        edge = new List[N];
        for(int i=0; i<N; i++) {
            edge[i] = new ArrayList<>();
        }
        for(int[] e: edges) {
            edge[e[0]].add(e[1]);
        }
        
        List<Integer> temp = new ArrayList<>();
        max_s = 0;
        dfs(0, 0, 0, temp);
        
        int answer = max_s;
        return answer;
    }
    
    public void dfs(int node, int sheep, int wolf, List<Integer> temp) {
        int info = Info[node];
        if(info == 0) {
            // 양
            sheep++;
        } else {
            // 늑대
            wolf++;
        }
        
        // 종료 조건1 - 늑대가 잡아먹음
        if(wolf >= sheep) {
            return;
        }
        max_s = Math.max(max_s, sheep);
        
        List<Integer> copy = new ArrayList<>(temp);
        copy.remove(Integer.valueOf(node));
        copy.addAll(edge[node]);
        
        for(int next: copy) {
            dfs(next, sheep, wolf, copy);
        }
    }
}
