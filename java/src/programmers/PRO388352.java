import java.util.*;

// 비밀 코드 해독
class Solution {
    static int N, M;
    static List<int[]> comb;
    static int[] arr, temp, an, target;
    static int[][] query;
    public int solution(int n, int[][] q, int[] ans) {
        N = n;
        
        arr = new int[n];
        for(int i=1; i<=n; i++) {
            arr[i-1] = i;
        }
        //System.out.println(Arrays.toString(arr));
        
        // n개 중 5개를 뽑은 조합
        temp = new int[5];
        comb = new ArrayList<int[]>();
        combination(0, 0, 5);
        
        //System.out.println(comb.size());
        M = q.length;
        an = ans;
        query = q;
        int answer = 0;
        
        for(int[] c: comb) {
            target = c;
            if(isPossible()) {
                answer++;
            }
        }
        
        return answer;
    }
    
    // target이 가능한 암호인지 
    // int[][] query, int[] an
    public boolean isPossible() {
        Map<Integer, Integer> map = new HashMap<>();
        for(int n: target) {
            map.put(n, 1);
        }
        
        for(int i=0; i<M; i++) {
            int total=0;
            for(int n: query[i]) {
                if(map.getOrDefault(n, 0) == 1) {
                    total++;
                }
            }
            
            if(total != an[i]) {
                return false;
            }
        }
        
        return true;
    }
    
    // n개 중 서로 다른 5개의 정수 -> 조합
    public void combination(int depth, int d, int r) {
        if(depth == r) {
            int[] result = temp.clone();
            comb.add(result);
            return;
        }
        
        for(int idx=d; idx<N; idx++) {
            temp[depth] = arr[idx];
            combination(depth+1, idx+1, r);
        }
    }
}
