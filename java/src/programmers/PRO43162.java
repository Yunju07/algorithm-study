import java.util.*;

class Solution {
    static int[] parent;
    public int solution(int n, int[][] computers) {
        
        parent = new int[n];
        for(int i=0; i<n; i++) {
            parent[i] = i;
        }
        
        for(int i=0; i<n; i++) {
            for(int j=i+1; j<n; j++) {
                if(computers[i][j] == 1) {
                    union(i, j);
                }
            }
        }
        
        Set<Integer> net = new HashSet<>();
        for(int p: parent) {
            net.add(find(p));
            //net.add(p); -> find 호출을 안해주면 경로 압축이 제대로 반영되지 않음
        }
        
        int answer = net.size();
        return answer;
    }
    
    public void union(int a, int b) {
        int A = find(a);
        int B = find(b);
        
        if(A != B) {
            parent[B] = A;
        }
    }
    
    public int find(int a) {
        if(parent[a] == a) {
            return a;
        }
        
        return parent[a] = find(parent[a]);
    }
}

