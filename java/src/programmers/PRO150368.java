import java.util.*;

// 이모티콘 할인행사
class PRO150368 {
    int[][] user;
    int[] emotics, comb, temp, rate;
    List<int[]> combination, results;
    public int[] solution(int[][] users, int[] emoticons) {
        user = users;
        emotics = emoticons;
        
        rate = new int[4];
        rate[0] = 10;
        rate[1] = 20;
        rate[2] = 30;
        rate[3] = 40;
        
        combination = new ArrayList<>();
        comb = new int[emotics.length];
        comb_emotics(0);
        
        results = new ArrayList<int[]>();
        calcaulate_total();
        
        // 플러스 가입자수, 판매액수로 정렬
        results.sort(Comparator.comparingInt((int[] n) -> -n[0]).
                    thenComparingInt(n -> -n[1]));
        
        int[] answer = results.get(0);
        
        return answer;
    }
    
    public void calcaulate_total() {
        for(int[] com: combination) {
            int[] user_buy = new int[user.length];
            for(int e=0; e<com.length; e++) {
                for(int i=0; i< user.length; i++) {
                    if(user[i][0] <= com[e]) {
                        user_buy[i] += calculate(com[e], emotics[e]);
                    }
                }   
            }
            
            // 이모티콘 구매가격과 플러스 사용수 
            int emo_plus = 0;
            int emo_sum = 0;
            for(int u=0; u<user.length; u++) {
                if(user_buy[u] >= user[u][1]) {
                    emo_plus++;
                } else {
                    emo_sum += user_buy[u];
                }
            }
            
            results.add(new int[] {emo_plus, emo_sum});
        }
    }
    
    // 이모티콘의 할인가 계산
    public int calculate(int rate, int price) {
        return price * (100 - rate) /100;
        
    }
    
    // 이모티콘 할인율 조합
    public void comb_emotics(int depth) {
        if(depth == emotics.length) {
            temp = comb.clone();
            combination.add(temp);
            return;
        }
        
        for(int r: rate) {
            comb[depth] = r;
            comb_emotics(depth+1);
        }
    }
       
}
