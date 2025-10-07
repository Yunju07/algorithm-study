import java.util.*;

// 양궁대회
// 1. 맞출 수 있으면 맞춘다
// 2. 안 맞추고 넘어간다
class PRO92342 {
    int N;
    int[] Info, temp;
    // [r1, r2, ... , r11, 점수차]
    List<int[]> results = new ArrayList<int[]>();
    public int[] solution(int n, int[] info) {
        N = n;
        Info = info;
        temp = new int[12];
        
        dfs(0, 0, 0, n);
        
        // 이길 수 없음
        if(results.size()==0) {
            return new int[]{-1};
        }
        
        results.sort(Comparator.comparing((int[] a) -> -a[11])
                    .thenComparing(a -> -a[10])
                    .thenComparing(a -> -a[9])
                    .thenComparing(a -> -a[8])
                    .thenComparing(a -> -a[7])
                    .thenComparing(a -> -a[6])
                    .thenComparing(a -> -a[5])
                    .thenComparing(a -> -a[4])
                    .thenComparing(a -> -a[3])
                    .thenComparing(a -> -a[2])
                    .thenComparing(a -> -a[1])
                    .thenComparing(a -> -a[0]));

        
        int[] result = results.get(0);
        int[] answer = new int[11];
        for(int i=0; i<11; i++) {
            answer[i] = result[i];
        }
        
        return answer;
    }
    
    // idx-과녁 번호, score1-어피치 점수, score2-라이언 점수
    // 남은 과녁 갯수
    public void dfs(int idx, int score1, int score2, int left) {
        if(idx == 11) {
            if(score2 > score1 && left == 0) {
                temp[11] = score2 - score1;
                int[] result = temp.clone();
                results.add(result);
            }
            return;
        }
        
        int score = 10 - idx;
        int info = Info[idx];   // 상대편 갯수
        
        // 마지막 과녁인 경우 - 그냥 남는거 다 쓰기
        if(idx == 10) {
            temp[idx] = left;
            if(left > info) {
                dfs(idx+1, score1, score2+score, 0);
            } else if(left < info) {
                dfs(idx+1, score1+score, score2, 0);
            } else {
                dfs(idx+1, score1, score2, 0);
            }
            return;
        }
        
        // 안 맞추는 경우 -> 상대편이 득점 || 둘 다 득점없음
        temp[idx] = 0;
        if(info > 0) {
            dfs(idx+1, score1+score, score2, left);
        } else {
            dfs(idx+1, score1, score2, left);
        }
        
        // 맞출 수 있으면 맞추는 경우
        if(left > info) {
            temp[idx] = info+1;
            dfs(idx+1, score1, score2+score, left-info-1);
        }
    }
}

