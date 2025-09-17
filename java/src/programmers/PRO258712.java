import java.util.*;
import java.math.*;

// System.out.println();
class PRO258712 {
    public int solution(String[] friends, String[] gifts) {
        StringTokenizer st;

        // 선물 지수 계산
        // * 선물 지수: 준 선물의 수 - 받은 선물 수
        Map<String, Integer> counts = new HashMap<>();
        Map<String, Map<String, Integer>> gift = new HashMap<>();

        int count;
        String f1, f2;
        Map<String, Integer> map;
        for(String g: gifts) {
            st = new StringTokenizer(g);
            f1 = st.nextToken();
            f2 = st.nextToken();

            map = gift.getOrDefault(f1, new HashMap<String, Integer>());
            map.put(f2, map.getOrDefault(f2, 0)+1);
            gift.put(f1, map);

            // 선물 준 친구
            count = counts.getOrDefault(f1, 0);
            counts.put(f1, count+1);

            // 선물 받은 친구
            count = counts.getOrDefault(f2, 0);
            counts.put(f2, count-1);

        }

        // 두 사람 간의 비교
        // gitfs: a가 b에게 준 선물이 있는 지
        // count: a의 선물 지수

        Map<String, Integer> results = new HashMap<>();
        Map<String, Integer> empty = new HashMap<>();
        int size = friends.length;
        for(int i=0; i<size; i++) {
            for(int j=i+1; j<size; j++) {
                String a = friends[i];
                String b = friends[j];

                int a_count = gift.getOrDefault(a, empty).getOrDefault(b, 0);
                int b_count = gift.getOrDefault(b, empty).getOrDefault(a, 0);

                //이번 달까지 두 사람 사이에 더 많은 선물을 준 사람이 다음 달에 선물을 하나 받습니다.
                if(a_count > b_count) {
                    results.put(a, results.getOrDefault(a, 0)+1);
                } else if(a_count < b_count) {
                    results.put(b, results.getOrDefault(b, 0)+1);
                } else {
                    // 2. 두 사람이 선물을 주고받은 기록이 하나도 없거나 주고받은 수가 같다면,

                    a_count = counts.getOrDefault(a, 0);
                    b_count = counts.getOrDefault(b, 0);

                    // 2-1. 선물 지수가 더 큰 사람이 선물 지수가 더 작은 사람에게 선물을 하나 받습니다.
                    if(a_count > b_count) {
                        results.put(a, results.getOrDefault(a, 0)+1);
                    } else if (a_count < b_count) {
                        results.put(b, results.getOrDefault(b, 0)+1);
                    } else {
                        // 2-3. 선물 지수가 같다면 주고받지 않음
                    }
                }

            }
        }

        int answer = 0;
        for(String key: results.keySet()) {
            //System.out.println(key+" "+ results.get(key));
            answer = Math.max(answer, results.get(key));
        }

        return answer;
    }
}