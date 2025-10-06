import java.util.*;
import java.math.*;

// k진수에서 소수 개수 구하기
class PRO92335 {
    public int solution(int n, int k) {
        
//         // 에라토스테네스의 체
//         boolean[] isPrime = new boolean[N+1];
//         Arrays.fill(isPrime, true);
//         isPrime[1] = false;
//         isPrime[2] = true;
        
//         for(int i=2; i<=Math.sqrt(N); i++) {
//             // 소수인지 체크
//             if(!isPrime[i]) continue;
            
//             // 소수면 그 배수 다 지우기
//             for(int a=(i*i); a<=N; a+=i) {
//                 isPrime[a] = false;
//             }
//         }
        
        int answer = 0;
        String[] nums = change(n, k).split("0");
        //System.out.println(Arrays.toString(nums));
            
        for(String num: nums) {
            if(num.equals("")) continue;
            
            long lnum = Long.parseLong(num);
            
            if(lnum==1) continue;
                
            boolean prime = true;
            for(long i=2; i<=Math.sqrt(lnum); i++) {
                if((lnum % i) == 0) {
                    prime = false;
                    break;
                }
            }
            
            if(prime) answer++;
        }
        return answer;
    }
    
    // k진수로 변경
    public String change(int n, int k) {
        long result = 0;
        
        long d = 1;
        while(n >= k) {
            result += d * (n % k);
            
            n /= k;
            d *= 10;
        }
        result += d*n;
        
        return String.valueOf(result);
    }
}

// 진수 형태 바꾸기
// 0 기준으로 Split
// 모든 숫자가 각각 소수인지 찾기 -> 에라토스테네스의 체 x
