import java.util.*;

// 가장큰수
class Solution {
    public String solution(int[] numbers) {
        
        int N = numbers.length;
        List<String> nums = new ArrayList<>();
        for(int n: numbers) {
            nums.add(String.valueOf(n));
        }
        
        Collections.sort(nums, (a, b) -> -(a+b).compareTo(b+a));
        
        if(nums.get(0).equals("0")) {
            return "0";
        }
        
        String answer = "";
        for(String str: nums) {
            answer += str;
        }
            
        return answer;
    }
}
// 순열 풀이 -> n! 시간 복잡도 걸림
// 커스텀 정렬
