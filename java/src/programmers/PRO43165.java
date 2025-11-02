import java.util.*;

// dfs
class Solution {
    static int answer, tar;
    static int[] number;
    public int solution(int[] numbers, int target) {
        tar = target;
        answer = 0;
        number = numbers;
        
        dfs(0, 0);
        
        return answer;
    }
    public void dfs(int d, int sum) {
        if(d == number.length) {
            if(sum == tar) {
                answer++;
            }
            
            return;
        }
        
        // +하는 경우
        dfs(d+1, sum+number[d]);
        
        // -하는 경우
        dfs(d+1, sum-number[d]);
        
    }
}
