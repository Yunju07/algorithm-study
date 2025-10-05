import java.util.*;

// 성격 유형 검사
class PRO118666 {
    public String solution(String[] survey, int[] choices) {
        String[][] type = new String[4][2];
        type[0] = new String[]{"R", "T"};
        type[1] = new String[]{"C", "F"};
        type[2] = new String[]{"J", "M"};
        type[3] = new String[]{"A", "N"};

        HashMap<String, Integer> point = new HashMap<>();
        for(int i=0; i<4; i++) {
            for(int j=0; j<2; j++) {
                point.put(type[i][j], 0);
            }
        }
        
        String[] t;
        int choice, p;
        for(int i=0; i<survey.length; i++) {
            t = survey[i].split("");
            choice = choices[i];
            
            if(choice == 4) continue;   // 모르겠음
            
            if(choice < 4) { // 비동의
                p = 4 - choice;
                point.replace(t[0], point.get(t[0])+p);
            } else { // 동의
                p = choice - 4;
                point.replace(t[1], point.get(t[1])+p);
            }
        }
        
        String answer = "";
        
        for(int i=0; i<4; i++) {
            String t1 = type[i][0];
            String t2 = type[i][1];
            if(point.get(t1) >= point.get(t2)) {
                answer += t1;
            } else {
                answer += t2;
            }
        }
                
        return answer;
    }
}
/*
    [R, T]
    [C, F]
    [J, M]
    [A, N]
*/
