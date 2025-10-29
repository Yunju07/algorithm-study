import java.util.*;
import java.math.*;

// 소수 - 에라토스테네스의 체
// 종이 조각으로 만들 수 있는 수 - 조합
class Solution {
    boolean[] visited;
    String[] str, arr;
    Set<Integer> set;
    public int solution(String numbers) {
        // 에라토스테네스
        int N = 9_999_999;  
        boolean[] isPrime = new boolean[N];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        for(int i=2; i<=Math.sqrt(N); i++) {
            if(isPrime[i]) {
                for(int j=i*i; j<N; j+=i) {
                    isPrime[j] = false;
                }
            }
        }
        
        // 순열
        // [0, 1, 1]로 만들 수 있는 모든 숫자 만들고 - 각 소수 판별
        str = numbers.split("");
        set = new HashSet<>();
        
        for(int len=1; len<=str.length; len++) {
            arr = new String[len];
            visited = new boolean[str.length];
            permutation(0, len);
        }
        
        int answer = 0;
        for(int s: set) {
            if(isPrime[s]) answer++;
        }
        
        return answer;
    }
    
    public void permutation(int depth, int r) {
        if(depth == r) {
            // 현재 arr를 숫자로 만들어서 set에 저장
            int val = toInt();
            set.add(val);
            return;
        }
        
        for(int idx=0; idx<str.length; idx++) {
            if(visited[idx]) {
                continue;
            }
            
            visited[idx] = true;
            arr[depth] = str[idx];
            permutation(depth+1, r);
            visited[idx] = false;
        }
    }
    
    // arr: [0, 1, 1] -> 011로
    public int toInt() {
        String st = "";
        for(String s: arr) {
            st += s;
        }
        
        //return 0;
        return Integer.parseInt(st);
    }
}
