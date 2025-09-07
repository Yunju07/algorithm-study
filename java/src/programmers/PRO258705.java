import java.util.*;

// 타일의 모양은 총 4가지
// 1. 그냥 삼각형
// 2,3. 누운 마름모 삼각형
// 4. 서있는 마름모 삼각형
class PRO258705 {
    public int solution(int n, int[] tops) {

        int answer = 0;

        int[] dp = new int[n * 2 + 2];
        dp[n * 2] = 1;
        dp[n * 2 + 1] = 1;
        int idx =  n * 2 - 1;

        while(true) {
            if(idx == -1){
                break;
            }

            // 인덱스가 홀수 - 윗변과 공유된 삼각형
            if(idx%2 == 1) {
                // 삼각형 쓴 경우
                dp[idx] += dp[idx+1];

                // 마름모
                dp[idx] += dp[idx+2];

                // 윗삼각형
                dp[idx] += (tops[idx/2] *dp[idx+1]);

            } else {
                // 짝수 - 삼각형 쓴 경우
                dp[idx] += dp[idx+1];

                // 마름모 쓴 경우
                dp[idx] += dp[idx+2];
            }
            dp[idx] = dp[idx] % 10007;
            idx--;
        }

        return dp[0] % 10007;
    }
}