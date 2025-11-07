import java.util.*;

// H-index
class PRO42742 {
    public int solution(int[] citations) {
        
        // 정렬
        Arrays.sort(citations);
        //System.out.println(Arrays.toString(citations));
        
        // 이분탐색
        int N = citations.length;
        int low = 0;
        int high = citations[N-1];
        while(low <= high) {
            int mid = (low+high) / 2;
            
            int count = 0;
            for(int c: citations) {
                if(c >= mid) {
                    count++;
                }
            }
            
            if(count >= mid) {
                low = mid+1;
            } else {
                high = mid-1;
            }
        }
        
        int answer = high;
        return answer;
    }
}
// n편중 h이상 인용된 논문이 h편 이상
