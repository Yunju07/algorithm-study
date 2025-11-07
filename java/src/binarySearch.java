import java.util.*;

class binarySearch {
    static int[] arr;
    public int solution() {
        
        arr = new int[]{13, 13, 15, 17, 17, 17, 20, 20};

        // upperBound와 lowerBound를 활용한 배열내 원소 갯수 구하기
        int answer = upperBound(17) - lowerBound(17);
        
        return answer;
    }
    
    public int lowerBound(int target) {
        int low = 0;
        int high = arr.length - 1;
        
        while(low < high) {
            int mid = (low + high) / 2;
            
            if(arr[mid] < target) {
                low = mid+1;
            } else {
                high = mid;
            } 
        }
        return low;
    }
    
    public int upperBound(int target) {
        int low = 0;
        int high = arr.length - 1;
        
        while(low < high) {
            int mid = (low + high) / 2;
            
            if(arr[mid] <= target) {
                low = mid+1;
            } else {
                high = mid;
            } 
        }
        return low;
    }
}
