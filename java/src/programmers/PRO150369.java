import java.util.*;
import java.io.*;
import java.math.*;

// 택배 배달과 수거하기
class PRO150369 {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = -1;
        
        int total_deliver=0;
        int total_pickup=0;
        int max_p = 0;
        
        for(int i=0; i<n; i++) {
            total_deliver += deliveries[i];
            total_pickup += pickups[i];
            
            if(deliveries[i]>0 || pickups[i]>0) {
                max_p = i;
            }
        }
        
        int d, p, size, new_max;
        long length = 0;
        while(total_deliver+total_pickup > 0) {
            length += 2*(max_p+1);
            
            // 1. 배달 과정
            new_max = -1;
            d=cap;
            for(int i=max_p; i>=0; i--) {
                if (deliveries[i] > 0) {
                    size = Math.min(deliveries[i], d);
                    deliveries[i] -= size;
                    total_deliver -= size;
                    d -= size;
                }
                
                if(deliveries[i] != 0) {
                    new_max = i;
                    break;
                }
            }
            
            // 2. 수거 과정
            p=cap;
            for(int i=max_p; i>=0; i--) {
                if (pickups[i] > 0) {
                    size = Math.min(pickups[i], p);
                    pickups[i] -= size;
                    total_pickup -= size;
                    p -= size;
                }
                
                if(pickups[i] != 0) {
                    new_max = Math.max(new_max, i);
                    break;
                }
            }
            
            // 전체 과정 -> 한번 나갔다 돌아올 때, 찍는 최대 지점을 계속 저장해둔다.
            max_p = new_max;
            if(max_p == -1) {
                break;
            }
        }
        
        answer = length;
        
        return answer;
    }
}
