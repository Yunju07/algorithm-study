import java.util.*;
import java.math.*;

// 파라매트릭 서치
class Solution {
    static int[] time;
    public long solution(int n, int[] times) {
        time = times;
        
        long low = 0;
        long high = 0;
        for(int t: times) {
            high = Math.max(high, t);
        }
        high *= n;
        long mid = 0;
        
        //System.out.println(isPossible(70, n));
        while(high > low) {
            mid = (high + low) / 2;
            
            // 가능 -> 최소를 위해 
            if(isPossible(mid, n)) {
                high = mid;
            } else {
                low = mid+1;
            }
    
        }
        
        return high;
    }
    
    // T초 내에 N명의 사람을 검사할 수 있나?
    public boolean isPossible(long T, int N) {
        long total = 0;
        for(int t: time) {
            total += (T / t);  // 심사위원 한명이 T초 동안 검사할 수 있는 사람수 
            
            if(total >= N) {
                return true;
            }
        }
        
        return false;
    }
    
}
