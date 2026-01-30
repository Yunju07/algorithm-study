// n퍼즐
// 제한 시간 내에 모두 해결하기 위한 숙련도의 최솟값
// 특정 숙련도에서 가능한지 여부 -> 구현
// 숙련도의 최소 -> 이분 탐색
class Solution {
    public static int[] D, T;
    public static long L;
    public int solution(int[] diffs, int[] times, long limit) {
        
        D = diffs;
        T = times;
        L = limit;
        
        int min = 1;
        int max = 0;
        for(int d: diffs) {
            max = Math.max(max, d);
        }
        
        // 이분 탐색
        int mid = 0;
        while(min < max) {
            mid = (min + max) / 2;
            
            if(isPossible(mid, limit)) {
                max = mid;
            } else {
                min = mid + 1;
            }
        }
        
        return max;
    }
    
    public boolean isPossible(int level, long limit) {
        long total_time = 0;
        int time_prev = 0;
        int diff, time;
        for(int idx=0; idx< D.length; idx++) {
            if(idx != 0) {
                time_prev = T[idx-1];
            }
            diff = D[idx];
            time = T[idx];
            
            if(diff <= level) {
                total_time += time;
            } else {
                total_time += (time+time_prev) * (diff - level);
                total_time += time;
            }
            
        }
        
        if(total_time <= limit) {
            return true;
        }
        return false;
    }
}

